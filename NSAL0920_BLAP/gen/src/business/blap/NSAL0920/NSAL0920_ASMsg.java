//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180524162707000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0920_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0920;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0920 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0920_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CONTR_BLLG_PK_A*/
	public final EZDSBigDecimalItem              svcContrBllgPk_A;

    /** SVC_CONTR_XS_MTR_BLLG_PK_A*/
	public final EZDSBigDecimalItem              svcContrXsMtrBllgPk_A;

    /** BLLG_APVL_REQ_FLG_A*/
	public final EZDSStringItem              bllgApvlReqFlg_A;

    /** BLLG_APVL_CPLT_FLG_A*/
	public final EZDSStringItem              bllgApvlCpltFlg_A;

    /** OVRD_NEXT_BLLG_DT_A*/
	public final EZDSDateItem              ovrdNextBllgDt_A;

    /** SVC_CONTR_BR_DESC_TXT_A*/
	public final EZDSStringItem              svcContrBrDescTxt_A;

    /** DS_CONTR_NUM_A*/
	public final EZDSStringItem              dsContrNum_A;

    /** DS_CONTR_CATG_CD_A*/
	public final EZDSStringItem              dsContrCatgCd_A;

    /** DS_CONTR_CATG_DESC_TXT_A*/
	public final EZDSStringItem              dsContrCatgDescTxt_A;

    /** DS_CONTR_CTRL_STS_NM_A*/
	public final EZDSStringItem              dsContrCtrlStsNm_A;

    /** SER_NUM_A*/
	public final EZDSStringItem              serNum_A;

    /** CUR_LOC_NUM_A*/
	public final EZDSStringItem              curLocNum_A;

    /** LOC_NM_A*/
	public final EZDSStringItem              locNm_A;

    /** DS_ACCT_NUM_A*/
	public final EZDSStringItem              dsAcctNum_A;

    /** BILL_TO_CUST_CD_A*/
	public final EZDSStringItem              billToCustCd_A;

    /** MTR_LB_DESC_TXT_A*/
	public final EZDSStringItem              mtrLbDescTxt_A;

    /** MTR_BLLG_FROM_DT_A*/
	public final EZDSDateItem              mtrBllgFromDt_A;

    /** MTR_BLLG_THRU_DT_A*/
	public final EZDSDateItem              mtrBllgThruDt_A;

    /** XS_MTR_FROM_COPY_QTY_A*/
	public final EZDSBigDecimalItem              xsMtrFromCopyQty_A;

    /** XS_MTR_AMT_RATE_A*/
	public final EZDSBigDecimalItem              xsMtrAmtRate_A;

    /** READ_MTR_CNT_AS*/
	public final EZDSBigDecimalItem              readMtrCnt_AS;

    /** READ_MTR_CNT_AE*/
	public final EZDSBigDecimalItem              readMtrCnt_AE;

    /** MTR_CHRG_DEAL_AMT_A*/
	public final EZDSBigDecimalItem              mtrChrgDealAmt_A;

    /** XX_YES_NO_NM_A*/
	public final EZDSStringItem              xxYesNoNm_A;

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
	 * NSAL0920_ASMsg is constructor.
	 * The initialization when the instance of NSAL0920_ASMsg is generated.
	 */
	public NSAL0920_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0920_ASMsg is constructor.
	 * The initialization when the instance of NSAL0920_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0920_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcContrBllgPk_A = (EZDSBigDecimalItem)newItem("svcContrBllgPk_A");
		svcContrXsMtrBllgPk_A = (EZDSBigDecimalItem)newItem("svcContrXsMtrBllgPk_A");
		bllgApvlReqFlg_A = (EZDSStringItem)newItem("bllgApvlReqFlg_A");
		bllgApvlCpltFlg_A = (EZDSStringItem)newItem("bllgApvlCpltFlg_A");
		ovrdNextBllgDt_A = (EZDSDateItem)newItem("ovrdNextBllgDt_A");
		svcContrBrDescTxt_A = (EZDSStringItem)newItem("svcContrBrDescTxt_A");
		dsContrNum_A = (EZDSStringItem)newItem("dsContrNum_A");
		dsContrCatgCd_A = (EZDSStringItem)newItem("dsContrCatgCd_A");
		dsContrCatgDescTxt_A = (EZDSStringItem)newItem("dsContrCatgDescTxt_A");
		dsContrCtrlStsNm_A = (EZDSStringItem)newItem("dsContrCtrlStsNm_A");
		serNum_A = (EZDSStringItem)newItem("serNum_A");
		curLocNum_A = (EZDSStringItem)newItem("curLocNum_A");
		locNm_A = (EZDSStringItem)newItem("locNm_A");
		dsAcctNum_A = (EZDSStringItem)newItem("dsAcctNum_A");
		billToCustCd_A = (EZDSStringItem)newItem("billToCustCd_A");
		mtrLbDescTxt_A = (EZDSStringItem)newItem("mtrLbDescTxt_A");
		mtrBllgFromDt_A = (EZDSDateItem)newItem("mtrBllgFromDt_A");
		mtrBllgThruDt_A = (EZDSDateItem)newItem("mtrBllgThruDt_A");
		xsMtrFromCopyQty_A = (EZDSBigDecimalItem)newItem("xsMtrFromCopyQty_A");
		xsMtrAmtRate_A = (EZDSBigDecimalItem)newItem("xsMtrAmtRate_A");
		readMtrCnt_AS = (EZDSBigDecimalItem)newItem("readMtrCnt_AS");
		readMtrCnt_AE = (EZDSBigDecimalItem)newItem("readMtrCnt_AE");
		mtrChrgDealAmt_A = (EZDSBigDecimalItem)newItem("mtrChrgDealAmt_A");
		xxYesNoNm_A = (EZDSStringItem)newItem("xxYesNoNm_A");
		xxRecHistCratTs_A = (EZDSStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDSStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDSStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDSStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDSStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0920_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0920_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcContrBllgPk_A", "svcContrBllgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcContrXsMtrBllgPk_A", "svcContrXsMtrBllgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"bllgApvlReqFlg_A", "bllgApvlReqFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgApvlCpltFlg_A", "bllgApvlCpltFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"ovrdNextBllgDt_A", "ovrdNextBllgDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"svcContrBrDescTxt_A", "svcContrBrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrCatgCd_A", "dsContrCatgCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrCatgDescTxt_A", "dsContrCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrCtrlStsNm_A", "dsContrCtrlStsNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"curLocNum_A", "curLocNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"locNm_A", "locNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"dsAcctNum_A", "dsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_A", "billToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"mtrLbDescTxt_A", "mtrLbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"mtrBllgFromDt_A", "mtrBllgFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"mtrBllgThruDt_A", "mtrBllgThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xsMtrFromCopyQty_A", "xsMtrFromCopyQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_A", "xsMtrAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"readMtrCnt_AS", "readMtrCnt_AS", "AS", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"readMtrCnt_AE", "readMtrCnt_AE", "AE", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mtrChrgDealAmt_A", "mtrChrgDealAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxYesNoNm_A", "xxYesNoNm_A", "A", null, TYPE_HANKAKUEISU, "3", null},
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

        {"SVC_CONTR_BLLG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBllgPk_A
        {"SVC_CONTR_XS_MTR_BLLG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrXsMtrBllgPk_A
        {"BLLG_APVL_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgApvlReqFlg_A
        {"BLLG_APVL_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgApvlCpltFlg_A
        {"OVRD_NEXT_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ovrdNextBllgDt_A
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_A
        {"DS_CONTR_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgDescTxt_A
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"CUR_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curLocNum_A
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_A
        {"MTR_BLLG_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrBllgFromDt_A
        {"MTR_BLLG_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrBllgThruDt_A
        {"XS_MTR_FROM_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrFromCopyQty_A
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_A
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt_AS
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt_AE
        {"MTR_CHRG_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrChrgDealAmt_A
        {"XX_YES_NO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoNm_A
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

