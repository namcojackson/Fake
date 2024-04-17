package com.canon.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.apps.jtf.aom.transaction.TransactionScope;


public class CanonCommonDao {

	public static String getSchema(){
		   
		
		String schemaName="";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		String qry="select sys_context('userenv','db_name') dbname , sys_context('userenv','session_user') sch_name from dual";
		CanonCommonUtil util = new CanonCommonUtil();
		
		try {
			conn = (Connection) TransactionScope
					.getConnection();
			if (conn != null) {
				statement = conn.prepareStatement(qry);
				if (statement != null) {
					
					resultSet = statement.executeQuery(qry);
					while (resultSet.next()) {
						schemaName =resultSet.getString("sch_name");
						util.logMsg(false,"CanonCommonDao.getSchema", "DATABASE SCHEMA : "+schemaName);
					}
				} else {
					util.logMsg(true,"CanonCommonDao.getSchema", "UNABLE_TO_CREATE_STATEMENT");
				}
			} else {
				util.logMsg(true,"CanonCommonDao.getSchema", "UNABLE TO GETCONNECTION");
				
			}
		}

		catch (Exception ex1) {
			util.logMsg(true,"CanonCommonDao.getSchema", ex1.getMessage());
		} finally {

			try {
				if (statement != null)
					statement.close();
			} catch (Exception eExp) {
			}
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception eExp) {
			}
			try {
				if (conn != null)
					TransactionScope.releaseConnection(conn);
			} catch (Exception eExp) {
			}
		}
		return schemaName;
	}	
}
