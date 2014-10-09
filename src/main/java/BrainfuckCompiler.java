import bvm.Command;
import bvm.Cycle;
import bvm.SimpleCommandsFactory;

import java.util.*;

/**
 * @author Bogdan Kovalev
 */
public class BrainfuckCompiler implements Compiler {

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

    public List<Command> compile(String bfCode) {
        long start = System.currentTimeMillis();

        if (!isValidGrammar(bfCode)) {
            System.out.println("Compilation error: bad grammar.\n");
            return null;
        }
        if (!isValidSyntax(bfCode)) {
            System.out.println("Compilation error: bad syntax.\n");
            return null;
        }

        List<Command> commands = new ArrayList<>();
        Deque<Cycle> cyclesStack = new ArrayDeque<>();
        Cycle currentCycle = null;
        char[] chars = bfCode.toCharArray();

        for (char c : chars) {
            if (c == '[') {
                Cycle cycle = new Cycle();
                if (currentCycle != null) {
                    currentCycle.addCommand(cycle);
                    cyclesStack.push(currentCycle);
                } else
                    commands.add(cycle);
                currentCycle = cycle;
                continue;
            } else if (c == ']') {
                currentCycle = cyclesStack.poll();
                continue;
            }

            if (currentCycle != null)
                currentCycle.addCommand(SimpleCommandsFactory.createCommand(c));
            else
                commands.add(SimpleCommandsFactory.createCommand(c));
        }

        long stop = System.currentTimeMillis();
        System.out.println("Compilation completed successfully in " + (double) (stop - start) / 1000 + " sec.");
        return commands;
    }

    private boolean isValidSyntax(String bfCode) {
        int counter = 0;
        for (char c : bfCode.toCharArray()) {
            if (c == ']') counter--;
            if (c == '[') counter++;
        }
        return counter == 0;
    }

    private boolean isValidGrammar(String bfCode) {
        for (char c : bfCode.toCharArray())
            if (!brainfuckCharSet.contains(c)) return false;
        return true;
    }
}
