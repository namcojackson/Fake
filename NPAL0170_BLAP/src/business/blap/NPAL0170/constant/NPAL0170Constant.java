/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL0170.constant;

/**
 * <pre>
 * PO Text Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/24/2012   SRA             N.Otsuji        Create          N/A
 * 02/05/2013   CSAI            K.Lee           Update          WDS#69
 * 01/21/2016   CITS            K.Ogino         Update          CSA
 * 03/08/2016   CITS            K.Ogino         Update          QC#4975
 * 03/25/2016   CITS            T.Tokutomi      Update          QC#5773
 *</pre>
 */
public interface NPAL0170Constant {

    /**
     * INIT
     */
    String INIT = "NPAL0170_INIT";

    /** */
    String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** */
    String BIND_MSG = "msg";

    /** */
    String BIND_ROWNUM = "rowNum";

    /** */
    String BIND_PO_MDG_TP_CD = "poMsgTpCd";

    /** */
    String RS_PO_MSG_SEG_ID = "PO_MSG_SEG_ID";

    /** */
    String RS_PO_MSG_PK = "PO_MSG_PK";

    /** */
    String RS_PO_MSG_SUBMT_PSN_CD = "PO_MSG_SUBMT_PSN_CD";

    /** */
    String RS_PO_MSG_TXT = "PO_MSG_TXT";

    /** */
    String RS_PO_MSG_TP_DESC_TXT = "PO_MSG_TP_DESC_TXT";

    /**
     * Max loop count
     */
    int MAX_COUNT = 10;
}
