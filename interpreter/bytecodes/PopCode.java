package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class PopCode extends ByteCode{
    private int popCount;

    @Override
    public void execute(VirtualMachine vm) {
        /*
         * ToDo: IS IT RTS SIZE?????
         */
        int currentFrame = vm.rtsSize();
        int maxPop = Math.min(popCount, currentFrame);
        for (int i = 0; i < maxPop; i++) {
            vm.pop();
        }
    }

    @Override
    public void init(List<String> args) {
        popCount = Integer.parseInt(args.get(1));
    }

    @Override
    public String toString() {
        return "POP " + popCount;
    }
}
