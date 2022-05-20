package pl.put.poznan.jsontools.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.jsontools.logic.format.JsonFormatter;
import pl.put.poznan.jsontools.logic.format.JsonReader;
import pl.put.poznan.jsontools.logic.util.JsonBundle;
import pl.put.poznan.jsontools.logic.util.DecoratorWrapper;

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

        //Check if Json is correct
        if (json.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must provide JSON object in the request! not a JSON string");
        }

        // log the parameters
        logger.debug(transforms);

        List<String> transformsList = new ArrayList<>(Arrays.asList(transforms.split(",")));
        List<String> keysList = new ArrayList<>(Arrays.asList(keys.split(",")));

        JsonFormatter formatter = decoratorWrapper.formatterFromList(new JsonReader(json), transformsList, keysList, reversed);
        JsonBundle jsonBundle = formatter.parse();

        return jsonBundle.getString();

    }

}

