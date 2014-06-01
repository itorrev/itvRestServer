package es.itv.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import es.itv.exception.BaseException;
import es.itv.model.data.dao.AsignaturaDAO;
import es.itv.model.data.dao.TitulacionDAO;
import es.itv.model.dto.Asignatura;
import es.itv.model.dto.Titulacion;

@Path("/titulacion")
public class TitulacionResource {

	private static Logger log = Logger.getLogger(TitulacionResource.class);
	
	private TitulacionDAO titulacionDAO;
	
	private AsignaturaDAO asignaturaDAO;

	@GET 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
	public Response getTitulacionList()
	{
		List<Titulacion> lista = new ArrayList<Titulacion>();
		
		try
		{
			lista = this.titulacionDAO.getList();
		}
		catch (BaseException e) 
		{
			log.error("getPersonaList() - Error obteniendo lista de objetos", e);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return Response.ok(lista.toString()).build();
	}
	
	@GET @Path("{id}/asignatura")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAsignaturaList(@PathParam("id") long id)
	{
		List<Asignatura> lista = new ArrayList<Asignatura>();
		
		try
		{
			lista = this.asignaturaDAO.getListByTitulacionId(id);
		}
		catch (BaseException e) 
		{
			log.error("getPersonaList() - Error obteniendo lista de objetos", e);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return Response.ok(lista.toString()).build();
	}
	
	public void setTitulacionDAO(TitulacionDAO titulacionDAO) {
		this.titulacionDAO = titulacionDAO;
	}

	public void setAsignaturaDAO(AsignaturaDAO asignaturaDAO) {
		this.asignaturaDAO = asignaturaDAO;
	}
}
