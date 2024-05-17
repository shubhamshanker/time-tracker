package ww.shubham.timetracker.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ww.shubham.timetracker.data.Category;
import ww.shubham.timetracker.data.CurrentTasks;
import ww.shubham.timetracker.data.Task;
import ww.shubham.timetracker.data.TaskStatus;

public class FileUtil{

    public static final String PATH = "task-info.csv";

    public CurrentTasks getSavedTasks() throws IOException{
        
        Path path = Paths.get(PATH);
        if(Files.notExists(path))
            Files.createFile(path);

        Map<String, Task> taskMap = Files.lines(path)
                    .map(line -> line.split(","))
                    .filter(tokenArray -> tokenArray.length == 5)
                    .map(tokenArray -> new Task(
                        tokenArray[0],
                        new Category(tokenArray[1]),
                        tokenArray[2] == "null" ? null : LocalDateTime.parse(tokenArray[2]),
                        "null".equals(tokenArray[3])  ? null : LocalDateTime.parse(tokenArray[3]),
                        TaskStatus.valueOf(tokenArray[4])
                    ))
                    .collect(Collectors.toMap(Task::getTaskName, Function.identity()));
        return new CurrentTasks(taskMap);
    }

    public void SaveTasksToFile(CurrentTasks tasks) throws IOException {
        Path path = Paths.get(PATH);
        if(Files.notExists(path))
            Files.createFile(path);
        
        List<String> lines = tasks.getCurrentTask()
            .values()
            .stream()
            .map(Task::getCSVFormat)
            .toList();
        
        Files.write(path, lines);
    }
}
