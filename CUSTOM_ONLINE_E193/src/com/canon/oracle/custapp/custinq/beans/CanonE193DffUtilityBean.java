//Start Changes for S21 by Mangala
//package com.canon.oracle.beans;
package com.canon.oracle.custapp.custinq.beans;
//End Changes for S21 by Mangala

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//import oracle.jdbc.driver.OracleTypes;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;

import com.canon.oracle.custapp.util.CanonCustAppDBUtil;

/**
 * Title:      CanonE193DffUtilityBean<br>
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

public class CanonE193DffUtilityBean {

  private String                        seqNumber           = "";
  private String                        prompt              = "";
  private String                        attributeId         = "";
  private String                        requiredFlag        = "";
  private String                        enabledFlag         = "";
  private String                        displayFlag         = "";
  private String                        securityEnabledFlag = "";
  private String                        valueSetName        = "";
  private String                        defaultType         = "";
  private String                        defaultValue        = "";
  private String                        displaySize         = "";
  private String                        userid              = "";
  private String                        attributeValue      = ""; // Newly Added.
  OracleConnection                      conn;
  PreparedStatement                     ps = null;
  ResultSet                             searchResults;
  private static final SimpleDateFormat df = new SimpleDateFormat();
  private static final ParsePosition    pp = new ParsePosition(0);

  public CanonE193DffUtilityBean()
  {
  }

  public String getseqNumber() {
    return seqNumber;
  }



public void setseqNumber(String seqNumber) {
    this.seqNumber = seqNumber;
  }

  public String getprompt() {
    return prompt;
  }

  public void setprompt(String prompt) {
    this.prompt = prompt;
  }

  public String getattributeId() {
    return attributeId;
  }

  public void setattributeId(String attributeId) {
    this.attributeId = attributeId;
  }

  public String getrequiredFlag() {
    return requiredFlag;
  }

  public void setrequiredFlag(String requiredFlag) {
    this.requiredFlag = requiredFlag;
  }

  public String getenabledFlag() {
    return enabledFlag;
  }

  public void setenabledFlag(String enabledFlag) {
    this.enabledFlag = enabledFlag;
  }

  public String getdisplayFlag() {
    return displayFlag;
  }

  public void setdisplayFlag(String displayFlag) {
    this.displayFlag = displayFlag;
  }

  public String getsecurityEnabledFlag() {
    return securityEnabledFlag;
  }

  public void setsecurityEnabledFlag(String securityEnabledFlag) {
    this.securityEnabledFlag = securityEnabledFlag;
  }

  public String getvalueSetName() {
    return valueSetName;
  }

  public void setvalueSetName(String valueSetName) {
    this.valueSetName = valueSetName;
  }

  public String getdefaultType() {
    return defaultType;
  }

  public void setdefaultType(String defaultType) {
    this.defaultType = defaultType;
  }

  public String getdefaultValue() {
    return defaultValue;
  }

  public void setdefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public String getdisplaySize() {
    return displaySize;
  }

  public void setdisplaySize(String displaySize) {
    this.displaySize = displaySize;
  }

  public OracleConnection getConn() {
    return conn;
  }

  public void setConn(OracleConnection newConn) {
    conn = newConn;
  }

  public void setUserId(String userid) {
    this.userid = userid;
  }

  public String getUserId() {
    return userid;
  }
  
  public void setAttributeValue(String attribValue) {
	  this.attributeValue = attribValue;
  }
  public String getAttributeValue() {
	  return this.attributeValue;
  }
  @Override
public String toString() {
	return "CanonE193DffUtilityBean [seqNumber=" + seqNumber + ", prompt="
			+ prompt + ", attributeId=" + attributeId + ", attributeValue=" + attributeValue +", requiredFlag="
			+ requiredFlag + ", enabledFlag=" + enabledFlag + ", displayFlag="
			+ displayFlag + ", securityEnabledFlag=" + securityEnabledFlag
			+ ", valueSetName=" + valueSetName + ", defaultType=" + defaultType
			+ ", defaultValue=" + defaultValue + ", displaySize=" + displaySize
			+ ", userid=" + userid + ", conn=" + conn + ", ps=" + ps
			+ ", searchResults=" + searchResults + "]";
}

  public static ArrayList getDffDetails (String sourceContext) throws SQLException
  {
	CanonCustAppDBUtil connAddrConnection = null;
    ArrayList alDffDetails = new ArrayList();
    //OracleConnection conn = null;
    Connection conn = null;
    //OracleCallableStatement cstmt = null;
    CallableStatement cstmt = null;
    ResultSet rs = null;
    CallableStatement pCall = null;
    try
    {
    	connAddrConnection = new CanonCustAppDBUtil();
    	conn = (Connection)(connAddrConnection.getConnection());
    //	System.out.println("!!!!!!!!!Inside getDffDetails");
     // conn = (OracleConnection)TransactionScope.getConnection();
    	//conn = TransactionScope.getConnection();
      //System.out.println("!!!!!!!!!!!conn = "+conn);
      //cstmt = (OracleCallableStatement)conn.prepareCall("{call CANON_E389_SQLS_PKG.SELECT_DFF_DETAILS(?,?)}");
      cstmt = conn.prepareCall("{call CANON_E389_SQLS_PKG.SELECT_DFF_DETAILS(?,?)}");
     //  System.out.println("cstmt = "+cstmt);
      cstmt.setString(1, sourceContext);
      cstmt.registerOutParameter(2, OracleTypes.CURSOR);
     // System.out.println("!!!!!!!!!Before calling cstmt.execute()");
      cstmt.execute();
     // System.out.println("!!!!!!!!!After calling cstmt.execute()");
      //rs = ((OracleCallableStatement)cstmt).getCursor(2);
      rs = (ResultSet) cstmt.getObject(2);
      //System.out.println("!!!!!!!!!!!rs = "+rs);
      while(rs.next())
      {
    	  CanonE193DffUtilityBean objRepData = new CanonE193DffUtilityBean();
        objRepData.setseqNumber(rs.getString(1));
        objRepData.setprompt(rs.getString(2));
        objRepData.setattributeId(rs.getString(3));
        objRepData.setAttributeValue(rs.getString(4));
        objRepData.setrequiredFlag(rs.getString(5));
        objRepData.setenabledFlag(rs.getString(6));
        objRepData.setdisplayFlag(rs.getString(7));
        objRepData.setsecurityEnabledFlag(rs.getString(8));
        objRepData.setvalueSetName(rs.getString(9));
        objRepData.setdefaultType(rs.getString(10));
        objRepData.setdefaultValue(rs.getString(11));
        objRepData.setdisplaySize(rs.getString(12));

        alDffDetails.add(objRepData);
     //   System.out.println("!!!!!!!!!!!alDffDetails = "+alDffDetails);
      }
    }
    catch(SQLException eSQLExp)
    {
      System.err.println("!!!!!!!!!SQL Exception is:" + eSQLExp.toString());
    }
    catch (Exception eExp)
    {
      System.err.println("!!!!!!!!!Exception is:" + eExp.toString());
    }
    finally
    {
      try
      {
        if(rs != null) {
          rs.close(); }
        if(cstmt != null) {
          cstmt.close(); }
        if(connAddrConnection != null) connAddrConnection.releaseConnection();
       // if(conn != null) {
         // TransactionScope.releaseConnection(conn); }
      }
      catch (SQLException eSQLExp)
      {
      }
      catch (Exception eExp)
      {
      }
    }
    return alDffDetails;
  } // getDffDetails

  public StringBuffer valueSetLovHTML (String attributeId, String valueSetName, String attrValue, String securityEnabledFlag, String enabledFlag, int dSize)
  throws Exception
  {
    String sbSQL = null;
    System.out.println("!!!!!!!!!Inside valueSetLovHTML" );
    sbSQL = (new StringBuffer("SELECT val.val1 list1, val.val1 list2")
                     .append(" FROM canon_s21_cd_tbl cd, ")
                     .append("      canon_s21_cd_val_tbl val ")
                     .append(" WHERE cd.cd_name = '")
                     .append(valueSetName)
                     .append("' ")
                     .append(" AND   cd.cd_id = val.cd_id ")
                     .append(" AND SYSDATE BETWEEN nvl(val.start_date_active, SYSDATE) AND nvl(val.end_date_active, SYSDATE) ")
                     .append(" ORDER BY val.val_id ASC ")
            ).toString();
    //System.out.println("!!!!!!!!!sbSQL = "+sbSQL );
    StringBuffer sbHTML = new StringBuffer();

    if (securityEnabledFlag.equals("Y"))
    {
      sbHTML.append("<select DISABLED name='");
    } else
    {
      sbHTML.append("<select name='");
    }

    sbHTML.append(attributeId);

    if (enabledFlag.equals("N"))
    {
      sbHTML.append("' DISABLED ");
    } else
    {
      sbHTML.append("' ");
    }

    sbHTML.append(" style='width:");
    sbHTML.append(dSize);
    sbHTML.append("'> ");

    sbHTML.append("<option value='");
    sbHTML.append(-1);
    sbHTML.append("'");
    sbHTML.append(" selected");
    sbHTML.append(" >Select Value</option>");

    PreparedStatement ps = null;
    ResultSet rst = null;
    //OracleConnection conn = null;
    Connection conn = null;
    CallableStatement cs = null;
    CanonCustAppDBUtil connConnection = null;

    try {
      //conn = (OracleConnection)TransactionScope.getConnection();
    	connConnection = new CanonCustAppDBUtil();
    	conn = (Connection)(connConnection.getConnection());
      ps = conn.prepareStatement(sbSQL.toString());
      rst = ps.executeQuery();
      String list1;
      String list2;

      // Loop through the results and create an option tag or each row
      while (rst.next()) {
        list1 = rst.getString(1);
        list2 = rst.getString(2);
        sbHTML.append("<option value='");
        sbHTML.append(list1);
        sbHTML.append("'");
        if(!(list1 == null || list1.equals("") || list1.equalsIgnoreCase("null")))
          sbHTML.append((list1.equals(attrValue)) ? " selected" : "");
        sbHTML.append(">");
        sbHTML.append(list2);
        sbHTML.append("</option>");
      }

      // Close the select tag and flush the writer
      sbHTML.append("</select>");

      // Close the prepared statement and connection to make sure db
      // resources release
      ps.close();
    } catch (SQLException e) {
    	System.err.println("!!!!!!!!!Exception e = "+e );
    	e.printStackTrace();
    }
    // Start finally
    finally {
      try {
        if (rst != null) {
          rst.close();
        }
      } catch (SQLException e) {}
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException e) {}
      try {
       // if(conn != null) {
         // TransactionScope.releaseConnection(conn);
    	  if(connConnection != null) connConnection.releaseConnection();
      
      } catch (Exception e) {}
    }
    return sbHTML;

  } // valueSetLovHTML

} // CanonE193DffUtilityBean
