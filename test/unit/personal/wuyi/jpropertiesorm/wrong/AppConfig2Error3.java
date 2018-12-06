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

import personal.wuyi.jpropertiesorm.annotation.PathX;
import personal.wuyi.jpropertiesorm.annotation.ValueX;
import personal.wuyi.jpropertiesorm.core.ConfigurationX;

public class AppConfig2Error3 {
	@PathX                  private Integer path;     // the field is not String
	
	@ValueX("app.host")     private String host;
	@ValueX("app.api_key")  private String apiKey;
	@ValueX("app.username") private String username;
	@ValueX("app.password") private String password;
	
	public AppConfig2Error3(String path) {
		
	}
	
	public AppConfig2Error3 initialize() throws IllegalArgumentException, IllegalAccessException, IOException {
		return ConfigurationX.bindExternalConfigurationWithInstanceFieldsUsingPathX(this);
	}

	public String getHost()                    { return host;                        }
	public void   setHost(String host)         { this.host = host;                   }
	public String getApiKey()                  { return apiKey;                      }
	public void   setApiKey(String apiKey)     { this.apiKey = apiKey;               }
	public String getUsername()                { return username;                    }
	public void   setUsername(String username) { this.username = username;           }
	public String getPassword()                { return password;                    }
	public void   setPassword(String password) { this.password = password;           }
	public String getPath()                    { return path.toString();             }
	public void   setPath(String path)         { this.path = Integer.parseInt(path); }
}
