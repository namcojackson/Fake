//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190213100019000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2510_ZCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2510 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2510_ZCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PSN_CD_Z1*/
	public final EZDCStringItem              psnCd_Z1;

    /** REV_ACCT_TP_CD_Z1*/
	public final EZDCStringItem              revAcctTpCd_Z1;

    /** EFF_FROM_DT_Z1*/
	public final EZDCDateItem              effFromDt_Z1;

    /** _EZUpdateDateTime_Z1*/
	public final EZDCStringItem              ezUpTime_Z1;

    /** _EZUpTimeZone_Z1*/
	public final EZDCStringItem              ezUpTimeZone_Z1;


	/**
	 * NMAL2510_ZCMsg is constructor.
	 * The initialization when the instance of NMAL2510_ZCMsg is generated.
	 */
	public NMAL2510_ZCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2510_ZCMsg is constructor.
	 * The initialization when the instance of NMAL2510_ZCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2510_ZCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		psnCd_Z1 = (EZDCStringItem)newItem("psnCd_Z1");
		revAcctTpCd_Z1 = (EZDCStringItem)newItem("revAcctTpCd_Z1");
		effFromDt_Z1 = (EZDCDateItem)newItem("effFromDt_Z1");
		ezUpTime_Z1 = (EZDCStringItem)newItem("ezUpTime_Z1");
		ezUpTimeZone_Z1 = (EZDCStringItem)newItem("ezUpTimeZone_Z1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2510_ZCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2510_ZCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"psnCd_Z1", "psnCd_Z1", "Z1", null, TYPE_HANKAKUEISU, "8", null},
	{"revAcctTpCd_Z1", "revAcctTpCd_Z1", "Z1", null, TYPE_HANKAKUEISU, "5", null},
	{"effFromDt_Z1", "effFromDt_Z1", "Z1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_Z1", "ezUpTime_Z1", "Z1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_Z1", "ezUpTimeZone_Z1", "Z1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_Z1
        {"REV_ACCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//revAcctTpCd_Z1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_Z1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_Z1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_Z1
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
