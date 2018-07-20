package personal.wuyi.jpropertiesorm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation at the field level that indicates a parameter name in the 
 * external configuration file.
 * 
 * @author  Wuyi Chen
 * @date    12/27/2017
 * @version 1.1
 * @since   1.1
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValueX {
	String value();
}
