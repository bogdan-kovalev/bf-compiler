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
        System.out.println("Execution on \"Brainfuck Virtual Machine[" + bvm.getMemorySize() + "]\":");
        if (commands != null)
            for (Command command : commands)
                command.execute(bvm);
        else System.out.println("Execution error: commands list is null");
        return bvm.getOut();
    }
}
