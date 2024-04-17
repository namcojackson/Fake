//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160414180536000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1670_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1670;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1670 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1670_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_A*/
	public final EZDCBigDecimalItem              xxRowNum_A;

    /** ORD_TEAM_MSTR_HDR_PK_A*/
	public final EZDCBigDecimalItem              ordTeamMstrHdrPk_A;

    /** ORD_TEAM_MSTR_NM_A*/
	public final EZDCStringItem              ordTeamMstrNm_A;

    /** ORD_ZN_CD_A*/
	public final EZDCStringItem              ordZnCd_A;

    /** EFF_FROM_DT_A*/
	public final EZDCDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDCDateItem              effThruDt_A;


	/**
	 * NWAL1670_ACMsg is constructor.
	 * The initialization when the instance of NWAL1670_ACMsg is generated.
	 */
	public NWAL1670_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1670_ACMsg is constructor.
	 * The initialization when the instance of NWAL1670_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1670_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_A = (EZDCBigDecimalItem)newItem("xxRowNum_A");
		ordTeamMstrHdrPk_A = (EZDCBigDecimalItem)newItem("ordTeamMstrHdrPk_A");
		ordTeamMstrNm_A = (EZDCStringItem)newItem("ordTeamMstrNm_A");
		ordZnCd_A = (EZDCStringItem)newItem("ordZnCd_A");
		effFromDt_A = (EZDCDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDCDateItem)newItem("effThruDt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1670_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1670_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ordTeamMstrHdrPk_A", "ordTeamMstrHdrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ordTeamMstrNm_A", "ordTeamMstrNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"ordZnCd_A", "ordZnCd_A", "A", null, TYPE_HANKAKUEISU, "6", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"ORD_TEAM_MSTR_HDR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTeamMstrHdrPk_A
        {"ORD_TEAM_MSTR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTeamMstrNm_A
        {"ORD_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordZnCd_A
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A
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
