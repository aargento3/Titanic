package cmis141hw5anthonyargentorainbow;

/*
 * File: Titanic.java
 * Author: Anthony Argento
 * Date: Aug 5, 2016
 * Purpose: This file contains the getter methods for the titanic project.
 *	It will also read in the required text file and populate the array with
 *      the data found in the supplied titanic.txt file.	
 */

//import required java.*
import java.io.*;
import java.util.*;

public class Titanic {
	
    //define static variables
    public static final int MAX_PAX = 2000;
    public static final int FIELDS = 6;
	
    //define local variables
    int actualPax = 0;
    
    //define 2D array to store data from read in file
    String[][] data = new String[MAX_PAX][FIELDS];

    //constructor
    Titanic(String filename) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File(filename));

        while (fileReader.hasNextLine()) {
            data[actualPax++] = fileReader.nextLine().split("\t");
        } //end while loop
    	fileReader.close();
    }//end constructor

    //GETTER METHODS
	
    //get total pax
    public int getTotalPax() {
        return actualPax;
    }//end get total 
	
    //get total perished 
    public int getTotalPerishedPax() {
        int count = 0;
        for (int i = 0; i < getTotalPax(); i++) {
            int status = Integer.parseInt(data[i][1]);
            //set status to perished if status == 0
            if (status == 0) {
                count++;
            }//end if statement
        }//end for loop
        return count;
    }//end get total perished 

    //get total survivor pax
    public int getTotalSurvivedPax() {
        return getTotalPax() - getTotalPerishedPax();
    }//end get total survivor

    //get total survived by class
    public int getTotalSurvivedPaxByClass(int cls) {
        int count = 0;
        for (int i = 0; i < getTotalPax(); i++) {
            int status = Integer.parseInt(data[i][1]);
            int whatClass = Integer.parseInt(data[i][0]);
            if (status == 1 && whatClass == cls) {
                count++;
            }//end if statement
        }//end for loop
        return count;
    }//end total survived by class

    //get total survived by gender
    public int getTotalSurvivedPaxByGender(String gender) {
        int count = 0;
        for (int i = 0; i < getTotalPax(); i++) {
            int status = Integer.parseInt(data[i][1]);
            if (status == 1 && gender.equalsIgnoreCase(data[i][3])) {
                count++;
            }//end if statement
        }//end for loop
        return count;
    }//end total survived by gender

    //get pax who paid fare > X
    public String getNamesPaidFare(double fare) {
        String name = "";
        for (int i = 0; i < getTotalPax(); i++) {
            if (data[i].length >= 6) {
                double paidFare = Double.parseDouble(data[i][5]);
                if (paidFare > fare) {
                    name += "\n" + data[i][2];
                }//end if statement
            }//end if statement
        }//end for loop
        return name;
    }//end names who paid > X

    //get names who survived < age X
    public String getSurvivedNamesLessGivenAge(int age) {
        String name = "";
        for (int i = 0; i < getTotalPax(); i++) {
            if (data[i].length >= 6 && data[i][4].length()>0) {
                int status = Integer.parseInt(data[i][1]);
                Double givenAge = Double.parseDouble(data[i][4]);
                if (status == 1 && givenAge < age) {
                    name += "\n" + data[i][2];
                }//end if statement
            }//end if statement
        }//end for statement
        return name;
    }//end names who survived < age X

    //get names who perished < age X
    public String getPerishedNamesLessGivenAge(int age) {
       String name = "";
        for (int i = 0; i < getTotalPax(); i++) {
            if (data[i].length >= 6 && data[i][4].length()>0) {
                int status = Integer.parseInt(data[i][1]);
                double givenAge = Double.parseDouble(data[i][4]);
                if (status == 0 && givenAge < age) {
                    name += "\n" + data[i][2];
                }//end if statement
            }//end if statement
        }//end for loop
        return name;
    }//end names who perished < age X

    //get number of names which start w/ X
    public int getNumberOfNamesStartingWithX(char ch) {
        int count = 0;
        for (int i = 0; i < getTotalPax(); i++) {
            char whatChar = data[i][2].charAt(0);
            if (Character.toUpperCase(ch) == Character.toUpperCase(whatChar)) {
                count++;
            }//end if statement
        }//end for loop
        return count;
    }//end get number of names that start w/ X
	
}//end titanic class
