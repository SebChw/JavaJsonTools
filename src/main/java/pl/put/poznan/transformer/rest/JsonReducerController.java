package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.format.JsonFormatter;
import pl.put.poznan.transformer.logic.format.JsonReader;
import pl.put.poznan.transformer.logic.format.JsonReducer;



@RestController
@RequestMapping("/reduce/{text}")
public class JsonReducerController {

    private static final Logger logger = LoggerFactory.getLogger(JsonReducerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public JsonNode get(@PathVariable String text) {

        // log the parameters
        logger.debug(text);
        JsonFormatter formatter = new JsonReader(text);
        JsonReducer reducer = new JsonReducer(formatter);

        return reducer.parse();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public JsonNode post(@PathVariable String text) {

        // log the parameters
        logger.debug(text);

        JsonFormatter formatter = new JsonReader(text);
        JsonReducer reducer = new JsonReducer(formatter);

        return reducer.parse();
    }



}

