package com.scheible.springbootgettingstarted;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;

/**
 *
 * @author sj
 */
@Controller
public class DateTimeProvider {

	public String getDateTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) + " "
				+ LocalDateTime.now().format(DateTimeFormatter.ISO_TIME);
	}
}
