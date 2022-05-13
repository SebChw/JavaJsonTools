package pl.put.poznan.transformer.logic.format;

/**
 * Base decorator for handling Json.
 */

public abstract class JsonFormatterDecorator implements JsonFormatter{

    protected JsonFormatter wrappee;

    public JsonFormatterDecorator(JsonFormatter json){
        this.wrappee = json;
    }
}
