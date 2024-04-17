//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170524135038000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1140_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1140 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1140_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_A*/
	public final EZDCBigDecimalItem              xxRowNum_A;

    /** FRZ_FLG_A*/
	public final EZDCStringItem              frzFlg_A;

    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** ABUSE_FLG_A*/
	public final EZDCStringItem              abuseFlg_A;

    /** OVWRT_ABUSE_FLG_A*/
	public final EZDCStringItem              ovwrtAbuseFlg_A;

    /** ABUSE_OVWRT_RSN_DESC_TXT_A*/
	public final EZDCStringItem              abuseOvwrtRsnDescTxt_A;

    /** OTH_CONTR_ABUSE_FLG_A*/
	public final EZDCStringItem              othContrAbuseFlg_A;

    /** PROC_DT_A*/
	public final EZDCDateItem              procDt_A;

    /** TERM_COND_CHK_FLG_A*/
	public final EZDCStringItem              termCondChkFlg_A;

    /** TERM_COND_CHK_DT_A*/
	public final EZDCDateItem              termCondChkDt_A;

    /** SHIP_TO_CUST_ACCT_CD_A*/
	public final EZDCStringItem              shipToCustAcctCd_A;

    /** SHIP_TO_CUST_ACCT_CD_LK*/
	public final EZDCStringItem              shipToCustAcctCd_LK;

    /** DS_ACCT_NM_A*/
	public final EZDCStringItem              dsAcctNm_A;

    /** DS_ACCT_GRP_DESC_TXT_A*/
	public final EZDCStringItem              dsAcctGrpDescTxt_A;

    /** DS_CONTR_NUM_A*/
	public final EZDCStringItem              dsContrNum_A;

    /** DS_CONTR_NUM_LK*/
	public final EZDCStringItem              dsContrNum_LK;

    /** SVC_CONTR_BR_DESC_TXT_A*/
	public final EZDCStringItem              svcContrBrDescTxt_A;

    /** SVC_RG_DESC_TXT_A*/
	public final EZDCStringItem              svcRgDescTxt_A;

    /** DS_CONTR_EDI_DESC_TXT_A*/
	public final EZDCStringItem              dsContrEdiDescTxt_A;

    /** SVC_COV_TMPL_TP_DESC_TXT_A*/
	public final EZDCStringItem              svcCovTmplTpDescTxt_A;

    /** NO_MAIN_UNIT_CNT_A*/
	public final EZDCBigDecimalItem              noMainUnitCnt_A;

    /** BLLG_CYCLE_DESC_TXT_A*/
	public final EZDCStringItem              bllgCycleDescTxt_A;

    /** CONTR_VRSN_EFF_FROM_DT_A*/
	public final EZDCDateItem              contrVrsnEffFromDt_A;

    /** XX_TOT_BASE_AMT_A*/
	public final EZDCBigDecimalItem              xxTotBaseAmt_A;

    /** XX_USED_QTY_A*/
	public final EZDCBigDecimalItem              xxUsedQty_A;

    /** ABUSE_VAR_PCT_A*/
	public final EZDCBigDecimalItem              abuseVarPct_A;

    /** ABUSE_BCKT_CD_A*/
	public final EZDCStringItem              abuseBcktCd_A;

    /** DS_CONTR_PK*/
	public final EZDCBigDecimalItem              dsContrPk;

    /** SVC_PGM_MDSE_CD*/
	public final EZDCStringItem              svcPgmMdseCd;

    /** SVC_SPLY_ABUSE_STAGE_PK*/
	public final EZDCBigDecimalItem              svcSplyAbuseStagePk;

    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;


	/**
	 * NSAL1140_ACMsg is constructor.
	 * The initialization when the instance of NSAL1140_ACMsg is generated.
	 */
	public NSAL1140_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1140_ACMsg is constructor.
	 * The initialization when the instance of NSAL1140_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1140_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_A = (EZDCBigDecimalItem)newItem("xxRowNum_A");
		frzFlg_A = (EZDCStringItem)newItem("frzFlg_A");
		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		abuseFlg_A = (EZDCStringItem)newItem("abuseFlg_A");
		ovwrtAbuseFlg_A = (EZDCStringItem)newItem("ovwrtAbuseFlg_A");
		abuseOvwrtRsnDescTxt_A = (EZDCStringItem)newItem("abuseOvwrtRsnDescTxt_A");
		othContrAbuseFlg_A = (EZDCStringItem)newItem("othContrAbuseFlg_A");
		procDt_A = (EZDCDateItem)newItem("procDt_A");
		termCondChkFlg_A = (EZDCStringItem)newItem("termCondChkFlg_A");
		termCondChkDt_A = (EZDCDateItem)newItem("termCondChkDt_A");
		shipToCustAcctCd_A = (EZDCStringItem)newItem("shipToCustAcctCd_A");
		shipToCustAcctCd_LK = (EZDCStringItem)newItem("shipToCustAcctCd_LK");
		dsAcctNm_A = (EZDCStringItem)newItem("dsAcctNm_A");
		dsAcctGrpDescTxt_A = (EZDCStringItem)newItem("dsAcctGrpDescTxt_A");
		dsContrNum_A = (EZDCStringItem)newItem("dsContrNum_A");
		dsContrNum_LK = (EZDCStringItem)newItem("dsContrNum_LK");
		svcContrBrDescTxt_A = (EZDCStringItem)newItem("svcContrBrDescTxt_A");
		svcRgDescTxt_A = (EZDCStringItem)newItem("svcRgDescTxt_A");
		dsContrEdiDescTxt_A = (EZDCStringItem)newItem("dsContrEdiDescTxt_A");
		svcCovTmplTpDescTxt_A = (EZDCStringItem)newItem("svcCovTmplTpDescTxt_A");
		noMainUnitCnt_A = (EZDCBigDecimalItem)newItem("noMainUnitCnt_A");
		bllgCycleDescTxt_A = (EZDCStringItem)newItem("bllgCycleDescTxt_A");
		contrVrsnEffFromDt_A = (EZDCDateItem)newItem("contrVrsnEffFromDt_A");
		xxTotBaseAmt_A = (EZDCBigDecimalItem)newItem("xxTotBaseAmt_A");
		xxUsedQty_A = (EZDCBigDecimalItem)newItem("xxUsedQty_A");
		abuseVarPct_A = (EZDCBigDecimalItem)newItem("abuseVarPct_A");
		abuseBcktCd_A = (EZDCStringItem)newItem("abuseBcktCd_A");
		dsContrPk = (EZDCBigDecimalItem)newItem("dsContrPk");
		svcPgmMdseCd = (EZDCStringItem)newItem("svcPgmMdseCd");
		svcSplyAbuseStagePk = (EZDCBigDecimalItem)newItem("svcSplyAbuseStagePk");
		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1140_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1140_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"frzFlg_A", "frzFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"abuseFlg_A", "abuseFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"ovwrtAbuseFlg_A", "ovwrtAbuseFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"abuseOvwrtRsnDescTxt_A", "abuseOvwrtRsnDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"othContrAbuseFlg_A", "othContrAbuseFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"procDt_A", "procDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"termCondChkFlg_A", "termCondChkFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"termCondChkDt_A", "termCondChkDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"shipToCustAcctCd_A", "shipToCustAcctCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustAcctCd_LK", "shipToCustAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A", "dsAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctGrpDescTxt_A", "dsAcctGrpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrNum_LK", "dsContrNum_LK", "LK", null, TYPE_HANKAKUEISU, "30", null},
	{"svcContrBrDescTxt_A", "svcContrBrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcRgDescTxt_A", "svcRgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrEdiDescTxt_A", "dsContrEdiDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcCovTmplTpDescTxt_A", "svcCovTmplTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"noMainUnitCnt_A", "noMainUnitCnt_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgCycleDescTxt_A", "bllgCycleDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"contrVrsnEffFromDt_A", "contrVrsnEffFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxTotBaseAmt_A", "xxTotBaseAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxUsedQty_A", "xxUsedQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"abuseVarPct_A", "abuseVarPct_A", "A", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"abuseBcktCd_A", "abuseBcktCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcPgmMdseCd", "svcPgmMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"svcSplyAbuseStagePk", "svcSplyAbuseStagePk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"FRZ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frzFlg_A
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"ABUSE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abuseFlg_A
        {"OVWRT_ABUSE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ovwrtAbuseFlg_A
        {"ABUSE_OVWRT_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abuseOvwrtRsnDescTxt_A
        {"OTH_CONTR_ABUSE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//othContrAbuseFlg_A
        {"PROC_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procDt_A
        {"TERM_COND_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termCondChkFlg_A
        {"TERM_COND_CHK_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termCondChkDt_A
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd_A
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd_LK
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A
        {"DS_ACCT_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctGrpDescTxt_A
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_LK
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A
        {"SVC_RG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgDescTxt_A
        {"DS_CONTR_EDI_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrEdiDescTxt_A
        {"SVC_COV_TMPL_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCovTmplTpDescTxt_A
        {"NO_MAIN_UNIT_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//noMainUnitCnt_A
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_A
        {"CONTR_VRSN_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffFromDt_A
        {"XX_TOT_BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotBaseAmt_A
        {"XX_USED_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUsedQty_A
        {"ABUSE_VAR_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abuseVarPct_A
        {"ABUSE_BCKT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abuseBcktCd_A
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"SVC_PGM_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPgmMdseCd
        {"SVC_SPLY_ABUSE_STAGE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSplyAbuseStagePk
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
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

