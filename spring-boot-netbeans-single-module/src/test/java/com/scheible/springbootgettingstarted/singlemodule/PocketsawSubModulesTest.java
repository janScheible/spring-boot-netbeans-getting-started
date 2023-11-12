package com.scheible.springbootgettingstarted.singlemodule;

import com.scheible.pocketsaw.impl.Pocketsaw;
import com.scheible.pocketsaw.impl.descriptor.annotation.ClassgraphClasspathScanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author sj
 */
class PocketsawSubModulesTest {

	@BeforeAll
	static void beforeClass() {
		Pocketsaw.analizeClasspath(
				ClassgraphClasspathScanner.create(PocketsawSubModulesTest.class.getPackageName()).enableAutoMatching());
	}

	@Test
	void justMakeItRunAsTestToCreateTheVisualization() {
		// No more usage whitelisting (too much work to maintain) and no dependency cylce
		// checking (done with ArchUnit) now.
	}

}
