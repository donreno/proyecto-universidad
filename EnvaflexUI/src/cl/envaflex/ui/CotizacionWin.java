package cl.envaflex.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Window;

import cl.envaflex.jpa.dao.ClienteDao;
import cl.envaflex.jpa.dao.DespachoDao;
import cl.envaflex.jpa.dao.DetalleNotaVentaDao;
import cl.envaflex.jpa.dao.EntregaDao;
import cl.envaflex.jpa.dao.NotaVentaDao;
import cl.envaflex.jpa.dao.ProductoDao;
import cl.envaflex.jpa.dao.VehiculoDao;
import cl.envaflex.jpa.model.Cliente;
import cl.envaflex.jpa.model.DetalleNotaVenta;
import cl.envaflex.jpa.model.Empleado;
import cl.envaflex.jpa.model.NotaVenta;
import cl.envaflex.jpa.model.Producto;
import cl.envaflex.service.DespachoService;
import cl.envaflex.service.NotaVentaService;
import cl.envaflex.ui.util.Constantes;
import cl.envaflex.util.SpringContext;


public class CotizacionWin extends Window {
	
	
	private static final long serialVersionUID = 2941411525021845459L;
	private String errorMessage = "";
	
	private ClienteDao cDao;
	private ProductoDao pDao;
	private NotaVentaDao nvDao;
	private DetalleNotaVentaDao dvtaDao;
	private NotaVentaService nvService;
	
