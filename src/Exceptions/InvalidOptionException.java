package Exceptions;

import java.io.IOException;

/*InvalidOptionException Class is a custom exception class to handle input that are not in the options*/

public class InvalidOptionException extends IOException {
    public InvalidOptionException(String message){
        super(message);
    }
}
