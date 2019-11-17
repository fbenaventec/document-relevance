package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportGenerator {
    private final DocumentReader documentReader;

    public DocumentRelevanceReport generate() {
        List<Document> documents = documentReader.readAll();
        List<DocumentRelevance> documentsRelevance = documents.stream()
                .map(file -> DocumentRelevance.builder()
                        .name(file.getName())
                        .ranking(BigDecimal.valueOf(Math.random()))
                        .build())
                .collect(Collectors.toList());
        return DocumentRelevanceReport.builder().documents(documentsRelevance).build();
    }
}
