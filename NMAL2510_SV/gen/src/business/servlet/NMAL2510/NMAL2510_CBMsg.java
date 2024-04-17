//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190213095852000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2510_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2510 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2510_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_C2*/
	public final EZDBStringItem              xxChkBox_C2;

    /** REV_ACCT_TP_CD_P1*/
	public final EZDBStringItem              revAcctTpCd_P1;

    /** COA_CMPY_CD_C2*/
	public final EZDBStringItem              coaCmpyCd_C2;

    /** COA_EXTN_CD_C2*/
	public final EZDBStringItem              coaExtnCd_C2;

    /** COA_BR_CD_C2*/
	public final EZDBStringItem              coaBrCd_C2;

    /** COA_CC_CD_C2*/
	public final EZDBStringItem              coaCcCd_C2;

    /** EFF_FROM_DT_C2*/
	public final EZDBDateItem              effFromDt_C2;

    /** EFF_THRU_DT_C2*/
	public final EZDBDateItem              effThruDt_C2;

    /** XX_CHK_BOX_C3*/
	public final EZDBStringItem              xxChkBox_C3;

    /** MAN_ENTRY_FLG_C2*/
	public final EZDBStringItem              manEntryFlg_C2;

    /** _EZUpdateDateTime_C3*/
	public final EZDBStringItem              ezUpTime_C3;

    /** _EZUpTimeZone_C3*/
	public final EZDBStringItem              ezUpTimeZone_C3;

    /** GNRN_TP_CD_C2*/
	public final EZDBStringItem              gnrnTpCd_C2;

    /** REV_COA_FLG_C2*/
	public final EZDBStringItem              revCoaFlg_C2;

    /** REV_ACCT_TP_CD_CB*/
	public final EZDBStringItem              revAcctTpCd_CB;

    /** COA_CMPY_CD_CB*/
	public final EZDBStringItem              coaCmpyCd_CB;

    /** COA_EXTN_CD_CB*/
	public final EZDBStringItem              coaExtnCd_CB;

    /** COA_BR_CD_CB*/
	public final EZDBStringItem              coaBrCd_CB;

    /** COA_CC_CD_CB*/
	public final EZDBStringItem              coaCcCd_CB;

    /** EFF_FROM_DT_CB*/
	public final EZDBDateItem              effFromDt_CB;

    /** EFF_THRU_DT_CB*/
	public final EZDBDateItem              effThruDt_CB;

    /** XX_CHK_BOX_CB*/
	public final EZDBStringItem              xxChkBox_CB;

    /** XX_REC_HIST_CRAT_TS_C1*/
	public final EZDBStringItem              xxRecHistCratTs_C1;

    /** XX_REC_HIST_CRAT_BY_NM_C1*/
	public final EZDBStringItem              xxRecHistCratByNm_C1;

    /** XX_REC_HIST_UPD_TS_C1*/
	public final EZDBStringItem              xxRecHistUpdTs_C1;

    /** XX_REC_HIST_UPD_BY_NM_C1*/
	public final EZDBStringItem              xxRecHistUpdByNm_C1;

    /** XX_REC_HIST_TBL_NM_C1*/
	public final EZDBStringItem              xxRecHistTblNm_C1;

    /** TOC_RGTN_REQ_FLG_C2*/
	public final EZDBStringItem              tocRgtnReqFlg_C2;


	/**
	 * NMAL2510_CBMsg is constructor.
	 * The initialization when the instance of NMAL2510_CBMsg is generated.
	 */
	public NMAL2510_CBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2510_CBMsg is constructor.
	 * The initialization when the instance of NMAL2510_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2510_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_C2 = (EZDBStringItem)newItem("xxChkBox_C2");
		revAcctTpCd_P1 = (EZDBStringItem)newItem("revAcctTpCd_P1");
		coaCmpyCd_C2 = (EZDBStringItem)newItem("coaCmpyCd_C2");
		coaExtnCd_C2 = (EZDBStringItem)newItem("coaExtnCd_C2");
		coaBrCd_C2 = (EZDBStringItem)newItem("coaBrCd_C2");
		coaCcCd_C2 = (EZDBStringItem)newItem("coaCcCd_C2");
		effFromDt_C2 = (EZDBDateItem)newItem("effFromDt_C2");
		effThruDt_C2 = (EZDBDateItem)newItem("effThruDt_C2");
		xxChkBox_C3 = (EZDBStringItem)newItem("xxChkBox_C3");
		manEntryFlg_C2 = (EZDBStringItem)newItem("manEntryFlg_C2");
		ezUpTime_C3 = (EZDBStringItem)newItem("ezUpTime_C3");
		ezUpTimeZone_C3 = (EZDBStringItem)newItem("ezUpTimeZone_C3");
		gnrnTpCd_C2 = (EZDBStringItem)newItem("gnrnTpCd_C2");
		revCoaFlg_C2 = (EZDBStringItem)newItem("revCoaFlg_C2");
		revAcctTpCd_CB = (EZDBStringItem)newItem("revAcctTpCd_CB");
		coaCmpyCd_CB = (EZDBStringItem)newItem("coaCmpyCd_CB");
		coaExtnCd_CB = (EZDBStringItem)newItem("coaExtnCd_CB");
		coaBrCd_CB = (EZDBStringItem)newItem("coaBrCd_CB");
		coaCcCd_CB = (EZDBStringItem)newItem("coaCcCd_CB");
		effFromDt_CB = (EZDBDateItem)newItem("effFromDt_CB");
		effThruDt_CB = (EZDBDateItem)newItem("effThruDt_CB");
		xxChkBox_CB = (EZDBStringItem)newItem("xxChkBox_CB");
		xxRecHistCratTs_C1 = (EZDBStringItem)newItem("xxRecHistCratTs_C1");
		xxRecHistCratByNm_C1 = (EZDBStringItem)newItem("xxRecHistCratByNm_C1");
		xxRecHistUpdTs_C1 = (EZDBStringItem)newItem("xxRecHistUpdTs_C1");
		xxRecHistUpdByNm_C1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_C1");
		xxRecHistTblNm_C1 = (EZDBStringItem)newItem("xxRecHistTblNm_C1");
		tocRgtnReqFlg_C2 = (EZDBStringItem)newItem("tocRgtnReqFlg_C2");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2510_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2510_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_C2", "xxChkBox_C2", "C2", null, TYPE_HANKAKUEIJI, "1", null},
	{"revAcctTpCd_P1", "revAcctTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "5", null},
	{"coaCmpyCd_C2", "coaCmpyCd_C2", "C2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaExtnCd_C2", "coaExtnCd_C2", "C2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_C2", "coaBrCd_C2", "C2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_C2", "coaCcCd_C2", "C2", null, TYPE_HANKAKUEISU, "6", null},
	{"effFromDt_C2", "effFromDt_C2", "C2", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_C2", "effThruDt_C2", "C2", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_C3", "xxChkBox_C3", "C3", null, TYPE_HANKAKUEIJI, "1", null},
	{"manEntryFlg_C2", "manEntryFlg_C2", "C2", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_C3", "ezUpTime_C3", "C3", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_C3", "ezUpTimeZone_C3", "C3", null, TYPE_HANKAKUEISU, "5", null},
	{"gnrnTpCd_C2", "gnrnTpCd_C2", "C2", null, TYPE_HANKAKUEISU, "1", null},
	{"revCoaFlg_C2", "revCoaFlg_C2", "C2", null, TYPE_HANKAKUEISU, "1", null},
	{"revAcctTpCd_CB", "revAcctTpCd_CB", "CB", null, TYPE_HANKAKUEISU, "5", null},
	{"coaCmpyCd_CB", "coaCmpyCd_CB", "CB", null, TYPE_HANKAKUEISU, "3", null},
	{"coaExtnCd_CB", "coaExtnCd_CB", "CB", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_CB", "coaBrCd_CB", "CB", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_CB", "coaCcCd_CB", "CB", null, TYPE_HANKAKUEISU, "6", null},
	{"effFromDt_CB", "effFromDt_CB", "CB", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_CB", "effThruDt_CB", "CB", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_CB", "xxChkBox_CB", "CB", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxRecHistCratTs_C1", "xxRecHistCratTs_C1", "C1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_C1", "xxRecHistCratByNm_C1", "C1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_C1", "xxRecHistUpdTs_C1", "C1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_C1", "xxRecHistUpdByNm_C1", "C1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_C1", "xxRecHistTblNm_C1", "C1", null, TYPE_HANKAKUEISU, "60", null},
	{"tocRgtnReqFlg_C2", "tocRgtnReqFlg_C2", "C2", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C2
        {"REV_ACCT_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//revAcctTpCd_P1
        {"COA_CMPY_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_C2
        {"COA_EXTN_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_C2
        {"COA_BR_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_C2
        {"COA_CC_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_C2
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_C2
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_C2
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C3
        {"MAN_ENTRY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manEntryFlg_C2
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_C3
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_C3
        {"GNRN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_C2
        {"REV_COA_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//revCoaFlg_C2
        {"REV_ACCT_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//revAcctTpCd_CB
        {"COA_CMPY_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_CB
        {"COA_EXTN_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_CB
        {"COA_BR_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_CB
        {"COA_CC_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_CB
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_CB
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_CB
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_CB
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_C1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_C1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_C1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_C1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_C1
        {"TOC_RGTN_REQ_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocRgtnReqFlg_C2
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
