package com.cd.concurrent.t08;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CachedThreadPool
 * 缓存的线程池，容量不限（Integer.MAX_VALUE）。自动扩容，容量管理策略；如果线程池中的线程数量不满足任务执行，创建新的线程，
 * 每次有新任务无法即时处理的时候，都会创建新的线程，当线程池中的线程空闲时长达到一定的临界值（默认60s），自动释放线程。
 *
 *  默认线程空闲60秒，自动摧毁。
 *
 * 应用场景：内部应用或测试应用，内部应用，有条件的内部数据瞬间处理时应用，如：电信平台夜间执行数据整理（有把握在短时间内处理完成所有工作，
 *  且对硬件和软件有足够的信心）。测试应用，在测试的时候，尝试得到硬件或软件的最高负载量，用于提供FixedThreadPool容量的指导。
 *
 */
public class Test_05_CachedThreadPool {

    public  static void main (String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for ( int i = 0 ; i < 5 ; i ++ ){
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
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service);
    }

}
