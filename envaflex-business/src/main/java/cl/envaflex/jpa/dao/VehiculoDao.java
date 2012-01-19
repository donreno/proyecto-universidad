package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.Vehiculo;

@Repository(VehiculoDao.BEAN_NAME)
public class VehiculoDao extends GenericHibernateDAO<Vehiculo> {
	
	public static final String BEAN_NAME = "vehiculoDAO";
	
	@Autowired
	public VehiculoDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_VEHICULOS = "FROM Vehiculo";
	
	@SuppressWarnings("unchecked")
	public List<Vehiculo> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_VEHICULOS);
	}
	
}
