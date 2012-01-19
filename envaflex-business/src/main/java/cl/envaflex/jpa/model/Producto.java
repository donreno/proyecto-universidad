package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.lang.String;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto
 *
 */
@Entity
public class Producto implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name="ID_PRODUCTO")
	private Long idProducto;
	@Column(name="NOMBRE_PRODUCTO",length=100)
	private String nombreProducto;
	@Column(name="DESC_PRODUCTO",length=500)
	private String descProducto;
	@Column(name="TIPO_UNIDAD",length=4)
	private String tipoUnidad;
	@Column(name="PRECIO_UNITARIO")
	private BigDecimal precioUnitario;
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_PRODUCTO", insertable=false, updatable=false)
	private TipoProducto tipoProducto;
	
	private static final long serialVersionUID = 1L;

	public Producto() {
		super();
	}   
	public Long getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	
	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}   
	public String getDescProducto() {
		return this.descProducto;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}   
	public String getTipoUnidad() {
		return this.tipoUnidad;
	}

	public void setTipoUnidad(String tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
   
}
