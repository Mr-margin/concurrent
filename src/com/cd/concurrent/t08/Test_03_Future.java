package com.cd.concurrent.t08;

import java.util.concurrent.*;

/**
 * Exectors 工具类型。为Executor线程池提供工具方法，可以快速的提供若干种线程池，如固定容量的，，，无线容量的，容量为1等各种线程池。
 * 线程池是一个进程级的重量级资源。默认的生命周期与JVM一致，当开启线程池后，直到JVM关闭为止，是线程池的默认生命周期。如果手动调用shutdown方法，
 * 那么线程池执行所有的任务后，自动关闭。
 * 开始 -- 创建线程池
 * 结束 -- JVM关闭或调用shutdown并处理完成所有的任务。
 * 类似Arrays,Collections等工具类型的功用。
 *
 */
public class Test_03_Future {

//    Executor
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(1);

        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("aaa");
                return Thread.currentThread().getName() + " - test executor";
            }
        });
        System.out.println(future);
        System.out.println(future.isDone());//查看线程是否结束，任务是否完成，call方法是否执行结束

        System.out.println(future.get());//获取call方法的返回值
        System.out.println(future.isDone());
    }


}
