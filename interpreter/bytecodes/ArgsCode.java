package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ArgsCode implements ByteCode {
    private int numOfArgs;

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrameAt(numOfArgs);
    }

    @Override
    public void init(List<String> args) {
        this.numOfArgs = Integer.parseInt(args.get(1));
    }

    @Override
    public String toString() {
        return "ARGS " + numOfArgs;
    }
}