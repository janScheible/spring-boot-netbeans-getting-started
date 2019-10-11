package com.scheible.springbootgettingstarted.singlemodule;

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
}
