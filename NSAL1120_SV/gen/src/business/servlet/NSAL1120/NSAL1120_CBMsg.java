//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220404140844000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1120_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1120 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1120_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_C1*/
	public final EZDBBigDecimalItem              xxRowNum_C1;

    /** XX_ROW_NUM_C2*/
	public final EZDBBigDecimalItem              xxRowNum_C2;

    /** XX_DPLY_CTRL_FLG_C1*/
	public final EZDBStringItem              xxDplyCtrlFlg_C1;

    /** XX_DPLY_CTRL_FLG_C2*/
	public final EZDBStringItem              xxDplyCtrlFlg_C2;

    /** SVC_CR_REBIL_MTR_BLLG_PK_C*/
	public final EZDBBigDecimalItem              svcCrRebilMtrBllgPk_C;

    /** SVC_INV_LINE_MTR_PK_C*/
	public final EZDBBigDecimalItem              svcInvLineMtrPk_C;

    /** SVC_CR_REBIL_XS_MTR_BLLG_PK_C*/
	public final EZDBBigDecimalItem              svcCrRebilXsMtrBllgPk_C;

    /** SVC_INV_LINE_XS_MTR_PK_C*/
	public final EZDBBigDecimalItem              svcInvLineXsMtrPk_C;

    /** XX_SCR_ITEM_40_TXT_C*/
	public final EZDBStringItem              xxScrItem40Txt_C;

    /** SER_NUM_C*/
	public final EZDBStringItem              serNum_C;

    /** MTR_BLLG_FROM_DT_C*/
	public final EZDBDateItem              mtrBllgFromDt_C;

    /** MTR_BLLG_THRU_DT_C*/
	public final EZDBDateItem              mtrBllgThruDt_C;

    /** MTR_LB_CD_C*/
	public final EZDBStringItem              mtrLbCd_C;

    /** MTR_LB_DESC_TXT_C*/
	public final EZDBStringItem              mtrLbDescTxt_C;

    /** NEW_MTR_CNT_C*/
	public final EZDBBigDecimalItem              newMtrCnt_C;

    /** XX_LIST_NUM_C*/
	public final EZDBBigDecimalItem              xxListNum_C;

    /** OLD_MTR_CHRG_DEAL_AMT_C*/
	public final EZDBBigDecimalItem              oldMtrChrgDealAmt_C;

    /** OLD_XS_COPY_QTY_C*/
	public final EZDBBigDecimalItem              oldXsCopyQty_C;

    /** OLD_XS_MTR_AMT_RATE_C*/
	public final EZDBBigDecimalItem              oldXsMtrAmtRate_C;

    /** NEW_XS_COPY_QTY_C*/
	public final EZDBBigDecimalItem              newXsCopyQty_C;

    /** NEW_XS_MTR_AMT_RATE_C*/
	public final EZDBBigDecimalItem              newXsMtrAmtRate_C;

    /** OLD_UNIT_XS_COPY_QTY_C*/
	public final EZDBBigDecimalItem              oldUnitXsCopyQty_C;

    /** NEW_UNIT_XS_COPY_QTY_C*/
	public final EZDBBigDecimalItem              newUnitXsCopyQty_C;

    /** ORIG_SVC_INV_NUM_C*/
	public final EZDBStringItem              origSvcInvNum_C;

    /** XX_REC_HIST_CRAT_TS_C*/
	public final EZDBStringItem              xxRecHistCratTs_C;

    /** XX_REC_HIST_CRAT_BY_NM_C*/
	public final EZDBStringItem              xxRecHistCratByNm_C;

    /** XX_REC_HIST_UPD_TS_C*/
	public final EZDBStringItem              xxRecHistUpdTs_C;

    /** XX_REC_HIST_UPD_BY_NM_C*/
	public final EZDBStringItem              xxRecHistUpdByNm_C;

    /** XX_REC_HIST_TBL_NM_C*/
	public final EZDBStringItem              xxRecHistTblNm_C;


	/**
	 * NSAL1120_CBMsg is constructor.
	 * The initialization when the instance of NSAL1120_CBMsg is generated.
	 */
	public NSAL1120_CBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1120_CBMsg is constructor.
	 * The initialization when the instance of NSAL1120_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1120_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_C1 = (EZDBBigDecimalItem)newItem("xxRowNum_C1");
		xxRowNum_C2 = (EZDBBigDecimalItem)newItem("xxRowNum_C2");
		xxDplyCtrlFlg_C1 = (EZDBStringItem)newItem("xxDplyCtrlFlg_C1");
		xxDplyCtrlFlg_C2 = (EZDBStringItem)newItem("xxDplyCtrlFlg_C2");
		svcCrRebilMtrBllgPk_C = (EZDBBigDecimalItem)newItem("svcCrRebilMtrBllgPk_C");
		svcInvLineMtrPk_C = (EZDBBigDecimalItem)newItem("svcInvLineMtrPk_C");
		svcCrRebilXsMtrBllgPk_C = (EZDBBigDecimalItem)newItem("svcCrRebilXsMtrBllgPk_C");
		svcInvLineXsMtrPk_C = (EZDBBigDecimalItem)newItem("svcInvLineXsMtrPk_C");
		xxScrItem40Txt_C = (EZDBStringItem)newItem("xxScrItem40Txt_C");
		serNum_C = (EZDBStringItem)newItem("serNum_C");
		mtrBllgFromDt_C = (EZDBDateItem)newItem("mtrBllgFromDt_C");
		mtrBllgThruDt_C = (EZDBDateItem)newItem("mtrBllgThruDt_C");
		mtrLbCd_C = (EZDBStringItem)newItem("mtrLbCd_C");
		mtrLbDescTxt_C = (EZDBStringItem)newItem("mtrLbDescTxt_C");
		newMtrCnt_C = (EZDBBigDecimalItem)newItem("newMtrCnt_C");
		xxListNum_C = (EZDBBigDecimalItem)newItem("xxListNum_C");
		oldMtrChrgDealAmt_C = (EZDBBigDecimalItem)newItem("oldMtrChrgDealAmt_C");
		oldXsCopyQty_C = (EZDBBigDecimalItem)newItem("oldXsCopyQty_C");
		oldXsMtrAmtRate_C = (EZDBBigDecimalItem)newItem("oldXsMtrAmtRate_C");
		newXsCopyQty_C = (EZDBBigDecimalItem)newItem("newXsCopyQty_C");
		newXsMtrAmtRate_C = (EZDBBigDecimalItem)newItem("newXsMtrAmtRate_C");
		oldUnitXsCopyQty_C = (EZDBBigDecimalItem)newItem("oldUnitXsCopyQty_C");
		newUnitXsCopyQty_C = (EZDBBigDecimalItem)newItem("newUnitXsCopyQty_C");
		origSvcInvNum_C = (EZDBStringItem)newItem("origSvcInvNum_C");
		xxRecHistCratTs_C = (EZDBStringItem)newItem("xxRecHistCratTs_C");
		xxRecHistCratByNm_C = (EZDBStringItem)newItem("xxRecHistCratByNm_C");
		xxRecHistUpdTs_C = (EZDBStringItem)newItem("xxRecHistUpdTs_C");
		xxRecHistUpdByNm_C = (EZDBStringItem)newItem("xxRecHistUpdByNm_C");
		xxRecHistTblNm_C = (EZDBStringItem)newItem("xxRecHistTblNm_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1120_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1120_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_C1", "xxRowNum_C1", "C1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRowNum_C2", "xxRowNum_C2", "C2", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxDplyCtrlFlg_C1", "xxDplyCtrlFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_C2", "xxDplyCtrlFlg_C2", "C2", null, TYPE_HANKAKUEISU, "1", null},
	{"svcCrRebilMtrBllgPk_C", "svcCrRebilMtrBllgPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcInvLineMtrPk_C", "svcInvLineMtrPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCrRebilXsMtrBllgPk_C", "svcCrRebilXsMtrBllgPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcInvLineXsMtrPk_C", "svcInvLineXsMtrPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxScrItem40Txt_C", "xxScrItem40Txt_C", "C", null, TYPE_HANKAKUEISU, "40", null},
	{"serNum_C", "serNum_C", "C", null, TYPE_HANKAKUEISU, "30", null},
	{"mtrBllgFromDt_C", "mtrBllgFromDt_C", "C", null, TYPE_NENTSUKIHI, "8", null},
	{"mtrBllgThruDt_C", "mtrBllgThruDt_C", "C", null, TYPE_NENTSUKIHI, "8", null},
	{"mtrLbCd_C", "mtrLbCd_C", "C", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_C", "mtrLbDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"newMtrCnt_C", "newMtrCnt_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxListNum_C", "xxListNum_C", "C", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"oldMtrChrgDealAmt_C", "oldMtrChrgDealAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"oldXsCopyQty_C", "oldXsCopyQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"oldXsMtrAmtRate_C", "oldXsMtrAmtRate_C", "C", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"newXsCopyQty_C", "newXsCopyQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"newXsMtrAmtRate_C", "newXsMtrAmtRate_C", "C", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"oldUnitXsCopyQty_C", "oldUnitXsCopyQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"newUnitXsCopyQty_C", "newUnitXsCopyQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"origSvcInvNum_C", "origSvcInvNum_C", "C", null, TYPE_HANKAKUEISU, "13", null},
	{"xxRecHistCratTs_C", "xxRecHistCratTs_C", "C", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_C", "xxRecHistCratByNm_C", "C", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_C", "xxRecHistUpdTs_C", "C", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_C", "xxRecHistUpdByNm_C", "C", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_C", "xxRecHistTblNm_C", "C", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_C1
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_C2
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_C1
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_C2
        {"SVC_CR_REBIL_MTR_BLLG_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilMtrBllgPk_C
        {"SVC_INV_LINE_MTR_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvLineMtrPk_C
        {"SVC_CR_REBIL_XS_MTR_BLLG_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilXsMtrBllgPk_C
        {"SVC_INV_LINE_XS_MTR_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvLineXsMtrPk_C
        {"XX_SCR_ITEM_40_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem40Txt_C
        {"SER_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_C
        {"MTR_BLLG_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//mtrBllgFromDt_C
        {"MTR_BLLG_THRU_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//mtrBllgThruDt_C
        {"MTR_LB_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_C
        {"MTR_LB_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_C
        {"NEW_MTR_CNT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//newMtrCnt_C
        {"XX_LIST_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxListNum_C
        {"OLD_MTR_CHRG_DEAL_AMT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//oldMtrChrgDealAmt_C
        {"OLD_XS_COPY_QTY",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//oldXsCopyQty_C
        {"OLD_XS_MTR_AMT_RATE",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//oldXsMtrAmtRate_C
        {"NEW_XS_COPY_QTY",  NO,  "0",null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//newXsCopyQty_C
        {"NEW_XS_MTR_AMT_RATE",  NO,  "0",null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//newXsMtrAmtRate_C
        {"OLD_UNIT_XS_COPY_QTY",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//oldUnitXsCopyQty_C
        {"NEW_UNIT_XS_COPY_QTY",  NO,  "0",null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//newUnitXsCopyQty_C
        {"ORIG_SVC_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSvcInvNum_C
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_C
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_C
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_C
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_C
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_C
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
