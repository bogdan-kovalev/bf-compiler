import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Bogdan Kovalev
 */
public class Tests {
    @Test
    public void testInterpreter() {
        String code = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++." +
                ".+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";

        String expected = "Hello World!\n";
        String actual = new BrainfuckInterpreter().interpret(code);

        assertEquals(expected, actual);
    }

    @Test
    public void testCompiler() {
        String code = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++." +
                ".+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";

        String expected = "Hello World!\n";
        String actual = new CommandsExecuter().run(new BrainfuckCompiler().compile(code));

        assertEquals(expected, actual);
    }

    @Test
    public void testLoopsOnCompiler() {
        String code = "++++++++[>+++++++[>+<-]<-]>>.";

        String expected = String.valueOf((char)56);
        String actual = new CommandsExecuter().run(new BrainfuckCompiler().compile(code));

        assertEquals(expected, actual);
    }
}
