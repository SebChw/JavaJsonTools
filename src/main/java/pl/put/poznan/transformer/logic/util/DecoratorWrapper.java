package pl.put.poznan.transformer.logic.util;

import pl.put.poznan.transformer.logic.format.*;

import java.util.List;

/**
 * Factory of Json decorators.
 * Creates different decorators based on list parameters.
 */

public class DecoratorWrapper {
    /**
     * Adds proper Json decorator to a Json formatter
     *
     * @param decorated Json formatter to be decorated
     * @param transform transformation to be applied
     * @param keys keys to be selected/removed, only for the Json Selector
     * @param reversed keys selection/dropping flag, only for Json Selector
     * @return decorated Json formatter
     */
        public JsonFormatter addDecorator(JsonFormatter decorated, String transform, List<String> keys, boolean reversed){

            if (transform.equals("reduce")){
                return new JsonReducer(decorated);
            }

            if (transform.equals("select")){
                return new JsonSelector(decorated, keys, reversed);
            }

            return new JsonExtender(decorated);
        }

    /**
     * Creates formatters based on parameters in a list.
     * @param baseFormatter basic Json formatter
     * @param transforms list of Json transformations to be applied
     * @param keys keys to be selected/removed, only for the Json Selector
     * @param reversed keys selection/dropping flag, only for Json Selector
     * @return decorated formatter
     */
        public JsonFormatter formatterFromList(JsonFormatter baseFormatter, List<String> transforms, List<String> keys, boolean reversed){

            if (!transforms.contains("reduce") || !transforms.contains("extend")){
                transforms.add("extend");
            }

            for (String transform: transforms) {
               baseFormatter = addDecorator(baseFormatter, transform, keys, reversed);
            }

            return baseFormatter;
        }
}
