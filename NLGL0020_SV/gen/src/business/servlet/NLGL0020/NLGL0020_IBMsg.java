//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170705181130000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0020_IBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLGL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0020_IBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_I1*/
	public final EZDBStringItem              xxChkBox_I1;

    /** XX_EXST_FLG_I1*/
	public final EZDBStringItem              xxExstFlg_I1;

    /** WMS_UPD_HIST_NUM_I1*/
	public final EZDBBigDecimalItem              wmsUpdHistNum_I1;

    /** XX_PROC_FLG_NM_I1*/
	public final EZDBStringItem              xxProcFlgNm_I1;

    /** PROC_STS_CD_I1*/
	public final EZDBStringItem              procStsCd_I1;

    /** WMS_REC_ID_I1*/
	public final EZDBStringItem              wmsRecId_I1;

    /** OTBD_ORD_LINE_NUM_I2*/
	public final EZDBBigDecimalItem              otbdOrdLineNum_I2;

    /** WMS_TASK_CD_I2*/
	public final EZDBStringItem              wmsTaskCd_I2;

    /** WMS_TASK_CD_I1*/
	public final EZDBStringItemArray              wmsTaskCd_I1;

    /** CRS_SVC_TASK_NM_I1*/
	public final EZDBStringItemArray              crsSvcTaskNm_I1;

    /** WMS_MDSE_CD_I1*/
	public final EZDBStringItem              wmsMdseCd_I1;

    /** WMS_STK_STS_CD_I2*/
	public final EZDBStringItem              wmsStkStsCd_I2;

    /** WMS_STK_STS_CD_I1*/
	public final EZDBStringItemArray              wmsStkStsCd_I1;

    /** WMS_STK_STS_NM_I1*/
	public final EZDBStringItemArray              wmsStkStsNm_I1;

    /** WMS_ORD_QTY_I1*/
	public final EZDBBigDecimalItem              wmsOrdQty_I1;

    /** WMS_ORD_TP_CD_I2*/
	public final EZDBStringItem              wmsOrdTpCd_I2;

    /** WMS_ORD_TP_CD_I1*/
	public final EZDBStringItemArray              wmsOrdTpCd_I1;

    /** WMS_PRCH_TP_NM_I2*/
	public final EZDBStringItemArray              wmsPrchTpNm_I2;

    /** WMS_TRX_DT_TM_TS_I1*/
	public final EZDBStringItem              wmsTrxDtTmTs_I1;

    /** XX_DT_TM_I1*/
	public final EZDBStringItem              xxDtTm_I1;

    /** INBD_ORD_TP_CD_I1*/
	public final EZDBStringItem              inbdOrdTpCd_I1;

    /** INBD_ORD_LINE_NUM_I1*/
	public final EZDBBigDecimalItem              inbdOrdLineNum_I1;

    /** _EZRegistrationDateTime_I1*/
	public final EZDBStringItem              ezInTime_I1;

    /** XX_DT_TM_I2*/
	public final EZDBStringItem              xxDtTm_I2;

    /** _EZCancelFlag_I1*/
	public final EZDBStringItem              ezCancelFlag_I1;

    /** WMS_INBD_TRX_PK_I1*/
	public final EZDBBigDecimalItem              wmsInbdTrxPk_I1;

    /** PACK_CD_TXT_I1*/
	public final EZDBStringItem              packCdTxt_I1;

    /** SER_NUM_I1*/
	public final EZDBStringItem              serNum_I1;

    /** SCE_ORD_TP_CD_I2*/
	public final EZDBStringItem              sceOrdTpCd_I2;

    /** SCE_ORD_TP_CD_I1*/
	public final EZDBStringItemArray              sceOrdTpCd_I1;

    /** WMS_PRCH_TP_NM_I3*/
	public final EZDBStringItemArray              wmsPrchTpNm_I3;


	/**
	 * NLGL0020_IBMsg is constructor.
	 * The initialization when the instance of NLGL0020_IBMsg is generated.
	 */
	public NLGL0020_IBMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0020_IBMsg is constructor.
	 * The initialization when the instance of NLGL0020_IBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0020_IBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_I1 = (EZDBStringItem)newItem("xxChkBox_I1");
		xxExstFlg_I1 = (EZDBStringItem)newItem("xxExstFlg_I1");
		wmsUpdHistNum_I1 = (EZDBBigDecimalItem)newItem("wmsUpdHistNum_I1");
		xxProcFlgNm_I1 = (EZDBStringItem)newItem("xxProcFlgNm_I1");
		procStsCd_I1 = (EZDBStringItem)newItem("procStsCd_I1");
		wmsRecId_I1 = (EZDBStringItem)newItem("wmsRecId_I1");
		otbdOrdLineNum_I2 = (EZDBBigDecimalItem)newItem("otbdOrdLineNum_I2");
		wmsTaskCd_I2 = (EZDBStringItem)newItem("wmsTaskCd_I2");
		wmsTaskCd_I1 = (EZDBStringItemArray)newItemArray("wmsTaskCd_I1");
		crsSvcTaskNm_I1 = (EZDBStringItemArray)newItemArray("crsSvcTaskNm_I1");
		wmsMdseCd_I1 = (EZDBStringItem)newItem("wmsMdseCd_I1");
		wmsStkStsCd_I2 = (EZDBStringItem)newItem("wmsStkStsCd_I2");
		wmsStkStsCd_I1 = (EZDBStringItemArray)newItemArray("wmsStkStsCd_I1");
		wmsStkStsNm_I1 = (EZDBStringItemArray)newItemArray("wmsStkStsNm_I1");
		wmsOrdQty_I1 = (EZDBBigDecimalItem)newItem("wmsOrdQty_I1");
		wmsOrdTpCd_I2 = (EZDBStringItem)newItem("wmsOrdTpCd_I2");
		wmsOrdTpCd_I1 = (EZDBStringItemArray)newItemArray("wmsOrdTpCd_I1");
		wmsPrchTpNm_I2 = (EZDBStringItemArray)newItemArray("wmsPrchTpNm_I2");
		wmsTrxDtTmTs_I1 = (EZDBStringItem)newItem("wmsTrxDtTmTs_I1");
		xxDtTm_I1 = (EZDBStringItem)newItem("xxDtTm_I1");
		inbdOrdTpCd_I1 = (EZDBStringItem)newItem("inbdOrdTpCd_I1");
		inbdOrdLineNum_I1 = (EZDBBigDecimalItem)newItem("inbdOrdLineNum_I1");
		ezInTime_I1 = (EZDBStringItem)newItem("ezInTime_I1");
		xxDtTm_I2 = (EZDBStringItem)newItem("xxDtTm_I2");
		ezCancelFlag_I1 = (EZDBStringItem)newItem("ezCancelFlag_I1");
		wmsInbdTrxPk_I1 = (EZDBBigDecimalItem)newItem("wmsInbdTrxPk_I1");
		packCdTxt_I1 = (EZDBStringItem)newItem("packCdTxt_I1");
		serNum_I1 = (EZDBStringItem)newItem("serNum_I1");
		sceOrdTpCd_I2 = (EZDBStringItem)newItem("sceOrdTpCd_I2");
		sceOrdTpCd_I1 = (EZDBStringItemArray)newItemArray("sceOrdTpCd_I1");
		wmsPrchTpNm_I3 = (EZDBStringItemArray)newItemArray("wmsPrchTpNm_I3");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0020_IBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0020_IBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_I1", "xxChkBox_I1", "I1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxExstFlg_I1", "xxExstFlg_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsUpdHistNum_I1", "wmsUpdHistNum_I1", "I1", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"xxProcFlgNm_I1", "xxProcFlgNm_I1", "I1", null, TYPE_HANKAKUEISU, "11", null},
	{"procStsCd_I1", "procStsCd_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsRecId_I1", "wmsRecId_I1", "I1", null, TYPE_HANKAKUEISU, "28", null},
	{"otbdOrdLineNum_I2", "otbdOrdLineNum_I2", "I2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wmsTaskCd_I2", "wmsTaskCd_I2", "I2", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsTaskCd_I1", "wmsTaskCd_I1", "I1", "99", TYPE_HANKAKUEISU, "4", null},
	{"crsSvcTaskNm_I1", "crsSvcTaskNm_I1", "I1", "99", TYPE_HANKAKUEISU, "80", null},
	{"wmsMdseCd_I1", "wmsMdseCd_I1", "I1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsStkStsCd_I2", "wmsStkStsCd_I2", "I2", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsStkStsCd_I1", "wmsStkStsCd_I1", "I1", "99", TYPE_HANKAKUEISU, "4", null},
	{"wmsStkStsNm_I1", "wmsStkStsNm_I1", "I1", "99", TYPE_HANKAKUEISU, "70", null},
	{"wmsOrdQty_I1", "wmsOrdQty_I1", "I1", null, TYPE_SEISU_SYOSU, "12", "3"},
	{"wmsOrdTpCd_I2", "wmsOrdTpCd_I2", "I2", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsOrdTpCd_I1", "wmsOrdTpCd_I1", "I1", "99", TYPE_HANKAKUEISU, "1", null},
	{"wmsPrchTpNm_I2", "wmsPrchTpNm_I2", "I2", "99", TYPE_HANKAKUEISU, "70", null},
	{"wmsTrxDtTmTs_I1", "wmsTrxDtTmTs_I1", "I1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_I1", "xxDtTm_I1", "I1", null, TYPE_HANKAKUEISU, "23", null},
	{"inbdOrdTpCd_I1", "inbdOrdTpCd_I1", "I1", null, TYPE_HANKAKUEISU, "4", null},
	{"inbdOrdLineNum_I1", "inbdOrdLineNum_I1", "I1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezInTime_I1", "ezInTime_I1", "I1", null, TYPE_HANKAKUEISU, "17", null},
	{"xxDtTm_I2", "xxDtTm_I2", "I2", null, TYPE_HANKAKUEISU, "23", null},
	{"ezCancelFlag_I1", "ezCancelFlag_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsInbdTrxPk_I1", "wmsInbdTrxPk_I1", "I1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"packCdTxt_I1", "packCdTxt_I1", "I1", null, TYPE_HANKAKUEISU, "6", null},
	{"serNum_I1", "serNum_I1", "I1", null, TYPE_HANKAKUEISU, "30", null},
	{"sceOrdTpCd_I2", "sceOrdTpCd_I2", "I2", null, TYPE_HANKAKUEISU, "2", null},
	{"sceOrdTpCd_I1", "sceOrdTpCd_I1", "I1", "99", TYPE_HANKAKUEISU, "2", null},
	{"wmsPrchTpNm_I3", "wmsPrchTpNm_I3", "I3", "99", TYPE_HANKAKUEISU, "70", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_I1
        {"XX_EXST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_I1
        {"WMS_UPD_HIST_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsUpdHistNum_I1
        {"XX_PROC_FLG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxProcFlgNm_I1
        {"PROC_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procStsCd_I1
        {"WMS_REC_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsRecId_I1
        {"OTBD_ORD_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otbdOrdLineNum_I2
        {"WMS_TASK_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTaskCd_I2
        {"WMS_TASK_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTaskCd_I1
        {"CRS_SVC_TASK_NM", YES,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crsSvcTaskNm_I1
        {"WMS_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseCd_I1
        {"WMS_STK_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsStkStsCd_I2
        {"WMS_STK_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsStkStsCd_I1
        {"WMS_STK_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsStkStsNm_I1
        {"WMS_ORD_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrdQty_I1
        {"WMS_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrdTpCd_I2
        {"WMS_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrdTpCd_I1
        {"WMS_PRCH_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsPrchTpNm_I2
        {"WMS_TRX_DT_TM_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTrxDtTmTs_I1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_I1
        {"INBD_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOrdTpCd_I1
        {"INBD_ORD_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOrdLineNum_I1
        {"_EZRegistrationDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_I1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_I2
        {"_EZCancelFlag",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezCancelFlag_I1
        {"WMS_INBD_TRX_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsInbdTrxPk_I1
        {"PACK_CD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//packCdTxt_I1
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_I1
        {"SCE_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_I2
        {"SCE_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_I1
        {"WMS_PRCH_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsPrchTpNm_I3
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

