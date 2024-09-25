package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class VerboseCode extends ByteCode{
    private String state;

    @Override
    public void execute(VirtualMachine vm) {
        vm.setVerbose(state);
    }

    @Override
    public void init(List<String> args) {
        this.state = args.get(1);
    }

    @Override
    public String toString() {
        return "VERBOSE " + this.state;
    }
}
