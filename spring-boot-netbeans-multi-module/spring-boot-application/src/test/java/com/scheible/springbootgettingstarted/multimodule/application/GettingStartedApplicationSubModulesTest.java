package com.scheible.springbootgettingstarted.multimodule.application;

import java.util.Optional;

import com.scheible.pocketsaw.impl.Pocketsaw;
import com.scheible.pocketsaw.impl.descriptor.annotation.ClassgraphClasspathScanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sj
 */
class GettingStartedApplicationSubModulesTest {

	private static Pocketsaw.AnalysisResult result;

	@BeforeAll
	static void beforeClass() {
		final String basePackage = Optional.of(GettingStartedApplicationSubModulesTest.class)
			.map(clazz -> clazz.getPackage().getName())
			.map(packageName -> packageName.substring(0, packageName.lastIndexOf(".")))
			.get();

		result = Pocketsaw.analizeClasspath(ClassgraphClasspathScanner.create(basePackage));
	}

	@Test
	void testNoDescriptorCycle() {
		assertThat(result.getAnyDescriptorCycle()).isEmpty();
	}

	@Test
	void testNoCodeCycle() {
		assertThat(result.getAnyCodeCycle()).isEmpty();
	}

	@Test
	void testNoIllegalCodeDependencies() {
		assertThat(result.getIllegalCodeDependencies()).isEmpty();
	}

}
