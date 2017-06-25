/*
 * Name: Andrew Yang
 * ID: V00878595
 * Date: 10/19/16
 * Filename: CurrencyToWords.java
 * Details: CSC 110 Assignment 4
 */

/*
 * CurrencyToWords is a class that takes a double value from the user
 * and runs it through three different methods to concatenate a string
 * which contains the spelled out words for the value input.
 */

import java.util.*;

public class CurrencyToWords {
    
    public static void main(String [] args){     
        double value = getValueFromUser();
        // Prompts the user for a double and stores it as value
        String text = convertToWords(value);
        // Runs value through convertToWords and stores it as String text
        String end = afterDecimal(value);
        // Runs value through afterDecimal and stores it as String end
        System.out.print(text);
        // Prints out String text
        
        for (int i = text.length(); i < 70 - end.length(); i++){
            System.out.print("-");
        }
        // Prints out the dashes
        System.out.print(" ");
        // Prints a space after the dashes
        System.out.print(end);
        // Prints out the String end
    
    }
    /*
    Prints out cents value as XX/100
    Input: The monetary amount
    Returns: The cents value string
    */
    public static String afterDecimal(double value){
        
        String str = "";
        // Initializes the String str
        double x = value - (int)value;
        // Stores the decimal place as double x
        
        if (x < .1 && x != 0){
            str = str + "0";
        }
        // Prints a 0 if the value is less than .1 and doesn't equal 0
        str = str + (((int)(100 * x + 0.5)) + "/100 dollars");
        // Takes 100 times x and adds 0.5 to it. Then that value is casted
        // as an int and adds that to the string /100 dollars. The 0.5 is
        // added to fix a rounding error
        return str;
        // Returns str
    }
    
    /*
    Concatenates the string moneyWords for the ones and tens place value
    Input: The montary amount
    Returns: The concatenated tens and ones value string
    */
    public static String baseCardinalToString(int value){
        String moneyWords = "";
        // Initializes the String moneyWords
        
        while(value != 0){
            // While value doesn't equal 1 run the loop. This loop concatenates
            // the string for the ones and tens place.
            if(value == 1){
                moneyWords = moneyWords + "one ";
                value = value - 1;
            }
            
            else if(value == 2){
                moneyWords = moneyWords + "two ";
                value = value - 2;
            }
            
            else if(value == 3){
                moneyWords = moneyWords + "three ";
                value = value - 3;
            }
            
            else if (value == 4){
                moneyWords = moneyWords + "four ";
                value = value - 4;
            }
            
            else if (value == 5){
                moneyWords = moneyWords + "five ";
                value = value - 5;
            }
            
            else if (value == 6){
                moneyWords = moneyWords + "six ";
                value = value - 6;
            }
            
            else if (value == 7){
                moneyWords = moneyWords + "seven ";
                value = value - 7;
            }
            
            else if (value == 8){
                moneyWords = moneyWords + "eight ";
                value = value - 8;
            }
            
            else if (value == 9){
                moneyWords = moneyWords + "nine ";
                value = value - 9;
            }
            
            else if (value == 10){
                moneyWords = moneyWords + "ten ";
                value = value - 10;
            }
            
            else if (value == 11){
                moneyWords = moneyWords + "eleven ";
                value = value - 11;
            }
            
            else if (value == 12){
                moneyWords = moneyWords + "twelve ";
                value = value - 12;
            }
            
            else if (value == 13){
                moneyWords = moneyWords + "thirteen ";
                value = value - 13;
            }
            
            else if (value == 14){
                moneyWords = moneyWords + "fourteen ";
                value = value - 14;
            }
            
            else if (value == 15){
                moneyWords = moneyWords + "fifteen ";
                value = value - 15;
            }
            
            else if (value == 16){
                moneyWords = moneyWords + "sixteen ";
                value = value - 16;
            }
            
            else if (value == 17){
                moneyWords = moneyWords + "seventeen ";
                value = value - 17;
            }
            
            else if (value == 18){
                moneyWords = moneyWords + "eighteen ";
                value = value - 18;
            }
            
            else if (value == 19){
                moneyWords = moneyWords + "nineteen ";
                value = value - 19;
            }
            
            else if (value >= 20 && value < 30){
                moneyWords = moneyWords + "twenty ";
                value = value - 20;
            }
            
            else if (value >= 30 && value < 40){
                moneyWords = moneyWords + "thirty ";
                value = value - 30;
            }
            
            else if (value >= 40 && value < 50){
                moneyWords = moneyWords + "fourty ";
                value = value - 40;
            }
            
            else if (value >= 50 && value < 60){
                moneyWords = moneyWords + "fifty ";
                value = value - 50;
            }
            
            else if (value >= 60 && value < 70){
                moneyWords = moneyWords + "sixty ";
                value = value - 60;
            }
            
            else if (value >= 70 && value < 80){
                moneyWords = moneyWords + "seventy ";
                value = value - 70;
            }
            
            else if (value >= 80 && value < 90){
                moneyWords = moneyWords + "eighty ";
                value = value - 80;
            }
            
            else if (value >= 90 && value < 100){
                moneyWords = moneyWords + "ninety ";
                value = value - 90;
            }
            
        }
        return moneyWords;
        // Returns moneyWords
    }
    
    /*
    convertToWords concatenates the String moneyWords with the values for
    less than 1 or the hundreds and the thousands place.
    Input: The monetary amount
    Returns: The concatenated string for the thousands and the hundreds place
    */
    public static String convertToWords(double value){
        // 
        String moneyWords = "";
        // Initializes the String moneyWords
        
        while (value != 0){
            // Concatenates the string while the value doesn't equal zero
            if(value < 1){
                moneyWords = moneyWords + "zero ";
            }
            // If value is less than 1 it prints the word zero
            else{
                if(value >= 1 && value < 100){
                    moneyWords = moneyWords + baseCardinalToString((int)value);
                    value = 0;
                }
            
                else if(value >= 100 && value < 1000){
                    moneyWords = moneyWords + baseCardinalToString((int)value/100);
                    moneyWords = moneyWords + "hundred";
                    if(value % 100 != 0){
                        moneyWords = moneyWords + " and ";
                    }
                    value = value % 100;
                }
            
                else if(value >= 1000 && value < 10000){
                    moneyWords = moneyWords + baseCardinalToString((int)value/1000);
                    moneyWords = moneyWords + "thousand ";
                    value = value % 1000;
                }
        }
        }
        return moneyWords;
        // Returns moneyWords
    }
    /*
    getValueFromUser prompts the user for a monetary amount. This method
    also rejects anything that isn't a double.
    Input: Takes an input from the user
    Returns: The value input by the user
    */
    public static double getValueFromUser(){
        Scanner sc = new Scanner(System.in);
        // Imports new Scanner sc
        
        
        System.out.print("Please enter a monetary amount:");
        // Prompts the user for a montary amount
                
        while(sc.hasNextDouble() == false){
            sc.nextLine();
            System.out.println("Please enter a numerical value...");
        }
        // If the value isn't a double. It prompts the user until a double is
        // inputted
        double value = sc.nextDouble();
        // Stores the input as value
        
        return value;
        // Returns value
    }
}


