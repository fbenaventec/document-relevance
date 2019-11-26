package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import es.fbenavente.documentrelevance.domain.Document;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class DocumentReader {
    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;

    public List<Document> readAll() {
        List<File> files = loadFiles();
        return files.stream()
                .map(this::readFile)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Document> readFile(File file) {
        try {
            List<String> words = extractWordsFromFile(file);
            Map<String, Integer> termFrequency = getTermFrequency(words);
            Document document = Document.builder()
                    .name(file.getName())
                    .words(words.size())
                    .termFrequency(termFrequency)
                    .build();
            return Optional.of(document);
        } catch (IOException e) {
            log.warn("Unable to read file; ignoring|file={}", file.getName(), e);
            return Optional.empty();
        }
    }

    private Map<String, Integer> getTermFrequency(List<String> words) {
        Map<String, List<String>> groupedWords = groupStringsByTerms(words);
        return groupedWords.entrySet().stream()
                .filter(isValidTerm())
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
    }

    private Map<String, List<String>> groupStringsByTerms(List<String> words) {
        return words.stream()
                    .collect(Collectors.groupingBy(normalizeWord()));
    }

    private Function<String, String> normalizeWord() {
        return String::toLowerCase;
    }

    private List<String> extractWordsFromFile(File file) throws IOException {
        Stream<String> lines = null;
        List<String> words = new ArrayList<>();
        try {
            lines = Files.lines(file.toPath());
            String content = lines.collect(Collectors.joining("\n"));
            Pattern pattern = Pattern.compile("\\w+");
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                words.add(matcher.group());
            }
        } finally {
            if (lines != null) {
                lines.close();
            }
        }
        return words;
    }

    private List<File> loadFiles() {
        File root = loadRootDocument();
        if (root.exists() && root.isDirectory() && root.listFiles() != null) {
            return Stream.of(root.listFiles())
                    .filter(File::isFile)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private Predicate<Map.Entry<String, List<String>>> isValidTerm() {
        return entry -> documentRelevanceConfiguration.getTerms() != null
                && documentRelevanceConfiguration.getTerms().contains(entry.getKey());
    }

    private File loadRootDocument() {
        return new File(documentRelevanceConfiguration.getDirectory());
    }
}
