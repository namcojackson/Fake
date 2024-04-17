//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170223093719000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2530_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2530;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2530 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2530_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_CD_A1*/
	public final EZDBStringItem              orgCd_A1;

    /** ORG_NM_A2*/
	public final EZDBStringItem              orgNm_A2;

    /** ORG_NM_A1*/
	public final EZDBStringItem              orgNm_A1;

    /** ORG_TIER_NM_A1*/
	public final EZDBStringItem              orgTierNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDBDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDBDateItem              effThruDt_A1;


	/**
	 * NMAL2530_ABMsg is constructor.
	 * The initialization when the instance of NMAL2530_ABMsg is generated.
	 */
	public NMAL2530_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2530_ABMsg is constructor.
	 * The initialization when the instance of NMAL2530_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2530_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgCd_A1 = (EZDBStringItem)newItem("orgCd_A1");
		orgNm_A2 = (EZDBStringItem)newItem("orgNm_A2");
		orgNm_A1 = (EZDBStringItem)newItem("orgNm_A1");
		orgTierNm_A1 = (EZDBStringItem)newItem("orgTierNm_A1");
		effFromDt_A1 = (EZDBDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDBDateItem)newItem("effThruDt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2530_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2530_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgCd_A1", "orgCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_A2", "orgNm_A2", "A2", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_A1", "orgNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgTierNm_A1", "orgTierNm_A1", "A1", null, TYPE_HANKAKUEISU, "70", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_A1
        {"ORG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_A2
        {"ORG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_A1
        {"ORG_TIER_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierNm_A1
        {"EFF_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A1
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
