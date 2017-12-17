package executors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mika on 17.12.2017.
 */
public class Task {
    private List<char[]> data;
    private List<char[]> result;
    private int n;
    private int start;
    private int end;

    public Task(List<char[]> data, List<char[]> result, int n, int start, int end) {
        this.data = data;
        this.result = result;
        this.n = n;
        this.start = start;
        this.end = end;
    }

    public int getStart(){
        return start;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            char[] line = new char[n];
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
            result.set(i, line);
        }
    }
}
