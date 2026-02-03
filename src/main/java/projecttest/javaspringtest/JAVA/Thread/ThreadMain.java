package projecttest.javaspringtest.JAVA.Thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

public class ThreadMain {
    public static void main(String[] args) throws InterruptedException {


        // 1. 쓰레드가 수행할 작업
        Runnable r = ()-> {
            System.out.println("스레드 : " + Thread.currentThread().getName() + "/ 가상스레드 여부 : " + Thread.currentThread().isVirtual());
        };
        // 2. 쓰레드를 생성하는 방법
        ThreadFactory factory = new PlatformThreadFactory();

        // 3. 쓰레드를 실행하는 방법
        Executor executor = new DaemonExecutor(factory);
        executor.execute(r);

        /*
        Thread t2 = Thread.ofPlatform().start(t1); 플렛폼 스레드 생성 및 실행 (인라인 방식)

        Thread t2 = new Thread(t1); 스레드 생성 (객체 생성 방식)
        t2.start(); 스레드 실행
        */
        
        // 1. 가상 스레드 생성
        ThreadFactory VTfactory = new VirtualThreadFactory();
        Thread VT = VTfactory.newThread(r);
        
        // 2. 가상 스레드 실행
        VT.start();
        VT.join();
    }
}