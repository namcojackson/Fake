//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20221012084141000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0350_JCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0350;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0350 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0350_JCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_BLLG_SCHD_PK_J1*/
	public final EZDCBigDecimalItem              dsContrBllgSchdPk_J1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_J1*/
	public final EZDCBigDecimalItem              dsContrBllgSchdSmryPk_J1;

    /** DS_CONTR_BLLG_MTR_PK_J1*/
	public final EZDCBigDecimalItem              dsContrBllgMtrPk_J1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_J1*/
	public final EZDCStringItem              dsContrBllgSchdSqNum_J1;

    /** DS_CONTR_BLLG_SCHD_LVL_NUM_J1*/
	public final EZDCStringItem              dsContrBllgSchdLvlNum_J1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_J1*/
	public final EZDCBigDecimalItem              dsContrPrcEffSqNum_J1;

    /** SKIP_RECOV_TP_CD_J3*/
	public final EZDCStringItem              skipRecovTpCd_J3;

    /** BLLG_SCHD_FROM_DT_J1*/
	public final EZDCDateItem              bllgSchdFromDt_J1;

    /** BLLG_SCHD_THRU_DT_J1*/
	public final EZDCDateItem              bllgSchdThruDt_J1;

    /** NEXT_BLLG_DT_J1*/
	public final EZDCDateItem              nextBllgDt_J1;

    /** NEXT_BLLG_DT_J2*/
	public final EZDCDateItem              nextBllgDt_J2;

    /** RVS_SCHD_DT_J1*/
	public final EZDCDateItem              rvsSchdDt_J1;

    /** BASE_PRC_DEAL_AMT_J1*/
	public final EZDCBigDecimalItem              basePrcDealAmt_J1;

    /** MTR_ENTRY_CPLT_FLG_J1*/
	public final EZDCStringItem              mtrEntryCpltFlg_J1;

    /** DS_BLLG_SCHD_STS_CD_J1*/
	public final EZDCStringItem              dsBllgSchdStsCd_J1;

    /** DS_BLLG_SCHD_STS_DESC_TXT_J1*/
	public final EZDCStringItem              dsBllgSchdStsDescTxt_J1;

    /** SVC_INV_NUM_J1*/
	public final EZDCStringItem              svcInvNum_J1;

    /** EIP_RPT_RQST_PK_J1*/
	public final EZDCBigDecimalItem              eipRptRqstPk_J1;

    /** INV_DT_J1*/
	public final EZDCDateItem              invDt_J1;

    /** BASE_ACTL_PRC_DEAL_AMT_J1*/
	public final EZDCBigDecimalItem              baseActlPrcDealAmt_J1;

    /** DEAL_TAX_AMT_J1*/
	public final EZDCBigDecimalItem              dealTaxAmt_J1;

    /** _EZUpdateDateTime_J1*/
	public final EZDCStringItem              ezUpTime_J1;

    /** _EZUpTimeZone_J1*/
	public final EZDCStringItem              ezUpTimeZone_J1;

    /** XX_REC_HIST_CRAT_TS_J1*/
	public final EZDCStringItem              xxRecHistCratTs_J1;

    /** XX_REC_HIST_CRAT_BY_NM_J1*/
	public final EZDCStringItem              xxRecHistCratByNm_J1;

    /** XX_REC_HIST_UPD_TS_J1*/
	public final EZDCStringItem              xxRecHistUpdTs_J1;

    /** XX_REC_HIST_UPD_BY_NM_J1*/
	public final EZDCStringItem              xxRecHistUpdByNm_J1;

    /** XX_REC_HIST_TBL_NM_J1*/
	public final EZDCStringItem              xxRecHistTblNm_J1;


	/**
	 * NSAL0350_JCMsg is constructor.
	 * The initialization when the instance of NSAL0350_JCMsg is generated.
	 */
	public NSAL0350_JCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0350_JCMsg is constructor.
	 * The initialization when the instance of NSAL0350_JCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0350_JCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrBllgSchdPk_J1 = (EZDCBigDecimalItem)newItem("dsContrBllgSchdPk_J1");
		dsContrBllgSchdSmryPk_J1 = (EZDCBigDecimalItem)newItem("dsContrBllgSchdSmryPk_J1");
		dsContrBllgMtrPk_J1 = (EZDCBigDecimalItem)newItem("dsContrBllgMtrPk_J1");
		dsContrBllgSchdSqNum_J1 = (EZDCStringItem)newItem("dsContrBllgSchdSqNum_J1");
		dsContrBllgSchdLvlNum_J1 = (EZDCStringItem)newItem("dsContrBllgSchdLvlNum_J1");
		dsContrPrcEffSqNum_J1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffSqNum_J1");
		skipRecovTpCd_J3 = (EZDCStringItem)newItem("skipRecovTpCd_J3");
		bllgSchdFromDt_J1 = (EZDCDateItem)newItem("bllgSchdFromDt_J1");
		bllgSchdThruDt_J1 = (EZDCDateItem)newItem("bllgSchdThruDt_J1");
		nextBllgDt_J1 = (EZDCDateItem)newItem("nextBllgDt_J1");
		nextBllgDt_J2 = (EZDCDateItem)newItem("nextBllgDt_J2");
		rvsSchdDt_J1 = (EZDCDateItem)newItem("rvsSchdDt_J1");
		basePrcDealAmt_J1 = (EZDCBigDecimalItem)newItem("basePrcDealAmt_J1");
		mtrEntryCpltFlg_J1 = (EZDCStringItem)newItem("mtrEntryCpltFlg_J1");
		dsBllgSchdStsCd_J1 = (EZDCStringItem)newItem("dsBllgSchdStsCd_J1");
		dsBllgSchdStsDescTxt_J1 = (EZDCStringItem)newItem("dsBllgSchdStsDescTxt_J1");
		svcInvNum_J1 = (EZDCStringItem)newItem("svcInvNum_J1");
		eipRptRqstPk_J1 = (EZDCBigDecimalItem)newItem("eipRptRqstPk_J1");
		invDt_J1 = (EZDCDateItem)newItem("invDt_J1");
		baseActlPrcDealAmt_J1 = (EZDCBigDecimalItem)newItem("baseActlPrcDealAmt_J1");
		dealTaxAmt_J1 = (EZDCBigDecimalItem)newItem("dealTaxAmt_J1");
		ezUpTime_J1 = (EZDCStringItem)newItem("ezUpTime_J1");
		ezUpTimeZone_J1 = (EZDCStringItem)newItem("ezUpTimeZone_J1");
		xxRecHistCratTs_J1 = (EZDCStringItem)newItem("xxRecHistCratTs_J1");
		xxRecHistCratByNm_J1 = (EZDCStringItem)newItem("xxRecHistCratByNm_J1");
		xxRecHistUpdTs_J1 = (EZDCStringItem)newItem("xxRecHistUpdTs_J1");
		xxRecHistUpdByNm_J1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_J1");
		xxRecHistTblNm_J1 = (EZDCStringItem)newItem("xxRecHistTblNm_J1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0350_JCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0350_JCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrBllgSchdPk_J1", "dsContrBllgSchdPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_J1", "dsContrBllgSchdSmryPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk_J1", "dsContrBllgMtrPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_J1", "dsContrBllgSchdSqNum_J1", "J1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrBllgSchdLvlNum_J1", "dsContrBllgSchdLvlNum_J1", "J1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffSqNum_J1", "dsContrPrcEffSqNum_J1", "J1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"skipRecovTpCd_J3", "skipRecovTpCd_J3", "J3", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgSchdFromDt_J1", "bllgSchdFromDt_J1", "J1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_J1", "bllgSchdThruDt_J1", "J1", null, TYPE_NENTSUKIHI, "8", null},
	{"nextBllgDt_J1", "nextBllgDt_J1", "J1", null, TYPE_NENTSUKIHI, "8", null},
	{"nextBllgDt_J2", "nextBllgDt_J2", "J2", null, TYPE_NENTSUKIHI, "8", null},
	{"rvsSchdDt_J1", "rvsSchdDt_J1", "J1", null, TYPE_NENTSUKIHI, "8", null},
	{"basePrcDealAmt_J1", "basePrcDealAmt_J1", "J1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mtrEntryCpltFlg_J1", "mtrEntryCpltFlg_J1", "J1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsBllgSchdStsCd_J1", "dsBllgSchdStsCd_J1", "J1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsBllgSchdStsDescTxt_J1", "dsBllgSchdStsDescTxt_J1", "J1", null, TYPE_HANKAKUEISU, "50", null},
	{"svcInvNum_J1", "svcInvNum_J1", "J1", null, TYPE_HANKAKUEISU, "13", null},
	{"eipRptRqstPk_J1", "eipRptRqstPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invDt_J1", "invDt_J1", "J1", null, TYPE_NENTSUKIHI, "8", null},
	{"baseActlPrcDealAmt_J1", "baseActlPrcDealAmt_J1", "J1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealTaxAmt_J1", "dealTaxAmt_J1", "J1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ezUpTime_J1", "ezUpTime_J1", "J1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_J1", "ezUpTimeZone_J1", "J1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_J1", "xxRecHistCratTs_J1", "J1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_J1", "xxRecHistCratByNm_J1", "J1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_J1", "xxRecHistUpdTs_J1", "J1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_J1", "xxRecHistUpdByNm_J1", "J1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_J1", "xxRecHistTblNm_J1", "J1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_BLLG_SCHD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdPk_J1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_J1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_J1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_J1
        {"DS_CONTR_BLLG_SCHD_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdLvlNum_J1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_J1
        {"SKIP_RECOV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//skipRecovTpCd_J3
        {"BLLG_SCHD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdFromDt_J1
        {"BLLG_SCHD_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdThruDt_J1
        {"NEXT_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextBllgDt_J1
        {"NEXT_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextBllgDt_J2
        {"RVS_SCHD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rvsSchdDt_J1
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_J1
        {"MTR_ENTRY_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEntryCpltFlg_J1
        {"DS_BLLG_SCHD_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBllgSchdStsCd_J1
        {"DS_BLLG_SCHD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBllgSchdStsDescTxt_J1
        {"SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvNum_J1
        {"EIP_RPT_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_J1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_J1
        {"BASE_ACTL_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseActlPrcDealAmt_J1
        {"DEAL_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealTaxAmt_J1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_J1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_J1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_J1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_J1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_J1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_J1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_J1
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

