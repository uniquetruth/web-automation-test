package com.github.uniqueT.webAuto.event.invoke;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

import com.github.uniqueT.webAuto.testScript.anno.CaseComment;
import com.github.uniqueT.webAuto.util.testNG.log.CaseLog;

/**
 * function interface indicates a case method without parameters
 * @author uniqueT
 *
 */
public interface CaseRunnable extends Runnable, CaseSerializable {
	
	/**
	 * generate case log to framework
	 * @return case log
	 */
	default CaseLog getCaseLog() {
		try {
			SerializedLambda lambda = getSerializedLambda();
			String className = lambda.getImplClass().replace("/", ".");
	        Class<?> c = Class.forName(className);
	        Method method = c.getMethod(lambda.getImplMethodName());
	        CaseLog log = CaseLog.getInstance(className+"."+method.getName());
	        if(method.isAnnotationPresent(CaseComment.class)) {
	        	log.setCaseComment(method.getAnnotation(CaseComment.class).value());
	        }
			return log;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CaseLog.getInstance("fail to get case info, use --debug to see full log in console");
	}
	
}
