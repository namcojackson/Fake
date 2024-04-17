//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170307132047000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2520_TSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2520;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2520 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2520_TSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_CD_T*/
	public final EZDSStringItem              orgCd_T;

    /** ORG_NM_T*/
	public final EZDSStringItem              orgNm_T;

    /** ORG_TIER_CD_T*/
	public final EZDSStringItem              orgTierCd_T;

    /** ORG_NM_0*/
	public final EZDSStringItem              orgNm_0;

    /** ORG_NM_1*/
	public final EZDSStringItem              orgNm_1;

    /** ORG_NM_2*/
	public final EZDSStringItem              orgNm_2;

    /** ORG_NM_3*/
	public final EZDSStringItem              orgNm_3;

    /** ORG_NM_4*/
	public final EZDSStringItem              orgNm_4;

    /** ORG_NM_5*/
	public final EZDSStringItem              orgNm_5;

    /** ORG_NM_6*/
	public final EZDSStringItem              orgNm_6;

    /** ORG_NM_7*/
	public final EZDSStringItem              orgNm_7;

    /** ORG_NM_8*/
	public final EZDSStringItem              orgNm_8;

    /** ORG_NM_9*/
	public final EZDSStringItem              orgNm_9;

    /** ORG_NM_10*/
	public final EZDSStringItem              orgNm_10;


	/**
	 * NMAL2520_TSMsg is constructor.
	 * The initialization when the instance of NMAL2520_TSMsg is generated.
	 */
	public NMAL2520_TSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2520_TSMsg is constructor.
	 * The initialization when the instance of NMAL2520_TSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2520_TSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgCd_T = (EZDSStringItem)newItem("orgCd_T");
		orgNm_T = (EZDSStringItem)newItem("orgNm_T");
		orgTierCd_T = (EZDSStringItem)newItem("orgTierCd_T");
		orgNm_0 = (EZDSStringItem)newItem("orgNm_0");
		orgNm_1 = (EZDSStringItem)newItem("orgNm_1");
		orgNm_2 = (EZDSStringItem)newItem("orgNm_2");
		orgNm_3 = (EZDSStringItem)newItem("orgNm_3");
		orgNm_4 = (EZDSStringItem)newItem("orgNm_4");
		orgNm_5 = (EZDSStringItem)newItem("orgNm_5");
		orgNm_6 = (EZDSStringItem)newItem("orgNm_6");
		orgNm_7 = (EZDSStringItem)newItem("orgNm_7");
		orgNm_8 = (EZDSStringItem)newItem("orgNm_8");
		orgNm_9 = (EZDSStringItem)newItem("orgNm_9");
		orgNm_10 = (EZDSStringItem)newItem("orgNm_10");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2520_TSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2520_TSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgCd_T", "orgCd_T", "T", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_T", "orgNm_T", "T", null, TYPE_HANKAKUEISU, "50", null},
	{"orgTierCd_T", "orgTierCd_T", "T", null, TYPE_HANKAKUEISU, "2", null},
	{"orgNm_0", "orgNm_0", "0", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_1", "orgNm_1", "1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_2", "orgNm_2", "2", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_3", "orgNm_3", "3", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_4", "orgNm_4", "4", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_5", "orgNm_5", "5", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_6", "orgNm_6", "6", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_7", "orgNm_7", "7", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_8", "orgNm_8", "8", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_9", "orgNm_9", "9", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_10", "orgNm_10", "10", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_T
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_T
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_T
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_0
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_2
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_3
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_4
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_5
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_6
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_7
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_8
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_9
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_10
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
