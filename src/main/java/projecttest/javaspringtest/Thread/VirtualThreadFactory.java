package projecttest.javaspringtest.Thread;

import java.util.concurrent.ThreadFactory;

public class VirtualThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return Thread.ofVirtual().unstarted(r); //생성후 대기
    }
}