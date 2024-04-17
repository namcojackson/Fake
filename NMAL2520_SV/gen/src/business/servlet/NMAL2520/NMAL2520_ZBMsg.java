//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170307132137000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2520_ZBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2520;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2520 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2520_ZBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TOC_CD_Z1*/
	public final EZDBStringItem              tocCd_Z1;

    /** PSN_CD_Z1*/
	public final EZDBStringItem              psnCd_Z1;

    /** EFF_FROM_DT_Z1*/
	public final EZDBDateItem              effFromDt_Z1;

    /** _EZUpdateDateTime_Z1*/
	public final EZDBStringItem              ezUpTime_Z1;

    /** _EZUpTimeZone_Z1*/
	public final EZDBStringItem              ezUpTimeZone_Z1;


	/**
	 * NMAL2520_ZBMsg is constructor.
	 * The initialization when the instance of NMAL2520_ZBMsg is generated.
	 */
	public NMAL2520_ZBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2520_ZBMsg is constructor.
	 * The initialization when the instance of NMAL2520_ZBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2520_ZBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		tocCd_Z1 = (EZDBStringItem)newItem("tocCd_Z1");
		psnCd_Z1 = (EZDBStringItem)newItem("psnCd_Z1");
		effFromDt_Z1 = (EZDBDateItem)newItem("effFromDt_Z1");
		ezUpTime_Z1 = (EZDBStringItem)newItem("ezUpTime_Z1");
		ezUpTimeZone_Z1 = (EZDBStringItem)newItem("ezUpTimeZone_Z1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2520_ZBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2520_ZBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"tocCd_Z1", "tocCd_Z1", "Z1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnCd_Z1", "psnCd_Z1", "Z1", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_Z1", "effFromDt_Z1", "Z1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_Z1", "ezUpTime_Z1", "Z1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_Z1", "ezUpTimeZone_Z1", "Z1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_Z1
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_Z1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_Z1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_Z1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_Z1
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
