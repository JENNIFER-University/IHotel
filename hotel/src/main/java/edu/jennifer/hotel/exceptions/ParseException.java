package edu.jennifer.hotel.exceptions;

/**
 * @author Khalid
 * @Created 3/11/19 11:53 AM.
 */
public class ParseException extends BaseException {

    public ParseException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "ER215";
    }

    @Override
    public String toString() {
        return String.format("Error Code: [%s]. Details [%s]", getErrorCode(), super.toString());
    }
}
