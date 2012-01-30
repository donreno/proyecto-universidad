package cl.envaflex.rest.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import cl.envaflex.jpa.dao.CargoDao;
import cl.envaflex.jpa.model.Cargo;
import cl.envaflex.rest.object.CargoObject;
import cl.envaflex.util.SpringContext;



@Service("CargoWS")
@Path("/cargo")
public class CargoWS {
	
	private CargoDao cargoDao;
	
	public CargoWS() {
		super();
		cargoDao = (CargoDao) SpringContext.getInstance().getBean(CargoDao.BEAN_NAME);
	}



	@GET
	@Produces("application/json")
	@Path("/hola")
	public CargoObject diHola(){
		CargoObject obj = new CargoObject();
		obj.setNombre("AURELIO");
		obj.setDesc("SHORT DESC...");
		obj.setAlgo("Algo.... :O");
		return obj;
	}
	
	@GET
	@Produces("application/json")
	@Path("/list")
	public List<Cargo> listAllCargo(){
		return cargoDao.findAll();
	}
}
