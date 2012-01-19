package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.Cargo;

@Repository(value=CargoDao.BEAN_NAME)
public class CargoDao extends GenericHibernateDAO<Cargo> {
	
	public static final String BEAN_NAME = "cargoDAO";
	
	@Autowired
	public CargoDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_CARGO = "FROM Cargo";
	
	@SuppressWarnings("unchecked")
	public List<Cargo> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_CARGO);
	}
	
}
