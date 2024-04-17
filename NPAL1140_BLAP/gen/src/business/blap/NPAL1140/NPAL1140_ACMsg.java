//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20181120160148000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1140_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1140 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1140_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A0*/
	public final EZDCStringItem              xxChkBox_A0;

    /** PO_ACK_HDR_WRK_PK_A0*/
	public final EZDCBigDecimalItem              poAckHdrWrkPk_A0;

    /** ITRL_INTFC_ID_A0*/
	public final EZDCStringItem              itrlIntfcId_A0;

    /** DPLY_VND_NM_A0*/
	public final EZDCStringItem              dplyVndNm_A0;

    /** EDI_PO_ORD_NUM_A0*/
	public final EZDCStringItem              ediPoOrdNum_A0;

    /** PO_ACK_NUM_A0*/
	public final EZDCStringItem              poAckNum_A0;

    /** ACK_EDI_PROC_STS_NM_A0*/
	public final EZDCStringItem              ackEdiProcStsNm_A0;

    /** OPEN_PO_ACK_WRK_FLG_A0*/
	public final EZDCStringItem              openPoAckWrkFlg_A0;

    /** EDI_RCV_DATE_TS_A0*/
	public final EZDCStringItem              ediRcvDateTs_A0;

    /** XX_TS_DSP_19_TXT_AR*/
	public final EZDCStringItem              xxTsDsp19Txt_AR;

    /** ORD_LAST_UPD_TS_A0*/
	public final EZDCStringItem              ordLastUpdTs_A0;

    /** XX_TS_DSP_19_TXT_AU*/
	public final EZDCStringItem              xxTsDsp19Txt_AU;

    /** VND_CPO_ORD_NUM_A0*/
	public final EZDCStringItem              vndCpoOrdNum_A0;

    /** BAT_ERR_MSG_TXT_AC*/
	public final EZDCStringItemArray              batErrMsgTxt_AC;

    /** BAT_ERR_MSG_TXT_AD*/
	public final EZDCStringItemArray              batErrMsgTxt_AD;

    /** BAT_ERR_MSG_TXT_AV*/
	public final EZDCStringItem              batErrMsgTxt_AV;

    /** _EZUpdateDateTime_A0*/
	public final EZDCStringItem              ezUpTime_A0;

    /** _EZUpTimeZone_A0*/
	public final EZDCStringItem              ezUpTimeZone_A0;

    /** EDI_PO_ORD_DTL_LINE_NUM_A0*/
	public final EZDCStringItem              ediPoOrdDtlLineNum_A0;

    /** PO_ORD_DTL_LINE_NUM_A0*/
	public final EZDCStringItem              poOrdDtlLineNum_A0;


	/**
	 * NPAL1140_ACMsg is constructor.
	 * The initialization when the instance of NPAL1140_ACMsg is generated.
	 */
	public NPAL1140_ACMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1140_ACMsg is constructor.
	 * The initialization when the instance of NPAL1140_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1140_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A0 = (EZDCStringItem)newItem("xxChkBox_A0");
		poAckHdrWrkPk_A0 = (EZDCBigDecimalItem)newItem("poAckHdrWrkPk_A0");
		itrlIntfcId_A0 = (EZDCStringItem)newItem("itrlIntfcId_A0");
		dplyVndNm_A0 = (EZDCStringItem)newItem("dplyVndNm_A0");
		ediPoOrdNum_A0 = (EZDCStringItem)newItem("ediPoOrdNum_A0");
		poAckNum_A0 = (EZDCStringItem)newItem("poAckNum_A0");
		ackEdiProcStsNm_A0 = (EZDCStringItem)newItem("ackEdiProcStsNm_A0");
		openPoAckWrkFlg_A0 = (EZDCStringItem)newItem("openPoAckWrkFlg_A0");
		ediRcvDateTs_A0 = (EZDCStringItem)newItem("ediRcvDateTs_A0");
		xxTsDsp19Txt_AR = (EZDCStringItem)newItem("xxTsDsp19Txt_AR");
		ordLastUpdTs_A0 = (EZDCStringItem)newItem("ordLastUpdTs_A0");
		xxTsDsp19Txt_AU = (EZDCStringItem)newItem("xxTsDsp19Txt_AU");
		vndCpoOrdNum_A0 = (EZDCStringItem)newItem("vndCpoOrdNum_A0");
		batErrMsgTxt_AC = (EZDCStringItemArray)newItemArray("batErrMsgTxt_AC");
		batErrMsgTxt_AD = (EZDCStringItemArray)newItemArray("batErrMsgTxt_AD");
		batErrMsgTxt_AV = (EZDCStringItem)newItem("batErrMsgTxt_AV");
		ezUpTime_A0 = (EZDCStringItem)newItem("ezUpTime_A0");
		ezUpTimeZone_A0 = (EZDCStringItem)newItem("ezUpTimeZone_A0");
		ediPoOrdDtlLineNum_A0 = (EZDCStringItem)newItem("ediPoOrdDtlLineNum_A0");
		poOrdDtlLineNum_A0 = (EZDCStringItem)newItem("poOrdDtlLineNum_A0");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1140_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1140_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A0", "xxChkBox_A0", "A0", null, TYPE_HANKAKUEIJI, "1", null},
	{"poAckHdrWrkPk_A0", "poAckHdrWrkPk_A0", "A0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"itrlIntfcId_A0", "itrlIntfcId_A0", "A0", null, TYPE_HANKAKUEISU, "30", null},
	{"dplyVndNm_A0", "dplyVndNm_A0", "A0", null, TYPE_HANKAKUEISU, "320", null},
	{"ediPoOrdNum_A0", "ediPoOrdNum_A0", "A0", null, TYPE_HANKAKUEISU, "35", null},
	{"poAckNum_A0", "poAckNum_A0", "A0", null, TYPE_HANKAKUEISU, "6", null},
	{"ackEdiProcStsNm_A0", "ackEdiProcStsNm_A0", "A0", null, TYPE_HANKAKUEISU, "30", null},
	{"openPoAckWrkFlg_A0", "openPoAckWrkFlg_A0", "A0", null, TYPE_HANKAKUEISU, "1", null},
	{"ediRcvDateTs_A0", "ediRcvDateTs_A0", "A0", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxTsDsp19Txt_AR", "xxTsDsp19Txt_AR", "AR", null, TYPE_HANKAKUEISU, "19", null},
	{"ordLastUpdTs_A0", "ordLastUpdTs_A0", "A0", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxTsDsp19Txt_AU", "xxTsDsp19Txt_AU", "AU", null, TYPE_HANKAKUEISU, "19", null},
	{"vndCpoOrdNum_A0", "vndCpoOrdNum_A0", "A0", null, TYPE_HANKAKUEISU, "8", null},
	{"batErrMsgTxt_AC", "batErrMsgTxt_AC", "AC", "200", TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_AD", "batErrMsgTxt_AD", "AD", "200", TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_AV", "batErrMsgTxt_AV", "AV", null, TYPE_HANKAKUEISU, "400", null},
	{"ezUpTime_A0", "ezUpTime_A0", "A0", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A0", "ezUpTimeZone_A0", "A0", null, TYPE_HANKAKUEISU, "5", null},
	{"ediPoOrdDtlLineNum_A0", "ediPoOrdDtlLineNum_A0", "A0", null, TYPE_HANKAKUEISU, "6", null},
	{"poOrdDtlLineNum_A0", "poOrdDtlLineNum_A0", "A0", null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A0
        {"PO_ACK_HDR_WRK_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poAckHdrWrkPk_A0
        {"ITRL_INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcId_A0
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_A0
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_A0
        {"PO_ACK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poAckNum_A0
        {"ACK_EDI_PROC_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ackEdiProcStsNm_A0
        {"OPEN_PO_ACK_WRK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openPoAckWrkFlg_A0
        {"EDI_RCV_DATE_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediRcvDateTs_A0
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_AR
        {"ORD_LAST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLastUpdTs_A0
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_AU
        {"VND_CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCpoOrdNum_A0
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_AC
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_AD
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_AV
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A0
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A0
        {"EDI_PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdDtlLineNum_A0
        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum_A0
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

