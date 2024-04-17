/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * interface name: NMAL6460Constant
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/24/2010   Fujitsu         N.Sakamoto      Create          N/A                                                         
 *</pre>
 */
package business.blap.NMAL6460.constant;

/**
 * Class name: NMAL6460Constant
 * <dd>The class explanation: Constant variable for business component.
 * <dd>Remarks:
 * 
 * @version 1.0
 * @author Q03156
 *
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 2016/05/19   Hitachi         T.Aoyagi        Update          QC#8569
 */
public interface NMAL6460Constant {
    
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
        ,NMAM8054E
        // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
        ,NMAM0031E
        ,NMAM0153E;
        // END 2016/05/19 T.Aoyagi [QC#8569, ADD]
    }
    
    enum DISPLAY_NAME {
        
         SORT_NUM("Sort Num")
        ,CODE("Code")
        ,PMT_TERM("Payment Term")
        ,CASH_DISC_TERM("Cash Discount Term")
        ,ISTL_PMT_TERM("Installment Payment Term")
         // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
        ,PMT_TERM_CONSL_DUE_DAY("Day of Month");
         // END 2016/05/19 T.Aoyagi [QC#8569, ADD]

         private String name;
         
         private DISPLAY_NAME(String name) {
             this.name = name;
         }
         public String toString() {
             return name;
         }
    }
    
    int MAX_DISPALY_ROWS = 100;
    // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
    /** MONTH_END */
    int MONTH_END = 31;
    // END 2016/05/19 T.Aoyagi [QC#8569, ADD]
}
