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

public class AppConfig2Error2 {
	@PathX                  private String path1;     // more than one PathX annotation
	@PathX                  private String path2;     // more than one PathX annotation
	
	@ValueX("app.host")     private String host;
	@ValueX("app.api_key")  private String apiKey;
	@ValueX("app.username") private String username;
	@ValueX("app.password") private String password;
	
	public AppConfig2Error2(String path) {
		this.path1 = path;
		this.path2 = path;
	}
	
	public AppConfig2Error2 initialize() throws IllegalArgumentException, IllegalAccessException, IOException {
		return ConfigurationX.bindExternalConfigurationWithInstanceFieldsUsingPathX(this);
	}

	public String getHost()                    { return host;              }
	public void   setHost(String host)         { this.host = host;         }
	public String getApiKey()                  { return apiKey;            }
	public void   setApiKey(String apiKey)     { this.apiKey = apiKey;     }
	public String getUsername()                { return username;          }
	public void   setUsername(String username) { this.username = username; }
	public String getPassword()                { return password;          }
	public void   setPassword(String password) { this.password = password; }
	public String getPath1()                   { return path1;             }
	public void   setPath1(String path1)       { this.path1 = path1;       }
	public String getPath2()                   { return path2;             }
	public void   setPath2(String path2)       { this.path2 = path2;       }
}
