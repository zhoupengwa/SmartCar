package cn.edu.xhu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.xhu.consts.JdbcConsts;

public class JdbcUtils {

	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
		protected Connection initialValue() {
			Connection conn = null;
			try {
				Class.forName(JdbcConsts.DRIVER);
				conn = DriverManager.getConnection(JdbcConsts.URL, JdbcConsts.USERNAME, JdbcConsts.PASSWORD);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return conn;
		};
	};

	/**
	 * 获取连接;
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return connectionHolder.get();
	}

	/**
	 * 获取预编译语句
	 * 
	 * @param sql
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getPreparedStatement(String sql, Connection conn) throws SQLException {
		PreparedStatement pstm = conn.prepareStatement(sql);
		return pstm == null ? null : pstm;
	}

	/**
	 * 设置参数
	 * 
	 * @param pstm
	 * @param params
	 * @throws SQLException
	 */
	public static void setParams(PreparedStatement pstm, Object[] params) throws SQLException {
		if (params != null) {
			for (int i = 1; i <= params.length; i++) {
				pstm.setObject(i, params[i - 1]);
			}
		}
	}

//	/**
//	 * 开启事物
//	 * 
//	 * @throws ClassNotFoundException
//	 * @throws SQLException
//	 */
//	public static void beginTransaction() throws ClassNotFoundException, SQLException {
//		Connection conn = connectionHolder.get();
//		conn.setAutoCommit(false);
//	}
//
//	/**
//	 * 提交事物
//	 * 
//	 * @throws SQLException
//	 */
//	public static void commitTransaction() throws SQLException {
//		Connection conn = connectionHolder.get();
//		conn.commit();
//		conn.setAutoCommit(true);
//	}
//
//	/**
//	 * 事物回滚
//	 * 
//	 * @throws SQLException
//	 */
//	public static void rollBackTransaction() throws SQLException {
//		Connection conn = connectionHolder.get();
//		if (conn != null) {
//			conn.rollback();
//		}
//	}

	/**
	 * 关闭连接
	 * 
	 * @param conn
	 * @param rs
	 * @param pstm
	 * @throws SQLException
	 */
	public static void close(Connection conn, ResultSet rs, PreparedStatement pstm) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (pstm != null) {
			pstm.close();
		}
		if (conn != null) {
			conn.close();
			connectionHolder.remove();
		}
	}

}
