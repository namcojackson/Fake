//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170530083717000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2030F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLAL2030F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2030F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDFStringItem              xxChkBox_A1;

    /** XX_CHK_BOX_A2*/
	public final EZDFStringItem              xxChkBox_A2;

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

    /** RTL_WH_NM*/
	public final EZDFStringItem              rtlWhNm;

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

    /** RWS_OPEN_FLG*/
	public final EZDFStringItem              rwsOpenFlg;

    /** COA_MDSE_TP_CD*/
	public final EZDFStringItem              coaMdseTpCd;

    /** SPLY_ITEM_NUM*/
	public final EZDFStringItem              splyItemNum;

    /** RTL_SWH_CD*/
	public final EZDFStringItem              rtlSwhCd;


	/**
	 * NLAL2030F00FMsg is constructor.
	 * The initialization when the instance of NLAL2030F00FMsg is generated.
	 */
	public NLAL2030F00FMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2030F00FMsg is constructor.
	 * The initialization when the instance of NLAL2030F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2030F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDFStringItem)newItem("xxChkBox_A1");
		xxChkBox_A2 = (EZDFStringItem)newItem("xxChkBox_A2");
		sceOrdTpNm = (EZDFStringItem)newItem("sceOrdTpNm");
		trxOrdNum = (EZDFStringItem)newItem("trxOrdNum");
		dplyLineNum = (EZDFStringItem)newItem("dplyLineNum");
		fromLocCd = (EZDFStringItem)newItem("fromLocCd");
		dsAcctNm = (EZDFStringItem)newItem("dsAcctNm");
		rtlWhNm = (EZDFStringItem)newItem("rtlWhNm");
		svcConfigMstrPk = (EZDFBigDecimalItem)newItem("svcConfigMstrPk");
		mdseCd = (EZDFStringItem)newItem("mdseCd");
		flipMdseCd = (EZDFStringItem)newItem("flipMdseCd");
		mdseDescShortTxt = (EZDFStringItem)newItem("mdseDescShortTxt");
		rwsQty = (EZDFBigDecimalItem)newItem("rwsQty");
		xxQty10Num = (EZDFBigDecimalItem)newItem("xxQty10Num");
		serNum = (EZDFStringItem)newItem("serNum");
		rwsOpenFlg = (EZDFStringItem)newItem("rwsOpenFlg");
		coaMdseTpCd = (EZDFStringItem)newItem("coaMdseTpCd");
		splyItemNum = (EZDFStringItem)newItem("splyItemNum");
		rtlSwhCd = (EZDFStringItem)newItem("rtlSwhCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2030F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2030F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"sceOrdTpNm", "sceOrdTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"trxOrdNum", "trxOrdNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"dplyLineNum", "dplyLineNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"fromLocCd", "fromLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"flipMdseCd", "flipMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"rwsQty", "rwsQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxQty10Num", "xxQty10Num", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rwsOpenFlg", "rwsOpenFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"coaMdseTpCd", "coaMdseTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyItemNum", "splyItemNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
        {"SCE_ORD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpNm
        {"TRX_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxOrdNum
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum
        {"FROM_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromLocCd
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"FLIP_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flipMdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"RWS_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsQty
        {"XX_QTY_10_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQty10Num
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"RWS_OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsOpenFlg
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd
        {"SPLY_ITEM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyItemNum
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
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

