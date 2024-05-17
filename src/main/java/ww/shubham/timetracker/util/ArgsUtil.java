package ww.shubham.timetracker.util;

import ww.shubham.timetracker.Logger;
import ww.shubham.timetracker.data.Category;
import ww.shubham.timetracker.data.Command;

public class ArgsUtil {
    
    public static void main(String[] args) {
        //Args args = new Args();

    }

    public Args parseArgs(String[] args)
    {
        if(!validate(args)){
            throw new RuntimeException("Invalid Arguments");
        }
        Args argsObj = new Args();

        String cmdString = args[0];
        Command command = switch(cmdString){
            case "start"-> Command.START_TASK;
            case "stop"-> Command.END_TASK;
            case "report"-> "task".equals(args[1]) ? Command.REPORT_TASK :
                            "category".equals(args[1]) ? Command.REPORT_CATEGORIES : null;
            default -> throw new RuntimeException("Invalid Input arguments");
        };

        argsObj.setCommand(command);

        if(Command.START_TASK.equals(command) || Command.END_TASK.equals(command)){
            argsObj.setTaskName(args[1]);
            argsObj.setCategoryName(args.length == 3 ? args[2] : Category.NONE);
        }
        return argsObj;
       
    }

    public boolean validate(String[] args)
    {
        if(args.length < 2){
            Logger.log("Error ! Not Enough Arguments!");
            return false;
        }
        return true;

    }

}
