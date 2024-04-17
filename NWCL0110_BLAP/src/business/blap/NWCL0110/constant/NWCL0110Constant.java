/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0110.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         H.Nagashima     Create          N/A
 * 2019/09/14   Fujitsu         Y.Kanefusa      Update          S21_NA#53014
 *</pre>
 */
public class NWCL0110Constant {

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Please select at least one invoice. */
    public static final String NWCM0096E = "NWCM0096E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Process Name (Submit) */
    public static final String SUBMIT = "Submit";

    /** Search results is over @. Please narrow your search criteria and retry. */
    public static final String NWCM0101W = "NWCM0101W";

    /** VarChar Constant Name */
    public static final String CONST_RECORD_PER_PAGE = "NWCL0110_RECORD_PER_PAGE";

    /** Number Constant Name */
    public static final String CONST_MAX_ROW_CNT = "NWCL0110_MAX_ROW_CNT";

    /** Output Type Pull Down Code Print */
    public static final String CONST_OTPT_TP_PRT = "1";
    /** Output Type Pull Down Code Email */
    public static final String CONST_OTPT_TP_EML = "2";
    /** Output Type Pull Down Code PDF */
    public static final String CONST_OTPT_TP_PDF = "3";
    /** Output Type Pull Down Code EDI */
    public static final String CONST_OTPT_TP_EDI = "4";
    /** Output Type Pull Down Code SpecialBilling */
    public static final String CONST_OTPT_TP_SB  = "5";
    /** Output Type Pull Down Code All */
    public static final String CONST_OTPT_TP_ALL = "A";
    /** Output Type Pull Down Array */
    public static final String[][] CONST_OTPT_TP_ARRAY = {
                                        {CONST_OTPT_TP_PRT, "Print"},
                                        {CONST_OTPT_TP_EML, "Email"},
                                        {CONST_OTPT_TP_PDF, "PDF"},
                                        {CONST_OTPT_TP_EDI, "EDI"},
                                        //{CONST_OTPT_TP_SB,  "Special Billing"},
                                        //{CONST_OTPT_TP_ALL, "Print,Email,PDF,EDI,Special Billing"}
                                        {CONST_OTPT_TP_ALL, "Print,Email,PDF,EDI"}
    };

    /** Process Status (not Processed) */
    public static final String PROC_STS_UNPROCESSED = "1";

    /** Process Status (Processed) */
    public static final String PROC_STS_PROCESSED = "2";

    /** Report ID */
    public static final String REPORT_ID_INVOICE = "NWCF0100";

    // QC#53014 2019/09/17 Add Start
    public static final String NO = "NO";
    // QC#53014 2019/09/17 Add End

}
