package pl.put.poznan.transformer.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.jsontools.logic.format.JsonExtender;
import pl.put.poznan.jsontools.logic.format.JsonFormatter;
import pl.put.poznan.jsontools.logic.format.JsonReader;
import pl.put.poznan.jsontools.logic.util.DecoratorWrapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonExtenderTest {
    private DecoratorWrapper mock;
    private String text;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        mock = mock(DecoratorWrapper.class);

        text = "{\"title\":\"Thinking in Java\",\"isbn\":\"978-0131872486\",\"year\":1998,\"authors\":[\"Bruce Eckel\"]}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(text);
        JsonFormatter formatter = new JsonReader(json);

        when(mock.formatterFromList(null, null, null, false)).thenReturn(formatter);
    }

    @Test
    public void Testparse() throws JsonProcessingException {
        String output="{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        JsonExtender extender = new JsonExtender(mock.formatterFromList(null,null,null,false));

        assertEquals(extender.parse().getString().replaceAll("\\r\\n", "\n"), output);
        assertNotEquals(extender.parse().getString(),text);
        assertNotEquals(extender.parse().getString(),"{\"title\"\n:\"Thinking in Java\",\"isbn\":\"978-0131872486\",\"year\":1998,\"authors\":[\"Bruce Eckel\"]}");
    }

}
