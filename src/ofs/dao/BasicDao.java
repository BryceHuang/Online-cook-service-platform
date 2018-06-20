package ofs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ofs.DbConnect.DbConnection;

public class BasicDao {
	protected Connection cn = null;
	protected PreparedStatement st = null;
	protected ResultSet rs = null;

	public BasicDao() {
		super();
	}

	protected void initConnection() {
		cn = DbConnection.getConnection();
	}

	protected void closeConnection() {
		if (cn != null)
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	protected void closeStatement() {
		if (st != null)
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	protected void closeResultSet() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
