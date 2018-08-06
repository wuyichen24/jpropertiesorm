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
			Set<Class<?>> classSet = ReflectUtil.getSubTypesOf(ConfigurationY.class);
			for (Class<?> classs : classSet) {
				bindExternalConfigurationWithStaticFields(classs);
			}
		} catch (Exception e) {
			// This is one drawback of this solution.
		}
	}
}
