package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class PopCode extends ByteCode{
    private int popCount;

    @Override
    public void execute(VirtualMachine vm) {
        if (popCount > 0) {
            vm.pop(popCount);
        } else {
            vm.pop();
        }
    }

    @Override
    public void init(List<String> args) {
        popCount = Integer.parseInt(args.get(1));
    }

    @Override
    public String toString() {
        return "POP " + popCount;
    }
}
