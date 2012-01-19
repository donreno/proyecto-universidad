package cl.envaflex.rest.object;

import java.io.Serializable;

public class CargoObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7768204852632300082L;
	
	private String nombre;
	private String desc;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
