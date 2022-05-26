package pl.put.poznan.jsontools.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonXmlConverterTest {

    @Test
    public void Testparse() throws JsonProcessingException {
        String text= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        String expected = "<ObjectNode><title>Thinking in Java</title><isbn>978-0131872486</isbn><year>1998</year><authors>Bruce Eckel</authors></ObjectNode>" ;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(text);
        JsonFormatter formatter = new JsonReader(json);
        JsonXmlConverter xml = new JsonXmlConverter(formatter);
        System.out.println(xml.parse().getString());
        String actual = xml.parse().getString();
        assertEquals(expected, actual);
    }


    @Test
    public void Testparse2() throws JsonProcessingException {
        String text= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        String expected =
                "title: \"Thinking in Java\"\n" +
                        "isbn: \"978-0131872486\"\n" +
                        "year: 1998\n" +
                        "authors:\n" +
                        "- \"Bruce Eckel\"\n" ;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(text);
        JsonFormatter formatter = new JsonReader(json);
        JsonXmlConverter xml = new JsonXmlConverter(formatter);
        String actual = xml.parse().getString();
        assertNotEquals(expected, actual);
        System.out.println(xml.parse().getString());
    }

}