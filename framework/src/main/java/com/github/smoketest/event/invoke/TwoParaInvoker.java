package com.github.smoketest.event.invoke;

import com.github.smoketest.util.testNG.log.EventLog;

/**
 * function interface, implicit two parameter2 function
 * @author uniqueT
 *
 * @param <T> type of parameter 1
 * @param <V> type of parameter 2
 */
@FunctionalInterface
public interface TwoParaInvoker<T, V> {
	
	/**
	 * @param t parameter 1
	 * @param v parameter 2
	 * @return event log
	 */
	public EventLog i(T t, V v);

}
