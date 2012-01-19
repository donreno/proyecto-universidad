package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.Producto;

@Repository(value=ProductoDao.BEAN_NAME)
public class ProductoDao extends GenericHibernateDAO<Producto> {
	
	public static final String BEAN_NAME = "productoDAO";
	
	@Autowired
	public ProductoDao(@Qualifier(SESSION_FACTORY)SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_PRODUCTO = "FROM Producto";
	
	@SuppressWarnings("unchecked")
	public List<Producto> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_PRODUCTO);
	}	

}
