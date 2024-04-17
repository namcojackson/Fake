//Start Changes for S21 by Mangala
//package com.canon.oracle.beans;
package com.canon.oracle.custapp.custinq.beans;
//End Changes for S21 by Mangala


import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
//import oracle.jdbc.driver.OracleTypes;

/**
 * Title:      CanonE389DffDataBean<br>
 * Description:   This is the main class of JSP DFF Utility Functions<br>
 * <p>
 * CopyRight:  Canon<br>
 * Company:    Canon<br>
 * @author:    Chandra Sekhar<br>
 * @version:   1.0, (06-Apr-2009)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
* <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 *
 * </pre>
 */

public class CanonE389DffDataBean
{

  private String attributeId;
  private String attributeValue;
  private String errmsg;

  public CanonE389DffDataBean()
  {
  }

  public String getattributeId()
  {
      return attributeId;
  }

  public void setattributeId(String s)
  {
      attributeId = s;
  }

  public String getattributeValue()
  {
      return attributeValue;
  }

  public void setattributeValue(String s)
  {
      attributeValue = s;
  }

  public void setErrMsg(String errmsg)
  {
      this.errmsg = errmsg;
  }

  public String getErrMsg()
  {
    if (errmsg == null|| errmsg.length()<1)
    {
      return null;
    }
    else
    {
      return errmsg;
    }
  }

  public String UploadData (Vector vAttrIds, Vector vAttrValues, String userId, String sourceApplication,
                            String sourceContext, String contextValue)
  throws Exception, SQLException
  {
    //OracleCallableStatement cs  = null;
   // OracleConnection conn = null;
	 // CanonCustAppDBUtil connUpdConnection = null;
	  Connection conn = null;
	  CallableStatement cs = null;
	  Connection nativeConn=null;
    StringBuffer sbCall   = new StringBuffer(200);
    System.out.println("!!!!!!!!!Inside UploadData");
    sbCall.append("{call CANON_E389_SQLS_PKG.UPLOAD_DATA(?, ?, ?, ?, ?, ?, ?, ?)}");
    //System.out.println("!!!!!!!!!After calling  CANON_E389_SQLS_PKG.UPLOAD_DATA");
    String[] strAttributeIds    = null;
    String[] strAttributeValues = null;

    ARRAY strAttrIdsArray    = null;
    ARRAY strAttrValuesArray = null;

    String xReturnStatus = null;
    String xErrorMsg     = null;
    

    try
    {
      //conn = (OracleConnection)TransactionScope.getConnection();
    	//connUpdConnection = new CanonCustAppDBUtil();
    	//conn = (Connection)(connUpdConnection.getConnection());
    	conn = TransactionScope.getConnection();
    	nativeConn = TransactionScope.nativeConnection(conn);
     // cs   = (OracleCallableStatement)conn.prepareCall(sbCall.toString());
    	cs = (CallableStatement)conn.prepareCall(sbCall.toString());

      strAttributeIds    = new String[vAttrIds.size()];
      strAttributeValues = new String[vAttrIds.size()];

      for (int i = 0; i < vAttrIds.size(); i++)
      {
        CanonE389DffDataBean obj1 = new CanonE389DffDataBean();
        obj1 = (CanonE389DffDataBean)vAttrIds.elementAt(i);
        strAttributeIds[i] = obj1.getattributeId();

        CanonE389DffDataBean obj2 = new CanonE389DffDataBean();
        obj2 = (CanonE389DffDataBean)vAttrValues.elementAt(i);
        strAttributeValues[i] = obj2.getattributeValue();
      }
      
       ArrayDescriptor arrayDescriptorIds = new ArrayDescriptor("JTF_VARCHAR2_TABLE_100", nativeConn);
	  strAttrIdsArray = new ARRAY(arrayDescriptorIds, nativeConn, strAttributeIds);
           
	  
	   
	  ArrayDescriptor arrayDescriptorValues = new ArrayDescriptor("JTF_VARCHAR2_TABLE_100", nativeConn);
	  strAttrValuesArray = new ARRAY(arrayDescriptorValues, nativeConn, strAttributeValues);
	  //System.out.println("!!!!!!!!!strAttrIdsArray = "+strAttrIdsArray);
	  //System.out.println("!!!!!!!!!strAttrValuesArray = "+strAttrValuesArray);

	 /*	   strAttrIdsArray = new oracle.sql.ARRAY(
	  oracle.apps.jtf.util.GeneralUtil.getArrayDescriptor(
                            "JTF_VARCHAR2_TABLE_100", nativeConn), conn, strAttributeIds);
	 
      strAttrValuesArray = new oracle.sql.ARRAY(
                            oracle.apps.jtf.util.GeneralUtil.getArrayDescriptor(
                            "JTF_VARCHAR2_TABLE_100", nativeConn), conn, strAttributeValues);
      System.out.println("!!!!!!!!!strAttrIdsArray = "+strAttrIdsArray);
	  System.out.println("!!!!!!!!!strAttrValuesArray = "+strAttrValuesArray);*/
      //Object strAttrIdsArray[] =(Object[]) ((Array)  strAttributeIds[i]).getArray();
      cs.setString(1, sourceApplication);
      cs.setString(2, sourceContext);
      cs.setString(3, contextValue);
      //cs.setInt   (4, Integer.parseInt(userId));
      cs.setString   (4, userId);
      if (strAttrIdsArray == null)
        cs.setNull(5, OracleTypes.ARRAY, "JTF_VARCHAR2_TABLE_100");
      else
    	//  System.out.println("!!!!!!!!!Inside if (strAttrIdsArray == null) strAttrIdsArray = "+strAttrIdsArray);
       // cs.setARRAY(5, strAttrIdsArray);
    	 cs.setObject(5, strAttrIdsArray);
    	// System.out.println("!!!!!!!!!After cs.setObject(5, strAttrIdsArray); ");
     // cs.setArray(5, CanonCustAppUtil.getArray(obj1, strHClassName, conn, strHSqlRecTyp, strHSqlTableTyp));
      if (strAttrValuesArray == null)
        cs.setNull(6, OracleTypes.ARRAY, "JTF_VARCHAR2_TABLE_100");
      else
        //cs.setARRAY(6, strAttrValuesArray);
    	  cs.setObject(6, strAttrValuesArray);

      cs.registerOutParameter(7, Types.VARCHAR);
      cs.registerOutParameter(8, Types.VARCHAR);
     // System.out.println("!!!!!!!!! Before cs.execute");
      cs.execute();
      //System.out.println("!!!!!!!!! After cs.execute");
      xReturnStatus = cs.getString(7);
      xErrorMsg     = cs.getString(8);
    }
    catch(Exception exception)
    {
      exception.printStackTrace();
    }
    finally
    {
      try
      {
        if(cs != null)
          cs.close();

       // if(conn != null)
         // TransactionScope.releaseConnection(conn);
        //if(connUpdConnection != null) connUpdConnection.releaseConnection();
        if(nativeConn!=null)
        	TransactionScope.releaseConnection(nativeConn);

      }
      catch(Exception exception2)
      {
        exception2.printStackTrace();
      }
    }
    return xErrorMsg;
  }

