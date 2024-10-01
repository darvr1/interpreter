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

    /**
     * Calls the runtime stack's push method.
     * @param value The value to push.
     * @return      The pushed value.
     */
    public int push(int value) {
        return this.runTimeStack.push(value);
    }

    /**
     * Calls the runtime stack's pop method.
     * @return The popped value.
     */
    public int pop() {
        return this.runTimeStack.pop();
    }

    /**
     * Calls the runtime stack's peek method.
     * @return The top of the stack.
     */
    public int peek() {
        return this.runTimeStack.peek();
    }

    /**
     * Calls the runtime stack's getCurrentFrameSize method.
     * @return The size of the current frame.
     */
    public int getCurrentFrameSize() {
        return this.runTimeStack.getCurrentFrameSize();
    }

    public void terminate() {
        isRunning = false;
    }

    /**
     * Calls the runtime stack's store method.
     * @param offset The number of slots above current frame marker.
     * @return       The stored value.
     */
    public int store(int offset) {
        return this.runTimeStack.store(offset);
    }

    /**
     * Calls the runtime stack's load method.
     * @param offset The number of slots above current frame marker.
     * @return       The item just loaded into the offset.
     */
    public int load(int offset) {
        return this.runTimeStack.load(offset);
    }

    /**
     * Calls the runtime stack's newFrameAt method.
     * @param offset slots down from the top of the runtime stack.
     */
    public void newFrameAt(int offset) {
        this.runTimeStack.newFrameAt(offset);
    }

    /**
     * Calls the runtime stack's popFrame method.
     */
    public void popFrame() {
        this.runTimeStack.popFrame();
    }

    /**
     * Sets the program counter to a specific number.
     * @param programCounter The number to set the program counter to.
     */
    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    /**
     * Stores the return address on the return address stack.
     */
    public void storeReturnAddress() {
        this.returnAddress.push(programCounter);
    }

    /**
     * Retrieves the top of the return address stack.
     * @return The value of the top-most value of RA stack.
     */
    public int retrieveReturnAddress() {
        return this.returnAddress.pop();
    }

    /**
     * Toggles the verbose state.
     * @param state The state of verbose (ON/OFF).
     */
    public void setVerbose(String state) {
        if (state.equals("ON")) {
            isVerbose = true;
        } else if (state.equals("OFF")) {
            isVerbose = false;
        }
    }

    /**
     * Gets the values of the current frame.
     * @return The values of the current frame.
     */
    public String getArgs() {
        String word = this.runTimeStack.verboseDisplay();
        int start = word.lastIndexOf("[");
        // Exclude brackets
        return word.substring(start + 1, word.length() - 2);
    }
}