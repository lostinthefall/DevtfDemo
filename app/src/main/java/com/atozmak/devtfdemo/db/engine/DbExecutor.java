package com.atozmak.devtfdemo.db.engine;

import android.os.*;
import android.os.Process;

import com.atozmak.devtfdemo.db.cmd.Command;
import com.atozmak.devtfdemo.listeners.DataListener;

/**
 * Created by Mak on 2016/3/19.
 */
public class DbExecutor {

    private static final HandlerThread HT = new HandlerThread(
            DbExecutor.class.getName(),
            Process.THREAD_PRIORITY_BACKGROUND
    );

    static {
        HT.start();
    }

    final static Handler sAsyncHandler = new Handler(HT.getLooper());

    final static Handler mUIHandler = new Handler(Looper.getMainLooper());

    static DbExecutor mDispatcher = new DbExecutor();

    private DbExecutor() {
    }

    public static DbExecutor getExecutor() {
        return mDispatcher;
    }

    public Handler getUIHandler() {
        return mUIHandler;
    }

    public void submit(Runnable runnable) {
        sAsyncHandler.post(runnable);
    }

    public <T> void execute(final Command<T> command) {
        sAsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                T result = command.execute();
                if (command.dataListener != null) {
                    postResult(result, command.dataListener);
                }
            }
        });
    }

    private <T> void postResult(final T result, final DataListener<T> listener) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.onComplete(result);
            }
        });
    }

}
