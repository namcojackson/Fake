//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161025022810000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0300_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0300 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0300_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** INVTY_ORD_NUM_T*/
	public final EZDCStringItem              invtyOrdNum_T;

    /** INVTY_ORD_LINE_NUM_T*/
	public final EZDCStringItem              invtyOrdLineNum_T;

    /** INVTY_ORD_NUM_A*/
	public final EZDCStringItem              invtyOrdNum_A;

    /** INVTY_ORD_LINE_NUM_A*/
	public final EZDCStringItem              invtyOrdLineNum_A;

    /** SVC_CONFIG_MSTR_PK_A*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_A;

    /** MDSE_CD_A*/
	public final EZDCStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDCStringItem              mdseDescShortTxt_A;

    /** STK_STS_CD_A*/
	public final EZDCStringItem              stkStsCd_A;

    /** RTL_SWH_CD_A*/
	public final EZDCStringItem              rtlSwhCd_A;

    /** ORD_QTY_A*/
	public final EZDCBigDecimalItem              ordQty_A;

    /** INVTY_AVAL_QTY_A*/
	public final EZDCBigDecimalItem              invtyAvalQty_A;

    /** SER_NUM_A*/
	public final EZDCStringItem              serNum_A;

    /** RMV_CONFIG_FLG_A*/
	public final EZDCStringItem              rmvConfigFlg_A;

    /** INVTY_LOC_CD_A*/
	public final EZDCStringItem              invtyLocCd_A;

    /** INVTY_ORD_LINE_COST_AMT_A*/
	public final EZDCBigDecimalItem              invtyOrdLineCostAmt_A;

    /** UNIT_PRC_AMT_A*/
	public final EZDCBigDecimalItem              unitPrcAmt_A;

    /** RTL_WH_CD_AH*/
	public final EZDCStringItem              rtlWhCd_AH;

    /** RTL_WH_NM_AH*/
	public final EZDCStringItem              rtlWhNm_AH;

    /** SVC_CONFIG_MSTR_PK_AH*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_AH;

    /** MDL_ID_AH*/
	public final EZDCBigDecimalItem              mdlId_AH;

    /** MDL_DESC_TXT_AH*/
	public final EZDCStringItem              mdlDescTxt_AH;

    /** FIRST_INVTY_ORD_CMNT_TXT_AH*/
	public final EZDCStringItem              firstInvtyOrdCmntTxt_AH;

    /** SCD_INVTY_ORD_CMNT_TXT_AH*/
	public final EZDCStringItem              scdInvtyOrdCmntTxt_AH;

    /** THIRD_INVTY_ORD_CMNT_TXT_AH*/
	public final EZDCStringItem              thirdInvtyOrdCmntTxt_AH;

    /** INVTY_ORD_STS_DESC_TXT_AH*/
	public final EZDCStringItem              invtyOrdStsDescTxt_AH;

    /** SO_NUM_AH*/
	public final EZDCStringItem              soNum_AH;


	/**
	 * NLCL0300_ACMsg is constructor.
	 * The initialization when the instance of NLCL0300_ACMsg is generated.
	 */
	public NLCL0300_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0300_ACMsg is constructor.
	 * The initialization when the instance of NLCL0300_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0300_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		invtyOrdNum_T = (EZDCStringItem)newItem("invtyOrdNum_T");
		invtyOrdLineNum_T = (EZDCStringItem)newItem("invtyOrdLineNum_T");
		invtyOrdNum_A = (EZDCStringItem)newItem("invtyOrdNum_A");
		invtyOrdLineNum_A = (EZDCStringItem)newItem("invtyOrdLineNum_A");
		svcConfigMstrPk_A = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_A");
		mdseCd_A = (EZDCStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDCStringItem)newItem("mdseDescShortTxt_A");
		stkStsCd_A = (EZDCStringItem)newItem("stkStsCd_A");
		rtlSwhCd_A = (EZDCStringItem)newItem("rtlSwhCd_A");
		ordQty_A = (EZDCBigDecimalItem)newItem("ordQty_A");
		invtyAvalQty_A = (EZDCBigDecimalItem)newItem("invtyAvalQty_A");
		serNum_A = (EZDCStringItem)newItem("serNum_A");
		rmvConfigFlg_A = (EZDCStringItem)newItem("rmvConfigFlg_A");
		invtyLocCd_A = (EZDCStringItem)newItem("invtyLocCd_A");
		invtyOrdLineCostAmt_A = (EZDCBigDecimalItem)newItem("invtyOrdLineCostAmt_A");
		unitPrcAmt_A = (EZDCBigDecimalItem)newItem("unitPrcAmt_A");
		rtlWhCd_AH = (EZDCStringItem)newItem("rtlWhCd_AH");
		rtlWhNm_AH = (EZDCStringItem)newItem("rtlWhNm_AH");
		svcConfigMstrPk_AH = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_AH");
		mdlId_AH = (EZDCBigDecimalItem)newItem("mdlId_AH");
		mdlDescTxt_AH = (EZDCStringItem)newItem("mdlDescTxt_AH");
		firstInvtyOrdCmntTxt_AH = (EZDCStringItem)newItem("firstInvtyOrdCmntTxt_AH");
		scdInvtyOrdCmntTxt_AH = (EZDCStringItem)newItem("scdInvtyOrdCmntTxt_AH");
		thirdInvtyOrdCmntTxt_AH = (EZDCStringItem)newItem("thirdInvtyOrdCmntTxt_AH");
		invtyOrdStsDescTxt_AH = (EZDCStringItem)newItem("invtyOrdStsDescTxt_AH");
		soNum_AH = (EZDCStringItem)newItem("soNum_AH");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0300_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0300_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"invtyOrdNum_T", "invtyOrdNum_T", "T", null, TYPE_HANKAKUEISU, "8", null},
	{"invtyOrdLineNum_T", "invtyOrdLineNum_T", "T", null, TYPE_HANKAKUEISU, "3", null},
	{"invtyOrdNum_A", "invtyOrdNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"invtyOrdLineNum_A", "invtyOrdLineNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"svcConfigMstrPk_A", "svcConfigMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"stkStsCd_A", "stkStsCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"rtlSwhCd_A", "rtlSwhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"ordQty_A", "ordQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invtyAvalQty_A", "invtyAvalQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"rmvConfigFlg_A", "rmvConfigFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"invtyLocCd_A", "invtyLocCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyOrdLineCostAmt_A", "invtyOrdLineCostAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"unitPrcAmt_A", "unitPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rtlWhCd_AH", "rtlWhCd_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_AH", "rtlWhNm_AH", "AH", null, TYPE_HANKAKUEISU, "30", null},
	{"svcConfigMstrPk_AH", "svcConfigMstrPk_AH", "AH", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId_AH", "mdlId_AH", "AH", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlDescTxt_AH", "mdlDescTxt_AH", "AH", null, TYPE_HANKAKUEISU, "90", null},
	{"firstInvtyOrdCmntTxt_AH", "firstInvtyOrdCmntTxt_AH", "AH", null, TYPE_HANKAKUEISU, "64", null},
	{"scdInvtyOrdCmntTxt_AH", "scdInvtyOrdCmntTxt_AH", "AH", null, TYPE_HANKAKUEISU, "64", null},
	{"thirdInvtyOrdCmntTxt_AH", "thirdInvtyOrdCmntTxt_AH", "AH", null, TYPE_HANKAKUEISU, "64", null},
	{"invtyOrdStsDescTxt_AH", "invtyOrdStsDescTxt_AH", "AH", null, TYPE_HANKAKUEISU, "50", null},
	{"soNum_AH", "soNum_AH", "AH", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"INVTY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdNum_T
        {"INVTY_ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdLineNum_T
        {"INVTY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdNum_A
        {"INVTY_ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdLineNum_A
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_A
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_A
        {"INVTY_AVAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAvalQty_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"RMV_CONFIG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmvConfigFlg_A
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_A
        {"INVTY_ORD_LINE_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdLineCostAmt_A
        {"UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPrcAmt_A
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_AH
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_AH
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_AH
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_AH
        {"MDL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlDescTxt_AH
        {"FIRST_INVTY_ORD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstInvtyOrdCmntTxt_AH
        {"SCD_INVTY_ORD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdInvtyOrdCmntTxt_AH
        {"THIRD_INVTY_ORD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdInvtyOrdCmntTxt_AH
        {"INVTY_ORD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdStsDescTxt_AH
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_AH
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

