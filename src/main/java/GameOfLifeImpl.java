import executors.GameExecutor;
import executors.OneThreadExecutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mika on 17.12.2017.
 */
public class GameOfLifeImpl implements GameOfLife {
    private GameExecutor executor = new OneThreadExecutor();

    public GameOfLifeImpl(GameExecutor gameExecutor) {
        executor = gameExecutor;
    }

    @Override
    public List<String> play(String inputFile) {
        // Сканируем текстовый файл
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null) {
            return null;
        }
        int n = 0, m = 0;
        List<char[]> data = new ArrayList<>(n);
        List<char[]> data1 = new ArrayList<>(n);
        if (scanner.hasNextLine()) {
            String[] args = scanner.nextLine().split(" ");
            n = Integer.valueOf(args[0]);
            m = Integer.valueOf(args[1]);
        }
        while (scanner.hasNextLine()) {
            data.add(scanner.nextLine().toCharArray());
        }

        for (int i = 0; i < n; i++) {
            data1.add(new char[n]);
        }
        //Исполняем шаги игры
        int iter = 0;
        List<char[]> lastResult = data;
        while (iter < m) {
            //System.out.println("Iteration: " + iter);
            if (iter % 2 == 0)
                lastResult = executor.computeOneIteration(n, data, data1);
            else
                lastResult = executor.computeOneIteration(n, data1, data);
            iter++;
        }
        executor.stop();
        List<String> result = new ArrayList<>();
        for (char[] line : lastResult) {
            result.add(String.valueOf(line));
        }
        return result;
    }
}
