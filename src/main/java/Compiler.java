import bvm.Command;

import java.util.List;

/**
 * @author Bogdan Kovalev
 */
public interface Compiler {
    List<Command> compile(String code);
}
