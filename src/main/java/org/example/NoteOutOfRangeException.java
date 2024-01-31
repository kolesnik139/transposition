package org.example;

public class NoteOutOfRangeException extends Exception{
    public NoteOutOfRangeException() {
        super("Note is uot of range");
    }
}
