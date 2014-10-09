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

    public List<Command> compile(String code) {
        if (!validCharSet(code) || !validBrackets(code)) {
            System.out.println("Compilation error.\n");
            return null;
        }

        List<Command> commands = new ArrayList<>(); // output
        Deque<Cycle> cyclesStack = new ArrayDeque<>();
        Cycle currentCycle = null;
        char[] chars = code.toCharArray();

        for (char c : chars) {
            if (c == '[') { // начался цикл
                Cycle cycle = new Cycle();

                if (currentCycle != null) { // если true, значит новый цикл открывается внутри текущего
                    currentCycle.addCommand(cycle); // добвляем команду Cycle к командам текущего цикла
                    cyclesStack.push(currentCycle); // текущий цикл сохраняем в стек
                } else // это цикл верхнего уровня, добавляем его в основные команды
                    commands.add(cycle);

                currentCycle = cycle; // замена текущего цикла (или null-а, если цикла еще не было) - только что созданным циклом.
                continue;
            } else if (c == ']') { // цикл закончился
                // получаем из стека предыдущий цикл (согласно вложенностям)
                // если закончился цикл верхнего уровня - текущему циклу присваивается null
                currentCycle = cyclesStack.poll();
                continue;
            }

            if (currentCycle != null) // true если сейчас читаются команды внутри цикла верхнего уровня
                currentCycle.addCommand(SimpleCommandsFactory.createCommand(c));
            else // обычная команда (не в цикле)
                commands.add(SimpleCommandsFactory.createCommand(c));
        }
        return commands;
    }

    private boolean validCharSet(String code) {
        for (char c : code.toCharArray())
            if (!brainfuckCharSet.contains(c)) return false;
        return true;
    }

    private boolean validBrackets(String code) {
        int counter = 0;
        for (char c : code.toCharArray()) {
            if (c == ']') counter--;
            if (c == '[') counter++;
        }
        // if counter == 0 - all brackets are closed
        return counter == 0;
    }
}
