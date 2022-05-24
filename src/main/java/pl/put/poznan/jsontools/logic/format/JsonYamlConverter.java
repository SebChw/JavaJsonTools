package pl.put.poznan.jsontools.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import pl.put.poznan.jsontools.logic.util.JsonBundle;

public class JsonYamlConverter extends JsonFormatterDecorator{

    public JsonYamlConverter(JsonFormatter json){
        super(json);
    }

    public JsonFormatter getWrappee(){
        return(this.wrappee);
    }

    /**
     * Reduces string from jsonBundle.
     *
     * @return jsonBundle with Json in one line
     */
    public JsonBundle parse() {
        JsonBundle jsonBundle = this.getWrappee().parse();
        JsonNode jsonNode = jsonBundle.getJsonNode();
        String yaml = null;
        try {
            yaml = new YAMLMapper().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jsonBundle.setString(yaml);
        return(jsonBundle);
    }

}
