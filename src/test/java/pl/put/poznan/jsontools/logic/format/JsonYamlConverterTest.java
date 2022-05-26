package pl.put.poznan.jsontools.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonYamlConverterTest {

    @Test
    public void Testparse() throws JsonProcessingException {
        String text= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        String expected = "---\n" +
                "title: \"Thinking in Java\"\n" +
                "isbn: \"978-0131872486\"\n" +
                "year: 1998\n" +
                "authors:\n" +
                "- \"Bruce Eckel\"\n" ;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(text);
        JsonFormatter formatter = new JsonReader(json);
        JsonYamlConverter yaml = new JsonYamlConverter(formatter);
        JsonXmlConverter xml = new JsonXmlConverter(formatter);
        System.out.println(yaml.parse().getString());
        String actual = yaml.parse().getString();
        assertEquals(expected, actual);
        //System.out.println(xml.parse().getString());
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
        JsonYamlConverter yaml = new JsonYamlConverter(formatter);
        //JsonXmlConverter xml = new JsonXmlConverter(formatter);
        String actual = yaml.parse().getString();
        assertNotEquals(expected, actual);
        //System.out.println(xml.parse().getString());
    }

}