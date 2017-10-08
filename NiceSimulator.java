/* NiceSimulator.java
   CSC 225 - Summer 2017

   An empty shell of the operations needed by the NiceSimulator
   data structure.

   B. Bird - 06/11/2017
*/


import java.io.*;
import java.util.Arrays;

public class NiceSimulator{

	public static final int SIMULATE_IDLE = -2;
	public static final int SIMULATE_NONE_FINISHED = -1;
	
    private int numberOfTasks = 0;
    private taskNode[] taskArray;
    private int[] mapArray;
	
	/* Constructor(maxTasks)
	   Instantiate the data structure with the provided maximum 
	   number of tasks. No more than maxTasks different tasks will
	   be simultaneously added to the simulator, and additionally
	   you may assume that all task IDs will be in the range
	     0, 1, ..., maxTasks - 1
	*/
	public NiceSimulator(int maxTasks){
        taskArray = new taskNode[maxTasks - 1];
        
        mapArray = new int[maxTasks - 1];
	}
	
	/* taskValid(taskID)
	   Given a task ID, return true if the ID is currently
	   in use by a valid task (i.e. a task with at least 1
	   unit of time remaining) and false otherwise.
	   
	   Note that you should include logic to check whether 
	   the ID is outside the valid range 0, 1, ..., maxTasks - 1
	   of task indices.
	
	*/
	public boolean taskValid(int taskID){
        if(mapArray[taskID] == 0){
            return false;
        }else if(taskArray[mapArray[taskID]].getTime_Remaining() < 1){
            return false;
        }
		return true;
	}
	
	/* getPriority(taskID)
	   Return the current priority value for the provided
	   task ID. You may assume that the task ID provided
	   is valid.
	
	*/
	public int getPriority(int taskID){
        return taskArray[mapArray[taskID]].getPriority();
	}
	
