package com.github.uniqueT.webAuto.event.invoke;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import com.github.uniqueT.webAuto.event.WindowEvent;
import com.github.uniqueT.webAuto.event.FrameEvent;
import com.github.uniqueT.webAuto.util.CommonUtil;
import com.github.uniqueT.webAuto.util.testNG.log.CaseLog;
import com.github.uniqueT.webAuto.util.testNG.log.EventLog;
import com.github.uniqueT.webAuto.util.testNG.log.Logger;

/**
 * <p>Event's and Case's invoker, it can execute selenium actions while recording log automatically</p>
 * <p>e.g.</p>
 * {@code Invoker.i(InputTextEvent::sendKeys, By.id("username"), username);}
 * <p>In scripts, please use wrapped method in {@linkplain com.github.uniqueT.webAuto.testScript.AbstractTestCase AbstractTestCase}</p>
 * @author uniqueT
 *
 */
public class Invoker {
	
	/**
	 * Non parameter event
	 * @param f function name
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static void event(Supplier<EventLog> f) throws RuntimeException {
		event(f, 0, false);
	}
	
	/**
	 * Non parameter event
	 * @param f function name
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static void event(Supplier<EventLog> f, int wait) throws RuntimeException {
		event(f, wait, false);
	}
	
	/**
	 * Non parameter event
	 * @param f function name
	 * @param needScreenShot if true, take a screenshot after this step
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static void event(Supplier<EventLog> f, boolean needScreenShot) throws RuntimeException {
		event(f, 0, needScreenShot);
	}
	
	/**
	 * Non parameter event
	 * @param f function name
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 * @param needScreenShot if true, take a screenshot after this step
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static void event(Supplier<EventLog> f, int wait, boolean needScreenShot) throws RuntimeException {
		EventLog log = null;
		try {
			log = f.get();
		}catch(RuntimeException e) {
			throw e;
		}finally{
			finalStep(log, wait, needScreenShot);
		}
	}
	
	private static void finalStep(EventLog log, int wait, boolean needScreenShot) {
		Logger.eventLog(log);
		if(wait > 0) {
			try {
				Thread.sleep(wait*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(needScreenShot) {
			CommonUtil.takeScreenShot();
		}
	}
	
	/**
	 * One parameter event
	 * @param f function name
	 * @param param parameter 1
	 * @throws RuntimeException
	 */
	public static <T> void event(Function<T, EventLog> f, T param) throws RuntimeException {
		event(f, param, 0, false);
	}
	
	/**
	 * One parameter event
	 * @param f function name
	 * @param param parameter 1
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 * @throws RuntimeException
	 */
	public static <T> void event(Function<T, EventLog> f, T param, int wait) throws RuntimeException {
		event(f, param, wait, false);
	}
	
	/**
	 * One parameter event
	 * @param f function name
	 * @param param parameter 1
	 * @param needScreenShot if true, take a screenshot after this step
	 * @throws RuntimeException
	 */
	public static <T> void event(Function<T, EventLog> f, T param, boolean needScreenShot) throws RuntimeException {
		event(f, param, 0, needScreenShot);
	}
	
	/**
	 * One parameter event
	 * @param f function name
	 * @param param parameter 1
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 * @param needScreenShot if true, take a screenshot after this step
	 * @throws RuntimeException
	 */
	public static <T> void event(Function<T, EventLog> f, T param, int wait, boolean needScreenShot) throws RuntimeException {
		EventLog log = null;
		try {
			log = f.apply(param);
		}catch(RuntimeException e) {
			throw e;
		}finally{
			finalStep(log, wait, needScreenShot);
		}
	}
	
	/**
	 * Two parameters event
	 * @param f function name
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @throws RuntimeException
	 */
	public static <T, V> void event(BiFunction<T, V, EventLog> f, T param1, V param2) throws RuntimeException {
		event(f, param1, param2, 0, false);
	}
	
	/**
	 * Two parameters event
	 * @param f function name
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 * @throws RuntimeException
	 */
	public static <T, V> void event(BiFunction<T, V, EventLog> f, T param1, V param2, int wait) throws RuntimeException {
		event(f, param1, param2, wait, false);
	}
	
	/**
	 * Two parameters event
	 * @param f function name
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param needScreenShot if true, take a screenshot after this step
	 * @throws RuntimeException
	 */
	public static <T, V> void event(BiFunction<T, V, EventLog> f, T param1, V param2, boolean needScreenShot) throws RuntimeException {
		event(f, param1, param2, 0, needScreenShot);
	}
	
