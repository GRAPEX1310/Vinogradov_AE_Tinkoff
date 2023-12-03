package edu.hw8.Task2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedThreadPool implements ThreadPool {
    private static final Queue<Runnable> TASKS = new ArrayDeque<>();

    private static int threadsAmount;
    private static final AtomicBoolean IS_CLOSED = new AtomicBoolean(true);

    @Override
    public void start() {
        for (int index = 0; index < threadsAmount; index++) {
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

    public void create(int threadsAmount) {
        FixedThreadPool.threadsAmount = threadsAmount;
        FixedThreadPool.IS_CLOSED.set(false);
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
