package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.List;

public abstract class ByteCode {
    public abstract void execute(VirtualMachine vm);
    public abstract void init(List<String> args);
    public abstract String toString();
}
