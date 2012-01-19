package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empleado
 *
 */

@Entity
public class Empleado implements Serializable {

	private static final long serialVersionUID = 1726252538042055625L;
	@Id
	@Column(name="RUT_EMPLEADO",length=12)
	private String rutEmpleado;
	@Column(name="DV_EMPLEADO",length=1,nullable=false)
	private String dvEmpleado;
	@Column(name="NOMBRE_EMPLEADO")
	private String nombreEmpleado;
	@Column(name="APELLIDO_MATERNO")
	private String apellidoMaterno;
	@Column(name="APELLIDO_PATERNO")
	private String apellidoPaterno;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="USER_NAME",unique=true,nullable=false)
	private String user;
	@Column(name="ES_USUARIO")
	private Boolean esUsuario;
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;
	
	@ManyToOne
	@JoinColumn(name="ID_CARGO")
	private Cargo cargo;

	public Empleado() {
		super();
	}   
	
	public String getRutEmpleado() {
		return this.rutEmpleado;
	}

	public void setRutEmpleado(String rutEmpleado) {
		this.rutEmpleado = rutEmpleado;
	}   
	public String getDvEmpleado() {
		return this.dvEmpleado;
	}

	public void setDvEmpleado(String dvEmpleado) {
		this.dvEmpleado = dvEmpleado;
	}   
	public String getNombreEmpleado() {
		return this.nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}   
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}   
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}   
	public Boolean getEsUsuario() {
		return this.esUsuario;
	}

	public void setEsUsuario(Boolean esUsuario) {
		this.esUsuario = esUsuario;
	}   
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}   
   
}
