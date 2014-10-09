import bvm.BrainfuckVirtualMachine;

import java.util.Stack;

/**
 * @author Bogdan Kovalev
 */
public class BrainfuckInterpreter implements Interpreter {

    private Stack<Integer> openedCyclesStartPoints = new Stack<>();

    public String interpret(String bfCode) {
        return interpret(new BrainfuckVirtualMachine(1000), bfCode);
    }

    public String interpret(BrainfuckVirtualMachine bvm, String bfCode) {
        char[] chars = bfCode.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
                case '>':
                    bvm.incrementPointer();
                    continue;
                case '<':
                    bvm.decrementPointer();
                    continue;
                case '+':
                    bvm.incrementCurrentCell();
                    continue;
                case '-':
                    bvm.decrementCurrentCell();
                    continue;
                case '.':
                    bvm.printCurrentCell();
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
                    System.out.println("Unknown symbol. Program stopped.\n");
                    return bvm.getOut();
            }
        }
        return bvm.getOut();
    }
}
