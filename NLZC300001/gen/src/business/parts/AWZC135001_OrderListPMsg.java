//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20100909195543000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *AWZC135001_OrderListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is AWZC135001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class AWZC135001_OrderListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

    // Serial Version UID
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** END_SEQ_NUM*/
    public final EZDPStringItem              endSeqNum;

    /** MDSE_CD*/
    public final EZDPStringItem              mdseCd;

    /** INBD_VIS_EVENT_CD*/
    public final EZDPStringItem              inbdVisEventCd;

    /** XX_ETA_DT_TXT*/
    public final EZDPStringItem              xxEtaDtTxt;

    /** PSD_DT_TXT*/
    public final EZDPStringItem              psdDtTxt;

    /** PDD_DT_TXT*/
    public final EZDPStringItem              pddDtTxt;

    /** VND_INV_NUM*/
    public final EZDPStringItem              vndInvNum;

    /** VND_INV_DO_NUM*/
    public final EZDPStringItem              vndInvDoNum;

    /** IMPT_CNTNR_NUM*/
    public final EZDPStringItem              imptCntnrNum;

    /** ALLOC_QTY*/
    public final EZDPBigDecimalItem              allocQty;


    /**
     * AWZC135001_OrderListPMsg is constructor.
     * The initialization when the instance of AWZC135001_OrderListPMsg is generated.
     */
    public AWZC135001_OrderListPMsg() {
        this(false, -1);
    }

    /**
     * AWZC135001_OrderListPMsg is constructor.
     * The initialization when the instance of AWZC135001_OrderListPMsg is generated.
     * @param child  Flag whether it is child message
     * @param eleNo  Index Number of array
     */
    public AWZC135001_OrderListPMsg(boolean child, int eleNo) {
        super(child, eleNo);

        // Initialization of item

        endSeqNum = (EZDPStringItem)newItem("endSeqNum");
        mdseCd = (EZDPStringItem)newItem("mdseCd");
        inbdVisEventCd = (EZDPStringItem)newItem("inbdVisEventCd");
        xxEtaDtTxt = (EZDPStringItem)newItem("xxEtaDtTxt");
        psdDtTxt = (EZDPStringItem)newItem("psdDtTxt");
        pddDtTxt = (EZDPStringItem)newItem("pddDtTxt");
        vndInvNum = (EZDPStringItem)newItem("vndInvNum");
        vndInvDoNum = (EZDPStringItem)newItem("vndInvDoNum");
        imptCntnrNum = (EZDPStringItem)newItem("imptCntnrNum");
        allocQty = (EZDPBigDecimalItem)newItem("allocQty");
    }

    /**
     * get the type of array which is stored
     * @return AWZC135001_OrderListPMsgArray
     */
    public EZDMsgArray getMyArray() {
        return new AWZC135001_OrderListPMsgArray();
    }


    /**
     * Array of schema data(Basic data)
     */
    private static final String[][] BASE_CONTENTS = {

    {"endSeqNum", "endSeqNum", null, null, TYPE_HANKAKUEISU, "6", null},
    {"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
    {"inbdVisEventCd", "inbdVisEventCd", null, null, TYPE_HANKAKUEISU, "2", null},
    {"xxEtaDtTxt", "xxEtaDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
    {"psdDtTxt", "psdDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
    {"pddDtTxt", "pddDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
    {"vndInvNum", "vndInvNum", null, null, TYPE_HANKAKUEISU, "15", null},
    {"vndInvDoNum", "vndInvDoNum", null, null, TYPE_HANKAKUEISU, "14", null},
    {"imptCntnrNum", "imptCntnrNum", null, null, TYPE_HANKAKUEISU, "15", null},
    {"allocQty", "allocQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
    };

    /**
     * Array of schema data(Visible Field)
     */
    private static final String[][] DISP_CONTENTS = {

        {"END_SEQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //endSeqNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //mdseCd
        {"INBD_VIS_EVENT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO}, //inbdVisEventCd
        {"XX_ETA_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO}, //xxEtaDtTxt
        {"PSD_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //psdDtTxt
        {"PDD_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //pddDtTxt
        {"VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //vndInvNum
        {"VND_INV_DO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //vndInvDoNum
        {"IMPT_CNTNR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //imptCntnrNum
        {"ALLOC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO}, //allocQty
    };

    /**
     * get Array of common (basic) data.
     * @return Array of common (basis) data
     */
    protected String[][] getBaseContents() {
        return BASE_CONTENTS;
    }

    /**
     * get Array of Display Field.
     * @return Array of  Display  Fields
     */
    protected String[][] getDispContents() {
        return DISP_CONTENTS;
    }

}
