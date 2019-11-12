package es.fbenavente.termfrequency.termfrequency.viewer;

import es.fbenavente.termfrequency.termfrequency.domain.DocumentRanking;
import es.fbenavente.termfrequency.termfrequency.domain.TermFrequencyReport;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.util.Locale.ENGLISH;

@Component
public class ReportViewer {
    private static final String DECIMAL_FORMAT = "0.00";
    private final PrintStream outputStream;

    public ReportViewer() {
        this(System.out);
    }

    public ReportViewer(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void view(TermFrequencyReport report) {
        if (report == null || CollectionUtils.isEmpty(report.getDocuments())) {
            outputStream.println("");
        } else {
            report.getDocuments().forEach(this::printLine);
        }
    }

    private void printLine(DocumentRanking documentRanking) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT, DecimalFormatSymbols.getInstance(ENGLISH));
        outputStream.println(documentRanking.getName() + " " + decimalFormat.format(documentRanking.getRanking()));
    }
}
