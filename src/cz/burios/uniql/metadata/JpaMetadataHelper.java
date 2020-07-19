package cz.burios.uniql.metadata;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cz.burios.uniql.persistence.internal.helper.DatabaseField;
import cz.burios.uniql.persistence.internal.helper.DatabaseTable;

public class JpaMetadataHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param conn
	 * @param schema
	 * @return
	 * @throws Exception
	 */
	public static List<DatabaseTable> getTables(Connection conn, String schema) throws Exception {
		List<DatabaseTable> tables = new ArrayList<>();
		DatabaseMetaData dbmd = conn.getMetaData();
		String catalog = null;
		String table = null;
		String[] types = { "TABLE" };
		
		try ( ResultSet rs = dbmd.getTables(catalog, schema, table, types); ) {
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				DatabaseTable dbTable = new DatabaseTable(tableName, schema);
				tables.add(dbTable);				
			}
		}
		return tables;
	}
	
	/**
	 * 
	 * @param conn
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static List<DatabaseField> getColumns(Connection conn, String tableName) throws Exception {
		DatabaseMetaData dbmd = conn.getMetaData();
		List<DatabaseField> result = new ArrayList<>();
		try ( ResultSet rs = dbmd.getColumns(null, null, tableName, null); ) {
			while(rs.next()) {
				if (rs.getString("TABLE_NAME").equalsIgnoreCase(tableName)) {
					DatabaseField dbf = new DatabaseField(rs.getString("COLUMN_NAME"), tableName);
					dbf.setSqlType(rs.getInt("DATA_TYPE"));
					dbf.setLength(rs.getInt("COLUMN_SIZE"));
					dbf.setPrecision(rs.getInt("DECIMAL_DIGITS"));

					String nullable = rs.getString("IS_NULLABLE");
					dbf.setNullable(nullable.trim().equals("NO")); 
					
					result.add(dbf);
				}
			}
		}
		return result;		
	}
}
