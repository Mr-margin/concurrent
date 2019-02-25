package com.cd.concurrent.t06;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 并发容器 - ConcurrentLinkedQueue
 * Queue 消息队列
 */
public class Test_03_ConcurrentLinkedQueue {

    public static void main(String[] args ) {
        Queue<String> queue = new ConcurrentLinkedQueue<String>();//链表结构  先进先出
        for ( int i = 0 ; i < 10 ; i ++ ) {
            queue.offer("value"+i);//加数据
        }
        System.out.println(queue);
        System.out.println(queue.size());

        //peek()  --> 常看queue的首数据
        System.out.println(queue.peek());
        System.out.println(queue.size());

        //poll --> 获取queue中的首数据
        System.out.println(queue.poll());
        System.out.println(queue.size());
    }

}
