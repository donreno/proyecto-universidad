package cl.envaflex.ui;

import java.util.List;

import org.zkoss.zul.Window;

import cl.envaflex.jpa.dao.CargoDao;
import cl.envaflex.jpa.dao.ClienteDao;
import cl.envaflex.jpa.model.Cargo;
import cl.envaflex.util.SpringContext;

public class MantenimientoCargoWin extends Window {
	
	private String errorMessage = "";
	private CargoDao cDao;
	
	public MantenimientoCargoWin(){
		SpringContext context = SpringContext.getInstance();
		cDao = (CargoDao) context.getBean(CargoDao.BEAN_NAME);
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Cargo> findAllCargos(){
		return cDao.findAll();
	}
	
	public boolean agregar(Cargo cargo){
		try{
			cDao.insertar(cargo);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean remover(Cargo cargo){
		try{
			cDao.eliminar(cargo);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean actualizar(Cargo cargo){
		try{
			cDao.actualizar(cargo);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			return false;
		}
		return true;
	}

}
