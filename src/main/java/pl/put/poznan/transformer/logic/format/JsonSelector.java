package pl.put.poznan.transformer.logic.format;

import pl.put.poznan.transformer.logic.util.JsonBundle;

import java.util.List;

public class JsonSelector extends JsonFormatterDecorator{
    private List<String> listKeys;
    private boolean reversed;

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

    public JsonBundle parse(){
        JsonBundle jsonnode_select= this.getWrappee().parse();
        if (this.getReversed()){
            /*Modify here jsonnode_select to select only keys of this.getListKeys()*/
            return(jsonnode_select);
        }
        else{
            /*Modify here jsonnode_select to remove keys of this.getListKeys()*/
            return(jsonnode_select);
        }
    }
}
