package cl.envaflex.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cargo/*")
public class CargoWS {
	
	@RequestMapping(value="/hola",method=RequestMethod.GET,
			headers="Accept=application/json")
	public String diHola(){
		return "HOLA";
	}
}
