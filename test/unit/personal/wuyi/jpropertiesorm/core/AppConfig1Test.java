package personal.wuyi.jpropertiesorm.core;

import java.io.IOException;

/**
 * The demonstration for using AppConfig1
 * 
 * @author  Wuyi Chen
 * @date    12/27/2017
 * @version 1.1
 */
public class AppConfig1Test {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException {
		AppConfig1 config = new AppConfig1().initialize();
		System.out.println(config.getHost());
		System.out.println(config.getApiKey());
		System.out.println(config.getUsername());
		System.out.println(config.getPassword());
	}
}
