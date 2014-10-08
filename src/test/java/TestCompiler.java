import bvm.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by B on 08.10.2014.
 */
public class TestCompiler {

    @Test
    public void testCompile() {
        String test = "++[>+<-]>.";

        List<Command> expected = new LinkedList<>();
        expected.add(new IncrementCellCommand());
        expected.add(new IncrementCellCommand());
        expected.add(new IncrementPointerCommand());
        expected.add(new IncrementCellCommand());
        expected.add(new DecrementPointerCommand());
        expected.add(new DecrementCellCommand());
        expected.add(new IncrementPointerCommand());
        expected.add(new IncrementCellCommand());
        expected.add(new DecrementPointerCommand());
        expected.add(new DecrementCellCommand());
        expected.add(new IncrementPointerCommand());
        expected.add(new PrintCellCommand());

        BrainfuckCompiler compiler = new BrainfuckCompiler();
        List<Command> result = compiler.compile(test);

        for(int i = 0; i<expected.size(); i++)
            assertTrue(result.get(i).getClass() == expected.get(i).getClass());
    }

    @Test
    public void testOptimize() {
        String test = "+++---";

        List<Command> expected = new LinkedList<>();
        expected.add(new AddValueToCellCommand(3));
        expected.add(new AddValueToCellCommand(-3));

        BrainfuckCompiler compiler = new BrainfuckCompiler();
        List<Command> result = compiler.optimize(compiler.compile(test));

        for(int i = 0; i<expected.size(); i++)
            assertTrue(result.get(i).getClass() == expected.get(i).getClass());
    }
}
