//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230901080022000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1160_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1160 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1160_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** APVL_TEAM_PK_A1*/
	public final EZDCBigDecimalItem              apvlTeamPk_A1;

    /** APVL_TEAM_NM_A1*/
	public final EZDCStringItem              apvlTeamNm_A1;

    /** APVL_TEAM_DESC_TXT_A1*/
	public final EZDCStringItem              apvlTeamDescTxt_A1;

    /** APVL_HRCH_TP_CD_AC*/
	public final EZDCStringItemArray              apvlHrchTpCd_AC;

    /** APVL_HRCH_TP_DESC_TXT_AD*/
	public final EZDCStringItemArray              apvlHrchTpDescTxt_AD;

    /** APVL_HRCH_TP_CD_AS*/
	public final EZDCStringItem              apvlHrchTpCd_AS;

    /** DELETE_FLAG_IF_A1*/
	public final EZDCStringItem              deleteFlagIf_A1;


	/**
	 * NPAL1160_ACMsg is constructor.
	 * The initialization when the instance of NPAL1160_ACMsg is generated.
	 */
	public NPAL1160_ACMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1160_ACMsg is constructor.
	 * The initialization when the instance of NPAL1160_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1160_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		apvlTeamPk_A1 = (EZDCBigDecimalItem)newItem("apvlTeamPk_A1");
		apvlTeamNm_A1 = (EZDCStringItem)newItem("apvlTeamNm_A1");
		apvlTeamDescTxt_A1 = (EZDCStringItem)newItem("apvlTeamDescTxt_A1");
		apvlHrchTpCd_AC = (EZDCStringItemArray)newItemArray("apvlHrchTpCd_AC");
		apvlHrchTpDescTxt_AD = (EZDCStringItemArray)newItemArray("apvlHrchTpDescTxt_AD");
		apvlHrchTpCd_AS = (EZDCStringItem)newItem("apvlHrchTpCd_AS");
		deleteFlagIf_A1 = (EZDCStringItem)newItem("deleteFlagIf_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1160_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1160_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"apvlTeamPk_A1", "apvlTeamPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"apvlTeamNm_A1", "apvlTeamNm_A1", "A1", null, TYPE_HANKAKUEISU, "80", null},
	{"apvlTeamDescTxt_A1", "apvlTeamDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "240", null},
	{"apvlHrchTpCd_AC", "apvlHrchTpCd_AC", "AC", "99", TYPE_HANKAKUEISU, "2", null},
	{"apvlHrchTpDescTxt_AD", "apvlHrchTpDescTxt_AD", "AD", "99", TYPE_HANKAKUEISU, "50", null},
	{"apvlHrchTpCd_AS", "apvlHrchTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"deleteFlagIf_A1", "deleteFlagIf_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"APVL_TEAM_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlTeamPk_A1
        {"APVL_TEAM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlTeamNm_A1
        {"APVL_TEAM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlTeamDescTxt_A1
        {"APVL_HRCH_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHrchTpCd_AC
        {"APVL_HRCH_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHrchTpDescTxt_AD
        {"APVL_HRCH_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHrchTpCd_AS
        {"DELETE_FLAG_IF",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//deleteFlagIf_A1
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
