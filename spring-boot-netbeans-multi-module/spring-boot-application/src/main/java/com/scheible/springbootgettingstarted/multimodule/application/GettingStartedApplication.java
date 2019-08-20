package com.scheible.springbootgettingstarted.multimodule.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.scheible.springbootgettingstarted.multimodule.library.LibraryCode;

/**
 *
 * @author sj
 */
@SpringBootApplication
public class GettingStartedApplication {

	LibraryCode libraryCode = new LibraryCode();

	public static void main(String[] args) {
		SpringApplication.run(GettingStartedApplication.class, args);
	}
}
