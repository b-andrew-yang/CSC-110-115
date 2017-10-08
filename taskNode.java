public class taskNode{
    private int task_id;
    private int priority;
    private int time_remaining;
    
    public taskNode(){
        
    }
    
    public taskNode(int task_id, int priority, int time_remaining){
        this.task_id = task_id;
        this.priority = priority;
        this.time_remaining = time_remaining;
    }
    
    public taskNode(int task_id, int time_remaining){
        this.task_id = task_id;
        priority = 0;
        this.time_remaining = time_remaining;
    }
    
    public int getTask_ID(){
        return task_id;
    }
    
    public int getPriority(){
        return priority;
    }
    
    public int getTime_Remaining(){
        return time_remaining;
    }
    
    public void setPriority(int newPriority){
        priority = newPriority;
    }
    
    public void runTime(){
        time_remaining--;
    }
    
    public String toString(){
        return String.valueOf(task_id);
    }
}