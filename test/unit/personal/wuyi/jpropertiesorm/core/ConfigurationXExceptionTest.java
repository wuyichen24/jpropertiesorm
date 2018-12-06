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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import personal.wuyi.jpropertiesorm.example.AppConfig2;
import personal.wuyi.jpropertiesorm.wrong.AppConfig1Error1;
import personal.wuyi.jpropertiesorm.wrong.AppConfig1Error2;
import personal.wuyi.jpropertiesorm.wrong.AppConfig1Error3;
import personal.wuyi.jpropertiesorm.wrong.AppConfig1Error4;
import personal.wuyi.jpropertiesorm.wrong.AppConfig1Error5;
import personal.wuyi.jpropertiesorm.wrong.AppConfig2Error1;
import personal.wuyi.jpropertiesorm.wrong.AppConfig2Error2;
import personal.wuyi.jpropertiesorm.wrong.AppConfig2Error3;

/**
 * Test class for {@code ConfigurationX}.
 * 
 * <p>This class is to test throwing exceptions in {@code ConfigurationX} 
 * class.
 * 
 * @author  Wuyi Chen
 * @date    12/04/2018
 * @version 1.2
 * @since   1.2
 */
public class ConfigurationXExceptionTest {
	@Test
	public void bindExternalConfigurationWithInstanceFieldsUsingPropertySourceXExceptionTest() throws IllegalArgumentException, IllegalAccessException, IOException {
		// test properties file doesn't exist (based on AppConfig1 style)
		try {
			new AppConfig1Error1().initialize();
	        fail("Expected an FileNotFoundException to be thrown");
	    } catch (FileNotFoundException e) {
	        assertThat(e.getMessage(), is("config/appABC.properties is not existing"));
	    }
		
		// test properties file doesn't exist (based on AppConfig2 style)
		try {
			new AppConfig2("config/appABC.properties").initialize();
	        fail("Expected an FileNotFoundException to be thrown");
	    } catch (FileNotFoundException e) {
	        assertThat(e.getMessage(), is("config/appABC.properties is not existing"));
	    }
	}
	
	@Test
	public void populateInstanceFieldsWithPropertiesExceptionTest() throws IllegalArgumentException, IllegalAccessException, IOException {
		// test one field can not be static
		try {
			new AppConfig1Error2().initialize();
	        fail("Expected an IllegalArgumentException to be thrown");
	    } catch (IllegalArgumentException e) {
	        assertThat(e.getMessage(), is("apiKey field can not be static."));
	    }
		
		// test one parameter is missing in the properties file
		try {
			new AppConfig1Error3().initialize();
	        fail("Expected an IllegalArgumentException to be thrown");
	    } catch (IllegalArgumentException e) {
	        assertThat(e.getMessage(), is("config/app.properties is missing parameter: app.orgnization"));
	    }
	}
	
	@Test
	public void validatePropertySourceXInConfigClassExceptionTest() throws IllegalAccessException, IOException {
		// test missing PropertySourceX annotation
		try {
			new AppConfig1Error4().initialize();
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("AppConfig1Error4 needs to have PropertySourceX annotation."));
		}
		
		// test PropertySourceX is missing value parameter
		try {
			new AppConfig1Error5().initialize();
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("The PropertySourceX annotation in AppConfig1Error5 class doesn't have proper values."));
		}
	}
	
	@Test
	public void validatePathXInConfigClassExceptionTest() throws IllegalArgumentException, IllegalAccessException, IOException {
		// test missing PathX annotation
		try {
			new AppConfig2Error1("config/app.properties").initialize();
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("AppConfig2Error1 needs to have PathX annotation."));
		}
		
		// test PathX annotation more than one
		try {
			new AppConfig2Error2("config/app.properties").initialize();
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("AppConfig2Error2 needs to have only one PathX annotation."));
		}
		
		// test the field annotated by PathX should be String
		try {
			new AppConfig2Error3("config/app.properties").initialize();
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("The field marked by PathX annotation needs to be String."));
		}
	}
}
