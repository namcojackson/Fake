//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170307132046000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2520_ACMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL2520 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2520_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** ORG_CD_A1*/
	public final EZDCStringItem              orgCd_A1;

    /** ORG_NM_A1*/
	public final EZDCStringItem              orgNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDCDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDCDateItem              effThruDt_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDCStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDCStringItem              ezUpTimeZone_A1;

    /** FIRST_ORG_CD_A1*/
	public final EZDCStringItem              firstOrgCd_A1;

    /** ORG_TIER_CD_A1*/
	public final EZDCStringItem              orgTierCd_A1;

    /** GNRN_TP_CD_A1*/
	public final EZDCStringItem              gnrnTpCd_A1;

    /** ORG_CD_AB*/
	public final EZDCStringItem              orgCd_AB;

    /** ORG_NM_AB*/
	public final EZDCStringItem              orgNm_AB;

    /** EFF_FROM_DT_AB*/
	public final EZDCDateItem              effFromDt_AB;

    /** EFF_THRU_DT_AB*/
	public final EZDCDateItem              effThruDt_AB;

    /** FIRST_ORG_CD_AB*/
	public final EZDCStringItem              firstOrgCd_AB;

    /** ORG_TIER_CD_AB*/
	public final EZDCStringItem              orgTierCd_AB;

    /** GNRN_TP_CD_AB*/
	public final EZDCStringItem              gnrnTpCd_AB;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDCStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDCStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDCStringItem              xxRecHistTblNm_A1;

    /** DS_ORG_RELN_PK_A1*/
	public final EZDCBigDecimalItem              dsOrgRelnPk_A1;


	/**
	 * NMAL2520_ACMsg is constructor.
	 * The initialization when the instance of NMAL2520_ACMsg is generated.
	 */
	public NMAL2520_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2520_ACMsg is constructor.
	 * The initialization when the instance of NMAL2520_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2520_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		orgCd_A1 = (EZDCStringItem)newItem("orgCd_A1");
		orgNm_A1 = (EZDCStringItem)newItem("orgNm_A1");
		effFromDt_A1 = (EZDCDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDCDateItem)newItem("effThruDt_A1");
		ezUpTime_A1 = (EZDCStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDCStringItem)newItem("ezUpTimeZone_A1");
		firstOrgCd_A1 = (EZDCStringItem)newItem("firstOrgCd_A1");
		orgTierCd_A1 = (EZDCStringItem)newItem("orgTierCd_A1");
		gnrnTpCd_A1 = (EZDCStringItem)newItem("gnrnTpCd_A1");
		orgCd_AB = (EZDCStringItem)newItem("orgCd_AB");
		orgNm_AB = (EZDCStringItem)newItem("orgNm_AB");
		effFromDt_AB = (EZDCDateItem)newItem("effFromDt_AB");
		effThruDt_AB = (EZDCDateItem)newItem("effThruDt_AB");
		firstOrgCd_AB = (EZDCStringItem)newItem("firstOrgCd_AB");
		orgTierCd_AB = (EZDCStringItem)newItem("orgTierCd_AB");
		gnrnTpCd_AB = (EZDCStringItem)newItem("gnrnTpCd_AB");
		xxRecHistCratTs_A1 = (EZDCStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDCStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDCStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDCStringItem)newItem("xxRecHistTblNm_A1");
		dsOrgRelnPk_A1 = (EZDCBigDecimalItem)newItem("dsOrgRelnPk_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2520_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2520_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"orgCd_A1", "orgCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_A1", "orgNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"firstOrgCd_A1", "firstOrgCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgTierCd_A1", "orgTierCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"gnrnTpCd_A1", "gnrnTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"orgCd_AB", "orgCd_AB", "AB", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_AB", "orgNm_AB", "AB", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_AB", "effFromDt_AB", "AB", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_AB", "effThruDt_AB", "AB", null, TYPE_NENTSUKIHI, "8", null},
	{"firstOrgCd_AB", "firstOrgCd_AB", "AB", null, TYPE_HANKAKUEISU, "8", null},
	{"orgTierCd_AB", "orgTierCd_AB", "AB", null, TYPE_HANKAKUEISU, "2", null},
	{"gnrnTpCd_AB", "gnrnTpCd_AB", "AB", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"dsOrgRelnPk_A1", "dsOrgRelnPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_A1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_A1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"FIRST_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstOrgCd_A1
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_A1
        {"GNRN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_A1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_AB
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_AB
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_AB
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_AB
        {"FIRST_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstOrgCd_AB
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_AB
        {"GNRN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_AB
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
        {"DS_ORG_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrgRelnPk_A1
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
