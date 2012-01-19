package cl.envaflex.ui;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.zkoss.zul.Window;

import cl.envaflex.jpa.dao.ClienteDao;
import cl.envaflex.jpa.dao.DespachoDao;
import cl.envaflex.jpa.dao.EntregaDao;
import cl.envaflex.jpa.dao.VehiculoDao;
import cl.envaflex.jpa.model.Despacho;
import cl.envaflex.jpa.model.Entrega;
import cl.envaflex.jpa.model.Vehiculo;
import cl.envaflex.service.DespachoService;
import cl.envaflex.service.exception.ServiceException;
import cl.envaflex.util.SpringContext;


public class DespachosWin extends Window {
	
	private String errorMessage = "";
	
	private DespachoDao dDao;
	private EntregaDao eDao;
	private VehiculoDao vDao;
	private DespachoService dService;
	
	public DespachosWin(){
		SpringContext context = SpringContext.getInstance();
		//Instanciación de DAOS
		dDao = (DespachoDao) context.getBean(DespachoDao.BEAN_NAME);
		eDao = (EntregaDao) context.getBean(EntregaDao.BEAN_NAME);
		vDao = (VehiculoDao) context.getBean(VehiculoDao.BEAN_NAME);
		dService = (DespachoService) context.getBean(DespachoService.BEAN_NAME);
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Despacho> findAllDespachos(){
		return dDao.findAll();
	}
	
	public List<Despacho> findDespachos(Map args){
		return dDao.findDespachos(args);
	}
	
	public List<Entrega> findEntregasForDespacho(Despacho desp){
		Long idDespacho = desp.getIdDespacho();
		return eDao.findEntregasForDespacho(idDespacho);
	}
	
	public List<Entrega> findEntregasSolicitadas(Long num,Date fechaDesde, Date fechaHasta){
		return eDao.findEntregasSolicitadas(num,fechaDesde,fechaHasta);
	}
	
	public List<Vehiculo> findAllVehiculos(){
		return vDao.findAll();
	}
	
	public boolean validarDespacho(Despacho desp, List<Entrega> entregas,Vehiculo vehi){
		if(desp.getFecha()==null){
			setErrorMessage("La fecha no puede estar vacia");
			return false;
		}
		if(entregas.size()<1){
			setErrorMessage("EL listado de Entregas no puede estar Vacio.");
			return false;
		}
		if(vehi==null){
			setErrorMessage("Debe seleccionar un vehiculo para realizar el despacho");
			return false;
		}
		return true;
	}
	
	public boolean ingresarDespacho(Despacho desp, List<Entrega> entregas,Vehiculo vehi){
		desp.setVehiculo(vehi);
		try {
			dService.programarDespacho(desp, entregas);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean modificarDespacho(Despacho desp,List<Entrega> entregas){
		// se prepara el despacho
//		Datebox modifDateBox = (Datebox)getFellow("modifDateBox");
//		desp.setFecha(modifDateBox.getValue());
		//primero se ejecuta el proceso de modificación
		try {
			dService.modificarDespacho(desp, entregas);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		//se ejecuta el proceso de actualizacion de notas de venta
		return true;
	}

}
