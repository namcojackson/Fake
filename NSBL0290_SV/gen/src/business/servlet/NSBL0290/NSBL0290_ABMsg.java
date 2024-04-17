//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161205154955000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0290_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0290;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0290 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0290_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX*/
	public final EZDBStringItem              xxChkBox;

    /** SVC_SKILL_NUM*/
	public final EZDBStringItem              svcSkillNum;

    /** SVC_SKILL_NM*/
	public final EZDBStringItem              svcSkillNm;

    /** SVC_SKILL_DESC_TXT*/
	public final EZDBStringItem              svcSkillDescTxt;

    /** SVC_ALIAS_RATE*/
	public final EZDBBigDecimalItem              svcAliasRate;

    /** EFF_FROM_DT*/
	public final EZDBDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDBDateItem              effThruDt;

    /** _EZUpdateDateTime_B*/
	public final EZDBStringItem              ezUpTime_B;

    /** _EZUpTimeZone_B*/
	public final EZDBStringItem              ezUpTimeZone_B;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDBStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDBStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDBStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDBStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDBStringItem              xxRecHistTblNm_A;


	/**
	 * NSBL0290_ABMsg is constructor.
	 * The initialization when the instance of NSBL0290_ABMsg is generated.
	 */
	public NSBL0290_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0290_ABMsg is constructor.
	 * The initialization when the instance of NSBL0290_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0290_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox = (EZDBStringItem)newItem("xxChkBox");
		svcSkillNum = (EZDBStringItem)newItem("svcSkillNum");
		svcSkillNm = (EZDBStringItem)newItem("svcSkillNm");
		svcSkillDescTxt = (EZDBStringItem)newItem("svcSkillDescTxt");
		svcAliasRate = (EZDBBigDecimalItem)newItem("svcAliasRate");
		effFromDt = (EZDBDateItem)newItem("effFromDt");
		effThruDt = (EZDBDateItem)newItem("effThruDt");
		ezUpTime_B = (EZDBStringItem)newItem("ezUpTime_B");
		ezUpTimeZone_B = (EZDBStringItem)newItem("ezUpTimeZone_B");
		xxRecHistCratTs_A = (EZDBStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDBStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDBStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDBStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDBStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0290_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0290_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"svcSkillNum", "svcSkillNum", null, null, TYPE_HANKAKUEISU, "5", null},
	{"svcSkillNm", "svcSkillNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcSkillDescTxt", "svcSkillDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcAliasRate", "svcAliasRate", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_B", "ezUpTime_B", "B", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B", "ezUpTimeZone_B", "B", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_A", "xxRecHistCratTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A", "xxRecHistCratByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A", "xxRecHistUpdTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A", "xxRecHistUpdByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A", "xxRecHistTblNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"SVC_SKILL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillNum
        {"SVC_SKILL_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillNm
        {"SVC_SKILL_DESC_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillDescTxt
        {"SVC_ALIAS_RATE",  NO,  "0",null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//svcAliasRate
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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
