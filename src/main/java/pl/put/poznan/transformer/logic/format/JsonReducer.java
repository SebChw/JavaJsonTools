package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.util.JsonBundle;

/**
 * Reduces Json
 */

public class JsonReducer extends JsonFormatterDecorator{

    public JsonReducer(JsonFormatter json){
        super(json);
    }

    public JsonFormatter getWrappee(){
        return(this.wrappee);
    }

    /**
     * Reduces string from jsonBundle
     * @return jsonBundle
     */
    public JsonBundle parse() {
        JsonBundle jsonBundle = this.getWrappee().parse();
        JsonNode jsonNode = jsonBundle.getJsonNode();

        ObjectMapper objectMapper = new ObjectMapper();
        String reduced = null;
        try {
            reduced = objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jsonBundle.setString(reduced);
        return(jsonBundle);
    }

}
