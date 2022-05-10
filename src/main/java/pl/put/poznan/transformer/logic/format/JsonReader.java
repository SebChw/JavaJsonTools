package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

public class JsonReader implements JsonFormatter{
    private String toBeFormatted;

    public JsonReader(String text){this.toBeFormatted=text;}

    public String getToBeFormatted() {
        return this.toBeFormatted;
    }

    public JsonNode parse(){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(this.getToBeFormatted());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return (jsonNode);
    }
}
