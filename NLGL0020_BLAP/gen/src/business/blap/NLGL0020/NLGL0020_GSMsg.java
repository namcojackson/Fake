//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170705181304000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0020_GSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLGL0020 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0020_GSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WMS_PO_ID_G2*/
	public final EZDSStringItem              wmsPoId_G2;

    /** WMS_SQ_NUM_G2*/
	public final EZDSBigDecimalItem              wmsSqNum_G2;

    /** WMS_PRCH_TP_NM_G1*/
	public final EZDSStringItem              wmsPrchTpNm_G1;

    /** VND_CD_G1*/
	public final EZDSStringItem              vndCd_G1;

    /** WMS_VESL_NM_G1*/
	public final EZDSStringItem              wmsVeslNm_G1;

    /** WMS_BOL_NUM_G1*/
	public final EZDSStringItem              wmsBolNum_G1;

    /** WMS_SER_NUM_G1*/
	public final EZDSStringItem              wmsSerNum_G1;

    /** XX_INTFC_ITEM_STS_DESC_TXT_G1*/
	public final EZDSStringItem              xxIntfcItemStsDescTxt_G1;

    /** WMS_TRX_CD_G1*/
	public final EZDSStringItem              wmsTrxCd_G1;

    /** _EZRegistrationDateTime_G1*/
	public final EZDSStringItem              ezInTime_G1;

    /** XX_DT_TM_G1*/
	public final EZDSStringItem              xxDtTm_G1;

    /** WMS_RESRC_TXT_G1*/
	public final EZDSStringItem              wmsResrcTxt_G1;

    /** WMS_CLO_DT_TM_TS_G1*/
	public final EZDSStringItem              wmsCloDtTmTs_G1;

    /** XX_DT_TM_G2*/
	public final EZDSStringItem              xxDtTm_G2;

    /** FILL_2_TXT_G1*/
	public final EZDSStringItem              fill2Txt_G1;

    /** RTL_WH_CD_G1*/
	public final EZDSStringItem              rtlWhCd_G1;

    /** INVTY_OWNR_CD_G1*/
	public final EZDSStringItem              invtyOwnrCd_G1;

    /** SVC_CONFIG_MSTR_PK_G1*/
	public final EZDSBigDecimalItem              svcConfigMstrPk_G1;


	/**
	 * NLGL0020_GSMsg is constructor.
	 * The initialization when the instance of NLGL0020_GSMsg is generated.
	 */
	public NLGL0020_GSMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0020_GSMsg is constructor.
	 * The initialization when the instance of NLGL0020_GSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0020_GSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wmsPoId_G2 = (EZDSStringItem)newItem("wmsPoId_G2");
		wmsSqNum_G2 = (EZDSBigDecimalItem)newItem("wmsSqNum_G2");
		wmsPrchTpNm_G1 = (EZDSStringItem)newItem("wmsPrchTpNm_G1");
		vndCd_G1 = (EZDSStringItem)newItem("vndCd_G1");
		wmsVeslNm_G1 = (EZDSStringItem)newItem("wmsVeslNm_G1");
		wmsBolNum_G1 = (EZDSStringItem)newItem("wmsBolNum_G1");
		wmsSerNum_G1 = (EZDSStringItem)newItem("wmsSerNum_G1");
		xxIntfcItemStsDescTxt_G1 = (EZDSStringItem)newItem("xxIntfcItemStsDescTxt_G1");
		wmsTrxCd_G1 = (EZDSStringItem)newItem("wmsTrxCd_G1");
		ezInTime_G1 = (EZDSStringItem)newItem("ezInTime_G1");
		xxDtTm_G1 = (EZDSStringItem)newItem("xxDtTm_G1");
		wmsResrcTxt_G1 = (EZDSStringItem)newItem("wmsResrcTxt_G1");
		wmsCloDtTmTs_G1 = (EZDSStringItem)newItem("wmsCloDtTmTs_G1");
		xxDtTm_G2 = (EZDSStringItem)newItem("xxDtTm_G2");
		fill2Txt_G1 = (EZDSStringItem)newItem("fill2Txt_G1");
		rtlWhCd_G1 = (EZDSStringItem)newItem("rtlWhCd_G1");
		invtyOwnrCd_G1 = (EZDSStringItem)newItem("invtyOwnrCd_G1");
		svcConfigMstrPk_G1 = (EZDSBigDecimalItem)newItem("svcConfigMstrPk_G1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0020_GSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0020_GSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wmsPoId_G2", "wmsPoId_G2", "G2", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsSqNum_G2", "wmsSqNum_G2", "G2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wmsPrchTpNm_G1", "wmsPrchTpNm_G1", "G1", null, TYPE_HANKAKUEISU, "70", null},
	{"vndCd_G1", "vndCd_G1", "G1", null, TYPE_HANKAKUEISU, "20", null},
	{"wmsVeslNm_G1", "wmsVeslNm_G1", "G1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsBolNum_G1", "wmsBolNum_G1", "G1", null, TYPE_HANKAKUEISU, "40", null},
	{"wmsSerNum_G1", "wmsSerNum_G1", "G1", null, TYPE_HANKAKUEISU, "15", null},
	{"xxIntfcItemStsDescTxt_G1", "xxIntfcItemStsDescTxt_G1", "G1", null, TYPE_HANKAKUEISU, "15", null},
	{"wmsTrxCd_G1", "wmsTrxCd_G1", "G1", null, TYPE_HANKAKUEISU, "2", null},
	{"ezInTime_G1", "ezInTime_G1", "G1", null, TYPE_HANKAKUEISU, "17", null},
	{"xxDtTm_G1", "xxDtTm_G1", "G1", null, TYPE_HANKAKUEISU, "23", null},
	{"wmsResrcTxt_G1", "wmsResrcTxt_G1", "G1", null, TYPE_HANKAKUEISU, "250", null},
	{"wmsCloDtTmTs_G1", "wmsCloDtTmTs_G1", "G1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTm_G2", "xxDtTm_G2", "G2", null, TYPE_HANKAKUEISU, "23", null},
	{"fill2Txt_G1", "fill2Txt_G1", "G1", null, TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCd_G1", "rtlWhCd_G1", "G1", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyOwnrCd_G1", "invtyOwnrCd_G1", "G1", null, TYPE_HANKAKUEISU, "3", null},
	{"svcConfigMstrPk_G1", "svcConfigMstrPk_G1", "G1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WMS_PO_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsPoId_G2
        {"WMS_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsSqNum_G2
        {"WMS_PRCH_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsPrchTpNm_G1
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_G1
        {"WMS_VESL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsVeslNm_G1
        {"WMS_BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsBolNum_G1
        {"WMS_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsSerNum_G1
        {"XX_INTFC_ITEM_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxIntfcItemStsDescTxt_G1
        {"WMS_TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTrxCd_G1
        {"_EZRegistrationDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_G1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_G1
        {"WMS_RESRC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsResrcTxt_G1
        {"WMS_CLO_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCloDtTmTs_G1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_G2
        {"FILL_2_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill2Txt_G1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_G1
        {"INVTY_OWNR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOwnrCd_G1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_G1
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
