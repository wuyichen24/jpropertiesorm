package personal.wuyi.jpropertiesorm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation at the field level that indicates which field store the path of 
 * the external configuration file.
 * 
 * @author  Wuyi Chen
 * @date    02/26/2017
 * @version 1.1
 * @since   1.1
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathX {
}