  public static Vector displayData(String sourceApplication, String sourceContext, String contextValue)
  throws Exception, SQLException
  {
    //OracleCallableStatement cs  = null;
   // OracleConnection conn = null;
	  CallableStatement cs = null;
	  Connection conn = null;
	CanonCustAppDBUtil connAddrConnection = null;
    StringBuffer sbCall   = new StringBuffer(200);

    sbCall.append("{call CANON_E389_SQLS_PKG.DISPLAY_DATA(?, ?, ?, ?, ?)}");

    String[] strAttrIds      = null;
    String[] strAttrValues   = null;

   // ARRAY strAttrIdsArray    = null;
    //ARRAY strAttrValuesArray = null;

    Vector vData = new Vector();

    try
    {
      //conn = (OracleConnection)TransactionScope.getConnection();
      //cs   = (OracleCallableStatement)conn.prepareCall(sbCall.toString());
    	connAddrConnection = new CanonCustAppDBUtil();
    	conn = (Connection)(connAddrConnection.getConnection());
    	 cs = (CallableStatement)conn.prepareCall(sbCall.toString());
      cs.setString(1, sourceApplication);
      cs.setString(2, sourceContext);
      cs.setString(3, contextValue);

      cs.registerOutParameter(4, OracleTypes.ARRAY, "JTF_VARCHAR2_TABLE_100");
      cs.registerOutParameter(5, OracleTypes.ARRAY, "JTF_VARCHAR2_TABLE_100");

      cs.execute();

      /*strAttrIdsArray = new oracle.sql.ARRAY(
                            oracle.apps.jtf.util.GeneralUtil.getArrayDescriptor(
                            "JTF_VARCHAR2_TABLE_100", conn), conn, strAttrIds);

      strAttrValuesArray = new oracle.sql.ARRAY(
                               oracle.apps.jtf.util.GeneralUtil.getArrayDescriptor(
                               "JTF_VARCHAR2_TABLE_100", conn), conn, strAttrValues);*/

     // strAttrIdsArray    = cs.getARRAY(4);
      //Object[] strAttrIdsArray = (Object[]) cs.getObject(4).getArray();
      Object strAttrIdsArray[] =(Object[]) ((Array) cs.getObject(4)).getArray();
      Object strAttrValuesArray[] =(Object[]) ((Array) cs.getObject(5)).getArray();
      //strAttrValuesArray = cs.getARRAY(5);
     // Object[] strAttrIdsArray = (Object[]) cs.getARRAY(4).getArray();
      String[] tempIdsArray      = new String[50];
      String[] tempIdValuesArray = new String[50];
      //tempIdsArray      = (String[]) strAttrIdsArray.getArray();
      //tempIdsArray      = (String[])  Object strAttrIdsArray[].getArray();
     // tempIdValuesArray = (String[]) strAttrValuesArray.getArray();

      //Start changes for S21 by Mangala
      //for (int iCnt = 0; iCnt < tempIdsArray.length; iCnt++ )
      for (int iCnt = 0; iCnt < strAttrIdsArray.length; iCnt++ )  
      //End changes for S21 by Mangala
      {
        //String tempId      = tempIdsArray[iCnt];
        //String tempIdValue = tempIdValuesArray[iCnt];
          Object tempId      = strAttrIdsArray[iCnt];
          Object tempIdValue = strAttrValuesArray[iCnt];

        if (tempId != null || tempId != "")
        {
          CanonE389DffDataBean obj1 = new CanonE389DffDataBean();
          //obj1.setattributeId(tempId);
          //obj1.setattributeValue(tempIdValue);
          obj1.setattributeId((String)tempId);
          obj1.setattributeValue((String)tempIdValue);
          vData.add(obj1);
        }
      }

    }
    catch(Exception exception)
    {
      exception.printStackTrace();
    }
    finally
    {
      try
      {
        if(cs != null)
          cs.close();

        //if(conn != null)
        //  TransactionScope.releaseConnection(conn);
        if(connAddrConnection != null) connAddrConnection.releaseConnection();
      }
      catch(Exception exception2)
      {
        exception2.printStackTrace();
      }
    }
    return vData;
  }

