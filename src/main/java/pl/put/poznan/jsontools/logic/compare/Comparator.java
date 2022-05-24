package pl.put.poznan.jsontools.logic.compare;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.jsontools.logic.util.JsonBundle;

/**
 * Comparator of two strings.
 */

public interface Comparator {
    /**
     * Compares two strings
     *
     * @param text1 first string
     * @param text2 second string
     */
    public String compare(JsonNode text1, JsonNode text2) throws JsonProcessingException;

}
