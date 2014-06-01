package es.itv.model.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.itv.exception.BaseException;
import es.itv.model.data.ConnectionManager;
import es.itv.model.dto.Titulacion;

public class TitulacionDAO {

	private static Logger log = Logger.getLogger(TitulacionDAO.class);
	
	private ConnectionManager connectionManager;
	
	private static final int COLUMNA_ID = 1;
	
	private static final int COLUMNA_NOMBRE = 2;
	
	private static final int COLUMNA_PLAN = 3;
	
	private static final int COLUMNA_CREDITOS = 4;
	
	private static final int COLUMNA_CURSOS = 5;
	
	private static final int COLUMNA_CENTRO = 6;
	
	private static final int COLUMNA_CATEGORIA = 7;
	
	private static final String QUERY_LIST = "select * from titulacion order by id";
	
	//private static final String QUERY_SEARCH = "select * from titulacion where id = ?";
	
	public List<Titulacion> getList() throws BaseException
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
		
		List<Titulacion> lista = new ArrayList<Titulacion>();
		
		try
		{
			ps = con.prepareStatement(QUERY_LIST, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = ps.executeQuery();
			
			if((rs == null) || (!rs.next()))
			{
				throw new BaseException("No se han recuperado registros");
			}
			
			rs.beforeFirst();
			
			while(rs.next())
			{
				Titulacion t = new Titulacion();
				
				t.setId(rs.getLong(COLUMNA_ID));
				t.setNombre(rs.getString(COLUMNA_NOMBRE));
				t.setPlan(rs.getString(COLUMNA_PLAN));
				t.setCreditos(rs.getString(COLUMNA_CREDITOS));
				t.setCursos(rs.getString(COLUMNA_CURSOS));
				t.setCentro(rs.getString(COLUMNA_CENTRO));
				t.setCategoria(rs.getString(COLUMNA_CATEGORIA));
				
				lista.add(t);
			}
		} 
		catch (SQLException e) 
		{
			log.error("Error SQL durante la recuperacion de la lista", e);
			throw new BaseException("Error SQL durante la recuperacion de la lista");
		}
		finally
		{
			this.connectionManager.close(rs, ps, con);			
		}
		
		return lista;
	}
	
	public ConnectionManager getConnectionManager() {
		return this.connectionManager;
	}

	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
}
