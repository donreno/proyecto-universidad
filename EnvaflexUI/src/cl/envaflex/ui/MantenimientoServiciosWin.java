package cl.envaflex.ui;

import java.util.List;

import org.zkoss.zul.Window;

import cl.envaflex.jpa.dao.ServicioDao;
import cl.envaflex.jpa.model.Servicio;
import cl.envaflex.util.SpringContext;

public class MantenimientoServiciosWin extends Window {


	private static final long serialVersionUID = 6763400186023886019L;
	private String errorMessage = "";
	
	private ServicioDao sDao;
	
	public MantenimientoServiciosWin(){
		SpringContext context = SpringContext.getInstance();
		sDao = (ServicioDao) context.getBean(ServicioDao.BEAN_NAME);
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Servicio> findAllServicios(){;
		return sDao.findAll();
	}
	
	public boolean agregar(Servicio servicio){
		try{
			sDao.insertar(servicio);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean remover(Servicio servicio){
		try{
			sDao.eliminar(servicio);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean actualizar(Servicio servicio){
		try{
			sDao.actualizar(servicio);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			return false;
		}
		return true;
	}
}
