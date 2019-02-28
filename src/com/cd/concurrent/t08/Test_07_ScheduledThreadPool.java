package com.cd.concurrent.t08;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPool
 *   计划任务线程池，可以根据计划自动执行任务的线程池
 *   scheduleAtFixedRate(Runnable,start_limit,,limit,timeunit)
 *   Runnable - 要执行的任务
 *   start_limit - 第一次任务执行的间隔
 *   limit - 多次任务执行的间隔
 *    timeunit - 多次任务执行间隔的时间单位
 *    使用场景： 计划任务时选用（DelaydQueue）,如：电信行业中的数据整理，每分钟整理，每小时整理，每天整理。
 */
public class Test_07_ScheduledThreadPool {

    public static void main(String[] args){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        System.out.println(service);

        //定时完成任务，scheduleAtFixedRate(Runnable,start_limit,,limit,timeunit)
        //runnable - 要执行的任务。
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        },0,300,TimeUnit.MILLISECONDS);
    }
}
