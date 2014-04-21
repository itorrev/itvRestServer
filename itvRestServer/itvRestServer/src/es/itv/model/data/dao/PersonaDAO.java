package es.itv.model.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.itv.exception.BaseException;
import es.itv.model.data.ConnectionManager;
import es.itv.model.dto.Persona;


public class PersonaDAO {

	private static Logger log = Logger.getLogger(PersonaDAO.class);
	
	private ConnectionManager connectionManager;
	
	private static final int COLUMNA_ID = 1;
	
	private static final int COLUMNA_NOMBRE = 2;
	
	private static final int COLUMNA_APELLIDO = 3;
	
	private static final int COLUMNA_TELEFONO = 4;
	
	private static final int COLUMNA_EMAIL = 5;
	
	private static final int COLUMNA_PAIS = 6;
	
	private static final int COLUMNA_CIUDAD = 7;
	
	private static final String QUERY_LIST = "select * from Persona order by id";
	
	private static final String QUERY_SEARCH = "select * from Persona where id = ?";
	
	private static final String QUERY_INSERT = "insert into Persona (Nombre,Apellido,Telefono,email,Pais,Ciudad) VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String QUERY_UPDATE = "update Persona set Nombre = ?, Apellido = ?, Telefono = ?, email = ?, pais = ?, ciudad = ? where id = ?";
	
	private static final String QUERY_DELETE = "delete from Persona where id = ?";
	
	public List<Persona> getList() throws BaseException
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//Obtiene la conexion y asigna la query a ejecutar
		con = this.connectionManager.getConnection();
		
		if (con == null)
		{
			throw new BaseException("Error al establecer la conexion con BBDD");
		}	
		
		List<Persona> lista = new ArrayList<Persona>();
		
		
		try {
			ps = con.prepareStatement(QUERY_LIST, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = ps.executeQuery();
			
			if((rs == null) || (!rs.next()))
			{
				throw new BaseException("No se han recuperado registros");
			}
			
			rs.beforeFirst();
			
			while(rs.next())
			{
				Persona p = new Persona();
				
				p.setId(rs.getInt(COLUMNA_ID));
				p.setNombre(rs.getString(COLUMNA_NOMBRE));
				p.setApellido(rs.getString(COLUMNA_APELLIDO));
				p.setTelefono(rs.getString(COLUMNA_TELEFONO));
				p.setEmail(rs.getString(COLUMNA_EMAIL));
				p.setPais(rs.getString(COLUMNA_PAIS));
				p.setCiudad(rs.getString(COLUMNA_CIUDAD));
				
				lista.add(p);
			}
			
		} catch (SQLException e) {
			log.error("Error SQL durante la recuperacion de la lista", e);
			throw new BaseException("Error SQL durante la recuperacion de la lista");
		}
		finally
		{
			this.connectionManager.close(rs, ps, con);			
		}
		
		return lista;
	}
	
	public Persona getPersona(long id) throws BaseException
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//Obtiene la conexion y asigna la query a ejecutar
		con = this.connectionManager.getConnection();
		
		if (con == null)
		{
			throw new BaseException("Error al establecer la conexion con BBDD");
		}	
		
		Persona p = new Persona();
		
		try
		{
			ps = con.prepareStatement(QUERY_SEARCH, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			if((rs == null) || (!rs.next()))
			{
				throw new BaseException("No se han recuperado registros");
			}
			
			rs.first();
			
			p.setId(rs.getInt(COLUMNA_ID));
			p.setNombre(rs.getString(COLUMNA_NOMBRE));
			p.setApellido(rs.getString(COLUMNA_APELLIDO));
			p.setTelefono(rs.getString(COLUMNA_TELEFONO));
			p.setEmail(rs.getString(COLUMNA_EMAIL));
			p.setPais(rs.getString(COLUMNA_PAIS));
			p.setCiudad(rs.getString(COLUMNA_CIUDAD));			
		}
		catch (Exception e) 
		{
			log.error("Error SQL durante la recuperacion del registro", e);
			throw new BaseException("Error SQL durante la recuperacion del registro");
		}
		finally
		{
			this.connectionManager.close(rs, ps, con);			
		}
		return p;
	}
	
	public void updatePersona(Persona p) throws BaseException
	{
		Connection con = null;
		PreparedStatement ps = null;
		
		//Obtiene la conexion y asigna la query a ejecutar
		con = this.connectionManager.getConnection();
		
		if (con == null)
		{
			throw new BaseException("Error al establecer la conexion con BBDD");
		}	
		
		try
		{
			ps = con.prepareStatement(QUERY_UPDATE);
			
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellido());
			ps.setString(3, p.getTelefono());
			ps.setString(4, p.getEmail());
			ps.setString(5, p.getPais());
			ps.setString(6, p.getCiudad());
			ps.setLong(7, p.getId());
			
			ps.executeUpdate();
		}
		catch (Exception e) 
		{
			log.error("Error SQL durante la actualización del registro", e);
			throw new BaseException("Error SQL durante la actualización del registro");
		}
		finally
		{
			this.connectionManager.close(ps, con);			
		}
	}
	
	public void deletePersona(long id) throws BaseException
	{
		Connection con = null;
		PreparedStatement ps = null;
		
		//Obtiene la conexion y asigna la query a ejecutar
		con = this.connectionManager.getConnection();
		
		if (con == null)
		{
			throw new BaseException("Error al establecer la conexion con BBDD");
		}	
		
		try
		{
			ps = con.prepareStatement(QUERY_DELETE);
			
			ps.setLong(1, id);
			
			ps.executeUpdate();
		}
		catch (Exception e) 
		{
			log.error("Error SQL durante la actualización del registro", e);
			throw new BaseException("Error SQL durante la actualización del registro");
		}
		finally
		{
			this.connectionManager.close(ps, con);			
		}
	}
	
	public long insertPersona(Persona p) throws BaseException
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//Obtiene la conexion y asigna la query a ejecutar
		con = this.connectionManager.getConnection();
		
		if (con == null)
		{
			throw new BaseException("Error al establecer la conexion con BBDD");
		}
		
		long generatedKey = -1;
		
		try
		{
			ps = con.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellido());
			ps.setString(3, p.getTelefono());
			ps.setString(4, p.getEmail());
			ps.setString(5, p.getPais());
			ps.setString(6, p.getCiudad());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs != null && rs.next())
			{
				generatedKey = rs.getLong(1);
			}
		}
		catch (Exception e) 
		{
			log.error("Error SQL durante la actualización del registro", e);
			throw new BaseException("Error SQL durante la actualización del registro");
		}
		finally
		{
			this.connectionManager.close(ps, con);			
		}
		
		return generatedKey;
	}

	public ConnectionManager getConnectionManager() {
		return this.connectionManager;
	}

	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
}
