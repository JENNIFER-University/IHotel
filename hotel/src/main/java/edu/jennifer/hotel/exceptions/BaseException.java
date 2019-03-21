package edu.jennifer.hotel.exceptions;

/**
 * @author Khalid
 * @Created 3/11/19 11:52 AM.
 */
public abstract class BaseException extends RuntimeException{

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract String getErrorCode();
}
