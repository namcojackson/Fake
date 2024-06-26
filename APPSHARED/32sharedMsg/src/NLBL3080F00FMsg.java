//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160418013631000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3080F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLBL3080F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3080F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SMRY_LINE_FLG*/
	public final EZDFStringItem              xxSmryLineFlg;

    /** XX_CHK_BOX_1*/
	public final EZDFStringItem              xxChkBox_1;

    /** XX_CHK_BOX_2*/
	public final EZDFStringItem              xxChkBox_2;

    /** CPO_ORD_NUM*/
	public final EZDFStringItem              cpoOrdNum;

    /** DPLY_LINE_NUM*/
	public final EZDFStringItem              dplyLineNum;

    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDFStringItem              mdseDescShortTxt;

    /** COA_MDSE_TP_CD*/
	public final EZDFStringItem              coaMdseTpCd;

    /** XX_SUPD_FLG*/
	public final EZDFStringItem              xxSupdFlg;

    /** SET_MDSE_CD*/
	public final EZDFStringItem              setMdseCd;

    /** BACK_ORD_IMPCT_TP_DESC_TXT*/
	public final EZDFStringItem              backOrdImpctTpDescTxt;

    /** STK_STS_CD*/
	public final EZDFStringItem              stkStsCd;

    /** ORD_QTY_BO*/
	public final EZDFBigDecimalItem              ordQty_BO;

    /** ORD_QTY_AL*/
	public final EZDFBigDecimalItem              ordQty_AL;

    /** INVTY_AVAL_QTY*/
	public final EZDFBigDecimalItem              invtyAvalQty;

    /** SER_NUM*/
	public final EZDFStringItem              serNum;

    /** T_MDL_NM*/
	public final EZDFStringItem              t_MdlNm;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDFBigDecimalItem              svcConfigMstrPk;

    /** PICK_SVC_CONFIG_MSTR_PK*/
	public final EZDFBigDecimalItem              pickSvcConfigMstrPk;

    /** RDD_DT*/
	public final EZDFDateItem              rddDt;

    /** RTL_WH_NM*/
	public final EZDFStringItem              rtlWhNm;

    /** RTL_SWH_CD*/
	public final EZDFStringItem              rtlSwhCd;

    /** DS_ORD_LINE_CATG_DESC_TXT*/
	public final EZDFStringItem              dsOrdLineCatgDescTxt;

    /** ORD_LINE_STS_NM*/
	public final EZDFStringItem              ordLineStsNm;


	/**
	 * NLBL3080F00FMsg is constructor.
	 * The initialization when the instance of NLBL3080F00FMsg is generated.
	 */
	public NLBL3080F00FMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3080F00FMsg is constructor.
	 * The initialization when the instance of NLBL3080F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3080F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSmryLineFlg = (EZDFStringItem)newItem("xxSmryLineFlg");
		xxChkBox_1 = (EZDFStringItem)newItem("xxChkBox_1");
		xxChkBox_2 = (EZDFStringItem)newItem("xxChkBox_2");
		cpoOrdNum = (EZDFStringItem)newItem("cpoOrdNum");
		dplyLineNum = (EZDFStringItem)newItem("dplyLineNum");
		mdseCd = (EZDFStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDFStringItem)newItem("mdseDescShortTxt");
		coaMdseTpCd = (EZDFStringItem)newItem("coaMdseTpCd");
		xxSupdFlg = (EZDFStringItem)newItem("xxSupdFlg");
		setMdseCd = (EZDFStringItem)newItem("setMdseCd");
		backOrdImpctTpDescTxt = (EZDFStringItem)newItem("backOrdImpctTpDescTxt");
		stkStsCd = (EZDFStringItem)newItem("stkStsCd");
		ordQty_BO = (EZDFBigDecimalItem)newItem("ordQty_BO");
		ordQty_AL = (EZDFBigDecimalItem)newItem("ordQty_AL");
		invtyAvalQty = (EZDFBigDecimalItem)newItem("invtyAvalQty");
		serNum = (EZDFStringItem)newItem("serNum");
		t_MdlNm = (EZDFStringItem)newItem("t_MdlNm");
		svcConfigMstrPk = (EZDFBigDecimalItem)newItem("svcConfigMstrPk");
		pickSvcConfigMstrPk = (EZDFBigDecimalItem)newItem("pickSvcConfigMstrPk");
		rddDt = (EZDFDateItem)newItem("rddDt");
		rtlWhNm = (EZDFStringItem)newItem("rtlWhNm");
		rtlSwhCd = (EZDFStringItem)newItem("rtlSwhCd");
		dsOrdLineCatgDescTxt = (EZDFStringItem)newItem("dsOrdLineCatgDescTxt");
		ordLineStsNm = (EZDFStringItem)newItem("ordLineStsNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3080F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3080F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSmryLineFlg", "xxSmryLineFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_1", "xxChkBox_1", "1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_2", "xxChkBox_2", "2", null, TYPE_HANKAKUEIJI, "1", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dplyLineNum", "dplyLineNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"coaMdseTpCd", "coaMdseTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxSupdFlg", "xxSupdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"setMdseCd", "setMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"backOrdImpctTpDescTxt", "backOrdImpctTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"stkStsCd", "stkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ordQty_BO", "ordQty_BO", "BO", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordQty_AL", "ordQty_AL", "AL", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invtyAvalQty", "invtyAvalQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"t_MdlNm", "t_MdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"pickSvcConfigMstrPk", "pickSvcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rddDt", "rddDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsOrdLineCatgDescTxt", "dsOrdLineCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"ordLineStsNm", "ordLineStsNm", null, null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_2
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd
        {"XX_SUPD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSupdFlg
        {"SET_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setMdseCd
        {"BACK_ORD_IMPCT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backOrdImpctTpDescTxt
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_BO
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_AL
        {"INVTY_AVAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAvalQty
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"PICK_SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickSvcConfigMstrPk
        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt
        {"ORD_LINE_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineStsNm
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

