package cl.envaflex.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cl.envaflex.codes.EstadoCotizacion;
import cl.envaflex.codes.EstadoEntrega;
import cl.envaflex.codes.NotaVentaEnum;
import cl.envaflex.jpa.dao.EntregaDao;
import cl.envaflex.jpa.dao.NotaVentaDao;
import cl.envaflex.jpa.model.Entrega;
import cl.envaflex.jpa.model.NotaVenta;
import cl.envaflex.service.exception.ServiceException;

@Service(NotaVentaService.BEAN_NAME)
public class NotaVentaService extends GenericService {

	public static final String BEAN_NAME = "NotaVentaService";

	private EntregaDao entregaDao;
	private NotaVentaDao notaVentaDao;

	@Autowired
	public NotaVentaService(
			@Qualifier(EntregaDao.BEAN_NAME) EntregaDao entregaDao,
			@Qualifier(NotaVentaDao.BEAN_NAME) NotaVentaDao notaVentaDao) {
		super();
		this.entregaDao = entregaDao;
		this.notaVentaDao = notaVentaDao;
		setGenericDAO(notaVentaDao);
	}

	/**
	 * Permite anular una nota de venta
	 * @param nota
	 * @throws ServiceException
	 */
	public void anulaNotaVenta(NotaVenta nota) throws ServiceException {
		try {
			if (NotaVenta.ESTADO_VENTA_COTIZ == nota.getEstadoVenta()) {
				nota.setEstadoVenta(NotaVentaEnum.ESTADO_VENTA_NOTA_VENTA_ANULADA
						.ordinal());
				nota.setEstadoCotizacion(EstadoCotizacion.COTIZACION_INACTIVA
						.ordinal());
				entregaDao.actualizar(nota);
			} else {
				// se cierra la nota y todas sus entregas
				nota.setEstadoVenta(NotaVentaEnum.ESTADO_VENTA_NOTA_VENTA_ANULADA
						.ordinal());
				notaVentaDao.actualizar(nota);
				List<Entrega> entregas = entregaDao
						.findEntregasForNotaVenta(nota.getIdNotaVenta());
				for (Entrega ent : entregas) {
					ent.setEstadoEntrega(EstadoEntrega.ESTADO_CERRADA.ordinal());
					entregaDao.actualizar(ent);
				}
				notaVentaDao.actualizar(nota);
			}
		} catch (HibernateException e) {
			throw new ServiceException(e,
					"No es posible anular la Nota de Venta por problema en capa de persistencia.");
		}
	}
	
	/***
	 * Crear una nota de venta
	 * @param notaventa
	 * @throws ServiceException
	 */
	public void crear(NotaVenta notaventa) throws ServiceException {
		try{
			crear(notaventa);
		}catch(HibernateException e){
			throw new ServiceException(e,"No es posible ingresar la Cotización.");
		}
	}
	
	/**
	 * Actualiza una nota de venta.
	 * @param notaventa
	 * @throws ServiceException
	 */
	public void actualizar(NotaVenta notaventa) throws ServiceException {
		try{
			actualizar(notaventa);
		}catch(HibernateException e){
			throw new ServiceException(e,"No es posible actualizar la Cotización.");
		}
	}

}
