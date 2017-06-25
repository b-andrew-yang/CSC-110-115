/*
* Name: Andrew Yang
* ID: V00878595
* Date: 11/1/16
* Filename: TextAnalysis.java
* Details: CSC Assignment 5
*/

/*
* TextAnalysis is a program which uses a scanner to read a file and 
* runs that program through various methods to print out statistics
* about the text in that file. The different metrics calculated are
* length of the longest word (also what word it is), number of words
* in the file, number of unique words in the file, word frequency
* statistics by length and word frequency statistics by word.
*/

import java.util.*;
import java.io.*;


public class TextAnalysis {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File (args[0]));
        
        int numberWords = wordCount(input)-1;
        
        String[] textFile = new String[numberWords];
        
        Scanner input2 = new Scanner(new File (args[0]));
        String[] filled = fillArray(textFile, input2);
		
		System.out.println("TEXT FILE STATISTICS");
		System.out.println("____________________");
		
		
        
        // prints the longest word
        System.out.println("Length of longest word: " + longestLength(filled) +
                "  (\"" + longestWord(filled) + "\")");
        // prints the number of words used
        System.out.println("Number of words in file wordlist: " +
                numberWordList(filled, numberWords));
        // prints the # of words in the file
        System.out.println("Number of words in file: " + numberWords);
        System.out.println();
        
        // prints the word frequency statistics
        System.out.println("Word-frequency statistics");
        for(int i=0; i<=9; i++){
            System.out.println("  Word-length " + i + ": " + 
                    wordFrequency(filled, i));
        }
        System.out.println("  Words=length >= 10: " + wordsFrequency(filled));
        
        // prints the word list and the word count
		int maxArraySize = 10000;
		
        String[] wordList = new String[maxArraySize];
        int[] wordCount = new int[maxArraySize];
        
        int wordIndex = 0;
        
        System.out.println("Wordlist dump: ");
        for(int i = 0; i < numberWords; i++){
            String word = filled[i];
            
            for(wordIndex = 0; wordIndex < numberWords; wordIndex++){
                if(wordList[wordIndex] == null){
                    wordList[wordIndex] = word;
                    break;
                }
                else if(wordList[wordIndex].equals(word)){
                    break;
                }
            }
            wordCount[wordIndex]++;
        }
    }
    
    // method returns the number of words list
    public static int numberWordList(String[] filled, int number){
        int count = 1;
        for(int i = 1; i < number -1; i++){
            String word = filled[i];
            for(int j = 0; j <= i; j++){
                if(filled[j].equals(word)){
                    break;
                }
                else if(j == (i-1)){
                    count++;
                }
                else if(j < (i-1)){
                    continue;
                }
                else{
                    break;
                }
            }
        }
        return count;
    }
    
	// wordFrequency returns the word frequency for a word length less than 10
    public static int wordFrequency(String[] filled, int i){
        int frequency = 0;
        for(String p: filled){
            if(p.length() == i){
                frequency++;
            }
        }
        return frequency;
    }
    
	// wordsFrequency returns the word frequency for a word length 10 or more
    public static int wordsFrequency(String[] filled){
        int frequency = 0;
        for(String p : filled){
            if(p.length() >= 10){
                frequency++;
            }
        }
        return frequency;
    }
    
	// longestLength calcuates the number of characters the word with the longest 
	// length has
    public static int longestLength(String[] filled){
        int length = 0;
        for(String w : filled){
            if(w.length()>length){
                length = w.length();
            }
        }
        return length;
    }
    
	// Stores the longest word
    public static String longestWord(String[] filled){
        int length = 0;
        String longest = null;
        for(String w: filled){
            if(w.length()>length){
                length = w.length();
                longest = w;
            }
        }
        return longest;
    }
    
	// fillArray fills the String array with the words in the file.
    public static String [] fillArray(String [] textFile, Scanner input2){
        int i = 0;
        while(input2.hasNext()){
            textFile[i] = input2.next();
            i++;
        }
        for (int j = 0; j<textFile.length; j++){
            textFile[j] = textFile[j].toLowerCase();
        }
        return textFile;
    }
    
    // wordCount counts the number of words and returns it
    public static int wordCount(Scanner input){
        int counter = 1;
        
        while(input.hasNext()){
            input.next();
            counter++;
        }
        return counter;
    }
}
