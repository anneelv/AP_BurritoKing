import java.io.IOException;

public class InvalidOptionException extends IOException {
    public InvalidOptionException(String message){
        super(message);
    }
}
