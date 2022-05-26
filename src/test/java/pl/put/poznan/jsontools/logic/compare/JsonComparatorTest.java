package pl.put.poznan.jsontools.logic.compare;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonComparatorTest {

    @Test
    public void Testcompare() throws JsonProcessingException {
        String text1= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        String text2= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1954,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json1 = mapper.readTree(text1);
        JsonNode json2 = mapper.readTree(text2);
        Comparator comparator = new JsonComparator();
        String actual = comparator.compare(json1,json2);
        String expected = "Keys and values in common:\n" +
                "\t- isbn: 978-0131872486\n" +
                "\t- title: Thinking in Java\n" +
                "\t- authors: [Bruce Eckel]\n" +
                "\n" +
                "Keys only in the first JSON:\n" +
                "\n" +
                "Keys only in the second JSON:\n" +
                "\n" +
                "Keys the same but different value:\n" +
                "\t- year: (1998, 1954)\n";
        assertEquals(expected,actual);

    }

    @Test
    public void Testcompare2() throws JsonProcessingException {
        String text1= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        String text2= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json1 = mapper.readTree(text1);
        JsonNode json2 = mapper.readTree(text2);
        Comparator comparator = new JsonComparator();
        String actual = comparator.compare(json1,json2);
        String expected = "Keys and values in common:\n" +
                "\t- year: 1998\n" +
                "\t- isbn: 978-0131872486\n" +
                "\t- title: Thinking in Java\n" +
                "\n" +
                "Keys only in the first JSON:\n" +
                "\t- authors: [Bruce Eckel]\n" +
                "\n" +
                "Keys only in the second JSON:\n" +
                "\n" +
                "Keys the same but different value:\n";
        assertEquals(expected,actual);

    }

    @Test
    public void Testcompare3() throws JsonProcessingException {
        String text1= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        String text2= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"isbn\" : \"978-0131872486\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json1 = mapper.readTree(text1);
        JsonNode json2 = mapper.readTree(text2);
        Comparator comparator = new JsonComparator();
        String actual = comparator.compare(json1,json2);
        String expected = "Keys and values in common:\n" +
                "\t- year: 1998\n" +
                "\t- title: Thinking in Java\n" +
                "\t- authors: [Bruce Eckel]\n" +
                "\n" +
                "Keys only in the first JSON:\n" +
                "\n" +
                "Keys only in the second JSON:\n" +
                "\t- isbn: 978-0131872486\n" +
                "\n" +
                "Keys the same but different value:\n";
        assertEquals(expected,actual);
    }

    @Test
    public void Testcompare4() throws JsonProcessingException {
        String text= "{\n" +
                "  \"title\" : \"Thinking in Java\",\n" +
                "  \"year\" : 1998,\n" +
                "  \"authors\" : [ \"Bruce Eckel\" ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json1 = mapper.readTree(text);
        Comparator comparator = new JsonComparator();
        String actual = comparator.compare(json1,json1);
        String expected = "Keys and values in common:\n" +
                "\t- year: 1998\n" +
                "\t- title: Thinking in Java\n" +
                "\t- authors: [Bruce Eckel]\n" +
                "\n" +
                "Keys only in the first JSON:\n" +
                "\n" +
                "Keys only in the second JSON:\n" +
                "\n" +
                "Keys the same but different value:\n";
        assertEquals(expected,actual);
    }

}