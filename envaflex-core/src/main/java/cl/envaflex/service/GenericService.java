package cl.envaflex.service;

import cl.envaflex.jpa.dao.GenericHibernateDAO;
import cl.envaflex.service.exception.ServiceException;

abstract class GenericService {
	
	public static String BEAN_NAME = "BEAN";

	@SuppressWarnings("rawtypes")
	protected GenericHibernateDAO genericDAO;
	
	
	@SuppressWarnings("rawtypes")
	protected void setGenericDAO(GenericHibernateDAO genericDao){
		genericDAO = genericDao;
	}
	
	@SuppressWarnings("rawtypes")
	protected GenericHibernateDAO getGenericDAO(){
		return genericDAO;
	}
	
	public void crear(Object o) throws ServiceException{
		genericDAO.insertar(o);
	}
	
	public void actualizar(Object o) throws ServiceException{
		genericDAO.actualizar(o);
	}
	
	public void eliminar(Object o) throws ServiceException{
		genericDAO.eliminar(o);
	}
}
