package ww.shubham.timetracker;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;
import ww.shubham.timetracker.data.Category;
import ww.shubham.timetracker.data.CurrentTasks;
import ww.shubham.timetracker.data.Command;
import ww.shubham.timetracker.data.Task;
import ww.shubham.timetracker.util.Args;
import ww.shubham.timetracker.util.ArgsUtil;
import ww.shubham.timetracker.util.FileUtil;


@SpringBootApplication
public class TimeTrackerApplication {

	@Autowired
    private Environment env;
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(TimeTrackerApplication.class, args);

		System.out.println("Started runnning");

		ArgsUtil argsUtil = new ArgsUtil();
		Args arguments = argsUtil.parseArgs(args);

		// Get current task from file
		FileUtil fileUtil = new FileUtil();
		CurrentTasks currentTasks = fileUtil.getSavedTasks();
		switch(arguments.getCommand()){
			case Command.START_TASK:
				Task task = new Task(arguments.getTaskName(), new Category(arguments.getCategoryName()));
				currentTasks.addTasks(task);
				break;
			case Command.END_TASK:
				currentTasks.completeTask(arguments.getTaskName());
				break;
			case Command.REPORT_TASK:
				Map<String, Duration> taskReport = currentTasks.getTaskReport();
				for(Map.Entry<String, Duration> entry : taskReport.entrySet())
				{
					System.out.println("Task : " + entry.getKey());
					System.out.println("Duration (secs) : " + entry.getValue().toSeconds());
				}
				break;
			case Command.REPORT_CATEGORIES:
				Map<String, Duration> categoryReport = currentTasks.getCategoriesReport();
				for(Map.Entry<String, Duration> entry : categoryReport.entrySet())
				{
					System.out.println("Category : " + entry.getKey());
					System.out.println("Duration (secs) : " + entry.getValue().toSeconds());
				}
				break;
			default:
		}
		fileUtil.SaveTasksToFile(currentTasks);
		System.out.println(currentTasks);
	}

	@PostConstruct
    public void init() {
        String[] activeProfiles = env.getActiveProfiles();
        for (String profile : activeProfiles) {
            System.out.println("Active profile: " + profile);
        }
    }

}
