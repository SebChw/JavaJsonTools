package pl.put.poznan.transformer.logic.util;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Combines JSON node with a String
 */

public class JsonBundle {
    private JsonNode jsonNode;
    private String string;

    public JsonBundle(JsonNode jsonNode){
        this.jsonNode = jsonNode;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public JsonNode getJsonNode() {
        return jsonNode;
    }

    public void setJsonNode(JsonNode jsonNode) {
        this.jsonNode = jsonNode;
    }
}
