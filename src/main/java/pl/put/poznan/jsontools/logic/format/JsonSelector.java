package pl.put.poznan.jsontools.logic.format;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pl.put.poznan.jsontools.logic.util.JsonBundle;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Selects given keys from a JsonNode or
 * selects all but given keys if
 * reversed equals true.
 */
public class JsonSelector extends JsonFormatterDecorator{
    /**
     * Keys to be selected/dropped
     */
    private List<String> listKeys;
    /**
     * Selection/dropping flag
     */
    private boolean reversed;

    /**
     * Class constructor with keys and reversed flag
     * @param json formatter
     * @param listKeys keys to be selected
     * @param reversed the selection flag, if true all but listKeys is returned
     */
    public JsonSelector(JsonFormatter json, List<String> listKeys, boolean reversed){
        super(json);
        this.listKeys=listKeys;
        this.reversed=reversed;
    }

    public JsonFormatter getWrappee(){
        return(this.wrappee);
    }

    public List<String> getListKeys(){return(this.listKeys);}

    public boolean getReversed(){return(this.reversed);}

    /**
     * Removes given keys from bundle's JsonNode.
     * If reversed is false, only listKeys are selected.
     * Else all other keys but listKeys are retained.
     * @return bundle with filtered keys
     */
    public JsonBundle parse(){
        JsonBundle jsonBundle= this.getWrappee().parse();
        JsonNode jsonNode = jsonBundle.getJsonNode();


        if (!reversed && !listKeys.isEmpty()){
            List<String> allKeys = new ArrayList<>();
            Iterator<String> iterator = jsonNode.fieldNames();
            iterator.forEachRemaining(e -> allKeys.add(e));
            allKeys.removeAll(listKeys);
            ((ObjectNode) jsonNode).remove(allKeys);
        } else{
            ((ObjectNode) jsonNode).remove(listKeys);
        }



        jsonBundle.setJsonNode(jsonNode);
        return(jsonBundle);
    }
}
