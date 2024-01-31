package org.example;

public class TransposeHelper {
    private final int MIN = -27;
    private final int MAX = 60;

    public void transposeArray(int[][] array, int offset) throws NoteOutOfRangeException {
        for (int i=0; i<array.length; i++) {
            int n = noteToNumber(array[i]);
            n += offset;
            if((n < MIN) || (n > MAX)){
                throw new NoteOutOfRangeException();
            }
            array[i] = numberToNote(n);
        }
    }

    private int noteToNumber(int[] note){
        return note[0] * 12 + note[1] - 1;
    }

    private int[] numberToNote(int n){
        int[] result = new int[2];
        if(n>=0) {
            result[0] = n / 12;
            result[1] = (n % 12) + 1;
        }else{
            n++;
            result[0] = (n / 12) -1;
            result[1] = 12 + (n % 12);
        }
        return result;
    }
}
