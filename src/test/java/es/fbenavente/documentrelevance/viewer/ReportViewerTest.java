package es.fbenavente.documentrelevance.viewer;

import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class ReportViewerTest {

    @Mock
    private PrintStream outputStream;

    @InjectMocks
    private ReportViewer viewer;

    @Test
    public void given_a_empty_report_should_be_print_only_header_and_empty_message() {
        DocumentRelevanceReport report = new DocumentRelevanceReport();

        viewer.view(report);

        InOrder inOrder = Mockito.inOrder(outputStream);
        inOrder.verify(outputStream).println("Document relevance report:");
        inOrder.verify(outputStream).println("Directory is empty");
    }

    @Test
    public void given_a_report_expect_a_line_with_name_and_ranking_for_each_document() {
        DocumentRelevanceReport report = DocumentRelevanceReport.builder()
                .documents(Arrays.asList(
                        DocumentRelevance.builder().name("First document.txt").ranking(new BigDecimal("0.8")).build(),
                        DocumentRelevance.builder().name("Second document.txt").ranking(new BigDecimal("0.33")).build()
                ))
                .build();

        viewer.view(report);

        InOrder inOrder = Mockito.inOrder(outputStream);
        inOrder.verify(outputStream).println("Document relevance report:");
        inOrder.verify(outputStream).println("First document.txt 0.80");
        inOrder.verify(outputStream).println("Second document.txt 0.33");
    }
}