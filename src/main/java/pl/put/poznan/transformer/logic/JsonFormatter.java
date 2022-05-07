package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonFormatter {
    public JsonNode parse();
}
