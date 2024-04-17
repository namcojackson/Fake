/**
 *  <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC180001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * NWZC1800 : Default WH API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/15   Fujitsu         S.Yoshida       Create          N/A
 * 2016/02/29   SRAA            K.Aratani       Update          QC2163
 * 2017/09/08   CITS            T.Kikuhara      Update          QC#19805(L3)
 * 2018/08/09   Fujitsu         K.Ishizuka      Update          QC#27677
 *</pre>
 */
public class NWZC180001Constant {

    // -- Process Mode ---------------------
    /** Process Mode : Out-Bound */
    public static final String PROC_MODE_OTBD = "01";
    /** Process Mode : In-Bound */
    public static final String PROC_MODE_INBD = "02";

    // -- Message Code --------------------
    /** "Global Company Code" is not set. */
    public static final String NWZM0473E = "NWZM0473E";
    /** "Sales Date" is not set. */
    public static final String NWZM0482E = "NWZM0482E";
    /** "Process Mode" is not set. */
    public static final String NWZM0580E = "NWZM0580E";
    /** Invalid value is set for process mode. */
    public static final String NWZM0977E = "NWZM0977E";
    /** "Direct Sales Order Category Code" is not set. */
    public static final String NWZM1494E = "NWZM1494E";
    /** "Direct Sales Order Reason Code" is not set. */
    public static final String NWZM1496E = "NWZM1496E";
    /** "Direct Sales Return Reason Code" is not set. */
    public static final String NWZM1244E = "NWZM1244E";
    /** The Direct Sales Return Reason Code does not exist in master. */
    public static final String NWZM1241E = "NWZM1241E";
    /** "Merchandise Code" is not set. */
    public static final String NWZM0492E = "NWZM0492E";
    /** "Ship To Postal Code" is not set. */
    public static final String NWZM0514E = "NWZM0514E";
    /** The data @ does not exist in the master. */
    public static final String NZZM0010E = "NZZM0010E";
    /** Outbound Default WH does not set up. */
    public static final String NWZM1923E = "NWZM1923E";
    //QC#19805(L3) ADD START
    /** It does not exist a valid setting in the DEF_SWH_BY_MDL. */
    public static final String NLZM2267E = "NLZM2267E";
    //QC#19805(L3) ADD END
    /** Warehouse defaulting rule has an invalid warehouse name "@". Please review the setup. */
    public static final String NWZM2276E = "NWZM2276E"; // 2018/08/09 S21_NA#27677 Add

    // -- Other ---------------------------
    /** VAR_CHAR_CONST Key:DS Retail Warehouse Code */
    public static final String VCK_DS_RTL_WH = "DROP_SHIP_RTL_WH_CD";

    //QC2163
    /** VAR_CHAR_CONST Key:DROPSHIP */
    public static final String NWZC1800_DS_PHYS_WH = "NWZC1800_DS_PHYS_WH";
    
    /** Merchandise Item Cls Type : ALL */
    public static final String MDSE_ITEM_CLS_TP_ALL = "*";

    //QC#19805(L3) ADD START
    /** Asterisk */
    public static final String ASTERISK = "*";

    /** Year Length */
    public static final int YR_LG = 4;

    /** Month Length */
    public static final int MTH_LG = 6;

    /** Day Length */
    public static final int DAY_LG = 8;

    /** First Day Of Month */
    public static final int FIRST_DAY = 1;

    /** Undefined Model ID */
    public static final BigDecimal UNDEF_MDL_ID = new BigDecimal(-1);

    //QC#19805(L3) ADD END

}
