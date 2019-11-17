package es.fbenavente.documentrelevance.viewer;

import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.util.Locale.ENGLISH;

@Component
public class ReportViewer {
    private static final String DECIMAL_FORMAT = "0.00";
    private static final String HEADER = "Document relevance report:";
    private static final String EMPTY_MESSAGE = "Directory is empty";
    private final PrintStream outputStream;

    public ReportViewer() {
        this(System.out);
    }

    public ReportViewer(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void view(DocumentRelevanceReport report) {
        printHeader();
        if (report == null || CollectionUtils.isEmpty(report.getDocuments())) {
            outputStream.println(EMPTY_MESSAGE);
        } else {
            report.getDocuments().forEach(this::printLine);
        }
    }

    private void printHeader() {
        outputStream.println(HEADER);
    }

    private void printLine(DocumentRelevance documentRelevance) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT, DecimalFormatSymbols.getInstance(ENGLISH));
        outputStream.println(documentRelevance.getName() + " " + decimalFormat.format(documentRelevance.getRanking()));
    }
}
