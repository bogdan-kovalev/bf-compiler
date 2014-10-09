package bvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kovalev
 */
public class Cycle implements Command {
    private final List<Command> commandsInCycle = new ArrayList<>();

    public void addCommand(Command command) {
        commandsInCycle.add(command);
    }

    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        while (bvm.getCurrentCell() != 0)
            for (Command c : commandsInCycle)
                c.execute(bvm);
    }
}
