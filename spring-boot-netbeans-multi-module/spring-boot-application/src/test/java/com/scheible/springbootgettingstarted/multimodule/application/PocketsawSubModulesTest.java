package com.scheible.springbootgettingstarted.multimodule.application;

import java.util.Optional;

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
		// get the base package of the whole multi-module project
		final String basePackage = Optional.of(PocketsawSubModulesTest.class)
			.map(clazz -> clazz.getPackage().getName())
			.map(packageName -> packageName.substring(0, packageName.lastIndexOf(".")))
			.get();

		Pocketsaw.analizeClasspath(ClassgraphClasspathScanner.create(basePackage).enableAutoMatching());
	}

	@Test
	void justMakeItRunAsTestToCreateTheVisualization() {
		// No more usage whitelisting (too much work to maintain) and no dependency cylce
		// checking (done with ArchUnit Maven plugin now, ArchUnit rules from
		// static-analysis-rules can be re-used in all projects) now.
	}

}
