package com.github.smoketest.event.invoke;

import com.github.smoketest.util.testNG.log.EventLog;

/**
 * function interface, implicit non parameter function
 * @author uniqueT
 *
 */
@FunctionalInterface
public interface NonParaInvoker {
	
	/**
	 * @return event log
	 */
	public EventLog i();

}
