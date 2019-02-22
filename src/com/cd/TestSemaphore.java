package com.cd;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

    public static void main ( String[] args ) {
        Semaphore semaphore = new Semaphore(5);//机器数目，即五个许可
        for ( int i = 0 ; i < 8 ; i ++ ) { //工人数，8个去抢许可

            new Worker(i,semaphore).start();

        }

    }
    static class Worker extends Thread {

        private int num ;
        private Semaphore semaphore;

        public Worker ( int num ,Semaphore semaphore){
            this.num = num ;
            this.semaphore = semaphore;
        }
        @Override
        public void run(){
            try{
                semaphore.acquire();//抢许可
                Thread.sleep(2000);
                semaphore.release();//释放许可
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }
}
