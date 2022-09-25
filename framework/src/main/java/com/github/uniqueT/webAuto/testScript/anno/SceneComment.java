package com.github.uniqueT.webAuto.testScript.anno;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * comments for test scenes. Test scenes without this annotation will diaplay their full class name in the report<br>
 * About the parameter <b>scenes</b> in rum.xml, you can use &#64;commentVaue to indicate a scene.
 * @author uniqueT
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface SceneComment {
	
	/**
	 * comments for test scene
	 * @return comments
	 */
	String value();

}
