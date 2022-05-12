package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.format.JsonExtender;
import pl.put.poznan.transformer.logic.format.JsonFormatter;
import pl.put.poznan.transformer.logic.format.JsonReader;
import pl.put.poznan.transformer.logic.format.JsonReducer;
import pl.put.poznan.transformer.logic.util.JsonBundle;
import pl.put.poznan.transformer.logic.util.DecoratorWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/json")
public class JsonReducerController {

    private static final Logger logger = LoggerFactory.getLogger(JsonReducerController.class);
    private static final DecoratorWrapper decoratorWrapper = new DecoratorWrapper();

    @RequestMapping(value="/transform/{transforms}", method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String transforms,
                       @RequestBody JsonNode json) {
        // log the parameters
        System.out.println(json);
        logger.debug(transforms);

        List<String> transformsList = new ArrayList<String>(Arrays.asList(transforms.split(",")));

        JsonFormatter formatter = decoratorWrapper.formatterFromList(new JsonReader(json), transformsList);
        JsonBundle jsonBundle = formatter.parse();

        return jsonBundle.getString();

    }

}

