package com.cd.concurrent.t01;
/**
 * @author chendong
 * @date 2019-02-20 15:37
 * @param
 * @return
 * @Description syschroniezd 关键字
 * 锁对象 synchronized(this) 和 synchronized 方法都是锁当前对象
 */
public class Test_01 {

    private int count = 0 ;
    private Object o = new Object();

    public void testSync1 () {
        synchronized(o) {
            System.out.println(Thread.currentThread().getName() + " count="+count++);
        }
    }

    public void testSync2 () {
        synchronized(this){
            System.out.println(Thread.currentThread().getName()+" count="+count++);
        }
    }

    public synchronized void testSync3 () {
        System.out.println(Thread.currentThread().getName()+" count="+count++);
    }

    public static void main(String[] args){
        Test_01 t = new Test_01();
        t.testSync1();
    }
}
