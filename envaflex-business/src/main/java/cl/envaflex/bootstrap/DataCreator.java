package cl.envaflex.bootstrap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cl.envaflex.jpa.dao.CargoDao;
import cl.envaflex.jpa.dao.EmpleadoDao;
import cl.envaflex.jpa.model.Cargo;
import cl.envaflex.jpa.model.Empleado;

@Component(DataCreator.BEAN_NAME)
public class DataCreator {
	
	public static final String BEAN_NAME = "DataCreator";
	
	private CargoDao cargoDao;
	private EmpleadoDao empleadoDao;
	
	@Autowired
	public DataCreator(@Qualifier(EmpleadoDao.BEAN_NAME) EmpleadoDao empDao,
			@Qualifier(CargoDao.BEAN_NAME) CargoDao cargoDao){
		this.empleadoDao = empDao;
		this.cargoDao = cargoDao;
	}
	
	@PostConstruct
	public void createData(){
		Cargo admin = createCargoAdmin();
		Cargo cargoVndr = createCargoVNDR();
		
		//Creamos usuarios
		Empleado gustavo = new Empleado();
		gustavo.setRutEmpleado("1234");
		gustavo.setDvEmpleado("K");
		gustavo.setNombreEmpleado("Gustavo");
		gustavo.setUser("gcruz");
		gustavo.setPassword("gustavo");
		gustavo.setCargo(cargoVndr);
		empleadoDao.insertar(gustavo);
		
		Empleado renato = new Empleado();
		renato.setRutEmpleado("1");
		renato.setDvEmpleado("9");
		renato.setNombreEmpleado("Gustavo");
		renato.setUser("rsanmartin");
		renato.setPassword("password");
		renato.setCargo(admin);
		empleadoDao.insertar(renato);
	}
	
	private Cargo createCargoVNDR(){
		Cargo cargo = new Cargo();
		cargo.setIdCargo("VNDR");
		cargo.setNombreCargo("VENDEDOR");
		cargo.setDescCargo("...");
		cargoDao.insertar(cargo);
		System.out.println("Cargo creado...");
		return cargo;
	}
	
	private Cargo createCargoAdmin(){
		Cargo cargo = new Cargo();
		cargo.setIdCargo("ADMN");
		cargo.setNombreCargo("ADMINISTRADOR");
		cargo.setDescCargo("...");
		cargoDao.insertar(cargo);
		System.out.println("Cargo creado...");
		return cargo;
	}

}
