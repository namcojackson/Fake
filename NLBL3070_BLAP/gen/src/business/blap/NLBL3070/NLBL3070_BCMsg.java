//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180307150917000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3070_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3070 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3070_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM_B1*/
	public final EZDCBigDecimalItem              xxNum_B1;

    /** XX_SMRY_LINE_FLG_B1*/
	public final EZDCStringItem              xxSmryLineFlg_B1;

    /** XX_DPLY_CTRL_FLG_B1*/
	public final EZDCStringItem              xxDplyCtrlFlg_B1;

    /** XX_CHK_BOX_B1*/
	public final EZDCStringItem              xxChkBox_B1;

    /** XX_CHK_BOX_B2*/
	public final EZDCStringItem              xxChkBox_B2;

    /** SHIP_SVC_CONFIG_MSTR_PK_B1*/
	public final EZDCBigDecimalItem              shipSvcConfigMstrPk_B1;

    /** TRX_HDR_NUM_B1*/
	public final EZDCStringItem              trxHdrNum_B1;

    /** TRX_LINE_NUM_B1*/
	public final EZDCStringItem              trxLineNum_B1;

    /** TRX_LINE_SUB_NUM_B1*/
	public final EZDCStringItem              trxLineSubNum_B1;

    /** DPLY_LINE_NUM_B1*/
	public final EZDCStringItem              dplyLineNum_B1;

    /** SO_NUM_B1*/
	public final EZDCStringItem              soNum_B1;

    /** SO_SLP_NUM_B1*/
	public final EZDCStringItem              soSlpNum_B1;

    /** XX_PSN_FIRST_MID_LAST_NM_B1*/
	public final EZDCStringItem              xxPsnFirstMidLastNm_B1;

    /** RTL_WH_CD_B1*/
	public final EZDCStringItem              rtlWhCd_B1;

    /** RTL_SWH_CD_B1*/
	public final EZDCStringItem              rtlSwhCd_B1;

    /** INVTY_LOC_CD_B1*/
	public final EZDCStringItem              invtyLocCd_B1;

    /** RTL_WH_NM_B1*/
	public final EZDCStringItem              rtlWhNm_B1;

    /** FIRST_LINE_ADDR_RW*/
	public final EZDCStringItem              firstLineAddr_RW;

    /** CTY_ADDR_RW*/
	public final EZDCStringItem              ctyAddr_RW;

    /** ST_CD_RW*/
	public final EZDCStringItem              stCd_RW;

    /** POST_CD_RW*/
	public final EZDCStringItem              postCd_RW;

    /** CTRY_CD_RW*/
	public final EZDCStringItem              ctryCd_RW;

    /** XX_PSN_FIRST_MID_LAST_NM_B2*/
	public final EZDCStringItem              xxPsnFirstMidLastNm_B2;

    /** MDSE_CD_B1*/
	public final EZDCStringItem              mdseCd_B1;

    /** MDSE_DESC_SHORT_TXT_B1*/
	public final EZDCStringItem              mdseDescShortTxt_B1;

    /** BACK_ORD_IMPCT_TP_DESC_TXT_B1*/
	public final EZDCStringItem              backOrdImpctTpDescTxt_B1;

    /** XX_SHIP_QTY_B1*/
	public final EZDCBigDecimalItem              xxShipQty_B1;

    /** SER_NUM_B1*/
	public final EZDCStringItem              serNum_B1;

    /** RDD_DT_B1*/
	public final EZDCDateItem              rddDt_B1;

    /** SCHD_DELY_DT_B1*/
	public final EZDCDateItem              schdDelyDt_B1;

    /** TECH_MEET_TRUCK_FLG_B1*/
	public final EZDCStringItem              techMeetTruckFlg_B1;

    /** SCHD_DELY_TS_DPLY_TXT_B1*/
	public final EZDCStringItem              schdDelyTsDplyTxt_B1;

    /** RQST_RCV_DT_TXT_B1*/
	public final EZDCStringItem              rqstRcvDtTxt_B1;

    /** XX_TS_DSP_19_TXT_PC*/
	public final EZDCStringItem              xxTsDsp19Txt_PC;

    /** XX_TS_DSP_19_TXT_DS*/
	public final EZDCStringItem              xxTsDsp19Txt_DS;

    /** XX_TS_DSP_19_TXT_TA*/
	public final EZDCStringItem              xxTsDsp19Txt_TA;

    /** XX_TS_DSP_19_TXT_DD*/
	public final EZDCStringItem              xxTsDsp19Txt_DD;

    /** XX_TS_DSP_19_TXT_SC*/
	public final EZDCStringItem              xxTsDsp19Txt_SC;

    /** XX_TS_DSP_19_TXT_OR*/
	public final EZDCStringItem              xxTsDsp19Txt_OR;

    /** SHIP_DT_TM_TS_B1*/
	public final EZDCStringItem              shipDtTmTs_B1;

    /** CARR_RSN_CD_B1*/
	public final EZDCStringItem              carrRsnCd_B1;

    /** ACTL_DELY_DT_B1*/
	public final EZDCDateItem              actlDelyDt_B1;

    /** ACTL_DELY_DT_BK*/
	public final EZDCDateItem              actlDelyDt_BK;

    /** SCHD_DELY_TS_DPLY_TXT_B2*/
	public final EZDCStringItem              schdDelyTsDplyTxt_B2;

    /** RQST_RCV_DT_TXT_B2*/
	public final EZDCStringItem              rqstRcvDtTxt_B2;

    /** SVC_TASK_CPLT_DT_B1*/
	public final EZDCDateItem              svcTaskCpltDt_B1;

    /** SVC_TASK_STS_DESC_TXT_B1*/
	public final EZDCStringItem              svcTaskStsDescTxt_B1;

    /** DS_SO_LINE_STS_DESC_TXT_B1*/
	public final EZDCStringItem              dsSoLineStsDescTxt_B1;

    /** CARR_CD_B1*/
	public final EZDCStringItem              carrCd_B1;

    /** CARR_NM_B1*/
	public final EZDCStringItem              carrNm_B1;

    /** BOL_NUM_B1*/
	public final EZDCStringItem              bolNum_B1;

    /** SHPG_SVC_LVL_CD_B1*/
	public final EZDCStringItem              shpgSvcLvlCd_B1;

    /** SHPG_SVC_LVL_DESC_TXT_B1*/
	public final EZDCStringItem              shpgSvcLvlDescTxt_B1;

    /** SCHD_COORD_STS_DESC_TXT_B1*/
	public final EZDCStringItem              schdCoordStsDescTxt_B1;

    /** XX_TS_DSP_19_TXT_SR*/
	public final EZDCStringItem              xxTsDsp19Txt_SR;

    /** TOT_FRT_AMT_B1*/
	public final EZDCBigDecimalItem              totFrtAmt_B1;

    /** SHIP_TO_CUST_CD_B1*/
	public final EZDCStringItem              shipToCustCd_B1;

    /** DS_ACCT_NM_B1*/
	public final EZDCStringItem              dsAcctNm_B1;

    /** FIRST_LINE_ADDR_B1*/
	public final EZDCStringItem              firstLineAddr_B1;

    /** CTY_ADDR_B1*/
	public final EZDCStringItem              ctyAddr_B1;

    /** ST_CD_B1*/
	public final EZDCStringItem              stCd_B1;

    /** POST_CD_B1*/
	public final EZDCStringItem              postCd_B1;

    /** CTRY_CD_B1*/
	public final EZDCStringItem              ctryCd_B1;

    /** PICK_SVC_CONFIG_MSTR_PK_B1*/
	public final EZDCBigDecimalItem              pickSvcConfigMstrPk_B1;

    /** STK_STS_CD_B1*/
	public final EZDCStringItem              stkStsCd_B1;

    /** DS_ORD_CATG_DESC_TXT_B1*/
	public final EZDCStringItem              dsOrdCatgDescTxt_B1;

    /** SCE_ORD_TP_CD_B1*/
	public final EZDCStringItem              sceOrdTpCd_B1;

    /** CARR_ACCT_NUM_B1*/
	public final EZDCStringItem              carrAcctNum_B1;

    /** OPEN_FLG_B1*/
	public final EZDCStringItem              openFlg_B1;

    /** SO_LINE_OPEN_FLG_B1*/
	public final EZDCStringItem              soLineOpenFlg_B1;

    /** SHIP_FLG_B1*/
	public final EZDCStringItem              shipFlg_B1;

    /** SO_SCR_CANC_AVAL_FLG_B1*/
	public final EZDCStringItem              soScrCancAvalFlg_B1;

    /** SHIP_AVAL_FLG_LS*/
	public final EZDCStringItem              shipAvalFlg_LS;

    /** SHIP_AVAL_FLG_PS*/
	public final EZDCStringItem              shipAvalFlg_PS;

    /** WMS_DROP_FLG_B1*/
	public final EZDCStringItem              wmsDropFlg_B1;

    /** WMS_DROP_RQST_FLG_B1*/
	public final EZDCStringItem              wmsDropRqstFlg_B1;

    /** MAN_SEND_RQST_FLG_BW*/
	public final EZDCStringItem              manSendRqstFlg_BW;

    /** MAN_SEND_RQST_FLG_BC*/
	public final EZDCStringItem              manSendRqstFlg_BC;

    /** SEND_RQST_FLG_B1*/
	public final EZDCStringItem              sendRqstFlg_B1;

    /** SET_MDSE_CD_B1*/
	public final EZDCStringItem              setMdseCd_B1;

    /** SET_SHPG_PLN_NUM_B1*/
	public final EZDCStringItem              setShpgPlnNum_B1;

    /** XX_QTY_10_NUM_B1*/
	public final EZDCBigDecimalItem              xxQty10Num_B1;

    /** INVTY_ACCT_CD_B1*/
	public final EZDCStringItem              invtyAcctCd_B1;

    /** SER_NUM_TAKE_FLG_B1*/
	public final EZDCStringItem              serNumTakeFlg_B1;

    /** INSTL_BASE_CTRL_FLG_B1*/
	public final EZDCStringItem              instlBaseCtrlFlg_B1;

    /** MDL_ID_B1*/
	public final EZDCBigDecimalItem              mdlId_B1;

    /** PRO_NUM_B1*/
	public final EZDCStringItem              proNum_B1;

    /** PRO_NUM_BD*/
	public final EZDCStringItem              proNum_BD;

    /** XX_MSG_ID_B1*/
	public final EZDCStringItem              xxMsgId_B1;


	/**
	 * NLBL3070_BCMsg is constructor.
	 * The initialization when the instance of NLBL3070_BCMsg is generated.
	 */
	public NLBL3070_BCMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3070_BCMsg is constructor.
	 * The initialization when the instance of NLBL3070_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3070_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum_B1 = (EZDCBigDecimalItem)newItem("xxNum_B1");
		xxSmryLineFlg_B1 = (EZDCStringItem)newItem("xxSmryLineFlg_B1");
		xxDplyCtrlFlg_B1 = (EZDCStringItem)newItem("xxDplyCtrlFlg_B1");
		xxChkBox_B1 = (EZDCStringItem)newItem("xxChkBox_B1");
		xxChkBox_B2 = (EZDCStringItem)newItem("xxChkBox_B2");
		shipSvcConfigMstrPk_B1 = (EZDCBigDecimalItem)newItem("shipSvcConfigMstrPk_B1");
		trxHdrNum_B1 = (EZDCStringItem)newItem("trxHdrNum_B1");
		trxLineNum_B1 = (EZDCStringItem)newItem("trxLineNum_B1");
		trxLineSubNum_B1 = (EZDCStringItem)newItem("trxLineSubNum_B1");
		dplyLineNum_B1 = (EZDCStringItem)newItem("dplyLineNum_B1");
		soNum_B1 = (EZDCStringItem)newItem("soNum_B1");
		soSlpNum_B1 = (EZDCStringItem)newItem("soSlpNum_B1");
		xxPsnFirstMidLastNm_B1 = (EZDCStringItem)newItem("xxPsnFirstMidLastNm_B1");
		rtlWhCd_B1 = (EZDCStringItem)newItem("rtlWhCd_B1");
		rtlSwhCd_B1 = (EZDCStringItem)newItem("rtlSwhCd_B1");
		invtyLocCd_B1 = (EZDCStringItem)newItem("invtyLocCd_B1");
		rtlWhNm_B1 = (EZDCStringItem)newItem("rtlWhNm_B1");
		firstLineAddr_RW = (EZDCStringItem)newItem("firstLineAddr_RW");
		ctyAddr_RW = (EZDCStringItem)newItem("ctyAddr_RW");
		stCd_RW = (EZDCStringItem)newItem("stCd_RW");
		postCd_RW = (EZDCStringItem)newItem("postCd_RW");
		ctryCd_RW = (EZDCStringItem)newItem("ctryCd_RW");
		xxPsnFirstMidLastNm_B2 = (EZDCStringItem)newItem("xxPsnFirstMidLastNm_B2");
		mdseCd_B1 = (EZDCStringItem)newItem("mdseCd_B1");
		mdseDescShortTxt_B1 = (EZDCStringItem)newItem("mdseDescShortTxt_B1");
		backOrdImpctTpDescTxt_B1 = (EZDCStringItem)newItem("backOrdImpctTpDescTxt_B1");
		xxShipQty_B1 = (EZDCBigDecimalItem)newItem("xxShipQty_B1");
		serNum_B1 = (EZDCStringItem)newItem("serNum_B1");
		rddDt_B1 = (EZDCDateItem)newItem("rddDt_B1");
		schdDelyDt_B1 = (EZDCDateItem)newItem("schdDelyDt_B1");
		techMeetTruckFlg_B1 = (EZDCStringItem)newItem("techMeetTruckFlg_B1");
		schdDelyTsDplyTxt_B1 = (EZDCStringItem)newItem("schdDelyTsDplyTxt_B1");
		rqstRcvDtTxt_B1 = (EZDCStringItem)newItem("rqstRcvDtTxt_B1");
		xxTsDsp19Txt_PC = (EZDCStringItem)newItem("xxTsDsp19Txt_PC");
		xxTsDsp19Txt_DS = (EZDCStringItem)newItem("xxTsDsp19Txt_DS");
		xxTsDsp19Txt_TA = (EZDCStringItem)newItem("xxTsDsp19Txt_TA");
		xxTsDsp19Txt_DD = (EZDCStringItem)newItem("xxTsDsp19Txt_DD");
		xxTsDsp19Txt_SC = (EZDCStringItem)newItem("xxTsDsp19Txt_SC");
		xxTsDsp19Txt_OR = (EZDCStringItem)newItem("xxTsDsp19Txt_OR");
		shipDtTmTs_B1 = (EZDCStringItem)newItem("shipDtTmTs_B1");
		carrRsnCd_B1 = (EZDCStringItem)newItem("carrRsnCd_B1");
		actlDelyDt_B1 = (EZDCDateItem)newItem("actlDelyDt_B1");
		actlDelyDt_BK = (EZDCDateItem)newItem("actlDelyDt_BK");
		schdDelyTsDplyTxt_B2 = (EZDCStringItem)newItem("schdDelyTsDplyTxt_B2");
		rqstRcvDtTxt_B2 = (EZDCStringItem)newItem("rqstRcvDtTxt_B2");
		svcTaskCpltDt_B1 = (EZDCDateItem)newItem("svcTaskCpltDt_B1");
		svcTaskStsDescTxt_B1 = (EZDCStringItem)newItem("svcTaskStsDescTxt_B1");
		dsSoLineStsDescTxt_B1 = (EZDCStringItem)newItem("dsSoLineStsDescTxt_B1");
		carrCd_B1 = (EZDCStringItem)newItem("carrCd_B1");
		carrNm_B1 = (EZDCStringItem)newItem("carrNm_B1");
		bolNum_B1 = (EZDCStringItem)newItem("bolNum_B1");
		shpgSvcLvlCd_B1 = (EZDCStringItem)newItem("shpgSvcLvlCd_B1");
		shpgSvcLvlDescTxt_B1 = (EZDCStringItem)newItem("shpgSvcLvlDescTxt_B1");
		schdCoordStsDescTxt_B1 = (EZDCStringItem)newItem("schdCoordStsDescTxt_B1");
		xxTsDsp19Txt_SR = (EZDCStringItem)newItem("xxTsDsp19Txt_SR");
		totFrtAmt_B1 = (EZDCBigDecimalItem)newItem("totFrtAmt_B1");
		shipToCustCd_B1 = (EZDCStringItem)newItem("shipToCustCd_B1");
		dsAcctNm_B1 = (EZDCStringItem)newItem("dsAcctNm_B1");
		firstLineAddr_B1 = (EZDCStringItem)newItem("firstLineAddr_B1");
		ctyAddr_B1 = (EZDCStringItem)newItem("ctyAddr_B1");
		stCd_B1 = (EZDCStringItem)newItem("stCd_B1");
		postCd_B1 = (EZDCStringItem)newItem("postCd_B1");
		ctryCd_B1 = (EZDCStringItem)newItem("ctryCd_B1");
		pickSvcConfigMstrPk_B1 = (EZDCBigDecimalItem)newItem("pickSvcConfigMstrPk_B1");
		stkStsCd_B1 = (EZDCStringItem)newItem("stkStsCd_B1");
		dsOrdCatgDescTxt_B1 = (EZDCStringItem)newItem("dsOrdCatgDescTxt_B1");
		sceOrdTpCd_B1 = (EZDCStringItem)newItem("sceOrdTpCd_B1");
		carrAcctNum_B1 = (EZDCStringItem)newItem("carrAcctNum_B1");
		openFlg_B1 = (EZDCStringItem)newItem("openFlg_B1");
		soLineOpenFlg_B1 = (EZDCStringItem)newItem("soLineOpenFlg_B1");
		shipFlg_B1 = (EZDCStringItem)newItem("shipFlg_B1");
		soScrCancAvalFlg_B1 = (EZDCStringItem)newItem("soScrCancAvalFlg_B1");
		shipAvalFlg_LS = (EZDCStringItem)newItem("shipAvalFlg_LS");
		shipAvalFlg_PS = (EZDCStringItem)newItem("shipAvalFlg_PS");
		wmsDropFlg_B1 = (EZDCStringItem)newItem("wmsDropFlg_B1");
		wmsDropRqstFlg_B1 = (EZDCStringItem)newItem("wmsDropRqstFlg_B1");
		manSendRqstFlg_BW = (EZDCStringItem)newItem("manSendRqstFlg_BW");
		manSendRqstFlg_BC = (EZDCStringItem)newItem("manSendRqstFlg_BC");
		sendRqstFlg_B1 = (EZDCStringItem)newItem("sendRqstFlg_B1");
		setMdseCd_B1 = (EZDCStringItem)newItem("setMdseCd_B1");
		setShpgPlnNum_B1 = (EZDCStringItem)newItem("setShpgPlnNum_B1");
		xxQty10Num_B1 = (EZDCBigDecimalItem)newItem("xxQty10Num_B1");
		invtyAcctCd_B1 = (EZDCStringItem)newItem("invtyAcctCd_B1");
		serNumTakeFlg_B1 = (EZDCStringItem)newItem("serNumTakeFlg_B1");
		instlBaseCtrlFlg_B1 = (EZDCStringItem)newItem("instlBaseCtrlFlg_B1");
		mdlId_B1 = (EZDCBigDecimalItem)newItem("mdlId_B1");
		proNum_B1 = (EZDCStringItem)newItem("proNum_B1");
		proNum_BD = (EZDCStringItem)newItem("proNum_BD");
		xxMsgId_B1 = (EZDCStringItem)newItem("xxMsgId_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3070_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3070_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum_B1", "xxNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSmryLineFlg_B1", "xxSmryLineFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_B1", "xxDplyCtrlFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_B2", "xxChkBox_B2", "B2", null, TYPE_HANKAKUEIJI, "1", null},
	{"shipSvcConfigMstrPk_B1", "shipSvcConfigMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"trxHdrNum_B1", "trxHdrNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"trxLineNum_B1", "trxLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"trxLineSubNum_B1", "trxLineSubNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"dplyLineNum_B1", "dplyLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"soNum_B1", "soNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"soSlpNum_B1", "soSlpNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"xxPsnFirstMidLastNm_B1", "xxPsnFirstMidLastNm_B1", "B1", null, TYPE_HANKAKUEISU, "90", null},
	{"rtlWhCd_B1", "rtlWhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd_B1", "rtlSwhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyLocCd_B1", "invtyLocCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_B1", "rtlWhNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"firstLineAddr_RW", "firstLineAddr_RW", "RW", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_RW", "ctyAddr_RW", "RW", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_RW", "stCd_RW", "RW", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_RW", "postCd_RW", "RW", null, TYPE_HANKAKUEISU, "15", null},
	{"ctryCd_RW", "ctryCd_RW", "RW", null, TYPE_HANKAKUEISU, "3", null},
	{"xxPsnFirstMidLastNm_B2", "xxPsnFirstMidLastNm_B2", "B2", null, TYPE_HANKAKUEISU, "90", null},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B1", "mdseDescShortTxt_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"backOrdImpctTpDescTxt_B1", "backOrdImpctTpDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxShipQty_B1", "xxShipQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_B1", "serNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"rddDt_B1", "rddDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"schdDelyDt_B1", "schdDelyDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"techMeetTruckFlg_B1", "techMeetTruckFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"schdDelyTsDplyTxt_B1", "schdDelyTsDplyTxt_B1", "B1", null, TYPE_HANKAKUEISU, "19", null},
	{"rqstRcvDtTxt_B1", "rqstRcvDtTxt_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxTsDsp19Txt_PC", "xxTsDsp19Txt_PC", "PC", null, TYPE_HANKAKUEISU, "19", null},
	{"xxTsDsp19Txt_DS", "xxTsDsp19Txt_DS", "DS", null, TYPE_HANKAKUEISU, "19", null},
	{"xxTsDsp19Txt_TA", "xxTsDsp19Txt_TA", "TA", null, TYPE_HANKAKUEISU, "19", null},
	{"xxTsDsp19Txt_DD", "xxTsDsp19Txt_DD", "DD", null, TYPE_HANKAKUEISU, "19", null},
	{"xxTsDsp19Txt_SC", "xxTsDsp19Txt_SC", "SC", null, TYPE_HANKAKUEISU, "19", null},
	{"xxTsDsp19Txt_OR", "xxTsDsp19Txt_OR", "OR", null, TYPE_HANKAKUEISU, "19", null},
	{"shipDtTmTs_B1", "shipDtTmTs_B1", "B1", null, TYPE_HANKAKUSUJI, "14", null},
	{"carrRsnCd_B1", "carrRsnCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"actlDelyDt_B1", "actlDelyDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"actlDelyDt_BK", "actlDelyDt_BK", "BK", null, TYPE_NENTSUKIHI, "8", null},
	{"schdDelyTsDplyTxt_B2", "schdDelyTsDplyTxt_B2", "B2", null, TYPE_HANKAKUEISU, "19", null},
	{"rqstRcvDtTxt_B2", "rqstRcvDtTxt_B2", "B2", null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskCpltDt_B1", "svcTaskCpltDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"svcTaskStsDescTxt_B1", "svcTaskStsDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsSoLineStsDescTxt_B1", "dsSoLineStsDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"carrCd_B1", "carrCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"carrNm_B1", "carrNm_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"bolNum_B1", "bolNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"shpgSvcLvlCd_B1", "shpgSvcLvlCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgSvcLvlDescTxt_B1", "shpgSvcLvlDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"schdCoordStsDescTxt_B1", "schdCoordStsDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxTsDsp19Txt_SR", "xxTsDsp19Txt_SR", "SR", null, TYPE_HANKAKUEISU, "19", null},
	{"totFrtAmt_B1", "totFrtAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"shipToCustCd_B1", "shipToCustCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_B1", "dsAcctNm_B1", "B1", null, TYPE_HANKAKUEISU, "360", null},
	{"firstLineAddr_B1", "firstLineAddr_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_B1", "ctyAddr_B1", "B1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_B1", "stCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_B1", "postCd_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"ctryCd_B1", "ctryCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"pickSvcConfigMstrPk_B1", "pickSvcConfigMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"stkStsCd_B1", "stkStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsOrdCatgDescTxt_B1", "dsOrdCatgDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"sceOrdTpCd_B1", "sceOrdTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"carrAcctNum_B1", "carrAcctNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"openFlg_B1", "openFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"soLineOpenFlg_B1", "soLineOpenFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"shipFlg_B1", "shipFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"soScrCancAvalFlg_B1", "soScrCancAvalFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"shipAvalFlg_LS", "shipAvalFlg_LS", "LS", null, TYPE_HANKAKUEISU, "1", null},
	{"shipAvalFlg_PS", "shipAvalFlg_PS", "PS", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsDropFlg_B1", "wmsDropFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsDropRqstFlg_B1", "wmsDropRqstFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"manSendRqstFlg_BW", "manSendRqstFlg_BW", "BW", null, TYPE_HANKAKUEISU, "1", null},
	{"manSendRqstFlg_BC", "manSendRqstFlg_BC", "BC", null, TYPE_HANKAKUEISU, "1", null},
	{"sendRqstFlg_B1", "sendRqstFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"setMdseCd_B1", "setMdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"setShpgPlnNum_B1", "setShpgPlnNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxQty10Num_B1", "xxQty10Num_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invtyAcctCd_B1", "invtyAcctCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"serNumTakeFlg_B1", "serNumTakeFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"instlBaseCtrlFlg_B1", "instlBaseCtrlFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"mdlId_B1", "mdlId_B1", "B1", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"proNum_B1", "proNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"proNum_BD", "proNum_BD", "BD", null, TYPE_HANKAKUEISU, "30", null},
	{"xxMsgId_B1", "xxMsgId_B1", "B1", null, TYPE_HANKAKUEISU, "9", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_B1
        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_B1
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B2
        {"SHIP_SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipSvcConfigMstrPk_B1
        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_B1
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum_B1
        {"TRX_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineSubNum_B1
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_B1
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_B1
        {"SO_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soSlpNum_B1
        {"XX_PSN_FIRST_MID_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnFirstMidLastNm_B1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_B1
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_B1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_B1
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_RW
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_RW
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_RW
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_RW
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd_RW
        {"XX_PSN_FIRST_MID_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnFirstMidLastNm_B2
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B1
        {"BACK_ORD_IMPCT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backOrdImpctTpDescTxt_B1
        {"XX_SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipQty_B1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_B1
        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt_B1
        {"SCHD_DELY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDelyDt_B1
        {"TECH_MEET_TRUCK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techMeetTruckFlg_B1
        {"SCHD_DELY_TS_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDelyTsDplyTxt_B1
        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_B1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_PC
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_DS
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_TA
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_DD
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_SC
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_OR
        {"SHIP_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipDtTmTs_B1
        {"CARR_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrRsnCd_B1
        {"ACTL_DELY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actlDelyDt_B1
        {"ACTL_DELY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actlDelyDt_BK
        {"SCHD_DELY_TS_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDelyTsDplyTxt_B2
        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_B2
        {"SVC_TASK_CPLT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskCpltDt_B1
        {"SVC_TASK_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsDescTxt_B1
        {"DS_SO_LINE_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSoLineStsDescTxt_B1
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_B1
        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_B1
        {"BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bolNum_B1
        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_B1
        {"SHPG_SVC_LVL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlDescTxt_B1
        {"SCHD_COORD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordStsDescTxt_B1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_SR
        {"TOT_FRT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFrtAmt_B1
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_B1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_B1
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_B1
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_B1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_B1
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_B1
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd_B1
        {"PICK_SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickSvcConfigMstrPk_B1
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_B1
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_B1
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_B1
        {"CARR_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrAcctNum_B1
        {"OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openFlg_B1
        {"SO_LINE_OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soLineOpenFlg_B1
        {"SHIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFlg_B1
        {"SO_SCR_CANC_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soScrCancAvalFlg_B1
        {"SHIP_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipAvalFlg_LS
        {"SHIP_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipAvalFlg_PS
        {"WMS_DROP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDropFlg_B1
        {"WMS_DROP_RQST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDropRqstFlg_B1
        {"MAN_SEND_RQST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manSendRqstFlg_BW
        {"MAN_SEND_RQST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manSendRqstFlg_BC
        {"SEND_RQST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sendRqstFlg_B1
        {"SET_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setMdseCd_B1
        {"SET_SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setShpgPlnNum_B1
        {"XX_QTY_10_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQty10Num_B1
        {"INVTY_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAcctCd_B1
        {"SER_NUM_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumTakeFlg_B1
        {"INSTL_BASE_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//instlBaseCtrlFlg_B1
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_B1
        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_B1
        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_BD
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId_B1
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

