package tw.idb.response;

import java.util.HashMap;
import java.util.Map;

public class Response {

    private Result result;
    private Map<String,Object> elements;

    public Response(){
        this.result = new Result();
        this.elements = new HashMap<>() ;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Map<String, Object> getElements() {
        return elements;
    }

    public void setElements(Map<String, Object> elements) {
        this.elements = elements;
    }
}
