/*
* Name: Andrew Yang
* ID: V00878595
* Date: 11/1/16
* Filename: MineSweeper.java
* Details: CSC Assignment 6
*/

package minesweeper;
import java.util.Random;
import java.util.Scanner;

/**
 * MineSweeper is a program that builds two 8x8 2D arrays. One
 * is an int array keeping track of the bombs and the number
 * of bombs each cell is touching. The other array is the gameBoard
 * that the player will see when they're playing. The program uses the random
 * class to generate 10 different bombs on the gameBoard. The program uses
 * several other methods in order to interchange between the two
 * to produce the game MineSweeper.
 */
public class MineSweeper {
    public static void main(String[] args) {
        // Creates a 2-D String array called gameBoard
        String [][] gameBoard = new String[8][8]; 
        
        // Creates a 2-D int array called mineSweeper
        int [][] mineSweeper = new int[8][8];
        
        // Creates a new Scanner sc
        Scanner sc = new Scanner(System.in);
        
        // Prints the top border of the game
        System.out.println("  | 0 1 2 3 4 5 6 7");
        System.out.println(" ------------------");
       
        // Prints the gameBoard
        for (int i = 0; i < 8; i++){
            System.out.print(i + " | ");
            for (int j = 0; j < 8; j++){
                gameBoard [i][j] = ". ";
                mineSweeper [i][j] = 0;
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
        
        // Accepts input and stores that as the variable row
        System.out.println("Select a cell. Row value (a digit between 0 and 7");
        int row = sc.nextInt();
        
        // Accepts input and stores that as the variable col
        System.out.println("Select a cell. Column value (a digit between 0 and 7");
        int col = sc.nextInt();
        
        // Creates a new random class
        Random r = new Random();
        
        // Creates 10 bombs
        for(int i = 0; i < 10; i++){
            int x = r.nextInt(8);
            int y = r.nextInt(8);
            mineSweeper[x][y] = -10;
        }
        
        // Calls initializeFullGrid method
        initializeFullGrid(mineSweeper);
        // Calls revealGridCell method to get the game started
        revealGridCell(row, col, mineSweeper,gameBoard);
        
        // while loop runs each time a bomb isn't selected or 
        while(mineSweeper[row][col] > 0){
            int cellsLeft = 0;
            // Calculates how many cells are left
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(gameBoard[i][j].equals(". ")){
                        cellsLeft++;
                    }
                }
            }
            // Determines if the user won or not
            if(cellsLeft == 10){
                System.out.println("Congrats you won!");
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        revealGridCell(i, j, mineSweeper, gameBoard);
                    }
                }
                drawBombs(row, col, mineSweeper, gameBoard);
            }
            // Runs another iteration of the game
            else{
                System.out.println("Select a cell. Row value (a digit between 0 and 7");
                row = sc.nextInt();
        
                System.out.println("Select a cell. Column value (a digit between 0 and 7");
                col = sc.nextInt();
            
                revealGridCell(row, col, mineSweeper,gameBoard);
            }
        }
    }
    
    public static int [][] initializeFullGrid(int [][] mineSweeper){
        for(int i = 0; i < 8; i++){
            /*
            *  This method handles calculating which cells are touching
            *  bombs and calcuates how many each cell is touching and 
            *  stores it into the mineSweeper array.
            */
            for(int j = 0; j < 8; j++){
                if(mineSweeper[i][j] < 0){
                    if(j != 0){
                        if (i != 7){
                            mineSweeper[i + 1][j - 1]++;
                        }
                        if (i != 0){
                            mineSweeper[i - 1][j - 1]++;
                        }
                        mineSweeper [i][j - 1]++;
                    }
                    if(j != 7){
                        if(i != 7){
                            mineSweeper[i + 1][j + 1]++;
                        }
                        if(i != 0){
                            mineSweeper[i - 1][j + 1]++;
                        }
                        mineSweeper [i][j + 1]++;
                    }
                    if(i != 0){
                        mineSweeper[i - 1][j]++;
                    }
                    if(i != 7){
                        mineSweeper[i + 1][j]++;
                    }
                }
            }
        }
        return mineSweeper;
    }
    
    public static String [][] revealGridCell(int row, int col, 
            int [][] mineSweeper, String [][] gameBoard){
        
        /*
        *  This method handles the case where the user doesn't select a bomb,
        *  and handles the situations where: the user selects a cell that isn't
        *  touching a bomb and the neighboring cells are revealed, or the user
        *  selects a cell where it is touching the bomb and only reveals that
        *  cell, or the case where they reveal a bomb and they lose the game.
        */
        System.out.print("  | ");
        for(int i = 0; i < 8; i++){
            System.out.print(i + " ");
        }
        
        System.out.println();
        System.out.println("--------------------");
                
        if(row != 0 && row != 7 && col != 0 && col != 7){
            if(mineSweeper [row][col] == 0){
                drawFullGrid(row - 1, col - 1, mineSweeper, gameBoard);
                drawFullGrid(row - 1, col, mineSweeper, gameBoard);
                drawFullGrid(row - 1, col + 1, mineSweeper, gameBoard);
                drawFullGrid(row, col + 1, mineSweeper, gameBoard);
                drawFullGrid(row, col, mineSweeper, gameBoard);
                drawFullGrid(row, col - 1, mineSweeper, gameBoard);
                drawFullGrid(row + 1, col - 1, mineSweeper, gameBoard);
                drawFullGrid(row + 1, col, mineSweeper, gameBoard);
                drawFullGrid(row + 1, col + 1, mineSweeper, gameBoard);
            }
            else if(mineSweeper[row][col] > 0){
                drawFullGrid(row, col, mineSweeper, gameBoard);
            }
            else if(mineSweeper[row][col] < 0){
                drawBombs(row, col, mineSweeper, gameBoard);
            }
        }
        
        if(row == 0){
            if(col != 0 && col != 7){
                if(mineSweeper[row][col] == 0){
                    drawFullGrid(row, col - 1, mineSweeper, gameBoard);
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                    drawFullGrid(row, col + 1, mineSweeper, gameBoard);
                    drawFullGrid(row + 1, col - 1, mineSweeper, gameBoard);
                    drawFullGrid(row + 1, col, mineSweeper, gameBoard);
                    drawFullGrid(row + 1, col + 1, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] > 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] < 0){
                    drawBombs(row, col, mineSweeper, gameBoard);
                }
            }
            else if(col == 0){
                if(mineSweeper[row][col] == 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                    drawFullGrid(row, col + 1, mineSweeper, gameBoard);
                    drawFullGrid(row + 1, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] > 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] < 0){
                    drawBombs(row, col, mineSweeper, gameBoard);
                }
            }
            else if(col == 7){
                if(mineSweeper[row][col] == 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                    drawFullGrid(row, col - 1, mineSweeper, gameBoard);
                    drawFullGrid(row + 1, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] > 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] < 0){
                    drawBombs(row, col, mineSweeper, gameBoard);
                }
            }
        }
        if(row == 7){
            if(col != 0 && col != 7){
                if(mineSweeper[row][col] == 0){
                    drawFullGrid(row, col - 1, mineSweeper, gameBoard);
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                    drawFullGrid(row, col + 1, mineSweeper, gameBoard);
                    drawFullGrid(row - 1, col -1, mineSweeper, gameBoard);
                    drawFullGrid(row - 1, col, mineSweeper, gameBoard);
                    drawFullGrid(row - 1, col + 1, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] > 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] < 0){
                    drawBombs(row, col, mineSweeper, gameBoard);
                }
            }
            else if(col == 0){
                if(mineSweeper[row][col] == 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                    drawFullGrid(row - 1, col, mineSweeper, gameBoard);
                    drawFullGrid(row, col + 1, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] > 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] < 0){
                    drawBombs(row, col, mineSweeper, gameBoard);
                }
            }
            else if(col == 7){
                if(mineSweeper[row][col] == 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                    drawFullGrid(row, col - 1, mineSweeper, gameBoard);
                    drawFullGrid(row - 1, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] > 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] < 0){
                    drawBombs(row, col, mineSweeper, gameBoard);
                }
            }
        }
        if(col == 0){
            if(row != 0 && row != 7){
                if(mineSweeper[row][col] == 0){
                     drawFullGrid(row -1, col, mineSweeper, gameBoard);
                     drawFullGrid(row, col, mineSweeper, gameBoard);
                     drawFullGrid(row + 1, col, mineSweeper, gameBoard);
                     drawFullGrid(row - 1, col + 1, mineSweeper, gameBoard);
                     drawFullGrid(row, col + 1, mineSweeper, gameBoard);
                     drawFullGrid(row + 1, col + 1, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] > 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] < 0){
                    drawBombs(row, col, mineSweeper, gameBoard);
                }
            }
        }    
        if(col == 7){
            if(row != 0 && row != 7){
                if(mineSweeper[row][col] == 0){
                     drawFullGrid(row -1, col, mineSweeper, gameBoard);
                     drawFullGrid(row, col, mineSweeper, gameBoard);
                     drawFullGrid(row + 1, col, mineSweeper, gameBoard);
                     drawFullGrid(row - 1, col - 1, mineSweeper, gameBoard);
                     drawFullGrid(row, col - 1, mineSweeper, gameBoard);
                     drawFullGrid(row + 1, col - 1, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] > 0){
                    drawFullGrid(row, col, mineSweeper, gameBoard);
                }
                else if(mineSweeper[row][col] < 0){
                    drawBombs(row, col, mineSweeper, gameBoard);
                }
            }
        }    
        
        for(int i = 0; i < 8; i++){
            System.out.print(i + " | ");
            for(int j = 0; j < 8; j++){
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
            
    return gameBoard;        
    }
        
    public static String [][] drawFullGrid(int row, int col, 
        int [][] mineSweeper, String [][] gameBoard){
        /* This method handles the revealing of cells called on by the previous
        *  method. If the cell isn't touching any bombs, the cell comes up 
        *  blank, but if it is touching a bomb, it'll reveal the number of 
        *  bombs it is touching.
        */
        if(mineSweeper[row][col] == 0){
            gameBoard[row][col] = "  ";
        }
        else if(mineSweeper[row][col] > 0){
            gameBoard[row][col] = Integer.toString(mineSweeper[row][col]) + " ";
        }
        return gameBoard;
    }
    
    public static String [][] drawBombs(int row, int col, 
        int [][] mineSweeper, String [][] gameBoard){
        /*
        *  This method draws all the bombs as a B in the case that one cell
        *  with a bomb is selected. That cell will be replaced with an X.
        */
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(mineSweeper[i][j] < 0){
                    gameBoard[i][j] = "B ";
                }
            }
        }
    
    gameBoard[row][col] = "X ";
    
    System.out.println("Kaboom! Game Over!");
    
    return gameBoard;
    }
}