   @Override
public String toString() {
	return "CanonE389DffDataBean [attributeId=" + attributeId
			+ ", attributeValue=" + attributeValue + ", errmsg=" + errmsg + "]";
}

/**
    * Get Actual Document Id from 170 System.
    * @param strDocumentId java.lang.String.
    * @return java.lang.String
    */
   public static String getDocumentIdLink(String strDocumentId)
   throws Exception, SQLException
   {
      String strActualLink = "";
      String strStatus     = "";
      CanonCustAppDBUtil connAddrConnection = null;
      CallableStatement cs = null;
      Connection conn = null;
      //OracleCallableStatement cs  = null;
      //OracleConnection conn = null;
      StringBuffer sbCall   = new StringBuffer(200);

      sbCall.append("{call CANON_E389_SQLS_PKG.DISPLAY_LINK(?, ?, ?)}");

      try
      {
       // conn = (OracleConnection)TransactionScope.getConnection();
       // cs   = (OracleCallableStatement)conn.prepareCall(sbCall.toString());
    	connAddrConnection = new CanonCustAppDBUtil();
      	conn = (Connection)(connAddrConnection.getConnection());
      	cs = (CallableStatement)conn.prepareCall(sbCall.toString());
        cs.setString(1, strDocumentId);

        cs.registerOutParameter(2, Types.VARCHAR);
        cs.registerOutParameter(3, Types.VARCHAR);

        cs.execute();

        strActualLink = cs.getString(2);
        strStatus     = cs.getString(3);

        if(!("S".equalsIgnoreCase(strStatus)))
          strActualLink = "ERROR";

      }
      catch(Exception exception)
      {
        exception.printStackTrace();
      }
      finally
      {
        try
        {
          if(cs != null)
            cs.close();

         // if(conn != null)
          //  TransactionScope.releaseConnection(conn);
          if(connAddrConnection != null) connAddrConnection.releaseConnection();

        }
        catch(Exception exception2)
        {
          exception2.printStackTrace();
        }
      }
      return strActualLink;
   }
}

