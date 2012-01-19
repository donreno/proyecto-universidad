package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DetalleEntrega
 *
 */
@Entity
@NamedQueries(value={
@NamedQuery(name="detalleEntrega.findDetalleForEntrega",query="SELECT det FROM DetalleEntrega det " +
		"WHERE Entrega_idEntrega = :idEntrega ")
})
public class DetalleEntrega implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	@Column(name="ID_DET_ENTREGA")
	private Long idDetalleEntrega;
	@Column(name="CANTIDAD_ENTREGA")
	private BigDecimal cantidadEntrega;
	@Column(name="PRECIO_UNITARIO")
	private BigDecimal precioUnitario;
	@Column(name="TOTAL_PRODUCtO")
	private BigDecimal totalProducto;
	@Transient
	private BigDecimal maxCantidadProducto;
	@Transient
	private boolean esNuevo;
	
	@ManyToOne
	@JoinColumn(name="ID_PRODUCTO", insertable=false,updatable=false)
	private Producto producto;
	

	public DetalleEntrega() {
		super();
		this.producto = new Producto();
		this.cantidadEntrega = new BigDecimal(0);
		this.maxCantidadProducto = new BigDecimal(1);
		this.totalProducto =  new BigDecimal(0);
	}   
	public Long getIdDetalleEntrega() {
		return this.idDetalleEntrega;
	}

	public void setIdDetalleEntrega(Long idDetalleEntrega) {
		this.idDetalleEntrega = idDetalleEntrega;
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
	public void setTotalProducto(BigDecimal totalProducto) {
		this.totalProducto = totalProducto;
	}
	public BigDecimal getTotalProducto() {
		return totalProducto;
	}
	public void setMaxCantidadProducto(BigDecimal maxCantidadProducto) {
		this.maxCantidadProducto = maxCantidadProducto;
	}
	public BigDecimal getMaxCantidadProducto() {
		return maxCantidadProducto;
	}
	public void setEsNuevo(boolean esNuevo) {
		this.esNuevo = esNuevo;
	}
	public boolean isEsNuevo() {
		return esNuevo;
	}
   
}
