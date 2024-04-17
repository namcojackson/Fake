//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170703164352000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0010_OSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0010 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0010_OSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_O1*/
	public final EZDSStringItem              xxChkBox_O1;

    /** XX_EXST_FLG_O1*/
	public final EZDSStringItem              xxExstFlg_O1;

    /** WMS_UPD_HIST_NUM_O1*/
	public final EZDSBigDecimalItem              wmsUpdHistNum_O1;

    /** FILL_30_TXT_O1*/
	public final EZDSStringItem              fill30Txt_O1;

    /** WMS_REC_ID_O1*/
	public final EZDSStringItem              wmsRecId_O1;

    /** OTBD_ORD_LINE_NUM_O1*/
	public final EZDSBigDecimalItem              otbdOrdLineNum_O1;

    /** INBD_ORD_LINE_NUM_O1*/
	public final EZDSBigDecimalItem              inbdOrdLineNum_O1;

    /** WMS_TASK_CD_O2*/
	public final EZDSStringItem              wmsTaskCd_O2;

    /** WMS_ORD_STS_CD_O2*/
	public final EZDSStringItem              wmsOrdStsCd_O2;

    /** WMS_MDSE_CD_O1*/
	public final EZDSStringItem              wmsMdseCd_O1;

    /** WMS_STK_STS_CD_O2*/
	public final EZDSStringItem              wmsStkStsCd_O2;

    /** WMS_ORD_QTY_O1*/
	public final EZDSBigDecimalItem              wmsOrdQty_O1;

    /** OTBD_ORD_TP_CD_O2*/
	public final EZDSStringItem              otbdOrdTpCd_O2;

    /** OTBD_ORD_NUM_O1*/
	public final EZDSStringItem              otbdOrdNum_O1;

    /** INBD_ORD_TP_CD_O2*/
	public final EZDSStringItem              inbdOrdTpCd_O2;

    /** INBD_ORD_NUM_O1*/
	public final EZDSStringItem              inbdOrdNum_O1;

    /** WMS_TRX_DT_TM_TS_O1*/
	public final EZDSStringItem              wmsTrxDtTmTs_O1;

    /** XX_DT_TM_O1*/
	public final EZDSStringItem              xxDtTm_O1;

    /** WMS_TOT_WT_O1*/
	public final EZDSBigDecimalItem              wmsTotWt_O1;

    /** WMS_CARR_CD_O1*/
	public final EZDSStringItem              wmsCarrCd_O1;

    /** WMS_TRAIL_ID_O1*/
	public final EZDSStringItem              wmsTrailId_O1;

    /** PRO_NUM_O1*/
	public final EZDSStringItem              proNum_O1;

    /** BOL_NUM_O1*/
	public final EZDSStringItem              bolNum_O1;

    /** WMS_SHIP_ID_O1*/
	public final EZDSStringItem              wmsShipId_O1;

    /** WMS_CNTNR_ID_O1*/
	public final EZDSStringItem              wmsCntnrId_O1;

    /** WMS_OUT_CNTNR_NUM_O1*/
	public final EZDSStringItem              wmsOutCntnrNum_O1;

    /** WMS_FRT_CHRG_AMT_O1*/
	public final EZDSBigDecimalItem              wmsFrtChrgAmt_O1;

    /** SER_NUM_O1*/
	public final EZDSStringItem              serNum_O1;

    /** EST_DOCK_DT_TM_TS_O1*/
	public final EZDSStringItem              estDockDtTmTs_O1;

    /** XX_DT_10_DT_O1*/
	public final EZDSDateItem              xxDt10Dt_O1;

    /** _EZRegistrationDateTime_O1*/
	public final EZDSStringItem              ezInTime_O1;

    /** XX_DT_TM_O2*/
	public final EZDSStringItem              xxDtTm_O2;

    /** TMS_FRT_CHRG_AMT_O1*/
	public final EZDSBigDecimalItem              tmsFrtChrgAmt_O1;

    /** WMS_ORG_HOST_ID_O1*/
	public final EZDSStringItem              wmsOrgHostId_O1;

    /** _EZCancelFlag_O1*/
	public final EZDSStringItem              ezCancelFlag_O1;

    /** _EZInTimeZone_O1*/
	public final EZDSStringItem              ezInTimeZone_O1;

    /** WMS_INBD_TRX_PK_O1*/
	public final EZDSBigDecimalItem              wmsInbdTrxPk_O1;

    /** TMS_TRK_NUM_O1*/
	public final EZDSStringItem              tmsTrkNum_O1;

    /** PACK_CD_TXT_O1*/
	public final EZDSStringItem              packCdTxt_O1;


	/**
	 * NLGL0010_OSMsg is constructor.
	 * The initialization when the instance of NLGL0010_OSMsg is generated.
	 */
	public NLGL0010_OSMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0010_OSMsg is constructor.
	 * The initialization when the instance of NLGL0010_OSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0010_OSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_O1 = (EZDSStringItem)newItem("xxChkBox_O1");
		xxExstFlg_O1 = (EZDSStringItem)newItem("xxExstFlg_O1");
		wmsUpdHistNum_O1 = (EZDSBigDecimalItem)newItem("wmsUpdHistNum_O1");
		fill30Txt_O1 = (EZDSStringItem)newItem("fill30Txt_O1");
		wmsRecId_O1 = (EZDSStringItem)newItem("wmsRecId_O1");
		otbdOrdLineNum_O1 = (EZDSBigDecimalItem)newItem("otbdOrdLineNum_O1");
		inbdOrdLineNum_O1 = (EZDSBigDecimalItem)newItem("inbdOrdLineNum_O1");
		wmsTaskCd_O2 = (EZDSStringItem)newItem("wmsTaskCd_O2");
		wmsOrdStsCd_O2 = (EZDSStringItem)newItem("wmsOrdStsCd_O2");
		wmsMdseCd_O1 = (EZDSStringItem)newItem("wmsMdseCd_O1");
		wmsStkStsCd_O2 = (EZDSStringItem)newItem("wmsStkStsCd_O2");
		wmsOrdQty_O1 = (EZDSBigDecimalItem)newItem("wmsOrdQty_O1");
		otbdOrdTpCd_O2 = (EZDSStringItem)newItem("otbdOrdTpCd_O2");
		otbdOrdNum_O1 = (EZDSStringItem)newItem("otbdOrdNum_O1");
		inbdOrdTpCd_O2 = (EZDSStringItem)newItem("inbdOrdTpCd_O2");
		inbdOrdNum_O1 = (EZDSStringItem)newItem("inbdOrdNum_O1");
		wmsTrxDtTmTs_O1 = (EZDSStringItem)newItem("wmsTrxDtTmTs_O1");
		xxDtTm_O1 = (EZDSStringItem)newItem("xxDtTm_O1");
		wmsTotWt_O1 = (EZDSBigDecimalItem)newItem("wmsTotWt_O1");
		wmsCarrCd_O1 = (EZDSStringItem)newItem("wmsCarrCd_O1");
		wmsTrailId_O1 = (EZDSStringItem)newItem("wmsTrailId_O1");
		proNum_O1 = (EZDSStringItem)newItem("proNum_O1");
		bolNum_O1 = (EZDSStringItem)newItem("bolNum_O1");
		wmsShipId_O1 = (EZDSStringItem)newItem("wmsShipId_O1");
		wmsCntnrId_O1 = (EZDSStringItem)newItem("wmsCntnrId_O1");
		wmsOutCntnrNum_O1 = (EZDSStringItem)newItem("wmsOutCntnrNum_O1");
		wmsFrtChrgAmt_O1 = (EZDSBigDecimalItem)newItem("wmsFrtChrgAmt_O1");
		serNum_O1 = (EZDSStringItem)newItem("serNum_O1");
		estDockDtTmTs_O1 = (EZDSStringItem)newItem("estDockDtTmTs_O1");
		xxDt10Dt_O1 = (EZDSDateItem)newItem("xxDt10Dt_O1");
		ezInTime_O1 = (EZDSStringItem)newItem("ezInTime_O1");
		xxDtTm_O2 = (EZDSStringItem)newItem("xxDtTm_O2");
		tmsFrtChrgAmt_O1 = (EZDSBigDecimalItem)newItem("tmsFrtChrgAmt_O1");
		wmsOrgHostId_O1 = (EZDSStringItem)newItem("wmsOrgHostId_O1");
		ezCancelFlag_O1 = (EZDSStringItem)newItem("ezCancelFlag_O1");
		ezInTimeZone_O1 = (EZDSStringItem)newItem("ezInTimeZone_O1");
		wmsInbdTrxPk_O1 = (EZDSBigDecimalItem)newItem("wmsInbdTrxPk_O1");
		tmsTrkNum_O1 = (EZDSStringItem)newItem("tmsTrkNum_O1");
		packCdTxt_O1 = (EZDSStringItem)newItem("packCdTxt_O1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0010_OSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0010_OSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_O1", "xxChkBox_O1", "O1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxExstFlg_O1", "xxExstFlg_O1", "O1", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsUpdHistNum_O1", "wmsUpdHistNum_O1", "O1", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"fill30Txt_O1", "fill30Txt_O1", "O1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsRecId_O1", "wmsRecId_O1", "O1", null, TYPE_HANKAKUEISU, "28", null},
	{"otbdOrdLineNum_O1", "otbdOrdLineNum_O1", "O1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"inbdOrdLineNum_O1", "inbdOrdLineNum_O1", "O1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wmsTaskCd_O2", "wmsTaskCd_O2", "O2", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsOrdStsCd_O2", "wmsOrdStsCd_O2", "O2", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsMdseCd_O1", "wmsMdseCd_O1", "O1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsStkStsCd_O2", "wmsStkStsCd_O2", "O2", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsOrdQty_O1", "wmsOrdQty_O1", "O1", null, TYPE_SEISU_SYOSU, "12", "3"},
	{"otbdOrdTpCd_O2", "otbdOrdTpCd_O2", "O2", null, TYPE_HANKAKUEISU, "4", null},
	{"otbdOrdNum_O1", "otbdOrdNum_O1", "O1", null, TYPE_HANKAKUEISU, "30", null},
	{"inbdOrdTpCd_O2", "inbdOrdTpCd_O2", "O2", null, TYPE_HANKAKUEISU, "4", null},
	{"inbdOrdNum_O1", "inbdOrdNum_O1", "O1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsTrxDtTmTs_O1", "wmsTrxDtTmTs_O1", "O1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_O1", "xxDtTm_O1", "O1", null, TYPE_HANKAKUEISU, "23", null},
	{"wmsTotWt_O1", "wmsTotWt_O1", "O1", null, TYPE_SEISU_SYOSU, "12", "3"},
	{"wmsCarrCd_O1", "wmsCarrCd_O1", "O1", null, TYPE_HANKAKUEISU, "40", null},
	{"wmsTrailId_O1", "wmsTrailId_O1", "O1", null, TYPE_HANKAKUEISU, "10", null},
	{"proNum_O1", "proNum_O1", "O1", null, TYPE_HANKAKUEISU, "30", null},
	{"bolNum_O1", "bolNum_O1", "O1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsShipId_O1", "wmsShipId_O1", "O1", null, TYPE_HANKAKUEISU, "10", null},
	{"wmsCntnrId_O1", "wmsCntnrId_O1", "O1", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsOutCntnrNum_O1", "wmsOutCntnrNum_O1", "O1", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsFrtChrgAmt_O1", "wmsFrtChrgAmt_O1", "O1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"serNum_O1", "serNum_O1", "O1", null, TYPE_HANKAKUEISU, "30", null},
	{"estDockDtTmTs_O1", "estDockDtTmTs_O1", "O1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDt10Dt_O1", "xxDt10Dt_O1", "O1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezInTime_O1", "ezInTime_O1", "O1", null, TYPE_HANKAKUEISU, "17", null},
	{"xxDtTm_O2", "xxDtTm_O2", "O2", null, TYPE_HANKAKUEISU, "23", null},
	{"tmsFrtChrgAmt_O1", "tmsFrtChrgAmt_O1", "O1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"wmsOrgHostId_O1", "wmsOrgHostId_O1", "O1", null, TYPE_HANKAKUEISU, "10", null},
	{"ezCancelFlag_O1", "ezCancelFlag_O1", "O1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezInTimeZone_O1", "ezInTimeZone_O1", "O1", null, TYPE_HANKAKUEISU, "5", null},
	{"wmsInbdTrxPk_O1", "wmsInbdTrxPk_O1", "O1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"tmsTrkNum_O1", "tmsTrkNum_O1", "O1", null, TYPE_HANKAKUEISU, "40", null},
	{"packCdTxt_O1", "packCdTxt_O1", "O1", null, TYPE_HANKAKUEISU, "6", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_O1
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_O1
        {"WMS_UPD_HIST_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsUpdHistNum_O1
        {"FILL_30_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill30Txt_O1
        {"WMS_REC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsRecId_O1
        {"OTBD_ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otbdOrdLineNum_O1
        {"INBD_ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOrdLineNum_O1
        {"WMS_TASK_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTaskCd_O2
        {"WMS_ORD_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrdStsCd_O2
        {"WMS_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseCd_O1
        {"WMS_STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsStkStsCd_O2
        {"WMS_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrdQty_O1
        {"OTBD_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otbdOrdTpCd_O2
        {"OTBD_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otbdOrdNum_O1
        {"INBD_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOrdTpCd_O2
        {"INBD_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOrdNum_O1
        {"WMS_TRX_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTrxDtTmTs_O1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_O1
        {"WMS_TOT_WT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTotWt_O1
        {"WMS_CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCarrCd_O1
        {"WMS_TRAIL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTrailId_O1
        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_O1
        {"BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bolNum_O1
        {"WMS_SHIP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsShipId_O1
        {"WMS_CNTNR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCntnrId_O1
        {"WMS_OUT_CNTNR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOutCntnrNum_O1
        {"WMS_FRT_CHRG_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsFrtChrgAmt_O1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_O1
        {"EST_DOCK_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estDockDtTmTs_O1
        {"XX_DT_10_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDt10Dt_O1
        {"_EZRegistrationDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_O1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_O2
        {"TMS_FRT_CHRG_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tmsFrtChrgAmt_O1
        {"WMS_ORG_HOST_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrgHostId_O1
        {"_EZCancelFlag",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezCancelFlag_O1
        {"_EZInTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTimeZone_O1
        {"WMS_INBD_TRX_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsInbdTrxPk_O1
        {"TMS_TRK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tmsTrkNum_O1
        {"PACK_CD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//packCdTxt_O1
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

