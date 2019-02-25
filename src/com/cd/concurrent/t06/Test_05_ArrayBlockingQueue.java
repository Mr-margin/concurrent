package com.cd.concurrent.t06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 并发容器 - ArrayBlockingQueue
 * 底层数组实现的有界队列。自动阻塞。根据调用API(add/put/offer)不同，有不同特性。
 * 当容量不足的时候，有阻塞能力。
 * add容量不足时候，抛异常
 * put方法在容量不足时候，阻塞等待
 * offer
 * 单参数offer方法，不阻塞，容量不足的时候，返回false。当前新增数据操作放弃。
 * 三参数offer方法(offer(value,times,timeout)),容量不足的时候，阻塞times时长（单位的timeunit），新增数据返回true，
 * 如果阻塞时长范围内，无容量空闲，放弃新增数据，返回false。
 */
public class Test_05_ArrayBlockingQueue {

    final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);

    public static void main (String[] args){
        final Test_05_ArrayBlockingQueue t = new Test_05_ArrayBlockingQueue();
        for( int i = 0 ; i < 5 ; i ++ ) {
            System.out.println("add method : "+t.queue.add("value"+i));
            try {
                t.queue.put("put"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("put method : "+i);
            System.out.println("offer method :"+t.queue.offer("value"+i));
            try {

                System.out.println("offer method :" + t.queue.offer("value"+i,1,TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
