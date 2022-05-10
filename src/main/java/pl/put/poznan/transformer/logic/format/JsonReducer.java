package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonReducer extends JsonFormatterDecorator{

    public JsonReducer(JsonFormatter json){
        super(json);
    }

    public JsonFormatter getWrappee(){
        return(this.wrappee);
    }

    public JsonNode parse() {
        JsonNode jsonnode_reduced= this.getWrappee().parse();
        /*Modify here jsonnode_reduced to reduce it*/
        return(jsonnode_reduced);
    }
}
