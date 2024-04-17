/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * interface name: NMAL6670Constant
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/24/2010   Fujitsu         N.Sakamoto      Create          N/A                                                         
 *</pre>
 */
package business.blap.NMAL6670.constant;

import java.math.BigDecimal;

/**
 * Class name: NMAL6670Constant
 * <dd>The class explanation: Constant variable for business component.
 * <dd>Remarks:
 * 
 * @version 1.0
 * @author Q03156
 */
public interface NMAL6670Constant {
    
    enum MESSAGE_ID {
         NZZM0002I
        ,NZZM0003E 
        ,NMAM0046E
        ,NMAM0007I
        ,NMAM0098I
        ,NMAM0009E
        ,NMAM0010E
        ,NMAM0050E
        ,NMAM0072E
        ,NMAM0177E
        ,NMAM8054E;
    }
    
    enum DISPLAY_NAME {
        
         
         CODE("Code")
         ,DTL_NUM("Detail Num")
         ,SUM_PCT("Sum of Pmt Pct")
         ,TOT_PCT("100%");
         
         private String name;
         
         private DISPLAY_NAME(String name) {
             this.name = name;
         }
         public String toString() {
             return name;
         }
    }
    
    int MAX_DISPALY_ROWS = 100;
    
    BigDecimal MAX_PCT_VALUE = new BigDecimal(100);
}
