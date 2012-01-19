package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Despacho
 *
 */
@Entity
@NamedQueries(value={
	@NamedQuery(name="Despacho.findDespachos",
			query="SELECT desp FROM Despacho desp " +
					"WHERE (:num IS NULL OR desp.idDespacho = :num) " +
					"AND (:estado IS NULL OR desp.estadoDespacho = :estado) " +
					"AND ((:fechaDesde IS NULL AND :fechaHasta IS NULL) " +
					"OR (:fechaDesde IS NULL AND (:fechaHasta IS NOT NULL AND desp.fecha <= :fechaHasta)) " +
					"OR (:fechaHasta IS NULL AND (:fechaDesde IS NOT NULL AND desp.fecha >= :fechaDesde)) " +
					"OR (:fechaHasta IS NOT NULL AND :fechaDesde IS NOT NULL AND desp.fecha BETWEEN :fechaDesde AND :fechaHasta)) ")
})
public class Despacho implements Serializable {
	
	private static final long serialVersionUID = -2061685423955819427L;
	@Transient
	public static final int ESTADO_PROGRAMADO = 0;
	@Transient
	public static final int ESTADO_ACTIVO = 1;
	@Transient
	public static final int ESTADO_CERRADO = 2;

	   
	@Id
	@GeneratedValue
	@Column(name="ID_DESPACHO")
	private Long idDespacho;
	@Column(name="FECHA",nullable=false)
	private Date fecha;
	@Column(name="ESTADO_DESPACHO",nullable=false)
	private int estadoDespacho;
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_DESPACHO")
	private List<Entrega> entregas;
	@ManyToOne
	@JoinColumn(name="ID_EMPLEADO")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name="ID_VEHICULO", insertable=false, updatable=false)
	private Vehiculo vehiculo;

	public Despacho() {
		super();
	}   
	public Long getIdDespacho() {
		return this.idDespacho;
	}

	public void setIdDespacho(Long idDespacho) {
		this.idDespacho = idDespacho;
	}   
	
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}   
	public int getEstadoDespacho() {
		return this.estadoDespacho;
	}

	public void setEstadoDespacho(int estadoDespacho) {
		this.estadoDespacho = estadoDespacho;
	}
	
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public List<Entrega> getEntregas() {
		return entregas;
	}
	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
	}
}
