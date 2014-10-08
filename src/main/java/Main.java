import java.util.List;

/**
 * Created by B on 07.10.2014.
 */
public class Main {
    public static final String bfCodeHelloWorld = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++." +
            ".+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";

    public static void main(String[] args) {
        BrainfuckInterpreter brainfuckInterpreter = new BrainfuckInterpreter();
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();

        brainfuckInterpreter.interpret(new BrainfuckVirtualMachine(1000), bfCodeHelloWorld);

        List<Command> compiledBfCode1 = brainfuckCompiler.compile(bfCodeHelloWorld);
        List<Command> optimized = brainfuckCompiler.optimize(compiledBfCode1);

        CommandsExecuter.run(new BrainfuckVirtualMachine(1000), compiledBfCode1);
        CommandsExecuter.run(new BrainfuckVirtualMachine(1000), optimized);
    }
}
