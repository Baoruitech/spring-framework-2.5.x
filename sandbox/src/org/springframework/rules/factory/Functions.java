/*
 * Copyright 2002-2004 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.springframework.rules.factory;

import org.springframework.rules.UnaryFunction;
import org.springframework.rules.UnaryProcedure;
import org.springframework.rules.functions.UnaryFunctionChain;

/**
 * A factory for easing the construction and composition of functions.
 * 
 * @author Keith Donald
 */
public class Functions {
    private static final Functions INSTANCE = new Functions();

    public Functions() {

    }

    public static Functions instance() {
        return INSTANCE;
    }

    public UnaryFunction chain(UnaryFunction firstFunction,
            UnaryFunction secondFunction) {
        return new UnaryFunctionChain(firstFunction, secondFunction);
    }

    public UnaryFunction chain(UnaryFunction[] functionsToChain) {
        return new UnaryFunctionChain(functionsToChain);
    }
    
    public static UnaryFunction toFunction(final UnaryProcedure procedure) {
        return new UnaryFunction() {
            public Object evaluate(Object argument) {
                procedure.run(argument);
                return null;
            }
        };
    }

}