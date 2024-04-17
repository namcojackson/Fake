//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20230221114036000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2030F01FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL2030F01 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2030F01FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX*/
	public final EZDFStringItem              xxChkBox;

    /** RWS_STS_DESC_TXT*/
	public final EZDFStringItem              rwsStsDescTxt;

    /** RWS_NUM*/
	public final EZDFStringItem              rwsNum;

    /** XX_CRAT_DT*/
	public final EZDFDateItem              xxCratDt;

    /** RWS_DTL_LINE_NUM*/
	public final EZDFStringItem              rwsDtlLineNum;

    /** RTL_WH_NM*/
	public final EZDFStringItem              rtlWhNm;

    /** RWS_REF_NUM*/
	public final EZDFStringItem              rwsRefNum;

    /** THIRD_PTY_DSP_TP_DESC_TXT*/
	public final EZDFStringItem              thirdPtyDspTpDescTxt;

    /** IMPT_INV_BOL_NUM*/
	public final EZDFStringItem              imptInvBolNum;

    /** SCE_ORD_TP_NM*/
	public final EZDFStringItem              sceOrdTpNm;

    /** TRX_ORD_NUM*/
	public final EZDFStringItem              trxOrdNum;

    /** DPLY_LINE_NUM*/
	public final EZDFStringItem              dplyLineNum;

    /** FROM_LOC_CD*/
	public final EZDFStringItem              fromLocCd;

    /** DS_ACCT_NM*/
	public final EZDFStringItem              dsAcctNm;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDFBigDecimalItem              svcConfigMstrPk;

    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** FLIP_MDSE_CD*/
	public final EZDFStringItem              flipMdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDFStringItem              mdseDescShortTxt;

    /** RWS_QTY*/
	public final EZDFBigDecimalItem              rwsQty;

    /** XX_QTY_10_NUM*/
	public final EZDFBigDecimalItem              xxQty10Num;

    /** SER_NUM*/
	public final EZDFStringItem              serNum;

    /** RTL_SWH_CD*/
	public final EZDFStringItem              rtlSwhCd;

    /** XX_RTL_WH_SRCH_TXT*/
	public final EZDFStringItem              xxRtlWhSrchTxt;


	/**
	 * NLAL2030F01FMsg is constructor.
	 * The initialization when the instance of NLAL2030F01FMsg is generated.
	 */
	public NLAL2030F01FMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2030F01FMsg is constructor.
	 * The initialization when the instance of NLAL2030F01FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2030F01FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox = (EZDFStringItem)newItem("xxChkBox");
		rwsStsDescTxt = (EZDFStringItem)newItem("rwsStsDescTxt");
		rwsNum = (EZDFStringItem)newItem("rwsNum");
		xxCratDt = (EZDFDateItem)newItem("xxCratDt");
		rwsDtlLineNum = (EZDFStringItem)newItem("rwsDtlLineNum");
		rtlWhNm = (EZDFStringItem)newItem("rtlWhNm");
		rwsRefNum = (EZDFStringItem)newItem("rwsRefNum");
		thirdPtyDspTpDescTxt = (EZDFStringItem)newItem("thirdPtyDspTpDescTxt");
		imptInvBolNum = (EZDFStringItem)newItem("imptInvBolNum");
		sceOrdTpNm = (EZDFStringItem)newItem("sceOrdTpNm");
		trxOrdNum = (EZDFStringItem)newItem("trxOrdNum");
		dplyLineNum = (EZDFStringItem)newItem("dplyLineNum");
		fromLocCd = (EZDFStringItem)newItem("fromLocCd");
		dsAcctNm = (EZDFStringItem)newItem("dsAcctNm");
		svcConfigMstrPk = (EZDFBigDecimalItem)newItem("svcConfigMstrPk");
		mdseCd = (EZDFStringItem)newItem("mdseCd");
		flipMdseCd = (EZDFStringItem)newItem("flipMdseCd");
		mdseDescShortTxt = (EZDFStringItem)newItem("mdseDescShortTxt");
		rwsQty = (EZDFBigDecimalItem)newItem("rwsQty");
		xxQty10Num = (EZDFBigDecimalItem)newItem("xxQty10Num");
		serNum = (EZDFStringItem)newItem("serNum");
		rtlSwhCd = (EZDFStringItem)newItem("rtlSwhCd");
		xxRtlWhSrchTxt = (EZDFStringItem)newItem("xxRtlWhSrchTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2030F01FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2030F01FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"rwsStsDescTxt", "rwsStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"rwsNum", "rwsNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxCratDt", "xxCratDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rwsDtlLineNum", "rwsDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rwsRefNum", "rwsRefNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"thirdPtyDspTpDescTxt", "thirdPtyDspTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"imptInvBolNum", "imptInvBolNum", null, null, TYPE_HANKAKUEISU, "25", null},
	{"sceOrdTpNm", "sceOrdTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"trxOrdNum", "trxOrdNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"dplyLineNum", "dplyLineNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"fromLocCd", "fromLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"flipMdseCd", "flipMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"rwsQty", "rwsQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxQty10Num", "xxQty10Num", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxRtlWhSrchTxt", "xxRtlWhSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"RWS_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStsDescTxt
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt
        {"RWS_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsDtlLineNum
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum
        {"THIRD_PTY_DSP_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdPtyDspTpDescTxt
        {"IMPT_INV_BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvBolNum
        {"SCE_ORD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpNm
        {"TRX_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxOrdNum
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum
        {"FROM_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromLocCd
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"FLIP_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flipMdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"RWS_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsQty
        {"XX_QTY_10_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQty10Num
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"XX_RTL_WH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlWhSrchTxt
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

