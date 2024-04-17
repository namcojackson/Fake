/*
 * <pre>Copyright (c) 20154 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2630.constant;

/**
 * <pre>
 * Invoice Creation and Document Send Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2015/12/15   Fujitsu         K.Koitabashi    Update          QC#2023
 * 2016/03/01   Fujitsu         M.Suzuki        Update          QC#4557
 *</pre>
 */
public class NMAL2630Constant {

    /** Default Length for Advanced Search  */
    public static final Integer ADV_DEF_CNT = 3;

    /** Max Length for Advanced Search  */
    public static final Integer ADV_MAX_CNT = 10;

    /** Max Length for Advanced Search  */
    public static final String MAX_EFFECTIVE_DATE = "99991231";
    //--------------------------------
    // Message ID
    //--------------------------------
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";
}
