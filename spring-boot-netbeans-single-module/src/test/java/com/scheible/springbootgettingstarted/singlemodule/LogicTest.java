package com.scheible.springbootgettingstarted.singlemodule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 *
 * @author sj
 */
class LogicTest {

	@Test
	void unitTest() {
		assertThat(Logic.calculate(2)).isEqualTo(44);
	}
}
