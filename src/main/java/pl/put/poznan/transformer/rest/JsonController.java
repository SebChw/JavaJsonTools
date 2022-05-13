package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.format.JsonFormatter;
import pl.put.poznan.transformer.logic.format.JsonReader;
import pl.put.poznan.transformer.logic.util.JsonBundle;
import pl.put.poznan.transformer.logic.util.DecoratorWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controls the endpoints and handles HTTP requests for Json transformations.
 */

@RestController
@RequestMapping("/json")
public class JsonController {

    private static final Logger logger = LoggerFactory.getLogger(JsonController.class);
    private static final DecoratorWrapper decoratorWrapper = new DecoratorWrapper();

//    //http://localhost:8080/json/transform/%7B"a":"b","b":"c","c":"d","d":"e"%7D?keys=a,b,c&transforms=reduce,select,extend
//    @RequestMapping(value = "/transform/{text}", method = RequestMethod.GET, produces = "application/json")
//    public JsonNode transform(@RequestParam(value = "transforms", defaultValue = "reduce") String transforms,
//                              @RequestParam(value = "keys", defaultValue = "all") String keys,
//                              @PathVariable String text) {
//
//        List<String> transformsList = Arrays.asList(transforms.split(","));
//        List<String> keysList = Arrays.asList(keys.split(","));
//
//        System.out.println(transformsList +"\n"+ keysList);
//
//        logger.debug(text);
//        JsonFormatter formatter = new JsonReader(text);
//        JsonReducer reducer = new JsonReducer(formatter);
//        JsonNode node = reducer.parse();
//
//
//
//        return node;
//    }
//
//    //http://localhost:8080/json/compare/%7B"a":"b"%7D/%7B"b":"c"%7D
//    @RequestMapping(value = "/compare/{text1}/{text2}", method = RequestMethod.GET, produces = "application/json")
//    public String compare(@PathVariable String text1, @PathVariable String text2) {
//        ObjectMapper mapper = new ObjectMapper();
//
//        // log the parameters
//        logger.debug(text1);
//        JsonFormatter formatter1 = new JsonReader(text1);
//        JsonReducer reducer1 = new JsonReducer(formatter1);
//        JsonNode node1 = reducer1.parse();
//
//
//        logger.debug(text2);
//        JsonFormatter formatter2 = new JsonReader(text2);
//        JsonReducer reducer2 = new JsonReducer(formatter2);
//        JsonNode node2 = reducer2.parse();
//
//        //just to show that it works
//        String a1 = "";
//        try {
//            a1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node1);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        String a2 = "";
//        try {
//            a2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node2);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        //
//
//        return a1 + "\n" +a2;
//    }

    /**
     *
     * Obtains Json from HTTP POST request and processes it according
     * to user's parameters. Json file is mandatory.
     * By default, returns an extended string.
     *
     *
     *
     * @param transforms list of transformations to be applied
     * @param json file to be transformed
     * @param keys list of keys to be selected/removed
     * @param reversed keys selection/dropping flag
     * @return transformed Json string
     * @see DecoratorWrapper#formatterFromList(JsonFormatter, List, List, boolean)
     */
    @RequestMapping(value="/transform/{transforms}", method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String transforms,
                       @RequestBody JsonNode json,
                       @RequestParam(value = "keys", defaultValue = "null") String keys,
                       @RequestParam(value = "reversed", defaultValue = "false") boolean reversed) {
        // log the parameters
        System.out.println(json);
        logger.debug(transforms);

        List<String> transformsList = new ArrayList<>(Arrays.asList(transforms.split(",")));
        List<String> keysList = new ArrayList<>(Arrays.asList(keys.split(",")));

        JsonFormatter formatter = decoratorWrapper.formatterFromList(new JsonReader(json), transformsList, keysList, reversed);
        JsonBundle jsonBundle = formatter.parse();

        return jsonBundle.getString();

    }

}

