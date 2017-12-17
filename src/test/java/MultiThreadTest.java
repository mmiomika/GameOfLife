import executors.MultiThreadExecutor;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mika on 17.12.2017.
 */
public class MultiThreadTest {

    @Test
    public void testMultiThreadGame10() throws FileNotFoundException {
        GameOfLife gameOfLife = new GameOfLifeImpl(new MultiThreadExecutor(4, 4));
        testOneGame(gameOfLife, "resources/input.txt", "resources/output.txt");
    }

    @Test
    public void testMultiThreadGame100() throws FileNotFoundException {
        GameOfLife gameOfLife = new GameOfLifeImpl(new MultiThreadExecutor(4, 4));
        testOneGame(gameOfLife, "resources/input100.txt", "resources/output100.txt");
    }

    @Test
    public void testMultiThreadGame1000() throws FileNotFoundException {
        GameOfLife gameOfLife = new GameOfLifeImpl(new MultiThreadExecutor(4, 4));
        testOneGame(gameOfLife, "resources/input1000.txt", "resources/output1000.txt");
    }




    private void testOneGame(GameOfLife gameOfLife, String inputFile, String expectedOutputFile) throws FileNotFoundException {
        List<String> result = gameOfLife.play(inputFile);
        assertEquals(readFile(expectedOutputFile), result);
    }

    private static List<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        Scanner scan = new Scanner(new File(fileName));
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
        scan.close();

        return lines;
    }
}
