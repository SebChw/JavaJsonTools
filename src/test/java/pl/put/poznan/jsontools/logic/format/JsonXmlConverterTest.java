package pl.put.poznan.jsontools.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.jsontools.logic.util.DecoratorWrapper;
import pl.put.poznan.jsontools.rest.JsonController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonXmlConverterTest {
    private DecoratorWrapper mock;
    private String text;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        mock = mock(DecoratorWrapper.class);

        text = "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(text);
        JsonFormatter formatter = new JsonReader(json);

        when(mock.formatterFromList(null, null, null, false)).thenReturn(formatter);
    }

    @Test
    public void Testparse()  throws JsonProcessingException{

        String expected = "<ObjectNode><title>Thinking in Java</title><isbn>978-0131872486</isbn><year>1998</year><authors>Bruce Eckel</authors></ObjectNode>" ;
        JsonXmlConverter xml = new JsonXmlConverter(mock.formatterFromList(null,null,null,false));

        String actual = xml.parse().getString();
        assertEquals(expected, actual);
    }


    @Test
    public void Testparse2() throws JsonProcessingException {
        String expected =
                "title: \"Thinking in Java\"\n" +
                        "isbn: \"978-0131872486\"\n" +
                        "year: 1998\n" +
                        "authors:\n" +
                        "- \"Bruce Eckel\"\n" ;

        JsonXmlConverter xml = new JsonXmlConverter(mock.formatterFromList(null,null,null,false));
        String actual = xml.parse().getString();
        assertNotEquals(expected, actual);

    }

}