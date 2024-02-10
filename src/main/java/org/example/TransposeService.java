package org.example;

public class TransposeService {
    private final int MIN_NOTE_NUMBER = -27;
    private final int MAX_NOTE_NUMBER = 60;

    public Note[] transposeMelody(Note[] inputMelody, int offset) throws NoteOutOfRangeException {
        Note[] outputMelody = new Note[inputMelody.length];
        for (int i=0; i<inputMelody.length; i++) {
            int n = noteToNumber(inputMelody[i]);
            n += offset; //transpose
            if((n < MIN_NOTE_NUMBER) || (n > MAX_NOTE_NUMBER)){
                throw new NoteOutOfRangeException();
            }
            outputMelody[i] = numberToNote(n);
        }
        return outputMelody;
    }

    private int noteToNumber(Note note){
        return note.getOctave() * 12 + note.getNote() - 1;
    }

    private Note numberToNote(int n){
        Note result = new Note();
        if(n>=0) {
            result.setOctave(n / 12);
            result.setNote((n % 12) + 1);
        }else{
            n++;
            result.setOctave((n / 12) - 1);
            result.setNote(12 + (n % 12));
        }
        return result;
    }
}
