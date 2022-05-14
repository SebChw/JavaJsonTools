package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonSelectorTest {

    @Test
    public void Testparse() throws JsonProcessingException {
        String text= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        String output = "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"year\" : 1998,\n" +
                "}";
        List<String> keys = new ArrayList<String>(){{add("title");add("year");}};
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(text);
        JsonFormatter formatter = new JsonReader(json);
        JsonSelector selector = new JsonSelector(formatter,keys,true);
        System.out.println(selector.parse());
        assertEquals(selector.parse().getString(), output);
        assertNotEquals(selector.parse().getString(),text);
        assertNotEquals(selector.parse().getString(),"{\"title\"\n:\"Thinking in Java\",\"isbn\":\"978-0131872486\",\"year\":1998,\"authors\":[\"Bruce Eckel\"]}");
    }

}