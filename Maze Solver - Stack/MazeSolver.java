/*
 * Name: Andrew Yang
 * ID: V00878595
 * Date: 3/10/17
 * Course: CSC 115
 * Assignment 3
 */

/*
 * MazeSolver.java
 *
 * UVic CSC 115, Spring 2017
 *
 * Purpose:
 *   class that contains a single public static method called
 *   "findPath". To involve the method one must have already created
 *   an instance of Maze. The method must return a path through the
 *   maze (if it exists) in the format shown within the Assignment #3
 *   description.
 *
 * Note: You are free to add to this class whatever other methods will
 * help you in writing a solution to A#3 part 2.
 */

public class MazeSolver {
    public static String findPath(Maze maze) {
        // create a 2D boolean array to keep track of visited locations
        boolean [][] visited = new boolean [maze.getNumRows()][maze.getNumCols()];
        
        // create a new stack to push correct locations
        StackRefBased<MazeLocation> search = new StackRefBased<MazeLocation>();
        
        // get entry location and push it onto the stack
        MazeLocation entry = maze.getEntry();
        search.push(entry);
        
        //get exit location to check if we've finished
        MazeLocation exit = maze.getExit();
        
        //mark the entry as visited
        visited [entry.getRow()][entry.getCol()] = true;

        try{
            while(search.size() != 0 && !search.peek().equals(exit)){
                // get the starting location for our maze
                MazeLocation scan = search.peek();
                int row = scan.getRow();
                int col = scan.getCol();
                
                // if row + 1, col is not a wall or visited push it onto the stack and mark as visited
                if(!maze.isWall(row + 1, col) && visited[row + 1][col] != true){
                    
                    MazeLocation next = new MazeLocation(row + 1, col);
                    search.push(next);
                    visited[row + 1][col] = true;
                  
                // if row, col + 1 is not a wall or visited push it onto the stack and mark as visited    
                }else if(!maze.isWall(row, col + 1) && visited[row][col + 1] != true){
                    
                    MazeLocation next = new MazeLocation(row, col + 1);
                    search.push(next);
                    visited[row][col + 1] = true;
                   
                // if row - 1, col is not a wall or visited push it onto the stack and mark as visited    
                }else if(!maze.isWall(row - 1, col) && visited[row - 1][col] != true){
                        
                    MazeLocation next = new MazeLocation(row - 1, col);
                    search.push(next);
                    visited[row - 1][col] = true;
                    
                // if row, col -1 is not a wall or visited push it onto the stack and mark as visited    
                }else if(!maze.isWall(row, col - 1) && visited[row][col - 1] != true){
                        
                    MazeLocation next = new MazeLocation(row, col - 1);
                    search.push(next);
                    visited[row][col - 1] = true;
                   
                }
                // if we hit a dead end pop until we find another path to go through
                else{
                    search.pop();
                }
            }
        }catch(StackEmptyException se){
            System.out.println("Stack is empty.");
        }
        
        // if the stack is empty, there is no path
        if(search.isEmpty()){
            System.out.println("There is no path from start to finish");
            return "";
        }
        // Stringbuilder to output in correct form
        StringBuilder sb = new StringBuilder();
        String [] tmp = new String[search.size()];
        try{
            tmp[0] = entry.toString();
            for(int i = search.size() - 1; i > 0; i--){
                MazeLocation add = search.pop();
                //System.out.print(add.toString());
                tmp[i] = add.toString();
            }
        }catch(StackEmptyException se){
            System.out.println("Stack is empty");
        }
        sb.append(tmp[0]);
        for(int i = 1; i < tmp.length; i++){
            
            sb.append(" " + tmp[i]);
            
        }       
        //System.out.print(sb);
        
        return sb.toString();
    }
}