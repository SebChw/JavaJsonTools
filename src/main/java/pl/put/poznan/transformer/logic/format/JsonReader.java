package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.util.JsonBundle;

public class JsonReader implements JsonFormatter{
    private String toBeFormatted;

    public JsonReader(String text){this.toBeFormatted=text;}

    public String getToBeFormatted() {
        return this.toBeFormatted;
    }

    /**
     * Parses string to Json and creates bundle
     * @return
     */
    public JsonBundle parse(){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(toBeFormatted);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonBundle bundle = new JsonBundle(jsonNode, toBeFormatted);

        return (bundle);
    }
}
