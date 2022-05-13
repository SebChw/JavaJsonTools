package pl.put.poznan.transformer.logic.util;

import pl.put.poznan.transformer.logic.format.*;

import java.util.List;


public class DecoratorWrapper {
        public JsonFormatter addDecorator(JsonFormatter decorated, String toAdd, List<String> keys, boolean reversed){
            //We should add some logic here too concerning selecting specific keys

            if (toAdd.equals("reduce")){
                return new JsonReducer(decorated);
            }

            if (toAdd.equals("select")){
                return new JsonSelector(decorated, keys, reversed);
            }

            return new JsonExtender(decorated);
        }

        public JsonFormatter formatterFromList(JsonFormatter baseFormatter, List<String> transforms, List<String> keys, boolean reversed){
            // if reduce and extend is not in transforms append extend by default
            for (String transform: transforms) {
               baseFormatter = addDecorator(baseFormatter, transform, keys, reversed);
            }

            return baseFormatter;
        }
}
