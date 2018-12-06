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

package personal.wuyi.jpropertiesorm.wrong;

import personal.wuyi.jpropertiesorm.annotation.PropertySourceX;
import personal.wuyi.jpropertiesorm.annotation.ValueX;
import personal.wuyi.jpropertiesorm.core.ConfigurationX;

@PropertySourceX({"config/appABC.properties"})   // file doesn't exist
public class AppConfig4Error1 {
	static {
		try {
			ConfigurationX.bindExternalConfigurationWithStaticFields(AppConfig4Error1.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ValueX("app.host")
	public static String host;
	
	@ValueX("app.api_key")
	public static String apiKey;
	
	@ValueX("app.username")
	public static String username;
	
	@ValueX("app.password")
	public static String password;
}
