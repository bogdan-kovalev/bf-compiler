package bvm;

/**
 * @author Bogdan Kovalev
 */
public class SimpleCommandsFactory {
    public static Command createCommand(char c) {
        switch (c) {
            case '>':
                return new IncrementPointerCommand();
            case '<':
                return new DecrementPointerCommand();
            case '+':
                return new IncrementCellCommand();
            case '-':
                return new DecrementCellCommand();
            case '.':
                return new PrintCellCommand();
            default:
                return null;
        }
    }
}
