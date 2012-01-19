package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.DetalleEntrega;

@Repository(value="DetalleEntregaDAO")
public class DetalleEntregaDao extends GenericHibernateDAO<DetalleEntrega> {
	
	@Autowired
	public DetalleEntregaDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_DETALLE_ENTREGA = "FROM DetalleEntrega;";
	
	@SuppressWarnings("unchecked")
	public List<DetalleEntrega> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_DETALLE_ENTREGA);
	}
	
	@SuppressWarnings("unchecked")
	public List<DetalleEntrega> findDetalleForEntrega(int idEntrega){
		Query query = getSession().getNamedQuery("detalleEntrega.findDetalleForEntrega");
		query.setParameter("idnota", idEntrega);
		List<DetalleEntrega> detallesnv = query.list();
		return detallesnv;
	}	

}
