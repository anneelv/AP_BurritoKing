public class Validation {

    public <T> Object checkNumberInput(T input) throws NotANumberException{
        if (input instanceof Integer) {
            if ((int)input < 1) {
                throw new NotANumberException();
            }
        } else if (input instanceof Double) {
            if ((double)input < 1) {
                throw new NotANumberException();
            }
        } else if (!(input instanceof Integer) && !(input instanceof Double)) {
            throw new NotANumberException();
        }
        return null;
    }

    public Object checkStringInput(String input) throws EmptyUserInputException{
        if (input.isEmpty()) {
            throw new EmptyUserInputException("Input must not be empty!");
        }
        return null;
    }
}
