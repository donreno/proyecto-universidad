package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.Servicio;

@Repository(ServicioDao.BEAN_NAME)
public class ServicioDao extends GenericHibernateDAO<Servicio> {
	
	public static final String BEAN_NAME = "serviceDAO";
	
	@Autowired
	public ServicioDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	private static final String FIND_ALL_SERVICIOS = "From Servicio";
	
	@SuppressWarnings("unchecked")
	public List<Servicio> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_SERVICIOS);
	}

}
