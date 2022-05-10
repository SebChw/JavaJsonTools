package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.util.JsonBundle;

/**
 * Extends Json
 */
public class JsonExtender extends JsonFormatterDecorator{

    public JsonExtender(JsonFormatter json){
        super(json);
    }

    public JsonFormatter getWrappee(){
        return(this.wrappee);
    }

    public JsonBundle parse() {
        JsonBundle jsonBundle = this.getWrappee().parse();
        JsonNode jsonNode = jsonBundle.getJsonNode();

        ObjectMapper objectMapper = new ObjectMapper();
        String extended = null;
        try {
            extended = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jsonBundle.setString(extended);
        return(jsonBundle);
    }
}
