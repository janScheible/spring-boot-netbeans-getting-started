package com.scheible.maven.extension.skipprofile;

import com.scheible.maven.extension.skipprofile.SkipExecutionConfiguration.Plugin;
import java.util.AbstractMap.SimpleImmutableEntry;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author sj
 */
public class SkipExecutionConfigurationTest {

	@Test
	public void testValidInput() {
		assertEquals(SkipExecutionConfiguration.parse("xxx.yyy.zzz:abc-def@default"),
				new SimpleImmutableEntry<>(new Plugin("xxx.yyy.zzz", "abc-def"), "default"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingExecutionId() {
		SkipExecutionConfiguration.parse("xxx.yyy.zzz:abc-def");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingGroupId() {
		SkipExecutionConfiguration.parse("abc-def@default");
	}
}
