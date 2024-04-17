package com.canon.oracle.custapp.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonBSDAdminUtil {
    
    public CanonBSDAdminUtil() {
        super();
    }
    
 /*   public  boolean isValidUser(String userName) {
      Connection conn = null;     
     
      CallableStatement cstmt = null;
      String result = "N";
      boolean flag = false;
      try
      {

        conn = TransactionScope.getConnection();
        String call = "begin :1 := canon_e473_bsdadmin_util_pkg.validate_user(:2,:3); end;";          
        cstmt = 
            conn.prepareCall(call);     
          
        cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
        cstmt.setString(2, userName);
        cstmt.setString(3, CanonBSDAdminConstants.RESP_KEY);
        cstmt.execute();
        
        result = cstmt.getString(1);
        if (result.equalsIgnoreCase("Y"))
            flag = true;




      }
      catch(Exception exception) {exception.printStackTrace(); }
      finally
      {
          try
          {
            if(cstmt != null)
              {
                  
                  cstmt.close();
              }
              if(conn != null)
                TransactionScope.releaseConnection(conn);
          }
          catch(Exception exception3) {exception3.printStackTrace(); }
      }
      return flag;
  }  
  
  public  String[] getUserInfo(String userName) {
      Connection conn = null;     
    CallableStatement cstmt = null;

      String result[] = new String[2];
      try
      {

        conn = TransactionScope.getConnection();
        cstmt = conn.prepareCall("" +
            "{call canon_e473_bsdadmin_util_pkg." +
                                              "get_user_info(?,?,?)}");
        cstmt.setString(1, userName);
        cstmt.registerOutParameter(2, OracleTypes.INTEGER);
        cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
        cstmt.execute();
        result[0] = cstmt.getString(2);
        result[1] = cstmt.getString(3);  
       




      }
      catch(Exception exception) {
          exception.printStackTrace();
        result[0] = "";
        result[0] = "";       
          
      }
      finally
      {
          try
          {
            
            if(cstmt != null)
              {
                 
                  cstmt.close();
              }             
              if(conn != null)
                TransactionScope.releaseConnection(conn);
          }
          catch(Exception exception3) {exception3.printStackTrace(); }
      }
      return result;
  }   
  
  
  public static void setContextValue(String name, String value){
      oracle.apps.jtf.base.session.ServletSessionManager.setCookieValue(name,
                                                                      value);
      
    
  }
  
  public static String getContextValue(String name)
    {
      String result =
            oracle.apps.jtf.base.session.ServletSessionManager.getCookieValue(
                name);
      
      return result;
      
    
  }     

  public ArrayList getBSDLookups(String strLookupType
                                ){
    Connection connection = null;
    CallableStatement cs  = null;
    ResultSet rsLookups = null;
    ArrayList lookupsAl = new ArrayList(); 
    
    try{
      connection = TransactionScope.getConnection();

      String plsqlExp 
        = " Begin \n "
        + " CANON_E473_BSDADMIN_UTIL_PKG.get_bsdlookups(:1,:2); \n"
        + " End;";

      cs = connection.prepareCall(plsqlExp);

      cs.setString(1,strLookupType);
      cs.registerOutParameter(2,oracle.jdbc.driver.OracleTypes.CURSOR);

      System.out.println("CanonBSDAdminUtil.getBSDLookups:Before PL/SQL call");

      cs.execute();
      
      System.out.println("CanonBSDAdminUtil.getBSDLookups:After PL/SQL call");
           
      rsLookups = ((OracleCallableStatement)cs).getCursor(2);

      while (rsLookups.next()){
        CanonBSDAdminLookupBean lookupBean 
            = new CanonBSDAdminLookupBean();
        lookupBean.setLookupCode(rsLookups.getString(1));
        lookupBean.setDescription(rsLookups.getString(2));
        lookupBean.setLookupType(rsLookups.getString(3));
        lookupsAl.add(lookupBean);
      } 
    }
    catch(Exception exception1) {
      System.out.println("CanonBSDAdminUtil.getBSDLookups:exception1");
      exception1.printStackTrace();
    } 
    finally{
      try {
        if(cs != null)
          cs.close();
        if(connection != null)
          TransactionScope.releaseConnection(connection);
        if(rsLookups != null)
          rsLookups.close();
      }
      catch(Exception exception2) {
        System.out.println("CanonBSDAdminUtil.getBSDLookups:exception2");
        exception2.printStackTrace();
      }
    }
    return lookupsAl;
  }
  
  public boolean isBidBilled(String bsdBid){
      
      boolean flag = true;
      
      //logic needs to be added once the site tool is ready 
      
      return flag;
    
  } */

    public String getProfile(String profileName) throws Exception{
        String s1 = "BEGIN FND_PROFILE.GET(?,?); END;";
        String s2 = null;
        Connection connection = null;
        CallableStatement callablestatement = null;



        try
            {
                connection = TransactionScope.getConnection();
                callablestatement = connection.prepareCall(s1);
                callablestatement.setString(1, profileName);
                callablestatement.registerOutParameter(2, OracleTypes.VARCHAR);
                callablestatement.execute();
                s2 = callablestatement.getString(2);


            }
            catch(SQLException  sqlexception)
            {
                s2 = null;
               throw new Exception(sqlexception);
            }  catch(Exception e){
               throw new Exception(e);
            }
            finally
            {
                try
                {
                    if(callablestatement != null)
                        callablestatement.close();
                  if(connection != null)
                  TransactionScope.releaseConnection(connection);
                }
                catch(SQLException _ex) { throw new Exception(_ex);}
            }
        return s2;
    }
    
    public static String BSD_PATH = "CANON_E473_BSD_PATH";
    public static String ASPOSE_LIC_FILE = "Aspose.Total.Java.lic";


}
