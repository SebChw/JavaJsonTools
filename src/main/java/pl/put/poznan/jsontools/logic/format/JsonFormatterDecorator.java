package pl.put.poznan.jsontools.logic.format;

/**
 * Base decorator for handling Json.
 */

public abstract class JsonFormatterDecorator implements JsonFormatter{

    /**
     * Decorator's JsonFormatter wrappee
     */
    protected JsonFormatter wrappee;

    public JsonFormatterDecorator(JsonFormatter json){
        this.wrappee = json;
    }
}
