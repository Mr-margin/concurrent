package com.cd.concurrent.t08;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 固定容量线程池
 * FixedThreadPool - 固定容量线程池。创建线程池的时候，容量固定。
 *  构造的时候，提供线程池最大容量。
 * new.xxxx
 * ExecutorService - 线程池服务类型。所有的线程池类型都实现这个接口
 * Exector 接口的子接口 。提供一个新的服务方法，submit有返回值（Future类型）。submit方法提供了overload方法。其中
 *      有参数类型为Runnable的，不需要提供返回值的；有参数类型为callable，可以提供线程执行后的返回值。
 *
 *      Future,是submit方法的返回值。代表未来，也就是线程执行结束后的一种结果。如返回值。
 *      获取线程执行结果的方式是通过get方法获取的。get 无参，阻塞等待线程执行结束，并得到结果。get 有参，阻塞
 *      固定时长，等待线程执行结束后的结果，如果在阻塞时长方位内，线程未执行结果，抛出异常。
 *      常见方法：T get() , T get(long,timeUnit)
 *
 * 常用方法： void execute(Runnable),Future submit(Callable),Future submit(Runnable)
 *
 *  实现这个接口，代表可以提供线程池能力
 *   shutdown - 优雅关闭 ，不是强行关闭线程池，回收线程池中的资源。而是不在处理新的任务，将已接收的任务处理完毕后再关闭
 *  Executors - Executor的工具类。类似Collection和Collections的关系。
 *   可以更简单的创建若干种线程池。
 */
public class Test_02_FixedThreadPool {

    public static void main (String[] args){
        ExecutorService service = Executors.newFixedThreadPool(5);
        for ( int i = 0 ; i < 6 ; i ++ ) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - test exector");
                }
            });
        }
        System.out.println(service);

        service.shutdown();//关闭 没有结束
        //是否已经结束，相当于回收了资源
        System.out.println(service.isTerminated());
        //是否已经关闭，是否调用过shutdown方法
        System.out.println(service.isShutdown());
        System.out.println(service);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        service.shutdown();
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}
