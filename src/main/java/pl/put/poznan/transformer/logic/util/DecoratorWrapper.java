package pl.put.poznan.transformer.logic.util;

import pl.put.poznan.transformer.logic.format.*;

import java.util.List;


public class DecoratorWrapper {
        public JsonFormatter addDecorator(JsonFormatter decorated, String toAdd){
            //We should add some logic here too concerning selecting specific keys

            if (toAdd.equals("reduce")){
                return new JsonReducer(decorated);
            }

            return new JsonExtender(decorated);
        }

        public JsonFormatter formatterFromList(JsonFormatter baseFormatter, List<String> transforms){
            for (String transform: transforms) {
               baseFormatter = addDecorator(baseFormatter, transform);
            }

            return baseFormatter;
        }
}
