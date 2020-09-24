package com.scheible.springbootgettingstarted.multimodule.library;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 *
 * @author sj
 */
class LibraryCodeTest {

	@Test
	void unitTest() {
		assertThat(new LibraryCode().doIt()).isEqualTo(true);
	}
}
