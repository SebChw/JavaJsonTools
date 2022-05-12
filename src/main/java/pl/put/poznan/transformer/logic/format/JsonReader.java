package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.util.JsonBundle;

public class JsonReader implements JsonFormatter{
    private JsonNode toBeFormatted;

    public JsonReader(JsonNode json){this.toBeFormatted=json;}

    /**
     * Creates an initial json bundle
     * @return
     */
    public JsonBundle parse(){
        JsonBundle bundle = new JsonBundle(toBeFormatted);

        return bundle;
    }
}
