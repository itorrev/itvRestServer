package es.itv.model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionManager
{
	private DataSource dataSource;
	
	private static final Logger log = Logger.getLogger(ConnectionManager.class);

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection()
	{
		Connection con = null;
		try
		{
			con = this.dataSource.getConnection();
		}
		catch(SQLException e)
		{
			log.error("ConnectionManager.getConnection() Error obteniendo conexión del DataSource", e);
		}
		return con;
	}

	public void close(Connection con)
	{
		try
		{
			if(con!=null) con.close();
		}
		catch(SQLException e)
		{
			log.error("ConnectionManager.getConnection() Error en el cierre de la conexión.", e);
		}
		finally
		{
			con = null;
		}
	}

	public void close(Statement s)
	{
		try
		{
			if(s!=null) s.close();
		}
		catch(SQLException e)
		{
			log.error("ConnectionManager.getConnection() Error en el cierre de la Statement", e);
		}
		finally
		{
			s = null;
		}
	}

	public void close(ResultSet rs)
	{
		try
		{
			if(rs!=null) rs.close();
		}
		catch(SQLException e)
		{
			log.error("ConnectionManager.getConnection() Error en el cierre el ResultSet", e);
		}
		finally
		{
			rs = null;
		}
	}

	public void close(ResultSet rs, Statement ps, Connection con)
	{
		close(rs);
		close(ps);
		close(con);
	}

	public void close(Statement ps, Connection con)
	{
		close(ps);
		close(con);
	}
}
