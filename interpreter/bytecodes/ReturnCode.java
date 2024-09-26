package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ReturnCode extends ByteCode{
    private String label;
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
            label = args.get(1);
        }
    }

    @Override
    public String toString() {
        String result = "RETURN";
        if (label != null) {
            // Get base id
            String baseId = this.label.substring(0, this.label.indexOf("<"));

            result += " " + this.label + "\tEXIT " + baseId + " : " + this.value;
        }
        return result;
    }
}
