package com.canon.oracle.custapp.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import canon.apps.common.CanonConstants;

/**
 * Title:		CanonCustAppUtil<br>
 * Description:	This is the main class of billing issues Functions<br>
 * <p>
 * CopyRight:	Canon<br>
 * Company:		Canon<br>
 * @author:		Subba<br>
 * @version:	1.0, (29-Sep-2005).<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 *
 * </pre>
 */

public class CanonCustAppUtil {
	
	/**
	 * CanonCustAppUtil constructor.
	 */
	public CanonCustAppUtil() {
		super();
	}
	/**
	 * This method will return the ARRAY of object
	 * <p>
	 * @return oracle.sql.ARRAY
	 * @param objBean Object.
	 * @param strObjBeanPkg java.lang.String.
	 * @param conn java.sql.Connection.
	 * @param strSqlRecTyp java.lang.String.
	 * @param strSqlTableTyp java.lang.String.
	 * @exception java.lang.Exception.
	 */
	public static ARRAY getArray(Object objBean, String strObjBeanPkg, Connection conn, String strSqlRecTyp, String strSqlTableTyp) throws SQLException, Exception {
		ARRAY recArray = null;		
		try {
			if(objBean == null || strObjBeanPkg == null || strSqlRecTyp == null || strSqlTableTyp == null) throw new Exception("input parameter should not be null");
			if(conn == null) throw new SQLException("connection should not be null");
			//System.out.println("hello1");
	    	Map map = (Map)conn.getTypeMap();
	    	//System.out.println("hello2" + Class.forName(strObjBeanPkg));
	    	map.put(strSqlRecTyp, Class.forName(strObjBeanPkg));
	    	//System.out.println("hello3" + strSqlTableTyp);
	    	ArrayDescriptor insDescriptor = ArrayDescriptor.createDescriptor(strSqlTableTyp, conn);
	    	//System.out.println("hello4" + insDescriptor.descType());
	    	recArray = new ARRAY(insDescriptor, conn, objBean);
	    	//System.out.println("hello5");
	    	
		}catch(SQLException eSQLExp) {
			System.err.println("in getArray eSQLExp " + eSQLExp.getMessage());
			eSQLExp.printStackTrace();
	    	//throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppUtil, Method:getArray()"));
	    }catch (Exception eExp) {
	    	System.err.println("in getArray eExp " + eExp.getMessage());
	    	eExp.printStackTrace();
	    	//throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppUtil, Method:getArray()"));
	    }    	
		return recArray;
	}
	
	/**
	 * This method will return the ARRAY of object
	 * <p>
	 * @return oracle.sql.ARRAY
	 * @param obj Object.
	 * @param conn java.sql.Connection.
	 * @param strSqlTableTyp java.lang.String.
	 * @exception java.lang.Exception.
	 */
	public static ARRAY getArray(Object obj, Connection conn, String strSqlTableTyp) throws SQLException, Exception {
		ARRAY recArray = null;
		try {
			if(obj == null || strSqlTableTyp == null) throw new Exception("input parameter should not be null");
			if(conn == null) throw new SQLException("connection should not be null");
			
	    	ArrayDescriptor insDescriptor = ArrayDescriptor.createDescriptor(strSqlTableTyp, conn);
	    	recArray = new ARRAY(insDescriptor, conn, obj);
	    	
		}catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppUtil, Method:getArray()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppUtil, Method:getArray()"));
	    }    	
		return recArray;
	}
	
	/**
	 * This method will return the Object
	 * <p>
	 * @return Object.
	 * @param objArr oracle.sql.ARRAY.
	 * @param strObjBeanPkg java.lang.String.
	 * @param conn java.sql.Connection.
	 * @param strSqlRecTyp java.lang.String.
	 * @exception java.lang.Exception.
	 */
	public static Object getRecordObject(ARRAY objArr, String strObjBeanPkg, Connection conn, String strSqlRecTyp) throws SQLException, Exception {
		
		try {
			if(objArr == null || strObjBeanPkg == null || strSqlRecTyp == null) throw new Exception("input parameter should not be null");
			if(conn == null) throw new SQLException("connection should not be null");
			
	    	Map map = (Map)conn.getTypeMap();
	    	map.put(strSqlRecTyp, Class.forName(strObjBeanPkg));
	    	return objArr.getArray();
	    	
		}catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppUtil, Method:getRecordObject()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppUtil, Method:getRecordObject()"));
	    }
	}
	/**
	 * This method format the double value.
	 *
	 * @return java.lang.String.
	 * @param dbl double.
	 */
	public static String getDoubleWithFormat(double dbl) {
		//if(dbl == -1.0) return "";
		DecimalFormat decFormat = new DecimalFormat("#,##0.00");
		decFormat.setGroupingSize(3);
		return decFormat.format(dbl);
	}
	
	public static String getFormattedDouble(double dbl) {		
		DecimalFormat decFormat = new DecimalFormat("#,##0.00");
		decFormat.setGroupingSize(3);
		return decFormat.format(dbl);
	}
	
	/**
	 * This method format the number.
	 *
	 * @return java.lang.String.
	 * @param iVal int.
	 */
	public static String getNumberWithFormat(int iVal) {
		if(iVal == -1) return "";		
		return NumberFormat.getInstance().format(iVal);
	}	
}
