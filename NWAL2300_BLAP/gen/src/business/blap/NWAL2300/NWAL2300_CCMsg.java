//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180411185612000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2300_CCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2300 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2300_CCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_TP_CD_C1*/
	public final EZDCStringItem              xxTpCd_C1;

    /** XX_CHK_BOX_C1*/
	public final EZDCStringItem              xxChkBox_C1;

    /** XX_DPLY_TRX_NUM_TXT_C1*/
	public final EZDCStringItem              xxDplyTrxNumTxt_C1;

    /** DPLY_LINE_NUM_C1*/
	public final EZDCStringItem              dplyLineNum_C1;

    /** DS_CPO_LINE_NUM_C1*/
	public final EZDCBigDecimalItem              dsCpoLineNum_C1;

    /** DS_CPO_LINE_SUB_NUM_C1*/
	public final EZDCBigDecimalItem              dsCpoLineSubNum_C1;

    /** MDSE_CD_C1*/
	public final EZDCStringItem              mdseCd_C1;

    /** MDSE_DESC_SHORT_TXT_C1*/
	public final EZDCStringItem              mdseDescShortTxt_C1;

    /** XX_DPLY_STS_NM_C1*/
	public final EZDCStringItem              xxDplyStsNm_C1;

    /** ORD_QTY_C1*/
	public final EZDCBigDecimalItem              ordQty_C1;

    /** DEAL_NET_UNIT_PRC_AMT_C1*/
	public final EZDCBigDecimalItem              dealNetUnitPrcAmt_C1;

    /** SP_TOT_DEAL_NET_AMT_C1*/
	public final EZDCBigDecimalItem              spTotDealNetAmt_C1;

    /** INV_NUM_C1*/
	public final EZDCStringItem              invNum_C1;

    /** INV_BOL_LINE_NUM_C1*/
	public final EZDCStringItem              invBolLineNum_C1;

    /** INV_LINE_NUM_C1*/
	public final EZDCStringItem              invLineNum_C1;

    /** INV_LINE_SUB_NUM_C1*/
	public final EZDCStringItem              invLineSubNum_C1;

    /** INV_LINE_SUB_TRX_NUM_C1*/
	public final EZDCStringItem              invLineSubTrxNum_C1;

    /** INV_AMT_C1*/
	public final EZDCBigDecimalItem              invAmt_C1;

    /** DS_ORD_POSN_NUM_C1*/
	public final EZDCStringItem              dsOrdPosnNum_C1;

    /** CPO_DTL_LINE_NUM_C1*/
	public final EZDCStringItem              cpoDtlLineNum_C1;

    /** CPO_DTL_LINE_SUB_NUM_C1*/
	public final EZDCStringItem              cpoDtlLineSubNum_C1;

    /** CPO_DTL_LINE_NUM_T*/
	public final EZDCStringItem              cpoDtlLineNum_T;

    /** DS_ORD_LINE_DRCTN_CD_C1*/
	public final EZDCStringItem              dsOrdLineDrctnCd_C1;

    /** OPEN_FLG_C1*/
	public final EZDCStringItem              openFlg_C1;

    /** EDI_REC_TP_CD_C1*/
	public final EZDCStringItem              ediRecTpCd_C1;

    /** SHPG_PLN_NUM_C1*/
	public final EZDCStringItem              shpgPlnNum_C1;

    /** DS_CPO_CONFIG_PK_C1*/
	public final EZDCBigDecimalItem              dsCpoConfigPk_C1;

    /** _EZUpdateDateTime_C1*/
	public final EZDCStringItem              ezUpTime_C1;

    /** _EZUpTimeZone_C1*/
	public final EZDCStringItem              ezUpTimeZone_C1;

    /** PMT_TERM_CASH_DISC_CD_C1*/
	public final EZDCStringItem              pmtTermCashDiscCd_C1;

    /** RTL_WH_CD_C1*/
	public final EZDCStringItem              rtlWhCd_C1;


	/**
	 * NWAL2300_CCMsg is constructor.
	 * The initialization when the instance of NWAL2300_CCMsg is generated.
	 */
	public NWAL2300_CCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2300_CCMsg is constructor.
	 * The initialization when the instance of NWAL2300_CCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2300_CCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxTpCd_C1 = (EZDCStringItem)newItem("xxTpCd_C1");
		xxChkBox_C1 = (EZDCStringItem)newItem("xxChkBox_C1");
		xxDplyTrxNumTxt_C1 = (EZDCStringItem)newItem("xxDplyTrxNumTxt_C1");
		dplyLineNum_C1 = (EZDCStringItem)newItem("dplyLineNum_C1");
		dsCpoLineNum_C1 = (EZDCBigDecimalItem)newItem("dsCpoLineNum_C1");
		dsCpoLineSubNum_C1 = (EZDCBigDecimalItem)newItem("dsCpoLineSubNum_C1");
		mdseCd_C1 = (EZDCStringItem)newItem("mdseCd_C1");
		mdseDescShortTxt_C1 = (EZDCStringItem)newItem("mdseDescShortTxt_C1");
		xxDplyStsNm_C1 = (EZDCStringItem)newItem("xxDplyStsNm_C1");
		ordQty_C1 = (EZDCBigDecimalItem)newItem("ordQty_C1");
		dealNetUnitPrcAmt_C1 = (EZDCBigDecimalItem)newItem("dealNetUnitPrcAmt_C1");
		spTotDealNetAmt_C1 = (EZDCBigDecimalItem)newItem("spTotDealNetAmt_C1");
		invNum_C1 = (EZDCStringItem)newItem("invNum_C1");
		invBolLineNum_C1 = (EZDCStringItem)newItem("invBolLineNum_C1");
		invLineNum_C1 = (EZDCStringItem)newItem("invLineNum_C1");
		invLineSubNum_C1 = (EZDCStringItem)newItem("invLineSubNum_C1");
		invLineSubTrxNum_C1 = (EZDCStringItem)newItem("invLineSubTrxNum_C1");
		invAmt_C1 = (EZDCBigDecimalItem)newItem("invAmt_C1");
		dsOrdPosnNum_C1 = (EZDCStringItem)newItem("dsOrdPosnNum_C1");
		cpoDtlLineNum_C1 = (EZDCStringItem)newItem("cpoDtlLineNum_C1");
		cpoDtlLineSubNum_C1 = (EZDCStringItem)newItem("cpoDtlLineSubNum_C1");
		cpoDtlLineNum_T = (EZDCStringItem)newItem("cpoDtlLineNum_T");
		dsOrdLineDrctnCd_C1 = (EZDCStringItem)newItem("dsOrdLineDrctnCd_C1");
		openFlg_C1 = (EZDCStringItem)newItem("openFlg_C1");
		ediRecTpCd_C1 = (EZDCStringItem)newItem("ediRecTpCd_C1");
		shpgPlnNum_C1 = (EZDCStringItem)newItem("shpgPlnNum_C1");
		dsCpoConfigPk_C1 = (EZDCBigDecimalItem)newItem("dsCpoConfigPk_C1");
		ezUpTime_C1 = (EZDCStringItem)newItem("ezUpTime_C1");
		ezUpTimeZone_C1 = (EZDCStringItem)newItem("ezUpTimeZone_C1");
		pmtTermCashDiscCd_C1 = (EZDCStringItem)newItem("pmtTermCashDiscCd_C1");
		rtlWhCd_C1 = (EZDCStringItem)newItem("rtlWhCd_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2300_CCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2300_CCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxTpCd_C1", "xxTpCd_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_C1", "xxChkBox_C1", "C1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxDplyTrxNumTxt_C1", "xxDplyTrxNumTxt_C1", "C1", null, TYPE_HANKAKUEISU, "15", null},
	{"dplyLineNum_C1", "dplyLineNum_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsCpoLineNum_C1", "dsCpoLineNum_C1", "C1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"dsCpoLineSubNum_C1", "dsCpoLineSubNum_C1", "C1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"mdseCd_C1", "mdseCd_C1", "C1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_C1", "mdseDescShortTxt_C1", "C1", null, TYPE_HANKAKUEISU, "250", null},
	{"xxDplyStsNm_C1", "xxDplyStsNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"ordQty_C1", "ordQty_C1", "C1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dealNetUnitPrcAmt_C1", "dealNetUnitPrcAmt_C1", "C1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"spTotDealNetAmt_C1", "spTotDealNetAmt_C1", "C1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invNum_C1", "invNum_C1", "C1", null, TYPE_HANKAKUEISU, "13", null},
	{"invBolLineNum_C1", "invBolLineNum_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"invLineNum_C1", "invLineNum_C1", "C1", null, TYPE_HANKAKUEISU, "5", null},
	{"invLineSubNum_C1", "invLineSubNum_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"invLineSubTrxNum_C1", "invLineSubTrxNum_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"invAmt_C1", "invAmt_C1", "C1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdPosnNum_C1", "dsOrdPosnNum_C1", "C1", null, TYPE_HANKAKUEISU, "6", null},
	{"cpoDtlLineNum_C1", "cpoDtlLineNum_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_C1", "cpoDtlLineSubNum_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineNum_T", "cpoDtlLineNum_T", "T", null, TYPE_HANKAKUEISU, "3", null},
	{"dsOrdLineDrctnCd_C1", "dsOrdLineDrctnCd_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"openFlg_C1", "openFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"ediRecTpCd_C1", "ediRecTpCd_C1", "C1", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgPlnNum_C1", "shpgPlnNum_C1", "C1", null, TYPE_HANKAKUEISU, "8", null},
	{"dsCpoConfigPk_C1", "dsCpoConfigPk_C1", "C1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_C1", "ezUpTime_C1", "C1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_C1", "ezUpTimeZone_C1", "C1", null, TYPE_HANKAKUEISU, "5", null},
	{"pmtTermCashDiscCd_C1", "pmtTermCashDiscCd_C1", "C1", null, TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCd_C1", "rtlWhCd_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_C1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C1
        {"XX_DPLY_TRX_NUM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTrxNumTxt_C1
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_C1
        {"DS_CPO_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineNum_C1
        {"DS_CPO_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineSubNum_C1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_C1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_C1
        {"XX_DPLY_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyStsNm_C1
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_C1
        {"DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealNetUnitPrcAmt_C1
        {"SP_TOT_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//spTotDealNetAmt_C1
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_C1
        {"INV_BOL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invBolLineNum_C1
        {"INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineNum_C1
        {"INV_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubNum_C1
        {"INV_LINE_SUB_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubTrxNum_C1
        {"INV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invAmt_C1
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_C1
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_C1
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_C1
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_T
        {"DS_ORD_LINE_DRCTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineDrctnCd_C1
        {"OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openFlg_C1
        {"EDI_REC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediRecTpCd_C1
        {"SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgPlnNum_C1
        {"DS_CPO_CONFIG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoConfigPk_C1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_C1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_C1
        {"PMT_TERM_CASH_DISC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscCd_C1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_C1
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

