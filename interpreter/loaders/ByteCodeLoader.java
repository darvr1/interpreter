package interpreter.loaders;

import interpreter.bytecodes.ByteCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class ByteCodeLoader {
    private String codSourceFileName;
    
    /**
     * Constructs ByteCodeLoader object given a COD source code
     * file name
     * @param fileName name of .cod File to load.
     */
    public ByteCodeLoader(String fileName){
        this.codSourceFileName = fileName;
    }
    
    /**
     * Loads a program from a .cod file.
     * @return a constructed Program Object.
     * @throws InvalidProgramException thrown when 
     * loadCodes fails.
     */
    public Program loadCodes() throws InvalidProgramException {
        Program program = new Program();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.codSourceFileName))) {
            while (reader.ready()) {
                String[] items = reader.readLine().split("\\s+");

                // Load class blueprint
                String className = CodeTable.getClassName(items[0]);
                ByteCode bc = (ByteCode) Class
                        .forName("interpreter.bytecodes." + className)
                        .getDeclaredConstructor()
                        .newInstance();

                bc.init(Arrays.asList(items));
                program.addCode(bc);
            }
        } catch(IOException | ClassNotFoundException | InvocationTargetException | InstantiationException |
                IllegalAccessException | NoSuchMethodException ex) {
            throw new InvalidProgramException(ex);
        }

        return program;
    }
}
