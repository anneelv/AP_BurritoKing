package MainProgram;

import Exceptions.*;

/*The Validation class ensures that user input is not what is expected*/

public class Validation {

    /*The method to ensure the number inputted is not 0 and negative number*/
    public <T> Object checkNumberInput(T input) throws NotANumberException {
        if (input instanceof Integer) {
            if ((int)input < 1) {
                throw new NotANumberException();
            }
        } else if (input instanceof Double) {
            if ((double)input < 1) {
                throw new NotANumberException();
            }
        }
        return null;
    }

    /*The method to ensure user input is not empty*/
    public Object checkStringInput(String input) throws EmptyUserInputException {
        if (input.isEmpty()) {
            throw new EmptyUserInputException("Input must not be empty!");
        }
        return null;
    }
}
