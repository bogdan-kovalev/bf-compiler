import bvm.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Bogdan Kovalev
 */
public class Tests {
    @Test
    public void testInterpreter() {
        String testCode = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++." +
                ".+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";

        String expected = "Hello World!\n";
        String actual = new BrainfuckInterpreter().interpret(testCode);

        assertEquals(expected, actual);
    }

    @Test
    public void testExecuter() {
        List<Command> testList = new ArrayList<>();
        testList.add(new IncrementPointerCommand());
        testList.add(new DecrementPointerCommand());
        testList.add(new IncrementCellCommand());
        testList.add(new PrintCellCommand());
        testList.add(new DecrementCellCommand());
        testList.add(new Cycle());

        String expected = String.valueOf((char)1);
        String actual = new CommandsExecuter().run(testList);

        assertEquals(expected, actual);
    }

    @Test
    public void testCompiler() {
        String testCode = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++." +
                ".+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";

        String expected = "Hello World!\n";
        String actual = new CommandsExecuter().run(new BrainfuckCompiler().compile(testCode));

        assertEquals(expected, actual);
    }

    @Test
    public void testLoopsOnCompiler() {
        String testCode = "++++++++[>+++++++[>+<-]<-]>>.";

        String expected = String.valueOf((char)56);
        String actual = new CommandsExecuter().run(new BrainfuckCompiler().compile(testCode));

        assertEquals(expected, actual);
    }
}
