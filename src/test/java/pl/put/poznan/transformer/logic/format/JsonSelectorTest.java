package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pl.put.poznan.jsontools.logic.format.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonSelectorTest {

    @Test
    public void Testparse() throws JsonProcessingException {
        String text = "{\"title\":\"Thinking in Java\",\"isbn\":\"978-0131872486\",\"year\":1998,\"authors\":[\"Bruce Eckel\"]}";
        String output = "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"year\" : 1998\n" +
                "}";
        List<String> keys = new ArrayList<String>(){{add("title");add("year");}};
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(text);
        JsonFormatter formatter = new JsonReader(json);
        JsonFormatter selector = new JsonExtender(new JsonSelector(formatter,keys,false));
        System.out.println(selector.parse());
        assertEquals(selector.parse().getString().replaceAll("\\r\\n", "\n"), output);
        assertNotEquals(selector.parse().getString(),text);
        assertNotEquals(selector.parse().getString(),"{\"title\"\n:\"Thinking in Java\",\"isbn\":\"978-0131872486\",\"year\":1998,\"authors\":[\"Bruce Eckel\"]}");
    }

}