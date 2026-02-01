package projecttest.javaspringtest.Thread;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEx {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(()-> delaySayHello(5000)); // 5초 대기
        executorService.submit(()-> getUserInput()); // 입력 대기

        for (int i = 1; i <= 5; i++)
            executorService.submit(()-> sayHello());
        executorService.shutdown();
    }
    public static void sayHello() {
        System.out.println("Hello - " + Thread.currentThread().getName());
    }

    public static void delaySayHello(int ms) {
        try {
            Thread.sleep(ms);
        } catch(Exception e) {}
        sayHello();
    }

    public static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input any number=");
        System.out.println(scanner.nextLine()+ " - "
                + Thread.currentThread().getName());
    }

    // 실행 결과는 스레드 1,2 번은 대기하느라 다른 작업을 못하고, 3,4번 스레드가 반복문을 처리함.
}