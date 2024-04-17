//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180803111238000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2170_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2170 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2170_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_SVC_DTL_PK*/
	public final EZDCBigDecimalItem              cpoSvcDtlPk;

    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** DS_IMPT_SVC_LINE_NUM*/
	public final EZDCBigDecimalItem              dsImptSvcLineNum;

    /** DS_IMPT_SVC_MDSE_CD*/
	public final EZDCStringItem              dsImptSvcMdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDCStringItem              mdseDescShortTxt;

    /** PRC_SVC_CONTR_TP_CD*/
	public final EZDCStringItem              prcSvcContrTpCd;

    /** PRC_SVC_PLN_TP_CD*/
	public final EZDCStringItem              prcSvcPlnTpCd;

    /** XX_TOT_AMT_PB*/
	public final EZDCBigDecimalItem              xxTotAmt_PB;

    /** BILL_WITH_EQUIP_FLG*/
	public final EZDCStringItem              billWithEquipFlg;

    /** FROM_PER_MTH_NUM*/
	public final EZDCBigDecimalItem              fromPerMthNum;

    /** TO_PER_MTH_NUM*/
	public final EZDCBigDecimalItem              toPerMthNum;

    /** SHPG_INTVL_MTH_NUM*/
	public final EZDCBigDecimalItem              shpgIntvlMthNum;

    /** FIX_TERM_IN_MTH_AOT*/
	public final EZDCBigDecimalItem              fixTermInMthAot;

    /** MAX_UPLFT_PCT*/
	public final EZDCBigDecimalItem              maxUplftPct;

    /** CPO_SVC_LINE_ACT_CD*/
	public final EZDCStringItem              cpoSvcLineActCd;

    /** DS_CONTR_CATG_CD*/
	public final EZDCStringItem              dsContrCatgCd;

    /** XX_SEL_FLG_CI*/
	public final EZDCStringItem              xxSelFlg_CI;

    /** BASE_BLLG_CYCLE_CD*/
	public final EZDCStringItem              baseBllgCycleCd;

    /** USG_BLLG_CYCLE_CD*/
	public final EZDCStringItem              usgBllgCycleCd;

    /** BILL_BY_TP_DESC_TXT_L*/
	public final EZDCStringItemArray              billByTpDescTxt_L;

    /** BILL_BY_TP_CD_L*/
	public final EZDCStringItemArray              billByTpCd_L;

    /** BILL_BY_TP_CD*/
	public final EZDCStringItem              billByTpCd;

    /** XX_TOT_AMT_S*/
	public final EZDCBigDecimalItem              xxTotAmt_S;

    /** DS_ACCT_NM*/
	public final EZDCStringItem              dsAcctNm;

    /** DS_ACCT_NUM*/
	public final EZDCStringItem              dsAcctNum;

    /** XX_BILL_TO_ACCT_NM_SRCH_TXT*/
	public final EZDCStringItem              xxBillToAcctNmSrchTxt;

    /** SOLD_TO_CUST_LOC_CD*/
	public final EZDCStringItem              soldToCustLocCd;

    /** BILL_TO_CUST_LOC_CD*/
	public final EZDCStringItem              billToCustLocCd;

    /** SHIP_TO_CUST_LOC_CD*/
	public final EZDCStringItem              shipToCustLocCd;

    /** SER_NUM_A*/
	public final EZDCStringItem              serNum_A;

    /** DS_CONTR_PK*/
	public final EZDCBigDecimalItem              dsContrPk;

    /** DS_CONTR_NUM*/
	public final EZDCStringItem              dsContrNum;

    /** DS_CONTR_DTL_PK*/
	public final EZDCBigDecimalItem              dsContrDtlPk;

    /** ADD_ASRY_FLG*/
	public final EZDCStringItem              addAsryFlg;

    /** CPO_SVC_AGMT_VER_NUM*/
	public final EZDCStringItem              cpoSvcAgmtVerNum;

    /** BILL_TO_CUST_ACCT_CD*/
	public final EZDCStringItem              billToCustAcctCd;

    /** BILL_TO_CUST_ACCT_NM*/
	public final EZDCStringItem              billToCustAcctNm;

    /** BILL_TO_CUST_LOC_ADDR*/
	public final EZDCStringItem              billToCustLocAddr;

    /** APPLY_EQUIP_BILL_TO_FLG*/
	public final EZDCStringItem              applyEquipBillToFlg;

    /** XX_CHK_BOX_BH*/
	public final EZDCStringItem              xxChkBox_BH;

    /** DS_CONTR_NUM_LK*/
	public final EZDCStringItem              dsContrNum_LK;

    /** DS_ACCT_NUM_LK*/
	public final EZDCStringItem              dsAcctNum_LK;

    /** SHIP_TO_CUST_LOC_CD_LK*/
	public final EZDCStringItem              shipToCustLocCd_LK;

    /** CPO_SVC_AGMT_VER_NUM_LK*/
	public final EZDCStringItem              cpoSvcAgmtVerNum_LK;

    /** BASE_AMT*/
	public final EZDCBigDecimalItem              baseAmt;

    /** MAN_CONTR_OVRD_FLG*/
	public final EZDCStringItem              manContrOvrdFlg;

    /** MAN_CONTR_OVRD_RSN_CD*/
	public final EZDCStringItem              manContrOvrdRsnCd;

    /** MAN_CONTR_OVRD_RSN_NM*/
	public final EZDCStringItem              manContrOvrdRsnNm;

    /** MAN_CONTR_OVRD_CMNT_TXT*/
	public final EZDCStringItem              manContrOvrdCmntTxt;

    /** XX_EXST_FLG_PR*/
	public final EZDCStringItem              xxExstFlg_PR;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;


	/**
	 * NWAL2170_ACMsg is constructor.
	 * The initialization when the instance of NWAL2170_ACMsg is generated.
	 */
	public NWAL2170_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2170_ACMsg is constructor.
	 * The initialization when the instance of NWAL2170_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2170_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoSvcDtlPk = (EZDCBigDecimalItem)newItem("cpoSvcDtlPk");
		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		dsImptSvcLineNum = (EZDCBigDecimalItem)newItem("dsImptSvcLineNum");
		dsImptSvcMdseCd = (EZDCStringItem)newItem("dsImptSvcMdseCd");
		mdseDescShortTxt = (EZDCStringItem)newItem("mdseDescShortTxt");
		prcSvcContrTpCd = (EZDCStringItem)newItem("prcSvcContrTpCd");
		prcSvcPlnTpCd = (EZDCStringItem)newItem("prcSvcPlnTpCd");
		xxTotAmt_PB = (EZDCBigDecimalItem)newItem("xxTotAmt_PB");
		billWithEquipFlg = (EZDCStringItem)newItem("billWithEquipFlg");
		fromPerMthNum = (EZDCBigDecimalItem)newItem("fromPerMthNum");
		toPerMthNum = (EZDCBigDecimalItem)newItem("toPerMthNum");
		shpgIntvlMthNum = (EZDCBigDecimalItem)newItem("shpgIntvlMthNum");
		fixTermInMthAot = (EZDCBigDecimalItem)newItem("fixTermInMthAot");
		maxUplftPct = (EZDCBigDecimalItem)newItem("maxUplftPct");
		cpoSvcLineActCd = (EZDCStringItem)newItem("cpoSvcLineActCd");
		dsContrCatgCd = (EZDCStringItem)newItem("dsContrCatgCd");
		xxSelFlg_CI = (EZDCStringItem)newItem("xxSelFlg_CI");
		baseBllgCycleCd = (EZDCStringItem)newItem("baseBllgCycleCd");
		usgBllgCycleCd = (EZDCStringItem)newItem("usgBllgCycleCd");
		billByTpDescTxt_L = (EZDCStringItemArray)newItemArray("billByTpDescTxt_L");
		billByTpCd_L = (EZDCStringItemArray)newItemArray("billByTpCd_L");
		billByTpCd = (EZDCStringItem)newItem("billByTpCd");
		xxTotAmt_S = (EZDCBigDecimalItem)newItem("xxTotAmt_S");
		dsAcctNm = (EZDCStringItem)newItem("dsAcctNm");
		dsAcctNum = (EZDCStringItem)newItem("dsAcctNum");
		xxBillToAcctNmSrchTxt = (EZDCStringItem)newItem("xxBillToAcctNmSrchTxt");
		soldToCustLocCd = (EZDCStringItem)newItem("soldToCustLocCd");
		billToCustLocCd = (EZDCStringItem)newItem("billToCustLocCd");
		shipToCustLocCd = (EZDCStringItem)newItem("shipToCustLocCd");
		serNum_A = (EZDCStringItem)newItem("serNum_A");
		dsContrPk = (EZDCBigDecimalItem)newItem("dsContrPk");
		dsContrNum = (EZDCStringItem)newItem("dsContrNum");
		dsContrDtlPk = (EZDCBigDecimalItem)newItem("dsContrDtlPk");
		addAsryFlg = (EZDCStringItem)newItem("addAsryFlg");
		cpoSvcAgmtVerNum = (EZDCStringItem)newItem("cpoSvcAgmtVerNum");
		billToCustAcctCd = (EZDCStringItem)newItem("billToCustAcctCd");
		billToCustAcctNm = (EZDCStringItem)newItem("billToCustAcctNm");
		billToCustLocAddr = (EZDCStringItem)newItem("billToCustLocAddr");
		applyEquipBillToFlg = (EZDCStringItem)newItem("applyEquipBillToFlg");
		xxChkBox_BH = (EZDCStringItem)newItem("xxChkBox_BH");
		dsContrNum_LK = (EZDCStringItem)newItem("dsContrNum_LK");
		dsAcctNum_LK = (EZDCStringItem)newItem("dsAcctNum_LK");
		shipToCustLocCd_LK = (EZDCStringItem)newItem("shipToCustLocCd_LK");
		cpoSvcAgmtVerNum_LK = (EZDCStringItem)newItem("cpoSvcAgmtVerNum_LK");
		baseAmt = (EZDCBigDecimalItem)newItem("baseAmt");
		manContrOvrdFlg = (EZDCStringItem)newItem("manContrOvrdFlg");
		manContrOvrdRsnCd = (EZDCStringItem)newItem("manContrOvrdRsnCd");
		manContrOvrdRsnNm = (EZDCStringItem)newItem("manContrOvrdRsnNm");
		manContrOvrdCmntTxt = (EZDCStringItem)newItem("manContrOvrdCmntTxt");
		xxExstFlg_PR = (EZDCStringItem)newItem("xxExstFlg_PR");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2170_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2170_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoSvcDtlPk", "cpoSvcDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsImptSvcLineNum", "dsImptSvcLineNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsImptSvcMdseCd", "dsImptSvcMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"prcSvcContrTpCd", "prcSvcContrTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcPlnTpCd", "prcSvcPlnTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxTotAmt_PB", "xxTotAmt_PB", "PB", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"billWithEquipFlg", "billWithEquipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"fromPerMthNum", "fromPerMthNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"toPerMthNum", "toPerMthNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"shpgIntvlMthNum", "shpgIntvlMthNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"fixTermInMthAot", "fixTermInMthAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"maxUplftPct", "maxUplftPct", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cpoSvcLineActCd", "cpoSvcLineActCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrCatgCd", "dsContrCatgCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxSelFlg_CI", "xxSelFlg_CI", "CI", null, TYPE_HANKAKUEISU, "1", null},
	{"baseBllgCycleCd", "baseBllgCycleCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"usgBllgCycleCd", "usgBllgCycleCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"billByTpDescTxt_L", "billByTpDescTxt_L", "L", "10", TYPE_HANKAKUEISU, "50", null},
	{"billByTpCd_L", "billByTpCd_L", "L", "10", TYPE_HANKAKUEISU, "2", null},
	{"billByTpCd", "billByTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxTotAmt_S", "xxTotAmt_S", "S", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxBillToAcctNmSrchTxt", "xxBillToAcctNmSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"soldToCustLocCd", "soldToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustLocCd", "billToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd", "shipToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"addAsryFlg", "addAsryFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"cpoSvcAgmtVerNum", "cpoSvcAgmtVerNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"billToCustAcctCd", "billToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustAcctNm", "billToCustAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"billToCustLocAddr", "billToCustLocAddr", null, null, TYPE_HANKAKUEISU, "300", null},
	{"applyEquipBillToFlg", "applyEquipBillToFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_BH", "xxChkBox_BH", "BH", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrNum_LK", "dsContrNum_LK", "LK", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNum_LK", "dsAcctNum_LK", "LK", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd_LK", "shipToCustLocCd_LK", "LK", null, TYPE_HANKAKUEISU, "20", null},
	{"cpoSvcAgmtVerNum_LK", "cpoSvcAgmtVerNum_LK", "LK", null, TYPE_HANKAKUEISU, "50", null},
	{"baseAmt", "baseAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"manContrOvrdFlg", "manContrOvrdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"manContrOvrdRsnCd", "manContrOvrdRsnCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"manContrOvrdRsnNm", "manContrOvrdRsnNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"manContrOvrdCmntTxt", "manContrOvrdCmntTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxExstFlg_PR", "xxExstFlg_PR", "PR", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_SVC_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcDtlPk
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"DS_IMPT_SVC_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcLineNum
        {"DS_IMPT_SVC_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcMdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"PRC_SVC_CONTR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcContrTpCd
        {"PRC_SVC_PLN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcPlnTpCd
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_PB
        {"BILL_WITH_EQUIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billWithEquipFlg
        {"FROM_PER_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromPerMthNum
        {"TO_PER_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toPerMthNum
        {"SHPG_INTVL_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgIntvlMthNum
        {"FIX_TERM_IN_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fixTermInMthAot
        {"MAX_UPLFT_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxUplftPct
        {"CPO_SVC_LINE_ACT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcLineActCd
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd
        {"XX_SEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_CI
        {"BASE_BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgCycleCd
        {"USG_BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usgBllgCycleCd
        {"BILL_BY_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billByTpDescTxt_L
        {"BILL_BY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billByTpCd_L
        {"BILL_BY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billByTpCd
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_S
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"XX_BILL_TO_ACCT_NM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBillToAcctNmSrchTxt
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"ADD_ASRY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addAsryFlg
        {"CPO_SVC_AGMT_VER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcAgmtVerNum
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd
        {"BILL_TO_CUST_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctNm
        {"BILL_TO_CUST_LOC_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocAddr
        {"APPLY_EQUIP_BILL_TO_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//applyEquipBillToFlg
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_BH
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_LK
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_LK
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_LK
        {"CPO_SVC_AGMT_VER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcAgmtVerNum_LK
        {"BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseAmt
        {"MAN_CONTR_OVRD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manContrOvrdFlg
        {"MAN_CONTR_OVRD_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manContrOvrdRsnCd
        {"MAN_CONTR_OVRD_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manContrOvrdRsnNm
        {"MAN_CONTR_OVRD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manContrOvrdCmntTxt
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_PR
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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
