import bvm.*;

import java.util.*;

/**
 * Created by B on 07.10.2014.
 */
public class BrainfuckCompiler {

    public static final Set<Character> brainfuckCharSet = new HashSet<>();

    static {
        brainfuckCharSet.add('>');
        brainfuckCharSet.add('<');
        brainfuckCharSet.add('+');
        brainfuckCharSet.add('-');
        brainfuckCharSet.add('.');
        brainfuckCharSet.add('[');
        brainfuckCharSet.add(']');
    }

    private Stack<Integer> openedCyclesStartPoints = new Stack<>();
    private List<Command> commands;
    private List<Command> optimizedCommands;
    private int incrementsCounter = 0;
    private int decrementsCounter = 0;

    public List<Command> compile(String bfCode) {
        System.out.println("compile()");
        System.out.println("Input string: " + bfCode);
        System.out.print("Compilation started... ");

        long start = System.currentTimeMillis();

        if (!isValidGrammar(bfCode)) {
            System.out.println("Compilation error: bad grammar.\n");
            return null;
        }
        if (!isValidSyntax(bfCode)) {
            System.out.println("Compilation error: bad syntax.\n");
            return null;
        }

        BrainfuckVirtualMachine bvm = new BrainfuckVirtualMachine(1000); // bvm for compiling
        commands = new LinkedList<>(); // output
        char[] chars = bfCode.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
                case '>':
                    bvm.incrementPointer();
                    commands.add(new IncrementPointerCommand());
                    continue;
                case '<':
                    bvm.decrementPointer();
                    commands.add(new DecrementPointerCommand());
                    continue;
                case '+':
                    bvm.incrementCurrentCell();
                    commands.add(new IncrementCellCommand());
                    continue;
                case '-':
                    bvm.decrementCurrentCell();
                    commands.add(new DecrementCellCommand());
                    continue;
                case '.':
                    commands.add(new PrintCellCommand());
                    continue;
                case '[':
                    if (bvm.getCurrentCell() == 0) {
                        int counter = 1;
                        while (counter != 0 && i < chars.length) {
                            if (chars[i] == ']') counter--;
                            if (chars[i] == '[') counter++;
                        }
                    } else
                        openedCyclesStartPoints.add(i);
                    continue;
                case ']':
                    if (bvm.getCurrentCell() != 0)
                        i = openedCyclesStartPoints.peek();
                    else openedCyclesStartPoints.pop();
                    continue;
                default:
                    System.out.println("Compilation error.\n");
                    return null;
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("Compilation completed successfully in " + (double) (stop - start) / 1000 + " sec.");
        System.out.println("Output size: " + commands.size() + " .\n");
        return commands;
    }

    private boolean isValidSyntax(String bfCode) {
        int counter = 0;
        for (char c : bfCode.toCharArray()) {
            if (c == ']') counter--;
            if (c == '[') counter++;
        }
        if (counter == 0)
            return true;
        else return false;
    }

    private boolean isValidGrammar(String bfCode) {
        for (char c : bfCode.toCharArray()) {
            if (brainfuckCharSet.contains(c)) continue;
            else return false;
        }
        return true;
    }

    public List<Command> optimize(List<Command> commands) {
        System.out.println("optimize()");
        System.out.print("Input size: " + commands.size() + " .\nOptimization... ");
        optimizedCommands = new LinkedList<>();

        for (Command c : commands) {
            if (c instanceof IncrementCellCommand) {
                incrementsCounter++;
                continue;
            }
            checkCounter(incrementsCounter);

            if (c instanceof DecrementCellCommand) {
                decrementsCounter--;
                continue;
            }
            checkCounter(decrementsCounter);

            optimizedCommands.add(c);
        }

        checkCounter(incrementsCounter);
        checkCounter(decrementsCounter);

        System.out.println("Optimization complete successfully");
        System.out.println("Output size: " + optimizedCommands.size() + " .\n");
        return optimizedCommands;
    }

    private void checkCounter(int counter) {
        if (counter > 1) {
            optimizedCommands.add(new AddValueToCellCommand(counter));
            incrementsCounter = 0;
        } else if (counter == 1) {
            optimizedCommands.add(new IncrementCellCommand());
            incrementsCounter = 0;
        }

        if (counter < -1) {
            optimizedCommands.add(new AddValueToCellCommand(counter));
            decrementsCounter = 0;
        } else if (counter == -1) {
            optimizedCommands.add(new DecrementCellCommand());
            decrementsCounter = 0;
        }
    }
}
