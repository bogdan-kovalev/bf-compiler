import java.util.*;

/**
 * Created by B on 07.10.2014.
 */
public class BrainfuckCompiler {

    public static final Set<Character> brainfuckCharSet = new HashSet<>();
    private Stack<Integer> openedCyclesStartPoints = new Stack<>();

    static {
        brainfuckCharSet.add('>');
        brainfuckCharSet.add('<');
        brainfuckCharSet.add('+');
        brainfuckCharSet.add('-');
        brainfuckCharSet.add('.');
        brainfuckCharSet.add('[');
        brainfuckCharSet.add(']');
    }


    public List<Command> compile(String bfCode) {
        // TODO время компиляции
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

        BrainfuckVirtualMachine bvm = new BrainfuckVirtualMachine(1000000); // bvm for compiling
        List<Command> commands = new LinkedList<>();
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
        // mirror brackets checking
        char[] chars = bfCode.toCharArray();
        int counter = 0;
        for (char c : chars) {
            if (c == ']') counter--;
            if (c == '[') counter++;
        }
        if (counter == 0)
            return true;
        else return false;
    }

    private boolean isValidGrammar(String bfCode) {
        char[] chars = bfCode.toCharArray();
        for (char c : chars) {
            if (brainfuckCharSet.contains(c)) continue;
            else return false;
        }
        return true;
    }

    public List<Command> optimize(List<Command> commands) {
        System.out.println("optimize()");
        System.out.print("Input size: " + commands.size() + " .\nOptimization...");
        List<Command> optimizedCommands = new LinkedList<>();
        int incrementsCounter = 0;
        int decrementsCounter = 0;

        for (Command c : commands) {
            if (c instanceof IncrementCellCommand) {
                incrementsCounter++;
                continue;
            }
            else if (incrementsCounter > 1) {
                optimizedCommands.add(new AddValueToCellCommand(incrementsCounter));
                incrementsCounter = 0;
            } else if (incrementsCounter == 1) {
                optimizedCommands.add(new IncrementCellCommand());
                incrementsCounter = 0;
            }

            if (c instanceof DecrementCellCommand) {
                decrementsCounter++;
                continue;
            }
            else if (decrementsCounter > 1) {
                optimizedCommands.add(new AddValueToCellCommand(-decrementsCounter));
                decrementsCounter = 0;
            } else if (decrementsCounter == 1) {
                optimizedCommands.add(new DecrementCellCommand());
                decrementsCounter = 0;
            }
            optimizedCommands.add(c);
        }

        if (incrementsCounter > 1) {
            optimizedCommands.add(new AddValueToCellCommand(incrementsCounter));
        } else if (incrementsCounter == 1) {
            optimizedCommands.add(new IncrementCellCommand());
        }

        if (decrementsCounter > 1) {
            optimizedCommands.add(new AddValueToCellCommand(-decrementsCounter));
        } else if (decrementsCounter == 1) {
            optimizedCommands.add(new DecrementCellCommand());
        }
        System.out.println("Optimization complete successfully");
        System.out.println("Output size: " + optimizedCommands.size()+ " .\n");
        return optimizedCommands;
    }
}
