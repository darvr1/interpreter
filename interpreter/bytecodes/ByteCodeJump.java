package interpreter.bytecodes;

public abstract class ByteCodeJump extends ByteCode {
    public abstract void setAddress(int address);
    public abstract String getLabel();
}
