//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170316131642000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL1120_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL1120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL1120 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL1120_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

    // Serial Version UID
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** AP_BAT_NUM_A1*/
    public final EZDSStringItem              apBatNum_A1;

    /** AP_BAT_DT_A1*/
    public final EZDSDateItem              apBatDt_A1;

    /** PRNT_VND_CD_A1*/
    public final EZDSStringItem              prntVndCd_A1;

    /** PRNT_VND_NM_A1*/
    public final EZDSStringItem              prntVndNm_A1;

    /** AP_VND_CD_A1*/
    public final EZDSStringItem              apVndCd_A1;

    /** VND_SITE_NM_A1*/
    public final EZDSStringItem              vndSiteNm_A1;

    /** AP_MAINT_INV_STS_CD_A1*/
    public final EZDSStringItem              apMaintInvStsCd_A1;

    /** AP_MAINT_INV_STS_DESC_TXT_A1*/
    public final EZDSStringItem              apMaintInvStsDescTxt_A1;

    /** APVR_USR_ID_A1*/
    public final EZDSStringItem              apvrUsrId_A1;

    /** VAR_CHAR_CONST_VAL_A1*/
    public final EZDSStringItem              varCharConstVal_A1;

    /** AP_INV_NUM_A1*/
    public final EZDSStringItem              apInvNum_A1;

    /** INV_DT_A1*/
    public final EZDSDateItem              invDt_A1;

    /** AP_INV_AMT_A1*/
    public final EZDSBigDecimalItem              apInvAmt_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
    public final EZDSStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
    public final EZDSStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
    public final EZDSStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
    public final EZDSStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
    public final EZDSStringItem              xxRecHistTblNm_A1;


    /**
     * NFBL1120_ASMsg is constructor.
     * The initialization when the instance of NFBL1120_ASMsg is generated.
     */
    public NFBL1120_ASMsg() {
        this(false, -1);
    }

    /**
     * NFBL1120_ASMsg is constructor.
     * The initialization when the instance of NFBL1120_ASMsg is generated.
     * @param child  Flag whether it is child message
     * @param eleNo  Index Number of array
     */
    public NFBL1120_ASMsg(boolean child, int eleNo) {
        super(child, eleNo);

        // Initialization of item

        apBatNum_A1 = (EZDSStringItem)newItem("apBatNum_A1");
        apBatDt_A1 = (EZDSDateItem)newItem("apBatDt_A1");
        prntVndCd_A1 = (EZDSStringItem)newItem("prntVndCd_A1");
        prntVndNm_A1 = (EZDSStringItem)newItem("prntVndNm_A1");
        apVndCd_A1 = (EZDSStringItem)newItem("apVndCd_A1");
        vndSiteNm_A1 = (EZDSStringItem)newItem("vndSiteNm_A1");
        apMaintInvStsCd_A1 = (EZDSStringItem)newItem("apMaintInvStsCd_A1");
        apMaintInvStsDescTxt_A1 = (EZDSStringItem)newItem("apMaintInvStsDescTxt_A1");
        apvrUsrId_A1 = (EZDSStringItem)newItem("apvrUsrId_A1");
        varCharConstVal_A1 = (EZDSStringItem)newItem("varCharConstVal_A1");
        apInvNum_A1 = (EZDSStringItem)newItem("apInvNum_A1");
        invDt_A1 = (EZDSDateItem)newItem("invDt_A1");
        apInvAmt_A1 = (EZDSBigDecimalItem)newItem("apInvAmt_A1");
        xxRecHistCratTs_A1 = (EZDSStringItem)newItem("xxRecHistCratTs_A1");
        xxRecHistCratByNm_A1 = (EZDSStringItem)newItem("xxRecHistCratByNm_A1");
        xxRecHistUpdTs_A1 = (EZDSStringItem)newItem("xxRecHistUpdTs_A1");
        xxRecHistUpdByNm_A1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_A1");
        xxRecHistTblNm_A1 = (EZDSStringItem)newItem("xxRecHistTblNm_A1");
    }

    /**
     * get the type of array which is stored
     * @return NFBL1120_ASMsgArray
     */
    public EZDMsgArray getMyArray() {
        return new NFBL1120_ASMsgArray();
    }


    /**
     * Array of schema data(Basic data)
     */
    private static final String[][] BASE_CONTENTS = {

    {"apBatNum_A1", "apBatNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
    {"apBatDt_A1", "apBatDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
    {"prntVndCd_A1", "prntVndCd_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
    {"prntVndNm_A1", "prntVndNm_A1", "A1", null, TYPE_HANKAKUEISU, "240", null},
    {"apVndCd_A1", "apVndCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
    {"vndSiteNm_A1", "vndSiteNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
    {"apMaintInvStsCd_A1", "apMaintInvStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
    {"apMaintInvStsDescTxt_A1", "apMaintInvStsDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
    {"apvrUsrId_A1", "apvrUsrId_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
    {"varCharConstVal_A1", "varCharConstVal_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
    {"apInvNum_A1", "apInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
    {"invDt_A1", "invDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
    {"apInvAmt_A1", "apInvAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
    {"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
    {"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
    {"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
    {"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
    {"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
    };

    /**
     * Array of schema data(Visible Field)
     */
    private static final String[][] DISP_CONTENTS = {

        {"AP_BAT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //apBatNum_A1
        {"AP_BAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO}, //apBatDt_A1
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //prntVndCd_A1
        {"PRNT_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //prntVndNm_A1
        {"AP_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO}, //apVndCd_A1
        {"VND_SITE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //vndSiteNm_A1
        {"AP_MAINT_INV_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //apMaintInvStsCd_A1
        {"AP_MAINT_INV_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO}, //apMaintInvStsDescTxt_A1
        {"APVR_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //apvrUsrId_A1
        {"VAR_CHAR_CONST_VAL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //varCharConstVal_A1
        {"AP_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //apInvNum_A1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //invDt_A1
        {"AP_INV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //apInvAmt_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},   //xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO}, //xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},    //xxRecHistTblNm_A1
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

