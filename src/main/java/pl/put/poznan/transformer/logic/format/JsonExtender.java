package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonExtender extends JsonFormatterDecorator{

    public JsonExtender(JsonFormatter json){
        super(json);
    }

    public JsonFormatter getWrappee(){
        return(this.wrappee);
    }

    public JsonNode parse() {
        JsonNode jsonnode_extended= this.getWrappee().parse();
        /*Modify here jsonnode_extended to extend it*/
        return(jsonnode_extended);
    }

    public String extend(JsonNode jsonNode){
        ObjectMapper objectMapper = new ObjectMapper();
        String extended = null;
        try {
            extended = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return(extended);
    }
}
