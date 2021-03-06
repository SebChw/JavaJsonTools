package pl.put.poznan.jsontools.logic.format;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.jsontools.logic.util.JsonBundle;

/**
 * Concrete component for parsing Json.
 */

public class JsonReader implements JsonFormatter{
    /**
     * A JsonNode to be further processed and formatted
     */
    private JsonNode toBeFormatted;

    public JsonReader(JsonNode json){this.toBeFormatted=json;}

    /**
     * Creates an initial json bundle.
     * The bundle is made of a JsonNode and a Json string.
     * @return JsonBundle to be further processed by decorators
     */
    public JsonBundle parse(){
        JsonBundle bundle = new JsonBundle(toBeFormatted);

        return bundle;
    }
}
