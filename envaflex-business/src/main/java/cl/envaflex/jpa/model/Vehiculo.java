package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vehiculo
 *
 */
@Entity

public class Vehiculo implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name="ID_VEHICULO")
	private int idVehiculo;
	@Column(name="PATENTE_VEHICULO",unique=true)
	private String patenteVehiculo;
	@Column(name="MARCA")
	private String marca;
	@Column(name="MODELO")
	private String modelo;
	private static final long serialVersionUID = 1L;

	public Vehiculo() {
		super();
	}   
	public int getIdVehiculo() {
		return this.idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}   
	public String getPatenteVehiculo() {
		return this.patenteVehiculo;
	}

	public void setPatenteVehiculo(String patenteVehiculo) {
		this.patenteVehiculo = patenteVehiculo;
	}   
	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}   
	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@Transient
	public String getDescripcion(){
		return marca+" "+modelo;
	}
   
}
