/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6790.constant;

/**
 * <pre>
 * Invoice Creation and Document Send Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 * 2018/10/11   Fujitsu         T.Noguchi       Update          QC#27869
 *</pre>
 */
public class NMAL6790Constant {

    /** Max Length for Advanced Search  */
    public static final String MAX_EFFECTIVE_DATE = "99991231";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** CTAC_TP */
    public static final String CTAC_TP_DBCOLUMN = "CTAC_TP";

    // 2018/10/11 QC#27869 Add Start
    /** SVC_CTAC_TP */
    public static final String SVC_CTAC_TP_DBCOLUMN = "SVC_CTAC_TP";
    // 2018/10/11 QC#27869 Add End
}
