//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20201118194726000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3170_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3170 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3170_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MSTR_PRO_NUM_A1*/
	public final EZDCStringItem              mstrProNum_A1;

    /** PRO_NUM_A1*/
	public final EZDCStringItem              proNum_A1;

    /** TRX_LINE_NUM_A1*/
	public final EZDCStringItem              trxLineNum_A1;

    /** WMS_CARR_CD_A1*/
	public final EZDCStringItem              wmsCarrCd_A1;

    /** XX_MSG_TXT_A1*/
	public final EZDCStringItem              xxMsgTxt_A1;

    /** PRO_SEND_FLG_A1*/
	public final EZDCStringItem              proSendFlg_A1;

    /** XX_DPLY_ORD_NUM_A1*/
	public final EZDCStringItem              xxDplyOrdNum_A1;

    /** CARR_TRK_URL_A1*/
	public final EZDCStringItem              carrTrkUrl_A1;

    /** CARR_TRK_URL_A2*/
	public final EZDCStringItem              carrTrkUrl_A2;

    /** CARR_ENC_TRK_URL_A1*/
	public final EZDCStringItem              carrEncTrkUrl_A1;

    /** CARR_ENC_TRK_URL_A2*/
	public final EZDCStringItem              carrEncTrkUrl_A2;


	/**
	 * NLBL3170_ACMsg is constructor.
	 * The initialization when the instance of NLBL3170_ACMsg is generated.
	 */
	public NLBL3170_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3170_ACMsg is constructor.
	 * The initialization when the instance of NLBL3170_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3170_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mstrProNum_A1 = (EZDCStringItem)newItem("mstrProNum_A1");
		proNum_A1 = (EZDCStringItem)newItem("proNum_A1");
		trxLineNum_A1 = (EZDCStringItem)newItem("trxLineNum_A1");
		wmsCarrCd_A1 = (EZDCStringItem)newItem("wmsCarrCd_A1");
		xxMsgTxt_A1 = (EZDCStringItem)newItem("xxMsgTxt_A1");
		proSendFlg_A1 = (EZDCStringItem)newItem("proSendFlg_A1");
		xxDplyOrdNum_A1 = (EZDCStringItem)newItem("xxDplyOrdNum_A1");
		carrTrkUrl_A1 = (EZDCStringItem)newItem("carrTrkUrl_A1");
		carrTrkUrl_A2 = (EZDCStringItem)newItem("carrTrkUrl_A2");
		carrEncTrkUrl_A1 = (EZDCStringItem)newItem("carrEncTrkUrl_A1");
		carrEncTrkUrl_A2 = (EZDCStringItem)newItem("carrEncTrkUrl_A2");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3170_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3170_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mstrProNum_A1", "mstrProNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"proNum_A1", "proNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"trxLineNum_A1", "trxLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"wmsCarrCd_A1", "wmsCarrCd_A1", "A1", null, TYPE_HANKAKUEISU, "40", null},
	{"xxMsgTxt_A1", "xxMsgTxt_A1", "A1", null, TYPE_HANKAKUEISU, "300", null},
	{"proSendFlg_A1", "proSendFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyOrdNum_A1", "xxDplyOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"carrTrkUrl_A1", "carrTrkUrl_A1", "A1", null, TYPE_HANKAKUEISU, "512", null},
	{"carrTrkUrl_A2", "carrTrkUrl_A2", "A2", null, TYPE_HANKAKUEISU, "512", null},
	{"carrEncTrkUrl_A1", "carrEncTrkUrl_A1", "A1", null, TYPE_HANKAKUEISU, "1500", null},
	{"carrEncTrkUrl_A2", "carrEncTrkUrl_A2", "A2", null, TYPE_HANKAKUEISU, "1500", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MSTR_PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrProNum_A1
        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_A1
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum_A1
        {"WMS_CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCarrCd_A1
        {"XX_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgTxt_A1
        {"PRO_SEND_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proSendFlg_A1
        {"XX_DPLY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyOrdNum_A1
        {"CARR_TRK_URL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrTrkUrl_A1
        {"CARR_TRK_URL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrTrkUrl_A2
        {"CARR_ENC_TRK_URL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrEncTrkUrl_A1
        {"CARR_ENC_TRK_URL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrEncTrkUrl_A2
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

