package com.scheible.springbootgettingstarted.singlemodule;

import com.scheible.pocketsaw.api.SubModule;
import com.scheible.springbootgettingstarted.singlemodule.ExternalFunctionalities.SpringFramework;

/**
 *
 * @author sj
 */
@SubModule(uses = {SpringFramework.class})
public final class ApplicationSubModule {

	private ApplicationSubModule() {
	}
}
