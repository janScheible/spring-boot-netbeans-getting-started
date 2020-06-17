package com.scheible.springbootgettingstarted.singlemodule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author sj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.main.banner-mode=off" })
public class GettingStartedApplicationIT {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void integrationTest() {
		assertThat(applicationContext).isNotNull();
	}
}
