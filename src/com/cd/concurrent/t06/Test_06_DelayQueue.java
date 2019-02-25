package com.cd.concurrent.t06;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 并发容器 - DelayQueue
 * 延时队列，根据比较机制，实现自定义处理顺序的队列。常用于定时任务，如定时关机
 */
public class Test_06_DelayQueue {

    static BlockingQueue<MyTask_06> queue = new DelayQueue<MyTask_06>();

    public static void main(String[] args){
        long value = System.currentTimeMillis();
        MyTask_06 task1 = new MyTask_06(value+2000);
        MyTask_06 task2 = new MyTask_06(value+1000);
        MyTask_06 task3 = new MyTask_06(value+3000);
        MyTask_06 task4 = new MyTask_06(value+2500);
        MyTask_06 task5 = new MyTask_06(value+1500);

        try {
            queue.put(task1);
            queue.put(task2);
            queue.put(task3);
            queue.put(task4);
            queue.put(task5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(queue);
        System.out.println(value);

        for ( int i = 0 ; i < 5 ; i ++ ) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class MyTask_06 implements Delayed {
    private long compareValue;
    public MyTask_06(long compareValue){
        this.compareValue = compareValue;
    }
    /**
     * 比较大小。自动实现升序
     * 建议和getDelay方法配合完成
     * 如果在DelayQueue是需要按时间完成的计划任务，必须配合getDelay方法完成。
     */
    @Override
    public int compareTo(Delayed o ) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    /**
     * 获取计划时长的方法
     * 根据参数TimeUnit来决定，如何返回结果值。
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit){
        return unit.convert(compareValue - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }
    @Override
    public String toString(){
        return "Task compare value is : " + this.compareValue;
    }
}
