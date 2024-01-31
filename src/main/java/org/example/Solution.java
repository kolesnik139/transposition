package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public void handleJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter input JSON file:");
        String inputFileName = scanner.nextLine();

        int[][] jsonData = null;
        try {
            jsonData = objectMapper.readValue(new File(inputFileName), int[][].class);
        }catch (IOException e){
            System.out.println("Error: Cannot read the input file");
            return;
        }

        System.out.println("Enter number of semitones to transpose:");
        String offsetStr = scanner.nextLine();
        Integer offset = 0;
        try {
            offset = Integer.parseInt(offsetStr);
        }catch (NumberFormatException e){
            System.out.println("Error: Wrong number format");
            return;
        }

        try {
            transposeArray(jsonData, offset); // main logic execution
        }catch (NoteOutOfRangeException e){
            System.out.println("Error: Note out of range");
            return;
        }

        System.out.println("Enter output JSON file:");
        String outputFileName = scanner.nextLine();

        try{
        objectMapper.writeValue(new File(outputFileName), jsonData);
        }catch (IOException e){
            System.out.println("Error: Cannot write to output file");
            return;
        }
        System.out.println("Transposition completed successfully!");
    }

    public void transposeArray(int[][] array, int offset){
        for (int[] data : array) {
            int note = data[1]-1;
            note += offset;
            int octaveOffset = 0;
            if(note >= 0) {
                octaveOffset = note / 12;
                note %= 12;
                note ++;
            }else{
                note ++;
                octaveOffset = (note / 12) -1;
                note %= 12;
                note += 12;
            }
            int octave = data[0]+octaveOffset;

            if((octave < -3) || (octave == -3 && note < 10) ||
                    (octave > 5) || (octave == 5 && note > 1)){
                throw new NoteOutOfRangeException();
            }
            data[0] = octave;
            data[1] = note;
        }
    }
}
