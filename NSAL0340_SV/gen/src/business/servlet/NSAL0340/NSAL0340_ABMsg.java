//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170530103912000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0340_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0340;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0340 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0340_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SKIP_RECOV_MTH_A0*/
	public final EZDBStringItem              skipRecovMth_A0;

    /** XX_DTL_NM_A0*/
	public final EZDBStringItem              xxDtlNm_A0;

    /** SKIP_RECOV_TP_CD_A0*/
	public final EZDBStringItem              skipRecovTpCd_A0;

    /** _EZUpTimeZone_A0*/
	public final EZDBStringItem              ezUpTimeZone_A0;

    /** _EZUpdateDateTime_A0*/
	public final EZDBStringItem              ezUpTime_A0;

    /** XX_REC_HIST_CRAT_TS_A0*/
	public final EZDBStringItem              xxRecHistCratTs_A0;

    /** XX_REC_HIST_CRAT_BY_NM_A0*/
	public final EZDBStringItem              xxRecHistCratByNm_A0;

    /** XX_REC_HIST_UPD_TS_A0*/
	public final EZDBStringItem              xxRecHistUpdTs_A0;

    /** XX_REC_HIST_UPD_BY_NM_A0*/
	public final EZDBStringItem              xxRecHistUpdByNm_A0;

    /** XX_REC_HIST_TBL_NM_A0*/
	public final EZDBStringItem              xxRecHistTblNm_A0;


	/**
	 * NSAL0340_ABMsg is constructor.
	 * The initialization when the instance of NSAL0340_ABMsg is generated.
	 */
	public NSAL0340_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0340_ABMsg is constructor.
	 * The initialization when the instance of NSAL0340_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0340_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		skipRecovMth_A0 = (EZDBStringItem)newItem("skipRecovMth_A0");
		xxDtlNm_A0 = (EZDBStringItem)newItem("xxDtlNm_A0");
		skipRecovTpCd_A0 = (EZDBStringItem)newItem("skipRecovTpCd_A0");
		ezUpTimeZone_A0 = (EZDBStringItem)newItem("ezUpTimeZone_A0");
		ezUpTime_A0 = (EZDBStringItem)newItem("ezUpTime_A0");
		xxRecHistCratTs_A0 = (EZDBStringItem)newItem("xxRecHistCratTs_A0");
		xxRecHistCratByNm_A0 = (EZDBStringItem)newItem("xxRecHistCratByNm_A0");
		xxRecHistUpdTs_A0 = (EZDBStringItem)newItem("xxRecHistUpdTs_A0");
		xxRecHistUpdByNm_A0 = (EZDBStringItem)newItem("xxRecHistUpdByNm_A0");
		xxRecHistTblNm_A0 = (EZDBStringItem)newItem("xxRecHistTblNm_A0");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0340_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0340_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"skipRecovMth_A0", "skipRecovMth_A0", "A0", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxDtlNm_A0", "xxDtlNm_A0", "A0", null, TYPE_HANKAKUEISU, "70", null},
	{"skipRecovTpCd_A0", "skipRecovTpCd_A0", "A0", null, TYPE_HANKAKUEISU, "2", null},
	{"ezUpTimeZone_A0", "ezUpTimeZone_A0", "A0", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_A0", "ezUpTime_A0", "A0", null, TYPE_HANKAKUEISU, "17", null},
	{"xxRecHistCratTs_A0", "xxRecHistCratTs_A0", "A0", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A0", "xxRecHistCratByNm_A0", "A0", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A0", "xxRecHistUpdTs_A0", "A0", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A0", "xxRecHistUpdByNm_A0", "A0", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A0", "xxRecHistTblNm_A0", "A0", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SKIP_RECOV_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//skipRecovMth_A0
        {"XX_DTL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNm_A0
        {"SKIP_RECOV_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//skipRecovTpCd_A0
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A0
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A0
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A0
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A0
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A0
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A0
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A0
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
