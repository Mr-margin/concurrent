package com.cd.concurrent.t06;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 并发容器 - LinkedBlockingQueue
 * LinkedBlockingQueue -  阻塞队列
 * put & take  - 自动阻塞
 * put自动阻塞，队列容量满后自动阻塞
 * take自动阻塞方法，队列容量为0后，自动阻塞。
 */
public class Test_04_LinkedBlockingQueue {

    final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
    final Random r = new Random();

    public static void main (String[] args) {
        final Test_04_LinkedBlockingQueue t = new Test_04_LinkedBlockingQueue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        t.queue.put("value"+t.r.nextInt(1000));
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"producer").start();
        for ( int i = 0 ; i < 3 ; i ++ ) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },"consumer"+i).start();
        }
    }

}
