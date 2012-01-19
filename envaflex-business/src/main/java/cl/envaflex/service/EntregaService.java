package cl.envaflex.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cl.envaflex.jpa.dao.DetalleNotaVentaDao;
import cl.envaflex.jpa.dao.EntregaDao;
import cl.envaflex.jpa.dao.NotaVentaDao;
import cl.envaflex.jpa.model.DetalleEntrega;
import cl.envaflex.jpa.model.DetalleNotaVenta;
import cl.envaflex.jpa.model.Entrega;
import cl.envaflex.jpa.model.NotaVenta;
import cl.envaflex.service.exception.ServiceException;

//TODO: Este proceso esta construido pero aun no ha sido probado, se deben realizar las pruebas.

@Service(EntregaService.BEAN_NAME)
public class EntregaService extends GenericService {

	public static final String BEAN_NAME = "EntregaService";

	@Autowired
	public EntregaService(
			@Qualifier(EntregaDao.BEAN_NAME) EntregaDao entregaDao,
			@Qualifier(DetalleNotaVentaDao.BEAN_NAME) DetalleNotaVentaDao detalleNotaVentaDao,
			@Qualifier(NotaVentaDao.BEAN_NAME) NotaVentaDao notaVentaDao) {
		setGenericDAO(entregaDao);
		this.detalleNotaVentaDao = detalleNotaVentaDao;
		this.notaVentaDao = notaVentaDao;
	}
	
	private DetalleNotaVentaDao detalleNotaVentaDao;
	private NotaVentaDao notaVentaDao;
	
	public void ingresaEntrega(NotaVenta nota,
			List<DetalleNotaVenta> detallesNota, Entrega ent,
			List<DetalleEntrega> detsEnt) throws ServiceException {
		try {
			// se obtienen los parametros
			ent.setDetallesEntrega(detsEnt);
			// se procesan
			crear(ent);
			for (DetalleEntrega detEnt : detsEnt) {
				for (DetalleNotaVenta detNota : detallesNota) {
					if (detNota.getProducto().equals(detEnt.getProducto())) {
						detNota.setEstadoEntrega(DetalleNotaVenta.ESTADO_ENTREGA_DESPACHO);
						detalleNotaVentaDao.actualizar(detNota);
					}
				}
			}
			nota.setEstadoVenta(NotaVenta.ESTADO_VENTA_NOTA_VENTA_DESPACHO);
			notaVentaDao.actualizar(nota);
			// se compromete la transaccion
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ServiceException(e,
					"No ha sido posible ingresar la Entrega.");
		}
	}
	
	public void verificaEntregas(List<Entrega> entregas) throws ServiceException {
		try{
			//TODO: Falta construir este proceso el cual deberia ejecutarse al modificar
			//la entrega, esto aun se esta validando
			for(Entrega ent:entregas){
				ent.getClass();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e,"No se ha podido verificar la entrega");
		}
	}

}
