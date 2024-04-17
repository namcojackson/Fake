//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160719193042000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0120_DCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0120 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0120_DCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLT_WRK_ITEM_CD_D*/
	public final EZDCStringItem              cltWrkItemCd_D;

    /** CLT_WRK_ITEM_NM_D*/
	public final EZDCStringItem              cltWrkItemNm_D;

    /** _EZUpdateDateTime_D*/
	public final EZDCStringItem              ezUpTime_D;

    /** _EZUpTimeZone_D*/
	public final EZDCStringItem              ezUpTimeZone_D;

    /** XX_TP_CD_D*/
	public final EZDCStringItem              xxTpCd_D;


	/**
	 * NFDL0120_DCMsg is constructor.
	 * The initialization when the instance of NFDL0120_DCMsg is generated.
	 */
	public NFDL0120_DCMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0120_DCMsg is constructor.
	 * The initialization when the instance of NFDL0120_DCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0120_DCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cltWrkItemCd_D = (EZDCStringItem)newItem("cltWrkItemCd_D");
		cltWrkItemNm_D = (EZDCStringItem)newItem("cltWrkItemNm_D");
		ezUpTime_D = (EZDCStringItem)newItem("ezUpTime_D");
		ezUpTimeZone_D = (EZDCStringItem)newItem("ezUpTimeZone_D");
		xxTpCd_D = (EZDCStringItem)newItem("xxTpCd_D");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0120_DCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0120_DCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cltWrkItemCd_D", "cltWrkItemCd_D", "D", null, TYPE_HANKAKUEISU, "28", null},
	{"cltWrkItemNm_D", "cltWrkItemNm_D", "D", null, TYPE_HANKAKUEISU, "100", null},
	{"ezUpTime_D", "ezUpTime_D", "D", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D", "ezUpTimeZone_D", "D", null, TYPE_HANKAKUEISU, "5", null},
	{"xxTpCd_D", "xxTpCd_D", "D", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CLT_WRK_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkItemCd_D
        {"CLT_WRK_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkItemNm_D
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D
        {"XX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_D
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

