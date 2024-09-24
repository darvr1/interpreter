package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LoadCode extends ByteCode{
    private int offset;
    private String id;

    @Override
    public void execute(VirtualMachine vm) {
        vm.load(offset);
    }

    @Override
    public void init(List<String> args) {
        this.offset  = Integer.parseInt(args.get(1));
        if (args.size() > 2) this.id = args.get(2);
    }

    @Override
    public String toString() {
        String result = "LOAD " + this.offset;
        if (this.id != null)  {
            result += " " + this.id + "\t\t<load " + this.id + ">";
        }
        return result;
    }
}