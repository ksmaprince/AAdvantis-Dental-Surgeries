package edu.cs489.adssysem.advice;

import edu.cs489.adssysem.exception.RegisterUserException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserAuthExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> handleFailedAuth(BadCredentialsException badCredentialsException) {
        var errorMsgMap = new HashMap<String, String>();
        errorMsgMap.put("errorMsg", badCredentialsException.getMessage());
        return errorMsgMap;
    }


    @ExceptionHandler(RegisterUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleUserAlreadyExistError(RegisterUserException registerUserException) {
        var errorMsgMap = new HashMap<String, String>();
        errorMsgMap.put("errorMsg", registerUserException.getMessage());
        return errorMsgMap;
    }

}
