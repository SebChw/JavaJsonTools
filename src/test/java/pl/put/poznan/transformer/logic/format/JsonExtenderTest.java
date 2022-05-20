package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pl.put.poznan.jsontools.logic.format.JsonExtender;
import pl.put.poznan.jsontools.logic.format.JsonFormatter;
import pl.put.poznan.jsontools.logic.format.JsonReader;

import static org.junit.jupiter.api.Assertions.*;

class JsonExtenderTest {

    @Test
    public void Testparse() throws JsonProcessingException {
        String text = "{\"title\":\"Thinking in Java\",\"isbn\":\"978-0131872486\",\"year\":1998,\"authors\":[\"Bruce Eckel\"]}";
        String output="{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(text);
        JsonFormatter formatter = new JsonReader(json);
        JsonExtender extender = new JsonExtender(formatter);
        System.out.println(extender.parse());
        assertEquals(extender.parse().getString().replaceAll("\\r\\n", "\n"), output);
        assertNotEquals(extender.parse().getString(),text);
        assertNotEquals(extender.parse().getString(),"{\"title\"\n:\"Thinking in Java\",\"isbn\":\"978-0131872486\",\"year\":1998,\"authors\":[\"Bruce Eckel\"]}");
    }

}
