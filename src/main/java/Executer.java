import bvm.BrainfuckVirtualMachine;
import bvm.Command;

import java.util.List;

/**
 * @author Bogdan Kovalev
 */
public interface Executer {
    String run(List<Command> commands);

    String run(BrainfuckVirtualMachine bvm, List<Command> commands);
}
