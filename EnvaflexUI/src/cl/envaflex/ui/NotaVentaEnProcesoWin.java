package cl.envaflex.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import cl.envaflex.jpa.dao.ClienteDao;
import cl.envaflex.jpa.dao.DetalleNotaVentaDao;
import cl.envaflex.jpa.dao.NotaVentaDao;
import cl.envaflex.jpa.dao.ParametroSistemaDao;
import cl.envaflex.jpa.dao.ProductoDao;
import cl.envaflex.jpa.model.Cliente;
import cl.envaflex.jpa.model.DetalleEntrega;
import cl.envaflex.jpa.model.DetalleNotaVenta;
import cl.envaflex.jpa.model.Entrega;
import cl.envaflex.jpa.model.NotaVenta;
import cl.envaflex.jpa.model.ParametroSistema;
import cl.envaflex.jpa.model.Producto;
import cl.envaflex.service.EntregaService;
import cl.envaflex.service.NotaVentaService;
import cl.envaflex.service.exception.ServiceException;
import cl.envaflex.ui.util.Constantes;
import cl.envaflex.util.SpringContext;

public class NotaVentaEnProcesoWin extends Window {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3161849597765247638L;
	private static final String NUEVA_SOL_TOTALDB = "nuevaSolTotaldb";
	private static final String NUEVA_SOL_IVADB = "nuevaSolIvadb";
	private static final String NUEVA_SOL_NETODB = "nuevaSolNetodb";
	private String errorMessage = "";
	
	private ClienteDao cDao;
	private ProductoDao pDao;
	private NotaVentaDao nvDao;
	private DetalleNotaVentaDao dvtaDao;
	private ParametroSistemaDao psisDao;
	private EntregaService entService;
	private NotaVentaService notaVentaService;
	
