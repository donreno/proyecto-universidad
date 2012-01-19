package cl.envaflex.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import cl.envaflex.rest.object.CargoObject;



@Service("CargoWS")
@Path("/cargo")
public class CargoWS {
	
	@GET
	@Produces("application/json")
	public CargoObject diHola(){
		CargoObject obj = new CargoObject();
		obj.setNombre("AURELIO");
		obj.setDesc("SHORT DESC...");
		return obj;
	}
}
