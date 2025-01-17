/*
 * This interface contains the ANSI color codes for the text colors of the code
 */

package OOP_Version.code;

public interface TextProperties{
    public final String WARN = "\u001B[31m"; // Warning color red
    public final String GOOD = "\u001B[32m"; // success color green
    public final String NORMAL = "\u001B[33m"; // Normal color yellow
    public final String WHITE = "\u001B[37m"; // Default color 
}