	/**
	 * Two parameters event
	 * @param f function name
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 * @param needScreenShot if true, take a screenshot after this step
	 * @throws RuntimeException
	 */
	public static <T, V> void event(BiFunction<T, V, EventLog> f, T param1, V param2, int wait, boolean needScreenShot) throws RuntimeException {
		EventLog log = null;
		try {
			log = f.apply(param1, param2);
		}catch(RuntimeException e) {
			throw e;
		}finally{
			finalStep(log, wait, needScreenShot);
		}
	}
	
	/**
	 * invoke non parameter case
	 * @param f case method
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static void ca5e(CaseRunnable f) throws RuntimeException {
		ca5e(f, null, null);
	}
	
	/**
	 * invoke non parameter case
	 * @param f case method
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static void ca5e(CaseRunnable f, String frame) throws RuntimeException {
		ca5e(f, frame, null);
	}
	
	/**
	 * invoke non parameter case
	 * @param f case method
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 * @param window if not null, change to window with this <b>window</b> title before executing steps
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static void ca5e(CaseRunnable f, String frame, String window) throws RuntimeException {
		CaseLog log = f.getCaseLog();
		long caseId = Logger.caseLog(log);
		long time = System.currentTimeMillis();
		changeFrameWindow(frame, window);
		try {
			f.run();
		}catch(RuntimeException e) {
			throw e;
		}finally{
			float costTime = (float)(System.currentTimeMillis() - time)/1000;
			Logger.caseEndLog(caseId, costTime);
		}
	}
	
	private static void changeFrameWindow(String frame, String window) {
		if(window!=null && !"".equals(window)) {
			event(WindowEvent::changeTo, window);
		}
		if(frame!=null && !"".equals(frame)) {
			event(FrameEvent::toFrame, frame);
		}
	}
	
	/**
	 * invoke one parameter case
	 * @param f case method
	 * @param param parameter 1
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static <T> void ca5e(CaseConsumer<T> f, T param) throws RuntimeException {
		ca5e(f, param, null, null);
	}
	
	/**
	 * invoke one parameter case
	 * @param f case method
	 * @param param parameter 1
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static <T> void ca5e(CaseConsumer<T> f, T param, String frame) throws RuntimeException {
		ca5e(f, param, frame, null);
	}
	
	/**
	 * invoke one parameter case
	 * @param f case method
	 * @param param parameter 1
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 * @param window if not null, change to window with this <b>window</b> title before executing steps
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static <T> void ca5e(CaseConsumer<T> f, T param, String frame, String window) throws RuntimeException {
		CaseLog log = f.getCaseLog(param.getClass());
		long caseId = Logger.caseLog(log);
		long time = System.currentTimeMillis();
		changeFrameWindow(frame, window);
		try {
			f.accept(param);
		}catch(RuntimeException e) {
			throw e;
		}finally{
			float costTime = (float)(System.currentTimeMillis() - time)/1000;
			Logger.caseEndLog(caseId, costTime);
		}
	}
	
	/**
	 * invoke two parameters case
	 * @param f case method
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static <T, V> void ca5e(CaseBiConsumer<T, V> f, T param1, V param2) throws RuntimeException {
		ca5e(f, param1, param2, null, null);
	}
	
	/**
	 * invoke two parameters case
	 * @param f case method
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static <T, V> void ca5e(CaseBiConsumer<T, V> f, T param1, V param2, String frame) throws RuntimeException {
		ca5e(f, param1, param2, frame, null);
	}
	
	/**
	 * invoke two parameters case
	 * @param f case method
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 * @param window if not null, change to window with this <b>window</b> title before executing steps
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static <T, V> void ca5e(CaseBiConsumer<T, V> f, T param1, V param2, String frame, String window) throws RuntimeException {
		CaseLog log = f.getCaseLog(param1.getClass(), param2.getClass());
		long caseId = Logger.caseLog(log);
		long time = System.currentTimeMillis();
		changeFrameWindow(frame, window);
		try {
			f.accept(param1, param2);
		}catch(RuntimeException e) {
			throw e;
		}finally{
			float costTime = (float)(System.currentTimeMillis() - time)/1000;
			Logger.caseEndLog(caseId, costTime);
		}
	}

}
