package org.example;

public class NoteOutOfRangeException extends RuntimeException{
    public NoteOutOfRangeException() {
        super("Note is uot of range");
    }
}
