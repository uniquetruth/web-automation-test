package com.github.uniqueT.webAuto.event.invoke;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;

import com.github.uniqueT.webAuto.testScript.anno.CaseComment;
import com.github.uniqueT.webAuto.util.testNG.log.CaseLog;

/**
 * function interface indicates a case method with 2 parameters
 * @author uniqueT
 *
 */
public interface CaseBiConsumer<T, U> extends BiConsumer<T, U>, CaseSerializable {
	
	/**
	 * generate case log to framework
	 * @param class1 the class of parameter1
	 * @param class2 the class of parameter2
	 * @return case log
	 */
	default CaseLog getCaseLog(Class<?> class1, Class<?> class2) {
		try {
			SerializedLambda lambda = getSerializedLambda();
			String className = lambda.getImplClass().replace("/", ".");
	        Class<?> c = Class.forName(className);
	        Method method = c.getMethod(lambda.getImplMethodName(), class1, class2);
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
