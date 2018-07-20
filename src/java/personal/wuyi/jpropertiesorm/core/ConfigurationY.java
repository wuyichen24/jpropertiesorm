package personal.wuyi.jpropertiesorm.core;

import java.util.Set;

import personal.wuyi.reflect.ReflectUtil;

/**
 * More intelligent way for handing the parameter mapping between Java class 
 * and the external configuration file
 * 
 * <p>This class is only for the solution of letting the configuration class 
 * extend this class. So that other solutions will not trigger the static 
 * block of the configurationX class.
 * 
 * @author  Wuyi Chen
 * @date    12/27/2017
 * @version 1.1
 * @since   1.1
 */
public class ConfigurationY extends ConfigurationX {
	/*
	 * All the sub-class of this class will be trigger this static block. It 
	 * will automatically detect all its sub-classes and handle the 
	 * configuration mapping between Java class and the external configuration 
	 * file.
	 **/
	static {
		try {
			Set<Class<?>> classSet = ReflectUtil.getSubTypesOf(ConfigurationX.class);
			for (Class<?> classs : classSet) {
				bindExternalConfigurationWithStaticFields(classs);
			}
		} catch (Exception e) {
			// This is one drawback of this solution.
		}
	}
}
