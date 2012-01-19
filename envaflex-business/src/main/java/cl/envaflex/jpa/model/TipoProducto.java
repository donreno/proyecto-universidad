package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoProducto
 *
 */
@Entity

public class TipoProducto implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name="ID_TIPO_PRODUCTO")
	private Long idTipoProducto;
	@Column(name="NOMBRE")
	private String nombreTipoProducto;
	@Column(name="DESCRIPCION")
	private String descTipoProducto;
	
	
	private static final long serialVersionUID = 1L;

	public TipoProducto() {
		super();
	}   
	public Long getIdTipoProducto() {
		return this.idTipoProducto;
	}

	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}   
	public String getNombreTipoProducto() {
		return this.nombreTipoProducto;
	}

	public void setNombreTipoProducto(String nombreTipoProducto) {
		this.nombreTipoProducto = nombreTipoProducto;
	}   
	public String getDescTipoProducto() {
		return this.descTipoProducto;
	}

	public void setDescTipoProducto(String descTipoProducto) {
		this.descTipoProducto = descTipoProducto;
	}
   
}
