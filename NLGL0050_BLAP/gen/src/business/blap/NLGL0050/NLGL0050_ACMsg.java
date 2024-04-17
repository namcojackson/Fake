//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180309102407000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0050_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0050 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0050_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** WMS_TRNSP_TP_CD_A1*/
	public final EZDCStringItem              wmsTrnspTpCd_A1;

    /** WMS_CARR_CD_A1*/
	public final EZDCStringItem              wmsCarrCd_A1;

    /** WMS_DESC_TXT_A1*/
	public final EZDCStringItem              wmsDescTxt_A1;

    /** WMS_CARR_NM_A1*/
	public final EZDCStringItem              wmsCarrNm_A1;

    /** CARR_SVC_TXT_A1*/
	public final EZDCStringItem              carrSvcTxt_A1;

    /** CARR_SCAC_CD_A1*/
	public final EZDCStringItem              carrScacCd_A1;

    /** SHPG_SVC_LVL_DESC_TXT_A1*/
	public final EZDCStringItem              shpgSvcLvlDescTxt_A1;

    /** FUEL_UPCHG_TP_CD_A1*/
	public final EZDCStringItem              fuelUpchgTpCd_A1;

    /** FUEL_UPCHG_AMT_A1*/
	public final EZDCBigDecimalItem              fuelUpchgAmt_A1;

    /** ADDL_UPCHG_TP_CD_A1*/
	public final EZDCStringItem              addlUpchgTpCd_A1;

    /** ADDL_UPCHG_AMT_A1*/
	public final EZDCBigDecimalItem              addlUpchgAmt_A1;

    /** _EZUpdateUserID_A1*/
	public final EZDCStringItem              ezUpUserID_A1;

    /** XX_DT_TM_A1*/
	public final EZDCStringItem              xxDtTm_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDCStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDCStringItem              ezUpTimeZone_A1;


	/**
	 * NLGL0050_ACMsg is constructor.
	 * The initialization when the instance of NLGL0050_ACMsg is generated.
	 */
	public NLGL0050_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0050_ACMsg is constructor.
	 * The initialization when the instance of NLGL0050_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0050_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		wmsTrnspTpCd_A1 = (EZDCStringItem)newItem("wmsTrnspTpCd_A1");
		wmsCarrCd_A1 = (EZDCStringItem)newItem("wmsCarrCd_A1");
		wmsDescTxt_A1 = (EZDCStringItem)newItem("wmsDescTxt_A1");
		wmsCarrNm_A1 = (EZDCStringItem)newItem("wmsCarrNm_A1");
		carrSvcTxt_A1 = (EZDCStringItem)newItem("carrSvcTxt_A1");
		carrScacCd_A1 = (EZDCStringItem)newItem("carrScacCd_A1");
		shpgSvcLvlDescTxt_A1 = (EZDCStringItem)newItem("shpgSvcLvlDescTxt_A1");
		fuelUpchgTpCd_A1 = (EZDCStringItem)newItem("fuelUpchgTpCd_A1");
		fuelUpchgAmt_A1 = (EZDCBigDecimalItem)newItem("fuelUpchgAmt_A1");
		addlUpchgTpCd_A1 = (EZDCStringItem)newItem("addlUpchgTpCd_A1");
		addlUpchgAmt_A1 = (EZDCBigDecimalItem)newItem("addlUpchgAmt_A1");
		ezUpUserID_A1 = (EZDCStringItem)newItem("ezUpUserID_A1");
		xxDtTm_A1 = (EZDCStringItem)newItem("xxDtTm_A1");
		ezUpTime_A1 = (EZDCStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDCStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0050_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0050_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"wmsTrnspTpCd_A1", "wmsTrnspTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsCarrCd_A1", "wmsCarrCd_A1", "A1", null, TYPE_HANKAKUEISU, "40", null},
	{"wmsDescTxt_A1", "wmsDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"wmsCarrNm_A1", "wmsCarrNm_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"carrSvcTxt_A1", "carrSvcTxt_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"carrScacCd_A1", "carrScacCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"shpgSvcLvlDescTxt_A1", "shpgSvcLvlDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"fuelUpchgTpCd_A1", "fuelUpchgTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"fuelUpchgAmt_A1", "fuelUpchgAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"addlUpchgTpCd_A1", "addlUpchgTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"addlUpchgAmt_A1", "addlUpchgAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ezUpUserID_A1", "ezUpUserID_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"xxDtTm_A1", "xxDtTm_A1", "A1", null, TYPE_HANKAKUEISU, "23", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"WMS_TRNSP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsTrnspTpCd_A1
        {"WMS_CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCarrCd_A1
        {"WMS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDescTxt_A1
        {"WMS_CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsCarrNm_A1
        {"CARR_SVC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrSvcTxt_A1
        {"CARR_SCAC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrScacCd_A1
        {"SHPG_SVC_LVL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlDescTxt_A1
        {"FUEL_UPCHG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fuelUpchgTpCd_A1
        {"FUEL_UPCHG_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fuelUpchgAmt_A1
        {"ADDL_UPCHG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlUpchgTpCd_A1
        {"ADDL_UPCHG_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlUpchgAmt_A1
        {"_EZUpdateUserID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpUserID_A1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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
