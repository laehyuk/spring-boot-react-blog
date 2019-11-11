package com.hd.blog.common.Exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{

    private static final long serialVersionUID = 5402611780106831838L;
    private String exceptionCode;
    private HttpStatus httpStatus;

    public ApiException(Throwable cause, HttpStatus httpStatus){
        super(cause);
        setHttpStatus(httpStatus);
    }

    public ApiException(String message, HttpStatus httpStatus){
        super(message);
        setHttpStatus(httpStatus);
    }

    public ApiException(String message){
        super(message);
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode( String exceptionCode ) {
        this.exceptionCode = exceptionCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus( HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }

}
