package es.itv.model.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class Persona {

	private long id;

	private String nombre;

	private String apellido;

	private String telefono;

	private String email;

	private String pais;

	private String ciudad;

	public Persona() {

	}

	public Persona(String nombre, String apellido, String telefono,
			String email, String pais, String ciudad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.pais = pais;
		this.ciudad = ciudad;
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

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String toString() {
		try {
			return new JSONObject().put("id", this.id).put("nombre", this.nombre)
					.put("apellido", this.apellido).put("telefono", this.telefono)
					.put("email", this.email).put("pais", this.pais)
					.put("ciudad", this.ciudad).toString();
		} catch (JSONException e) {
			return null;
		}
	}
}
