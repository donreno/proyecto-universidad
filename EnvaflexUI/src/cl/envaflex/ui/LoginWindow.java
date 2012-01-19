package cl.envaflex.ui;

import org.zkoss.zul.Window;

import cl.envaflex.jpa.dao.EmpleadoDao;
import cl.envaflex.jpa.model.Empleado;
import cl.envaflex.ui.util.UserCredentialManager;
import cl.envaflex.util.SpringContext;

public class LoginWindow extends Window {
	
	private static final long serialVersionUID = 7310197761008890784L;
	private String errorMessage = "";
	private EmpleadoDao empDao;
	
	public LoginWindow(){
		empDao = (EmpleadoDao) SpringContext.getInstance().getBean(EmpleadoDao.BEAN_NAME);
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Empleado doLogin(Empleado emp){
		emp = empDao.findUsuario(emp.getUser(),emp.getPassword());
		if(emp!=null){
			UserCredentialManager.getInstance(emp.getUser());
		}
		return emp;
	}

}
