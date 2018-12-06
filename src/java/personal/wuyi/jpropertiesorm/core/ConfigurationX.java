/*
 * Copyright 2018 Wuyi Chen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package personal.wuyi.jpropertiesorm.core;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.commons.lang3.reflect.FieldUtils;

import personal.wuyi.io.file.properties.PropertiesUtil;
import personal.wuyi.jpropertiesorm.annotation.PathX;
import personal.wuyi.jpropertiesorm.annotation.PropertySourceX;
import personal.wuyi.jpropertiesorm.annotation.ValueX;
import personal.wuyi.reflect.ReflectUtil;

/**
 * More intelligent way for handing the parameter mapping between Java class 
 * and the external configuration file.
 * 
 * <p>This class simulate the style of Spring framework for handling the 
 * application configuration file. But this solution is light-weight without 
 * any extra driver class.
 * 
 * <p>You can create a Java class for wrapping configuration parameters. Use 
 * the {@code PropertySourceX} annotation to mark this configuration class and 
 * specify the path of the external configuration file. Use the {@code ValueX} 
 * to mark the fields and specify the name of the parameters in the external 
 * configuration file for grabbing values.
 * 
 * <p>This class support different ways for the configuration mapping. The 
 * Java class can inherit this class or call the functions of this class 
 * directly.
 * 
 * @author  Wuyi Chen
 * @date    12/27/2017
 * @version 1.1
 * @since   1.1
 */
public class ConfigurationX {
	protected ConfigurationX() {}
	
	/**
	 * Bind the parameters in the external configuration with the instance 
	 * fields in the Java class by {@code PropertySourceX} annotation.
	 * 
	 * <p>This function will use the path in the {@code PropertySourceX} 
	 * annotation which annotated the Java class and use the name in that 
	 * annotation to specify the path of the external configuration file.
	 * 
	 * <p>This class will handle the one-to-one mapping between the instance 
	 * fields with {@code ValueX} annotation in the Java configuration class 
	 * and the parameters in the external configuration file. 
	 * 
	 * <p>This class will update each annotated fields of an existing object 
	 * with the parameters from the configuration file.
	 * 
	 * @param  clazz
	 *         The Java class for the configuration mapping.
	 *         
	 * @return  The instance of clazz.
	 * 
	 * @throws  IllegalAccessException
	 *          If a certain field is enforcing Java language access control 
	 *          and the underlying field is either inaccessible or final.
	 * 
	 * @throws  FileNotFoundException 
	 *          If the external configuration file does not exist, is a 
	 *          directory rather than a regular file, or for some other reason 
	 *          cannot be opened for reading.
	 *          
	 * @since   1.1
	 */
	public static <T> T bindExternalConfigurationWithInstanceFieldsUsingPropertySourceX(final T t) throws IllegalAccessException, FileNotFoundException  {
		final Class<?> clazz = t.getClass();
		final String configFilePath = getConfigFilePathPathByPropertySourceXAnnotation(clazz);
		final Properties properties = PropertiesUtil.getProperties(configFilePath) ;
		if (properties == null) {
			throw new FileNotFoundException(configFilePath + " is not existing");
		}
		
		return populateInstanceFieldsWithProperties(t, properties, configFilePath);
	}
	
	/**
	 * Bind the parameters in the external configuration with the instance 
	 * fields in the Java class by {@code PathX} annotation.
	 * 
	 * <p>The function will use the value of the field which annotated by 
	 * {@code PathX} annotation to specify the path of the external 
	 * configuration file.
	 * 
	 * <p>This class will handle the one-to-one mapping between the instance 
	 * fields with {@code ValueX} annotation in the Java configuration class 
	 * and the parameters in the external configuration file. 
	 * 
	 * <p>This class will update each annotated fields of an existing object 
	 * with the parameters from the configuration file.
	 * 
	 * @param  t
	 *         The object of the class for the configuration mapping.
	 *         
	 * @return  The update object with external parameters.
	 * 
	 * @throws  IllegalAccessException
	 *          If a certain field is enforcing Java language access control 
	 *          and the underlying field is either inaccessible or final.
	 * 
	 * @throws  FileNotFoundException 
	 *          If the external configuration file does not exist, is a 
	 *          directory rather than a regular file, or for some other reason 
	 *          cannot be opened for reading.
	 *          
	 * @since   1.1
	 */
	public static <T> T bindExternalConfigurationWithInstanceFieldsUsingPathX(final T t) throws IllegalAccessException, FileNotFoundException  {
		final String     configFilePath = getConfigFilePathByPathXAnnotation(t);
		final Properties properties     = PropertiesUtil.getProperties(configFilePath);
		if (properties == null) {
			throw new FileNotFoundException(configFilePath + " is not existing");
		}
		
		return populateInstanceFieldsWithProperties(t, properties, configFilePath);
	}
	
