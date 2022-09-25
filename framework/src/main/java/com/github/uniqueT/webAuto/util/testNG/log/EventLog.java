package com.github.uniqueT.webAuto.util.testNG.log;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.github.uniqueT.webAuto.event.BaseEvent;

/**
 * <p>event's function must return this. Must be unidealized by static methods in this class</p>
 * <p>Only can create instance in event's class</p>
 * @author uniqueT
 *
 */
public class EventLog {
	
	private String eventName;
	private String by;
	private String methodName;
	private String value;
	
	private EventLog() {
		
	}
	
	/**
	 * according to By and value to create a log
	 * @param by
	 * @param _value
	 * @return
	 */
	public static EventLog getInstance(By by, String _value) {
		EventLog eventLog = new EventLog();
		String[] result = {null, null};
		try {
			StackTraceElement[] stack = Thread.currentThread().getStackTrace();
			result = getClassAndMethod(stack);
		}catch(ReflectiveOperationException e) {
			Reporter.log(e.getMessage());
			return null;
		}
		eventLog.eventName = result[0];
		eventLog.methodName = result[1];
		eventLog.by = by.toString();
		eventLog.value = _value;
		return eventLog;
	}
	
	/**
	 * according to By to create a log
	 * @param by
	 * @return
	 */
	public static EventLog getInstance(By by) {
		return getInstance(by, " ");
	}
	
	private static String[] getClassAndMethod(StackTraceElement[] stack) throws ReflectiveOperationException {
		String[] result = new String[2];
		for(StackTraceElement s : stack) {
		    Class<?> c = Class.forName(s.getClassName());
		    if(BaseEvent.class.isAssignableFrom(c)) {
		    	result[0] = c.getSimpleName();
		    	result[1] = s.getMethodName();
		    	return result;
		    }
		}
		throw new ReflectiveOperationException("can't find event class in current thread stacks\n"
				+ "don't use EventLog.getInstance outside of event class");
	}
	
	/**
	 * get event's name
	 * @return event's name
	 */
	public String getEventName() {
		return this.eventName;
	}
	
	/**
	 * get By
	 * @return By
	 */
	public String getBy() {
		return this.by;
	}
	
	/**
	 * get event's method
	 * @return method's name
	 */
	public String getmethodName() {
		return this.methodName;
	}
	
	/**
	 * get value
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * <p>In some events that don't use selenium By, use this method to create a log</p>
	 * <p>In report is looks like {@link Logger#stringLog(String)}, but has an extra event's value</p>
	 * @param customStr a simple message
	 * @return log
	 */
	public static EventLog getInstance(String customStr) {
		EventLog eventLog = new EventLog();
		String[] result = {null, null};
		try {
			StackTraceElement[] stack = Thread.currentThread().getStackTrace();
			result = getClassAndMethod(stack);
		}catch(ReflectiveOperationException e) {
			Reporter.log(e.getMessage());
			return null;
		}
		eventLog.eventName = result[0];
		eventLog.methodName = result[1];
		eventLog.by = customStr;
		eventLog.value = " ";
		return eventLog;
	}

}
