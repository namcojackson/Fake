//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230227160345000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2030_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL2030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL2030 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2030_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM_B1*/
	public final EZDSBigDecimalItem              xxNum_B1;

    /** XX_CHK_BOX_B1*/
	public final EZDSStringItem              xxChkBox_B1;

    /** XX_CHK_BOX_BK*/
	public final EZDSStringItem              xxChkBox_BK;

    /** RWS_STS_DESC_TXT_B1*/
	public final EZDSStringItem              rwsStsDescTxt_B1;

    /** RWS_STS_CD_B1*/
	public final EZDSStringItem              rwsStsCd_B1;

    /** RWS_NUM_B1*/
	public final EZDSStringItem              rwsNum_B1;

    /** XX_CRAT_DT_B1*/
	public final EZDSDateItem              xxCratDt_B1;

    /** RWS_DTL_LINE_NUM_B1*/
	public final EZDSStringItem              rwsDtlLineNum_B1;

    /** RTL_WH_CD_B1*/
	public final EZDSStringItem              rtlWhCd_B1;

    /** RTL_WH_NM_B1*/
	public final EZDSStringItem              rtlWhNm_B1;

    /** RTL_WH_CD_B2*/
	public final EZDSStringItem              rtlWhCd_B2;

    /** XX_DPLY_CTRL_FLG_WH*/
	public final EZDSStringItem              xxDplyCtrlFlg_WH;

    /** INVTY_OWNR_CD_B1*/
	public final EZDSStringItem              invtyOwnrCd_B1;

    /** RWS_REF_NUM_B1*/
	public final EZDSStringItem              rwsRefNum_B1;

    /** THIRD_PTY_DSP_TP_CD_B1*/
	public final EZDSStringItem              thirdPtyDspTpCd_B1;

    /** THIRD_PTY_DSP_TP_CD_B2*/
	public final EZDSStringItem              thirdPtyDspTpCd_B2;

    /** XX_DPLY_CTRL_FLG_TP*/
	public final EZDSStringItem              xxDplyCtrlFlg_TP;

    /** IMPT_INV_BOL_NUM_B1*/
	public final EZDSStringItem              imptInvBolNum_B1;

    /** SCE_ORD_TP_CD_B1*/
	public final EZDSStringItem              sceOrdTpCd_B1;

    /** SCE_ORD_TP_NM_B1*/
	public final EZDSStringItem              sceOrdTpNm_B1;

    /** TRX_ORD_NUM_B1*/
	public final EZDSStringItem              trxOrdNum_B1;

    /** TRX_LINE_NUM_B1*/
	public final EZDSStringItem              trxLineNum_B1;

    /** TRX_LINE_SUB_NUM_B1*/
	public final EZDSStringItem              trxLineSubNum_B1;

    /** DPLY_LINE_NUM_B1*/
	public final EZDSStringItem              dplyLineNum_B1;

    /** FROM_LOC_CD_B1*/
	public final EZDSStringItem              fromLocCd_B1;

    /** DS_ACCT_NM_B1*/
	public final EZDSStringItem              dsAcctNm_B1;

    /** SVC_CONFIG_MSTR_PK_B1*/
	public final EZDSBigDecimalItem              svcConfigMstrPk_B1;

    /** MDSE_CD_B1*/
	public final EZDSStringItem              mdseCd_B1;

    /** FLIP_MDSE_CD_B1*/
	public final EZDSStringItem              flipMdseCd_B1;

    /** MDSE_DESC_SHORT_TXT_B1*/
	public final EZDSStringItem              mdseDescShortTxt_B1;

    /** RWS_QTY_B1*/
	public final EZDSBigDecimalItem              rwsQty_B1;

    /** XX_QTY_10_NUM_B1*/
	public final EZDSBigDecimalItem              xxQty10Num_B1;

    /** RWS_PUT_AWAY_QTY_B1*/
	public final EZDSBigDecimalItem              rwsPutAwayQty_B1;

    /** SER_NUM_B1*/
	public final EZDSStringItem              serNum_B1;

    /** RTL_SWH_CD_B1*/
	public final EZDSStringItem              rtlSwhCd_B1;

    /** RTL_SWH_NM_B1*/
	public final EZDSStringItem              rtlSwhNm_B1;

    /** XX_RTL_WH_SRCH_TXT_B1*/
	public final EZDSStringItem              xxRtlWhSrchTxt_B1;

    /** SER_NUM_TAKE_FLG_B1*/
	public final EZDSStringItem              serNumTakeFlg_B1;

    /** RWS_SCR_CANC_AVAL_FLG_B1*/
	public final EZDSStringItem              rwsScrCancAvalFlg_B1;

    /** XX_STS_CD_10_TXT_B1*/
	public final EZDSStringItem              xxStsCd10Txt_B1;

    /** OPEN_FLG_BH*/
	public final EZDSStringItem              openFlg_BH;

    /** OPEN_FLG_BD*/
	public final EZDSStringItem              openFlg_BD;

    /** RCV_FLG_BH*/
	public final EZDSStringItem              rcvFlg_BH;

    /** RCV_FLG_BD*/
	public final EZDSStringItem              rcvFlg_BD;

    /** PO_ORD_NUM_B1*/
	public final EZDSStringItem              poOrdNum_B1;

    /** PO_ORD_DTL_LINE_NUM_B1*/
	public final EZDSStringItem              poOrdDtlLineNum_B1;

    /** WMS_DROP_FLG_B1*/
	public final EZDSStringItem              wmsDropFlg_B1;

    /** SEND_RQST_FLG_B1*/
	public final EZDSStringItem              sendRqstFlg_B1;

    /** TRX_ORD_NUM_B2*/
	public final EZDSStringItem              trxOrdNum_B2;

    /** TRX_LINE_NUM_B2*/
	public final EZDSStringItem              trxLineNum_B2;

    /** S80_STK_STS_CD_B1*/
	public final EZDSStringItem              s80StkStsCd_B1;

    /** RCV_INVTY_LOC_CD_B1*/
	public final EZDSStringItem              rcvInvtyLocCd_B1;

    /** _EZUpdateDateTime_RH*/
	public final EZDSStringItem              ezUpTime_RH;

    /** _EZUpTimeZone_RH*/
	public final EZDSStringItem              ezUpTimeZone_RH;

    /** _EZUpdateDateTime_RD*/
	public final EZDSStringItem              ezUpTime_RD;

    /** _EZUpTimeZone_RD*/
	public final EZDSStringItem              ezUpTimeZone_RD;

    /** _EZUpdateDateTime_PH*/
	public final EZDSStringItem              ezUpTime_PH;

    /** _EZUpTimeZone_PH*/
	public final EZDSStringItem              ezUpTimeZone_PH;

    /** _EZUpdateDateTime_PD*/
	public final EZDSStringItem              ezUpTime_PD;

    /** _EZUpTimeZone_PD*/
	public final EZDSStringItem              ezUpTimeZone_PD;

    /** DS_ORD_POSN_NUM_B1*/
	public final EZDSStringItem              dsOrdPosnNum_B1;


	/**
	 * NLAL2030_BSMsg is constructor.
	 * The initialization when the instance of NLAL2030_BSMsg is generated.
	 */
	public NLAL2030_BSMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2030_BSMsg is constructor.
	 * The initialization when the instance of NLAL2030_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2030_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum_B1 = (EZDSBigDecimalItem)newItem("xxNum_B1");
		xxChkBox_B1 = (EZDSStringItem)newItem("xxChkBox_B1");
		xxChkBox_BK = (EZDSStringItem)newItem("xxChkBox_BK");
		rwsStsDescTxt_B1 = (EZDSStringItem)newItem("rwsStsDescTxt_B1");
		rwsStsCd_B1 = (EZDSStringItem)newItem("rwsStsCd_B1");
		rwsNum_B1 = (EZDSStringItem)newItem("rwsNum_B1");
		xxCratDt_B1 = (EZDSDateItem)newItem("xxCratDt_B1");
		rwsDtlLineNum_B1 = (EZDSStringItem)newItem("rwsDtlLineNum_B1");
		rtlWhCd_B1 = (EZDSStringItem)newItem("rtlWhCd_B1");
		rtlWhNm_B1 = (EZDSStringItem)newItem("rtlWhNm_B1");
		rtlWhCd_B2 = (EZDSStringItem)newItem("rtlWhCd_B2");
		xxDplyCtrlFlg_WH = (EZDSStringItem)newItem("xxDplyCtrlFlg_WH");
		invtyOwnrCd_B1 = (EZDSStringItem)newItem("invtyOwnrCd_B1");
		rwsRefNum_B1 = (EZDSStringItem)newItem("rwsRefNum_B1");
		thirdPtyDspTpCd_B1 = (EZDSStringItem)newItem("thirdPtyDspTpCd_B1");
		thirdPtyDspTpCd_B2 = (EZDSStringItem)newItem("thirdPtyDspTpCd_B2");
		xxDplyCtrlFlg_TP = (EZDSStringItem)newItem("xxDplyCtrlFlg_TP");
		imptInvBolNum_B1 = (EZDSStringItem)newItem("imptInvBolNum_B1");
		sceOrdTpCd_B1 = (EZDSStringItem)newItem("sceOrdTpCd_B1");
		sceOrdTpNm_B1 = (EZDSStringItem)newItem("sceOrdTpNm_B1");
		trxOrdNum_B1 = (EZDSStringItem)newItem("trxOrdNum_B1");
		trxLineNum_B1 = (EZDSStringItem)newItem("trxLineNum_B1");
		trxLineSubNum_B1 = (EZDSStringItem)newItem("trxLineSubNum_B1");
		dplyLineNum_B1 = (EZDSStringItem)newItem("dplyLineNum_B1");
		fromLocCd_B1 = (EZDSStringItem)newItem("fromLocCd_B1");
		dsAcctNm_B1 = (EZDSStringItem)newItem("dsAcctNm_B1");
		svcConfigMstrPk_B1 = (EZDSBigDecimalItem)newItem("svcConfigMstrPk_B1");
		mdseCd_B1 = (EZDSStringItem)newItem("mdseCd_B1");
		flipMdseCd_B1 = (EZDSStringItem)newItem("flipMdseCd_B1");
		mdseDescShortTxt_B1 = (EZDSStringItem)newItem("mdseDescShortTxt_B1");
		rwsQty_B1 = (EZDSBigDecimalItem)newItem("rwsQty_B1");
		xxQty10Num_B1 = (EZDSBigDecimalItem)newItem("xxQty10Num_B1");
		rwsPutAwayQty_B1 = (EZDSBigDecimalItem)newItem("rwsPutAwayQty_B1");
		serNum_B1 = (EZDSStringItem)newItem("serNum_B1");
		rtlSwhCd_B1 = (EZDSStringItem)newItem("rtlSwhCd_B1");
		rtlSwhNm_B1 = (EZDSStringItem)newItem("rtlSwhNm_B1");
		xxRtlWhSrchTxt_B1 = (EZDSStringItem)newItem("xxRtlWhSrchTxt_B1");
		serNumTakeFlg_B1 = (EZDSStringItem)newItem("serNumTakeFlg_B1");
		rwsScrCancAvalFlg_B1 = (EZDSStringItem)newItem("rwsScrCancAvalFlg_B1");
		xxStsCd10Txt_B1 = (EZDSStringItem)newItem("xxStsCd10Txt_B1");
		openFlg_BH = (EZDSStringItem)newItem("openFlg_BH");
		openFlg_BD = (EZDSStringItem)newItem("openFlg_BD");
		rcvFlg_BH = (EZDSStringItem)newItem("rcvFlg_BH");
		rcvFlg_BD = (EZDSStringItem)newItem("rcvFlg_BD");
		poOrdNum_B1 = (EZDSStringItem)newItem("poOrdNum_B1");
		poOrdDtlLineNum_B1 = (EZDSStringItem)newItem("poOrdDtlLineNum_B1");
		wmsDropFlg_B1 = (EZDSStringItem)newItem("wmsDropFlg_B1");
		sendRqstFlg_B1 = (EZDSStringItem)newItem("sendRqstFlg_B1");
		trxOrdNum_B2 = (EZDSStringItem)newItem("trxOrdNum_B2");
		trxLineNum_B2 = (EZDSStringItem)newItem("trxLineNum_B2");
		s80StkStsCd_B1 = (EZDSStringItem)newItem("s80StkStsCd_B1");
		rcvInvtyLocCd_B1 = (EZDSStringItem)newItem("rcvInvtyLocCd_B1");
		ezUpTime_RH = (EZDSStringItem)newItem("ezUpTime_RH");
		ezUpTimeZone_RH = (EZDSStringItem)newItem("ezUpTimeZone_RH");
		ezUpTime_RD = (EZDSStringItem)newItem("ezUpTime_RD");
		ezUpTimeZone_RD = (EZDSStringItem)newItem("ezUpTimeZone_RD");
		ezUpTime_PH = (EZDSStringItem)newItem("ezUpTime_PH");
		ezUpTimeZone_PH = (EZDSStringItem)newItem("ezUpTimeZone_PH");
		ezUpTime_PD = (EZDSStringItem)newItem("ezUpTime_PD");
		ezUpTimeZone_PD = (EZDSStringItem)newItem("ezUpTimeZone_PD");
		dsOrdPosnNum_B1 = (EZDSStringItem)newItem("dsOrdPosnNum_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2030_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2030_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum_B1", "xxNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_BK", "xxChkBox_BK", "BK", null, TYPE_HANKAKUEIJI, "1", null},
	{"rwsStsDescTxt_B1", "rwsStsDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"rwsStsCd_B1", "rwsStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"rwsNum_B1", "rwsNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxCratDt_B1", "xxCratDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"rwsDtlLineNum_B1", "rwsDtlLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhCd_B1", "rtlWhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_B1", "rtlWhNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhCd_B2", "rtlWhCd_B2", "B2", null, TYPE_HANKAKUEISU, "20", null},
	{"xxDplyCtrlFlg_WH", "xxDplyCtrlFlg_WH", "WH", null, TYPE_HANKAKUEISU, "1", null},
	{"invtyOwnrCd_B1", "invtyOwnrCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"rwsRefNum_B1", "rwsRefNum_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"thirdPtyDspTpCd_B1", "thirdPtyDspTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"thirdPtyDspTpCd_B2", "thirdPtyDspTpCd_B2", "B2", null, TYPE_HANKAKUEISU, "2", null},
	{"xxDplyCtrlFlg_TP", "xxDplyCtrlFlg_TP", "TP", null, TYPE_HANKAKUEISU, "1", null},
	{"imptInvBolNum_B1", "imptInvBolNum_B1", "B1", null, TYPE_HANKAKUEISU, "25", null},
	{"sceOrdTpCd_B1", "sceOrdTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"sceOrdTpNm_B1", "sceOrdTpNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"trxOrdNum_B1", "trxOrdNum_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"trxLineNum_B1", "trxLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"trxLineSubNum_B1", "trxLineSubNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"dplyLineNum_B1", "dplyLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"fromLocCd_B1", "fromLocCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_B1", "dsAcctNm_B1", "B1", null, TYPE_HANKAKUEISU, "360", null},
	{"svcConfigMstrPk_B1", "svcConfigMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"flipMdseCd_B1", "flipMdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B1", "mdseDescShortTxt_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"rwsQty_B1", "rwsQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxQty10Num_B1", "xxQty10Num_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rwsPutAwayQty_B1", "rwsPutAwayQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_B1", "serNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_B1", "rtlSwhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_B1", "rtlSwhNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxRtlWhSrchTxt_B1", "xxRtlWhSrchTxt_B1", "B1", null, TYPE_HANKAKUEISU, "1000", null},
	{"serNumTakeFlg_B1", "serNumTakeFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"rwsScrCancAvalFlg_B1", "rwsScrCancAvalFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxStsCd10Txt_B1", "xxStsCd10Txt_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"openFlg_BH", "openFlg_BH", "BH", null, TYPE_HANKAKUEISU, "1", null},
	{"openFlg_BD", "openFlg_BD", "BD", null, TYPE_HANKAKUEISU, "1", null},
	{"rcvFlg_BH", "rcvFlg_BH", "BH", null, TYPE_HANKAKUEISU, "1", null},
	{"rcvFlg_BD", "rcvFlg_BD", "BD", null, TYPE_HANKAKUEISU, "1", null},
	{"poOrdNum_B1", "poOrdNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"poOrdDtlLineNum_B1", "poOrdDtlLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"wmsDropFlg_B1", "wmsDropFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"sendRqstFlg_B1", "sendRqstFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"trxOrdNum_B2", "trxOrdNum_B2", "B2", null, TYPE_HANKAKUEISU, "10", null},
	{"trxLineNum_B2", "trxLineNum_B2", "B2", null, TYPE_HANKAKUEISU, "3", null},
	{"s80StkStsCd_B1", "s80StkStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"rcvInvtyLocCd_B1", "rcvInvtyLocCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"ezUpTime_RH", "ezUpTime_RH", "RH", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_RH", "ezUpTimeZone_RH", "RH", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_RD", "ezUpTime_RD", "RD", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_RD", "ezUpTimeZone_RD", "RD", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_PH", "ezUpTime_PH", "PH", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_PH", "ezUpTimeZone_PH", "PH", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_PD", "ezUpTime_PD", "PD", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_PD", "ezUpTimeZone_PD", "PD", null, TYPE_HANKAKUEISU, "5", null},
	{"dsOrdPosnNum_B1", "dsOrdPosnNum_B1", "B1", null, TYPE_HANKAKUEISU, "6", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_BK
        {"RWS_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStsDescTxt_B1
        {"RWS_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStsCd_B1
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_B1
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_B1
        {"RWS_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsDtlLineNum_B1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_B1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B2
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_WH
        {"INVTY_OWNR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOwnrCd_B1
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_B1
        {"THIRD_PTY_DSP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdPtyDspTpCd_B1
        {"THIRD_PTY_DSP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdPtyDspTpCd_B2
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_TP
        {"IMPT_INV_BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvBolNum_B1
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_B1
        {"SCE_ORD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpNm_B1
        {"TRX_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxOrdNum_B1
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum_B1
        {"TRX_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineSubNum_B1
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_B1
        {"FROM_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromLocCd_B1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_B1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"FLIP_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flipMdseCd_B1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B1
        {"RWS_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsQty_B1
        {"XX_QTY_10_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQty10Num_B1
        {"RWS_PUT_AWAY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsPutAwayQty_B1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_B1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_B1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_B1
        {"XX_RTL_WH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlWhSrchTxt_B1
        {"SER_NUM_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumTakeFlg_B1
        {"RWS_SCR_CANC_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsScrCancAvalFlg_B1
        {"XX_STS_CD_10_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxStsCd10Txt_B1
        {"OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openFlg_BH
        {"OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openFlg_BD
        {"RCV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvFlg_BH
        {"RCV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvFlg_BD
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_B1
        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum_B1
        {"WMS_DROP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDropFlg_B1
        {"SEND_RQST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sendRqstFlg_B1
        {"TRX_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxOrdNum_B2
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum_B2
        {"S80_STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//s80StkStsCd_B1
        {"RCV_INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvInvtyLocCd_B1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_RH
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_RH
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_RD
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_RD
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_PH
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_PH
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_PD
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_PD
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_B1
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

