package cl.envaflex.rest.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Cargo")
public class CargoVO {
	
	private String idCargo;
	private String nombreCargo;
	private String descCargo;
	
	public String getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}
	public String getNombreCargo() {
		return nombreCargo;
	}
	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}
	public String getDescCargo() {
		return descCargo;
	}
	public void setDescCargo(String descCargo) {
		this.descCargo = descCargo;
	}

}
