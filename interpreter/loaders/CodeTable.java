/**
 * Code table of byte codes in language X
 * @key name of a specific byte code
 * @value name of the class that the key belongs to.
 */
package interpreter.loaders;

import java.util.HashMap;
import java.util.Map;

public final class CodeTable {
   private static final Map<String, String> table = new HashMap<>();
   private CodeTable() {
      // do nothing
   }

   /**
    * fill code table with class name mappings
    */
   public static void init() {
      table.put("ARGS", "ArgsCode");
      table.put("BOP", "BopCode");
      table.put("CALL", "CallCode");
      table.put("FALSEBRANCH", "FalseBranchCode");
      table.put("GOTO", "GotoCode");
      table.put("HALT", "HaltCode");
      table.put("LABEL", "LabelCode");
      table.put("LIT", "LitCode");
      table.put("LOAD", "LoadCode");
      table.put("POP", "PopCode");
      table.put("READ", "ReadCode");
      table.put("RETURN", "ReturnCode");
      table.put("STORE", "StoreCode");
      table.put("VERBOSE", "VerboseCode");
      table.put("WRITE", "WriteCode");
   }

   /**
    * Returns the ByteCode class name for a given token.
    * 
    * @param token bytecode to map. For example, HALT --> HaltCode
    * @return class name of bytecode
    */
   public static String getClassName(String token) {
      return table.get(token);
   }

}
