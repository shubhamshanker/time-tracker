package ww.shubham.timetracker.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category {

    private String name;
    private int totalTime;

}
