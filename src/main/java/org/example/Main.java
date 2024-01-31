package org.example;

import java.io.IOException;

public class Main {
    // Parameters should be passed: <input file path> <offset> <output file path>
    public static void main(String[] args) {
        Solution s = new Solution();
        try {
            s.transposeMelody(args[0], Integer.parseInt(args[1]), args[2]); // main logic execution
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Not all parameters are specified");
            System.exit(2);
        }catch (NumberFormatException e){
            System.out.println("Error: Wrong offset format");
            System.exit(2);
        }catch (NoteOutOfRangeException e) {
            System.out.println("Error: Note out of range");
            System.exit(1);
        }catch (IOException e) {
            System.out.println("Error: File operation error");
            System.exit(1);
        }
    }
}