package pl.put.poznan.transformer.logic.format;


public abstract class JsonFormatterDecorator implements JsonFormatter{
    protected JsonFormatter wrappee;

    public JsonFormatterDecorator(JsonFormatter json){
        this.wrappee = json;
    }
}
