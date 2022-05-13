package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.util.JsonBundle;

/**
 * Concrete component for parsing Json.
 */

public class JsonReader implements JsonFormatter{
    private JsonNode toBeFormatted;

    public JsonReader(JsonNode json){this.toBeFormatted=json;}

    /**
     * Creates an initial json bundle.
     * @return JsonBundle to be further processed by decorators
     */
    public JsonBundle parse(){
        JsonBundle bundle = new JsonBundle(toBeFormatted);

        return bundle;
    }
}
