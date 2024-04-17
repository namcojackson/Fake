//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160309122718000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2510_FCMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NMAL2510_FCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BIZ_AREA_ORG_CD_F2*/
	public final EZDCStringItem              bizAreaOrgCd_F2;

    /** ORG_NM_F2*/
	public final EZDCStringItem              orgNm_F2;

    /** ACCT_TEAM_ROLE_TP_CD_F2*/
	public final EZDCStringItem              acctTeamRoleTpCd_F2;

    /** EFF_FROM_DT_F2*/
	public final EZDCDateItem              effFromDt_F2;

    /** EFF_THRU_DT_F2*/
	public final EZDCDateItem              effThruDt_F2;


	/**
	 * NMAL2510_FCMsg is constructor.
	 * The initialization when the instance of NMAL2510_FCMsg is generated.
	 */
	public NMAL2510_FCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2510_FCMsg is constructor.
	 * The initialization when the instance of NMAL2510_FCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2510_FCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		bizAreaOrgCd_F2 = (EZDCStringItem)newItem("bizAreaOrgCd_F2");
		orgNm_F2 = (EZDCStringItem)newItem("orgNm_F2");
		acctTeamRoleTpCd_F2 = (EZDCStringItem)newItem("acctTeamRoleTpCd_F2");
		effFromDt_F2 = (EZDCDateItem)newItem("effFromDt_F2");
		effThruDt_F2 = (EZDCDateItem)newItem("effThruDt_F2");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2510_FCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2510_FCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"bizAreaOrgCd_F2", "bizAreaOrgCd_F2", "F2", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_F2", "orgNm_F2", "F2", null, TYPE_HANKAKUEISU, "50", null},
	{"acctTeamRoleTpCd_F2", "acctTeamRoleTpCd_F2", "F2", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_F2", "effFromDt_F2", "F2", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_F2", "effThruDt_F2", "F2", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_F2
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_F2
        {"ACCT_TEAM_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctTeamRoleTpCd_F2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_F2
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_F2
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

