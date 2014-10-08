import java.util.List;

/**
 * Created by B on 07.10.2014.
 */
public class CommandsExecuter {
    public static void run(BrainfuckVirtualMachine bvm, List<Command> commands) {
        System.out.println("Execution on \"Brainfuck Virtual Machine[" + bvm.getMemorySize() + "]\":");
        if (commands != null)
            for (Command command : commands)
                command.execute(bvm);
        else System.out.println("Execution error: List<BVMCommand> is null");
        System.out.println();
    }
}
