package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.Before;
import org.junit.Test;

public class HuffmanTest {
    private Huffman testHuffman;
    private BufferedWriter writer;
    private String pathToTestDir = "src/test/java/pl/edu/pw/ee/testInput";
    private String pathToSamplesDir = "src/test/java/pl/edu/pw/ee/testInputSamples";
    private String nameOfDecompressedFile = "decompressedFile.txt";
    private String nameOfCompressedFile = "compressedFile.txt";
    private String nameOfFileForKey = "key.txt";
    private File decompressedFile;
    private File compressedFile;
    private File keyFile;

    @Before
    public void setUp() {
        testHuffman = new Huffman();
        validateInput(pathToTestDir);

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPathIsNull() {
        // given
        String pathToRootDir = null;
        Boolean compress = true;

        // when
        testHuffman.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPathIsEmpty() {
        // given
        String pathToRootDir = "";
        Boolean compress = true;

        // when
        testHuffman.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenPathLeadToFile() {
        // given
        String pathToRootDir = compressedFile.getPath();
        Boolean compress = true;

        // when
        testHuffman.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenCompressWithoutFile() {
        // given
        boolean compress = true;

        // when
        decompressedFile.delete();
        if (decompressedFile.exists()) {
            assert false;
        }
        testHuffman.huffman(pathToTestDir, compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenDecompressWithoutCompressedFile() {
        // given
        boolean compress = false;

        // when
        compressedFile.delete();
        if (compressedFile.exists()) {
            assert false;
        }
        testHuffman.huffman(pathToTestDir, compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenDecompressWithoutKeyFile() {
        // given
        boolean compress = false;

        // when
        keyFile.delete();
        if (keyFile.exists()) {
            assert false;
        }
        testHuffman.huffman(pathToTestDir, compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenTheCompressedCodeIsInvalid() {
        // given
        String text = "abc";

        // when
        prepareWriter(decompressedFile);
        writeString(text);
        testHuffman.huffman(pathToTestDir, true);
        prepareWriter(compressedFile);
        writeString("code");
        testHuffman.huffman(pathToTestDir, false);

        // then
        assert false;
    }

    @Test
    public void should_createRequiredFiles_toCompress() {
        // given
        boolean filesExist = false;
        boolean filesNotExist = false;

        // when
        compressedFile.delete();
        keyFile.delete();
        if (!compressedFile.exists() && !compressedFile.exists()) {
            filesNotExist = true;
        }
        testHuffman.huffman(pathToTestDir, true);
        if (compressedFile.exists() && compressedFile.exists()) {
            filesExist = true;
        }

        // then
        assertTrue(filesExist);
        assertTrue(filesNotExist);
    }

    @Test
    public void should_createRequiredFile_toDecompress() {
        // given
        boolean fileExist = false;
        boolean fileNotExist = false;

        // when
        decompressedFile.delete();
        if (!decompressedFile.exists()) {
            fileNotExist = true;
        }
        testHuffman.huffman(pathToTestDir, false);
        if (decompressedFile.exists()) {
            fileExist = true;
        }

        // then
        assertTrue(fileExist);
        assertTrue(fileNotExist);
    }

    @Test
    public void should_correctlyCompress_emptyFile() {
        // given
        boolean compress = true;
        int actualResult = testHuffman.huffman(pathToTestDir, compress);
        // when

        // then
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_correctlyDecompress_emptyFile() {
        // given
        boolean compress = false;
        int actualResult = testHuffman.huffman(pathToTestDir, compress);
        // when

        // then
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_correctlyCompressAndDecompress_oneLetter() {
        // given
        String text = "a";
        int actualResultOfCompress = 0;
        int actualResultOfDecompress = 0;

        // when
        prepareWriter(decompressedFile);
        writeString(text);
        actualResultOfCompress = testHuffman.huffman(pathToTestDir, true);
        actualResultOfDecompress = testHuffman.huffman(pathToTestDir, false);

        // then
        int expectedResultOfCompress = 1;
        int expectedResultOfDecompress = 1;
        assertEquals(expectedResultOfCompress, actualResultOfCompress);
        assertEquals(expectedResultOfDecompress, actualResultOfDecompress);
    }

    @Test
    public void should_correctlyCompressAndDecompress_twoEqualLetter() {
        // given
        String text = "aa";
        int actualResultOfCompress = 0;
        int actualResultOfDecompress = 0;

        // when
        prepareWriter(decompressedFile);
        writeString(text);
        actualResultOfCompress = testHuffman.huffman(pathToTestDir, true);
        actualResultOfDecompress = testHuffman.huffman(pathToTestDir, false);

        // then
        int expectedResultOfCompress = 2;
        int expectedResultOfDecompress = 2;
        assertEquals(expectedResultOfCompress, actualResultOfCompress);
        assertEquals(expectedResultOfDecompress, actualResultOfDecompress);
    }

    @Test
    public void should_correctlyCompressAndDecompress_twoDifferentLetter() {
        // given
        String text = "ab";
        int actualResultOfCompress = 0;
        int actualResultOfDecompress = 0;

        // when
        prepareWriter(decompressedFile);
        writeString(text);
        actualResultOfCompress = testHuffman.huffman(pathToTestDir, true);
        actualResultOfDecompress = testHuffman.huffman(pathToTestDir, false);

        // then
        int expectedResultOfCompress = 2;
        int expectedResultOfDecompress = 2;
        assertEquals(expectedResultOfCompress, actualResultOfCompress);
        assertEquals(expectedResultOfDecompress, actualResultOfDecompress);
    }

    @Test
    public void should_correctlyCompressAndDecompress_threeDifferentLetter() {
        // given
        String text = "abc";
        int actualResultOfCompress = 0;
        int actualResultOfDecompress = 0;

        // when
        prepareWriter(decompressedFile);
        writeString(text);
        actualResultOfCompress = testHuffman.huffman(pathToTestDir, true);
        actualResultOfDecompress = testHuffman.huffman(pathToTestDir, false);

        // then
        int expectedResultOfCompress = 5;
        int expectedResultOfDecompress = 3;
        assertEquals(expectedResultOfCompress, actualResultOfCompress);
        assertEquals(expectedResultOfDecompress, actualResultOfDecompress);
    }

    @Test
    public void should_correctlyCompressAndDecompress_examplaryFile_niemanie() {
        // given
        int actualResultOfCompress = 0;
        int actualResultOfDecompress = 0;

        // when
        prepareFile("niemanie.txt");
        actualResultOfCompress = testHuffman.huffman(pathToTestDir, true);
        actualResultOfDecompress = testHuffman.huffman(pathToTestDir, false);

        // then
        int expectedResultOfCompress = 8900;
        int expectedResultOfDecompress = 1908;
        assertEquals(expectedResultOfCompress, actualResultOfCompress);
        assertEquals(expectedResultOfDecompress, actualResultOfDecompress);
    }

    @Test
    public void should_correctlyCompressAndDecompress_examplaryFile_niemanie_refren() {
        // given
        int actualResultOfCompress = 0;
        int actualResultOfDecompress = 0;

        // when
        prepareFile("niemanie_refren.txt");
        actualResultOfCompress = testHuffman.huffman(pathToTestDir, true);
        actualResultOfDecompress = testHuffman.huffman(pathToTestDir, false);

        // then
        int expectedResultOfCompress = 567;
        int expectedResultOfDecompress = 167;
        assertEquals(expectedResultOfCompress, actualResultOfCompress);
        assertEquals(expectedResultOfDecompress, actualResultOfDecompress);
    }

    @Test
    public void should_correctlyCompressAndDecompress_examplaryFile_lorem_ipsum() {
        // given
        int actualResultOfCompress = 0;
        int actualResultOfDecompress = 0;

        // when
        prepareFile("lorem_ipsum.txt");
        actualResultOfCompress = testHuffman.huffman(pathToTestDir, true);
        actualResultOfDecompress = testHuffman.huffman(pathToTestDir, false);

        // then
        int expectedResultOfCompress = 379554;
        int expectedResultOfDecompress = 88384;
        assertEquals(expectedResultOfCompress, actualResultOfCompress);
        assertEquals(expectedResultOfDecompress, actualResultOfDecompress);
    }

    @Test
    public void should_giveIdenticalFileAfterDecompress() {
        // given
        String fileName = "lorem_ipsum.txt";

        // when
        prepareFile("lorem_ipsum.txt");
        testHuffman.huffman(pathToTestDir, true);
        testHuffman.huffman(pathToTestDir, false);

        // then
        try {
            byte[] f1 = Files.readAllBytes(Paths.get(pathToSamplesDir + "/" + fileName));
            byte[] f2 = Files.readAllBytes(Paths.get(decompressedFile.getPath()));
            assertArrayEquals(f1, f2);
        } catch (IOException e) {
            throw new IllegalArgumentException("Problem with file to test");
        }
    }

    @Test
    public void should_correctlyInterpret_polishDiacriticalMarks() {
        // given
        String text = "ąćęłńóśźż";
        int actualResultOfCompress = 0;
        int actualResultOfDecompress = 0;

        // when
        prepareWriter(decompressedFile);
        writeString(text);
        actualResultOfCompress = testHuffman.huffman(pathToTestDir, true);
        actualResultOfDecompress = testHuffman.huffman(pathToTestDir, false);

        // then
        int expectedResultOfCompress = 29;
        int expectedResultOfDecompress = 9;
        assertEquals(expectedResultOfCompress, actualResultOfCompress);
        assertEquals(expectedResultOfDecompress, actualResultOfDecompress);
    }

    @Test
    public void should_correctlyInterpret_newLineCharacters() {
        // given
        String text = "\n\n\n\n\n\n\n\n";
        int actualResultOfCompress = 0;
        int actualResultOfDecompress = 0;

        // when
        prepareWriter(decompressedFile);
        writeString(text);
        actualResultOfCompress = testHuffman.huffman(pathToTestDir, true);
        actualResultOfDecompress = testHuffman.huffman(pathToTestDir, false);

        // then
        int expectedResultOfCompress = 8;
        int expectedResultOfDecompress = 8;
        assertEquals(expectedResultOfCompress, actualResultOfCompress);
        assertEquals(expectedResultOfDecompress, actualResultOfDecompress);
    }

    private void prepareFile(String fileName) {
        try {
            Files.copy(Paths.get(pathToSamplesDir + "/" + fileName), Paths.get(decompressedFile.getAbsolutePath()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalArgumentException("There is no file to test");
        }
    }

    private void prepareWriter(File file) {
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("There is no required files for tests");
        }
    }

    private void writeString(String text) {
        try {
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot write to test file");
        }
    }

    private void validateInput(String pathToRootDir) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path to test dir is null");
        }
        File verifiedFile = new File(pathToRootDir);
        if (!verifiedFile.isDirectory()) {
            throw new IllegalArgumentException("Path does not lead to a test directory");
        }
        if (!verifiedFile.canRead()) {
            throw new IllegalArgumentException("Cannot read from test directory");
        }

        decompressedFile = new File(verifiedFile.getPath() + "\\" + nameOfDecompressedFile);
        compressedFile = new File(verifiedFile.getPath() + "\\" + nameOfCompressedFile);
        keyFile = new File(verifiedFile.getPath() + "\\" + nameOfFileForKey);

        if (decompressedFile.exists() || compressedFile.exists() || keyFile.exists()) {
            try {
                decompressedFile.delete();
                decompressedFile.createNewFile();
                compressedFile.delete();
                compressedFile.createNewFile();
                keyFile.delete();
                keyFile.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("There is no required test files");
            }
        }

        if (!decompressedFile.canWrite() || !compressedFile.canWrite() || !keyFile.canWrite()) {
            throw new IllegalArgumentException("Cannot write from test files");
        }
    }

}
