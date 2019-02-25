package com.cd.concurrent.t06;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 并发容器 - CopyOnWriteList
 * CopyOnWriteArrayList - 写数据慢  读数据快，删除最后一个速度快，删除中间和修改都是比较慢的。用于写少读多。线程安全。
 * 写时复制集合，写入效率低，读取效率高。每次写入数据，都会创建一个新的底层数组。
 * Voctor 线程安全
 * ArrayList 线程不安全，速度最快。
 *
 */
public class Test_02_CopyOnWriteList {

    public static void main(String[] args){
        //final List<String > list = new ArrayList<String>();
        final List<String> list = new Vector<String>();
        //final List<String> list = new CopyOnWriteArrayList<String>();

        final Random r = new Random();
        Thread[] array = new Thread[100];
        final CountDownLatch latch = new CountDownLatch(array.length);

        long begin = System.currentTimeMillis();
        for( int i = 0 ; i < array.length; i ++ ) {
            array[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for ( int j = 0; j < 1000;j++ ) {
                        list.add("value" + r.nextInt(100000));
                    }
                    latch.countDown();
                }
            });
        }
        for ( Thread t : array ) {
            t.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间为："+(end-begin)+"毫秒！");
        System.out.println("List.size()："+list.size());
    }
}
