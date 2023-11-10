package com.scheible.springbootgettingstarted.multimodule.library;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sj
 */
class LibraryCodeTest {

	@Test
	void unitTest() {
		assertThat(new LibraryCode().doIt()).isEqualTo(true);
	}

}
