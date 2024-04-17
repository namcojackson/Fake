/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * interface name: NMAL6350Constant
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.blap.NMAL6350.constant;

/**
 * Class name: NMAL6350Constant
 * <dd>The class explanation: Constant variable for business component.
 * <dd>Remarks:
 * 
 * @version 1.0
 * @author Q03156
 */
public interface NMAL6350Constant {
    
    enum MESSAGE_ID {
         NZZM0002I
        ,NZZM0003E 
        ,NMAM0007I
        ,NMAM0010E
        ,NMAM0098I
        ,NMAM0009E
        ,NMAM0050E
        ,NMAM0072E
        ,NMAM0176E
        ,NMAM0177E
        ,NMAM8054E;
    }
    
    enum DISPLAY_NAME {
         SortNum
        ,Code
    }
    
    int MAX_DISPALY_ROWS = 100;
   
    int MAX_RECORD_COUNT = 999;

}
