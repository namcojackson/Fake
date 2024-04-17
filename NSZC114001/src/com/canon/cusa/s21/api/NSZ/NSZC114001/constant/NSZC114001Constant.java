/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC114001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Create MyCSA Ticket API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/09   Hitachi         A.Kohinata      Create          QC#16993
 * 2017/04/12   Hitachi         K.Kitachi       Update          QC#18008
 *</pre>
 */
public class NSZC114001Constant {

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    // START 2017/04/12 K.Kitachi [QC#18008, DEL]
//    /** Input Parameter "Parent Category Code" is mandatory. */
//    public static final String NSZM1102E = "NSZM1102E";
//
//    /** Input Parameter "Invoice Number" is mandatory. */
//    public static final String NSZM1103E = "NSZM1103E";
//
//    /** Input Parameter "Customer Name" is mandatory. */
//    public static final String NSZM1104E = "NSZM1104E";
//
//    /** Input Parameter "Customer Phone Number" is mandatory. */
//    public static final String NSZM1105E = "NSZM1105E";
//
//    /** Input Parameter "Customer Email Address" is mandatory. */
//    public static final String NSZM1106E = "NSZM1106E";
//
//    /** Input Parameter "Create User ID" is mandatory. */
//    public static final String NSZM1107E = "NSZM1107E";
//
//    /** Input Parameter "Category Code" is mandatory. */
//    public static final String NSZM1108E = "NSZM1108E";
    // END 2017/04/12 K.Kitachi [QC#18008, DEL]

    /** Exception occurred while creating customer care ticket. */
    public static final String NSZM1110E = "NSZM1110E";

    // START 2017/04/12 K.Kitachi [QC#18008, DEL]
//    /** Target invoice does not exists. */
//    public static final String NSZM1111E = "NSZM1111E";
    // END 2017/04/12 K.Kitachi [QC#18008, DEL]

    /** customer care calling statement */
    public static final String CALL_PRC_CANON_E193_CREATE_MYCSA_TICKET = "{call CANON_E193_CS_EVOLUTION_PKG.CREATE_MYCSA_TICKET(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

    /** customer care parameter:CANON_E193_HDR_TICKET_STATUS */
    public static final String CANON_E193_HDR_TICKET_STATUS = "UNASSIGNED";

    /** customer care parameter:CANON_E193_HDR_ORG_ID */
    public static final BigDecimal CANON_E193_HDR_ORG_ID = BigDecimal.valueOf(81);

    /** customer care parameter:CANON_E193_HDR_ORIG_TYPE */
    public static final String CANON_E193_HDR_ORIG_TYPE = "customer";

    /** customer care parameter:CANON_E193_HDR_REGION */
    public static final String CANON_E193_HDR_REGION = "EAST_REGION";

    /** customer care parameter:CANON_E193_LINE_STATUS */
    public static final String CANON_E193_LINE_STATUS = "UNASSIGNED";

    /** customer care parameter:CANON_E193_LINE_SEVERITY */
    public static final String CANON_E193_LINE_SEVERITY = "NORMAL";

    /** customer care parameter:CANON_E193_LINE_REASON_CODE */
    public static final String CANON_E193_LINE_REASON_CODE = "CSR_E193_CONTRACTS";

    /** customer care parameter:CANON_E193_LINE_REASON */
    public static final String CANON_E193_LINE_REASON = "OTHER -- CONTRACT ISSUES";

    /** customer care parameter:CANON_E193_NOTES_DETAIL */
    public static final String CANON_E193_NOTES_DETAIL = "Customer Incident created from Create MyCSA Ticket";
}
