package pl.put.poznan.transformer.logic.format;

import pl.put.poznan.transformer.logic.util.JsonBundle;

/**
 * Component interface for parsing Json.
 */

public interface JsonFormatter {
    public JsonBundle parse();
}
