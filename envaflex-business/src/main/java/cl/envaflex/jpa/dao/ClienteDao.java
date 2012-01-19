package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.Cliente;

@Repository(value=ClienteDao.BEAN_NAME)
public class ClienteDao extends GenericHibernateDAO<Cliente> {
	
	public static final String BEAN_NAME = "clienteDAO";
	
	@Autowired
	public ClienteDao(@Qualifier(SESSION_FACTORY)SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	private static final String FIND_ALL_CLIENTE = "From Cliente";
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_CLIENTE);
	}
	
	

}
