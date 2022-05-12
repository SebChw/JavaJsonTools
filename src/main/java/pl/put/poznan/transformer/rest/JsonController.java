package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.format.JsonFormatter;
import pl.put.poznan.transformer.logic.format.JsonReader;
import pl.put.poznan.transformer.logic.format.JsonReducer;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/json")
public class JsonController {

    private static final Logger logger = LoggerFactory.getLogger(JsonController.class);

    //http://localhost:8080/json/transform/%7B"a":"b","b":"c","c":"d","d":"e"%7D?keys=a,b,c&transforms=reduce,select,extend
    @RequestMapping(value = "/transform/{text}", method = RequestMethod.GET, produces = "application/json")
    public JsonNode transform(@RequestParam(value = "transforms", defaultValue = "reduce") String transforms,
                              @RequestParam(value = "keys", defaultValue = "all") String keys,
                              @PathVariable String text) {

        List<String> transformsList = Arrays.asList(transforms.split(","));
        List<String> keysList = Arrays.asList(keys.split(","));

        System.out.println(transformsList +"\n"+ keysList);

        logger.debug(text);
        JsonFormatter formatter = new JsonReader(text);
        JsonReducer reducer = new JsonReducer(formatter);
        JsonNode node = reducer.parse();



        return node;
    }

    //http://localhost:8080/json/compare/%7B"a":"b"%7D/%7B"b":"c"%7D
    @RequestMapping(value = "/compare/{text1}/{text2}", method = RequestMethod.GET, produces = "application/json")
    public String compare(@PathVariable String text1, @PathVariable String text2) {
        ObjectMapper mapper = new ObjectMapper();

        // log the parameters
        logger.debug(text1);
        JsonFormatter formatter1 = new JsonReader(text1);
        JsonReducer reducer1 = new JsonReducer(formatter1);
        JsonNode node1 = reducer1.parse();


        logger.debug(text2);
        JsonFormatter formatter2 = new JsonReader(text2);
        JsonReducer reducer2 = new JsonReducer(formatter2);
        JsonNode node2 = reducer2.parse();

        //just to show that it works
        String a1 = "";
        try {
            a1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String a2 = "";
        try {
            a2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        //

        return a1 + "\n" +a2;
    }



}