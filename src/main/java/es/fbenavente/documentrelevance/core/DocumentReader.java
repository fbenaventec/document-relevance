package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import es.fbenavente.documentrelevance.domain.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DocumentReader {
    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;

    public List<Document> readAll() {
        List<File> files = loadFiles();
        return files.stream()
                .map(this::readFile)
                .collect(Collectors.toList());
    }

    private Document readFile(File file) {
        return Document.builder()
                .name(file.getName())
                .build();
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

    private File loadRootDocument() {
        return new File(documentRelevanceConfiguration.getDirectory());
    }
}
