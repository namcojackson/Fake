//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170705181303000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0020_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0020_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** WMS_PO_ID_A1*/
	public final EZDCStringItem              wmsPoId_A1;

    /** WMS_SQ_NUM_A1*/
	public final EZDCBigDecimalItem              wmsSqNum_A1;

    /** WMS_ORD_TP_CD_A1*/
	public final EZDCStringItem              wmsOrdTpCd_A1;

    /** WMS_TRX_CD_A1*/
	public final EZDCStringItem              wmsTrxCd_A1;

    /** PO_FROM_DT_TM_TS_A1*/
	public final EZDCStringItem              poFromDtTmTs_A1;

    /** XX_DT_TM_A1*/
	public final EZDCStringItem              xxDtTm_A1;

    /** VND_CD_A1*/
	public final EZDCStringItem              vndCd_A1;

    /** XX_INTFC_LINE_NUM_A1*/
	public final EZDCBigDecimalItem              xxIntfcLineNum_A1;

    /** _EZRegistrationDateTime_A1*/
	public final EZDCStringItem              ezInTime_A1;

    /** XX_DT_TM_A2*/
	public final EZDCStringItem              xxDtTm_A2;

    /** XX_MSG_TXT_A1*/
	public final EZDCStringItem              xxMsgTxt_A1;

    /** WMS_RESRC_TXT_A1*/
	public final EZDCStringItem              wmsResrcTxt_A1;

    /** XX_DTL_CD_A1*/
	public final EZDCStringItem              xxDtlCd_A1;

    /** WMS_CLO_DT_TM_TS_A1*/
	public final EZDCStringItem              wmsCloDtTmTs_A1;

    /** WH_CD_A1*/
	public final EZDCStringItem              whCd_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDCStringItem              ezUpTime_A1;

    /** RTL_WH_CD_A1*/
	public final EZDCStringItem              rtlWhCd_A1;

    /** INVTY_OWNR_CD_A1*/
	public final EZDCStringItem              invtyOwnrCd_A1;

    /** SVC_CONFIG_MSTR_PK_A1*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_A1;

    /** INBD_PO_MSG_TXT_A1*/
	public final EZDCStringItem              inbdPoMsgTxt_A1;

    /** INBD_PO_MSG_TXT_A2*/
	public final EZDCStringItem              inbdPoMsgTxt_A2;

    /** INBD_PO_MSG_TXT_A3*/
	public final EZDCStringItem              inbdPoMsgTxt_A3;

    /** INBD_PO_MSG_TXT_A4*/
	public final EZDCStringItem              inbdPoMsgTxt_A4;

    /** SCE_ORD_TP_CD_A1*/
	public final EZDCStringItem              sceOrdTpCd_A1;


	/**
	 * NLGL0020_ACMsg is constructor.
	 * The initialization when the instance of NLGL0020_ACMsg is generated.
	 */
	public NLGL0020_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0020_ACMsg is constructor.
	 * The initialization when the instance of NLGL0020_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0020_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		wmsPoId_A1 = (EZDCStringItem)newItem("wmsPoId_A1");
		wmsSqNum_A1 = (EZDCBigDecimalItem)newItem("wmsSqNum_A1");
		wmsOrdTpCd_A1 = (EZDCStringItem)newItem("wmsOrdTpCd_A1");
		wmsTrxCd_A1 = (EZDCStringItem)newItem("wmsTrxCd_A1");
		poFromDtTmTs_A1 = (EZDCStringItem)newItem("poFromDtTmTs_A1");
		xxDtTm_A1 = (EZDCStringItem)newItem("xxDtTm_A1");
		vndCd_A1 = (EZDCStringItem)newItem("vndCd_A1");
		xxIntfcLineNum_A1 = (EZDCBigDecimalItem)newItem("xxIntfcLineNum_A1");
		ezInTime_A1 = (EZDCStringItem)newItem("ezInTime_A1");
		xxDtTm_A2 = (EZDCStringItem)newItem("xxDtTm_A2");
		xxMsgTxt_A1 = (EZDCStringItem)newItem("xxMsgTxt_A1");
		wmsResrcTxt_A1 = (EZDCStringItem)newItem("wmsResrcTxt_A1");
		xxDtlCd_A1 = (EZDCStringItem)newItem("xxDtlCd_A1");
		wmsCloDtTmTs_A1 = (EZDCStringItem)newItem("wmsCloDtTmTs_A1");
		whCd_A1 = (EZDCStringItem)newItem("whCd_A1");
		ezUpTime_A1 = (EZDCStringItem)newItem("ezUpTime_A1");
		rtlWhCd_A1 = (EZDCStringItem)newItem("rtlWhCd_A1");
		invtyOwnrCd_A1 = (EZDCStringItem)newItem("invtyOwnrCd_A1");
		svcConfigMstrPk_A1 = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_A1");
		inbdPoMsgTxt_A1 = (EZDCStringItem)newItem("inbdPoMsgTxt_A1");
		inbdPoMsgTxt_A2 = (EZDCStringItem)newItem("inbdPoMsgTxt_A2");
		inbdPoMsgTxt_A3 = (EZDCStringItem)newItem("inbdPoMsgTxt_A3");
		inbdPoMsgTxt_A4 = (EZDCStringItem)newItem("inbdPoMsgTxt_A4");
		sceOrdTpCd_A1 = (EZDCStringItem)newItem("sceOrdTpCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0020_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0020_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"wmsPoId_A1", "wmsPoId_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsSqNum_A1", "wmsSqNum_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wmsOrdTpCd_A1", "wmsOrdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"wmsTrxCd_A1", "wmsTrxCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"poFromDtTmTs_A1", "poFromDtTmTs_A1", "A1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_A1", "xxDtTm_A1", "A1", null, TYPE_HANKAKUEISU, "23", null},
	{"vndCd_A1", "vndCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxIntfcLineNum_A1", "xxIntfcLineNum_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"ezInTime_A1", "ezInTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"xxDtTm_A2", "xxDtTm_A2", "A2", null, TYPE_HANKAKUEISU, "23", null},
	{"xxMsgTxt_A1", "xxMsgTxt_A1", "A1", null, TYPE_HANKAKUEISU, "300", null},
	{"wmsResrcTxt_A1", "wmsResrcTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"xxDtlCd_A1", "xxDtlCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsCloDtTmTs_A1", "wmsCloDtTmTs_A1", "A1", null, TYPE_HANKAKUSUJI, "14", null},
	{"whCd_A1", "whCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyOwnrCd_A1", "invtyOwnrCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"svcConfigMstrPk_A1", "svcConfigMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"inbdPoMsgTxt_A1", "inbdPoMsgTxt_A1", "A1", null, TYPE_HANKAKUEISU, "65", null},
	{"inbdPoMsgTxt_A2", "inbdPoMsgTxt_A2", "A2", null, TYPE_HANKAKUEISU, "65", null},
	{"inbdPoMsgTxt_A3", "inbdPoMsgTxt_A3", "A3", null, TYPE_HANKAKUEISU, "65", null},
	{"inbdPoMsgTxt_A4", "inbdPoMsgTxt_A4", "A4", null, TYPE_HANKAKUEISU, "65", null},
	{"sceOrdTpCd_A1", "sceOrdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"WMS_PO_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsPoId_A1
        {"WMS_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsSqNum_A1
        {"WMS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsOrdTpCd_A1
        {"WMS_TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTrxCd_A1
        {"PO_FROM_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poFromDtTmTs_A1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A1
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_A1
        {"XX_INTFC_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxIntfcLineNum_A1
        {"_EZRegistrationDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_A1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A2
        {"XX_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgTxt_A1
        {"WMS_RESRC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsResrcTxt_A1
        {"XX_DTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCd_A1
        {"WMS_CLO_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCloDtTmTs_A1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"INVTY_OWNR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOwnrCd_A1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A1
        {"INBD_PO_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdPoMsgTxt_A1
        {"INBD_PO_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdPoMsgTxt_A2
        {"INBD_PO_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdPoMsgTxt_A3
        {"INBD_PO_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdPoMsgTxt_A4
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_A1
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
