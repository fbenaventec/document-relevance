package es.fbenavente.termfrequency.termfrequency.viewer;

import es.fbenavente.termfrequency.termfrequency.domain.DocumentRanking;
import es.fbenavente.termfrequency.termfrequency.domain.TermFrequencyReport;
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
    public void given_a_empty_report_should_be_print_only_header_and_empty_results() {
        TermFrequencyReport report = new TermFrequencyReport();

        viewer.view(report);

        Mockito.verify(outputStream).println("");
    }

    @Test
    public void given_a_report_expect_a_line_with_name_and_ranking_for_each_document() {
        TermFrequencyReport report = TermFrequencyReport.builder()
                .documents(Arrays.asList(
                        DocumentRanking.builder().name("First document.txt").ranking(new BigDecimal("0.8")).build(),
                        DocumentRanking.builder().name("Second document.txt").ranking(new BigDecimal("0.33")).build()
                ))
                .build();

        viewer.view(report);

        InOrder inOrder = Mockito.inOrder(outputStream);
        inOrder.verify(outputStream).println("First document.txt 0.80");
        inOrder.verify(outputStream).println("Second document.txt 0.33");
    }
}