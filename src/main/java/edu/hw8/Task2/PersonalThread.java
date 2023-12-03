package edu.hw8.Task2;

public class PersonalThread extends Thread {
    private final FixedThreadPool threadPool;

    public PersonalThread(FixedThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        while (!threadPool.allTasksCompleted() || !threadPool.isClosed()) {
            Runnable task = threadPool.getTask();

            if (task != null) {
                task.run();
            }
        }
    }
}
