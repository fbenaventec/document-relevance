package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportGenerator {

    private final DocumentRelevanceComponent documentRelevanceComponent;

    public DocumentRelevanceReport generate() {
        List<File> documents = loadDocuments();
        List<DocumentRelevance> documentsRelevance = documents.stream()
                .map(documentRelevanceComponent::getDocumentRelevance)
                .collect(Collectors.toList());
        return DocumentRelevanceReport.builder().documents(documentsRelevance).build();
    }

    private List<File> loadDocuments() {
        return new ArrayList<>();
    }
}
