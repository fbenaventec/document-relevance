package es.fbenavente.documentrelevance.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class Document {
    private String name;
    private List<String> words;
    private Map<String, Integer> termFrequencies;
}
