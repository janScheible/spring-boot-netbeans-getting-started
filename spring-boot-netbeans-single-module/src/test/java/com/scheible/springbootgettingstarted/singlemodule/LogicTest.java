package com.scheible.springbootgettingstarted.singlemodule;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sj
 */
class LogicTest {

	@Test
	void unitTest() {
		assertThat(Logic.calculate(2)).isEqualTo(44);
	}

}
