/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.aspectj;

import junit.framework.TestCase;

import org.springframework.beans.TestBean;

/**
 * Tests for matching of bean() pointcut designator.
 *
 * @author Ramnivas Laddad
 */
public class BeanNamePointcutMatchingTests extends TestCase {

	public void testMatchingPointcuts() {
		assertMatch("someName", "bean(someName)");

		// Spring beans are less restrictive 
		assertMatch("someName/someOtherName", "bean(someName/someOtherName)"); // MVC Controller-kind 
		assertMatch("service:name=traceService", "bean(service:name=traceService)"); // JMX-kind

		// Wildcards
		assertMatch("someName", "bean(*someName)");
		assertMatch("someName", "bean(*Name)");
		assertMatch("someName", "bean(*)");
		assertMatch("someName", "bean(someName*)");
		assertMatch("someName", "bean(some*)");
		assertMatch("someName", "bean(some*Name)");
		assertMatch("someName", "bean(*some*Name*)");
		assertMatch("someName", "bean(*s*N*)");

		// Or, not expressions
		assertMatch("someName", "bean(someName) || bean(someOtherName)");
		assertMatch("someName", "!bean(someOtherName)");
	}

	public void testNonMatchingPointcuts() {
		assertMisMatch("someName", "bean(someNamex)");
		assertMisMatch("someName", "bean(someX*Name)");

		// And, not expressions
		assertMisMatch("someName", "bean(someName) && bean(someOtherName)");
		assertMisMatch("someName", "!bean(someName)");
	}

	private void assertMatch(String beanName, String pcExpression) {
		assertTrue("Unexpected mismatch for bean \"" + beanName + "\" for pcExpression \"" + pcExpression + "\"",
				matches(beanName, pcExpression));
	}

	private void assertMisMatch(String beanName, String pcExpression) {
		assertFalse("Unexpected match for bean \"" + beanName + "\" for pcExpression \"" + pcExpression + "\"",
				matches(beanName, pcExpression));
	}

	private static boolean matches(final String beanName, String pcExpression) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut() {
			protected String getCurrentProxiedBeanName() {
				return beanName;
			}
		};
		pointcut.setExpression(pcExpression);
		return pointcut.matches(TestBean.class);
	}
}