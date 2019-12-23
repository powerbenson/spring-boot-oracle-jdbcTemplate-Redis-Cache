package tw.idb.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tw.idb.constant.ResultCode;
import tw.idb.constant.ResultMessage;
import tw.idb.response.Response;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<String> handleUnAuthorizedException(UnAuthorizedException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SystemErrorException.class)
    public ResponseEntity<Response> handleSystemErrorException(SystemErrorException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Response result = new Response();
        result.getResult().setResult(ResultCode.SYSTEM_ERROR);
        result.getResult().setMessage(ResultMessage.SYSTEM_ERROR);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Response result = new Response();
        result.getResult().setResult(ResultCode.SYSTEM_ERROR);
        result.getResult().setMessage(ResultMessage.SYSTEM_ERROR);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
}
