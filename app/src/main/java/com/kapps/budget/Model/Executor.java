package com.kapps.budget.Model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    public static void IOThread(Runnable t) {
        ExecutorService IO_EXECUTOR = Executors.newSingleThreadExecutor();
        IO_EXECUTOR.execute(t);
    }
}