/* Andrew Yang
   A5Algorithms.java
   CSC 225 - Summer 2017
   Programming Assignment 3 - Image Algorithms
*/ 

import java.awt.Color;
import java.util.*;

public class A3Algorithms{

	/* FloodFillDFS(v, viewer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void FloodFillDFS(PixelVertex v, ImageViewer225 viewer, Color fillColour){
        //Mark v as visited
        //Visit v
        
        //for each neighbour w of v do
        //  if w has not already been visited then 
        //      (w,v) is a discovery edge
        //      Recursively traverse w
        //      FloodFillDFS(w, viewer, fillColour)
        //  else 
        //      (w,v) is a back edge
        //  end if/else
        //end for
        
        //boolean[][];
        viewer.setPixel(v.getX(), v.getY(), fillColour);
        v.setVisited();
        
        PixelVertex[] vNeighbours = v.getNeighbours();
        
        for(int i = 0; i< 4; i++){
            System.out.println(vNeighbours[i]);
            if(vNeighbours[i] != null && vNeighbours[i].getVisited() == false){
                FloodFillDFS(vNeighbours[i], viewer, fillColour);
            }
        }
	}
	
	/* FloodFillBFS(v, viewer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, ImageViewer225 viewer, Color fillColour){
		// Q <-- empty queue
        // Enqueue r into Q
        // Mark r as visited
        // while Q is non-empty do
        //      v <-- dequeue(Q)
        //      for each neighbur of w of v do
        //          if w is unvisited then 
        //              Mark w as visited 
        //              Enqueue w in Q
        //          end if
        //      end for
        // end while
        
        Queue<PixelVertex> Q = new LinkedList<PixelVertex>();
        viewer.setPixel(v.getX(), v.getY(), fillColour);
        Q.add(v);
        
        while(Q.peek() != null){
            //System.out.println(Q.peek().getX() + " " + Q.peek().getY());
            PixelVertex w = Q.poll();
            PixelVertex[] wNeighbours = w.getNeighbours();
            for(int i = 0; i< 4; i++){
                if(wNeighbours[i] != null){
                   // System.out.println("test");
                    if(!wNeighbours[i].getVisited()){
                        //System.out.println("test2");
                        viewer.setPixel(wNeighbours[i].getX(), wNeighbours[i].getY(), fillColour);
                        wNeighbours[i].setVisited();
                        Q.add(wNeighbours[i]);
                    }
                }
            }
        }
	}
	
	/* OutlineRegionDFS(v, viewer, outlineColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void OutlineRegionDFS(PixelVertex v, ImageViewer225 viewer, Color outlineColour){
		/* Your code here */
        // if the neighbours array != size 4 then color in.
        
        PixelVertex[] vNeighbours = v.getNeighbours();
        v.setVisited();
        if(vNeighbours[3] == null){
            viewer.setPixel(v.getX(), v.getY(), outlineColour);
        }
        
        for(int i = 0; i< 4; i++){
            if(vNeighbours[i] != null && !vNeighbours[i].getVisited()){
                OutlineRegionDFS(vNeighbours[i], viewer, outlineColour);
            }
        }
	}
	
	/* OutlineRegionBFS(v, viewer, outlineColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			viewer.setPixel(x,y,c);
	*/
	public static void OutlineRegionBFS(PixelVertex v, ImageViewer225 viewer, Color outlineColour){
		/* Your code here */
        
        Queue<PixelVertex> Q = new LinkedList<PixelVertex>();
        PixelVertex[] vNeighbours = v.getNeighbours();
        
        if(vNeighbours[3] == null){
            viewer.setPixel(v.getX(), v.getY(), outlineColour);
        }
        Q.add(v);
        v.setVisited();
        
        while(Q.peek() != null){
            PixelVertex w = Q.poll();
            //System.out.println(w);
            PixelVertex[] wNeighbours = w.getNeighbours();

            if(wNeighbours[3] == null){
                viewer.setPixel(w.getX(), w.getY(), outlineColour);
            }
            
            for(int i = 0; i< 4; i++){
                if(wNeighbours[i] != null && !wNeighbours[i].getVisited()){
                    Q.add(wNeighbours[i]);
                    wNeighbours[i].setVisited();
                }
            }
        }
        
	}

	/* CountComponents(G)
	   Count the number of connected components in the provided PixelGraph 
	   object.
	*/
	public static int CountComponents(PixelGraph G){
		/* Your code here */
        PixelVertex[] components = new PixelVertex[G.getWidth() * G.getHeight()];
        int numComponents = 0;
        int index = 0;
        Queue<PixelVertex>Q = new LinkedList<PixelVertex>();
        
            
        for(int i = 0; i< G.getWidth() ; i++){
            for(int j = 0; j<G.getHeight() ; j++){
                if(!G.getPixelVertex(i,j).getVisited()){
                    index++;
                    System.out.println(index);
                    Q.add(G.getPixelVertex(i,j));
                    while(Q.peek() != null){
                        PixelVertex w = Q.poll();
                        w.setVisited();
                        PixelVertex[] wNeighbours = w.getNeighbours();
                        for(int k = 0; k< 4; k++){
                            if(wNeighbours[k] != null && !wNeighbours[k].getVisited()){
                                Q.add(wNeighbours[k]);
                                wNeighbours[k].setVisited();
                            }
                        }
                    }
                }
            }
        }
        return index;
	}
}