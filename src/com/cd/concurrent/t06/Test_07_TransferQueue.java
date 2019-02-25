package com.cd.concurrent.t06;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * 并发容器 - LinkedTransferQueue
 * 转移队列  实现数据的转移  无容量
 * add - 队列会保存数据，不做阻塞等待。
 * transfer - 是TransferQueue的特有方法。必须有消费者(take()方法的调用者).
 * 如果没有任意线程消息数据，transfer方法阻塞。一般用于处理即时消息。
 */
public class Test_07_TransferQueue {
    TransferQueue<String> queue = new LinkedTransferQueue<String>();

    public static void main(String[] args){
        final Test_07_TransferQueue t = new Test_07_TransferQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " thread begin");
                try {
                    System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"output thread").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t.queue.transfer("test string");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
