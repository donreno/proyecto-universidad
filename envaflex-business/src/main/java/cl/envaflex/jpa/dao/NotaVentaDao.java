package cl.envaflex.jpa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.codes.NotaVentaEnum;
import cl.envaflex.jpa.model.NotaVenta;

@Repository(NotaVentaDao.BEAN_NAME)
public class NotaVentaDao extends GenericHibernateDAO<NotaVenta> {
	
	public static final String BEAN_NAME = "notaVentaDAO";

	@Autowired
	public NotaVentaDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_NOTA_VENTA = "FROM NotaVenta;";
	
	@SuppressWarnings("unchecked")
	public List<NotaVenta> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_NOTA_VENTA);
	}
	
	@SuppressWarnings("unchecked")
	public List<NotaVenta> findCotizaciones(Long idCliente,Long num, Date fechaDesde,Date fechaHasta){
		Criteria crit = getSession().createCriteria(NotaVenta.class);
		crit.add(Restrictions.eq("num", num));
		crit.add(Restrictions.eq("cliente", idCliente));
		crit.add(Restrictions.eq("estadoVenta",NotaVentaEnum.ESTADO_VENTA_COTIZ.ordinal()));
		crit.add(Restrictions.between("fechaCotizacion", fechaDesde, fechaHasta));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<NotaVenta> findNotasVtaEnProceso(Long idCliente,Long num, Date fechaDesde,Date fechaHasta){
		Criteria crit = getSession().createCriteria(NotaVenta.class);
		crit.add(Restrictions.eq("num", num));
		crit.add(Restrictions.eq("cliente", idCliente));
		crit.add(Restrictions.or(Restrictions.eq("estadoVenta",NotaVentaEnum.ESTADO_VENTA_NOTA_VENTA_INICIAL), Restrictions.eq("estadoVenta",NotaVentaEnum.ESTADO_VENTA_NOTA_VENTA_DESPACHO)));
		crit.add(Restrictions.between("fechaCotizacion", fechaDesde, fechaHasta));
		return crit.list();
	}
}
