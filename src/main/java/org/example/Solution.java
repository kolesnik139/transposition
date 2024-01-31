package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Solution {
    public void transposeMelody(String inputPath, int offset, String outputPath) throws NoteOutOfRangeException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        int[][] jsonData = objectMapper.readValue(new File(inputPath), int[][].class); // read from input file

        new TransposeHelper().transposeArray(jsonData, offset); // transpose

        objectMapper.writeValue(new File(outputPath), jsonData); // write to output file
    }


}
