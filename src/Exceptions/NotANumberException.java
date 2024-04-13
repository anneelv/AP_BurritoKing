package Exceptions;

/*NotANumberException Class is a custom exception to handle invalid number input such as 0 and negative values*/

public class NotANumberException extends NumberFormatException {
    public NotANumberException(String message){
        super(message);
    }

    public NotANumberException(){
        super("Please input a valid number!");
    }
}
