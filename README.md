
# Burrito King Assignment

# Contents
1. [Introduction](#introduction)
2. [Main Java File](#mainfile)
3. [MainProgram Package](#mainprogram)
4. [Exceptions Package](#exceptions)
5. [Tests Package](#test)
6. [Running Program From Command Line](#cmdMain)

## Introduction <a name="introduction"></a>
This burrito king program consist of 3 packages and 1 Main java files. The 3 packages are as follow:

1. Exceptions 
2. MainProgram
3. Tests

BurritoKing program are separated into classes and methods so we will need to instantiate an object to access the relevant methods. Since BurritoKing has 3 kind of foods available, I created a Food class with 3 different subclasses to provide unique attributes of the classes. These food object will be used within Order class to operate the program. The operations include adding current food to order ArrayList, calculate total sales, waiting time, remaining fries, along with changing the food price and sending the information for SalesReport. In SalesReport, 3 HashMaps are used to collate the information between the number of food sold and the total revenue from the food. All user input are validated with additional custom exceptions to ensure the program keeps running. Price changes from the Order class will help to reflect in the food objects along with the sales report information to make sure that everything are on the same page.

Every small operations are separated into different methods to simplify the overall code structure and avoid duplication of the same code logic in other area.

## Main.java file <a name="mainfile"></a>
This Java file will be the only entry point for Burrito King program. This java will interact with other java files in different packages to make sure the program is working well.

## MainProgram Package <a name="mainprogram"></a>
tldr: All functionalities from ordering food, changing price, and show sales report are available in this package

In this package, all main classes needed for BurritoKing program are created. This includes class to manage the foods available in BurritoKing along with each food unique attributes. The main functionality such as ordering food, changing price, and managing sales report are also available in this package. 
* The Menu class manage the initial display menu that user will encounter when first running the BurritoKing program
* The Food class has 3 subclasses to handle attributes unique to each food.
* The Order class manages the main functionality of ordering food, handles remaining fries, manage customer waiting time, send the order information to SalesReport class, and manage food change prices
* The SalesReport class collect all information of order and summarize the number of food sold along with the profit from all orders
* The Validation class handles validating user input to make sure it is within expected input


## Exceptions Package <a name="exceptions"></a>
tldr: All custom exceptions created to handle specific scenarios in BurritoKing are available in this package

There are 3 custom exception created for BurritoKing. The 3 exceptions are:
1. EmptyUserInputException --> Throw exception if the input is empty.
3. InvalidOptionException --> Throw exception if the input is not within the offered options
4. NotANumberException --> Throw exception if the input is not the accepted number (0 and negative number)

## Tests Package <a name="test"></a>
tldr: All JUnit Test Cases created for methods in MainProgram Packages are available in this package

The tests created in this packages focused on methods in Order, SalesReport, and Validation. Some of the method that I test including calculating total sales, in case of change prices, check if exceptions are working well, and many more. The test cases will test both the expected correct output and wrong output.

## Running Program From Command Line <a name="cmdMain"></a>
When trying to run the program from command line for the first time, we can do the following:
1. Navigate to the program folder (AP_BurritoKing) from the command line
2. Compile all .java files available in the folders. Use the following command: `javac ./src/Exceptions/*.java ./src/MainProgram/*.java ./src/Tests/*.java ./src/Main.java`
3. HOWEVER, since the program was created in IntelliJ it's already compiled and stored in `AP_BurritoKing/out/` folder
4. After the program is compiled we can navigate to `./out/production/AP_BurritoKing` where the previous java files becomes `.class`
5. Then in the command line run the following command: `java Main`
