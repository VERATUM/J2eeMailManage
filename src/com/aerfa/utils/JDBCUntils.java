package com.aerfa.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUntils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
    public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	public static DataSource getDataSource() {
		return ds;
	}
}
