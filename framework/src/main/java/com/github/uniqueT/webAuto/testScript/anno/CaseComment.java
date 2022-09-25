package com.github.uniqueT.webAuto.testScript.anno;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * comments for test cases. Test cases without this annotation will diaplay their full class name in the report
 * @author uniqueT
 *
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface CaseComment {
	/**
	 * comments for test cases
	 * @return comments
	 */
	String value();
}
