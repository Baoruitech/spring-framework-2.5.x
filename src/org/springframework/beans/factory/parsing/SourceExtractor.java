/*
 * Copyright 2002-2006 the original author or authors.
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

package org.springframework.beans.factory.parsing;

/**
 * Simple strategy allowing tools to control how source metadata is
 * attached to the bean definition metadata.
 *
 * <p>Configuration parsers <strong>may</strong> provide the ability
 * to attach source metadata during the parse phase. They will offer
 * this metadata in a generic format which can be further modified by
 * a {@link SourceExtractor} before being attached to the bean
 * definition metadata.
 *
 * @author Rob Harrop
 * @since 2.0
 */
public interface SourceExtractor {

	/**
	 * Extract the source metadata from the candidate object supplied
	 * by the configuration parser.
	 * @param sourceCandidate the source metatada
	 */
	Object extract(Object sourceCandidate);

}
