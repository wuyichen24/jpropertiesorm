package personal.wuyi.jpropertiesorm.core;

import java.io.IOException;

/**
 * The demonstration for using AppConfig2
 * 
 * @author  Wuyi Chen
 * @date    12/27/2017
 * @version 1.1
 */
public class AppConfig2Test {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException {
		AppConfig2 config = new AppConfig2("config/app.properties").initialize();
		System.out.println(config.getHost());
		System.out.println(config.getApiKey());
		System.out.println(config.getUsername());
		System.out.println(config.getPassword());
	}
}
