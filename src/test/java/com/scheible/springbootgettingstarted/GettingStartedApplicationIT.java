package com.scheible.springbootgettingstarted;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author sj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GettingStartedApplicationIT {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void integrationTest() {
		assertThat(applicationContext).isNotNull();
	}
}
