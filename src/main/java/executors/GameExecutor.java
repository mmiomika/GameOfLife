package executors;

import java.util.List;

/**
 * Created by Mika on 17.12.2017.
 */
public interface GameExecutor {
    List<char[]> computeOneIteration(int n, List<char[]> data, List<char[]> result);
    void stop();
}
