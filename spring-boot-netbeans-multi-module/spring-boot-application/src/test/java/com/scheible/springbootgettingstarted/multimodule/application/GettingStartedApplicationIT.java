package com.scheible.springbootgettingstarted.multimodule.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sj
 */
@SpringBootTest
@TestPropertySource(properties = { "spring.main.banner-mode=off" })
class GettingStartedApplicationIT {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void integrationTest() {
		assertThat(this.applicationContext).isNotNull();
	}

}
