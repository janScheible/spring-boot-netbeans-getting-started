package com.scheible.springbootgettingstarted;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author sj
 */
@Controller
public class MainController {
	
	private final DateTimeProvider dateTimeProvider;

	@Autowired
	public MainController(DateTimeProvider dateTimeProvider) {
		this.dateTimeProvider = dateTimeProvider;
	}	

	@RequestMapping(path = "/", method = GET)
	public String redirectToIndex() {
		return "redirect:/index.html";
	}

	@RequestMapping(path = "/index.html", method = GET)
	public String index(Map<String, Object> model) {
		model.put("dateTime", dateTimeProvider.getDateTime());
		return "index";
	}
}
