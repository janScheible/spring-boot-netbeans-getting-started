package com.scheible.springbootgettingstarted.multimodule.application;

import com.scheible.pocketsaw.api.ExternalFunctionality;

/**
 *
 * @author sj
 */
public class ExternalFunctionalities {

	@ExternalFunctionality(packageMatchPattern = {"org.springframework.**"})
	public static class SpringFramework {
	}

	@ExternalFunctionality(packageMatchPattern = {"org.slf4j.**"})
	public static class Slf4j {
	}

	@ExternalFunctionality(packageMatchPattern = {"com.scheible.springbootgettingstarted.multimodule.library.**"})
	public static class SharedCodeLibrary {
	}
}
