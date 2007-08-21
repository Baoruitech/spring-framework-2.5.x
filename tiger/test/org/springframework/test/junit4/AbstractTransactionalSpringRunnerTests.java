/*
 * Copyright 2007 the original author or authors.
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
package org.springframework.test.junit4;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Abstract base class for verifying support of Spring's &#064;Transactional and
 * &#064;NotTransactional annotations.
 * </p>
 *
 * @see ClassLevelTransactionalSpringRunnerTests
 * @see MethodLevelTransactionalSpringRunnerTests
 * @see Transactional
 * @see NotTransactional
 * @author Sam Brannen
 * @version $Revision: 1.2 $
 * @since 2.1
 */
public abstract class AbstractTransactionalSpringRunnerTests {

	// ------------------------------------------------------------------------|
	// --- CONSTANTS ----------------------------------------------------------|
	// ------------------------------------------------------------------------|

	protected static final String	BOB		= "bob";

	protected static final String	JANE	= "jane";

	protected static final String	SUE		= "sue";

	protected static final String	LUKE	= "luke";

	protected static final String	LEIA	= "leia";

	protected static final String	YODA	= "yoda";

	// ------------------------------------------------------------------------|
	// --- STATIC METHODS -----------------------------------------------------|
	// ------------------------------------------------------------------------|

	protected static int clearPersonTable(final SimpleJdbcTemplate simpleJdbcTemplate) {

		return simpleJdbcTemplate.update("DELETE FROM person");
	}

	protected static void createPersonTable(final SimpleJdbcTemplate simpleJdbcTemplate) {

		try {
			simpleJdbcTemplate.update("CREATE TABLE person (name VARCHAR(20) NOT NULL, PRIMARY KEY(name))");
		}
		catch (final BadSqlGrammarException bsge) {
			/* ignore */
		}
	}

	protected static int countRowsInPersonTable(final SimpleJdbcTemplate simpleJdbcTemplate) {

		return simpleJdbcTemplate.queryForInt("SELECT COUNT(0) FROM person");
	}

	protected static int addPerson(final SimpleJdbcTemplate simpleJdbcTemplate, final String name) {

		return simpleJdbcTemplate.update("INSERT INTO person VALUES(?)", name);
	}

	protected static int deletePerson(final SimpleJdbcTemplate simpleJdbcTemplate, final String name) {

		return simpleJdbcTemplate.update("DELETE FROM person WHERE name=?", name);
	}

}