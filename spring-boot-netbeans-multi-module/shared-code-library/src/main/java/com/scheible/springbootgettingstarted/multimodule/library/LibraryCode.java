package com.scheible.springbootgettingstarted.multimodule.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sj
 */
public class LibraryCode {

	private static final Logger logger = LoggerFactory.getLogger(LibraryCode.class);

	public void doIt() {
		logger.info("I did it!");
	}
}
