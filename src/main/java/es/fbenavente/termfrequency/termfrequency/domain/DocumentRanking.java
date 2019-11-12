package es.fbenavente.termfrequency.termfrequency.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentRanking {
    private String name;
    private BigDecimal ranking;
}
