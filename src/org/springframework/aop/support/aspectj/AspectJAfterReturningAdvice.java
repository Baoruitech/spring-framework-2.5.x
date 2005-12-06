/*
 * Copyright 2002-2005 the original author or authors.
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
package org.springframework.aop.support.aspectj;

import java.lang.reflect.Method;

import org.aspectj.weaver.tools.PointcutExpression;
import org.springframework.aop.AfterReturningAdvice;

public class AspectJAfterReturningAdvice extends AbstractAspectJAdvice implements AfterReturningAdvice {
	
	public AspectJAfterReturningAdvice(Method aspectJBeforeAdviceMethod, PointcutExpression pe, AspectInstanceFactory aif) {
		super(aspectJBeforeAdviceMethod, pe, aif);
	}
	
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		invokeAdviceMethod(args);
	}
}