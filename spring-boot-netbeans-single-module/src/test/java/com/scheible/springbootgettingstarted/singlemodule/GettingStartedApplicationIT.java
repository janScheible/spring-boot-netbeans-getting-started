package com.scheible.springbootgettingstarted.singlemodule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author sj
 */
@SpringBootTest
@TestPropertySource(properties = { "spring.main.banner-mode=off" })
class GettingStartedApplicationIT {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void integrationTest() {
		assertThat(applicationContext).isNotNull();
	}
}
