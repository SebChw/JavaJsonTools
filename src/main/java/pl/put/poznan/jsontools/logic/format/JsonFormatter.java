package pl.put.poznan.jsontools.logic.format;

import pl.put.poznan.jsontools.logic.util.JsonBundle;

/**
 * Component interface for parsing Json.
 */

public interface JsonFormatter {
    /**
     * Parses Json
     * @return bundle of combined Json and string
     */
    public JsonBundle parse();
}
