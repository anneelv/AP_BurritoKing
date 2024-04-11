public class Validation {

    public <T> void checkNumberInput(T input) throws NotANumberException{
        if (input instanceof Integer) {
            if ((int)input < 1) {
                throw new NotANumberException();
            }
        } else if (input instanceof Double) {
            if ((double)input < 1) {
                throw new NotANumberException();
            }
        }
    }

    public void checkStringInput(String input) throws EmptyUserInputException{
        if (input.isEmpty()) {
            throw new EmptyUserInputException("Input must not be empty!");
        }
    }
}
