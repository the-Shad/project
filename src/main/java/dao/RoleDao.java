package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import dao.rsh.RoleRSH;
import exceptions.ApplicationException;


public class RoleDao{


	public static HashMap<String, Long> getRoleMap(BasicDataSource dataSource) throws  ApplicationException {
		try(Connection c = dataSource.getConnection()){
			return new QueryRunner().query(c, "SELECT * FROM role", new RoleRSH());
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
		
	}

}
