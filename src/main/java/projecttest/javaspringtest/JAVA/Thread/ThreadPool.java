package projecttest.javaspringtest.JAVA.Thread;

import java.nio.charset.Charset;
import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(java.nio.charset.Charset.defaultCharset());
        System.out.println("defaultCharset = " + Charset.defaultCharset());
        System.out.println("System.out.charset = " + System.out.charset());
        //Fixed : 정해진 개수만큼 스레드 생성, Cached : 스레드 0개로 시작해서 필요할 때마다 생성, 작업끝나면 60초 대기후 삭제,
        // newCachedThreadPool(int nThreads)
        ExecutorService executorService = Executors.newFixedThreadPool(4); //Runtime.getRuntime().availableProcessors() : 현재JVM 에서 사용가능한 코어 개수를 쓰레드로 만듬.

    // 커스텀
    ExecutorService threadPool = new ThreadPoolExecutor(
            3, // 코어 스레드 개수
            100, // 최대 스레드 개수
            120L, // 최대 놀 수 있는 시간 (이 시간 넘으면 스레드 풀에서 쫓겨 남.)
            TimeUnit.SECONDS, // 놀 수 있는 시간 단위
            new SynchronousQueue<Runnable>() // 작업 큐
    );

    for(int i=0;i<5;i++){
        Runnable runnable = ()-> System.out.println("execute : "+Thread.currentThread().getName());
        executorService.execute(runnable);  // void 리턴 , Runnable을 작업 큐에 저장하고, 작업 처리 결과를 받지 못함. 예외가 발생하면 해당 스레드를 스레드 풀에서 제거함
    }

    for(int i=0;i<5;i++){
        Callable<String> callable = ()-> {
            System.out.println("submit : "+Thread.currentThread().getName());
            return "리턴값";
        };

        Future<String> future = executorService.submit(callable);  // Future 리턴 , Runnable 또는 Callable을 작업 큐에 저장. 리턴된 Future를 통해 작업 처리 결과를 알 수 있음.  예외가 발생하더라도 스레드는 종료되지 않고 다른 작업에 재사용될 수 있음
        System.out.println(future.get());
    }

    executorService.shutdown(); // void 리턴 타입 , 현재 처리 중인 작업 뿐만 아니라 작업 큐에 대기하고 있는 모든 작업을 처리한 뒤에 스레드 풀을 종료한다.
        // shutdownNow() : List 리턴 타입 , 현재 작업 처리 중인 스레드를 interrupt해서 작업 중지를 시도하고 스레드 풀을 종료한다. 리턴 값은 작업 큐에 있는 미처리된 작업의 목록이다.
        //awaitTermination(long timeout, TimeUnit unit) : boolean 리턴 타입 , shutdown() 메소드 호출 이후, 모든 작업 처리를 timeout 시간 내에 완료하면 true를 리턴하고, 그렇지 않으면 작업 처리 중인 스레드를 interrupt하고 false를 리턴한다.






    }
}
