package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportGenerator {

    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;

    public DocumentRelevanceReport generate() {
        List<File> documents = loadDocuments();
        List<DocumentRelevance> documentsRelevance = documents.stream()
                .filter(File::isFile)
                .map(file -> DocumentRelevance.builder()
                        .name(file.getName())
                        .ranking(BigDecimal.valueOf(Math.random()))
                        .build())
                .collect(Collectors.toList());
        return DocumentRelevanceReport.builder().documents(documentsRelevance).build();
    }

    private List<File> loadDocuments() {
        File root = loadRootDocument();
        if (root.exists() && root.isDirectory() && root.listFiles() != null) {
            return Arrays.asList(root.listFiles());
        } else {
            return new ArrayList<>();
        }
    }

    private File loadRootDocument() {
        return new File(documentRelevanceConfiguration.getDirectory());
    }
}
