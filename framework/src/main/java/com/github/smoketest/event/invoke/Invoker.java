package com.github.smoketest.event.invoke;

import com.github.smoketest.util.testNG.log.EventLog;
import com.github.smoketest.util.testNG.log.Logger;

/**
 * <p>Event's invoker, it can execute selenium actions while recording log automatically</p>
 * <p>e.g.</p>
 * {@code Invoker.i(InputTextEvent::sendKeys, By.id("username"), username);}
 * @author uniqueT
 *
 */
public class Invoker {
	
	/**
	 * Non parameter event
	 * @param f function name
	 * @throws RuntimeException most of selenium's Exception is RuntimeException
	 */
	public static void i(NonParaInvoker f) throws RuntimeException {
		EventLog log = null;
		try {
			log = f.i();
		}catch(RuntimeException e) {
			throw e;
		}finally{
			Logger.eventLog(log);
		}
		
	}
	
	/**
	 * One parameter event
	 * @param f function name
	 * @param param parameter 1
	 * @throws RuntimeException
	 */
	public static <T> void i(OneParaInvoker<T> f, T param) throws RuntimeException {
		EventLog log = null;
		try {
			log = f.i(param);
		}catch(RuntimeException e) {
			throw e;
		}finally{
			Logger.eventLog(log);
		}
	}
	
	/**
	 * Two parameters event
	 * @param f function name
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @throws RuntimeException
	 */
	public static <T, V> void i(TwoParaInvoker<T, V> f, T param1, V param2) throws RuntimeException {
		EventLog log = null;
		try {
			log = f.i(param1, param2);
		}catch(RuntimeException e) {
			throw e;
		}finally{
			Logger.eventLog(log);
		}
	}

}
