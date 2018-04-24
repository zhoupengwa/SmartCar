package cn.edu.xhu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import cn.edu.xhu.util.JdbcUtils;

public class BaseDao {
	/**
	 * ≤È—Ø
	 * 
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public <T> List<T> query(String sql, Object[] params, Class<T> clazz) throws Exception {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		T object = null;
		List<T> objectList = new ArrayList<>();
		conn = JdbcUtils.getConnection();
		pstm = JdbcUtils.getPreparedStatement(sql, conn);
		JdbcUtils.setParams(pstm, params);
		rs = pstm.executeQuery();
		ResultSetMetaData rstm = rs.getMetaData();
		int columnCount = rstm.getColumnCount();
		while (rs.next()) {
			object = clazz.newInstance();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = rstm.getColumnName(i);
				Object columnValue = rs.getObject(columnName);
				// System.out.println(columnName+": "+columnValue);
				BeanUtils.setProperty(object, columnName, columnValue);
			}
			objectList.add(object);
		}
		JdbcUtils.close(conn, rs, pstm);
		return objectList;
	}

	/**
	 * –ﬁ∏ƒ
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public int update(String sql, Object[] params) throws Exception {
		Connection conn = null;
		PreparedStatement pstm = null;
		int count;
		conn = JdbcUtils.getConnection();
		pstm = JdbcUtils.getPreparedStatement(sql, conn);
		JdbcUtils.setParams(pstm, params);
		count = pstm.executeUpdate();
		JdbcUtils.close(conn, null, pstm);
		return count;
	}
}
