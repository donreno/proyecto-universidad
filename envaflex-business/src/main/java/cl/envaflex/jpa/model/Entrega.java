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

/**
 * Entity implementation class for Entity: Entrega
 *
 */
@Entity
@NamedQueries(value={
@NamedQuery(name="entrega.findEntregasSolicitadas",
		query="SELECT entrega FROM Entrega entrega " +
		"WHERE (entrega.estadoEntrega = 0 OR entrega.estadoEntrega = 2) " +
		"AND (:num IS NULL OR entrega.idEntrega = :num) " +
		"AND ((:fecha_desde IS NULL AND :fecha_hasta IS NULL) " +
		"OR (:fecha_desde IS NULL AND (:fecha_hasta IS NOT NULL AND entrega.fechaEstimadaEntrega <= :fecha_hasta)) " +
		"OR (:fecha_hasta IS NULL AND (:fecha_desde IS NOT NULL AND entrega.fechaEstimadaEntrega >= :fecha_desde)) " +
		"OR (:fecha_hasta IS NOT NULL AND :fecha_desde IS NOT NULL AND entrega.fechaEstimadaEntrega BETWEEN :fecha_desde AND :fecha_hasta)) "),
@NamedQuery(name="entrega.findEntregasForDespacho",
		query="SELECT entrega FROM Entrega entrega " +
		"WHERE entrega.despacho.id = :idDespacho "),
@NamedQuery(name="entrega.findEntregasForNotaVenta",
		query="SELECT entrega FROM Entrega entrega " +
		"WHERE entrega.notaVenta.id = :idNota ")
})
public class Entrega implements Serializable {
	
	@Transient
	public static final int ESTADO_INICIADA = 0;
	@Transient
	public static final int ESTADO_ASIGNADA = 1;
	@Transient
	public static final int ESTADO_PENDIENTE = 2;
	@Transient
	public static final int ESTADO_CERRADA = 3;
	   
	@Id
	@GeneratedValue
	@Column(name="ID_ENTREGA")
	private Long idEntrega;
	@Column(name="FECHA_ENTREGA")
	private Date fechaEntrega;
	@Column(name="FECHA_ESTIMADA_ENTREGA")
	private Date fechaEstimadaEntrega;
	@Column(name="ESTADO_ENTREGA")
	private int estadoEntrega;
	@Column(name="RECARGO_ENTREGA")
	private BigDecimal recargoEntrega;
	@Column(name="IVA")
	private BigDecimal iva;
	@Column(name="totalNeta")
	private BigDecimal totalNeto;
	@Column(name="TOTAL")
	private BigDecimal total;
	@Column(name="EN_LOCAL")
	private String enLocal;

	private static final long serialVersionUID = 1L;
	
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ENTREGA")
	private List<DetalleEntrega> detallesEntrega;
	
	@ManyToOne
	@JoinColumn(name="ID_NOTA_VENTA", insertable=false,updatable=false)
	private NotaVenta notaVenta;
	
	@ManyToOne
	@JoinColumn(name="ID_DESPACHO", insertable=false, updatable=false)
	private Despacho despacho;

	public Entrega() {
		super();
	}
	
	public Long getIdEntrega() {
		return this.idEntrega;
	}

	public void setIdEntrega(Long idEntrega) {
		this.idEntrega = idEntrega;
	}
	
	public int getEstadoEntrega() {
		return this.estadoEntrega;
	}

	public void setEstadoEntrega(int estadoEntrega) {
		this.estadoEntrega = estadoEntrega;
	}
	public void setRecargoEntrega(BigDecimal recargoEntrega) {
		this.recargoEntrega = recargoEntrega;
	}
	public BigDecimal getRecargoEntrega() {
		return recargoEntrega;
	}
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	public BigDecimal getIva() {
		return iva;
	}
	public void setTotalNeto(BigDecimal totalNeto) {
		this.totalNeto = totalNeto;
	}
	public BigDecimal getTotalNeto() {
		return totalNeto;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getTotal() {
		return total;
	}

	public void setDetallesEntrega(List<DetalleEntrega> detallesEntrega) {
		this.detallesEntrega = detallesEntrega;
	}

	public List<DetalleEntrega> getDetallesEntrega() {
		return detallesEntrega;
	}

	public void setNotaVenta(NotaVenta notaVenta) {
		this.notaVenta = notaVenta;
	}

	public NotaVenta getNotaVenta() {
		return notaVenta;
	}

	public void setEnLocal(String enLocal) {
		this.enLocal = enLocal;
	}

	public String getEnLocal() {
		return enLocal;
	}
   
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaEstimadaEntrega() {
		return fechaEstimadaEntrega;
	}

	public void setFechaEstimadaEntrega(Date fechaEstimadaEntrega) {
		this.fechaEstimadaEntrega = fechaEstimadaEntrega;
	}

	public Despacho getDespacho() {
		return despacho;
	}

	public void setDespacho(Despacho despacho) {
		this.despacho = despacho;
	}

	@Transient
	public String getTextoEstadoDespacho(){
		if(estadoEntrega == ESTADO_INICIADA)
			return "Iniciada";
		if(estadoEntrega == ESTADO_PENDIENTE)
			return "Pendiente";
		if(estadoEntrega == ESTADO_ASIGNADA)
			return "Asignada";
		if(estadoEntrega == ESTADO_CERRADA)
			return "Cerrada";
		return "";
	}
	
	@Transient
	public void setTextoEstadoEntrega(String texto){
		if("Iniciada".equals(texto)){
			estadoEntrega = ESTADO_INICIADA;
		}
		if("Pendiente".equals(texto)){
			estadoEntrega = ESTADO_PENDIENTE;
		}
		if("Asignada".equals(texto)){
			estadoEntrega = ESTADO_ASIGNADA;
		}
		if("Cerrada".equals(texto)){
			estadoEntrega = ESTADO_CERRADA;
		}
	}
}
