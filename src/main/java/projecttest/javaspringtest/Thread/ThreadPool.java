package projecttest.javaspringtest.Thread;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {
        //Fixed : 정해진 개수만큼 스레드 생성, Cached : 스레드 0개로 시작해서 필요할 때마다 생성, 작업끝나면 60초 대기후 삭제,
        // newCachedThreadPool(int nThreads)
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // 현재JVM 에서 사용가능한 코어 개수를 쓰레드로 만듬.

    // 커스텀
    ExecutorService threadPool = new ThreadPoolExecutor(
            3, // 코어 스레드 개수
            100, // 최대 스레드 개수
            120L, // 최대 놀 수 있는 시간 (이 시간 넘으면 스레드 풀에서 쫓겨 남.)
            TimeUnit.SECONDS, // 놀 수 있는 시간 단위
            new SynchronousQueue<Runnable>() // 작업 큐
    );
    }
}
