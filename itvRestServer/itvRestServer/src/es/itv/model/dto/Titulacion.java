package es.itv.model.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class Titulacion {
	
	private long id;

	private String nombre;

	private String plan;
	
	private String creditos;
	
	private String cursos;
	
	private String centro;
	
	private String categoria;
	
	public Titulacion(){};
	
	public Titulacion(long id, String nombre, String plan, String creditos, String cursos, String centro, String categoria)
	{
		this.id = id;
		this.nombre = nombre;
		this.plan = plan;
		this.creditos = creditos;
		this.cursos = cursos;
		this.centro = centro;
		this.categoria = categoria;
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

	public String getPlan() {
		return this.plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getCreditos() {
		return this.creditos;
	}

	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}

	public String getCursos() {
		return this.cursos;
	}

	public void setCursos(String cursos) {
		this.cursos = cursos;
	}

	public String getCentro() {
		return this.centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String toString() {
		try {
			return new JSONObject().put("id", this.id)
					.put("nombre", this.nombre).put("plan", this.plan)
					.put("creditos", this.creditos).put("cursos", this.cursos)
					.put("centro", this.centro)
					.put("categoria", this.categoria).toString();
		} catch (JSONException e) {
			return null;
		}
	}
}
