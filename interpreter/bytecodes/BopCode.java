package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class BopCode implements ByteCode {
    private String operator;
    @Override
    public void execute(VirtualMachine vm) {
        int value2 = vm.pop();
        int value1 = vm.pop();
        int result = switch (operator) {
            case "+" -> value1 + value2;
            case "-" -> value1 - value2;
            case "*" -> value1 * value2;
            case "/" -> value1 / value2;
            case "!=" -> (value1 != value2) ? 1 : 0;
            case "==" -> (value1 == value2) ? 1 : 0;
            case "<" -> (value1 < value2) ? 1 : 0;
            case "<=" -> (value1 <= value2) ? 1 : 0;
            case ">" -> (value1 > value2) ? 1 : 0;
            case ">=" -> (value1 >= value2) ? 1 : 0;
            case "|" -> (value1 != 0 || value2 != 0) ? 1 : 0;
            case "&" -> (value1 != 0 && value2 != 0) ? 1 : 0;
            default -> throw new RuntimeException("Unknown operator: " + operator);
        };

        vm.push(result);
    }

    @Override
    public void init(List<String> args) {
        this.operator = args.get(1);
    }

    @Override
    public String toString() {
        return "BOP " + this.operator;
    }
}