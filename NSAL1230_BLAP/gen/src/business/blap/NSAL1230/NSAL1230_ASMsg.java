//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180419113002000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1230_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1230;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1230 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1230_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** COA_AFFL_ACCT_NM_A1*/
	public final EZDSStringItem              coaAfflAcctNm_A1;

    /** COA_ACCT_DESC_TXT_A1*/
	public final EZDSStringItem              coaAcctDescTxt_A1;

    /** PRC_ALLOC_RATE_A1*/
	public final EZDSBigDecimalItem              prcAllocRate_A1;

    /** PRC_ALLOC_AMT_A1*/
	public final EZDSBigDecimalItem              prcAllocAmt_A1;

    /** COA_CMPY_CD_A1*/
	public final EZDSStringItem              coaCmpyCd_A1;

    /** COA_EXTN_CD_A1*/
	public final EZDSStringItem              coaExtnCd_A1;

    /** COA_BR_CD_A1*/
	public final EZDSStringItem              coaBrCd_A1;

    /** COA_CC_CD_A1*/
	public final EZDSStringItem              coaCcCd_A1;

    /** COA_ACCT_CD_A1*/
	public final EZDSStringItem              coaAcctCd_A1;

    /** COA_PROJ_CD_A1*/
	public final EZDSStringItem              coaProjCd_A1;

    /** COA_PROD_CD_A1*/
	public final EZDSStringItem              coaProdCd_A1;

    /** COA_AFFL_CD_A1*/
	public final EZDSStringItem              coaAfflCd_A1;

    /** COA_CH_CD_A1*/
	public final EZDSStringItem              coaChCd_A1;

    /** GLBL_CMPY_CD_A1*/
	public final EZDSStringItem              glblCmpyCd_A1;

    /** DS_CONTR_SEG_ALLOC_PK_A1*/
	public final EZDSBigDecimalItem              dsContrSegAllocPk_A1;

    /** DS_CONTR_PK_A1*/
	public final EZDSBigDecimalItem              dsContrPk_A1;

    /** DS_CONTR_DTL_PK_A1*/
	public final EZDSBigDecimalItem              dsContrDtlPk_A1;

    /** SVC_INV_CHRG_TP_CD_A1*/
	public final EZDSStringItem              svcInvChrgTpCd_A1;

    /** XX_ROW_NUM_A1*/
	public final EZDSBigDecimalItem              xxRowNum_A1;

    /** XX_INS_UPD_DEL_FLG_A1*/
	public final EZDSStringItem              xxInsUpdDelFlg_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDSStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDSStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDSStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDSStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDSStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL1230_ASMsg is constructor.
	 * The initialization when the instance of NSAL1230_ASMsg is generated.
	 */
	public NSAL1230_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1230_ASMsg is constructor.
	 * The initialization when the instance of NSAL1230_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1230_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		coaAfflAcctNm_A1 = (EZDSStringItem)newItem("coaAfflAcctNm_A1");
		coaAcctDescTxt_A1 = (EZDSStringItem)newItem("coaAcctDescTxt_A1");
		prcAllocRate_A1 = (EZDSBigDecimalItem)newItem("prcAllocRate_A1");
		prcAllocAmt_A1 = (EZDSBigDecimalItem)newItem("prcAllocAmt_A1");
		coaCmpyCd_A1 = (EZDSStringItem)newItem("coaCmpyCd_A1");
		coaExtnCd_A1 = (EZDSStringItem)newItem("coaExtnCd_A1");
		coaBrCd_A1 = (EZDSStringItem)newItem("coaBrCd_A1");
		coaCcCd_A1 = (EZDSStringItem)newItem("coaCcCd_A1");
		coaAcctCd_A1 = (EZDSStringItem)newItem("coaAcctCd_A1");
		coaProjCd_A1 = (EZDSStringItem)newItem("coaProjCd_A1");
		coaProdCd_A1 = (EZDSStringItem)newItem("coaProdCd_A1");
		coaAfflCd_A1 = (EZDSStringItem)newItem("coaAfflCd_A1");
		coaChCd_A1 = (EZDSStringItem)newItem("coaChCd_A1");
		glblCmpyCd_A1 = (EZDSStringItem)newItem("glblCmpyCd_A1");
		dsContrSegAllocPk_A1 = (EZDSBigDecimalItem)newItem("dsContrSegAllocPk_A1");
		dsContrPk_A1 = (EZDSBigDecimalItem)newItem("dsContrPk_A1");
		dsContrDtlPk_A1 = (EZDSBigDecimalItem)newItem("dsContrDtlPk_A1");
		svcInvChrgTpCd_A1 = (EZDSStringItem)newItem("svcInvChrgTpCd_A1");
		xxRowNum_A1 = (EZDSBigDecimalItem)newItem("xxRowNum_A1");
		xxInsUpdDelFlg_A1 = (EZDSStringItem)newItem("xxInsUpdDelFlg_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
		xxRecHistCratTs_A = (EZDSStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDSStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDSStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDSStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDSStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1230_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1230_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"coaAfflAcctNm_A1", "coaAfflAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"coaAcctDescTxt_A1", "coaAcctDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcAllocRate_A1", "prcAllocRate_A1", "A1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"prcAllocAmt_A1", "prcAllocAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"coaCmpyCd_A1", "coaCmpyCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaExtnCd_A1", "coaExtnCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_A1", "coaBrCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_A1", "coaCcCd_A1", "A1", null, TYPE_HANKAKUEISU, "6", null},
	{"coaAcctCd_A1", "coaAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProjCd_A1", "coaProjCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"coaProdCd_A1", "coaProdCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaAfflCd_A1", "coaAfflCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaChCd_A1", "coaChCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"glblCmpyCd_A1", "glblCmpyCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrSegAllocPk_A1", "dsContrSegAllocPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPk_A1", "dsContrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_A1", "dsContrDtlPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcInvChrgTpCd_A1", "svcInvChrgTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "6", null},
	{"xxRowNum_A1", "xxRowNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxInsUpdDelFlg_A1", "xxInsUpdDelFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
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

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"COA_AFFL_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflAcctNm_A1
        {"COA_ACCT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctDescTxt_A1
        {"PRC_ALLOC_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAllocRate_A1
        {"PRC_ALLOC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAllocAmt_A1
        {"COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_A1
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_A1
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_A1
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_A1
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_A1
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_A1
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_A1
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_A1
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_A1
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A1
        {"DS_CONTR_SEG_ALLOC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSegAllocPk_A1
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_A1
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_A1
        {"SVC_INV_CHRG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvChrgTpCd_A1
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A1
        {"XX_INS_UPD_DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInsUpdDelFlg_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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
