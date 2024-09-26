package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class BopCode extends ByteCode{
    private String operator;
    @Override
    public void execute(VirtualMachine vm) {
        int value2 = vm.pop();
        int value1 = vm.pop();
        int result = 0;

        switch (operator) {
            case "+":
                result = value1 + value2;
                break;
            case "-":
                result = value1 - value2;
                break;
            case "*":
                result = value1 * value2;
                break;
            case "/":
                result = value1 / value2;
                break;
            case "!=":
                result = (value1 != value2) ? 1 : 0;
                break;
            case "==":
                result = (value1 == value2) ? 1 : 0;
                break;
            case "<":
                result = (value1 < value2) ? 1 : 0;
                break;
            case "<=":
                result = (value1 <= value2) ? 1 : 0;
                break;
            case ">":
                result = (value1 > value2) ? 1 : 0;
                break;
            case ">=":
                result = (value1 >= value2) ? 1 : 0;
                break;
            case "|":
                result = (value1 != 0 || value2 != 0) ? 1 : 0;
                break;
            case "&":
                result = (value1 != 0 && value2 != 0) ? 1 : 0;
                break;
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }

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
