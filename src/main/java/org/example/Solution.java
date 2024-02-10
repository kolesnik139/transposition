package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class Solution {
    public void transposeMelody(String inputPath, int offset, String outputPath)
            throws NoteOutOfRangeException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode inputJson = objectMapper.readTree(new File(inputPath));
        Note[] inputMelody = jsonToNotes(inputJson);

        Note[] outputMelody = new TransposeService().transposeMelody(inputMelody, offset); // transpose

        JsonNode outputJson = notesToJson(outputMelody);
        objectMapper.writeValue(new File(outputPath), outputJson);
    }

    public Note[] jsonToNotes(JsonNode jsonNode) {
        Note[] notes = new Note[jsonNode.size()];
        for (int i = 0; i < jsonNode.size(); i++) {
            JsonNode arrayNode = jsonNode.get(i);
            int octave = arrayNode.get(0).asInt();
            int note = arrayNode.get(1).asInt();
            notes[i] = new Note(octave, note);
        }
        return notes;
    }

    public JsonNode notesToJson(Note[] melody){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode rootNode = objectMapper.createArrayNode();
        for(int i=0; i<melody.length; i++){
            ArrayNode arrayNode = objectMapper.createArrayNode();
            arrayNode.add(melody[i].getOctave());
            arrayNode.add(melody[i].getNote());
            rootNode.add(arrayNode);
        }
        return rootNode;
    }
}
