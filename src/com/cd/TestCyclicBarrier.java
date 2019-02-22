package com.cd;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main (String[] args ) {

        new TestCyclicBarrier().begin();

    }

    private void begin() {

        for ( int i = 0 ; i < 5 ; i ++ ) {
            new Thread(new Student()).start();
        }

    }

    public class Student implements Runnable {
        @Override
        public void run() {
            try{
                Thread.sleep(2000);//该学生正在赶往饭店的路上
                cyclicBarrier.await();//到了就等着，等其他人到齐了，就进饭店
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            // TODO : 大家都到了，进去吃饭
        }

    }

}
