# jpropertiesorm

[![Build Status](https://travis-ci.org/wuyichen24/jpropertiesorm.svg?branch=master)](https://travis-ci.org/wuyichen24/jpropertiesorm)
[![Coverage Status](https://coveralls.io/repos/github/wuyichen24/jpropertiesorm/badge.svg?branch=master)](https://coveralls.io/github/wuyichen24/jpropertiesorm?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0) 

Use annotation and reflection to handle the ORM (Object-Relational Mapping) between Java class and the Java related external configuration files (.properties).

## Overview
.properties is a file extension for files mainly used in Java related technologies to store the configurable parameters of an application. The structure of properties file content is very similar to a list of key-value pairs. Traditionally, we need to create a Java config class and populate each field by reading the properties file manually. But jpropertiesorm is a framework which can map Java config class to a properties file directly, and you don't have to populate each field manually.

In jpropertiesorm, there are multiple ways to do ORM between Java config class and properties file, and the pros & cons among different ways is still in discussion. Generally, jpropertiesorm uses *@ValueX* annotation with parameter to specify each field map to which parameter in the properties file. And use *@PathX* annotation to specify which field is to store the path of the properties file, like 
```java
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
```
And the properties file looks like 
```
app.host=aaa
app.api_key=bbb
app.username=ccc
app.password=ddd
```

Currently, jproperties just supports reading properties file, the functionality of writing properties file needs to be implemented in the next release.

## Getting Started
Please see our [Wiki](https://github.com/wuyichen24/jpropertiesorm/wiki/Getting-Started) page.

## Documentation
Please see our [Wiki](https://github.com/wuyichen24/jpropertiesorm/wiki) page.

## Download
- [Download ZIP](https://github.com/wuyichen24/jpropertiesorm/archive/master.zip)
- [Download JAR](https://github.com/wuyichen24/jpropertiesorm/releases/download/v1.1/boost-1.1.jar)

## Contributing

## License
[Apache-2.0](https://opensource.org/licenses/Apache-2.0)

## Authors
- **[Wuyi Chen](https://www.linkedin.com/in/wuyichen24/)**
