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

import personal.wuyi.jpropertiesorm.annotation.PropertySourceX;
import personal.wuyi.jpropertiesorm.annotation.ValueX;
import personal.wuyi.jpropertiesorm.core.ConfigurationX;

/**
 * Application configuration class example
 * 
 * <p>This class is an example to show the binding between the instance 
 * members in java class and the parameters in the external configuration file 
 * by annotation.
 * 
 * <p>This class will use the static block to trigger the operation of 
 * grabbing the parameters from the external configuration file and populate 
 * them into the static fields of this class. 
 * 
 * <p>
 * This solution is similar to the AppConfig3, and it has some drawbacks:
 * 
 * <ul>
 * <li>Any exception in static block can not be thrown out. So you have to put
 * try-catch block in that static block. Because of the process is in static
 * phrase, so that it may not be logged if there is any exception in that
 * process.
 * <li>This class can only map to one external configuration.
 * </ul>
 * 
 * @author  Wuyi Chen
 * @date    12/27/2017
 * @version 1.1
 * @since   1.1
 */
@PropertySourceX({"config/app.properties"})
public class AppConfig4 {
	static {
		try {
			ConfigurationX.bindExternalConfigurationWithStaticFields(AppConfig4.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ValueX("app.host")
	static String host;
	
	@ValueX("app.api_key")
	static String apiKey;
	
	@ValueX("app.username")
	static String username;
	
	@ValueX("app.password")
	static String password;
}
