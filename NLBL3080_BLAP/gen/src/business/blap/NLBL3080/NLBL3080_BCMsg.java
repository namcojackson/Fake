//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180720110758000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3080_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3080_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SMRY_LINE_FLG_B1*/
	public final EZDCStringItem              xxSmryLineFlg_B1;

    /** XX_SMRY_LINE_FLG_B2*/
	public final EZDCStringItem              xxSmryLineFlg_B2;

    /** XX_NUM_B1*/
	public final EZDCBigDecimalItem              xxNum_B1;

    /** XX_CHK_BOX_B1*/
	public final EZDCStringItem              xxChkBox_B1;

    /** XX_CHK_BOX_B2*/
	public final EZDCStringItem              xxChkBox_B2;

    /** CPO_ORD_NUM_B1*/
	public final EZDCStringItem              cpoOrdNum_B1;

    /** DS_ORD_POSN_NUM_B1*/
	public final EZDCStringItem              dsOrdPosnNum_B1;

    /** DPLY_LINE_NUM_B1*/
	public final EZDCStringItem              dplyLineNum_B1;

    /** CPO_DTL_LINE_NUM_B1*/
	public final EZDCStringItem              cpoDtlLineNum_B1;

    /** CPO_DTL_LINE_SUB_NUM_B1*/
	public final EZDCStringItem              cpoDtlLineSubNum_B1;

    /** XX_DPLY_BY_ITEM_NM_B1*/
	public final EZDCStringItem              xxDplyByItemNm_B1;

    /** SVC_CONFIG_MSTR_PK_B1*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_B1;

    /** T_MDL_NM_B1*/
	public final EZDCStringItem              t_MdlNm_B1;

    /** MDSE_CD_B1*/
	public final EZDCStringItem              mdseCd_B1;

    /** MDSE_DESC_SHORT_TXT_B1*/
	public final EZDCStringItem              mdseDescShortTxt_B1;

    /** COA_MDSE_TP_CD_B1*/
	public final EZDCStringItem              coaMdseTpCd_B1;

    /** XX_SUPD_FLG_B1*/
	public final EZDCStringItem              xxSupdFlg_B1;

    /** SET_MDSE_CD_B1*/
	public final EZDCStringItem              setMdseCd_B1;

    /** BACK_ORD_IMPCT_TP_DESC_TXT_B1*/
	public final EZDCStringItem              backOrdImpctTpDescTxt_B1;

    /** STK_STS_CD_B1*/
	public final EZDCStringItem              stkStsCd_B1;

    /** ORD_QTY_B1*/
	public final EZDCBigDecimalItem              ordQty_B1;

    /** ORD_QTY_B2*/
	public final EZDCBigDecimalItem              ordQty_B2;

    /** INVTY_AVAL_QTY_B1*/
	public final EZDCBigDecimalItem              invtyAvalQty_B1;

    /** SER_NUM_B1*/
	public final EZDCStringItem              serNum_B1;

    /** PICK_SVC_CONFIG_MSTR_PK_B1*/
	public final EZDCBigDecimalItem              pickSvcConfigMstrPk_B1;

    /** RDD_DT_B1*/
	public final EZDCDateItem              rddDt_B1;

    /** DS_ORD_LINE_CATG_DESC_TXT_B1*/
	public final EZDCStringItem              dsOrdLineCatgDescTxt_B1;

    /** ORD_LINE_STS_NM_B1*/
	public final EZDCStringItem              ordLineStsNm_B1;

    /** RTL_WH_CD_B1*/
	public final EZDCStringItem              rtlWhCd_B1;

    /** RTL_WH_NM_B1*/
	public final EZDCStringItem              rtlWhNm_B1;

    /** RTL_SWH_CD_B1*/
	public final EZDCStringItem              rtlSwhCd_B1;

    /** RTL_SWH_NM_B1*/
	public final EZDCStringItem              rtlSwhNm_B1;

    /** SUPD_LOCK_FLG_B1*/
	public final EZDCStringItem              supdLockFlg_B1;

    /** SHPG_PLN_NUM_B1*/
	public final EZDCStringItem              shpgPlnNum_B1;

    /** TRX_SRC_TP_CD_B1*/
	public final EZDCStringItem              trxSrcTpCd_B1;

    /** INVTY_LOC_CD_B1*/
	public final EZDCStringItem              invtyLocCd_B1;

    /** REQ_SHPG_SVC_LVL_CD_B1*/
	public final EZDCStringItem              reqShpgSvcLvlCd_B1;

    /** CPO_ORD_TS_B1*/
	public final EZDCStringItem              cpoOrdTs_B1;

    /** SET_SHPG_PLN_NUM_B1*/
	public final EZDCStringItem              setShpgPlnNum_B1;

    /** SVC_MACH_MSTR_PK_B1*/
	public final EZDCBigDecimalItem              svcMachMstrPk_B1;

    /** SVC_MACH_MSTR_LOC_STS_CD_B1*/
	public final EZDCStringItem              svcMachMstrLocStsCd_B1;

    /** CUR_LOC_NUM_B1*/
	public final EZDCStringItem              curLocNum_B1;

    /** ALLOC_REQ_FLG_B1*/
	public final EZDCStringItem              allocReqFlg_B1;

    /** MDL_ID_B1*/
	public final EZDCBigDecimalItem              mdlId_B1;

    /** INVTY_ACCT_CD_B1*/
	public final EZDCStringItem              invtyAcctCd_B1;


	/**
	 * NLBL3080_BCMsg is constructor.
	 * The initialization when the instance of NLBL3080_BCMsg is generated.
	 */
	public NLBL3080_BCMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3080_BCMsg is constructor.
	 * The initialization when the instance of NLBL3080_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3080_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSmryLineFlg_B1 = (EZDCStringItem)newItem("xxSmryLineFlg_B1");
		xxSmryLineFlg_B2 = (EZDCStringItem)newItem("xxSmryLineFlg_B2");
		xxNum_B1 = (EZDCBigDecimalItem)newItem("xxNum_B1");
		xxChkBox_B1 = (EZDCStringItem)newItem("xxChkBox_B1");
		xxChkBox_B2 = (EZDCStringItem)newItem("xxChkBox_B2");
		cpoOrdNum_B1 = (EZDCStringItem)newItem("cpoOrdNum_B1");
		dsOrdPosnNum_B1 = (EZDCStringItem)newItem("dsOrdPosnNum_B1");
		dplyLineNum_B1 = (EZDCStringItem)newItem("dplyLineNum_B1");
		cpoDtlLineNum_B1 = (EZDCStringItem)newItem("cpoDtlLineNum_B1");
		cpoDtlLineSubNum_B1 = (EZDCStringItem)newItem("cpoDtlLineSubNum_B1");
		xxDplyByItemNm_B1 = (EZDCStringItem)newItem("xxDplyByItemNm_B1");
		svcConfigMstrPk_B1 = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_B1");
		t_MdlNm_B1 = (EZDCStringItem)newItem("t_MdlNm_B1");
		mdseCd_B1 = (EZDCStringItem)newItem("mdseCd_B1");
		mdseDescShortTxt_B1 = (EZDCStringItem)newItem("mdseDescShortTxt_B1");
		coaMdseTpCd_B1 = (EZDCStringItem)newItem("coaMdseTpCd_B1");
		xxSupdFlg_B1 = (EZDCStringItem)newItem("xxSupdFlg_B1");
		setMdseCd_B1 = (EZDCStringItem)newItem("setMdseCd_B1");
		backOrdImpctTpDescTxt_B1 = (EZDCStringItem)newItem("backOrdImpctTpDescTxt_B1");
		stkStsCd_B1 = (EZDCStringItem)newItem("stkStsCd_B1");
		ordQty_B1 = (EZDCBigDecimalItem)newItem("ordQty_B1");
		ordQty_B2 = (EZDCBigDecimalItem)newItem("ordQty_B2");
		invtyAvalQty_B1 = (EZDCBigDecimalItem)newItem("invtyAvalQty_B1");
		serNum_B1 = (EZDCStringItem)newItem("serNum_B1");
		pickSvcConfigMstrPk_B1 = (EZDCBigDecimalItem)newItem("pickSvcConfigMstrPk_B1");
		rddDt_B1 = (EZDCDateItem)newItem("rddDt_B1");
		dsOrdLineCatgDescTxt_B1 = (EZDCStringItem)newItem("dsOrdLineCatgDescTxt_B1");
		ordLineStsNm_B1 = (EZDCStringItem)newItem("ordLineStsNm_B1");
		rtlWhCd_B1 = (EZDCStringItem)newItem("rtlWhCd_B1");
		rtlWhNm_B1 = (EZDCStringItem)newItem("rtlWhNm_B1");
		rtlSwhCd_B1 = (EZDCStringItem)newItem("rtlSwhCd_B1");
		rtlSwhNm_B1 = (EZDCStringItem)newItem("rtlSwhNm_B1");
		supdLockFlg_B1 = (EZDCStringItem)newItem("supdLockFlg_B1");
		shpgPlnNum_B1 = (EZDCStringItem)newItem("shpgPlnNum_B1");
		trxSrcTpCd_B1 = (EZDCStringItem)newItem("trxSrcTpCd_B1");
		invtyLocCd_B1 = (EZDCStringItem)newItem("invtyLocCd_B1");
		reqShpgSvcLvlCd_B1 = (EZDCStringItem)newItem("reqShpgSvcLvlCd_B1");
		cpoOrdTs_B1 = (EZDCStringItem)newItem("cpoOrdTs_B1");
		setShpgPlnNum_B1 = (EZDCStringItem)newItem("setShpgPlnNum_B1");
		svcMachMstrPk_B1 = (EZDCBigDecimalItem)newItem("svcMachMstrPk_B1");
		svcMachMstrLocStsCd_B1 = (EZDCStringItem)newItem("svcMachMstrLocStsCd_B1");
		curLocNum_B1 = (EZDCStringItem)newItem("curLocNum_B1");
		allocReqFlg_B1 = (EZDCStringItem)newItem("allocReqFlg_B1");
		mdlId_B1 = (EZDCBigDecimalItem)newItem("mdlId_B1");
		invtyAcctCd_B1 = (EZDCStringItem)newItem("invtyAcctCd_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3080_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3080_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSmryLineFlg_B1", "xxSmryLineFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSmryLineFlg_B2", "xxSmryLineFlg_B2", "B2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxNum_B1", "xxNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_B2", "xxChkBox_B2", "B2", null, TYPE_HANKAKUEIJI, "1", null},
	{"cpoOrdNum_B1", "cpoOrdNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdPosnNum_B1", "dsOrdPosnNum_B1", "B1", null, TYPE_HANKAKUEISU, "6", null},
	{"dplyLineNum_B1", "dplyLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"cpoDtlLineNum_B1", "cpoDtlLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_B1", "cpoDtlLineSubNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"xxDplyByItemNm_B1", "xxDplyByItemNm_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"svcConfigMstrPk_B1", "svcConfigMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"t_MdlNm_B1", "t_MdlNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B1", "mdseDescShortTxt_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"coaMdseTpCd_B1", "coaMdseTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxSupdFlg_B1", "xxSupdFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"setMdseCd_B1", "setMdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"backOrdImpctTpDescTxt_B1", "backOrdImpctTpDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"stkStsCd_B1", "stkStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"ordQty_B1", "ordQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordQty_B2", "ordQty_B2", "B2", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invtyAvalQty_B1", "invtyAvalQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_B1", "serNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"pickSvcConfigMstrPk_B1", "pickSvcConfigMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rddDt_B1", "rddDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsOrdLineCatgDescTxt_B1", "dsOrdLineCatgDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"ordLineStsNm_B1", "ordLineStsNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhCd_B1", "rtlWhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_B1", "rtlWhNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_B1", "rtlSwhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_B1", "rtlSwhNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"supdLockFlg_B1", "supdLockFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"shpgPlnNum_B1", "shpgPlnNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"trxSrcTpCd_B1", "trxSrcTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"invtyLocCd_B1", "invtyLocCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"reqShpgSvcLvlCd_B1", "reqShpgSvcLvlCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"cpoOrdTs_B1", "cpoOrdTs_B1", "B1", null, TYPE_HANKAKUSUJI, "17", null},
	{"setShpgPlnNum_B1", "setShpgPlnNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"svcMachMstrPk_B1", "svcMachMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrLocStsCd_B1", "svcMachMstrLocStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"curLocNum_B1", "curLocNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"allocReqFlg_B1", "allocReqFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"mdlId_B1", "mdlId_B1", "B1", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"invtyAcctCd_B1", "invtyAcctCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_B1
        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_B2
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B2
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_B1
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_B1
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_B1
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_B1
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_B1
        {"XX_DPLY_BY_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByItemNm_B1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_B1
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B1
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_B1
        {"XX_SUPD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSupdFlg_B1
        {"SET_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setMdseCd_B1
        {"BACK_ORD_IMPCT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backOrdImpctTpDescTxt_B1
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_B1
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_B1
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_B2
        {"INVTY_AVAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAvalQty_B1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_B1
        {"PICK_SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickSvcConfigMstrPk_B1
        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt_B1
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_B1
        {"ORD_LINE_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineStsNm_B1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_B1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_B1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_B1
        {"SUPD_LOCK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdLockFlg_B1
        {"SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgPlnNum_B1
        {"TRX_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxSrcTpCd_B1
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_B1
        {"REQ_SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//reqShpgSvcLvlCd_B1
        {"CPO_ORD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdTs_B1
        {"SET_SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setShpgPlnNum_B1
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_B1
        {"SVC_MACH_MSTR_LOC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrLocStsCd_B1
        {"CUR_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curLocNum_B1
        {"ALLOC_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//allocReqFlg_B1
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_B1
        {"INVTY_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAcctCd_B1
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
