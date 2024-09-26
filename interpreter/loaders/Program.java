package interpreter.loaders;

import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.ByteCodeJump;
import interpreter.bytecodes.LabelCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {

    private List<ByteCode> program;

    /**
     * Instantiates a program object using an
     * ArrayList
     */
    public Program() {
        this.program = new ArrayList<>();
    }

    /**
     * Gets the size of the current program.
     * @return size of program
     */
    public int getSize() {
        return this.program.size();
    }

    /**
     * Grabs an instance of a bytecode at an index.
     * @param programCounter index of bytecode to get.
     * @return a bytecode.
     */
    public ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    /**
     * Adds a bytecode instance to the Program List.
     * @param c bytecode to be added
     */
    public void addCode(ByteCode c) {
        this.program.add(c);
    }

    /**
     * Makes multiple passes through the program ArrayList
     * resolving addrss for Goto,Call and FalseBranch
     * bytecodes. These bytecodes can only jump to Label
     * codes that have a matching label value.
     * HINT: make note of what type of data-structure
     * ByteCodes are stored in.
     * **** METHOD SIGNATURE CANNOT BE CHANGED *****
     */
    public void resolveAddress() {
    Map<String, Integer> jumpMap = new HashMap<>();
        // Map all labels
        for (int i = 0 ; i < this.program.size() ; i++) {
            ByteCode code = this.program.get(i);
            if (code instanceof LabelCode label) {
                jumpMap.put(label.getLabel(), i);
            }
        }

        // Set address for each jumper byte codes
        for (ByteCode code : this.program) {
            if (code instanceof ByteCodeJump jumper) {
                jumper.setAddress(jumpMap.get(jumper.getLabel()));
            }
        }
    }
}   