package pl.put.poznan.jsontools.logic.util;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Combines JSON Node with a String.
 */

public class JsonBundle {
    private JsonNode jsonNode;
    private String string;
    private String yaml=null;
    private String xml = null;

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

    public String getYaml() {
        return yaml;
    }

    public void setYaml(String yaml) {
        this.yaml = yaml;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
}
