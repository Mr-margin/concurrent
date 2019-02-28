package com.cd.concurrent.t08;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SingleThreadExecutor
 *     单一容量线程
 *     使用场景：保证任务顺序时使用，如：游戏大厅中的公共频道聊天。秒杀商品
 */
public class Test_06_SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        System.out.println(service);

        for( int i = 0 ; i < 5; i++ ) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - test executor");
                }
            });
        }
    }
}
