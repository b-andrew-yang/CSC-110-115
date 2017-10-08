/* Andrew Yang
   PixelVertex.java
   CSC 225 - Summer 2017
   Programming Assignment 3 - Pixel Vertex Data Structure
*/


public class PixelVertex{
    public int xCoor;
    public int yCoor;
    public PixelVertex[] neighbours;
    public boolean visited;
    
	/* Constructor for PixelVertex */
	public PixelVertex(int x, int y){
        xCoor = x;
        yCoor = y;
        
        neighbours = new PixelVertex[4];
        visited = false;
    }
    /* Constructor for PixelVertex */
    public PixelVertex(){
        PixelVertex[] neighbours = new PixelVertex[4];
        visited = false;
    }
	
	/* getX()
	   Return the x-coordinate of the pixel corresponding to this vertex.
	*/
	public int getX(){
		return xCoor;
	}
	
	/* getY()
	   Return the y-coordinate of the pixel corresponding to this vertex.
	*/
	public int getY(){
		return yCoor;
	}
	
	/* getNeighbours()
	   Return an array containing references to all neighbours of this vertex.
	*/
	public PixelVertex[] getNeighbours(){
		return neighbours;
	}
	
	/* addNeighbour(newNeighbour)
	   Add the provided vertex as a neighbour of this vertex.
	*/
	public void addNeighbour(PixelVertex newNeighbour){
        
        System.out.println("b");
        if(neighbours[0] == null){
            neighbours[0] = newNeighbour;
        }else if(neighbours[1] == null){
            neighbours[1] = newNeighbour;
        }else if(neighbours[2] == null){
            neighbours[2] = newNeighbour;
        }else if(neighbours[3] == null){
            neighbours[3] = newNeighbour;
        }
	}
	
	/* removeNeighbour(neighbour)
	   If the provided vertex object is a neighbour of this vertex,
	   remove it from the list of neighbours.
	*/
	public void removeNeighbour(PixelVertex neighbour){
        for(int i = 0; i< neighbours.length; i++){
            if(neighbours[i] == neighbour){
                neighbours[i] = null;
            }else{
                System.out.println("Neighbour doesn't exist.");
            }
        }
	}
	
	/* getDegree()
	   Return the degree of this vertex.
	*/
	public int getDegree(){
        int degree = 0;
        
        for(int i = 0; i< neighbours.length; i++){
            if(neighbours[i] != null){
                degree++;
            }
        }
		return degree;
	}
	
	/* isNeighbour(otherVertex)
	   Return true if the provided PixelVertex object is a neighbour of this
	   vertex and false otherwise.
	*/
	public boolean isNeighbour(PixelVertex otherVertex){
        boolean isNeighbour = false;
        
        for(int i = 0; i< neighbours.length; i++){
            if(neighbours[i] == otherVertex){
                isNeighbour = true;
            }
        }
		return isNeighbour;
	}
	/* Returns the visited value */
    public boolean getVisited(){
        return visited;
    }
    
    /* Sets the PixelVertex as visited */
    public void setVisited(){
        visited = true;
    }
}