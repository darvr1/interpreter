package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ReturnCode extends ByteCode{
    private String id;
    private int value;

    @Override
    public void execute(VirtualMachine vm) {
        int result = vm.peek();
        vm.popFrame();
        vm.setProgramCounter(vm.retrieveReturnAddress());
        value = vm.push(result);
    }

    @Override
    public void init(List<String> args) {
        if (args.size() > 1) {
            id = args.get(1);
        }
    }

    @Override
    public String toString() {
        String result = "RETURN";
        if (id != null) {
            // Get base id
            String baseId = this.id.substring(0, this.id.indexOf("<"));

            result += " " + this.id + "\t\tEXIT " + baseId + " : " + this.value;
        }
        return result;
    }
}
