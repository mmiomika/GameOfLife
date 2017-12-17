package executors;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Mika on 17.12.2017.
 */
public class ThreadPool {
    private ConcurrentLinkedQueue<Task> waitForWork;
    private ConcurrentLinkedQueue<Task> workingNow;
    private volatile boolean work;

    public ThreadPool(int threads){
        work = true;
        workingNow = new ConcurrentLinkedQueue<>();
        waitForWork = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < threads; i++) {
            new Thread(new TaskExecutor()).start();
        }
    }

    public void stopPool() {
        work = false;
    }

    public void waitTasks() {
        while (!waitForWork.isEmpty() || !workingNow.isEmpty()) {}
    }

    public void addTask(Task task) {
        waitForWork.offer(task);
    }

    private final class TaskExecutor implements Runnable {
        @Override
        public void run() {
            while (work){
                Task task = waitForWork.poll();
                if (task != null) {
                    workingNow.offer(task);
                    task.run();
                    workingNow.remove(task);
                }
            }
        }
    }

}
