package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LabelCode extends ByteCode{
    private String label;

    @Override
    public void execute(VirtualMachine vm) {
        // Do Nothing
    }

    @Override
    public void init(List<String> args) {
        this.label = args.get(1);
    }

    @Override
    public String toString() {
        return "LABEL " + this.label;
    }

    public String getLabel() {
        return this.label;
    }
}
