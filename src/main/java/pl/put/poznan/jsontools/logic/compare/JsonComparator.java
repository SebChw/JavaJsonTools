package pl.put.poznan.jsontools.logic.compare;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 * Compares two Json strings
 */

public class JsonComparator implements Comparator{


    /**
     * Compares two Json strings
     *
     * @param json1 first Json Node
     * @param json2 second Json Node
     */
    @Override
    public String compare(JsonNode json1, JsonNode json2) throws JsonProcessingException {

        /*
        Exemplary json to compare
        {
            "x":{
            "a":"These rows",
                    "b":"should be in common",
                    "c":"This is on the left only",
                    "f":"This has the same keys but"
            },
            "b":{
            "a":"These rows",
                    "b":"should be in common",
                    "d":"This is on the right only",
                    "f":"different values"
            }
        }
        */
        ObjectMapper objectMapper = new ObjectMapper();
        String j1 = null;
        String j2 = null;
        try {
            j1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json1);
            j2 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        TypeReference<Map<String, Object>> type = new TypeReference<>() {
        };

        Map<String, Object> map1 = objectMapper.readValue(j1, type);
        Map<String, Object> map2 = objectMapper.readValue(j2, type);
        MapDifference<String, Object> difference = Maps.difference(map1, map2);

        StringBuilder cmpString = new StringBuilder();
        cmpString.append("Keys and values in common:\n");
        difference.entriesInCommon().forEach((key, value) -> cmpString.append("\t- " + key + ": " + value + "\n"));
        cmpString.append("\nKeys only in the first JSON:\n");
        difference.entriesOnlyOnLeft().forEach((key, value) -> cmpString.append("\t- " + key + ": " + value + "\n"));
        cmpString.append("\nKeys only in the second JSON:\n");
        difference.entriesOnlyOnRight().forEach((key, value) -> cmpString.append("\t- " + key + ": " + value + "\n"));
        cmpString.append("\nKeys the same but different value:\n");
        difference.entriesDiffering().forEach((key, value) -> cmpString.append("\t- " + key + ": " + value + "\n"));

        return cmpString.toString();
    }
}
