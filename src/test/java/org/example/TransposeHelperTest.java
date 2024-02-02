package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransposeHelperTest {

    @Test
    public void testTransposeArrayTaskExample() throws NoteOutOfRangeException {
        int[][] array = {{2,1},{2,6},{2,1},{2,8},{2,1},{2,9},{2,1},{2,6},{2,1},{2,8},{2,1},{2,9},{2,1},{2,11},{2,1},{2,8},{2,1},{2,9},{2,1},{2,
                11},{2,1},{3,1},{2,1},{2,9},{2,1},{2,11},{2,1},{3,1},{2,1},{3,2},{2,1},{2,11},{2,1},{3,1},{2,1},{2,9},{2,1},{2,11},{2,
                1},{2,8},{2,1},{2,9},{2,1},{2,6},{2,1},{2,8},{2,1},{2,5},{2,1},{2,6},{2,1},{2,1},{2,1},{2,2},{2,1},{1,11},{2,1},{2,1},{
                2,1},{1,9},{2,1},{1,11},{2,1},{1,8},{2,1},{1,9},{2,1},{1,6},{2,1},{1,11},{2,1},{1,8},{2,1},{1,9},{2,1},{1,6},{2,1},{1,
                8},{2,1},{1,5},{2,1},{1,6}};
        int offset = -3;

        TransposeHelper transposeHelper = new TransposeHelper();
        transposeHelper.transposeArray(array, offset);

        int[][] expectedArray = {{1,10},{2,3},{1,10},{2,5},{1,10},{2,6},{1,10},{2,3},{1,10},{2,5},{1,10},{2,6},{1,10},{2,8},{1,10},{2,5},{1,10},{2,
                6},{1,10},{2,8},{1,10},{2,10},{1,10},{2,6},{1,10},{2,8},{1,10},{2,10},{1,10},{2,11},{1,10},{2,8},{1,10},{2,10},{1,
                10},{2,6},{1,10},{2,8},{1,10},{2,5},{1,10},{2,6},{1,10},{2,3},{1,10},{2,5},{1,10},{2,2},{1,10},{2,3},{1,10},{1,10},
                {1,10},{1,11},{1,10},{1,8},{1,10},{1,10},{1,10},{1,6},{1,10},{1,8},{1,10},{1,5},{1,10},{1,6},{1,10},{1,3},{1,10},{1
                ,8},{1,10},{1,5},{1,10},{1,6},{1,10},{1,3},{1,10},{1,5},{1,10},{1,2},{1,10},{1,3}};
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testTransposeArrayPositiveFiveOffset() throws NoteOutOfRangeException {
        int[][] array = {{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12}};
        int offset = +5;

        TransposeHelper transposeHelper = new TransposeHelper();
        transposeHelper.transposeArray(array, offset);

        int[][] expectedArray = {{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,1},{1,2},{1,3},{1,4},{1,5}};
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testTransposeArrayNegativeFiveOffset() throws NoteOutOfRangeException {
        int[][] array = {{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12}};
        int offset = -5;

        TransposeHelper transposeHelper = new TransposeHelper();
        transposeHelper.transposeArray(array, offset);

        int[][] expectedArray = {{-1,8},{-1,9},{-1,10},{-1,11},{-1,12},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7}};
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testTransposeArrayPositiveSixtyFourOffset() throws NoteOutOfRangeException {
        int[][] array = {{-2,1},{-2,2},{-2,3},{-2,4},{-2,5},{-2,6},{-2,7},{-2,8},{-2,9},{-2,10},{-2,11},{-2,12}};
        int offset = +64;

        TransposeHelper transposeHelper = new TransposeHelper();
        transposeHelper.transposeArray(array, offset);

        int[][] expectedArray = {{3,5},{3,6},{3,7},{3,8},{3,9},{3,10},{3,11},{3,12},{4,1},{4,2},{4,3},{4,4}};
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testTransposeArrayNegativeSixtySevenOffset() throws NoteOutOfRangeException {
        int[][] array = {{4,1},{4,2},{4,3},{4,4},{4,5},{4,6},{4,7},{4,8},{4,9},{4,10},{4,11},{4,12}};
        int offset = -67;

        TransposeHelper transposeHelper = new TransposeHelper();
        transposeHelper.transposeArray(array, offset);

        int[][] expectedArray = {{-2,6},{-2,7},{-2,8},{-2,9},{-2,10},{-2,11},{-2,12},{-1,1},{-1,2},{-1,3},{-1,4},{-1,5}};
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testTransposeArrayThresholds() throws NoteOutOfRangeException {
        int[][] array = {{-3,10},{5,1}};
        int offset = 0;

        TransposeHelper transposeHelper = new TransposeHelper();
        transposeHelper.transposeArray(array, offset);

        int[][] expectedArray = {{-3,10},{5,1}};
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testTransposeArrayOutOfMinimumRangeByNote() {
        int[][] array = {{-3,10},{5,1}};
        int offset = -1;

        TransposeHelper transposeHelper = new TransposeHelper();

        assertThrows(NoteOutOfRangeException.class, () -> transposeHelper.transposeArray(array, offset));
    }

    @Test
    public void testTransposeArrayOutOfMinimumRangeByOctave() {
        int[][] array = {{-3,10},{5,1}};
        int offset = -13;

        TransposeHelper transposeHelper = new TransposeHelper();

        assertThrows(NoteOutOfRangeException.class, () -> transposeHelper.transposeArray(array, offset));
    }

    @Test
    public void testTransposeArrayOutOfMaximumRangeByNote() {
        int[][] array = {{-3,10},{5,1}};
        int offset = +1;

        TransposeHelper transposeHelper = new TransposeHelper();

        assertThrows(NoteOutOfRangeException.class, () -> transposeHelper.transposeArray(array, offset));
    }

    @Test
    public void testTransposeArrayOutOfMaximumRangeByOctave() {
        int[][] array = {{-3,10},{5,1}};
        int offset = +13;

        TransposeHelper transposeHelper = new TransposeHelper();

        assertThrows(NoteOutOfRangeException.class, () -> transposeHelper.transposeArray(array, offset));
    }
}
