package com.scheible.springbootgettingstarted.multimodule.application;

import com.scheible.pocketsaw.api.SubModule;
import com.scheible.springbootgettingstarted.multimodule.application.ExternalFunctionalities.SharedCodeLibrary;
import com.scheible.springbootgettingstarted.multimodule.application.ExternalFunctionalities.SpringFramework;

/**
 *
 * @author sj
 */
@SubModule(uses = {SpringFramework.class, SharedCodeLibrary.class})
public final class ApplicationSubModule {

	private ApplicationSubModule() {
	}
}
