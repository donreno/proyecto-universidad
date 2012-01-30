package cl.envaflex.jpa.dao;


import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;

import cl.envaflex.jpa.model.Cargo;
import cl.envaflex.jpa.model.Empleado;
import cl.envaflex.util.SpringContext;

public class EmpleadoDaoTest extends TestCase {

	
	private CargoDao cargoDao;
	private EmpleadoDao empleadoDao;
	
	@Before
	public void setUp() throws Exception {
		SpringContext context = SpringContext.getInstance();
		cargoDao = (CargoDao) context.getBean("cargoDAO");
		empleadoDao = (EmpleadoDao) context.getBean("empleadoDAO");
	}
	
	public void testContext(){
		System.out.println("Context UP!");
	}
	
	private Cargo createCargoVNDR(){
		Cargo cargo = new Cargo();
		cargo.setIdCargo("VNDR");
		cargo.setNombreCargo("VENDEDOR");
		cargo.setDescCargo("...");
		cargoDao.insertar(cargo);
//		cargoDao.getHibernateTemplate().flush();
		System.out.println("Cargo creado...");
		return cargo;
	}
	
	private Cargo createCargoOPR(){
		Cargo cargo = new Cargo();
		cargo.setIdCargo("OPR");
		cargo.setNombreCargo("Operario");
		cargo.setDescCargo("...");
		cargoDao.insertar(cargo);
//		cargoDao.getHibernateTemplate().flush();
		System.out.println("Cargo creado...");
		return cargo;
	}
	
	public void testCreateEmpleados(){
		Cargo cargoVndr = createCargoVNDR();
		Cargo cargoOpr = createCargoOPR();
		
		Empleado emp = new Empleado();
		emp.setRutEmpleado("12345679");
		emp.setDvEmpleado("8");
		emp.setCargo(cargoVndr);
		emp.setEsUsuario(true);
		emp.setUser("vendedor");
		emp.setPassword("password");
		emp.setFechaNacimiento(new Date());
		emp.setNombreEmpleado("Mister");
		emp.setApellidoPaterno("Vende");
		emp.setApellidoMaterno("Door");
		empleadoDao.insertar(emp);
		System.out.println("Empleado 1 Creado...");
		
		Empleado emp2 = new Empleado();
		emp2.setRutEmpleado("12345678");
		emp2.setDvEmpleado("9");
		emp2.setCargo(cargoOpr);
		emp2.setEsUsuario(true);
		emp2.setUser("operador");
		emp2.setPassword("password");
		emp2.setFechaNacimiento(new Date());
		emp2.setNombreEmpleado("Mister");
		emp2.setApellidoPaterno("Oper");
		emp2.setApellidoMaterno("Ario");
		empleadoDao.insertar(emp2);
		System.out.println("Empleado 2 Creado...");
	}
	
	public void testFindEmpleadosJpa(){
		List<Empleado> empleados = empleadoDao.findAll();
		for(Empleado empl:empleados){
			System.out.println("***************************");
			System.out.println("ID: "+empl.getRutEmpleado());
			System.out.println("Nombre: "+empl.getNombreEmpleado());
			System.out.println("Cargo: "+empl.getCargo().getNombreCargo());
			System.out.println("***************************");
		}
	}

}
