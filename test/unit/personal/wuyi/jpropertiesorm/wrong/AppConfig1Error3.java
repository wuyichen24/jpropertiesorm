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

import java.io.IOException;

import personal.wuyi.jpropertiesorm.annotation.PropertySourceX;
import personal.wuyi.jpropertiesorm.annotation.ValueX;
import personal.wuyi.jpropertiesorm.core.ConfigurationX;

@PropertySourceX({"config/app.properties"}) 
public class AppConfig1Error3 {
	@ValueX("app.host")        private String host;
	@ValueX("app.api_key")     private String apiKey;
	@ValueX("app.orgnization") private String orgnization;	 // this parameter is not existing in the properties file
	@ValueX("app.password")    private String password;
	
	public AppConfig1Error3 initialize() throws IllegalArgumentException, IllegalAccessException, IOException {
		return ConfigurationX.bindExternalConfigurationWithInstanceFieldsUsingPropertySourceX(this);
	}
	
	public String getHost()                       { return host;                 }
	public void   setHost(String host)            { this.host = host;            }
	public String getApiKey()                     { return apiKey;               }
	public void   setApiKey(String apiKey)        { this.apiKey = apiKey;        }
	public String getOrgnization()                { return orgnization;          }
	public void   setOrgnization(String username) { this.orgnization = username; }
	public String getPassword()                   { return password;             }
	public void   setPassword(String password)    { this.password = password;    }
}