	public CotizacionWin(){
		SpringContext context = SpringContext.getInstance();
		//Instanciación de DAOS
		cDao = (ClienteDao) context.getBean(ClienteDao.BEAN_NAME);
		pDao = (ProductoDao) context.getBean(ProductoDao.BEAN_NAME);
		nvDao = (NotaVentaDao) context.getBean(NotaVentaDao.BEAN_NAME);
		nvService = (NotaVentaService) context.getBean(NotaVentaService.BEAN_NAME);
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
	
	public List<NotaVenta> findCotizaciones(Long idCliente,Long num, Date fechaDesde,Date fechaHasta){
		return nvDao.findCotizaciones(idCliente,num, fechaDesde,fechaHasta);
	}
	
	//metodos de las nuevas cotizaciones
	
	public boolean validarCotizacion(NotaVenta cotiz, List detalles){
		boolean valida;
		valida = (cotiz.getCliente()!=null 
				&& detalles!=null
				&& detalles.size()>0);
		if(!valida){
			setErrorMessage("La Cotización no es valida, porfavor comprobar " +
					"que se haya seleccionado un cliente,\n o que hayan productos ingresados.");
		}
		return valida;
	}
	
	 
	public List<DetalleNotaVenta> addDetalleCotizacionNueva(DetalleNotaVenta detCotiz, List<DetalleNotaVenta> detallesCotiz){
		boolean esta = false;
		for(DetalleNotaVenta dvta:detallesCotiz){
			if(dvta.getProducto().getIdProducto() == detCotiz.getProducto().getIdProducto()){
				esta=true;
				break;
			}
		}
		if(!esta){
			detallesCotiz.add(detCotiz);
		}
		return detallesCotiz;
	}
	
	public void actualizarTotalDetalleNueva(DetalleNotaVenta detalle){
		//bigdecimal zero
		BigDecimal zero = new BigDecimal(0);
		//se obtienen los componentes
		Decimalbox punitario =(Decimalbox)getFellow("punitarionbox");
		Decimalbox cantidad = (Decimalbox)getFellow("cantidadnbox");
		Decimalbox total = (Decimalbox)getFellow("totalnbox");
		System.out.println(punitario.getValue().toString());
		System.out.println(cantidad.getValue().toString());
		//se asigna un valor 0 a aquellos que tengan valores nulos
		if(detalle.getPrecioUnitario()==null
				|| zero.compareTo(detalle.getPrecioUnitario())>0){
			detalle.setPrecioUnitario(zero);
		}
		punitario.setValue(detalle.getPrecioUnitario());
		if(detalle.getCantidadProducto()==null
				|| zero.compareTo(detalle.getCantidadProducto())>0){
			detalle.setCantidadProducto(zero);
		}
		cantidad.setValue(detalle.getCantidadProducto());
		//se multiplican los valores y se calcula el total
		BigDecimal totalDecimal = detalle.getPrecioUnitario().multiply(detalle.getCantidadProducto()).setScale(0, BigDecimal.ROUND_UP);
		System.out.println(totalDecimal.toString());
		total.setValue(totalDecimal);
		detalle.setTotalProducto(totalDecimal);
	}
	
	public NotaVenta actualizaDatosNuevaCotiz(List<DetalleNotaVenta> detalles,NotaVenta cotiz){
		BigDecimal sum = new BigDecimal(0);
		for(DetalleNotaVenta detalle:detalles){
			BigDecimal valor = detalle.getCantidadProducto().multiply(detalle.getPrecioUnitario());
			detalle.setTotalProducto(valor);
			sum = sum.add(valor);
		}
		sum = sum.setScale(0, RoundingMode.UP);
		BigDecimal iva = sum.multiply(Constantes.IVA).setScale(0, RoundingMode.UP);
		BigDecimal total = sum.add(iva);
		total = total.setScale(0, RoundingMode.UP);
		//se asignan los valores a la cotizacion
		cotiz.setTotal(total);
		cotiz.setIva(iva);
		cotiz.setTotalNeto(sum);
		//se asignan los valores a los componentes
		((Decimalbox)getFellow("netoNuevaCotizlb")).setValue(sum);
		((Decimalbox)getFellow("ivaNuevaCotizlb")).setValue(iva);
		((Decimalbox)getFellow("totalNuevaCotizlb")).setValue(total);
		return cotiz;
	}
	
	public boolean ingresarCotizacion(NotaVenta cotiz,List detalles){
		boolean ok = true;
		HashMap param = new HashMap();
		cotiz.setCliente(cotiz.getCliente());
		Empleado emp = (Empleado)getDesktop().getSession().getAttribute("USUARIO_SESION");
		cotiz.setEmpleado(emp);
		cotiz.setEstadoVenta(NotaVenta.ESTADO_VENTA_COTIZ);
		try {
			nvService.crear(cotiz);
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			ok = false;
			e.printStackTrace();
		}
		return ok;
	}
	
	
	//funciones para la modificación
	public List<DetalleNotaVenta> findDetalleForCotiz(NotaVenta cotiz){
		return dvtaDao.findDetalleForNota(cotiz.getIdNotaVenta());
	}
	
	public NotaVenta actualizaDatosModifCotiz(List<DetalleNotaVenta> detalles,NotaVenta cotiz){
		BigDecimal sum = new BigDecimal(0);
		for(DetalleNotaVenta detalle:detalles){
			BigDecimal valor = detalle.getCantidadProducto().multiply(detalle.getPrecioUnitario());
			detalle.setTotalProducto(valor);
			sum = sum.add(valor);
		}
		//se redondea el sum
		sum = sum.setScale(0, RoundingMode.UP);
		BigDecimal iva = sum.multiply(Constantes.IVA).setScale(0, RoundingMode.UP);
		BigDecimal total = sum.add(iva);
		//se redondean los valores
		total = total.setScale(0, RoundingMode.UP);
		//se asignan los valores a la cotizacion
		cotiz.setTotal(total);
		cotiz.setIva(iva);
		cotiz.setTotalNeto(sum);
		//se asignan los valores a los componentes
		((Decimalbox)getFellow("netoModifCotizlb")).setValue(sum);
		((Decimalbox)getFellow("ivaModifCotizlb")).setValue(iva);
		((Decimalbox)getFellow("totalModifCotizlb")).setValue(total);
		return cotiz;
	}
	
	public void actualizarTotalDetalleModif(DetalleNotaVenta detalle){
		//se crea un bigDecimal 0
		BigDecimal zero = new BigDecimal(0);
		//se obtienen los componentes
		Decimalbox punitario =(Decimalbox)getFellow("punitariombox");
		Decimalbox cantidad = (Decimalbox)getFellow("cantidadmbox");
		Decimalbox total = (Decimalbox)getFellow("totalmbox");
		System.out.println(punitario.getValue().toString());
		System.out.println(cantidad.getValue().toString());
		//se asigna un valor 0 a aquellos que tengan valores nulos o menores a 0
		if(detalle.getPrecioUnitario()==null
				|| zero.compareTo(detalle.getPrecioUnitario())>0){
			detalle.setPrecioUnitario(zero);
		}
		punitario.setValue(detalle.getPrecioUnitario());
		if(detalle.getCantidadProducto()==null
				|| zero.compareTo(detalle.getCantidadProducto())>0){
			detalle.setCantidadProducto(zero);
		}
		cantidad.setValue(detalle.getCantidadProducto());
		//se multiplican los valores y se calcula el total
		BigDecimal totalDecimal = detalle.getPrecioUnitario().multiply(detalle.getCantidadProducto()).setScale(0, BigDecimal.ROUND_UP);
		System.out.println(totalDecimal.toString());
		total.setValue(totalDecimal);
		detalle.setTotalProducto(totalDecimal);
	}
	
	public boolean aplicarCambioModifDetalle(DetalleNotaVenta det){
		try{
			dvtaDao.actualizar(det);
			return true;
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			return false;
		}
	}
	
	public boolean ingresarDetalleModif(DetalleNotaVenta det){
		try{
			dvtaDao.insertar(det);
			return true;
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			return false;
		}
	}
	
	public boolean iniciarNotaVenta(NotaVenta cotiz){
		try{
			cotiz.setEstadoVenta(NotaVenta.ESTADO_VENTA_NOTA_VENTA_INICIAL);
			nvDao.actualizar(cotiz);
			return true;
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			return false;
		}
	}

	public boolean modifcarCotizacion(NotaVenta cotiz,List detalles){
		boolean ok = true;
		cotiz.setDetallesNotaVenta(detalles);
		try {
			nvService.actualizar(cotiz);
		} catch (Exception e) {
			setErrorMessage(e.getMessage());
			ok = false;
			e.printStackTrace();
		}
		return ok;
	}
	
	public boolean anularNotaVenta(NotaVenta nvta){
		//se crea el proceso
		try{
			nvService.anulaNotaVenta(nvta);
			return true;
		}catch(Exception e){
			setErrorMessage(e.getMessage());
		}
		return false;
	}
}
