package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonReducerTest {

    @Test
    public void Testparse() throws JsonProcessingException {
        String text= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(text);
        JsonFormatter formatter = new JsonReader(json);
        JsonReducer reducer = new JsonReducer(formatter);
        System.out.println(reducer.parse());
        assertEquals(reducer.parse().getString(), "{\"title\":\"Thinking in Java\",\"isbn\":\"978-0131872486\",\"year\":1998,\"authors\":[\"Bruce Eckel\"]}");
        assertNotEquals(reducer.parse().getString(),text);
        assertNotEquals(reducer.parse().getString(),"{\"title\"\n:\"Thinking in Java\",\"isbn\":\"978-0131872486\",\"year\":1998,\"authors\":[\"Bruce Eckel\"]}");
    }

}