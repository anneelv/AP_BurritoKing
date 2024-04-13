package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import MainProgram.Validation;
import Exceptions.*;
import static org.junit.Assert.*;

/*The ValidationTest Class is a JUnit Test for methods in Validation Class*/

public class ValidationTest {

    private Validation validation;

    @Before
    public void setUp() throws Exception {
        validation = new Validation();
    }

    @Test (expected = NotANumberException.class)
    public void testInvalidNumberInput() {
        validation.checkNumberInput(0);
    }

    @Test (expected = NotANumberException.class)
    public void testMinusNumberInput() {
        validation.checkNumberInput(-3);
    }

//    @Test (expected = NotANumberException.class)
//    public void testAlphabetInNumberInput() throws NotANumberException {
//        validation.checkNumberInput("A");
//    }

    @Test
    public void testCorrectInputIntegerWithoutException() {
        assertNull(validation.checkNumberInput(4));
    }

    @Test
    public void testCorrectInputDoubleWithoutException() {
        assertNull(validation.checkNumberInput(5.5));
    }

    @Test (expected = EmptyUserInputException.class)
    public void testEmptyUserException() throws EmptyUserInputException {
        validation.checkStringInput("");
    }

    @Test
    public void properUserInputTest() throws EmptyUserInputException {
        assertNull(validation.checkStringInput("A"));
        assertNull(validation.checkStringInput("1"));
        assertNull(validation.checkStringInput(" "));
    }

    @After
    public void tearDown() throws Exception {
    }
}