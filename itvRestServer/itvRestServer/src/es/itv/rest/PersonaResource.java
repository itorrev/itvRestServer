package es.itv.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;

import es.itv.exception.BaseException;
import es.itv.model.data.dao.PersonaDAO;
import es.itv.model.dto.Persona;

@Path("/personas")
public class PersonaResource 
{
	private static Logger log = Logger.getLogger(PersonaResource.class);
	
	private PersonaDAO personaDAO;
	
	@GET 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getPersonaList()
	{
		List<Persona> lista = new ArrayList<Persona>();
		try {
			lista = this.personaDAO.getList();
		} catch (BaseException e) {
			log.error("getPersonaList() - Error obteniendo lista de objetos", e);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return Response.ok(lista.toString()).build();
	}

	@GET @Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getPersona(@PathParam("id") long id)
	{
		Persona p = null;
		try 
		{
			p = this.personaDAO.getPersona(id);
		} catch (BaseException e) {
			log.error("getPersona() - Error recuperando objeto", e);
		}
		
		if(p == null)
		{
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		else
		{
			return Response.ok(p).build();
		}
		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response insertPersona(Persona p)
	{
		long id = -1;
		try 
		{
			id = this.personaDAO.insertPersona(p);
		} catch (BaseException e) {
			log.error("getPersona() - Error insertando objeto", e);
		}
		if(id != -1)
		{
			p.setId(id);
			return Response.ok(p).build();
		}
		else
		{
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updatePersona(JAXBElement<Persona> element)
	{
		Persona p = element.getValue();
		try 
		{
			this.personaDAO.updatePersona(p);
		} catch (BaseException e) {
			log.error("getPersona() - Error actualizando objeto", e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok(p).build();
	}

	
	@DELETE
	@Path("{id}")
	public Response deletePersona(@PathParam("id") long id)
	{
		try {
			this.personaDAO.deletePersona(id);
		} catch (BaseException e) {
			log.error("getPersona() - Error borrando objeto", e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok().build();
	}
	
	@OPTIONS
	public Response options()
	{
		return Response.ok().build();
	}
	
	public PersonaDAO getPersonaDAO() {
		return personaDAO;
	}

	public void setPersonaDAO(PersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}
}
