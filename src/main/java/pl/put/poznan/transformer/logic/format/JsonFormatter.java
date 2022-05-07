package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonFormatter {
    public JsonNode parse();
}
