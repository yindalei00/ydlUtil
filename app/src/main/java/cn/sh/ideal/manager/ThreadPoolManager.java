package cn.sh.ideal.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 对线程池进行管理和封装
 *
 * @author yindalei
 */
public class ThreadPoolManager {
    private static ThreadPoolManager mInstance = new ThreadPoolManager();
    private ThreadPoolExecutor executor;

    /**
     * 核心线程池数量，表示能够同时执行的任务数量
     */
    private int corePoolSize;
    /**
     * 最大线程池数量，其实是包含了核心线程池数量在内的
     */
    private int maximumPoolSize;

    /**
     * 存活时间,表示最大线程池中等待任务的存活时间
     */
    private long     keepAliveTime = 1;
    /**
     * 存活时间的时间单位
     */
    private TimeUnit unit          = TimeUnit.HOURS;

    public static ThreadPoolManager getInstance() {
        return mInstance;
    }

    private ThreadPoolManager() {
        /**
         * 核心线程池数量的计算规则：当前设备的可用处理器核心数*2 + 1,能够让CPU得到最大效率的发挥；
         */
        corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        /**
         * 虽然用不到，但是不能是0，否则报错
         */
        maximumPoolSize = corePoolSize;
        /**
         * 线程池机制：领工资的机制
         */
        executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /**
     * 往线程池中添加任务
     *
     * @param r runnable
     */
    public void execute(Runnable r) {
        if (r != null) {
            executor.execute(r);
        }
    }

    /**
     * 从线程池中移除任务
     *
     * @param r runnable
     */
    public void cancle(Runnable r) {
        if (r != null) {
            executor.remove(r);
        }
    }





}
