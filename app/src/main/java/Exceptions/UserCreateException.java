package Exceptions;

/**
 * Created by wassim on 21/12/15.
 */
public class UserCreateException  extends Exception {
    public UserCreateException(String detailMessage) {
        super(detailMessage);

    }

    public UserCreateException() {
    }
}
