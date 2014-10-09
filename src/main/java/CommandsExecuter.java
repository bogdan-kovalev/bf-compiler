import bvm.BrainfuckVirtualMachine;
import bvm.Command;

import java.util.List;

/**
 * @author Bogdan Kovalev
 */
public class CommandsExecuter implements Executer {

    public String run(List<Command> commands) {
        return run(new BrainfuckVirtualMachine(1000), commands);
    }

    public String run(BrainfuckVirtualMachine bvm, List<Command> commands) {
        if (commands != null)
            for (Command command : commands)
                command.execute(bvm);
        return bvm.getOut();
    }
}
