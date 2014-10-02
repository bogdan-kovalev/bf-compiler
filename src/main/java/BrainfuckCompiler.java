import java.util.Stack;

/**
 * Created by bogdan on 02.10.14.
 */
public class BrainfuckCompiler {
    public static final String bfCodeHelloWorld = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++." +
            ".+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
    public static final String bfCode2Cycles = "++++++++++[>+++o++[>+<-]<-]>>.";
    private char[] memory;
    private int p;
    private Stack<Integer> stack = new Stack<Integer>();


    public void interpret(String bfCode) {
        memory = new char[10000];
        p = 0;
        char[] chars = bfCode.toCharArray();
        cycle:
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
                case '>':
                    if (p == memory.length - 1) p = 0;
                    else p++;
                    continue;
                case '<':
                    if (p == 0) p = memory.length - 1;
                    else p--;
                    continue;
                case '+':
                    memory[p]++;
                    continue;
                case '-':
                    memory[p]--;
                    continue;
                case '.':
                    System.out.print(memory[p] + " ");
                    continue;
                case '[':
                    if (memory[p] == 0) {
                        int counter = 1;
                        while (counter != 0 && i < chars.length) {
                            if (chars[i] == ']') counter--;
                            if (chars[i] == '[') counter++;
                        }
                    } else
                        stack.add(i);
                    continue;
                case ']':
                    if (memory[p] != 0)
                        i = stack.peek();
                    else stack.pop();
                    continue;
                default:
                    System.out.println("Unknown symbol. Program stopped.");
                    break cycle;
            }
        }
        System.out.println();
    }

    public void compileAndRun(String bfCode) {

    }

    public static void main(String[] args) {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        brainfuckCompiler.interpret(bfCodeHelloWorld);
        brainfuckCompiler.interpret(bfCode2Cycles);
    }
}
