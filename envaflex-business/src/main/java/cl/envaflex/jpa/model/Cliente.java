package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
public class Cliente implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name="ID_CLIENTE")
	private Long idCliente;
	@Column(name="RUT_CLIENTE",unique=true,nullable=false)
	private String rutCliente;
	@Column(name="DV_CLIENTE",nullable=false)
	private String dvCliente;
	@Column(name="NOMBRE_CLIENTE")
	private String nombreCliente;
	private static final long serialVersionUID = 1L;

	public Cliente() {
		super();
	}   
	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}   
	public String getRutCliente() {
		return this.rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}   
	public String getDvCliente() {
		return this.dvCliente;
	}

	public void setDvCliente(String dvCliente) {
		this.dvCliente = dvCliente;
	}   
	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
   
}
