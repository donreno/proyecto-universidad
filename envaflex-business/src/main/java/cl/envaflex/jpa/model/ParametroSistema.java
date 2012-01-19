package cl.envaflex.jpa.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ParametroSistema
 *
 */
@Entity
@NamedQueries({
@NamedQuery(name="ParametroSistema.findParametroByNombre",
		query="SELECT param FROM ParametroSistema param " +
			  "WHERE param.nombreParametro = :nom")
		
})
public class ParametroSistema implements Serializable {

	//CONSTANTES DE PARAMETROS DE SISTEMA
	@Transient
	public static final String IVA = "IVA";
	@Transient
	public static final String MAXIMO_VENTA = "MAXIMO_VENTA";
	@Transient
	public static final String MARGEN_ENTREGA = "MARGEN_ENTREGA";
	@Transient
	public static final String RECARGO_EXPRESS = "RECARGO_EXPRESS";
	   
	@Id
	@Column(name="NOMBRE_PARAMETRO",length=8,unique=true)
	private String nombreParametro;
	@Column(name="VALOR",length=250)
	private String valor;
	@Column(name="TIPO_VALOR")
	private String tipoValor;
	private static final long serialVersionUID = 1L;

	public ParametroSistema() {
		super();
	}   
	
	public String getNombreParametro() {
		return this.nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}   
	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}   
	public String getTipoValor() {
		return this.tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}
   
}
