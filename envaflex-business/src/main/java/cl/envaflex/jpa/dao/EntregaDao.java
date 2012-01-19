package cl.envaflex.jpa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.Entrega;

@Repository(value=EntregaDao.BEAN_NAME)
public class EntregaDao extends GenericHibernateDAO<Entrega> {
	
	public static final String BEAN_NAME = "entregaDAO";
	
	@Autowired
	public EntregaDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_ENTREGA = "FROM Entrega";
	
	@SuppressWarnings("unchecked")
	public List<Entrega> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_ENTREGA);
	}
	
	@SuppressWarnings("unchecked")
	public List<Entrega> findEntregasSolicitadas(Long num,Date fechaDesde, Date fechaHasta){
		
		Criteria crit = getSession().createCriteria(Entrega.class);
		crit.add(Restrictions.eq("num", num));
		crit.add(Restrictions.between("fechaEstimadaEntrega", fechaDesde, fechaHasta));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Entrega> findEntregasForDespacho(Long idDespacho){
		Criteria crit = getSession().createCriteria(Entrega.class);
		crit.createAlias("despacho", "entrega.despacho");
		crit.add(Restrictions.eq("despacho.id",idDespacho));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Entrega> findEntregasForNotaVenta(int idNota){
		Criteria crit = getSession().createCriteria(Entrega.class);
		crit.createAlias("notaVenta", "entrega.notaVenta");
		crit.add(Restrictions.eq("notaVenta.id",idNota));
		return crit.list();
	}
}
