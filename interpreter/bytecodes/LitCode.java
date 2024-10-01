package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LitCode implements ByteCode {
    private int value;
    private String id;

    @Override
    public void execute(VirtualMachine vm) {
        vm.push(this.value);
    }

    @Override
    public void init(List<String> args) {
        this.value  = Integer.parseInt(args.get(1));
        if (args.size() > 2) this.id = args.get(2);
    }

    @Override
    public String toString() {
        String result = "LIT " + this.value;
        if (this.id != null) result += "\tint " + this.id;
        return result;
    }
}