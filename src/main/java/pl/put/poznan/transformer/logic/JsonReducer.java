package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonReducer extends JsonFormatterDecorator{

    public JsonReducer(JsonFormatter json){
        this.wrappee=json;
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
