package com.cd.concurrent.t08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor
 *     线程池底层实现。除了 ForkJoinPoll外，其他常用线程池底层都是使用 ThreadPollExecutor实现的
 *     public ThreadPoolExecutor (
 *         int corePoolSize,//核心容量，创建线程池的时候，默认有多少线程。也就是线程池保持的最少线程数
 *         int maximumPoolSize,//最大容量，线程池最多有多少个线程
 *         long keepAliveTime,//生命周期，0为永久
 *         TimeUnit unit,//生命周期单位，毫秒、秒
 *         BlockingQueue<Runnable> workQueue //任务队列，阻塞队列，注意，泛型必须是Runnable。
 */
public class Test_09_ThreadPoolExecutor {

    public static void main(String[] main){
        //模拟fixedThreadPool，核心线程5个，最大容量5个，线程的生命周期无限。
        ExecutorService service = new ThreadPoolExecutor(5,5,0L,TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        for( int i = 0 ; i < 6 ; i ++ ) {
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
        System.out.println(service);

        service.shutdown();
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

    }
}
