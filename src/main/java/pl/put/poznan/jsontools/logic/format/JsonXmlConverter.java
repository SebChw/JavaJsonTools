package pl.put.poznan.jsontools.logic.format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pl.put.poznan.jsontools.logic.util.JsonBundle;

/**
 * Converts JSON to XML format.
 */

public class JsonXmlConverter extends JsonFormatterDecorator{

    public JsonXmlConverter(JsonFormatter json){
        super(json);
    }

    public JsonFormatter getWrappee(){
        return(this.wrappee);
    }

    /**
     * Converts JsonNode to an XML string.
     *
     * @return jsonBundle with string containing Json converted to XML
     */
    public JsonBundle parse() {
        JsonBundle jsonBundle = this.getWrappee().parse();
        JsonNode jsonNode = jsonBundle.getJsonNode();
        String xml = null;
        try {
            xml = new XmlMapper().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jsonBundle.setString(xml);
        return(jsonBundle);
    }

}