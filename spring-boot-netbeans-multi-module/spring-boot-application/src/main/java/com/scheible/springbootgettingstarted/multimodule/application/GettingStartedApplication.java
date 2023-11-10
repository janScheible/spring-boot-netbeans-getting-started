package com.scheible.springbootgettingstarted.multimodule.application;

import com.scheible.springbootgettingstarted.multimodule.library.LibraryCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sj
 */
@SpringBootApplication
public class GettingStartedApplication {

	private static final Logger logger = LoggerFactory.getLogger(GettingStartedApplication.class);

	LibraryCode libraryCode = new LibraryCode();

	public static void main(String[] args) {
		logger.info("Running the application...");
		SpringApplication.run(GettingStartedApplication.class, args);
	}

}
