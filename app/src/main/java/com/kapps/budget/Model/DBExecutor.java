package com.kapps.budget.Model;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBExecutor {

    private static DBExecutor sInstance;
    private Executor diskIo;
    private Executor UIThread;

    private DBExecutor(Executor diskIo, Executor UIThread) {
        this.diskIo = diskIo;
        this.UIThread = UIThread;
    }

    //
//    public static DBExecutor getInstance() {
//        if (sInstance == null) {
//            sInstance = new DBExecutor(Executors.newSingleThreadExecutor(), new MainThreadExecutor());
//        }
//
//        public Executor getDiskIo () {
//            return diskIo;
//        }
//
//        public Executor getUIThread () {
//            return UIThread;
//        }
//
//    }
    public static void Execute(Runnable t) {
        ExecutorService IO_EXECUTOR = Executors.newSingleThreadExecutor();
        IO_EXECUTOR.execute(t);
    }
}