package projecttest.javaspringtest.Thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

//플렛폼 스레드를 데몬 스레드로 만드는 클래스
public class DaemonExecutor implements Executor {
    private final ThreadFactory factory;

    public DaemonExecutor(ThreadFactory factory) {
        this.factory = factory;
    }

    public void execute(Runnable r) {
        Thread th = factory.newThread(r);
        th.setDaemon(true);
        th.start();
    }
}
