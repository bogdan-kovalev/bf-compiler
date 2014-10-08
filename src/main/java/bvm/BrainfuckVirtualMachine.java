package bvm;

/**
 * Created by B on 07.10.2014.
 */
public class BrainfuckVirtualMachine {
    private char[] memory;
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
        System.out.print(getCurrentCell());
    }

    public int getMemorySize() {
        return memory.length;
    }

    public void setCurrentMemoryCell(char value) {
        memory[pointer] = value;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }
}
