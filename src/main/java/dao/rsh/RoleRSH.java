package dao.rsh;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.dbutils.ResultSetHandler;

public class RoleRSH implements ResultSetHandler<HashMap<String, Long>> {
	@Override
	public HashMap<String, Long> handle(ResultSet rs) throws SQLException {
		HashMap<String, Long> res = new HashMap<>();
		while(rs.next()) {
			res.put(rs.getString("role_name"), rs.getLong("id"));
		}
		return res;
	}
}
