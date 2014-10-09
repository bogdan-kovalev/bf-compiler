package bvm;

/**
 * @author Bogdan Kovalev
 */
public class BrainfuckVirtualMachine {
    private final char[] memory;
    private final StringBuilder out = new StringBuilder();
    private int pointer = 0;

    public BrainfuckVirtualMachine(int memorySize) {
        this.memory = new char[memorySize];
    }

    public void incrementPointer() {
        if (pointer == memory.length - 1) pointer = 0;
        else pointer++;
    }

    public void decrementPointer() {
        if (pointer == 0) pointer = memory.length - 1;
        else pointer--;
    }

    public void incrementCurrentCell() {
        memory[pointer]++;
    }

    public void decrementCurrentCell() {
        memory[pointer]--;
    }

    public char getCurrentCell() {
        return memory[pointer];
    }

    public void printCurrentCell() {
        out.append(memory[pointer]);
    }

    public String getOut() {
        return out.toString();
    }

}
