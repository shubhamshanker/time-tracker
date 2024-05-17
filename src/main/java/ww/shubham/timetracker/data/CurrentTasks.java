package ww.shubham.timetracker.data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ww.shubham.timetracker.Logger;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CurrentTasks {

    private Map<String, Task> currentTask = new HashMap<>();

    public void addTasks(Task task){
        if (currentTask.putIfAbsent(task.getTaskName(), task) != null){
            Logger.log("Task already exists, skipping");
        } 
    }

    public Map<String, Duration> getTaskReport(){

        return currentTask
                .values()
                .stream()
                .filter(task -> task.getEndTime() != null)
                .collect(Collectors.toMap(Task::getTaskName, Task::getTaskDuration));

    }

    public Map<String, Duration> getCategoriesReport(){
        Map<String, Duration> categortyReport = new HashMap<>();
        currentTask
            .values()
            .stream()
            .filter(task -> task.getEndTime() != null)
            .forEach(task -> {
                String category = task.getCategory().getName();
                Duration categoryDuration = 
                categortyReport.getOrDefault(category, Duration.ZERO);
                categortyReport.put(category, categoryDuration.plus(task.getTaskDuration()));
            });
        return categortyReport;

    }

    public void completeTask(String taskName){
        Task existingTask = currentTask.get(taskName);
        if (existingTask == null){
            Logger.log("No task found");
        } else {
            existingTask.setEndTime(LocalDateTime.now());
            existingTask.setTaskStatus(TaskStatus.COMPLETE);
        }
    }

}
