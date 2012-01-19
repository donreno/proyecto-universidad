package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import cl.envaflex.util.Limpiable;

/**
 * Entity implementation class for Entity: NotaVenta
 *
 */
@Entity
@NamedQueries(value={
@NamedQuery(name="notaVenta.findCotizaciones",
		query="SELECT cotiz FROM NotaVenta cotiz " +
		"WHERE cotiz.estadoVenta = 0 " +
		"AND (:num IS NULL OR cotiz.idNotaVenta = :num) " +
		"AND (:cliente IS NULL OR cotiz.cliente.id = :cliente) " +
		"AND ((:fecha_desde IS NULL AND :fecha_hasta IS NULL) " +
		"OR (:fecha_desde IS NULL AND (:fecha_hasta IS NOT NULL AND cotiz.fechaCotizacion <= :fecha_hasta)) " +
		"OR (:fecha_hasta IS NULL AND (:fecha_desde IS NOT NULL AND cotiz.fechaCotizacion >= :fecha_desde)) " +
		"OR (:fecha_hasta IS NOT NULL AND :fecha_desde IS NOT NULL AND cotiz.fechaCotizacion BETWEEN :fecha_desde AND :fecha_hasta)) "),
@NamedQuery(name="notaVenta.findNotasVentaEnProceso",
		query="SELECT cotiz FROM NotaVenta cotiz " +
		"WHERE (cotiz.estadoVenta = 1 OR  cotiz.estadoVenta = 3) " +
		"AND (:num IS NULL OR cotiz.idNotaVenta = :num) " +
		"AND (:cliente IS NULL OR cotiz.cliente.id = :cliente) " +
		"AND ((:fecha_desde IS NULL AND :fecha_hasta IS NULL) " +
		"OR (:fecha_desde IS NULL AND (:fecha_hasta IS NOT NULL AND cotiz.fechaCotizacion <= :fecha_hasta)) " +
		"OR (:fecha_hasta IS NULL AND (:fecha_desde IS NOT NULL AND cotiz.fechaCotizacion >= :fecha_desde)) " +
		"OR (:fecha_hasta IS NOT NULL AND :fecha_desde IS NOT NULL AND cotiz.fechaCotizacion BETWEEN :fecha_desde AND :fecha_hasta)) ")		
})
public class NotaVenta implements Serializable,Limpiable {
	
	@Transient
	public static final int ESTADO_VENTA_COTIZ = 0;
	@Transient
	public static final int ESTADO_VENTA_NOTA_VENTA_INICIAL = 1;
	@Transient
	public static final int ESTADO_VENTA_NOTA_VENTA_ANULADA = 2;
	@Transient
	public static final int ESTADO_VENTA_NOTA_VENTA_DESPACHO = 3;
	@Transient
	public static final int ESTADO_VENTA_NOTA_VENTA_CERRADA = 4;

	   
	@Id
	@GeneratedValue
	@Column(name="ID_NOTA_VENTA")
	private int idNotaVenta;
	@ManyToOne
	@JoinColumn(name="ID_EMPLEADO")
	private Empleado empleado;
	@Column(name="FECHA_COTIZACION")
	private Date fechaCotizacion;
	@Column(name="ESTADO_VENTA")
	private int estadoVenta;
	@Column(name="TOTAL_NETO")
	private BigDecimal totalNeto;
	@Column(name="IVA")
	private BigDecimal iva;
	@Column(name="TOTAL")
	private BigDecimal total;
	@Column(name="FECHA_INICIO_VENTA")
	private Date fechaInicioVenta;
	@Column(name="ESTADO_COTIZACION")
	private int estadoCotizacion;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="ID_NOTA_VENTA")
	private List<DetalleNotaVenta> detallesNotaVenta;
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE", insertable=false, updatable=false)
	private Cliente cliente;
	
	private static final long serialVersionUID = 1L;

	public NotaVenta() {
		super();
	}
	
	public int getIdNotaVenta() {
		return this.idNotaVenta;
	}

	public void setIdNotaVenta(int idNotaVenta) {
		this.idNotaVenta = idNotaVenta;
	}     
	public Date getFechaCotizacion() {
		return this.fechaCotizacion;
	}

	public void setFechaCotizacion(Date fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}   
	public int getEstadoVenta() {
		return this.estadoVenta;
	}

	public void setEstadoVenta(int estadoVenta) {
		this.estadoVenta = estadoVenta;
	}   
	public BigDecimal getTotalNeto() {
		return this.totalNeto;
	}

	public void setTotalNeto(BigDecimal totalNeto) {
		this.totalNeto = totalNeto;
	}
	
	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}   
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}   
	public Date getFechaInicioVenta() {
		return this.fechaInicioVenta;
	}

	public void setFechaInicioVenta(Date fechaInicioVenta) {
		this.fechaInicioVenta = fechaInicioVenta;
	}   
	public int getEstadoCotizacion() {
		return this.estadoCotizacion;
	}

	public void setEstadoCotizacion(int estadoCotizacion) {
		this.estadoCotizacion = estadoCotizacion;
	}
	public void setDetallesNotaVenta(List<DetalleNotaVenta> detallesNotaVenta) {
		this.detallesNotaVenta = detallesNotaVenta;
	}
	public List<DetalleNotaVenta> getDetallesNotaVenta() {
		return detallesNotaVenta;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public void limpiar() {
		NotaVenta nueva = new NotaVenta();
		setIdNotaVenta(nueva.getIdNotaVenta());
		setDetallesNotaVenta(null);
		setEstadoCotizacion(nueva.getEstadoCotizacion());
		setEstadoVenta(nueva.getEstadoVenta());
		setFechaCotizacion(null);
		setFechaInicioVenta(null);
		setCliente(null);
		setIva(null);
		setTotal(null);
		setTotalNeto(null);
	}
   
}
