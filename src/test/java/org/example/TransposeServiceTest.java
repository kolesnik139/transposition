package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TransposeServiceTest {

    ObjectMapper objectMapper = new ObjectMapper();
    private TransposeService transposeService = new TransposeService();
    private Solution solution = new Solution();

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testTransposeMelody(String melodyStr, int offset, String expectedMelodyStr)
            throws NoteOutOfRangeException, JsonProcessingException {
        JsonNode melodyJson = objectMapper.readTree(melodyStr);
        Note[] melody = solution.jsonToNotes(melodyJson);

        JsonNode expectedMelodyJson = objectMapper.readTree(expectedMelodyStr);
        Note[] expectedMelody = solution.jsonToNotes(expectedMelodyJson);

        Note[] resultMelody = transposeService.transposeMelody(melody, offset);

        assertArrayEquals(expectedMelody, resultMelody);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("[[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,9]," +
                        "[2,1],[2,11],[2,1],[2,8],[2,1],[2,9],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11]," +
                        "[2,1],[3,1],[2,1],[3,2],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,1],[2,8],[2,1]," +
                        "[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,5],[2,1],[2,6],[2,1],[2,1],[2,1],[2,2],[2,1],[1,11]," +
                        "[2,1],[2,1],[2,1],[1,9],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,11],[2,1]," +
                        "[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,8],[2,1],[1,5],[2,1],[1,6]]",
                        -3,
                        "[[1,10],[2,3],[1,10],[2,5],[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,6],[1,10],[2,8]," +
                        "[1,10],[2,5],[1,10],[2,6],[1,10],[2,8],[1,10],[2,10],[1,10],[2,6],[1,10],[2,8],[1,10]," +
                        "[2,10],[1,10],[2,11],[1,10],[2,8],[1,10],[2,10],[1,10],[2,6],[1,10],[2,8],[1,10],[2,5]," +
                        "[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,2],[1,10],[2,3],[1,10],[1,10],[1,10]," +
                        "[1,11],[1,10],[1,8],[1,10],[1,10],[1,10],[1,6],[1,10],[1,8],[1,10],[1,5],[1,10],[1,6]," +
                        "[1,10],[1,3],[1,10],[1,8],[1,10],[1,5],[1,10],[1,6],[1,10],[1,3],[1,10],[1,5],[1,10],[1,2]," +
                        "[1,10],[1,3]]"
                ),
                Arguments.of("[[0,1],[0,2],[0,3],[0,4],[0,5],[0,6],[0,7],[0,8],[0,9],[0,10],[0,11],[0,12]]",
                        +5,
                        "[[0,6],[0,7],[0,8],[0,9],[0,10],[0,11],[0,12],[1,1],[1,2],[1,3],[1,4],[1,5]]"
                ),
                Arguments.of("[[0,1],[0,2],[0,3],[0,4],[0,5],[0,6],[0,7],[0,8],[0,9],[0,10],[0,11],[0,12]]",
                        -5,
                        "[[-1,8],[-1,9],[-1,10],[-1,11],[-1,12],[0,1],[0,2],[0,3],[0,4],[0,5],[0,6],[0,7]]"
                ),
                Arguments.of("[[-2,1],[-2,2],[-2,3],[-2,4],[-2,5],[-2,6],[-2,7],[-2,8],[-2,9],[-2,10]," +
                        "[-2,11],[-2,12]]",
                        +64,
                        "[[3,5],[3,6],[3,7],[3,8],[3,9],[3,10],[3,11],[3,12],[4,1],[4,2],[4,3],[4,4]]"
                ),
                Arguments.of("[[4,1],[4,2],[4,3],[4,4],[4,5],[4,6],[4,7],[4,8],[4,9],[4,10],[4,11],[4,12]]",
                        -67,
                        "[[-2,6],[-2,7],[-2,8],[-2,9],[-2,10],[-2,11],[-2,12],[-1,1],[-1,2],[-1,3],[-1,4],[-1,5]]"
                ),
                Arguments.of("[[-3,10],[5,1]]",
                        0,
                        "[[-3,10],[5,1]]"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCasesWithException")
    public void testTransposeMelodyWithException(String melodyStr, int offset) throws JsonProcessingException {
        JsonNode melodyJson = objectMapper.readTree(melodyStr);
        Note[] melody = solution.jsonToNotes(melodyJson);

        assertThrows(NoteOutOfRangeException.class, () -> transposeService.transposeMelody(melody, offset));
    }

    private static Stream<Arguments> provideTestCasesWithException() {
        return Stream.of(
                Arguments.of("[[-3,10],[5,1]]", -1),
                Arguments.of("[[-3,10],[5,1]]", -13),
                Arguments.of("[[-3,10],[5,1]]", +1),
                Arguments.of("[[-3,10],[5,1]]", +13)
        );
    }

}
