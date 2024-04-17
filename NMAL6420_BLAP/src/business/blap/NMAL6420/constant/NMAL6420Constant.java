/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * interface name: NMAL6420Constant
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/24/2010   Fujitsu         N.Sakamoto      Create          N/A                                                         
 *</pre>
 */
package business.blap.NMAL6420.constant;

/**
 * Class name: NMAL6420Constant
 * <dd>The class explanation: Constant variable for business component.
 * <dd>Remarks:
 * 
 * @version 1.0
 * @author Q03156
 */
public interface NMAL6420Constant {
    
    enum MESSAGE_ID {
         NZZM0002I
        ,NZZM0003E 
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
        
         SORT_NUM("Sort Num")
        ,CODE("Code");
         
         private String name;
         
         private DISPLAY_NAME(String name) {
             this.name = name;
         }
         public String toString() {
             return name;
         }
    }
    
    int MAX_DISPALY_ROWS = 100;
   
    
}
