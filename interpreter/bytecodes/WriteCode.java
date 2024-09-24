package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class WriteCode extends ByteCode{
    @Override
    public void execute(VirtualMachine vm) {
        System.out.println(vm.peek());
    }

    @Override
    public void init(List<String> args) {
        // Do nothing
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}
