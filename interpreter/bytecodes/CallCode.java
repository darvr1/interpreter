package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class CallCode extends ByteCodeJump {
    private String label;
    private String args;
    private int address;

    @Override
    public void execute(VirtualMachine vm) {
        args = vm.getArgs();
        vm.storeReturnAddress();
        vm.setProgramCounter(address);
    }

    @Override
    public void init(List<String> args) {
        this.label = args.get(1);
    }

    @Override
    public String toString() {
        String result = "CALL " + this.label;

        // Get base id
        String baseId = this.label;
        int a = this.label.indexOf("<");
        if (a != -1) {
            baseId = this.label.substring(0, this.label.indexOf("<"));
        }

        result += "\t" + baseId + "(" + args.replaceAll(" ", "") + ")";
        return result;
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