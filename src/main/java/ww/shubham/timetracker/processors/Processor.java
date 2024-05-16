package ww.shubham.timetracker.processors;

import ww.shubham.timetracker.data.Task;

public interface Processor {
    public void process(Task task);
}
