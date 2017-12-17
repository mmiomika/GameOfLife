package executors;

import java.util.List;

/**
 * Created by Mika on 17.12.2017.
 */
public class OneThreadExecutor implements GameExecutor {
    @Override
    public List<char[]> computeOneIteration(int n, List<char[]> data, List<char[]> result) {
        for (int i = 0; i < n; i++) {
            char[] line = result.get(i);
            for (int j = 0; j < n; j++) {
                int count = 0;
                //Считаем количество живых клеток вокруг текущей
                StringBuilder builder = new StringBuilder();
                int iPrev = i - 1 >= 0 ? i - 1 : n - 1;
                int iNext = i + 1 < n ? i + 1 : 0;
                int jPrev = j - 1 >= 0 ? j - 1 : n - 1;
                int jNext = j + 1 < n ? j + 1 : 0;

                int[] xArr = {iPrev, i, iNext};
                int[] yArr = {jPrev, j, jNext};

                for (int x : xArr) {
                    for (int y : yArr) {
                        if (x != i || y != j)
                            builder.append(data.get(x)[y]);
                    }
                }
                count = builder.toString().replace("0", "").length();
                //System.out.print(builder.toString() + " " + count + "; ");
                //Выполняем преобразование
                char current = data.get(i)[j];
                if (current == '1') {
                    if (count == 2 || count == 3) {
                        line[j] = '1';
                    }
                    else {
                        line[j] = '0';
                    }
                }
                else {
                    if (count == 3) {
                        line[j] = '1';
                    }
                    else {
                        line[j] = '0';
                    }
                }
            }
            //result.add(line);
        }
        return result;
    }

    @Override
    public void stop() {

    }
}
