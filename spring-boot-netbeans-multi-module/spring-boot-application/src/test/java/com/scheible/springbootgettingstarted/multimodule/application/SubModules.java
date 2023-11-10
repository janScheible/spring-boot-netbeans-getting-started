package com.scheible.springbootgettingstarted.multimodule.application;

import com.scheible.pocketsaw.api.SubModule;
import com.scheible.springbootgettingstarted.multimodule.application.ExternalFunctionalities.Slf4j;
import com.scheible.springbootgettingstarted.multimodule.library.LibraryCode;

/**
 * @author sj
 */
public class SubModules {

	@SubModule(basePackageClass = LibraryCode.class, uses = { Slf4j.class })
	public static class SharedCodeLibrary {

	}

}