	public NotaVentaEnProcesoWin(){
		SpringContext context = SpringContext.getInstance();
		//Instanciación de DAOS
		cDao = (ClienteDao) context.getBean(ClienteDao.BEAN_NAME);
		pDao = (ProductoDao) context.getBean(ProductoDao.BEAN_NAME);
		nvDao = (NotaVentaDao) context.getBean(NotaVentaDao.BEAN_NAME);
		dvtaDao = (DetalleNotaVentaDao) context.getBean(DetalleNotaVentaDao.BEAN_NAME);
		psisDao = (ParametroSistemaDao) context.getBean(ParametroSistemaDao.BEAN_NAME);
		
		notaVentaService = (NotaVentaService) context.getBean(NotaVentaService.BEAN_NAME);
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
	
	public List<Cliente> findAllClientes(){
		return cDao.findAll();
	}
	
	public List<Producto> findAllProductos(){
		return pDao.findAll();
	}
	
	public List<NotaVenta> findNotasVta(Long idCliente,Long num, Date fechaDesde,Date fechaHasta){
		
		return nvDao.findNotasVtaEnProceso(idCliente,num,fechaDesde,fechaHasta);
	}
	
	public Entrega nuevaEntregaOrigen(NotaVenta notaVta){
		Entrega ent = new Entrega();
		//se obtienen los componentes
		Decimalbox nuevaSolNetodb = (Decimalbox)getFellow(NUEVA_SOL_NETODB);
		Decimalbox nuevaSolIvadb = (Decimalbox)getFellow(NUEVA_SOL_IVADB);
		Decimalbox nuevaSolTotaldb = (Decimalbox)getFellow(NUEVA_SOL_TOTALDB);
		//se copian valores de la venta en la entrega
		ent.setNotaVenta(notaVta);
		ent.setIva(notaVta.getIva());
		ent.setTotal(notaVta.getTotal());
		ent.setTotalNeto(notaVta.getTotalNeto());
		//se actualizan los valores de los componentes
		nuevaSolNetodb.setValue(new BigDecimal(0));
		nuevaSolIvadb.setValue(new BigDecimal(0));
		nuevaSolTotaldb.setValue(new BigDecimal(0));
		//se procesa el detalle de la nota de venta
		List<DetalleNotaVenta> detallesNota = findDetalleForNewEntrega(notaVta);
		ent.setDetallesEntrega(new ArrayList<DetalleEntrega>());
		for(DetalleNotaVenta dvta:detallesNota){
			DetalleEntrega detEnt = new DetalleEntrega();
			detEnt.setProducto(dvta.getProducto());;
			detEnt.setCantidadEntrega(dvta.getCantidadProducto());
			detEnt.setMaxCantidadProducto(dvta.getCantidadProducto());
			detEnt.setPrecioUnitario(dvta.getPrecioUnitario());
			detEnt.setTotalProducto(dvta.getTotalProducto());
			ent.getDetallesEntrega().add(detEnt);
		}
		return ent;
	}
	
	//funciones para la nueva Solicitud de Entrega
	public List<DetalleNotaVenta> findDetalleForNota(NotaVenta notaVta){
		return dvtaDao.findDetalleForNota(notaVta.getIdNotaVenta());
	}
	
	//funciones para la nueva Solicitud de Entrega
	public List<DetalleNotaVenta> findDetalleForNewEntrega(NotaVenta notaVta){
		return dvtaDao.findDetalleForNota(notaVta.getIdNotaVenta());
	}
	
	/*
	 * Obtencion de parametros de sistema
	 */
	public BigDecimal getRecargoExpress(){
		ParametroSistema psis = psisDao.findParametroByNombre(ParametroSistema.RECARGO_EXPRESS);
		BigDecimal valor = new BigDecimal(psis.getValor());
		return valor;
	}
	
	public int getMargenEntrega(){
		ParametroSistema psis = psisDao.findParametroByNombre(ParametroSistema.MARGEN_ENTREGA);
		Integer val = new Integer(psis.getValor());
		return val.intValue();
	}
	
	
	/**
	 * Metodo para actualizar los valores visibles de la Entrega
	 */
	public void actualizarEntrega(Entrega entr, List<DetalleEntrega> detsEntrega){
		//checkbox de recargo express
		Checkbox checkExpress = (Checkbox)getFellow("checkExpress");
		//detalle totalizacion
		Decimalbox nuevaSolNetodb = (Decimalbox)getFellow(NUEVA_SOL_NETODB);
		Decimalbox nuevaSolIvadb = (Decimalbox)getFellow(NUEVA_SOL_IVADB);
		Decimalbox nuevaSolTotaldb = (Decimalbox)getFellow(NUEVA_SOL_TOTALDB);
		
		BigDecimal totalNeto = new BigDecimal(0);
		if(checkExpress.isChecked()){
			totalNeto = totalNeto.add(getRecargoExpress());
		}
		
		//se suman los detalles de las entregas
		for(DetalleEntrega detEnt: detsEntrega){
			BigDecimal valor = detEnt.getCantidadEntrega().multiply(detEnt.getPrecioUnitario());
			totalNeto = totalNeto.add(valor);
		}
		
		BigDecimal iva = totalNeto.multiply(Constantes.IVA).setScale(0, BigDecimal.ROUND_UP);
		BigDecimal total = totalNeto.add(iva);
		//se actualizan los totales
		entr.setTotal(total);
		entr.setTotalNeto(totalNeto);
		entr.setIva(iva);
		//se actualizan los componetes
		nuevaSolIvadb.setValue(entr.getIva());
		nuevaSolNetodb.setValue(entr.getTotalNeto());
		nuevaSolTotaldb.setValue(entr.getTotal());
	}
	
	public boolean validarCamposEntrega(Entrega ent,List<DetalleEntrega> detsEntrega){
		if(detsEntrega.size()<1){
			setErrorMessage("El listado de entrega no puede estar vacio.");
			return false;
		}
		if(ent.getFechaEstimadaEntrega()==null){
			setErrorMessage("La fecha estimada de entrega no puede estar vacia");
			return false;
		}
		return true;
	}
	
	public boolean procesarEntrega(Entrega ent, NotaVenta ntvta, List<DetalleEntrega> detsEntrega){
		Radiogroup localRadio = (Radiogroup)getFellow("localRadio");
		String valorRadio = localRadio.getSelectedItem().getLabel();
		if("Si".equals(valorRadio)){
			valorRadio = "S";
		}else{
			valorRadio = "N";
		}
		ent.setEnLocal(valorRadio);
		//se crea el proceso
		
		//se ingresan los parametros
		try {
			entService.ingresaEntrega(ntvta, findDetalleForNota(ntvta), ent, detsEntrega);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return false;
	}
	
	public boolean anularNotaVenta(NotaVenta nvta){
		//se crea el proceso
		try {
			notaVentaService.anulaNotaVenta(nvta);
			return true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
