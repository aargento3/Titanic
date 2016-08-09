package cmis141hw5anthonyargentorainbow;

/*
 * File: TestTitanic.java
 * Author: Anthony Argento
 * Date: Aug 5, 2016
 * Purpose: This file contains the main method for the titanic project
 *	It will track the time from start to finish, provide the menu for  
 *	user navigation of program, catch invalid entries where appropriate,
 *      and contains several additional methods to efficiently gain user input
 *      when the same type of variable is used multiple times in the menu.	
 */

//import all required java.*
import java.io.*;
import java.util.*;
import java.lang.*;

public class TestTitanic {

    public static void main(String[] args) throws FileNotFoundException {
          
        //begin tracking time
        long startTime = System.currentTimeMillis(); 
        
        //try catch for possible failures
	try{
            //get file name argument from command line
            String fileName = args[0];
            //instatiate titanic object
            Titanic titanic = new Titanic(fileName);     

            //welcome note
            System.out.println("Welcome to the Titanic Project! \nThis program"
                    + " will allow you to parse through the supplied 'titanic.txt'"
                    + " file using the menu below. \n\nPlease use the numbers"
                    + " associated with the specific questions to navigate the menu."
                    + " \nYou will be prompted for any additional data required.");

            //define array of choices for user
            String choices[] = {"Total number of passengers on the Titanic",
                "Total number of passengers who perished on the Titanic",
                "Total number of Passengers who survived the sinking of the Titanic",
                "Number of passengers who survived the sinking of the Titanic as a " 
                    + "function of the passenger class (e.g. 1,2,3)",
                "Number of passengers who survived the sinking of the Titanic as a " 
                    + "function of the passenger gender (e.g., male, female)",
                "A list of the names of passengers who paid greater than X dollars "
                    + "for their tickets",
                "A list of the names of passengers who were less than X years old "
                    + "who survived",
                "A list of the names of passengers who were less than X years old "
                    + "who perished ",
                "The count of the number of passengers as a function of the first letter " +
                    " of their last name. (e.g., A: 13, B: 33, C: 12, ect...)", "Exit" };

            //define variable for switch selection 
            int choice = menuChoice(choices);

            //while and switch to loop user through choices as long as desired
            while (choice != 10) {
                switch (choice) {
                    case 1:
                        System.out.println("Total number of passengers on the Titanic: " + 
                            titanic.getTotalPax());
                        break;
                    case 2:
                        System.out.println("Total number of passengers who perished "
                                + "on the Titanic: " + titanic.getTotalPerishedPax());
                        break;
                    case 3:
                        System.out.println("Total number of passengers who survived: " + 
                            titanic.getTotalSurvivedPax());
                        break;
                    case 4:
                        System.out.print("Enter class (e.g. 1,2,3): ");
                        int cls = getIntInRange(1, 3);
                        System.out.println(cls + " class number of passengers who "
                            + "survived: " + titanic.getTotalSurvivedPaxByClass(cls));
                        break;
                    case 5:
                        System.out.print("Enter gender (e.g., male, female): ");
                        String gender = getString();
                        System.out.println("Number of " + gender + " passengers "
                                + "who survived: " + 
                            titanic.getTotalSurvivedPaxByGender(gender));
                        break;
                    case 6:
                        Scanner scanFare = new Scanner (System.in);
                        System.out.println("What is the minimum fare you would like to see?");
                        double minFare = scanFare.nextDouble();
                        System.out.print("Names of passengers who paid greater than "+ 
                            minFare + " for their tickets: ");
                        System.out.println(titanic.getNamesPaidFare(minFare));
                        break;
                    case 7:
                        System.out.println("What is the minimum age for survivors you "
                                + "would like to see?");
                            int minAge = getInteger();
                            System.out.print("Names of passengers who were less than " + 
                                minAge + " years old who survived: ");
                        System.out.println(titanic.getSurvivedNamesLessGivenAge(minAge));
                        break;
                    case 8:
                        System.out.println("What is the minimum age for those who "
                                + "perished you would like to see?");
                        int minPerishedAge = getInteger();
                        System.out.print("Names of passengers who were less than " + 
                            minPerishedAge + " years old who perished: ");
                        System.out.println(titanic.getPerishedNamesLessGivenAge
                            (minPerishedAge));
                        break;
                    case 9:
                        System.out.print("Enter the letter: ");
                        char ch = getString().charAt(0);
                        System.out.println("Count: " + titanic.getNumberOfNamesStartingWithX(ch));
                        break;
                }//end switch statement
                choice = menuChoice(choices);
            }//end while loop

            //track current time and determine length of time run
            long endTime = System.currentTimeMillis();
            long elapsedTime = (endTime - startTime) / 1000;

            //exit note
            System.out.println("Thank you for trying the Titanic program");
            System.out.println("Elapsed time in seconds: " + elapsedTime);
            
            //catch statements 
            } catch (FileNotFoundException e){
                System.out.println("titanic.txt not found! \nPlease double check"
                    + " arguments and/or file location! \nProgram will now exit.");
                e.printStackTrace();
                System.exit(0);
            } catch (ArrayIndexOutOfBoundsException a){
                System.out.println("titanic.txt not found! \nPlease double check"
                    + " arguments and/or file location! \nProgram will now exit.");
                a.printStackTrace();
                System.exit(0);
            }//end try/catch sequence 
        
        }//end main

    //get string method for use in switch statement
    public static String getString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }//end getString method

    //get int method for use in switch statement
    public static int getInteger() {
        Scanner getInteger = new Scanner(System.in);
        return getInteger.nextInt();
    }//end getInteger method
	
    //method to ensure user entry inside expected range
    public static int getIntInRange(int low, int high) {
        int num = 0;
        do {
            num = getInt();
            if (num < low || num > high) {
                System.out.println("Enter number between " + low + " and " + high);
            }//end if statement
        } while (num < low || num > high);
        return num;
    }//end getIntInRange method

	//method to ensure user enters valid choice in menu
    public static int menuChoice(String choices[]) {
        int choice = 0;
        System.out.println("");
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i]);
        }//end for loop
        System.out.print("Enter choice (1-" + choices.length + "): ");
        choice = getIntInRange(1, choices.length);
        return choice;
    }//end menuChoice method

    //method to ensure user enters a numeric value in menu selection
    public static int getInt() {
        Scanner input = new Scanner(System.in);
        int num = 0;
        boolean isNum;
        do {
            isNum = true;
            try {
                num = input.nextInt();
            } catch (Exception e) {
                isNum = false;
                input = new Scanner(System.in);
                System.out.println("Enter numeric value: ");
            }//end catch
        }//end do loop 
        while (!isNum);
        return num;
    }//end getInt method 
	
}//end class
