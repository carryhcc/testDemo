package com.example.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author :
 * @date : 2021/8/4 15:55
 * @return : null
 * @description: 阿里巴巴推荐，不自己启动线程。而使用线程池处理
 * 线程池处理类
 */
public class ThreadPoolService {

    /**
     * 自定义线程名称,方便出错的时候溯源
     */
    private static final ThreadFactory NAMED_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("THREAD-POOL-%d").build();

    /**
     * corePoolSize    线程池核心池的大小
     * maximumPoolSize 线程池中允许的最大线程数量
     * keepAliveTime   当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间
     * unit            keepAliveTime 的时间单位
     * workQueue       用来储存等待执行任务的队列
     * threadFactory   创建线程的工厂类
     * handler         拒绝策略类,当线程池数量达到上线并且workQueue队列长度达到上限时就需要对到来的任务做拒绝处理
     * 原理：
     * 有请求时，创建线程执行任务，当线程数量等于corePoolSize时，请求加入阻塞队列里，当队列满了时，接着创建线程，
     * 线程数等于maximumPoolSize。 当任务处理不过来的时候，线程池开始执行拒绝策略。
     * 换言之，线程池最多同时并行执行maximumPoolSize的线程，最多处理maximumPoolSize+workQueue.size()的任务。多余的默认采用AbortPolicy会丢弃。
     * 阻塞队列：
     * 　　ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
     * 　　LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
     * 　　PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
     * 　　DelayQueue： 一个使用优先级队列实现的无界阻塞队列。
     * 　　SynchronousQueue： 一个不存储元素的阻塞队列。
     * 　　LinkedTransferQueue： 一个由链表结构组成的无界阻塞队列。
     * 　　LinkedBlockingDeque： 一个由链表结构组成的双向阻塞队列。
     * 　　拒绝策略：
     * 　　ThreadPoolExecutor.AbortPolicy: 丢弃任务并抛出RejectedExecutionException异常。 (默认)
     * 　　ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * 　　ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务。（重复此过程）
     * 　　ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务。
     */
    private static final ExecutorService SERVICE = new ThreadPoolExecutor(
            10,
            20,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(4),
            NAMED_THREAD_FACTORY,
            new ThreadPoolExecutor.AbortPolicy()
    );

    /**
     * 获取线程池
     *
     * @return 线程池
     */
    public static ExecutorService getEs() {
        return SERVICE;
    }

    /**
     * 使用线程池创建线程并异步执行任务
     *
     * @param r 任务
     */
    public static void newTask(Runnable r) {
        System.out.println("创建任务成功");
        SERVICE.execute(r);
    }

    public static void shutdown() {
        SERVICE.shutdown();
    }
}