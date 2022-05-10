package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.format.JsonExtender;
import pl.put.poznan.transformer.logic.format.JsonFormatter;
import pl.put.poznan.transformer.logic.format.JsonReader;
import pl.put.poznan.transformer.logic.format.JsonReducer;
import pl.put.poznan.transformer.logic.util.JsonBundle;


@RestController
@RequestMapping("/reduce/{text}")
public class JsonReducerController {

    private static final Logger logger = LoggerFactory.getLogger(JsonReducerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text) {

        // log the parameters
        logger.debug(text);
        JsonFormatter formatter = new JsonReader(text);
        JsonReducer reducer = new JsonReducer(formatter);
        JsonExtender extender = new JsonExtender(reducer);
        JsonBundle jsonBundle = extender.parse();

        return jsonBundle.getString();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text) {

        // log the parameters
        logger.debug(text);

        JsonFormatter formatter = new JsonReader(text);
        JsonReducer reducer = new JsonReducer(formatter);
        JsonBundle jsonBundle = reducer.parse();
        return jsonBundle.getString();
    }



}

