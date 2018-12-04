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

package personal.wuyi.jpropertiesorm.example;

import personal.wuyi.jpropertiesorm.annotation.PropertySourceX;
import personal.wuyi.jpropertiesorm.annotation.ValueX;
import personal.wuyi.jpropertiesorm.core.ConfigurationY;

/**
 * Application configuration class example
 * 
 * <p>
 * This class is an example to show the binding between the instance members in
 * java class and the parameters in the external configuration file by
 * annotation.
 * 
 * <p>
 * This class is the sub-class of the ConfigurationX class. If the static
 * initialization of this class has been triggered, the static initialization of
 * its super-class (ConfigurationX) would be triggered too. So the operation of
 * grabbing the parameters from the external configuration file and populate
 * them into the static fields of this class will be executed in the static
 * block of the ConfigurationX class. And ConfigurationX will do the same thing
 * for all its sub-classes.
 * 
 * <p>
 * This solution is most concise and the code quantity is least, but it has some
 * drawbacks:
 * 
 * <ul>
 * <li>Any exception in static block can not be thrown out. So you have to put
 * try-catch block in that static block. Because of the process is in static
 * phrase, so that it may not be logged if there is any exception in that
 * process.
 * <li>This class can only map to one external configuration.
 * <li>ConfigurationX will do package scanning and find all the sub-classes of
 * it. The performance will be degraded by this operation.
 * </ul>
 * 
 * @author  Wuyi Chen
 * @date    12/27/2017
 * @version 1.1
 * @since   1.1
 */
@PropertySourceX({ "config/app.properties" })
public class AppConfig3 extends ConfigurationY {
	@ValueX("app.host")
	public static String host;

	@ValueX("app.api_key")
	public static String apiKey;

	@ValueX("app.username")
	public static String username;

	@ValueX("app.password")
	public static String password;
}
