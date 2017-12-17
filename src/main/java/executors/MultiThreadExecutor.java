package executors;

import java.util.List;

/**
 * Created by Mika on 17.12.2017.
 */
public class MultiThreadExecutor implements GameExecutor {
    private int threadNumber;
    private int cellNumber;
    private ThreadPool pool;

    public MultiThreadExecutor(int threadNumber, int cellNumber) {
        this.cellNumber = cellNumber;
        this.threadNumber = threadNumber;
        pool = new ThreadPool(threadNumber);
    }

    @Override
    public void stop(){
        pool.stopPool();
    }

    @Override
    public List<char[]> computeOneIteration(int n, List<char[]> data, List<char[]> result) {
        int batchSize = n / cellNumber;
        for (int i = 0; i < cellNumber - 1; i++) {
            pool.addTask(new Task(data, result, n, i * batchSize, (i + 1) * batchSize));
        }
        pool.addTask(new Task(data, result, n, (cellNumber - 1) * batchSize, n));
        pool.waitTasks();
        return result;
    }
}
