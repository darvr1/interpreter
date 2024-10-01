package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.List;

public interface ByteCode {
    void execute(VirtualMachine vm);
    void init(List<String> args);
    String toString();
}