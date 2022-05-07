package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonExtender extends JsonFormatterDecorator{

    public JsonExtender(JsonFormatter json){
        this.wrappee=json;
    }

    public JsonFormatter getWrappee(){
        return(this.wrappee);
    }

    public JsonNode parse() {
        JsonNode jsonnode_extended= this.getWrappee().parse();
        /*Modify here jsonnode_extended to extend it*/
        return(jsonnode_extended);
    }
}
