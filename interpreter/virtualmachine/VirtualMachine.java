package interpreter.virtualmachine;

import java.util.Stack;

import interpreter.bytecodes.HaltCode;
import interpreter.loaders.Program;
import interpreter.bytecodes.ByteCode;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        isVerbose;

    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
    }

    public void executeProgram() {
        isRunning = true;

        while(isRunning) {
            ByteCode code = program.getCode(programCounter);
            code.execute(this);

            if (isVerbose && !(code instanceof HaltCode)) {
                System.out.println(code);
                System.out.println(runTimeStack.verboseDisplay());
            }

            programCounter++;
        }
    }

    public int push(int value) {
        return this.runTimeStack.push(value);
    }

    public int pop() {
        return this.runTimeStack.pop();
    }

    public int peek() {
        return this.runTimeStack.peek();
    }

    public int getCurrentFrameSize() {
        return this.runTimeStack.getCurrentFrameSize();
    }

    public void terminate() {
        isRunning = false;
    }

    /**
     *
     * @param offset
     * @return
     */
    public int store(int offset) {
        return this.runTimeStack.store(offset);
    }

    /**
     *
     * @param offset
     * @return
     */
    public int load(int offset) {
        return this.runTimeStack.load(offset);
    }

    /**
     *
     * @param offset
     */
    public void newFrameAt(int offset) {
        this.runTimeStack.newFrameAt(offset);
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public void storeReturnAddress() {
        this.returnAddress.push(programCounter);
    }

    public int retrieveReturnAddress() {
        return this.returnAddress.pop();
    }

    public void popFrame() {
        this.runTimeStack.popFrame();
    }

    public void setVerbose(String state) {
        if (state.equals("ON")) {
            isVerbose = true;
        } else if (state.equals("OFF")) {
            isVerbose = false;
        }
    }

    public String getArgs() {
        String word = this.runTimeStack.verboseDisplay();
        int start = word.lastIndexOf("[");
        // Exclude brackets
        return word.substring(start + 1, word.length() - 2);
    }
}