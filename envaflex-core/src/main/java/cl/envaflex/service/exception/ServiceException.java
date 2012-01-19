package cl.envaflex.service.exception;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = -5177037180384878854L;

	public ServiceException(Throwable t, String arg0) {
		super("Problema en la ejecución del servicio:\n"+arg0);
		this.initCause(t);
	}
	
	

}
