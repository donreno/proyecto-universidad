package cl.envaflex.jpa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Servicio
 *
 */
@Entity
public class Servicio implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_SERVICIO")
	private String idServicio;
	@Column(name="TITULO")
	private String titulo;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="URL")
	private String url;

	public String getIdServicio() {
		return idServicio;
	}


	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}


	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Servicio() {
		super();
	}
   
}
