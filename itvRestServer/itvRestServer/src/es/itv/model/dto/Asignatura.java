package es.itv.model.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class Asignatura {

	private long id;
	
	private String nombre;
	
	private String creditos;
	
	private String tipo;
	
	private String curso;
	
	private String cuatrimestre;
	
	private long idTitulacion;
	
	public Asignatura(){};
	
	public Asignatura(long id, String nombre, String creditos, String tipo, String curso, String cuatrimestre, long idTitulacion)
	{
		this.id = id;
		this.nombre = nombre;
		this.creditos = creditos;
		this.tipo = tipo;
		this.cuatrimestre = cuatrimestre;
		this.curso = curso;
		this.idTitulacion = idTitulacion;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCreditos() {
		return this.creditos;
	}

	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCuatrimestre() {
		return this.cuatrimestre;
	}

	public void setCuatrimestre(String cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}

	public long getIdTitulacion() {
		return this.idTitulacion;
	}

	public void setIdTitulacion(long idTitulacion) {
		this.idTitulacion = idTitulacion;
	}

	public String toString() {
		try {
			return new JSONObject().put("id", this.id)
					.put("nombre", this.nombre).put("tipo", this.tipo)
					.put("creditos", this.creditos).put("curso", this.curso)
					.put("cuatrimestre", this.cuatrimestre).toString();
		} catch (JSONException e) {
			return null;
		}
	}
}
