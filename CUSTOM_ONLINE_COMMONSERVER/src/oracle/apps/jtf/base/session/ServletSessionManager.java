/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.apps.jtf.base.session;

import java.util.HashMap;
/*import oracle.apps.bsd.admin.beans.CanonBSDAdminAcctProfileBean;*/

public class ServletSessionManager {
    
  static HashMap contextValue=new HashMap();
  static{
//      contextValue.put("ACCOUNT_INFO",b("BSN00001","NEW YORK LIFE FM","DIEBEL, PAUL K. (K00171)","1093524"));
     //   contextValue.put("ACCOUNT_INFO",b("S1325243_1325243","LINCOLNSHIRE SENIOR CARE FM","CLARK, LESLIE ANN (D02685)","1325243"));
  }
  
  public static Object getAttribute(String name)
  {
      return contextValue.get(name);
  }
   
  public static void setAttribute(String key, Object value){
      contextValue.put(key, value);
  }
  
  /*
  static private CanonBSDAdminAcctProfileBean b(String bsdBid,String accountName,String salesRepName,String accountNumber){
      CanonBSDAdminAcctProfileBean acctProfileBean=new CanonBSDAdminAcctProfileBean();
      acctProfileBean.setBsdBid(bsdBid);
      acctProfileBean.setAccountName(accountName);
      acctProfileBean.setSalesRepName(salesRepName);
      acctProfileBean.setAccountNumber(accountNumber);
      return acctProfileBean;
  }
 */ 
}
