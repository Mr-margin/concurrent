package com.cd.concurrent.t08;

import java.util.concurrent.Executor;

/**
 * 线程池
 * Exceutor - 线程池底层处理机制
 * 线程池顶级接口。定义方法， void (Runnable)。方法是用于处理任务的一个服务方法。调用者提供 Runnable 接口的实现，线程池
 *   通过线程执行这个Runnable。服务方法无返回值的。是Runnable接口中的 run() 方法无返回值。
 *   常用方法：- void execute(Runnable)
 *   作用是 ：启动线程任务的
 *
 * 在使用线程池的时候，底层如何调用线程汇总的逻辑
 */
public class Test_01_Myexcutor implements Executor {
    public static void main(String[] args){
        new Test_01_Myexcutor().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " - test executor");
            }
        });
    }
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
