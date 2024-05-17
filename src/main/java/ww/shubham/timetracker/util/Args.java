package ww.shubham.timetracker.util;

import lombok.Getter;
import lombok.Setter;
import ww.shubham.timetracker.data.Command;

@Getter
@Setter
public class Args {
    private Command command;
    private String taskName;
    private String categoryName;
}
