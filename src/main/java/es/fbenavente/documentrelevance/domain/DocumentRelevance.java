package es.fbenavente.documentrelevance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentRelevance {
    private String name;
    private BigDecimal ranking;
    private BigDecimal inverseDocumentFrequency;
    private BigDecimal termFrequency;
}
