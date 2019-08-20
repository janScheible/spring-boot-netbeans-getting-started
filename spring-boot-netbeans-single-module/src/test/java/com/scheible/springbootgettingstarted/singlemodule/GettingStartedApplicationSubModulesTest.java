package com.scheible.springbootgettingstarted.singlemodule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.scheible.pocketsaw.impl.Pocketsaw;
import com.scheible.pocketsaw.impl.descriptor.annotation.SpringClasspathScanner;

/**
 *
 * @author sj
 */
public class GettingStartedApplicationSubModulesTest {

	private static Pocketsaw.AnalysisResult result;

	@BeforeClass
	public static void beforeClass() {
		result = Pocketsaw
				.analizeCurrentProject(SpringClasspathScanner.create(GettingStartedApplicationSubModulesTest.class));
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
