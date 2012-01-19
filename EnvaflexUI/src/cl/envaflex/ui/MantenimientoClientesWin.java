package cl.envaflex.ui;

import java.util.List;

import org.zkoss.zul.Window;

import cl.envaflex.jpa.dao.ClienteDao;
import cl.envaflex.jpa.model.Cliente;
import cl.envaflex.util.SpringContext;

public class MantenimientoClientesWin extends Window {
	
	private static final long serialVersionUID = -5033259267438061390L;
	private String errorMessage = "";
	private ClienteDao cDao;
	
	public MantenimientoClientesWin(){
		SpringContext context = SpringContext.getInstance();
		cDao = (ClienteDao) context.getBean(ClienteDao.BEAN_NAME);
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Cliente> findAllClientes(){
		return cDao.findAll();
	}
	
	public boolean agregar(Cliente cliente){
		try{
			cDao.insertar(cliente);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean remover(Cliente cliente){
		try{
			cDao.eliminar(cliente);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean actualizar(Cliente cliente){
		try{
			cDao.actualizar(cliente);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			return false;
		}
		return true;
	}

}
