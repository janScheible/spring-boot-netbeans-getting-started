package com.scheible.springbootgettingstarted.multimodule.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;

import com.scheible.pocketsaw.impl.Pocketsaw;
import com.scheible.pocketsaw.impl.descriptor.annotation.ClassgraphClasspathScanner;

/**
 *
 * @author sj
 */
public class GettingStartedApplicationSubModulesTest {

	private static Pocketsaw.AnalysisResult result;

	@BeforeClass
	public static void beforeClass() {
		final String basePackage = Optional.of(GettingStartedApplicationSubModulesTest.class)
				.map(clazz -> clazz.getPackage().getName())
				.map(packageName -> packageName.substring(0, packageName.lastIndexOf("."))).get();

		result = Pocketsaw.analizeClasspath(ClassgraphClasspathScanner.create(basePackage));
	}

	@Test
	public void testNoDescriptorCycle() {
		assertThat(result.getAnyDescriptorCycle()).isEmpty();
	}

	@Test
	public void testNoCodeCycle() {
		assertThat(result.getAnyCodeCycle()).isEmpty();
	}

	@Test
	public void testNoIllegalCodeDependencies() {
		assertThat(result.getIllegalCodeDependencies()).isEmpty();
	}
}
