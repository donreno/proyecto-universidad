package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.DetalleNotaVenta;

@Repository(value=DetalleNotaVentaDao.BEAN_NAME)
public class DetalleNotaVentaDao extends GenericHibernateDAO<DetalleNotaVenta> {
	
	public static final String BEAN_NAME = "DetalleNotaVentaDAO";
	
	@Autowired
	public DetalleNotaVentaDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_DETALLE_NOTA_VENTA = "FROM DetalleNotaVenta";
	
	@SuppressWarnings("unchecked")
	public List<DetalleNotaVenta> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_DETALLE_NOTA_VENTA);
	}
	
	@SuppressWarnings("unchecked")
	public List<DetalleNotaVenta> findDetalleForNota(int idNota){
		Query query = getSession().getNamedQuery("detalleNotaVenta.findDetalleForNota");
		query.setParameter("idnota", idNota);
		List<DetalleNotaVenta> detallesnv = query.list();
		return detallesnv;
	}
	
	@SuppressWarnings("unchecked")
	public List<DetalleNotaVenta> findDetalleForNewEntrega(int idNota){
		Query query = getSession().getNamedQuery("detalleNotaVenta.findDetalleForNota");
		query.setParameter("idnota", idNota);
		List<DetalleNotaVenta> detallesnv = query.list();
		return detallesnv;
	}

}
