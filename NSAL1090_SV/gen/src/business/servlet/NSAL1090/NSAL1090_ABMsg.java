//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20210112102752000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1090_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1090 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1090_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_AL*/
	public final EZDBBigDecimalItem              xxRowNum_AL;

    /** XX_ROW_NUM_A*/
	public final EZDBBigDecimalItem              xxRowNum_A;

    /** ORIG_SVC_INV_NUM_A*/
	public final EZDBStringItem              origSvcInvNum_A;

    /** DS_CONTR_NUM_A*/
	public final EZDBStringItem              dsContrNum_A;

    /** CONSL_BILL_PK_A*/
	public final EZDBBigDecimalItem              conslBillPk_A;

    /** INV_TP_NM_A*/
	public final EZDBStringItem              invTpNm_A;

    /** XX_FROM_DT_A*/
	public final EZDBDateItem              xxFromDt_A;

    /** XX_THRU_DT_A*/
	public final EZDBDateItem              xxThruDt_A;

    /** XX_INV_AMT_A1*/
	public final EZDBBigDecimalItem              xxInvAmt_A1;

    /** XX_INV_AMT_A2*/
	public final EZDBBigDecimalItem              xxInvAmt_A2;

    /** XX_INV_AMT_A3*/
	public final EZDBBigDecimalItem              xxInvAmt_A3;

    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** REBIL_SVC_INV_NUM_A*/
	public final EZDBStringItem              rebilSvcInvNum_A;

    /** CR_SVC_INV_NUM_A*/
	public final EZDBStringItem              crSvcInvNum_A;

    /** SVC_CR_REBIL_DTL_PK_A*/
	public final EZDBBigDecimalItem              svcCrRebilDtlPk_A;

    /** DS_CONTR_DTL_PK_A*/
	public final EZDBBigDecimalItem              dsContrDtlPk_A;

    /** SVC_CR_REBIL_BASE_BLLG_PK_A*/
	public final EZDBBigDecimalItem              svcCrRebilBaseBllgPk_A;

    /** SVC_CR_REBIL_MTR_BLLG_PK_A*/
	public final EZDBBigDecimalItem              svcCrRebilMtrBllgPk_A;

    /** XX_EXST_FLG_A*/
	public final EZDBStringItem              xxExstFlg_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDBStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDBStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDBStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDBStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDBStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL1090_ABMsg is constructor.
	 * The initialization when the instance of NSAL1090_ABMsg is generated.
	 */
	public NSAL1090_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1090_ABMsg is constructor.
	 * The initialization when the instance of NSAL1090_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1090_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_AL = (EZDBBigDecimalItem)newItem("xxRowNum_AL");
		xxRowNum_A = (EZDBBigDecimalItem)newItem("xxRowNum_A");
		origSvcInvNum_A = (EZDBStringItem)newItem("origSvcInvNum_A");
		dsContrNum_A = (EZDBStringItem)newItem("dsContrNum_A");
		conslBillPk_A = (EZDBBigDecimalItem)newItem("conslBillPk_A");
		invTpNm_A = (EZDBStringItem)newItem("invTpNm_A");
		xxFromDt_A = (EZDBDateItem)newItem("xxFromDt_A");
		xxThruDt_A = (EZDBDateItem)newItem("xxThruDt_A");
		xxInvAmt_A1 = (EZDBBigDecimalItem)newItem("xxInvAmt_A1");
		xxInvAmt_A2 = (EZDBBigDecimalItem)newItem("xxInvAmt_A2");
		xxInvAmt_A3 = (EZDBBigDecimalItem)newItem("xxInvAmt_A3");
		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		rebilSvcInvNum_A = (EZDBStringItem)newItem("rebilSvcInvNum_A");
		crSvcInvNum_A = (EZDBStringItem)newItem("crSvcInvNum_A");
		svcCrRebilDtlPk_A = (EZDBBigDecimalItem)newItem("svcCrRebilDtlPk_A");
		dsContrDtlPk_A = (EZDBBigDecimalItem)newItem("dsContrDtlPk_A");
		svcCrRebilBaseBllgPk_A = (EZDBBigDecimalItem)newItem("svcCrRebilBaseBllgPk_A");
		svcCrRebilMtrBllgPk_A = (EZDBBigDecimalItem)newItem("svcCrRebilMtrBllgPk_A");
		xxExstFlg_A = (EZDBStringItem)newItem("xxExstFlg_A");
		xxRecHistCratTs_A = (EZDBStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDBStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDBStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDBStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDBStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1090_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1090_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_AL", "xxRowNum_AL", "AL", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"origSvcInvNum_A", "origSvcInvNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"conslBillPk_A", "conslBillPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invTpNm_A", "invTpNm_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	{"xxFromDt_A", "xxFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxThruDt_A", "xxThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxInvAmt_A1", "xxInvAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxInvAmt_A2", "xxInvAmt_A2", "A2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxInvAmt_A3", "xxInvAmt_A3", "A3", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"rebilSvcInvNum_A", "rebilSvcInvNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"crSvcInvNum_A", "crSvcInvNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"svcCrRebilDtlPk_A", "svcCrRebilDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_A", "dsContrDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCrRebilBaseBllgPk_A", "svcCrRebilBaseBllgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCrRebilMtrBllgPk_A", "svcCrRebilMtrBllgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxExstFlg_A", "xxExstFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs_A", "xxRecHistCratTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A", "xxRecHistCratByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A", "xxRecHistUpdTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A", "xxRecHistUpdByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A", "xxRecHistTblNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_AL
        {"XX_ROW_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"ORIG_SVC_INV_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSvcInvNum_A
        {"DS_CONTR_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"CONSL_BILL_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillPk_A
        {"INV_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpNm_A
        {"XX_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt_A
        {"XX_THRU_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxThruDt_A
        {"XX_INV_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//xxInvAmt_A1
        {"XX_INV_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//xxInvAmt_A2
        {"XX_INV_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//xxInvAmt_A3
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"REBIL_SVC_INV_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rebilSvcInvNum_A
        {"CR_SVC_INV_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crSvcInvNum_A
        {"SVC_CR_REBIL_DTL_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDtlPk_A
        {"DS_CONTR_DTL_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_A
        {"SVC_CR_REBIL_BASE_BLLG_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilBaseBllgPk_A
        {"SVC_CR_REBIL_MTR_BLLG_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilMtrBllgPk_A
        {"XX_EXST_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_A
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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
