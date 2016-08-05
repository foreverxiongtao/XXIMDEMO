package com.xuxian.xximdemo.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 *
 *
 * 版 权 :@Copyright bear版权所有
 *
 * 作 者 :bear
 *
 * 版 本 :1.0
 *
 * 创建日期 :16/1/4  下午5:38
 *
 * 描 述 :创建一个线程池的管理，主要主要创建一个线程池，用来处理异步的任务
 *
 * 修订日期 :
 */
public class ThreadManager {
	private static final ThreadManager threadManager = new ThreadManager();

	// 第一个参数表示线程池中可以执行的线程数，第二个参数表示如果超过请求队列后，还追加的一个线程数，第三个参数表示线程池可以存活的时间，第四个参数表示线程池存时间的模式，最后一个参数表示请求等待的队列最大的个数
	private ThreadPoolExecutor tpeLong;
	private ThreadPoolExecutor tpeshort;

	private ThreadManager() {

	}

	public static ThreadManager newInstance() {
		return threadManager;
	}

	// 创建一个处理耗时长任务的方法
	public void executeLongTask(Runnable runnable) {
		if (null == tpeLong) {
			tpeLong = new ThreadPoolExecutor(3, 3, 1000, TimeUnit.MILLISECONDS,
					new LinkedBlockingQueue<Runnable>(10));
		}
		tpeLong.execute(runnable);
	}

	// 创建一个处理耗时短任务的方法
	public void executeShortTack(Runnable runnable) {
		if (null == tpeshort) {
			tpeshort = new ThreadPoolExecutor(3, 3, 1000,
					TimeUnit.MICROSECONDS,
					new LinkedBlockingQueue<Runnable>(10));
		}
		tpeshort.execute(runnable);
	}

	// 取消一个耗时长的任务
	public void cancelLongTask(Runnable runnable) {
		// 当前的线程不为空，并且当前的线程池不为空也没有关闭
		if (null != runnable && null != tpeLong && !tpeLong.isShutdown()) {
			tpeLong.remove(runnable);
		}
	}

	// 取消一个耗时短的任务
	public void cancelShortTask(Runnable runnable) {
		if (null != runnable && null != tpeshort && !tpeshort.isShutdown()) {
			tpeshort.remove(runnable);
		}
	}
}
