package com.management.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.Model.Airplane;


@Controller
public class AirplaneController {
	@RequestMapping("/Airplane")
	@ResponseBody
	public String ret() {
		Airplane airplane = new Airplane();
		return "test123 12312123 312 3123 1231";
	}
}
