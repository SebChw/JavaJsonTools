package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReducer extends JsonFormatterDecorator{

    public JsonReducer(JsonFormatter json){
        super(json);
    }

    public JsonFormatter getWrappee(){
        return(this.wrappee);
    }

    public JsonNode parse() {
        JsonNode jsonNode = this.getWrappee().parse();
        return(jsonNode);
    }

    public String reduce(JsonNode jsonNode){
        ObjectMapper objectMapper = new ObjectMapper();
        String reduced = null;
        try {
            reduced = objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return(reduced);
    }
}
