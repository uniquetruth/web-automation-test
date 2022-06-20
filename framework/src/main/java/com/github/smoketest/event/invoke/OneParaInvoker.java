package com.github.smoketest.event.invoke;

import com.github.smoketest.util.testNG.log.EventLog;

/**
 * function interface, implicit one parameter function
 * @author uniqueT
 *
 * @param <T> parameter type
 */
@FunctionalInterface
public interface OneParaInvoker<T> {
	
	/**
	 * @param t parameter
	 * @return event log
	 */
	public EventLog i(T t);

}
