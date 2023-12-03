package edu.hw8.Task2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedThreadPool implements ThreadPool {
    private static final Queue<Runnable> TASKS = new ArrayDeque<>();

    private static final int THREADS_AMOUNT = 6;
    private static final AtomicBoolean IS_CLOSED = new AtomicBoolean(true);

    public FixedThreadPool() {
        IS_CLOSED.set(false);
    }

    @Override
    public void start() {
        for (int index = 0; index < THREADS_AMOUNT; index++) {
            PersonalThread thread = new PersonalThread(this);
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!IS_CLOSED.get()) {
            TASKS.add(runnable);
        }
    }

    @Override
    public synchronized void close() throws Exception {
        IS_CLOSED.set(true);
        TASKS.clear();
    }

    public boolean allTasksCompleted() {
        return TASKS.isEmpty();
    }

    public synchronized boolean isClosed() {
        return IS_CLOSED.get();
    }

    public Runnable getTask() {
        return TASKS.poll();
    }
}
