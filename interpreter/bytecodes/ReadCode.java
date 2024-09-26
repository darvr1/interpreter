package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ReadCode extends ByteCode{
    @Override
    public void execute(VirtualMachine vm) {
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            System.out.print("Please enter an integer: ");
            try {
                input = scanner.nextInt();
                vm.push(input);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.next();
            }
        }

        scanner.close();
    }

    @Override
    public void init(List<String> args) {
        // Do nothing
    }

    @Override
    public String toString() {
        return "READ";
    }
}