	/**
	 * Bind the parameters in the external configuration with the static 
	 * fields in the Java class.
	 * 
	 * <p>This function will use the path in the {@code PropertySourceX} 
	 * annotation which annotated the Java class and use the name in that 
	 * annotation to specify the path of the external configuration file.
	 * 
	 * <p>This class will handle the one-to-one mapping between the static 
	 * fields with {@code ValueX} annotation in the Java configuration class 
	 * and the parameters in the external configuration file.
	 * 
	 * <p>This class will update the annotated static fields directly with the 
	 * parameters from the configuration file.
	 * 
	 * @param  clazz
	 *         The Java class for the configuration mapping.
	 * 
	 * @throws  IllegalAccessException
	 *          If a certain field is enforcing Java language access control 
	 *          and the underlying field is either inaccessible or final.
	 * 
	 * @throws  FileNotFoundException 
	 *          If the external configuration file does not exist, is a 
	 *          directory rather than a regular file, or for some other reason 
	 *          cannot be opened for reading.
	 *          
	 * @since   1.1
	 */
	public static void bindExternalConfigurationWithStaticFields(final Class<?> clazz) throws IllegalAccessException, FileNotFoundException  {
		final String     configFilePath = getConfigFilePathPathByPropertySourceXAnnotation(clazz);
		final Properties properties     = PropertiesUtil.getProperties(configFilePath) ;
		if (properties == null) {
			throw new FileNotFoundException(configFilePath + " is not existing");
		}
		
		populateStaticFieldsWithProperties(clazz, properties, configFilePath);
	}
	
	/**
	 * Get the path of the external configuration file by {@code PathX} 
	 * annotation.
	 * 
	 * @param  t
	 *         The object of the class for the configuration mapping.
	 *         
	 * @return  The path of the external configuration file.
	 * 
	 * @throws  IllegalAccessException
	 *          If a certain field is enforcing Java language access control 
	 *          and the underlying field is either inaccessible or final.
	 *          
	 * @since   1.1
	 */
	public static <T> String getConfigFilePathByPathXAnnotation(final T t) throws IllegalAccessException {
		final Class<?> clazz = t.getClass();
		final Field[] pathFields = FieldUtils.getFieldsWithAnnotation(clazz, PathX.class);
		validatePathXInConfigClass(clazz, pathFields);
		pathFields[0].setAccessible(true);
		return (String) pathFields[0].get(t);
	}
	
	/**
	 * Get the path of the external configuration file by 
	 * {@code PropertySourceX}.
	 * 
	 * @param  clazz
	 *         The Java class for the configuration mapping.
	 *         
	 * @return  The path of the external configuration file.
	 * 
	 * @since   1.1
	 */
	public static String getConfigFilePathPathByPropertySourceXAnnotation(final Class<?> clazz) {
		final PropertySourceX propertiesSourceX = clazz.getAnnotation(PropertySourceX.class);
		validatePropertySourceXInConfigClass(clazz, propertiesSourceX);
		return propertiesSourceX.value()[0];
	}
	
