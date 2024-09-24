package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.List;

public class StoreCode extends ByteCode{
    private int offset;
    private String id;
    private int valuePopped;

    @Override
    public void execute(VirtualMachine vm) {
        valuePopped = vm.store(offset);
    }

    @Override
    public void init(List<String> args) {
        this.offset = Integer.parseInt(args.get(1));
        if (args.size() > 2) this.id = args.get(2);
    }

    @Override
    public String toString() {
        String result = "STORE " + this.offset;
        if (this.id != null)  {
            result += " " + this.id + "\t\t" + this.id + "=" + this.valuePopped;
        }
        return result;
    }
}
