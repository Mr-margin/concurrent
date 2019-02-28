package com.cd.concurrent.t08;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class Test_08_ForkJoinPool {

    final static int[] numbers = new int[1000000];
    final static int MAX_SIZE = 50000;
    final static Random r = new Random();

    static {
        for ( int i = 0 ; i < numbers.length; i ++ ) {
            numbers[i] = r.nextInt(1000);
        }
    }
    static class AddTask extends RecursiveTask<Long> {
        int begin,end;
        public AddTask(int begin,int end){
            this.begin=begin;
            this.end =end ;
        }
        @Override
        protected Long compute() {
            if((end-begin) < MAX_SIZE){
                long sum = 0L ;
                for( int i = begin;i < end ; i ++ ){
                    sum += numbers[i];
                }
                return sum;
            } else {
                int middle = begin + (end - begin)/2;
                AddTask taks1 = new AddTask(begin,middle);
                AddTask task2 = new AddTask(middle,end);
                taks1.fork();//就是用于开启新的任务的，就是分支工作的。就是开启一个新的线程任务。
                task2.fork();
                //join 合并，将任务的结果获取。这是一个阻塞方法。一定会得到结果数据。
                return taks1.join() + task2.join();
            }
        }
    }

    public static void main(String[] args){
        long result = 0L;
        for ( int i = 0 ; i < numbers.length; i ++ ) {
            result += numbers[i];
        }
        System.out.println(result);
        ForkJoinPool pool = new ForkJoinPool();
        AddTask task = new AddTask(0,numbers.length);

        Future<Long> future = pool.submit(task);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
