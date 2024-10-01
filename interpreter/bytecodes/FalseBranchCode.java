package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class FalseBranchCode implements Branchable {
    private String label;
    private int address;

    @Override
    public void execute(VirtualMachine vm) {
        if (vm.pop() == 0) vm.setProgramCounter(address);
    }

    @Override
    public void init(List<String> args) {
        this.label = args.get(1);
    }

    @Override
    public String toString() {
        return "FALSEBRANCH " + this.label;
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