package org.example;

import java.util.Objects;

public class Note {
    private int octave;
    private int note;

    public Note() {
    }

    public Note(int octave, int note) {
        this.octave = octave;
        this.note = note;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return octave == note1.octave && note == note1.note;
    }

    @Override
    public int hashCode() {
        return Objects.hash(octave, note);
    }
}
