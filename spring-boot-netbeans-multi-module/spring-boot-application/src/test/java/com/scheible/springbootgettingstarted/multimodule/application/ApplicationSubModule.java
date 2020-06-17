package com.scheible.springbootgettingstarted.multimodule.application;

import com.scheible.pocketsaw.api.SubModule;
import com.scheible.springbootgettingstarted.multimodule.application.ExternalFunctionalities.Slf4j;
import com.scheible.springbootgettingstarted.multimodule.application.ExternalFunctionalities.SpringFramework;
import com.scheible.springbootgettingstarted.multimodule.application.SubModules.SharedCodeLibrary;

/**
 *
 * @author sj
 */
@SubModule(uses = { SharedCodeLibrary.class, SpringFramework.class, Slf4j.class })
public final class ApplicationSubModule {

	private ApplicationSubModule() {
	}
}
