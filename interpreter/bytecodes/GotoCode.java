package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class GotoCode extends ByteCodeJump {
    private String label;
    private int address;

    @Override
    public void execute(VirtualMachine vm) {
        vm.setProgramCounter(address);
    }

    @Override
    public void init(List<String> args) {
        this.label = args.get(1);
    }

    @Override
    public String toString() {
        return "GOTO " + this.label;
    }

    @Override
    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}