//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230227155653000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2030_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLAL2030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL2030 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2030_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM_A1*/
	public final EZDBBigDecimalItem              xxNum_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** XX_CHK_BOX_A2*/
	public final EZDBStringItem              xxChkBox_A2;

    /** SCE_ORD_TP_NM_A1*/
	public final EZDBStringItem              sceOrdTpNm_A1;

    /** SCE_ORD_TP_CD_A1*/
	public final EZDBStringItem              sceOrdTpCd_A1;

    /** TRX_ORD_NUM_A1*/
	public final EZDBStringItem              trxOrdNum_A1;

    /** TRX_LINE_NUM_A1*/
	public final EZDBStringItem              trxLineNum_A1;

    /** TRX_LINE_SUB_NUM_A1*/
	public final EZDBStringItem              trxLineSubNum_A1;

    /** DPLY_LINE_NUM_A1*/
	public final EZDBStringItem              dplyLineNum_A1;

    /** FROM_LOC_CD_A1*/
	public final EZDBStringItem              fromLocCd_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDBStringItem              dsAcctNm_A1;

    /** RTL_WH_CD_A1*/
	public final EZDBStringItem              rtlWhCd_A1;

    /** RTL_WH_NM_A1*/
	public final EZDBStringItem              rtlWhNm_A1;

    /** SVC_CONFIG_MSTR_PK_A1*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_A1;

    /** RWS_OPEN_FLG_A1*/
	public final EZDBStringItem              rwsOpenFlg_A1;

    /** MDSE_CD_A1*/
	public final EZDBStringItem              mdseCd_A1;

    /** FLIP_MDSE_CD_A1*/
	public final EZDBStringItem              flipMdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDBStringItem              mdseDescShortTxt_A1;

    /** RWS_QTY_A1*/
	public final EZDBBigDecimalItem              rwsQty_A1;

    /** XX_QTY_10_NUM_A1*/
	public final EZDBBigDecimalItem              xxQty10Num_A1;

    /** SER_NUM_A1*/
	public final EZDBStringItem              serNum_A1;

    /** COA_MDSE_TP_CD_A1*/
	public final EZDBStringItem              coaMdseTpCd_A1;

    /** ASL_MDSE_CD_A1*/
	public final EZDBStringItem              aslMdseCd_A1;

    /** RTL_SWH_CD_A1*/
	public final EZDBStringItem              rtlSwhCd_A1;

    /** RTL_SWH_NM_A1*/
	public final EZDBStringItem              rtlSwhNm_A1;

    /** RWS_FLG_A1*/
	public final EZDBStringItem              rwsFlg_A1;

    /** DS_ORD_POSN_NUM_A1*/
	public final EZDBStringItem              dsOrdPosnNum_A1;

    /** SER_NUM_TAKE_FLG_A1*/
	public final EZDBStringItem              serNumTakeFlg_A1;

    /** RWS_NUM_A1*/
	public final EZDBStringItem              rwsNum_A1;


	/**
	 * NLAL2030_ABMsg is constructor.
	 * The initialization when the instance of NLAL2030_ABMsg is generated.
	 */
	public NLAL2030_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2030_ABMsg is constructor.
	 * The initialization when the instance of NLAL2030_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2030_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum_A1 = (EZDBBigDecimalItem)newItem("xxNum_A1");
		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		xxChkBox_A2 = (EZDBStringItem)newItem("xxChkBox_A2");
		sceOrdTpNm_A1 = (EZDBStringItem)newItem("sceOrdTpNm_A1");
		sceOrdTpCd_A1 = (EZDBStringItem)newItem("sceOrdTpCd_A1");
		trxOrdNum_A1 = (EZDBStringItem)newItem("trxOrdNum_A1");
		trxLineNum_A1 = (EZDBStringItem)newItem("trxLineNum_A1");
		trxLineSubNum_A1 = (EZDBStringItem)newItem("trxLineSubNum_A1");
		dplyLineNum_A1 = (EZDBStringItem)newItem("dplyLineNum_A1");
		fromLocCd_A1 = (EZDBStringItem)newItem("fromLocCd_A1");
		dsAcctNm_A1 = (EZDBStringItem)newItem("dsAcctNm_A1");
		rtlWhCd_A1 = (EZDBStringItem)newItem("rtlWhCd_A1");
		rtlWhNm_A1 = (EZDBStringItem)newItem("rtlWhNm_A1");
		svcConfigMstrPk_A1 = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_A1");
		rwsOpenFlg_A1 = (EZDBStringItem)newItem("rwsOpenFlg_A1");
		mdseCd_A1 = (EZDBStringItem)newItem("mdseCd_A1");
		flipMdseCd_A1 = (EZDBStringItem)newItem("flipMdseCd_A1");
		mdseDescShortTxt_A1 = (EZDBStringItem)newItem("mdseDescShortTxt_A1");
		rwsQty_A1 = (EZDBBigDecimalItem)newItem("rwsQty_A1");
		xxQty10Num_A1 = (EZDBBigDecimalItem)newItem("xxQty10Num_A1");
		serNum_A1 = (EZDBStringItem)newItem("serNum_A1");
		coaMdseTpCd_A1 = (EZDBStringItem)newItem("coaMdseTpCd_A1");
		aslMdseCd_A1 = (EZDBStringItem)newItem("aslMdseCd_A1");
		rtlSwhCd_A1 = (EZDBStringItem)newItem("rtlSwhCd_A1");
		rtlSwhNm_A1 = (EZDBStringItem)newItem("rtlSwhNm_A1");
		rwsFlg_A1 = (EZDBStringItem)newItem("rwsFlg_A1");
		dsOrdPosnNum_A1 = (EZDBStringItem)newItem("dsOrdPosnNum_A1");
		serNumTakeFlg_A1 = (EZDBStringItem)newItem("serNumTakeFlg_A1");
		rwsNum_A1 = (EZDBStringItem)newItem("rwsNum_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2030_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2030_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum_A1", "xxNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"sceOrdTpNm_A1", "sceOrdTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"sceOrdTpCd_A1", "sceOrdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"trxOrdNum_A1", "trxOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"trxLineNum_A1", "trxLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"trxLineSubNum_A1", "trxLineSubNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"dplyLineNum_A1", "dplyLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"fromLocCd_A1", "fromLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"svcConfigMstrPk_A1", "svcConfigMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rwsOpenFlg_A1", "rwsOpenFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"flipMdseCd_A1", "flipMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"rwsQty_A1", "rwsQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxQty10Num_A1", "xxQty10Num_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"coaMdseTpCd_A1", "coaMdseTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"aslMdseCd_A1", "aslMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlSwhCd_A1", "rtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A1", "rtlSwhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"rwsFlg_A1", "rwsFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsOrdPosnNum_A1", "dsOrdPosnNum_A1", "A1", null, TYPE_HANKAKUEISU, "6", null},
	{"serNumTakeFlg_A1", "serNumTakeFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"rwsNum_A1", "rwsNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A1
        {"XX_CHK_BOX",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_CHK_BOX",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
        {"SCE_ORD_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpNm_A1
        {"SCE_ORD_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_A1
        {"TRX_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxOrdNum_A1
        {"TRX_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum_A1
        {"TRX_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineSubNum_A1
        {"DPLY_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_A1
        {"FROM_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromLocCd_A1
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A1
        {"RWS_OPEN_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsOpenFlg_A1
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"FLIP_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flipMdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"RWS_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsQty_A1
        {"XX_QTY_10_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQty10Num_A1
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"COA_MDSE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_A1
        {"ASL_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aslMdseCd_A1
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A1
        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A1
        {"RWS_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsFlg_A1
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_A1
        {"SER_NUM_TAKE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumTakeFlg_A1
        {"RWS_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_A1
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
