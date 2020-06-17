package com.scheible.springbootgettingstarted.singlemodule;

import com.scheible.pocketsaw.api.SubModule;
import com.scheible.springbootgettingstarted.singlemodule.ExternalFunctionalities.Slf4j;
import com.scheible.springbootgettingstarted.singlemodule.ExternalFunctionalities.SpringFramework;

/**
 *
 * @author sj
 */
@SubModule(uses = { SpringFramework.class, Slf4j.class })
public final class ApplicationSubModule {

	private ApplicationSubModule() {
	}
}
