//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171117183034000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2610_ZBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2610;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2610 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2610_ZBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_CD_R3*/
	public final EZDBStringItem              orgCd_R3;

    /** ORG_STRU_TP_CD_R3*/
	public final EZDBStringItem              orgStruTpCd_R3;

    /** PSN_CD_R3*/
	public final EZDBStringItem              psnCd_R3;

    /** ORG_FUNC_ROLE_TP_CD_R3*/
	public final EZDBStringItem              orgFuncRoleTpCd_R3;

    /** EFF_FROM_DT_R3*/
	public final EZDBDateItem              effFromDt_R3;

    /** _EZUpdateDateTime_R3*/
	public final EZDBStringItem              ezUpTime_R3;

    /** _EZUpTimeZone_R3*/
	public final EZDBStringItem              ezUpTimeZone_R3;


	/**
	 * NMAL2610_ZBMsg is constructor.
	 * The initialization when the instance of NMAL2610_ZBMsg is generated.
	 */
	public NMAL2610_ZBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2610_ZBMsg is constructor.
	 * The initialization when the instance of NMAL2610_ZBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2610_ZBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgCd_R3 = (EZDBStringItem)newItem("orgCd_R3");
		orgStruTpCd_R3 = (EZDBStringItem)newItem("orgStruTpCd_R3");
		psnCd_R3 = (EZDBStringItem)newItem("psnCd_R3");
		orgFuncRoleTpCd_R3 = (EZDBStringItem)newItem("orgFuncRoleTpCd_R3");
		effFromDt_R3 = (EZDBDateItem)newItem("effFromDt_R3");
		ezUpTime_R3 = (EZDBStringItem)newItem("ezUpTime_R3");
		ezUpTimeZone_R3 = (EZDBStringItem)newItem("ezUpTimeZone_R3");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2610_ZBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2610_ZBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgCd_R3", "orgCd_R3", "R3", null, TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpCd_R3", "orgStruTpCd_R3", "R3", null, TYPE_HANKAKUEISU, "8", null},
	{"psnCd_R3", "psnCd_R3", "R3", null, TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpCd_R3", "orgFuncRoleTpCd_R3", "R3", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_R3", "effFromDt_R3", "R3", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_R3", "ezUpTime_R3", "R3", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_R3", "ezUpTimeZone_R3", "R3", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_R3
        {"ORG_STRU_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_R3
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_R3
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_R3
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_R3
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_R3
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_R3
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

