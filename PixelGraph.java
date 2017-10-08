/* Andrew Yang
   PixelGraph.java
   CSC 225 - Summer 2017
   Programming Assignment 3 - Pixel Graph Data Structure
*/ 

import java.awt.Color;

public class PixelGraph{
    public PixelVertex[][] picture;
    
	/* PixelGraph constructor
	   Given a 2d array of colour values (where element [x][y] is the colour 
	   of the pixel at position (x,y) in the image), initialize the data
	   structure to contain the pixel graph of the image. 
	*/
    
	public PixelGraph(Color[][] imagePixels){
        int width = imagePixels.length;
        int height = imagePixels[0].length;
        
        System.out.println(width);
        System.out.println(height);
        
        picture = new PixelVertex[width][height];
        
		for(int i = 0; i< width; i++){
            for(int j = 0; j< height; j++){
                PixelVertex a = new PixelVertex(i,j);
                picture[i][j] = a;
                //System.out.println(picture[i][j].xCoor + " " + picture[i][j].yCoor);
            }
        }
        
        for(int i = 0; i< width; i++){
            for(int j = 0; j< height; j++){
                //System.out.println(i + " " + j);
                if(j - 1 >= 0){
                    checkUp(imagePixels, picture[i][j]);
                }
                if(j + 1 < height){
                    checkDown(imagePixels, picture[i][j]);
                }
                if(i - 1 >= 0){
                    checkLeft(imagePixels, picture[i][j]);
                }
                if(i + 1 < width){
                    checkRight(imagePixels, picture[i][j]);
                }
            }
        }
	}
	
	/* getPixelVertex(x,y)
	   Given an (x,y) coordinate pair, return the PixelVertex object 
	   corresponding to the pixel at the provided coordinates.
	   This method is not required to perform any error checking (and you may
	   assume that the provided (x,y) pair is always a valid point in the 
	   image).
	*/
	public PixelVertex getPixelVertex(int x, int y){
		return picture[x][y];
	}
	
	/* getWidth()
	   Return the width of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getWidth(){
		return picture.length;
	}
	
	/* getHeight()
	   Return the height of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getHeight(){
		return picture[0].length;
	}
	
    /* Checks the element to the left of PixelVertex a for color equivalency */
    public void checkLeft(Color [][] image, PixelVertex a){
        int x = a.getX();
        int y = a.getY();
        if(image[x][y].equals(image[x - 1][y])){
            //System.out.println("left");
            a.addNeighbour(picture[x - 1][y]);
        }
    }
    
    /* Checks the element to the top of PixelVertex a for color equivalency */
    public void checkUp(Color [][] image, PixelVertex a){
        int x = a.getX();
        int y = a.getY();
        if(image[x][y].equals(image[x][y - 1])){
            //System.out.println("up");
            a.addNeighbour(picture[x][y - 1]);
        }
    }
    
    /* Checks the element to the bottom of PixelVertex a for color equivalency */
    public void checkDown(Color [][] image, PixelVertex a){
        int x = a.getX();
        int y = a.getY();
        if(image[x][y].equals(image[x][y + 1])){
            //System.out.println("down");
            //System.out.println(a.xCoor + " " + a.yCoor);
            //System.out.println(picture[0][1].xCoor + " " + picture[0][ 1].yCoor);
            //System.out.println(x + " " + (y + 1));
            a.addNeighbour(picture[x][y + 1]);
            
        }
    }
    
    /* Checks the element to the right of PixelVertex a for color equivalency */
    public void checkRight(Color [][] image, PixelVertex a){
        int x = a.getX();
        int y = a.getY();
        if(image[x][y].equals(image[x + 1][y])){
            //System.out.println("right");
            a.addNeighbour(picture[x + 1][y]);
        }
    }
}