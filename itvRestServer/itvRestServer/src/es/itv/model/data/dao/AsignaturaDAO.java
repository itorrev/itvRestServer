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
import es.itv.model.dto.Asignatura;

public class AsignaturaDAO {

	private static Logger log = Logger.getLogger(AsignaturaDAO.class);
	
	private ConnectionManager connectionManager;
	
	private static final int COLUMNA_ID = 1;
	
	private static final int COLUMNA_NOMBRE = 2;
	
	private static final int COLUMNA_CREDITOS = 3;
	
	private static final int COLUMNA_TIPO = 4;
	
	private static final int COLUMNA_CUATRIMESTRE = 5;
	
	private static final int COLUMNA_CURSO = 6;
	
	//private static final int COLUMNA_ID_TITULACION = 7;
	
	private static final String QUERY_BY_TITULACION_ID_LIST = "select * from asignatura where id_titulacion = ? order by id";
	
	public List<Asignatura> getListByTitulacionId(long idTitulacion) throws BaseException
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
		
		List<Asignatura> lista = new ArrayList<Asignatura>();
		
		try
		{
			ps = con.prepareStatement(QUERY_BY_TITULACION_ID_LIST, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ps.setLong(1, idTitulacion);
			
			rs = ps.executeQuery();
			
			if((rs == null) || (!rs.next()))
			{
				throw new BaseException("No se han recuperado registros");
			}
			
			rs.beforeFirst();
			
			while(rs.next())
			{
				Asignatura a = new Asignatura();
				
				a.setId(rs.getLong(COLUMNA_ID));
				a.setNombre(rs.getString(COLUMNA_NOMBRE));
				a.setCreditos(rs.getString(COLUMNA_CREDITOS));
				a.setTipo(rs.getString(COLUMNA_TIPO));
				a.setCuatrimestre(rs.getString(COLUMNA_CUATRIMESTRE));
				a.setCurso(rs.getString(COLUMNA_CURSO));
				a.setIdTitulacion(idTitulacion);
				
				lista.add(a);
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
