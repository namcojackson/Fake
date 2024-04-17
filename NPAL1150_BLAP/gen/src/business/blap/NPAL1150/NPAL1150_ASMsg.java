//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20181120160254000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1150_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1150 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1150_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_B1*/
	public final EZDSStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDSStringItem              ezUpTimeZone_B1;

    /** XX_CHK_BOX_B1*/
	public final EZDSStringItem              xxChkBox_B1;

    /** DPLY_VND_NM_B1*/
	public final EZDSStringItem              dplyVndNm_B1;

    /** ASN_SO_NUM_B1*/
	public final EZDSStringItem              asnSoNum_B1;

    /** EDI_PO_ORD_NUM_B1*/
	public final EZDSStringItem              ediPoOrdNum_B1;

    /** ASN_EDI_PROC_STS_CD_B1*/
	public final EZDSStringItem              asnEdiProcStsCd_B1;

    /** ASN_EDI_PROC_STS_NM_B1*/
	public final EZDSStringItem              asnEdiProcStsNm_B1;

    /** EDI_RCV_DATE_TS_B1*/
	public final EZDSStringItem              ediRcvDateTs_B1;

    /** XX_TS_DSP_19_TXT_BR*/
	public final EZDSStringItem              xxTsDsp19Txt_BR;

    /** VND_INVTY_LOC_CD_B1*/
	public final EZDSStringItem              vndInvtyLocCd_B1;

    /** ASN_SHIP_DT_TM_TS_B1*/
	public final EZDSStringItem              asnShipDtTmTs_B1;

    /** XX_TS_DSP_19_TXT_BS*/
	public final EZDSStringItem              xxTsDsp19Txt_BS;

    /** OPEN_ASN_WRK_FLG_B1*/
	public final EZDSStringItem              openAsnWrkFlg_B1;


	/**
	 * NPAL1150_ASMsg is constructor.
	 * The initialization when the instance of NPAL1150_ASMsg is generated.
	 */
	public NPAL1150_ASMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1150_ASMsg is constructor.
	 * The initialization when the instance of NPAL1150_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1150_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_B1 = (EZDSStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDSStringItem)newItem("ezUpTimeZone_B1");
		xxChkBox_B1 = (EZDSStringItem)newItem("xxChkBox_B1");
		dplyVndNm_B1 = (EZDSStringItem)newItem("dplyVndNm_B1");
		asnSoNum_B1 = (EZDSStringItem)newItem("asnSoNum_B1");
		ediPoOrdNum_B1 = (EZDSStringItem)newItem("ediPoOrdNum_B1");
		asnEdiProcStsCd_B1 = (EZDSStringItem)newItem("asnEdiProcStsCd_B1");
		asnEdiProcStsNm_B1 = (EZDSStringItem)newItem("asnEdiProcStsNm_B1");
		ediRcvDateTs_B1 = (EZDSStringItem)newItem("ediRcvDateTs_B1");
		xxTsDsp19Txt_BR = (EZDSStringItem)newItem("xxTsDsp19Txt_BR");
		vndInvtyLocCd_B1 = (EZDSStringItem)newItem("vndInvtyLocCd_B1");
		asnShipDtTmTs_B1 = (EZDSStringItem)newItem("asnShipDtTmTs_B1");
		xxTsDsp19Txt_BS = (EZDSStringItem)newItem("xxTsDsp19Txt_BS");
		openAsnWrkFlg_B1 = (EZDSStringItem)newItem("openAsnWrkFlg_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1150_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1150_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dplyVndNm_B1", "dplyVndNm_B1", "B1", null, TYPE_HANKAKUEISU, "320", null},
	{"asnSoNum_B1", "asnSoNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"ediPoOrdNum_B1", "ediPoOrdNum_B1", "B1", null, TYPE_HANKAKUEISU, "35", null},
	{"asnEdiProcStsCd_B1", "asnEdiProcStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"asnEdiProcStsNm_B1", "asnEdiProcStsNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"ediRcvDateTs_B1", "ediRcvDateTs_B1", "B1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxTsDsp19Txt_BR", "xxTsDsp19Txt_BR", "BR", null, TYPE_HANKAKUEISU, "19", null},
	{"vndInvtyLocCd_B1", "vndInvtyLocCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"asnShipDtTmTs_B1", "asnShipDtTmTs_B1", "B1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxTsDsp19Txt_BS", "xxTsDsp19Txt_BS", "BS", null, TYPE_HANKAKUEISU, "19", null},
	{"openAsnWrkFlg_B1", "openAsnWrkFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_B1
        {"ASN_SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnSoNum_B1
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_B1
        {"ASN_EDI_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnEdiProcStsCd_B1
        {"ASN_EDI_PROC_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnEdiProcStsNm_B1
        {"EDI_RCV_DATE_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediRcvDateTs_B1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_BR
        {"VND_INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvtyLocCd_B1
        {"ASN_SHIP_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnShipDtTmTs_B1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_BS
        {"OPEN_ASN_WRK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openAsnWrkFlg_B1
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
