package pl.put.poznan.transformer.logic;


public abstract class JsonFormatterDecorator implements JsonFormatter{
    protected JsonFormatter wrappee;
}
