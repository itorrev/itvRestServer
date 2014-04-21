package es.itv.test;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.itv.exception.BaseException;
import es.itv.model.data.dao.PersonaDAO;
import es.itv.model.dto.Persona;

@Path("/hello")
public class Hello {
	
	private PersonaDAO personaDAO;

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		
//		String test = "test";
//		System.out.print(test);
//		
//		try {
//			List<Persona> lista = this.personaDAO.getList();
//			
//			lista.size();
//		} catch (BaseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// TODO listar
//		
//		// TODO buscar por id
//		
//		// TODO actualizar
//		
//		// TODO borrar
//		
//		// TODO insertar de nuevo
		
		
		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
				+ "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	}

	public PersonaDAO getPersonaDAO() {
		return personaDAO;
	}

	public void setPersonaDAO(PersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}

}
