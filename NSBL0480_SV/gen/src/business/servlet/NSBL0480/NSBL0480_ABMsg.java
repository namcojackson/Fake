//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170202132206000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0480_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0480;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0480 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0480_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TECH_CD_A*/
	public final EZDBStringItem              techCd_A;

    /** SVC_ACCS_PMIT_DESC_TXT_A*/
	public final EZDBStringItem              svcAccsPmitDescTxt_A;

    /** SVC_PMIT_LVL_TP_CD_A*/
	public final EZDBStringItem              svcPmitLvlTpCd_A;

    /** XX_SCR_ITEM_30_TXT_A*/
	public final EZDBStringItem              xxScrItem30Txt_A;

    /** EFF_FROM_DT_A*/
	public final EZDBDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDBDateItem              effThruDt_A;

    /** SVC_ACCS_PMIT_NUM_A*/
	public final EZDBStringItem              svcAccsPmitNum_A;

    /** SVC_ACCS_PMIT_TECH_RELN_PK_A*/
	public final EZDBBigDecimalItem              svcAccsPmitTechRelnPk_A;

    /** SVC_ACCS_PMIT_PK_A*/
	public final EZDBBigDecimalItem              svcAccsPmitPk_A;

    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;

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
	 * NSBL0480_ABMsg is constructor.
	 * The initialization when the instance of NSBL0480_ABMsg is generated.
	 */
	public NSBL0480_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0480_ABMsg is constructor.
	 * The initialization when the instance of NSBL0480_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0480_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		techCd_A = (EZDBStringItem)newItem("techCd_A");
		svcAccsPmitDescTxt_A = (EZDBStringItem)newItem("svcAccsPmitDescTxt_A");
		svcPmitLvlTpCd_A = (EZDBStringItem)newItem("svcPmitLvlTpCd_A");
		xxScrItem30Txt_A = (EZDBStringItem)newItem("xxScrItem30Txt_A");
		effFromDt_A = (EZDBDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDBDateItem)newItem("effThruDt_A");
		svcAccsPmitNum_A = (EZDBStringItem)newItem("svcAccsPmitNum_A");
		svcAccsPmitTechRelnPk_A = (EZDBBigDecimalItem)newItem("svcAccsPmitTechRelnPk_A");
		svcAccsPmitPk_A = (EZDBBigDecimalItem)newItem("svcAccsPmitPk_A");
		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
		xxRecHistCratTs_A = (EZDBStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDBStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDBStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDBStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDBStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0480_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0480_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"techCd_A", "techCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"svcAccsPmitDescTxt_A", "svcAccsPmitDescTxt_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"svcPmitLvlTpCd_A", "svcPmitLvlTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"xxScrItem30Txt_A", "xxScrItem30Txt_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"svcAccsPmitNum_A", "svcAccsPmitNum_A", "A", null, TYPE_HANKAKUEISU, "6", null},
	{"svcAccsPmitTechRelnPk_A", "svcAccsPmitTechRelnPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcAccsPmitPk_A", "svcAccsPmitPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
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

        {"TECH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd_A
        {"SVC_ACCS_PMIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitDescTxt_A
        {"SVC_PMIT_LVL_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPmitLvlTpCd_A
        {"XX_SCR_ITEM_30_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt_A
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A
        {"SVC_ACCS_PMIT_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitNum_A
        {"SVC_ACCS_PMIT_TECH_RELN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitTechRelnPk_A
        {"SVC_ACCS_PMIT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitPk_A
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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