	/**
	 * Populate the instance fields (marked with the {@code ValueX} 
	 * annotation) with the parameters in the external configuration file.
	 * 
	 * @param  t
	 *         The object of the class for the configuration mapping.
	 *         
	 * @param  properties
	 *         The Properties object which contains the parameters in the 
	 *         external configuration file.
	 *         
	 * @param  configFilePath
	 *         The path of the external configuration file.
	 *         
	 * @return  The updated object of the class for the configuration mapping.
	 * 
	 * @throws  IllegalAccessException
	 *          If a certain field is enforcing Java language access control 
	 *          and the underlying field is either inaccessible or final.
	 *          
	 * @since   1.1
	 */
	public static <T> T populateInstanceFieldsWithProperties(T t, Properties properties, String configFilePath) throws IllegalAccessException {
		final Field[] fields = t.getClass().getDeclaredFields();
		
		for(int i = 0; (fields != null && i < fields.length); i++) {
            final ValueX valueX = fields[i].getAnnotation(ValueX.class);
            if (valueX == null) {
            	continue;
            }
            
            fields[i].setAccessible(true);
            if (ReflectUtil.isStaticField(fields[i])) {
            	throw new IllegalArgumentException(fields[i].getName() + " field can not be static.");
            } else if (properties.getProperty(valueX.value()) == null) {
            	throw new IllegalArgumentException(configFilePath + " is missing parameter: " + valueX.value());
            } else {
            	fields[i].set(t, properties.getProperty(valueX.value()));
            }
		}
		
		return t;
	}
	
	/**
	 * Populate the static fields (marked with the {@code ValueX} annotation) 
	 * with the parameters in the external configuration file.
	 * 
	 * @param  clazz
	 *         The Java class for the configuration mapping.
	 *         
	 * @param  properties
	 *         The Properties object which contains the parameters in the 
	 *         external configuration file.
	 *         
	 * @param  configFilePath
	 *         The path of the external configuration file.
	 *          
	 * @throws  IllegalAccessException
	 *          If the clazz and its fields are missing proper annotations.
	 *          
	 * @since   1.1
	 */
	public static void populateStaticFieldsWithProperties(Class<?> clazz, Properties properties, String configFilePath) throws IllegalAccessException {
		final Field[] fields = clazz.getDeclaredFields();
		
		for(int i = 0; (fields != null && i < fields.length); i++) {
            final ValueX valueX   = fields[i].getAnnotation(ValueX.class);
            if (valueX == null) {
            	continue;
            }
            
            fields[i].setAccessible(true);
            if (!ReflectUtil.isStaticField(fields[i])) {
            	throw new IllegalArgumentException(fields[i].getName() + " field can not be non-static.");
            } else if (properties.getProperty(valueX.value()) == null) {
            	throw new IllegalArgumentException(configFilePath + " is missing parameter: " + valueX.value());
            } else {
            	fields[i].set(clazz, properties.getProperty(valueX.value()));
            }
		}
	}
	
	
	/**
	 * Verify the {@code PropertySourceX} annotation in the config class.
	 * 
	 * @param  clazz
	 *         The Java class for the configuration mapping.
	 *         
	 * @param  propertiesSourceX
	 *         The annotation object for recognizing the configuration mapping 
	 *         class.
	 *          
	 * @since   1.1
	 */
	public static void validatePropertySourceXInConfigClass(final Class<?> clazz, final PropertySourceX propertiesSourceX) {
		if (propertiesSourceX == null) {
			throw new IllegalArgumentException(clazz.getSimpleName() + " needs to have " + PropertySourceX.class.getSimpleName() + " annotation.");
		}
		if (propertiesSourceX.value().length == 0) {
			throw new IllegalArgumentException("The " + PropertySourceX.class.getSimpleName() + " ann0tation in " + clazz.getSimpleName() + " class doesn't have proper values.");
		}
	}
	
	/**
	 * Verify the {@code PathX} annotation in the config class.
	 * 
	 * @param  clazz
	 *         The Java class for configuration mapping.
	 * 
	 * @param  pathFields
	 *         The array of {@code Field} object which was annotated by 
	 *         {@code PathX}.
	 *          
	 * @since   1.1
	 */
	public static void validatePathXInConfigClass (final Class<?> clazz, final Field[] pathFields) {
		if (pathFields == null) {
			throw new IllegalArgumentException(clazz.getSimpleName() + " needs to have " + PathX.class.getSimpleName() + " anntation.");
		}
		if (pathFields.length == 0) {
			throw new IllegalArgumentException(clazz.getSimpleName() + " needs to have " + PathX.class.getSimpleName() + " anntation.");
		}
		if (pathFields.length > 1) {
			throw new IllegalArgumentException(clazz.getSimpleName() + " needs to have only one " + PathX.class.getSimpleName() + " anntation.");
		}
		if (pathFields.length == 1 && pathFields[0].getType() != String.class) {
			throw new IllegalArgumentException("The field marked by " + PathX.class.getSimpleName() + " annotation needs to be String.");
		}
	}
}
