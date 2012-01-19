package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: DetalleNotaVenta
 *
 */
@Entity
@NamedQueries(value={
@NamedQuery(name="detalleNotaVenta.findDetalleForNota",query="SELECT det FROM DetalleNotaVenta det " +
		"WHERE notaVenta_idNotaVenta = :idnota "),
@NamedQuery(name="detalleNotaVenta.findDetalleForNewEntrega",query="SELECT det FROM DetalleNotaVenta det " +
		"WHERE notaVenta_idNotaVenta = :idnota " +
		"AND estadoEntrega = 0 ")		
		
})
public class DetalleNotaVenta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final int ESTADO_ENTREGA_INICIAL = 0;
	@Transient
	public static final int ESTADO_ENTREGA_DESPACHO = 1;
	@Transient
	public static final int ESTADO_ENTREGA_CERRADA = 1;

	   
	@Id
	@GeneratedValue
	@Column(name="ID_DET_ENTREGA")
	private Long idDetalleNotaVenta;
	@Column(name="TOTAL_PRODUCTO")
	private BigDecimal totalProducto;
	@Column(name="CANTIDAD_PRODUCTO")
	private BigDecimal cantidadProducto;
	@Column(name="CANTIDAD_ENTREGA")
	private BigDecimal cantidadEntrega;
	@Column(name="PRECIO_UNITARIO")
	private BigDecimal precioUnitario;
	@Column(name="RESTO_ENTREGA")
	private BigDecimal restoEntrega;
	@Column(name="ESTADO_ENTREGA")
	private int estadoEntrega;
	
	@ManyToOne
	@JoinColumn(name="ID_PRODUCTO", insertable=false,updatable=false)
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name="ID_NOTA_VENTA", insertable=false,updatable=false)
	private NotaVenta notaVenta;

	public DetalleNotaVenta() {
		super();
		cantidadEntrega = BigDecimal.ZERO;
		cantidadProducto = BigDecimal.ZERO;
	}
	
	public Long getIdDetalleNotaVenta() {
		return this.idDetalleNotaVenta;
	}

	public void setIdDetalleNotaVenta(Long idDetalleNotaVenta) {
		this.idDetalleNotaVenta = idDetalleNotaVenta;
	}
	   
	public BigDecimal getTotalProducto() {
		return this.totalProducto;
	}

	public void setTotalProducto(BigDecimal totalProdutco) {
		this.totalProducto = totalProdutco;
	}   
	public BigDecimal getCantidadProducto() {
		return this.cantidadProducto;
	}

	public void setCantidadProducto(BigDecimal cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}   
	public BigDecimal getCantidadEntrega() {
		return this.cantidadEntrega;
	}

	public void setCantidadEntrega(BigDecimal cantidadEntrega) {
		this.cantidadEntrega = cantidadEntrega;
	}   
	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Producto getProducto() {
		return producto;
	}

	public void setRestoEntrega(BigDecimal restoEntrega) {
		this.restoEntrega = restoEntrega;
	}

	public BigDecimal getRestoEntrega() {
		return restoEntrega;
	}

	public void setEstadoEntrega(int estadoEntrega) {
		this.estadoEntrega = estadoEntrega;
	}

	public int getEstadoEntrega() {
		return estadoEntrega;
	}
	
	public NotaVenta getNotaVenta() {
		return notaVenta;
	}

	public void setNotaVenta(NotaVenta notaVenta) {
		this.notaVenta = notaVenta;
	}

	@Transient
	public String getTextoEstadoEntrega(){
		if(ESTADO_ENTREGA_DESPACHO == estadoEntrega){
			return "En Despacho";
		}else{
			return "Vigente";
		}
	}
   
}
