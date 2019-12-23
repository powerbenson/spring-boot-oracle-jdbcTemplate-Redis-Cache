package tw.idb.response;

import tw.idb.constant.ResultCode;
import tw.idb.constant.ResultMessage;

public class Result {

    private String result;
    private String message;

    public Result() {
        setResult(ResultCode.SUCCESS);
        setMessage(ResultMessage.SUCCESS);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
