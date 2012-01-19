package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Menu
 *
 */
@Entity
public class Menu implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name="ID_MENU")
	private Long idMenu;
	@Column(name="NOMBRE",length=100)
	private String nombre;
	@Column(name="DESCRIPCION",length=500)
	private String descripcion;
	private static final long serialVersionUID = 1L;

	public Menu() {
		super();
	}   
	public Long getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
   
}
