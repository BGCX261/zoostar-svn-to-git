package net.zoostar.metrade.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
public class MetradeMultiActionController extends MultiActionController {

	@RequestMapping("/index.html")
	public ModelAndView loadHomePage(HttpServletRequest request, HttpServletResponse response) {
		String view = "index";
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		map.put("system_properties", System.getenv());

		Properties properties = System.getProperties();
		Map<String, String> java_props = new HashMap<String, String>();
		for(Object key : properties.keySet()) {
			java_props.put((String)key, (String)properties.get(key));
		}
		map.put("java_properties", java_props);
		
		return new ModelAndView(view);
	}
}
