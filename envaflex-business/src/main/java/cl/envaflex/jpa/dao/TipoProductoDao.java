package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.TipoProducto;

@Repository("tipoProductoDAO")
public class TipoProductoDao extends GenericHibernateDAO<TipoProducto> {
	
	@Autowired
	public TipoProductoDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	private static final String FIND_ALL_TIPO_PRODUCTO = "FROM TipoProducto";
	
	@SuppressWarnings("unchecked")
	public List<TipoProducto> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_TIPO_PRODUCTO);
	}
	
	

}
