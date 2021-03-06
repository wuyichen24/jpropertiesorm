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

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * The demonstration for using AppConfig1
 * 
 * @author  Wuyi Chen
 * @date    12/27/2017
 * @version 1.1
 */
public class AppConfig4Test {
	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException, IOException {
		Assert.assertEquals("aaa", AppConfig4.host);
		Assert.assertEquals("bbb", AppConfig4.apiKey);
		Assert.assertEquals("ccc", AppConfig4.username);
		Assert.assertEquals("ddd", AppConfig4.password);
	}
}
