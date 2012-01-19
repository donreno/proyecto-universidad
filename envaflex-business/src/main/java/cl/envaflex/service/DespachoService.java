package cl.envaflex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cl.envaflex.jpa.dao.DespachoDao;
import cl.envaflex.jpa.dao.EntregaDao;
import cl.envaflex.jpa.model.Despacho;
import cl.envaflex.jpa.model.Entrega;
import cl.envaflex.service.exception.ServiceException;

@Service(DespachoService.BEAN_NAME)
public class DespachoService extends GenericService {

	public static final String BEAN_NAME = "DespachoService";

	@Autowired
	public DespachoService(@Qualifier(DespachoDao.BEAN_NAME) DespachoDao dao,
			@Qualifier(EntregaDao.BEAN_NAME) EntregaDao entregaDao) {
		this.despachoDao = dao;
		this.entregaDao = entregaDao;
	}

	private DespachoDao despachoDao;
	private EntregaDao entregaDao;

	/**
	 * Permite crear y programar un despacho
	 * 
	 * @param desp
	 * @param entregas
	 * @throws ServiceException
	 */
	public void programarDespacho(Despacho desp, List<Entrega> entregas)
			throws ServiceException {
		desp.setEntregas(entregas);
		try {
			despachoDao.insertar(desp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e,
					"No se ha podido programar el despacho");
		}
	}

	/**
	 * Permite modificar un despacho
	 * 
	 * @param desp
	 * @param entregas
	 * @param entregasCerradas
	 * @param entregasPendientes
	 * @throws ServiceException
	 */
	public void modificarDespacho(Despacho desp, List<Entrega> entregas)
			throws ServiceException {
		try {
			List<Entrega> entregasCerradas = new ArrayList<Entrega>();
			List<Entrega> entregasPendientes = new ArrayList<Entrega>();
			for (Entrega ent : entregas) {
				if (ent.getEstadoEntrega() == Entrega.ESTADO_CERRADA) {
					entregasCerradas.add(ent);
				}
				if (ent.getEstadoEntrega() == Entrega.ESTADO_PENDIENTE) {
					entregasPendientes.add(ent);
				}
				entregaDao.actualizar(ent);
			}
			// se remueven los elementos que se encuentran pendientes
			entregas.removeAll(entregasPendientes);
			// se verifica si se encuentran todas cerradas
			int cerradas = entregasCerradas.size();
			int size = entregas.size();
			// si estan todas cerradas y/o pendientes, se cierra el despacho
			if (cerradas == size) {
				desp.setEstadoDespacho(Despacho.ESTADO_CERRADO);
				despachoDao.actualizar(desp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e,
					"No se ha podido modificar el despacho");
		}
	}

}
