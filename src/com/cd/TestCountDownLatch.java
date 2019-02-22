package com.cd;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    private CountDownLatch countDownLatch = new CountDownLatch(4);//构造方法指明计数器

    public static void main(String[] args){
        TestCountDownLatch testCountDownLatch = new TestCountDownLatch();
        testCountDownLatch.begin();
    }

    private class Runner implements Runnable{
        private int result;
        public Runner(int result ) {
            this.result = result;
        }
        @Override
        public void run () {
            try{
                Thread.sleep(result*1000);//模拟跑了多少分
                countDownLatch.countDown();//跑完了就计数器减1
            } catch (Exception e ) {
                e.printStackTrace();
            }
        }
    }
    private void begin() {
        System.out.println("赛跑开始啦");
        Random random = new Random(System.currentTimeMillis());
        for ( int i = 0 ; i < 4 ; i ++ ) {
            int result = random.nextInt(3)+1;
            new Thread( new Runner(result)).start();
        }
        try {
            countDownLatch.await();//线程等待则为0
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        System.out.println("所有人都跑完了，裁判开始算成绩");
    }
}
