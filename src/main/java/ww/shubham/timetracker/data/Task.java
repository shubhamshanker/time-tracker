package ww.shubham.timetracker.data;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {

    private String taskName;
    private Category category;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private TaskStatus taskStatus;

    public Task(String taskName, Category category)
    {
        this.taskName = taskName;
        this.category = category;
        this.startTime = LocalDateTime.now();
        this.taskStatus = TaskStatus.IN_PROGRESS;
    }
}
