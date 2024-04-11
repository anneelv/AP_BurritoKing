public class NotANumberException extends NumberFormatException {
    public NotANumberException(String message){
        super(message);
    }

    public NotANumberException(){
        super("Please input a valid number!");
    }
}
