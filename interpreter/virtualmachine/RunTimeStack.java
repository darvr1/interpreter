package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

class RunTimeStack {

    private List<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial frame pointer value, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    /**
     * Used for displaying the current state of the runTimeStack.
     * It will print portions of the stack based on respective
     * frame markers.
     * Example [1,2,3] [4,5,6] [7,8]
     * Frame pointers would be 0,3,6
     */
    public String verboseDisplay() {
        StringBuilder initialResult = new StringBuilder();
        List<Integer> framePointers = new ArrayList<>(framePointer);

        // Create sub-lists
        for (int i = 0; i < framePointers.size(); i++) {
            int start = framePointers.get(i);
            // The end of the sublist is the start of the next sublist (exclusive)
            // If it's the last sublist, the end is the size of RTS
            int end = (i < framePointers.size() - 1) ? framePointers.get(i + 1) : runTimeStack.size();
            initialResult.append(runTimeStack.subList(start, end));
        }

        // Clean up the format made by `subList()`
        String finalResult = initialResult.toString();
        // Remove spaces in sub-lists
        finalResult = finalResult.replaceAll(" ", "");
        // Separate each sub-lists by a space
        finalResult = finalResult.replaceAll("]", "] ");

        return finalResult;
    }

    /**
     * returns the top of the runtime stack, but does not remove
     * @return copy of the top of the stack.
     */
    public int peek() {
        return runTimeStack.getLast();
    }

     /**
     * push the value i to the top of the stack.
     * @param i value to be pushed.
     * @return value pushed
     */
    public int push(int i) {
        runTimeStack.add(i);
        return i;
    }

    /**
     * removes to the top of the runtime stack.
     * @return the value popped.
     */
    public int pop() {
        return runTimeStack.removeLast();
    }

     /**
     * Takes the top item of the run time stack, and stores
     * it into an offset starting from the current frame.
     * @param offsetInFrame number of slots above current frame marker
     * @return the item just stored
     */
    public int store(int offsetInFrame) {
        int item = this.pop();
        runTimeStack.set(offsetInFrame + framePointer.peek(), item);
        return item;
    }

    /**
     * Takes a value from the run time stack that is at offset
     * from the current frame marker and pushes it onto the top of the stack.
     * @param offsetInFrame number of slots above current frame marker
     * @return item just loaded into the offset
     */
    public int load(int offsetInFrame) {
        int index = offsetInFrame + framePointer.peek();
        return this.push(runTimeStack.get(index));
    }

    /**
     * create a new frame pointer at the index offset slots down
     * from the top of the runtime stack.
     * @param offsetFromTopOfRunStack slots down from the top of
     * the runtime stack
     */
    public void newFrameAt(int offsetFromTopOfRunStack) {
        framePointer.push(runTimeStack.size() - offsetFromTopOfRunStack);
    }

    /**
     * pop the current frame off the runtime stack. Also removes
     * the frame pointer value from the FramePointer Stack.
     */
    public void popFrame() {
        while (runTimeStack.size() > framePointer.peek()) {
            this.pop();
        }
        framePointer.pop();
    }
}