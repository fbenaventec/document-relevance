package es.fbenavente.documentrelevance.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Document {
    private String name;
    private Integer words;
    private Map<String, Integer> termFrequency;
}
