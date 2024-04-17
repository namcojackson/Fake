/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * interface name: NMAL6550Constant
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.blap.NMAL6550.constant;

/**
 * Class name: NMAL6550Constant
 * <dd>The class explanation: Constant variable for business component.
 * <dd>Remarks:
 * 
 * @version 1.0
 * @author Q03156
 */
public interface NMAL6550Constant {
    
    enum MESSAGE_ID {
         NZZM0002I
        ,NZZM0003E
        ,NMAM0011E
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
          SORT_NUM("Sort Num")
         ,CODE("Code")
         ,CTRY_CD("Country Code")
         ,CCY_CD("Currency Code")
         ,COA_AFFL_CD("COA Affiliate Code")
         ,ACCT_CD("Account Code");
          
          private String name;
          
          private DISPLAY_NAME(String name) {
              this.name = name;
          }
          public String toString() {
              return name;
          }

    }
    
    int MAX_DISPALY_ROWS = 100;
   
    int MAX_RECORD_COUNT = 999;
 
}
