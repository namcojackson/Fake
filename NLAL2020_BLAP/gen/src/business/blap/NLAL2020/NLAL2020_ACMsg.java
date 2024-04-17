//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230831165134000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2020_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL2020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL2020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2020_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM_A1*/
	public final EZDCBigDecimalItem              xxNum_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** XX_CHK_BOX_A2*/
	public final EZDCStringItem              xxChkBox_A2;

    /** RWS_NUM_A1*/
	public final EZDCStringItem              rwsNum_A1;

    /** RWS_DTL_LINE_NUM_A1*/
	public final EZDCStringItem              rwsDtlLineNum_A1;

    /** RWS_STS_DESC_TXT_A1*/
	public final EZDCStringItem              rwsStsDescTxt_A1;

    /** RTL_WH_NM_A1*/
	public final EZDCStringItem              rtlWhNm_A1;

    /** ORIG_MDSE_CD_A1*/
	public final EZDCStringItem              origMdseCd_A1;

    /** FLIP_MDSE_CD_A1*/
	public final EZDCStringItem              flipMdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDCStringItem              mdseDescShortTxt_A1;

    /** COA_MDSE_TP_CD_A1*/
	public final EZDCStringItem              coaMdseTpCd_A1;

    /** RWS_QTY_A1*/
	public final EZDCBigDecimalItem              rwsQty_A1;

    /** RWS_STK_QTY_A1*/
	public final EZDCBigDecimalItem              rwsStkQty_A1;

    /** RTL_WH_CD_A1*/
	public final EZDCStringItem              rtlWhCd_A1;

    /** RMA_SLS_WH_NM_A1*/
	public final EZDCStringItem              rmaSlsWhNm_A1;

    /** INVTY_STK_STS_CD_A1*/
	public final EZDCStringItem              invtyStkStsCd_A1;

    /** PO_BAL_QTY_A1*/
	public final EZDCBigDecimalItem              poBalQty_A1;

    /** SER_NUM_A1*/
	public final EZDCStringItem              serNum_A1;

    /** XX_SER_NUM_SRCH_TXT_A1*/
	public final EZDCStringItem              xxSerNumSrchTxt_A1;

    /** SVC_CONFIG_MSTR_PK_A1*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_A1;

    /** RTL_SWH_CD_A1*/
	public final EZDCStringItem              rtlSwhCd_A1;

    /** SHIP_LOC_CD_A1*/
	public final EZDCStringItem              shipLocCd_A1;

    /** SHIP_FROM_ACCT_NM_A1*/
	public final EZDCStringItem              shipFromAcctNm_A1;

    /** FROM_LOC_CTY_ADDR_A1*/
	public final EZDCStringItem              fromLocCtyAddr_A1;

    /** RWS_REF_NUM_A1*/
	public final EZDCStringItem              rwsRefNum_A1;

    /** IMPT_INV_BOL_NUM_A1*/
	public final EZDCStringItem              imptInvBolNum_A1;

    /** SCE_ORD_TP_CD_A1*/
	public final EZDCStringItem              sceOrdTpCd_A1;

    /** SCE_ORD_TP_NM_A1*/
	public final EZDCStringItem              sceOrdTpNm_A1;

    /** SRC_ORD_NUM_A1*/
	public final EZDCStringItem              srcOrdNum_A1;

    /** DPLY_LINE_NUM_A1*/
	public final EZDCStringItem              dplyLineNum_A1;

    /** SVC_LVL_DESC_TXT_A1*/
	public final EZDCStringItem              svcLvlDescTxt_A1;

    /** CARR_NM_A1*/
	public final EZDCStringItem              carrNm_A1;

    /** MDSE_CD_A1*/
	public final EZDCStringItem              mdseCd_A1;

    /** DS_ORD_POSN_NUM_A1*/
	public final EZDCStringItem              dsOrdPosnNum_A1;

    /** CARR_CD_A1*/
	public final EZDCStringItem              carrCd_A1;

    /** SHIP_TO_RTL_WH_CD_A1*/
	public final EZDCStringItem              shipToRtlWhCd_A1;

    /** RCV_INVTY_LOC_CD_A1*/
	public final EZDCStringItem              rcvInvtyLocCd_A1;

    /** INVTY_ACCT_CD_A1*/
	public final EZDCStringItem              invtyAcctCd_A1;

    /** INVTY_OWNR_CD_A1*/
	public final EZDCStringItem              invtyOwnrCd_A1;

    /** WH_OWNR_CD_A1*/
	public final EZDCStringItem              whOwnrCd_A1;

    /** SHIP_FROM_INVTY_LOC_CD_A1*/
	public final EZDCStringItem              shipFromInvtyLocCd_A1;

    /** PO_BAL_QTY_A2*/
	public final EZDCBigDecimalItem              poBalQty_A2;

    /** RWS_OPEN_FLG_A1*/
	public final EZDCStringItem              rwsOpenFlg_A1;

    /** OPEN_FLG_A1*/
	public final EZDCStringItem              openFlg_A1;

    /** CMPY_INVTY_FLG_A1*/
	public final EZDCStringItem              cmpyInvtyFlg_A1;

    /** INSTL_BASE_CTRL_FLG_A1*/
	public final EZDCStringItem              instlBaseCtrlFlg_A1;

    /** XX_PRTL_ACPT_FLG_A1*/
	public final EZDCStringItem              xxPrtlAcptFlg_A1;

    /** SER_NUM_TAKE_FLG_A1*/
	public final EZDCStringItem              serNumTakeFlg_A1;

    /** XX_PUT_AWAY_FLG_A1*/
	public final EZDCStringItem              xxPutAwayFlg_A1;

    /** SVC_MACH_MSTR_PK_A1*/
	public final EZDCBigDecimalItem              svcMachMstrPk_A1;

    /** MDL_ID_A1*/
	public final EZDCBigDecimalItem              mdlId_A1;

    /** TO_LOC_TP_CD_A1*/
	public final EZDCStringItem              toLocTpCd_A1;

    /** DEST_INVTY_LOC_CD_A1*/
	public final EZDCStringItem              destInvtyLocCd_A1;

    /** XX_TO_INVTY_LOC_NM_A1*/
	public final EZDCStringItem              xxToInvtyLocNm_A1;

    /** INVTY_ORD_LINE_NUM_A1*/
	public final EZDCStringItem              invtyOrdLineNum_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDCStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDCStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDCStringItem              xxRecHistTblNm_A1;

    /** XX_EDT_MODE_FLG_A1*/
	public final EZDCStringItem              xxEdtModeFlg_A1;

    /** MTR_REQ_MDL_FLG_A1*/
	public final EZDCStringItem              mtrReqMdlFlg_A1;


	/**
	 * NLAL2020_ACMsg is constructor.
	 * The initialization when the instance of NLAL2020_ACMsg is generated.
	 */
	public NLAL2020_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2020_ACMsg is constructor.
	 * The initialization when the instance of NLAL2020_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2020_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum_A1 = (EZDCBigDecimalItem)newItem("xxNum_A1");
		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		xxChkBox_A2 = (EZDCStringItem)newItem("xxChkBox_A2");
		rwsNum_A1 = (EZDCStringItem)newItem("rwsNum_A1");
		rwsDtlLineNum_A1 = (EZDCStringItem)newItem("rwsDtlLineNum_A1");
		rwsStsDescTxt_A1 = (EZDCStringItem)newItem("rwsStsDescTxt_A1");
		rtlWhNm_A1 = (EZDCStringItem)newItem("rtlWhNm_A1");
		origMdseCd_A1 = (EZDCStringItem)newItem("origMdseCd_A1");
		flipMdseCd_A1 = (EZDCStringItem)newItem("flipMdseCd_A1");
		mdseDescShortTxt_A1 = (EZDCStringItem)newItem("mdseDescShortTxt_A1");
		coaMdseTpCd_A1 = (EZDCStringItem)newItem("coaMdseTpCd_A1");
		rwsQty_A1 = (EZDCBigDecimalItem)newItem("rwsQty_A1");
		rwsStkQty_A1 = (EZDCBigDecimalItem)newItem("rwsStkQty_A1");
		rtlWhCd_A1 = (EZDCStringItem)newItem("rtlWhCd_A1");
		rmaSlsWhNm_A1 = (EZDCStringItem)newItem("rmaSlsWhNm_A1");
		invtyStkStsCd_A1 = (EZDCStringItem)newItem("invtyStkStsCd_A1");
		poBalQty_A1 = (EZDCBigDecimalItem)newItem("poBalQty_A1");
		serNum_A1 = (EZDCStringItem)newItem("serNum_A1");
		xxSerNumSrchTxt_A1 = (EZDCStringItem)newItem("xxSerNumSrchTxt_A1");
		svcConfigMstrPk_A1 = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_A1");
		rtlSwhCd_A1 = (EZDCStringItem)newItem("rtlSwhCd_A1");
		shipLocCd_A1 = (EZDCStringItem)newItem("shipLocCd_A1");
		shipFromAcctNm_A1 = (EZDCStringItem)newItem("shipFromAcctNm_A1");
		fromLocCtyAddr_A1 = (EZDCStringItem)newItem("fromLocCtyAddr_A1");
		rwsRefNum_A1 = (EZDCStringItem)newItem("rwsRefNum_A1");
		imptInvBolNum_A1 = (EZDCStringItem)newItem("imptInvBolNum_A1");
		sceOrdTpCd_A1 = (EZDCStringItem)newItem("sceOrdTpCd_A1");
		sceOrdTpNm_A1 = (EZDCStringItem)newItem("sceOrdTpNm_A1");
		srcOrdNum_A1 = (EZDCStringItem)newItem("srcOrdNum_A1");
		dplyLineNum_A1 = (EZDCStringItem)newItem("dplyLineNum_A1");
		svcLvlDescTxt_A1 = (EZDCStringItem)newItem("svcLvlDescTxt_A1");
		carrNm_A1 = (EZDCStringItem)newItem("carrNm_A1");
		mdseCd_A1 = (EZDCStringItem)newItem("mdseCd_A1");
		dsOrdPosnNum_A1 = (EZDCStringItem)newItem("dsOrdPosnNum_A1");
		carrCd_A1 = (EZDCStringItem)newItem("carrCd_A1");
		shipToRtlWhCd_A1 = (EZDCStringItem)newItem("shipToRtlWhCd_A1");
		rcvInvtyLocCd_A1 = (EZDCStringItem)newItem("rcvInvtyLocCd_A1");
		invtyAcctCd_A1 = (EZDCStringItem)newItem("invtyAcctCd_A1");
		invtyOwnrCd_A1 = (EZDCStringItem)newItem("invtyOwnrCd_A1");
		whOwnrCd_A1 = (EZDCStringItem)newItem("whOwnrCd_A1");
		shipFromInvtyLocCd_A1 = (EZDCStringItem)newItem("shipFromInvtyLocCd_A1");
		poBalQty_A2 = (EZDCBigDecimalItem)newItem("poBalQty_A2");
		rwsOpenFlg_A1 = (EZDCStringItem)newItem("rwsOpenFlg_A1");
		openFlg_A1 = (EZDCStringItem)newItem("openFlg_A1");
		cmpyInvtyFlg_A1 = (EZDCStringItem)newItem("cmpyInvtyFlg_A1");
		instlBaseCtrlFlg_A1 = (EZDCStringItem)newItem("instlBaseCtrlFlg_A1");
		xxPrtlAcptFlg_A1 = (EZDCStringItem)newItem("xxPrtlAcptFlg_A1");
		serNumTakeFlg_A1 = (EZDCStringItem)newItem("serNumTakeFlg_A1");
		xxPutAwayFlg_A1 = (EZDCStringItem)newItem("xxPutAwayFlg_A1");
		svcMachMstrPk_A1 = (EZDCBigDecimalItem)newItem("svcMachMstrPk_A1");
		mdlId_A1 = (EZDCBigDecimalItem)newItem("mdlId_A1");
		toLocTpCd_A1 = (EZDCStringItem)newItem("toLocTpCd_A1");
		destInvtyLocCd_A1 = (EZDCStringItem)newItem("destInvtyLocCd_A1");
		xxToInvtyLocNm_A1 = (EZDCStringItem)newItem("xxToInvtyLocNm_A1");
		invtyOrdLineNum_A1 = (EZDCStringItem)newItem("invtyOrdLineNum_A1");
		xxRecHistCratTs_A1 = (EZDCStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDCStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDCStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDCStringItem)newItem("xxRecHistTblNm_A1");
		xxEdtModeFlg_A1 = (EZDCStringItem)newItem("xxEdtModeFlg_A1");
		mtrReqMdlFlg_A1 = (EZDCStringItem)newItem("mtrReqMdlFlg_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2020_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2020_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum_A1", "xxNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"rwsNum_A1", "rwsNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"rwsDtlLineNum_A1", "rwsDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"rwsStsDescTxt_A1", "rwsStsDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"origMdseCd_A1", "origMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"flipMdseCd_A1", "flipMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"coaMdseTpCd_A1", "coaMdseTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"rwsQty_A1", "rwsQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rwsStkQty_A1", "rwsStkQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rmaSlsWhNm_A1", "rmaSlsWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"invtyStkStsCd_A1", "invtyStkStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"poBalQty_A1", "poBalQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxSerNumSrchTxt_A1", "xxSerNumSrchTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	{"svcConfigMstrPk_A1", "svcConfigMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rtlSwhCd_A1", "rtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipLocCd_A1", "shipLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"shipFromAcctNm_A1", "shipFromAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"fromLocCtyAddr_A1", "fromLocCtyAddr_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"rwsRefNum_A1", "rwsRefNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"imptInvBolNum_A1", "imptInvBolNum_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"sceOrdTpCd_A1", "sceOrdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"sceOrdTpNm_A1", "sceOrdTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"srcOrdNum_A1", "srcOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"dplyLineNum_A1", "dplyLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"svcLvlDescTxt_A1", "svcLvlDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"carrNm_A1", "carrNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"dsOrdPosnNum_A1", "dsOrdPosnNum_A1", "A1", null, TYPE_HANKAKUEISU, "6", null},
	{"carrCd_A1", "carrCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToRtlWhCd_A1", "shipToRtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rcvInvtyLocCd_A1", "rcvInvtyLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyAcctCd_A1", "invtyAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"invtyOwnrCd_A1", "invtyOwnrCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"whOwnrCd_A1", "whOwnrCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"shipFromInvtyLocCd_A1", "shipFromInvtyLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"poBalQty_A2", "poBalQty_A2", "A2", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rwsOpenFlg_A1", "rwsOpenFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"openFlg_A1", "openFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"cmpyInvtyFlg_A1", "cmpyInvtyFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"instlBaseCtrlFlg_A1", "instlBaseCtrlFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPrtlAcptFlg_A1", "xxPrtlAcptFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"serNumTakeFlg_A1", "serNumTakeFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPutAwayFlg_A1", "xxPutAwayFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"svcMachMstrPk_A1", "svcMachMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId_A1", "mdlId_A1", "A1", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"toLocTpCd_A1", "toLocTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"destInvtyLocCd_A1", "destInvtyLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxToInvtyLocNm_A1", "xxToInvtyLocNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"invtyOrdLineNum_A1", "invtyOrdLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxEdtModeFlg_A1", "xxEdtModeFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"mtrReqMdlFlg_A1", "mtrReqMdlFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_A1
        {"RWS_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsDtlLineNum_A1
        {"RWS_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStsDescTxt_A1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"ORIG_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origMdseCd_A1
        {"FLIP_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flipMdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_A1
        {"RWS_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsQty_A1
        {"RWS_STK_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStkQty_A1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RMA_SLS_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmaSlsWhNm_A1
        {"INVTY_STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyStkStsCd_A1
        {"PO_BAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poBalQty_A1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"XX_SER_NUM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSerNumSrchTxt_A1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A1
        {"SHIP_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipLocCd_A1
        {"SHIP_FROM_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFromAcctNm_A1
        {"FROM_LOC_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromLocCtyAddr_A1
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_A1
        {"IMPT_INV_BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvBolNum_A1
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_A1
        {"SCE_ORD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpNm_A1
        {"SRC_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcOrdNum_A1
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_A1
        {"SVC_LVL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLvlDescTxt_A1
        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_A1
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_A1
        {"SHIP_TO_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToRtlWhCd_A1
        {"RCV_INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvInvtyLocCd_A1
        {"INVTY_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAcctCd_A1
        {"INVTY_OWNR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOwnrCd_A1
        {"WH_OWNR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whOwnrCd_A1
        {"SHIP_FROM_INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFromInvtyLocCd_A1
        {"PO_BAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poBalQty_A2
        {"RWS_OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsOpenFlg_A1
        {"OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openFlg_A1
        {"CMPY_INVTY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpyInvtyFlg_A1
        {"INSTL_BASE_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//instlBaseCtrlFlg_A1
        {"XX_PRTL_ACPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPrtlAcptFlg_A1
        {"SER_NUM_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumTakeFlg_A1
        {"XX_PUT_AWAY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPutAwayFlg_A1
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A1
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_A1
        {"TO_LOC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toLocTpCd_A1
        {"DEST_INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destInvtyLocCd_A1
        {"XX_TO_INVTY_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxToInvtyLocNm_A1
        {"INVTY_ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdLineNum_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
        {"XX_EDT_MODE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtModeFlg_A1
        {"MTR_REQ_MDL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReqMdlFlg_A1
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

