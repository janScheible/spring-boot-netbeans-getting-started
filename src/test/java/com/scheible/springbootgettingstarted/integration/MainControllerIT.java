package com.scheible.springbootgettingstarted.integration;

import com.scheible.SpringBootNetbeansGettingStarted;
import com.scheible.springbootgettingstarted.MainController;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author sj
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringBootNetbeansGettingStarted.class)
public class MainControllerIT {
	
	@Autowired
	MainController mainController;
	
	@Test
	public void testAutowiredDependency() {
		Map<String, Object> model = new HashMap<>();
		mainController.index(model);
		
		assertThat(model.size()).isEqualTo(1);
	}	
}
