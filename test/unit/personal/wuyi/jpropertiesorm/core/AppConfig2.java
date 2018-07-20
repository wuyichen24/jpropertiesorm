package personal.wuyi.jpropertiesorm.core;

import java.io.IOException;

import personal.wuyi.jpropertiesorm.annotation.PathX;
import personal.wuyi.jpropertiesorm.annotation.ValueX;
import personal.wuyi.jpropertiesorm.core.ConfigurationX;

/**
 * Application configuration class example
 * 
 * <p>This class is an example to show the binding between the instance 
 * members in java class and the parameters in the external configuration file 
 * by annotation.
 * 
 * <p>This class will use method chain to return a new instance of AppConfig2 
 * which has been populated the parameters from the external configuration 
 * file.
 * 
 * <p>This class uses instance fields for the parameters and use 
 * {@code PathX} on a certain instance field to specify the path of the 
 * external configuration file.
 * 
 * <p>This solution is similar to AppConfig1, the benefit of this class is if 
 * we need to have a bunch of configurations in same style and the only 
 * difference is the path (for example, you have an application which needs to 
 * connect to MySQL and OracleDb at the same time). So you can reuse this 
 * class for multiple cases with different paths rather than creating several 
 * classes.
 * 
 * @author  Wuyi Chen
 * @date    12/27/2017
 * @version 1.1
 * @since   1.1
 */
public class AppConfig2 {
	@PathX
	private String path;
	
	public AppConfig2(String path) {
		this.path = path;
	}
	
	public AppConfig2 initialize() throws IllegalArgumentException, IllegalAccessException, IOException {
		return ConfigurationX.bindExternalConfigurationWithInstanceFieldsUsingPathX(this);
	}
	
	@ValueX("app.host")
	private String host;
	
	@ValueX("app.api_key")
	private String apiKey;
	
	@ValueX("app.username")
	private String username;
	
	@ValueX("app.password")
	private String password;

	public String getHost()                    { return host;              }
	public void   setHost(String host)         { this.host = host;         }
	public String getApiKey()                  { return apiKey;            }
	public void   setApiKey(String apiKey)     { this.apiKey = apiKey;     }
	public String getUsername()                { return username;          }
	public void   setUsername(String username) { this.username = username; }
	public String getPassword()                { return password;          }
	public void   setPassword(String password) { this.password = password; }
}
