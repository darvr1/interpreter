package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ReturnCode implements ByteCode {
    private String label;
    private int value;

    @Override
    public void execute(VirtualMachine vm) {
        // Todo: refactor result to value
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
            String baseId = this.label;
            int indexOfBracket = this.label.indexOf("<");
            if (indexOfBracket != -1) {
                baseId = this.label.substring(0, indexOfBracket);
            }

            // Double space before EXIT instead of \t to match sample output
            result += " " + this.label + "  EXIT " + baseId + " : " + this.value;
        }
        return result;
    }
}