import bvm.BrainfuckVirtualMachine;
import bvm.Command;

import java.util.List;

/**
 * Created by B on 07.10.2014.
 */
public class Main {
    public static final String helloWorld = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++." +
            ".+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";

    public static void main(String[] args) {
        BrainfuckInterpreter brainfuckInterpreter = new BrainfuckInterpreter();
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();

        brainfuckInterpreter.interpret(new BrainfuckVirtualMachine(1000), helloWorld);

        List<Command> compiled = brainfuckCompiler.compile(helloWorld);
        List<Command> optimized = brainfuckCompiler.optimize(compiled);

        CommandsExecuter.run(new BrainfuckVirtualMachine(1000), compiled);
        CommandsExecuter.run(new BrainfuckVirtualMachine(1000), optimized);
    }
}
