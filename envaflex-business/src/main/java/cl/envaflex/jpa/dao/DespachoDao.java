package cl.envaflex.jpa.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.Despacho;

@Repository(DespachoDao.BEAN_NAME)
public class DespachoDao extends GenericHibernateDAO<Despacho> {
	
	public static final String BEAN_NAME = "DespachoDAO";
	
	@Autowired
	public DespachoDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_DESPACHO = "FROM Despacho";
	
	@SuppressWarnings("unchecked")
	public List<Despacho> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_DESPACHO);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Despacho> findDespachos(Map args){
		Query query = getSession().getNamedQuery("Despacho.findDespachos");
		query.setParameter("num", args.get("num"));
		query.setParameter("fechaDesde", args.get("fechaDesde"));
		query.setParameter("fechaHasta", args.get("fechaHasta"));
		query.setParameter("estado", args.get("estado"));
		List<Despacho> despachos = query.list();
		return despachos;
	}
	
}