	/* getRemaining(taskID)
	   Given a task ID, return the number of timesteps
	   remaining before the task completes. You may assume
	   that the task ID provided is valid.
	
	*/
	public int getRemaining(int taskID){
		return taskArray[mapArray[taskID]].getTime_Remaining();
	}
	
	
	/* add(taskID, time_required)
	   Add a task with the provided task ID and time requirement
	   to the system. You may assume that the provided task ID is in
	   the correct range and is not a currently-active task.
	   The new task will be assigned nice level 0.
	*/
	public void add(int taskID, int time_required){
        taskNode a = new taskNode(taskID, time_required);
        numberOfTasks++;
        
        taskArray[numberOfTasks] = a;
        mapArray[a.getTask_ID()] = numberOfTasks;
        
        int parent = numberOfTasks/2;
        int child = numberOfTasks;
        
        while(taskArray[parent] != null && a.getPriority() <= taskArray[parent].getPriority()){
            if(a.getPriority() != taskArray[parent].getPriority()){
                taskNode temp = taskArray[parent];
                taskArray[parent] = a;
                taskArray[child] = temp;
                mapArray[a.getTask_ID()] = parent;
                mapArray[temp.getTask_ID()] = child;
            }else if(a.getPriority() == taskArray[parent].getPriority()){
                if(a.getTask_ID() < taskArray[parent].getTask_ID()){
                    taskNode temp = taskArray[parent];
                    taskArray[parent] = a;
                    taskArray[child] = temp;
                    mapArray[a.getTask_ID()] = parent;
                    mapArray[temp.getTask_ID()] = child;
                }
            }
            
            
            /*if(a.getPriority() == taskArray[parent].getPriority() && (a.getTask_ID() < taskArray[parent].getTask_ID())){
                taskNode temp = taskArray[parent];
                taskArray[parent] = a;
                taskArray[child] = temp;
                mapArray[a.getTask_ID()] = parent;
                mapArray[temp.getTask_ID()] = child;
            }else{
                taskNode temp = taskArray[parent];
                taskArray[parent] = a;
                taskArray[child] = temp;
                mapArray[a.getTask_ID()] = parent;
                mapArray[temp.getTask_ID()] = child;
            }*/
            
            child = parent;
            parent = parent/2;
        }
        taskNode[] tempArray = new taskNode[20];
        
        for(int i =0; i<20; i++){
            tempArray[i] = taskArray[i];
        }
        System.out.println(Arrays.deepToString(tempArray));
	}
	
	
	/* kill(taskID)
	   Delete the task with the provided task ID from the system.
	   You may assume that the provided task ID is in the correct
	   range and is a currently-active task.
	*/
	public void kill(int taskID){
        int parent = mapArray[taskID]; //Get the index that we're deleting in the heap
        mapArray[taskArray[numberOfTasks].getTask_ID()] = mapArray[taskID]; //Set the mapArray for the ID of the last task to point at the spot we're trying to delete
        taskArray[mapArray[taskID]] = taskArray[numberOfTasks]; //Insert the last task into the spot where we're trying to delete.
        numberOfTasks--;
        
        int leftChild = 2 * parent; //Stores the index of the leftChild in the taskArray
        int rightChild = 2 * parent + 1; //Stores the index of the rightChild in the taskArray
        int smallerChild = 0;
        if(taskArray[rightChild] != null){
            if(taskArray[leftChild].getPriority() < taskArray[rightChild].getPriority()){
                smallerChild = leftChild;
            }else if(taskArray[rightChild].getPriority() == taskArray[leftChild].getPriority()){
                if(taskArray[rightChild].getTask_ID() < taskArray[leftChild].getTask_ID()){
                    smallerChild = rightChild;
                }else{
                    smallerChild = leftChild;
                }
            }else{
                smallerChild = rightChild;
            }
        }else if(taskArray[leftChild] != null){
            smallerChild = leftChild;
        }
        
        while(taskArray[leftChild] != null && (taskArray[parent].getPriority() > taskArray[smallerChild].getPriority())){ //If we have a child node and the parent node is greater than one of the child nodes we swap
                taskNode temp = taskArray[parent];
                taskArray[parent] = taskArray[smallerChild];
                taskArray[smallerChild] = temp;
                mapArray[taskArray[parent].getTask_ID()] = smallerChild;
                mapArray[taskArray[smallerChild].getTask_ID()] = parent;
                
                parent = smallerChild;
                leftChild = 2 * parent;
                rightChild = 2 * parent + 1;
                
                if(taskArray[rightChild] != null){
                    if(taskArray[leftChild].getPriority() < taskArray[rightChild].getPriority()){
                        smallerChild = leftChild;
                    }else if(taskArray[rightChild].getPriority() == taskArray[leftChild].getPriority()){
                        if(taskArray[rightChild].getTask_ID() < taskArray[leftChild].getTask_ID()){
                            smallerChild = rightChild;
                        }else{
                            smallerChild = leftChild;
                        }
                    }else{
                        smallerChild = rightChild;
                    }
                }else if(taskArray[leftChild] != null){
                    smallerChild = leftChild;
                }else{
                    break;
                }
        }
        taskNode[] tempArray = new taskNode[20];
        
        for(int i =0; i<20; i++){
            tempArray[i] = taskArray[i];
        }
        System.out.println(Arrays.deepToString(tempArray));
    }
	
	
	/* renice(taskID, new_priority)
	   Change the priority of the the provided task ID to the new priority
       value provided. The change must take effect at the next simulate() step.
	   You may assume that the provided task ID is in the correct
	   range and is a currently-active task.
	
	*/
	public void renice(int taskID, int new_priority){
        int time_required = taskArray[mapArray[taskID]].getTime_Remaining();
        kill(taskID);
            
        taskNode a = new taskNode(taskID, new_priority, time_required);
        numberOfTasks++;
        
        taskArray[numberOfTasks] = a;
        mapArray[a.getTask_ID()] = numberOfTasks;
        
        int parent = numberOfTasks/2;
        int child = numberOfTasks;
        
        while(taskArray[parent] != null && a.getPriority() <= taskArray[parent].getPriority()){
            if(a.getPriority() != taskArray[parent].getPriority()){
                taskNode temp = taskArray[parent];
                taskArray[parent] = a;
                taskArray[child] = temp;
                mapArray[a.getTask_ID()] = parent;
                mapArray[temp.getTask_ID()] = child;
            }else if(a.getPriority() == taskArray[parent].getPriority()){
                if(a.getTask_ID() < taskArray[parent].getTask_ID()){
                    taskNode temp = taskArray[parent];
                    taskArray[parent] = a;
                    taskArray[child] = temp;
                    mapArray[a.getTask_ID()] = parent;
                    mapArray[temp.getTask_ID()] = child;
                }
            }
            
            child = parent;
            parent = parent/2;
        }
        
        taskNode[] tempArray = new taskNode[20];
        for(int i =0; i<20; i++){
            tempArray[i] = taskArray[i];
        }
        System.out.println(Arrays.deepToString(tempArray));
	}

	
	/* simulate()
	   Run one step of the simulation:
		 - If no tasks are left in the system, the CPU is idle, so return
		   the value SIMULATE_IDLE.
		 - Identify the next task to run based on the criteria given in the
		   specification (tasks with the lowest priority value are ranked first,
		   and if multiple tasks have the lowest priority value, choose the 
		   task with the lowest task ID).
		 - Subtract one from the chosen task's time requirement (since it is
		   being run for one step). If the task now requires 0 units of time,
		   it has finished, so remove it from the system and return its task ID.
		 - If the task did not finish, return SIMULATE_NONE_FINISHED.
	*/
	public int simulate(){
        taskNode[] tempArray = new taskNode[20];
        /*for(int i =0; i<numberOfTasks; i++){
            tempArray[i] = taskArray[i];
        }
        System.out.println(Arrays.deepToString(tempArray));*/
        
        if(taskArray[1]!= null){//If there is a task
            if(taskArray[1].getTime_Remaining() > 0){
                    taskArray[1].runTime();
                if(taskArray[1].getTime_Remaining() == 0){
                    taskNode temp = taskArray[1];
                    kill(taskArray[1].getTask_ID());
                    return temp.getTask_ID();
                }
                return SIMULATE_NONE_FINISHED;
            }
        }
		return SIMULATE_IDLE;
	}


}