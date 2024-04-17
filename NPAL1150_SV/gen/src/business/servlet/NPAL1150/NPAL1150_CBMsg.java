//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20181120162610000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1150_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1150 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1150_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_DD*/
	public final EZDBStringItem              ezUpTime_DD;

    /** _EZUpTimeZone_DD*/
	public final EZDBStringItem              ezUpTimeZone_DD;

    /** DISP_PO_DTL_LINE_NUM_D1*/
	public final EZDBStringItem              dispPoDtlLineNum_D1;

    /** DISP_PO_DTL_LINE_NUM_D2*/
	public final EZDBStringItem              dispPoDtlLineNum_D2;

    /** DISP_PO_DTL_LINE_NUM_BK*/
	public final EZDBStringItem              dispPoDtlLineNum_BK;

    /** EDI_PO_ORD_DTL_LINE_NUM_D1*/
	public final EZDBStringItem              ediPoOrdDtlLineNum_D1;

    /** EDI_PO_ORD_DTL_LINE_NUM_D2*/
	public final EZDBStringItem              ediPoOrdDtlLineNum_D2;

    /** ASN_SO_NUM_D1*/
	public final EZDBStringItem              asnSoNum_D1;

    /** ASN_LINE_NUM_D1*/
	public final EZDBStringItem              asnLineNum_D1;

    /** ASN_SO_SLP_NUM_D1*/
	public final EZDBStringItem              asnSoSlpNum_D1;

    /** MDSE_CD_D1*/
	public final EZDBStringItem              mdseCd_D1;

    /** MDSE_CD_UPD_FLG_D1*/
	public final EZDBStringItem              mdseCdUpdFlg_D1;

    /** ASN_QTY_D1*/
	public final EZDBBigDecimalItem              asnQty_D1;

    /** ASN_CARR_CD_D1*/
	public final EZDBStringItem              asnCarrCd_D1;

    /** ASN_PRO_NUM_D1*/
	public final EZDBStringItem              asnProNum_D1;

    /** ASN_BOL_NUM_D1*/
	public final EZDBStringItem              asnBolNum_D1;

    /** ASN_PLN_DELY_DT_D1*/
	public final EZDBDateItem              asnPlnDelyDt_D1;

    /** XX_NUM_D1*/
	public final EZDBBigDecimalItem              xxNum_D1;

    /** BAT_ERR_MSG_TXT_DV*/
	public final EZDBStringItem              batErrMsgTxt_DV;

    /** BAT_ERR_MSG_TXT_DC*/
	public final EZDBStringItemArray              batErrMsgTxt_DC;

    /** BAT_ERR_MSG_TXT_DD*/
	public final EZDBStringItemArray              batErrMsgTxt_DD;

    /** EDI_PO_ORD_DTL_LINE_NUM_HD*/
	public final EZDBStringItem              ediPoOrdDtlLineNum_HD;

    /** DISP_PO_DTL_LINE_NUM_HD*/
	public final EZDBStringItem              dispPoDtlLineNum_HD;


	/**
	 * NPAL1150_CBMsg is constructor.
	 * The initialization when the instance of NPAL1150_CBMsg is generated.
	 */
	public NPAL1150_CBMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1150_CBMsg is constructor.
	 * The initialization when the instance of NPAL1150_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1150_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_DD = (EZDBStringItem)newItem("ezUpTime_DD");
		ezUpTimeZone_DD = (EZDBStringItem)newItem("ezUpTimeZone_DD");
		dispPoDtlLineNum_D1 = (EZDBStringItem)newItem("dispPoDtlLineNum_D1");
		dispPoDtlLineNum_D2 = (EZDBStringItem)newItem("dispPoDtlLineNum_D2");
		dispPoDtlLineNum_BK = (EZDBStringItem)newItem("dispPoDtlLineNum_BK");
		ediPoOrdDtlLineNum_D1 = (EZDBStringItem)newItem("ediPoOrdDtlLineNum_D1");
		ediPoOrdDtlLineNum_D2 = (EZDBStringItem)newItem("ediPoOrdDtlLineNum_D2");
		asnSoNum_D1 = (EZDBStringItem)newItem("asnSoNum_D1");
		asnLineNum_D1 = (EZDBStringItem)newItem("asnLineNum_D1");
		asnSoSlpNum_D1 = (EZDBStringItem)newItem("asnSoSlpNum_D1");
		mdseCd_D1 = (EZDBStringItem)newItem("mdseCd_D1");
		mdseCdUpdFlg_D1 = (EZDBStringItem)newItem("mdseCdUpdFlg_D1");
		asnQty_D1 = (EZDBBigDecimalItem)newItem("asnQty_D1");
		asnCarrCd_D1 = (EZDBStringItem)newItem("asnCarrCd_D1");
		asnProNum_D1 = (EZDBStringItem)newItem("asnProNum_D1");
		asnBolNum_D1 = (EZDBStringItem)newItem("asnBolNum_D1");
		asnPlnDelyDt_D1 = (EZDBDateItem)newItem("asnPlnDelyDt_D1");
		xxNum_D1 = (EZDBBigDecimalItem)newItem("xxNum_D1");
		batErrMsgTxt_DV = (EZDBStringItem)newItem("batErrMsgTxt_DV");
		batErrMsgTxt_DC = (EZDBStringItemArray)newItemArray("batErrMsgTxt_DC");
		batErrMsgTxt_DD = (EZDBStringItemArray)newItemArray("batErrMsgTxt_DD");
		ediPoOrdDtlLineNum_HD = (EZDBStringItem)newItem("ediPoOrdDtlLineNum_HD");
		dispPoDtlLineNum_HD = (EZDBStringItem)newItem("dispPoDtlLineNum_HD");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1150_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1150_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_DD", "ezUpTime_DD", "DD", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_DD", "ezUpTimeZone_DD", "DD", null, TYPE_HANKAKUEISU, "5", null},
	{"dispPoDtlLineNum_D1", "dispPoDtlLineNum_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	{"dispPoDtlLineNum_D2", "dispPoDtlLineNum_D2", "D2", null, TYPE_HANKAKUEISU, "8", null},
	{"dispPoDtlLineNum_BK", "dispPoDtlLineNum_BK", "BK", null, TYPE_HANKAKUEISU, "8", null},
	{"ediPoOrdDtlLineNum_D1", "ediPoOrdDtlLineNum_D1", "D1", null, TYPE_HANKAKUEISU, "6", null},
	{"ediPoOrdDtlLineNum_D2", "ediPoOrdDtlLineNum_D2", "D2", null, TYPE_HANKAKUEISU, "6", null},
	{"asnSoNum_D1", "asnSoNum_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"asnLineNum_D1", "asnLineNum_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"asnSoSlpNum_D1", "asnSoSlpNum_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_D1", "mdseCd_D1", "D1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseCdUpdFlg_D1", "mdseCdUpdFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"asnQty_D1", "asnQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"asnCarrCd_D1", "asnCarrCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"asnProNum_D1", "asnProNum_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"asnBolNum_D1", "asnBolNum_D1", "D1", null, TYPE_HANKAKUEISU, "40", null},
	{"asnPlnDelyDt_D1", "asnPlnDelyDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxNum_D1", "xxNum_D1", "D1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"batErrMsgTxt_DV", "batErrMsgTxt_DV", "DV", null, TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_DC", "batErrMsgTxt_DC", "DC", "200", TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_DD", "batErrMsgTxt_DD", "DD", "200", TYPE_HANKAKUEISU, "400", null},
	{"ediPoOrdDtlLineNum_HD", "ediPoOrdDtlLineNum_HD", "HD", null, TYPE_HANKAKUEISU, "6", null},
	{"dispPoDtlLineNum_HD", "dispPoDtlLineNum_HD", "HD", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_DD
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_DD
        {"DISP_PO_DTL_LINE_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_D1
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_D2
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_BK
        {"EDI_PO_ORD_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdDtlLineNum_D1
        {"EDI_PO_ORD_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdDtlLineNum_D2
        {"ASN_SO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnSoNum_D1
        {"ASN_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnLineNum_D1
        {"ASN_SO_SLP_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnSoSlpNum_D1
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_D1
        {"MDSE_CD_UPD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCdUpdFlg_D1
        {"ASN_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//asnQty_D1
        {"ASN_CARR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnCarrCd_D1
        {"ASN_PRO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnProNum_D1
        {"ASN_BOL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnBolNum_D1
        {"ASN_PLN_DELY_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//asnPlnDelyDt_D1
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_D1
        {"BAT_ERR_MSG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_DV
        {"BAT_ERR_MSG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_DC
        {"BAT_ERR_MSG_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_DD
        {"EDI_PO_ORD_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdDtlLineNum_HD
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_HD
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

