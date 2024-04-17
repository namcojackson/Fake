/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC119001.constant;

/**
 * <pre>
 * CSA Website Ticket Creation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/09   CITS            M.Naito         Create          QC#23377
 *</pre>
 */
public class NSZC119001Constant {

    /** INTERFACE ID */
    public static final String INTFC_ID = "NSZI1190";

    /** customer care calling statement */
    public static final String CALL_PRC_CANON_E193_INSERT_DATA_MGMT_STG = "{call CANON_E193_DATAMGMT_PKG.INSERT_DATA_MGMT_STG(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

    /** CANON_E193_DATAMGMT_PKG Output Parameter: Status(Error) */
    public static final String CANON_E193_DATAMGMT_PKG_STS_ERROR = "E";
}
