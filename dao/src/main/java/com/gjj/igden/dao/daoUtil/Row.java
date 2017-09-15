package com.gjj.igden.dao.daoUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Adam Dziedzic
 */
public class Row {

	private static Map<String, Class> TYPE;

	static {
		TYPE = new HashMap<>();
		TYPE.put("INTEGER", Integer.class);
		TYPE.put("INT UNSIGNED", Long.class);
		TYPE.put("INT", Integer.class);
		TYPE.put("TINYINT", Byte.class);
		TYPE.put("SMALLINT", Short.class);
		TYPE.put("BIGINT", Long.class);
		TYPE.put("REAL", Float.class);
		TYPE.put("FLOAT", Float.class);
		TYPE.put("FLOAT UNSIGNED", Float.class);
		TYPE.put("DOUBLE", Double.class);
		TYPE.put("DECIMAL", BigDecimal.class);
		TYPE.put("NUMERIC", BigDecimal.class);
		TYPE.put("BOOLEAN", Boolean.class);
		TYPE.put("CHAR", String.class);
		TYPE.put("VARCHAR", String.class);
		TYPE.put("LONGVARCHAR", String.class);
		TYPE.put("DATE", Date.class);
		TYPE.put("TIME", Time.class);
		TYPE.put("TIMESTAMP", Timestamp.class);
		TYPE.put("SERIAL", Integer.class);
		TYPE.put("DATETIME", Timestamp.class);
		TYPE.put("BIT", Boolean.class);
		// ...
	}

	List<Entry<Object, Class>> row;

	private Row() {
		row = new ArrayList<>();
	}

	static void formTable(ResultSet rs, List<Row> table)
					throws SQLException {
		if (rs == null) {
			return;
		}
		ResultSetMetaData rsmd;
		rsmd = rs.getMetaData();
		int NumOfCol = rsmd.getColumnCount();
		while (rs.next()) {
			Row currentRow = new Row();
			for (int i = 1; i <= NumOfCol; i++) {
				currentRow.add(rs.getObject(i), rsmd.getColumnTypeName(i));
			}
			table.add(currentRow);
		}
	}

	private <T> void add(T data) {
		row.add(new AbstractMap.SimpleImmutableEntry<>(data, data.getClass()));
	}

	private void add(Object data, String sqlType) {
		Class castType = Row.TYPE.get(sqlType.toUpperCase());
		try {
			if (data == null) {
				this.add("NULL");
			} else {
				this.add(castType.cast(data));
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			Logger lgr = Logger.getLogger(Row.class.getName());
			lgr.log(Level.SEVERE, e.getMessage() + " Add the type " + sqlType +
							" to the TYPE hash map in the Row class.", e);
			throw e;
		}
	}
}