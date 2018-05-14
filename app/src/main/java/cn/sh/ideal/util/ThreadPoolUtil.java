package cn.sh.ideal.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池规则
 线程池的线程执行规则跟任务队列有很大的关系。

 下面都假设任务队列没有大小限制：

 如果线程数量<=核心线程数量，那么直接启动一个核心线程来执行任务，不会放入队列中。
 如果线程数量>核心线程数，但<=最大线程数，并且任务队列是LinkedBlockingDeque的时候，超过核心线程数量的任务会放在任务队列中排队。
 如果线程数量>核心线程数，但<=最大线程数，并且任务队列是SynchronousQueue的时候，线程池会创建新线程执行任务，这些任务也不会被放在任务队列中。这些线程属于非核心线程，在任务完成后，闲置时间达到了超时时间就会被清除。
 如果线程数量>核心线程数，并且>最大线程数，当任务队列是LinkedBlockingDeque，会将超过核心线程的任务放在任务队列中排队。也就是当任务队列是LinkedBlockingDeque并且没有大小限制时，线程池的最大线程数设置是无效的，他的线程数最多不会超过核心线程数。
 如果线程数量>核心线程数，并且>最大线程数，当任务队列是SynchronousQueue的时候，会因为线程池拒绝添加任务而抛出异常。
 任务队列大小有限时

 当LinkedBlockingDeque塞满时，新增的任务会直接创建新线程来执行，当创建的线程数量超过最大线程数量时会抛异常。
 SynchronousQueue没有数量限制。因为他根本不保持这些任务，而是直接交给线程池去执行。当任务数量超过最大线程数时会直接抛异常。


 * 对线程池进行管理和封装
 *
 *
 * @author yindalei
 */
public class ThreadPoolUtil {

    private static ThreadPoolUtil mInstance = new ThreadPoolUtil();
    private ThreadPoolExecutor executor;

    /**
     * 核心线程池数量，表示能够同时执行的任务数量
     */
    private int corePoolSize;
    /**
     * 最大线程池数量，其实是包含了核心线程池数量在内的,
     * 超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
     */
    private int maximumPoolSize;

    /**
     * 存活时间,表示最大线程池中等待任务的存活时间
     */
    private long     keepAliveTime = 1;
    /**
     * 指定keepAliveTime的单位，如TimeUnit.SECONDS。
     * 当将allowCoreThreadTimeOut设置为true时对corePoolSize生效。
     */
    private TimeUnit unit          = TimeUnit.HOURS;

    public static ThreadPoolUtil getInstance() {
        return mInstance;
    }

    @SuppressWarnings("AlibabaThreadShouldSetName")
    private ThreadPoolUtil() {
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
