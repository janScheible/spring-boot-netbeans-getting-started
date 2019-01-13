package com.scheible.maven.extension.skipprofile;

import com.scheible.maven.extension.skipprofile.SkipExecutionConfiguration.Plugin;
import java.util.AbstractMap.SimpleImmutableEntry;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author sj
 */
public class SkipExecutionConfigurationTest {

	@Test
	public void testSomeMethod() {
		Assert.assertEquals(SkipExecutionConfiguration.parse("xxx.yyy.zzz:abc-def@default"),
				new SimpleImmutableEntry<>(new Plugin("xxx.yyy.zzz", "abc-def"), "default"));
	}
}
