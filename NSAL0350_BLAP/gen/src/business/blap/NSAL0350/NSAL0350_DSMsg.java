//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20221012084143000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0350_DSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSAL0350 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0350_DSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_BLLG_SCHD_PK_D1*/
	public final EZDSBigDecimalItem              dsContrBllgSchdPk_D1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_D1*/
	public final EZDSBigDecimalItem              dsContrBllgSchdSmryPk_D1;

    /** DS_CONTR_BLLG_MTR_PK_D1*/
	public final EZDSBigDecimalItem              dsContrBllgMtrPk_D1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_D1*/
	public final EZDSStringItem              dsContrBllgSchdSqNum_D1;

    /** DS_CONTR_BLLG_SCHD_LVL_NUM_D1*/
	public final EZDSStringItem              dsContrBllgSchdLvlNum_D1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_D1*/
	public final EZDSBigDecimalItem              dsContrPrcEffSqNum_D1;

    /** SKIP_RECOV_TP_CD_D3*/
	public final EZDSStringItem              skipRecovTpCd_D3;

    /** BLLG_SCHD_FROM_DT_D1*/
	public final EZDSDateItem              bllgSchdFromDt_D1;

    /** BLLG_SCHD_THRU_DT_D1*/
	public final EZDSDateItem              bllgSchdThruDt_D1;

    /** NEXT_BLLG_DT_D1*/
	public final EZDSDateItem              nextBllgDt_D1;

    /** NEXT_BLLG_DT_D2*/
	public final EZDSDateItem              nextBllgDt_D2;

    /** RVS_SCHD_DT_D1*/
	public final EZDSDateItem              rvsSchdDt_D1;

    /** BASE_PRC_DEAL_AMT_D1*/
	public final EZDSBigDecimalItem              basePrcDealAmt_D1;

    /** MTR_ENTRY_CPLT_FLG_D1*/
	public final EZDSStringItem              mtrEntryCpltFlg_D1;

    /** DS_BLLG_SCHD_STS_CD_D1*/
	public final EZDSStringItem              dsBllgSchdStsCd_D1;

    /** DS_BLLG_SCHD_STS_DESC_TXT_D1*/
	public final EZDSStringItem              dsBllgSchdStsDescTxt_D1;

    /** SVC_INV_NUM_D1*/
	public final EZDSStringItem              svcInvNum_D1;

    /** EIP_RPT_RQST_PK_D1*/
	public final EZDSBigDecimalItem              eipRptRqstPk_D1;

    /** INV_DT_D1*/
	public final EZDSDateItem              invDt_D1;

    /** BASE_ACTL_PRC_DEAL_AMT_D1*/
	public final EZDSBigDecimalItem              baseActlPrcDealAmt_D1;

    /** DEAL_TAX_AMT_D1*/
	public final EZDSBigDecimalItem              dealTaxAmt_D1;

    /** _EZUpdateDateTime_D1*/
	public final EZDSStringItem              ezUpTime_D1;

    /** _EZUpTimeZone_D1*/
	public final EZDSStringItem              ezUpTimeZone_D1;

    /** XX_REC_HIST_CRAT_TS_D1*/
	public final EZDSStringItem              xxRecHistCratTs_D1;

    /** XX_REC_HIST_CRAT_BY_NM_D1*/
	public final EZDSStringItem              xxRecHistCratByNm_D1;

    /** XX_REC_HIST_UPD_TS_D1*/
	public final EZDSStringItem              xxRecHistUpdTs_D1;

    /** XX_REC_HIST_UPD_BY_NM_D1*/
	public final EZDSStringItem              xxRecHistUpdByNm_D1;

    /** XX_REC_HIST_TBL_NM_D1*/
	public final EZDSStringItem              xxRecHistTblNm_D1;


	/**
	 * NSAL0350_DSMsg is constructor.
	 * The initialization when the instance of NSAL0350_DSMsg is generated.
	 */
	public NSAL0350_DSMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0350_DSMsg is constructor.
	 * The initialization when the instance of NSAL0350_DSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0350_DSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrBllgSchdPk_D1 = (EZDSBigDecimalItem)newItem("dsContrBllgSchdPk_D1");
		dsContrBllgSchdSmryPk_D1 = (EZDSBigDecimalItem)newItem("dsContrBllgSchdSmryPk_D1");
		dsContrBllgMtrPk_D1 = (EZDSBigDecimalItem)newItem("dsContrBllgMtrPk_D1");
		dsContrBllgSchdSqNum_D1 = (EZDSStringItem)newItem("dsContrBllgSchdSqNum_D1");
		dsContrBllgSchdLvlNum_D1 = (EZDSStringItem)newItem("dsContrBllgSchdLvlNum_D1");
		dsContrPrcEffSqNum_D1 = (EZDSBigDecimalItem)newItem("dsContrPrcEffSqNum_D1");
		skipRecovTpCd_D3 = (EZDSStringItem)newItem("skipRecovTpCd_D3");
		bllgSchdFromDt_D1 = (EZDSDateItem)newItem("bllgSchdFromDt_D1");
		bllgSchdThruDt_D1 = (EZDSDateItem)newItem("bllgSchdThruDt_D1");
		nextBllgDt_D1 = (EZDSDateItem)newItem("nextBllgDt_D1");
		nextBllgDt_D2 = (EZDSDateItem)newItem("nextBllgDt_D2");
		rvsSchdDt_D1 = (EZDSDateItem)newItem("rvsSchdDt_D1");
		basePrcDealAmt_D1 = (EZDSBigDecimalItem)newItem("basePrcDealAmt_D1");
		mtrEntryCpltFlg_D1 = (EZDSStringItem)newItem("mtrEntryCpltFlg_D1");
		dsBllgSchdStsCd_D1 = (EZDSStringItem)newItem("dsBllgSchdStsCd_D1");
		dsBllgSchdStsDescTxt_D1 = (EZDSStringItem)newItem("dsBllgSchdStsDescTxt_D1");
		svcInvNum_D1 = (EZDSStringItem)newItem("svcInvNum_D1");
		eipRptRqstPk_D1 = (EZDSBigDecimalItem)newItem("eipRptRqstPk_D1");
		invDt_D1 = (EZDSDateItem)newItem("invDt_D1");
		baseActlPrcDealAmt_D1 = (EZDSBigDecimalItem)newItem("baseActlPrcDealAmt_D1");
		dealTaxAmt_D1 = (EZDSBigDecimalItem)newItem("dealTaxAmt_D1");
		ezUpTime_D1 = (EZDSStringItem)newItem("ezUpTime_D1");
		ezUpTimeZone_D1 = (EZDSStringItem)newItem("ezUpTimeZone_D1");
		xxRecHistCratTs_D1 = (EZDSStringItem)newItem("xxRecHistCratTs_D1");
		xxRecHistCratByNm_D1 = (EZDSStringItem)newItem("xxRecHistCratByNm_D1");
		xxRecHistUpdTs_D1 = (EZDSStringItem)newItem("xxRecHistUpdTs_D1");
		xxRecHistUpdByNm_D1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_D1");
		xxRecHistTblNm_D1 = (EZDSStringItem)newItem("xxRecHistTblNm_D1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0350_DSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0350_DSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrBllgSchdPk_D1", "dsContrBllgSchdPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_D1", "dsContrBllgSchdSmryPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk_D1", "dsContrBllgMtrPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_D1", "dsContrBllgSchdSqNum_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrBllgSchdLvlNum_D1", "dsContrBllgSchdLvlNum_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffSqNum_D1", "dsContrPrcEffSqNum_D1", "D1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"skipRecovTpCd_D3", "skipRecovTpCd_D3", "D3", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgSchdFromDt_D1", "bllgSchdFromDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_D1", "bllgSchdThruDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"nextBllgDt_D1", "nextBllgDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"nextBllgDt_D2", "nextBllgDt_D2", "D2", null, TYPE_NENTSUKIHI, "8", null},
	{"rvsSchdDt_D1", "rvsSchdDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"basePrcDealAmt_D1", "basePrcDealAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mtrEntryCpltFlg_D1", "mtrEntryCpltFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsBllgSchdStsCd_D1", "dsBllgSchdStsCd_D1", "D1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsBllgSchdStsDescTxt_D1", "dsBllgSchdStsDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"svcInvNum_D1", "svcInvNum_D1", "D1", null, TYPE_HANKAKUEISU, "13", null},
	{"eipRptRqstPk_D1", "eipRptRqstPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invDt_D1", "invDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"baseActlPrcDealAmt_D1", "baseActlPrcDealAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealTaxAmt_D1", "dealTaxAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ezUpTime_D1", "ezUpTime_D1", "D1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D1", "ezUpTimeZone_D1", "D1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_D1", "xxRecHistCratTs_D1", "D1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_D1", "xxRecHistCratByNm_D1", "D1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_D1", "xxRecHistUpdTs_D1", "D1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_D1", "xxRecHistUpdByNm_D1", "D1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_D1", "xxRecHistTblNm_D1", "D1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_BLLG_SCHD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdPk_D1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_D1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_D1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_D1
        {"DS_CONTR_BLLG_SCHD_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdLvlNum_D1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_D1
        {"SKIP_RECOV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//skipRecovTpCd_D3
        {"BLLG_SCHD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdFromDt_D1
        {"BLLG_SCHD_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdThruDt_D1
        {"NEXT_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextBllgDt_D1
        {"NEXT_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextBllgDt_D2
        {"RVS_SCHD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rvsSchdDt_D1
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_D1
        {"MTR_ENTRY_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEntryCpltFlg_D1
        {"DS_BLLG_SCHD_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBllgSchdStsCd_D1
        {"DS_BLLG_SCHD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBllgSchdStsDescTxt_D1
        {"SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvNum_D1
        {"EIP_RPT_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_D1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_D1
        {"BASE_ACTL_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseActlPrcDealAmt_D1
        {"DEAL_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealTaxAmt_D1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_D1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_D1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_D1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_D1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_D1
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
