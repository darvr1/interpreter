package interpreter.bytecodes;

public interface Branchable extends ByteCode {
    void setAddress(int address);
    String getLabel();
}