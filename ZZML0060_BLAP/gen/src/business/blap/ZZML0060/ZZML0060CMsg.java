//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20100128193324000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0060CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZML0060 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZML0060CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_NM_S*/
	public final EZDCStringItem              xxScrNm_S;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** GLBL_CMPY_CD_S*/
	public final EZDCStringItem              glblCmpyCd_S;

    /** ML_GRP_ID_S*/
	public final EZDCStringItem              mlGrpId_S;

    /** ML_GRP_NM_S*/
	public final EZDCStringItem              mlGrpNm_S;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.ZZML0060.ZZML0060_ACMsgArray              A;

    /** XX_SCR_NM_01*/
	public final EZDCStringItem              xxScrNm_01;

    /** XX_NUM_01*/
	public final EZDCBigDecimalItem              xxNum_01;

    /** GLBL_CMPY_CD_01*/
	public final EZDCStringItem              glblCmpyCd_01;

    /** ML_GRP_ID_01*/
	public final EZDCStringItem              mlGrpId_01;

    /** ML_GRP_NM_01*/
	public final EZDCStringItem              mlGrpNm_01;

    /** ML_KEY_FIRST_NM_01*/
	public final EZDCStringItem              mlKeyFirstNm_01;

    /** ML_KEY_SCD_NM_01*/
	public final EZDCStringItem              mlKeyScdNm_01;

    /** ML_KEY_THIRD_NM_01*/
	public final EZDCStringItem              mlKeyThirdNm_01;

    /** ML_GRP_DESC_TXT_01*/
	public final EZDCStringItem              mlGrpDescTxt_01;

    /** _EZUpdateDateTime_01*/
	public final EZDCStringItem              ezUpTime_01;

    /** _EZUpTimeZone_01*/
	public final EZDCStringItem              ezUpTimeZone_01;


	/**
	 * ZZML0060CMsg is constructor.
	 * The initialization when the instance of ZZML0060CMsg is generated.
	 */
	public ZZML0060CMsg() {
		this(false, -1);
	}

	/**
	 * ZZML0060CMsg is constructor.
	 * The initialization when the instance of ZZML0060CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZML0060CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrNm_S = (EZDCStringItem)newItem("xxScrNm_S");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		glblCmpyCd_S = (EZDCStringItem)newItem("glblCmpyCd_S");
		mlGrpId_S = (EZDCStringItem)newItem("mlGrpId_S");
		mlGrpNm_S = (EZDCStringItem)newItem("mlGrpNm_S");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.ZZML0060.ZZML0060_ACMsgArray)newMsgArray("A");
		xxScrNm_01 = (EZDCStringItem)newItem("xxScrNm_01");
		xxNum_01 = (EZDCBigDecimalItem)newItem("xxNum_01");
		glblCmpyCd_01 = (EZDCStringItem)newItem("glblCmpyCd_01");
		mlGrpId_01 = (EZDCStringItem)newItem("mlGrpId_01");
		mlGrpNm_01 = (EZDCStringItem)newItem("mlGrpNm_01");
		mlKeyFirstNm_01 = (EZDCStringItem)newItem("mlKeyFirstNm_01");
		mlKeyScdNm_01 = (EZDCStringItem)newItem("mlKeyScdNm_01");
		mlKeyThirdNm_01 = (EZDCStringItem)newItem("mlKeyThirdNm_01");
		mlGrpDescTxt_01 = (EZDCStringItem)newItem("mlGrpDescTxt_01");
		ezUpTime_01 = (EZDCStringItem)newItem("ezUpTime_01");
		ezUpTimeZone_01 = (EZDCStringItem)newItem("ezUpTimeZone_01");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZML0060CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZML0060CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrNm_S", "xxScrNm_S", "S", null, TYPE_HANKAKUEISU, "70", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"glblCmpyCd_S", "glblCmpyCd_S", "S", null, TYPE_HANKAKUEISU, "4", null},
	{"mlGrpId_S", "mlGrpId_S", "S", null, TYPE_HANKAKUEISU, "20", null},
	{"mlGrpNm_S", "mlGrpNm_S", "S", null, TYPE_HANKAKUEISU, "100", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "40", "business.blap.ZZML0060.ZZML0060_ACMsgArray", null, null},
	
	{"xxScrNm_01", "xxScrNm_01", "01", null, TYPE_HANKAKUEISU, "70", null},
	{"xxNum_01", "xxNum_01", "01", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"glblCmpyCd_01", "glblCmpyCd_01", "01", null, TYPE_HANKAKUEISU, "4", null},
	{"mlGrpId_01", "mlGrpId_01", "01", null, TYPE_HANKAKUEISU, "20", null},
	{"mlGrpNm_01", "mlGrpNm_01", "01", null, TYPE_HANKAKUEISU, "100", null},
	{"mlKeyFirstNm_01", "mlKeyFirstNm_01", "01", null, TYPE_HANKAKUEISU, "100", null},
	{"mlKeyScdNm_01", "mlKeyScdNm_01", "01", null, TYPE_HANKAKUEISU, "100", null},
	{"mlKeyThirdNm_01", "mlKeyThirdNm_01", "01", null, TYPE_HANKAKUEISU, "100", null},
	{"mlGrpDescTxt_01", "mlGrpDescTxt_01", "01", null, TYPE_HANKAKUEISU, "300", null},
	{"ezUpTime_01", "ezUpTime_01", "01", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_01", "ezUpTimeZone_01", "01", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm_S
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_S
        {"ML_GRP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpId_S
        {"ML_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpNm_S
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm_01
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_01
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_01
        {"ML_GRP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpId_01
        {"ML_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpNm_01
        {"ML_KEY_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlKeyFirstNm_01
        {"ML_KEY_SCD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlKeyScdNm_01
        {"ML_KEY_THIRD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlKeyThirdNm_01
        {"ML_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpDescTxt_01
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_01
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_01
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

