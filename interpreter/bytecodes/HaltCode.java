package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class HaltCode extends ByteCode{
    @Override
    public void execute(VirtualMachine vm) {
        vm.terminate();
    }

    @Override
    public void init(List<String> args) {
        // Do nothing
    }

    @Override
    public String toString() {
        return null;
    }
}
