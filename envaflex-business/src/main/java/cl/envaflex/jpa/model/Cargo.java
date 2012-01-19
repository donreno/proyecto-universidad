package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cargo
 *
 */
@Entity
public class Cargo implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@Column(name="ID_CARGO")
	private String idCargo;
	@Column(name="NOMBRE_CARGO")
	private String nombreCargo;
	@Column(name="DESC_CARGO")
	private String descCargo;

	public Cargo() {
		super();
	}   
	
	public String getIdCargo() {
		return this.idCargo;
	}

	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}   
	public String getNombreCargo() {
		return this.nombreCargo;
	}

	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}   
	public String getDescCargo() {
		return this.descCargo;
	}

	public void setDescCargo(String descCargo) {
		this.descCargo = descCargo;
	}
   
}
