/**
 * @author Bogdan Kovalev
 */
public class Main {
    public static final String helloWorld = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++." +
            ".+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";

    public static void main(String[] args) {
        Interpreter interpreter = new BrainfuckInterpreter();

        System.out.println(interpreter.interpret(helloWorld));

        Compiler compiler = new BrainfuckCompiler();
        Executer executer = new CommandsExecuter();

        System.out.println(executer.run(compiler.compile(helloWorld)));
    }
}
