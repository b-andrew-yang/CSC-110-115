/*
 * Name: Andrew Yang    
 * ID: V00878595
 */

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Integer;
import java.lang.System;
import java.lang.String;
import java.lang.Double;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Aggregate{
    public static ArrayList<String[]> sum(String [][] dataArray){
        //The final ArrayList that will be output
        ArrayList<String[]> aggFinal = new ArrayList<String[]>();
        //*NOTE* When doing ArrayList of String[] you can't initialize it with a size.
        
        //Creates the top groups row for the final array
        String [] finalGroups = dataArray[0];
        finalGroups[finalGroups.length - 1] = "sum(" + dataArray[0][dataArray[0].length - 1] + ")";
        aggFinal.add(finalGroups);
        
        //Sums up the data
        for(int i=1; i<dataArray.length;i++){
            String [] temp = Arrays.copyOfRange(dataArray[i], 0, dataArray[0].length - 1);
                
            if(getIndex(aggFinal, temp) == -1){
                aggFinal.add(dataArray[i]);
            }else{
                //grabs the index of the grouping in aggFinal.
                int index = getIndex(aggFinal, temp);
                
                String [] sumTemp = aggFinal.get(index);
                //ok getting super confused but this is the current value that I want to add a new value to.
                int aggFinalSum = Integer.parseInt(sumTemp[sumTemp.length - 1]);
                //now i'm adding the new number to it.
                aggFinalSum = aggFinalSum + Integer.parseInt(dataArray[i][dataArray[0].length - 1]);
                sumTemp[sumTemp.length - 1] = Integer.toString(aggFinalSum);
            }
        }
        
        return aggFinal;
    }
    
    public static ArrayList<String[]> count(String [][] dataArray){
        //The final ArrayList that will be output
        ArrayList<String[]> aggFinal = new ArrayList<String[]>();
        
        //Creates the top groups row for the final array
        String [] finalGroups = dataArray[0];
        finalGroups[finalGroups.length - 1] = "count(" + dataArray[0][dataArray[0].length - 1] + ")";
        aggFinal.add(finalGroups);
        
        //Here we aggregate and produce the counts
        for(int i=1; i<dataArray.length; i++){
            String [] temp = Arrays.copyOfRange(dataArray[i], 0, dataArray[0].length - 1);
            
            if(getIndex(aggFinal, temp) == -1){
                String [] countTemp = dataArray[i];
                countTemp[countTemp.length - 1] = "1";
                aggFinal.add(countTemp);
            }else{
                //adds 1 each time the group comes up again
                int index = getIndex(aggFinal, temp);
                
                //Grab the array at the index in aggFinal
                String [] addCount = aggFinal.get(index);
                
                //Now add 1 to the last element
                int aggFinalCount = Integer.parseInt(addCount[addCount.length - 1]);
                aggFinalCount += 1;
                addCount[addCount.length - 1] = Integer.toString(aggFinalCount);
                
            }
        }
        return aggFinal;
    }
    
    public static ArrayList<String[]> avg(String [][] dataArray){
        //The final ArrayList that will be output

        String [][] copy = new String[dataArray.length][dataArray[0].length];
        for(int i = 0; i < dataArray.length; i++){
            copy[i] = dataArray[i].clone();
        }
        
        ArrayList<String[]> sumFinal = new ArrayList<String[]>();
        ArrayList<String[]> countFinal = new ArrayList<String[]>();
        ArrayList<String[]> aggFinal = new ArrayList<String[]>();
        
        //Creates the top groups row for the final array
        String [] finalGroups = dataArray[0];
        finalGroups[finalGroups.length - 1] = "avg(" + dataArray[0][dataArray[0].length - 1] + ")";
        aggFinal.add(finalGroups);
        
        for(int i=1; i<copy.length; i++){
            String [] temp = Arrays.copyOfRange(copy[i], 0, copy[0].length - 1);
            if(getIndex(aggFinal, temp) == -1){
                aggFinal.add(copy[i]);
            }else{
                int index = getIndex(aggFinal, temp);
                
                String [] sumTemp = aggFinal.get(index);
                double sumFinalSum = Double.parseDouble(sumTemp[sumTemp.length - 1]);
                sumFinalSum = sumFinalSum + Double.parseDouble(copy[i][copy[0].length - 1]);
                sumTemp[sumTemp.length - 1] = Double.toString(sumFinalSum);
            }
        }
        String [] empty = new String [dataArray[0].length];
        
        countFinal.add(empty);
        
        for(int i=1; i<dataArray.length; i++){
            String [] temp = Arrays.copyOfRange(dataArray[i], 0, dataArray[0].length - 1);
            
            if(getIndex(countFinal, temp) == -1){
                String [] countTemp = dataArray[i];
                countTemp[countTemp.length - 1] = "1";
                countFinal.add(countTemp);
            }else{
                //adds 1 each time the group comes up again
                int index = getIndex(countFinal, temp);
                
                //Grab the array at the index in countFinal
                String [] addCount = countFinal.get(index);
                
                //Now add 1 to the last element
                int countFinalCount = Integer.parseInt(addCount[addCount.length - 1]);
                countFinalCount += 1;
                addCount[addCount.length - 1] = Integer.toString(countFinalCount);
            }
        }
        
        for(int i=1; i<countFinal.size(); i++){
            String [] countTemp = countFinal.get(i);
            String [] aggTemp = aggFinal.get(i);
            
            double count = Double.parseDouble(countTemp[countTemp.length - 1]);
            double sum = Double.parseDouble(aggTemp[aggTemp.length - 1]);
            double avg = sum/count;
            aggTemp[aggTemp.length - 1] = String.valueOf(round(avg, 2));
        }
        
        return aggFinal;
    }
    
    public static int find(String [] array, String value){
        //Method to find the index of a String in the array
        int index = 0;
        
        for(int i = 0; i<array.length; i++){
            if(array[i].equals(value)){
                index = i;
            }
        }
        //System.out.println(index);
        return index;
    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static int getIndex(ArrayList<String[]> uniqueAgg ,String [] repGroup){
        //uniqueAgg is the partially or full arrayList containing the unique groups so far.
        //String [] repGroup is the repeated Group that we need the index of.
        for(int i=0; i<uniqueAgg.size(); i++){
            String[] temp = uniqueAgg.get(i);
            String[] pieceOfTemp = Arrays.copyOfRange(temp, 0, temp.length - 1);
            if(Arrays.equals(repGroup, pieceOfTemp)){
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String [] args){
            int argsCount = args.length; //counts the number of args in the command line
            
            int rowLength = 1;//finds the length the rows
            ArrayList<String[]> data = new ArrayList<String[]>();//make new ArrayList
            
            try{
                BufferedReader br = new BufferedReader(new FileReader(args[2]));
                String line;
                while((line = br.readLine()) != null){
                    String[] parts = line.split(",");
                    data.add(parts);
                }
                
            }catch(FileNotFoundException e){
                //Exception if file doesn't exist
                System.out.println("Error: " + e);
            }catch(IOException e){
                System.out.println("Error: " + e);
            }
            
            String[][] dataArray = new String[data.size()][argsCount - 2];
            //2D array of only the groups we want
            String [] dataGroups = data.get(0);
            //System.out.println(Arrays.deepToString(dataGroups));
            //gets firstline of 2D ArrayList which contains groups
            int [] aggGroups = new int[argsCount - 2];
            
            int groupsIndex = 0;
            //index for aggGroups
            for(int j=3; j<argsCount; j++){
                //Finds the index of the groups declared in args
                aggGroups[groupsIndex] = find(dataGroups, args[j]);
                groupsIndex++;
            }
            aggGroups[aggGroups.length - 1] = find(dataGroups, args[1]);
        
            for(int i=0; i<dataArray.length; i++){
                String[] rowData = data.get(i);
                for(int j=0; j<argsCount - 2; j++){
                    dataArray[i][j] = rowData[aggGroups[j]];
                    //Stores into dataArray, only the rows from the groups we want.
                }
            }
            //Throws an error if the aggregation group isn't numerical.
            try{
                double aggNumCheck = Double.parseDouble(dataArray[1][dataArray[1].length - 1]);
            }catch(Exception e){
                System.out.println("Aggregation group is not a number.");
            }
        
            //Throws an error if the aggregation or the groups don't exist in the code.
            for(int j=1; j<argsCount; j++){
                boolean groupExist = false;
                if(j == 2){
                    j = 3;
                }
                groupExist = Arrays.asList(dataGroups).contains(args[j]);
                if(groupExist == false){
                    throw new IllegalArgumentException("Error: Group doesn't exist in the table.");
                }
            }
            
            for(int k=3; k<argsCount; k++){
                if(args[1].equals(args[k])){
                    throw new IllegalArgumentException("Error: Aggregation column is the same as a grouping column");
                }
            }
            
            ArrayList<String[]> veryFinal = new ArrayList<String[]>();
            //Here we can get the calls
            if(args[0].equals("sum")){
                veryFinal = sum(dataArray);
            }else if(args[0].equals("avg")){
                veryFinal = avg(dataArray);
            }else if(args[0].equals("count")){
                veryFinal = count(dataArray);
            }else{
                System.out.println("Error: Please enter an appropriate operation. Either sum, avg, or count");
            }
            
            String [][] finalOut = new String [veryFinal.size()][argsCount - 2];
            
            for(int j=0; j<finalOut.length; j++){
                String [] finalRowData = veryFinal.get(j);
                finalOut[j] = finalRowData;
            }
            
            try{
                StringBuilder builder = new StringBuilder();
                for(int i=0; i< finalOut.length; i++){
                    for(int j=0; j< finalOut[i].length; j++){
                        builder.append(finalOut[i][j] + "");
                        if(j < finalOut[i].length - 1){
                            builder.append(",");
                        }
                    }
                    builder.append("\n");
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter("V00878595.csv"));
                writer.write(builder.toString());
                writer.close();
            }catch(IOException e){
                System.out.println("Error: " + e);
            }
        }
    }