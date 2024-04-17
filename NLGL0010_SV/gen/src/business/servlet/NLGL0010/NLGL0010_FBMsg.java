//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170703172120000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0010_FBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLGL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0010 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0010_FBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WMS_REC_ID_F1*/
	public final EZDBStringItem              wmsRecId_F1;

    /** WH_CD_F1*/
	public final EZDBStringItem              whCd_F1;

    /** OTBD_ORD_LINE_NUM_F1*/
	public final EZDBBigDecimalItem              otbdOrdLineNum_F1;

    /** INBD_ORD_LINE_NUM_F1*/
	public final EZDBBigDecimalItem              inbdOrdLineNum_F1;

    /** WMS_TASK_CD_F1*/
	public final EZDBStringItem              wmsTaskCd_F1;

    /** WMS_ORD_STS_CD_F1*/
	public final EZDBStringItem              wmsOrdStsCd_F1;

    /** WMS_MDSE_CD_F1*/
	public final EZDBStringItem              wmsMdseCd_F1;

    /** WMS_STK_STS_CD_F1*/
	public final EZDBStringItem              wmsStkStsCd_F1;

    /** WMS_ORD_QTY_F1*/
	public final EZDBBigDecimalItem              wmsOrdQty_F1;

    /** WMS_OP_ID_F1*/
	public final EZDBStringItem              wmsOpId_F1;

    /** USR_ID_F1*/
	public final EZDBStringItem              usrId_F1;

    /** OTBD_ORD_TP_CD_F1*/
	public final EZDBStringItem              otbdOrdTpCd_F1;

    /** OTBD_ORD_NUM_F1*/
	public final EZDBStringItem              otbdOrdNum_F1;

    /** INBD_ORD_TP_CD_F1*/
	public final EZDBStringItem              inbdOrdTpCd_F1;

    /** INBD_ORD_NUM_F1*/
	public final EZDBStringItem              inbdOrdNum_F1;

    /** WMS_TRX_DT_TM_TS_F1*/
	public final EZDBStringItem              wmsTrxDtTmTs_F1;

    /** XX_DT_TM_F1*/
	public final EZDBStringItem              xxDtTm_F1;

    /** WMS_WAVE_ID_F1*/
	public final EZDBStringItem              wmsWaveId_F1;

    /** WMS_TOT_WT_F1*/
	public final EZDBBigDecimalItem              wmsTotWt_F1;

    /** WMS_CARR_CD_F1*/
	public final EZDBStringItem              wmsCarrCd_F1;

    /** WMS_TRAIL_ID_F1*/
	public final EZDBStringItem              wmsTrailId_F1;

    /** PRO_NUM_F1*/
	public final EZDBStringItem              proNum_F1;

    /** BOL_NUM_F1*/
	public final EZDBStringItem              bolNum_F1;

    /** WMS_GRP_ID_F1*/
	public final EZDBStringItem              wmsGrpId_F1;

    /** WMS_GRP_ID_F2*/
	public final EZDBStringItem              wmsGrpId_F2;

    /** WMS_SHIP_ID_F1*/
	public final EZDBStringItem              wmsShipId_F1;

    /** WMS_FRT_TERM_CD_F1*/
	public final EZDBStringItem              wmsFrtTermCd_F1;

    /** WMS_LOT_NUM_F1*/
	public final EZDBStringItem              wmsLotNum_F1;

    /** WMS_TAG_ID_F1*/
	public final EZDBStringItem              wmsTagId_F1;

    /** MOD_BY_TXT_F1*/
	public final EZDBStringItem              modByTxt_F1;

    /** WMS_CNTNR_ID_F1*/
	public final EZDBStringItem              wmsCntnrId_F1;

    /** WMS_OUT_CNTNR_NUM_F1*/
	public final EZDBStringItem              wmsOutCntnrNum_F1;

    /** WMS_FRT_CHRG_AMT_F1*/
	public final EZDBBigDecimalItem              wmsFrtChrgAmt_F1;

    /** SER_NUM_F1*/
	public final EZDBStringItem              serNum_F1;

    /** SHIP_UNIT_WT_F1*/
	public final EZDBBigDecimalItem              shipUnitWt_F1;

    /** TMS_FRT_CHRG_AMT_F1*/
	public final EZDBBigDecimalItem              tmsFrtChrgAmt_F1;

    /** TMS_FRT_CHRG_COST_AMT_F1*/
	public final EZDBBigDecimalItem              tmsFrtChrgCostAmt_F1;

    /** EST_DOCK_DT_TM_TS_F1*/
	public final EZDBStringItem              estDockDtTmTs_F1;

    /** XX_DT_TM_F2*/
	public final EZDBStringItem              xxDtTm_F2;

    /** _EZRegistrationDateTime_F1*/
	public final EZDBStringItem              ezInTime_F1;

    /** XX_DT_TM_F3*/
	public final EZDBStringItem              xxDtTm_F3;


	/**
	 * NLGL0010_FBMsg is constructor.
	 * The initialization when the instance of NLGL0010_FBMsg is generated.
	 */
	public NLGL0010_FBMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0010_FBMsg is constructor.
	 * The initialization when the instance of NLGL0010_FBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0010_FBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wmsRecId_F1 = (EZDBStringItem)newItem("wmsRecId_F1");
		whCd_F1 = (EZDBStringItem)newItem("whCd_F1");
		otbdOrdLineNum_F1 = (EZDBBigDecimalItem)newItem("otbdOrdLineNum_F1");
		inbdOrdLineNum_F1 = (EZDBBigDecimalItem)newItem("inbdOrdLineNum_F1");
		wmsTaskCd_F1 = (EZDBStringItem)newItem("wmsTaskCd_F1");
		wmsOrdStsCd_F1 = (EZDBStringItem)newItem("wmsOrdStsCd_F1");
		wmsMdseCd_F1 = (EZDBStringItem)newItem("wmsMdseCd_F1");
		wmsStkStsCd_F1 = (EZDBStringItem)newItem("wmsStkStsCd_F1");
		wmsOrdQty_F1 = (EZDBBigDecimalItem)newItem("wmsOrdQty_F1");
		wmsOpId_F1 = (EZDBStringItem)newItem("wmsOpId_F1");
		usrId_F1 = (EZDBStringItem)newItem("usrId_F1");
		otbdOrdTpCd_F1 = (EZDBStringItem)newItem("otbdOrdTpCd_F1");
		otbdOrdNum_F1 = (EZDBStringItem)newItem("otbdOrdNum_F1");
		inbdOrdTpCd_F1 = (EZDBStringItem)newItem("inbdOrdTpCd_F1");
		inbdOrdNum_F1 = (EZDBStringItem)newItem("inbdOrdNum_F1");
		wmsTrxDtTmTs_F1 = (EZDBStringItem)newItem("wmsTrxDtTmTs_F1");
		xxDtTm_F1 = (EZDBStringItem)newItem("xxDtTm_F1");
		wmsWaveId_F1 = (EZDBStringItem)newItem("wmsWaveId_F1");
		wmsTotWt_F1 = (EZDBBigDecimalItem)newItem("wmsTotWt_F1");
		wmsCarrCd_F1 = (EZDBStringItem)newItem("wmsCarrCd_F1");
		wmsTrailId_F1 = (EZDBStringItem)newItem("wmsTrailId_F1");
		proNum_F1 = (EZDBStringItem)newItem("proNum_F1");
		bolNum_F1 = (EZDBStringItem)newItem("bolNum_F1");
		wmsGrpId_F1 = (EZDBStringItem)newItem("wmsGrpId_F1");
		wmsGrpId_F2 = (EZDBStringItem)newItem("wmsGrpId_F2");
		wmsShipId_F1 = (EZDBStringItem)newItem("wmsShipId_F1");
		wmsFrtTermCd_F1 = (EZDBStringItem)newItem("wmsFrtTermCd_F1");
		wmsLotNum_F1 = (EZDBStringItem)newItem("wmsLotNum_F1");
		wmsTagId_F1 = (EZDBStringItem)newItem("wmsTagId_F1");
		modByTxt_F1 = (EZDBStringItem)newItem("modByTxt_F1");
		wmsCntnrId_F1 = (EZDBStringItem)newItem("wmsCntnrId_F1");
		wmsOutCntnrNum_F1 = (EZDBStringItem)newItem("wmsOutCntnrNum_F1");
		wmsFrtChrgAmt_F1 = (EZDBBigDecimalItem)newItem("wmsFrtChrgAmt_F1");
		serNum_F1 = (EZDBStringItem)newItem("serNum_F1");
		shipUnitWt_F1 = (EZDBBigDecimalItem)newItem("shipUnitWt_F1");
		tmsFrtChrgAmt_F1 = (EZDBBigDecimalItem)newItem("tmsFrtChrgAmt_F1");
		tmsFrtChrgCostAmt_F1 = (EZDBBigDecimalItem)newItem("tmsFrtChrgCostAmt_F1");
		estDockDtTmTs_F1 = (EZDBStringItem)newItem("estDockDtTmTs_F1");
		xxDtTm_F2 = (EZDBStringItem)newItem("xxDtTm_F2");
		ezInTime_F1 = (EZDBStringItem)newItem("ezInTime_F1");
		xxDtTm_F3 = (EZDBStringItem)newItem("xxDtTm_F3");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0010_FBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0010_FBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wmsRecId_F1", "wmsRecId_F1", "F1", null, TYPE_HANKAKUEISU, "28", null},
	{"whCd_F1", "whCd_F1", "F1", null, TYPE_HANKAKUEISU, "20", null},
	{"otbdOrdLineNum_F1", "otbdOrdLineNum_F1", "F1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"inbdOrdLineNum_F1", "inbdOrdLineNum_F1", "F1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wmsTaskCd_F1", "wmsTaskCd_F1", "F1", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsOrdStsCd_F1", "wmsOrdStsCd_F1", "F1", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsMdseCd_F1", "wmsMdseCd_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsStkStsCd_F1", "wmsStkStsCd_F1", "F1", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsOrdQty_F1", "wmsOrdQty_F1", "F1", null, TYPE_SEISU_SYOSU, "12", "3"},
	{"wmsOpId_F1", "wmsOpId_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"usrId_F1", "usrId_F1", "F1", null, TYPE_HANKAKUEISU, "16", null},
	{"otbdOrdTpCd_F1", "otbdOrdTpCd_F1", "F1", null, TYPE_HANKAKUEISU, "4", null},
	{"otbdOrdNum_F1", "otbdOrdNum_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"inbdOrdTpCd_F1", "inbdOrdTpCd_F1", "F1", null, TYPE_HANKAKUEISU, "4", null},
	{"inbdOrdNum_F1", "inbdOrdNum_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsTrxDtTmTs_F1", "wmsTrxDtTmTs_F1", "F1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_F1", "xxDtTm_F1", "F1", null, TYPE_HANKAKUEISU, "23", null},
	{"wmsWaveId_F1", "wmsWaveId_F1", "F1", null, TYPE_HANKAKUEISU, "10", null},
	{"wmsTotWt_F1", "wmsTotWt_F1", "F1", null, TYPE_SEISU_SYOSU, "12", "3"},
	{"wmsCarrCd_F1", "wmsCarrCd_F1", "F1", null, TYPE_HANKAKUEISU, "40", null},
	{"wmsTrailId_F1", "wmsTrailId_F1", "F1", null, TYPE_HANKAKUEISU, "10", null},
	{"proNum_F1", "proNum_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"bolNum_F1", "bolNum_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsGrpId_F1", "wmsGrpId_F1", "F1", null, TYPE_HANKAKUEISU, "8", null},
	{"wmsGrpId_F2", "wmsGrpId_F2", "F2", null, TYPE_HANKAKUEISU, "8", null},
	{"wmsShipId_F1", "wmsShipId_F1", "F1", null, TYPE_HANKAKUEISU, "10", null},
	{"wmsFrtTermCd_F1", "wmsFrtTermCd_F1", "F1", null, TYPE_HANKAKUEISU, "2", null},
	{"wmsLotNum_F1", "wmsLotNum_F1", "F1", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsTagId_F1", "wmsTagId_F1", "F1", null, TYPE_HANKAKUEISU, "10", null},
	{"modByTxt_F1", "modByTxt_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsCntnrId_F1", "wmsCntnrId_F1", "F1", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsOutCntnrNum_F1", "wmsOutCntnrNum_F1", "F1", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsFrtChrgAmt_F1", "wmsFrtChrgAmt_F1", "F1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"serNum_F1", "serNum_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"shipUnitWt_F1", "shipUnitWt_F1", "F1", null, TYPE_SEISU_SYOSU, "12", "3"},
	{"tmsFrtChrgAmt_F1", "tmsFrtChrgAmt_F1", "F1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"tmsFrtChrgCostAmt_F1", "tmsFrtChrgCostAmt_F1", "F1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"estDockDtTmTs_F1", "estDockDtTmTs_F1", "F1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_F2", "xxDtTm_F2", "F2", null, TYPE_HANKAKUEISU, "23", null},
	{"ezInTime_F1", "ezInTime_F1", "F1", null, TYPE_HANKAKUEISU, "17", null},
	{"xxDtTm_F3", "xxDtTm_F3", "F3", null, TYPE_HANKAKUEISU, "23", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WMS_REC_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsRecId_F1
        {"WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_F1
        {"OTBD_ORD_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otbdOrdLineNum_F1
        {"INBD_ORD_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOrdLineNum_F1
        {"WMS_TASK_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTaskCd_F1
        {"WMS_ORD_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrdStsCd_F1
        {"WMS_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseCd_F1
        {"WMS_STK_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsStkStsCd_F1
        {"WMS_ORD_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrdQty_F1
        {"WMS_OP_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOpId_F1
        {"USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usrId_F1
        {"OTBD_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otbdOrdTpCd_F1
        {"OTBD_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otbdOrdNum_F1
        {"INBD_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOrdTpCd_F1
        {"INBD_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOrdNum_F1
        {"WMS_TRX_DT_TM_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTrxDtTmTs_F1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_F1
        {"WMS_WAVE_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsWaveId_F1
        {"WMS_TOT_WT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//wmsTotWt_F1
        {"WMS_CARR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCarrCd_F1
        {"WMS_TRAIL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTrailId_F1
        {"PRO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_F1
        {"BOL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bolNum_F1
        {"WMS_GRP_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsGrpId_F1
        {"WMS_GRP_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsGrpId_F2
        {"WMS_SHIP_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsShipId_F1
        {"WMS_FRT_TERM_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsFrtTermCd_F1
        {"WMS_LOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsLotNum_F1
        {"WMS_TAG_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTagId_F1
        {"MOD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//modByTxt_F1
        {"WMS_CNTNR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCntnrId_F1
        {"WMS_OUT_CNTNR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOutCntnrNum_F1
        {"WMS_FRT_CHRG_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//wmsFrtChrgAmt_F1
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_F1
        {"SHIP_UNIT_WT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//shipUnitWt_F1
        {"TMS_FRT_CHRG_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//tmsFrtChrgAmt_F1
        {"TMS_FRT_CHRG_COST_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//tmsFrtChrgCostAmt_F1
        {"EST_DOCK_DT_TM_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estDockDtTmTs_F1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_F2
        {"_EZRegistrationDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_F1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_F3
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
