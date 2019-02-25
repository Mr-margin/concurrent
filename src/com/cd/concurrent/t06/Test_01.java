package com.cd.concurrent.t06;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class Test_01 {
    public static void main(String[] args){
        final Map<String,String> map = new Hashtable<String, String>();
        //final Map<String,String> map = new ConcurrentHashMap<String, String>();
       // final Map<String,String> map = new ConcurrentSkipListMap<String, String>();
        final Random rn = new Random();
        Thread[] array = new Thread[100];
        final CountDownLatch latch = new CountDownLatch(array.length);
        long begin = System.currentTimeMillis();
        for ( int i = 0 ; i < array.length; i++ ) {
            array[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for( int j = 0 ; j < 100000; j ++ ) {
                        map.put("key"+rn.nextInt(100000),"value"+rn.nextInt(100000));
                    }
                    latch.countDown();
                }
            });
        }
        for ( Thread t :array){
            t.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间为："+(end-begin)+"毫秒！");
    }
}
