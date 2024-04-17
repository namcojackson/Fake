//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171226051006000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0570_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0570;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0570 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0570_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PRC_EFF_SQ_NUM_A1*/
	public final EZDCBigDecimalItem              dsContrPrcEffSqNum_A1;

    /** CONTR_PRC_EFF_FROM_DT_A1*/
	public final EZDCDateItem              contrPrcEffFromDt_A1;

    /** CONTR_PRC_EFF_THRU_DT_A1*/
	public final EZDCDateItem              contrPrcEffThruDt_A1;

    /** BLLG_CYCLE_CD_A3*/
	public final EZDCStringItem              bllgCycleCd_A3;

    /** BLLG_MIN_CNT_A1*/
	public final EZDCBigDecimalItem              bllgMinCnt_A1;

    /** BLLG_MIN_AMT_RATE_A1*/
	public final EZDCBigDecimalItem              bllgMinAmtRate_A1;

    /** BLLG_ROLL_OVER_RATIO_A1*/
	public final EZDCBigDecimalItem              bllgRollOverRatio_A1;

    /** BLLG_FREE_COPY_CNT_A1*/
	public final EZDCBigDecimalItem              bllgFreeCopyCnt_A1;

    /** XS_MTR_COPY_QTY_A1*/
	public final EZDCBigDecimalItem              xsMtrCopyQty_A1;

    /** XS_MTR_AMT_RATE_A1*/
	public final EZDCBigDecimalItem              xsMtrAmtRate_A1;

    /** DS_CONTR_DTL_STS_CD_A1*/
	public final EZDCStringItem              dsContrDtlStsCd_A1;

    /** DS_CONTR_DTL_STS_NM_A1*/
	public final EZDCStringItem              dsContrDtlStsNm_A1;

    /** CRAT_DT_A1*/
	public final EZDCDateItem              cratDt_A1;

    /** DS_CONTR_PRC_EFF_PK_A1*/
	public final EZDCBigDecimalItem              dsContrPrcEffPk_A1;

    /** DS_CONTR_PRC_EFF_STS_CD_A2*/
	public final EZDCStringItem              dsContrPrcEffStsCd_A2;

    /** QLTY_ASRN_HLD_FLG_A2*/
	public final EZDCStringItem              qltyAsrnHldFlg_A2;

    /** MTR_HLD_FLG_A2*/
	public final EZDCStringItem              mtrHldFlg_A2;

    /** CONTR_HLD_FLG_A2*/
	public final EZDCStringItem              contrHldFlg_A2;

    /** BLLG_HLD_FLG_A2*/
	public final EZDCStringItem              bllgHldFlg_A2;

    /** _EZUpdateDateTime_A1*/
	public final EZDCStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDCStringItem              ezUpTimeZone_A1;

    /** XX_EXST_FLG_X1*/
	public final EZDCStringItem              xxExstFlg_X1;

    /** XX_EXST_FLG_X2*/
	public final EZDCStringItem              xxExstFlg_X2;

    /** XX_EXST_FLG_X3*/
	public final EZDCStringItem              xxExstFlg_X3;

    /** CONTR_XS_COPY_PK_A1*/
	public final EZDCBigDecimalItem              contrXsCopyPk_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDCStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDCStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDCStringItem              xxRecHistTblNm_A1;


	/**
	 * NSAL0570_ACMsg is constructor.
	 * The initialization when the instance of NSAL0570_ACMsg is generated.
	 */
	public NSAL0570_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0570_ACMsg is constructor.
	 * The initialization when the instance of NSAL0570_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0570_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPrcEffSqNum_A1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffSqNum_A1");
		contrPrcEffFromDt_A1 = (EZDCDateItem)newItem("contrPrcEffFromDt_A1");
		contrPrcEffThruDt_A1 = (EZDCDateItem)newItem("contrPrcEffThruDt_A1");
		bllgCycleCd_A3 = (EZDCStringItem)newItem("bllgCycleCd_A3");
		bllgMinCnt_A1 = (EZDCBigDecimalItem)newItem("bllgMinCnt_A1");
		bllgMinAmtRate_A1 = (EZDCBigDecimalItem)newItem("bllgMinAmtRate_A1");
		bllgRollOverRatio_A1 = (EZDCBigDecimalItem)newItem("bllgRollOverRatio_A1");
		bllgFreeCopyCnt_A1 = (EZDCBigDecimalItem)newItem("bllgFreeCopyCnt_A1");
		xsMtrCopyQty_A1 = (EZDCBigDecimalItem)newItem("xsMtrCopyQty_A1");
		xsMtrAmtRate_A1 = (EZDCBigDecimalItem)newItem("xsMtrAmtRate_A1");
		dsContrDtlStsCd_A1 = (EZDCStringItem)newItem("dsContrDtlStsCd_A1");
		dsContrDtlStsNm_A1 = (EZDCStringItem)newItem("dsContrDtlStsNm_A1");
		cratDt_A1 = (EZDCDateItem)newItem("cratDt_A1");
		dsContrPrcEffPk_A1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffPk_A1");
		dsContrPrcEffStsCd_A2 = (EZDCStringItem)newItem("dsContrPrcEffStsCd_A2");
		qltyAsrnHldFlg_A2 = (EZDCStringItem)newItem("qltyAsrnHldFlg_A2");
		mtrHldFlg_A2 = (EZDCStringItem)newItem("mtrHldFlg_A2");
		contrHldFlg_A2 = (EZDCStringItem)newItem("contrHldFlg_A2");
		bllgHldFlg_A2 = (EZDCStringItem)newItem("bllgHldFlg_A2");
		ezUpTime_A1 = (EZDCStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDCStringItem)newItem("ezUpTimeZone_A1");
		xxExstFlg_X1 = (EZDCStringItem)newItem("xxExstFlg_X1");
		xxExstFlg_X2 = (EZDCStringItem)newItem("xxExstFlg_X2");
		xxExstFlg_X3 = (EZDCStringItem)newItem("xxExstFlg_X3");
		contrXsCopyPk_A1 = (EZDCBigDecimalItem)newItem("contrXsCopyPk_A1");
		xxRecHistCratTs_A1 = (EZDCStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDCStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDCStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDCStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0570_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0570_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPrcEffSqNum_A1", "dsContrPrcEffSqNum_A1", "A1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"contrPrcEffFromDt_A1", "contrPrcEffFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"contrPrcEffThruDt_A1", "contrPrcEffThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleCd_A3", "bllgCycleCd_A3", "A3", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgMinCnt_A1", "bllgMinCnt_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgMinAmtRate_A1", "bllgMinAmtRate_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"bllgRollOverRatio_A1", "bllgRollOverRatio_A1", "A1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"bllgFreeCopyCnt_A1", "bllgFreeCopyCnt_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrCopyQty_A1", "xsMtrCopyQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_A1", "xsMtrAmtRate_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"dsContrDtlStsCd_A1", "dsContrDtlStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrDtlStsNm_A1", "dsContrDtlStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"cratDt_A1", "cratDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrPrcEffPk_A1", "dsContrPrcEffPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffStsCd_A2", "dsContrPrcEffStsCd_A2", "A2", null, TYPE_HANKAKUEISU, "4", null},
	{"qltyAsrnHldFlg_A2", "qltyAsrnHldFlg_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"mtrHldFlg_A2", "mtrHldFlg_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"contrHldFlg_A2", "contrHldFlg_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgHldFlg_A2", "bllgHldFlg_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxExstFlg_X1", "xxExstFlg_X1", "X1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxExstFlg_X2", "xxExstFlg_X2", "X2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxExstFlg_X3", "xxExstFlg_X3", "X3", null, TYPE_HANKAKUEISU, "1", null},
	{"contrXsCopyPk_A1", "contrXsCopyPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
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

        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_A1
        {"CONTR_PRC_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrPrcEffFromDt_A1
        {"CONTR_PRC_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrPrcEffThruDt_A1
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_A3
        {"BLLG_MIN_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinCnt_A1
        {"BLLG_MIN_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinAmtRate_A1
        {"BLLG_ROLL_OVER_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgRollOverRatio_A1
        {"BLLG_FREE_COPY_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgFreeCopyCnt_A1
        {"XS_MTR_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_A1
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_A1
        {"DS_CONTR_DTL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlStsCd_A1
        {"DS_CONTR_DTL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlStsNm_A1
        {"CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratDt_A1
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_A1
        {"DS_CONTR_PRC_EFF_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffStsCd_A2
        {"QLTY_ASRN_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qltyAsrnHldFlg_A2
        {"MTR_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrHldFlg_A2
        {"CONTR_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrHldFlg_A2
        {"BLLG_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgHldFlg_A2
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_X1
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_X2
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_X3
        {"CONTR_XS_COPY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
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
