package ww.shubham.timetracker.data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import ww.shubham.timetracker.Logger;

@Getter
@Setter
public class CurrentTasks {

    private Map<String, Task> currentTask = new HashMap<>();

    public void addTasks(Task task){
        if (currentTask.putIfAbsent(task.getTaskName(), task) != null){
            Logger.log("Task already exists, skipping");
        } 
    }

     public void completeTask(Task task){
        Task existingTask = currentTask.get(task.getTaskName());
        if (existingTask == null){
            Logger.log("No task found");
        } else {
            existingTask.setEndTime(LocalDateTime.now());
            existingTask.setTaskStatus(TaskStatus.COMPLETE);
        }
    }

}
