package ww.shubham.timetracker.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category {

    public static final String NONE = "no-category";

    public Category(String categoryName) {
        this.name = categoryName;
    }
    private String name;
    private int totalTime;

}
