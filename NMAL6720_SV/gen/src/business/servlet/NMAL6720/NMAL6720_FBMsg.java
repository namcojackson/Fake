//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231108082939000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6720_FBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6720 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6720_FBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_F1*/
	public final EZDBStringItem              xxChkBox_F1;

    /** SVC_ACCS_PMIT_PK_F1*/
	public final EZDBBigDecimalItem              svcAccsPmitPk_F1;

    /** SVC_ACCS_PMIT_NUM_F1*/
	public final EZDBStringItem              svcAccsPmitNum_F1;

    /** SVC_ACCS_PMIT_DESC_TXT_F1*/
	public final EZDBStringItem              svcAccsPmitDescTxt_F1;

    /** EFF_FROM_DT_F1*/
	public final EZDBDateItem              effFromDt_F1;

    /** EFF_TO_DT_F1*/
	public final EZDBDateItem              effToDt_F1;

    /** _EZUpdateDateTime_F1*/
	public final EZDBStringItem              ezUpTime_F1;

    /** _EZUpTimeZone_F1*/
	public final EZDBStringItem              ezUpTimeZone_F1;

    /** XX_REC_HIST_CRAT_TS_F1*/
	public final EZDBStringItem              xxRecHistCratTs_F1;

    /** XX_REC_HIST_CRAT_BY_NM_F1*/
	public final EZDBStringItem              xxRecHistCratByNm_F1;

    /** XX_REC_HIST_UPD_TS_F1*/
	public final EZDBStringItem              xxRecHistUpdTs_F1;

    /** XX_REC_HIST_UPD_BY_NM_F1*/
	public final EZDBStringItem              xxRecHistUpdByNm_F1;

    /** XX_REC_HIST_TBL_NM_F1*/
	public final EZDBStringItem              xxRecHistTblNm_F1;


	/**
	 * NMAL6720_FBMsg is constructor.
	 * The initialization when the instance of NMAL6720_FBMsg is generated.
	 */
	public NMAL6720_FBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6720_FBMsg is constructor.
	 * The initialization when the instance of NMAL6720_FBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6720_FBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_F1 = (EZDBStringItem)newItem("xxChkBox_F1");
		svcAccsPmitPk_F1 = (EZDBBigDecimalItem)newItem("svcAccsPmitPk_F1");
		svcAccsPmitNum_F1 = (EZDBStringItem)newItem("svcAccsPmitNum_F1");
		svcAccsPmitDescTxt_F1 = (EZDBStringItem)newItem("svcAccsPmitDescTxt_F1");
		effFromDt_F1 = (EZDBDateItem)newItem("effFromDt_F1");
		effToDt_F1 = (EZDBDateItem)newItem("effToDt_F1");
		ezUpTime_F1 = (EZDBStringItem)newItem("ezUpTime_F1");
		ezUpTimeZone_F1 = (EZDBStringItem)newItem("ezUpTimeZone_F1");
		xxRecHistCratTs_F1 = (EZDBStringItem)newItem("xxRecHistCratTs_F1");
		xxRecHistCratByNm_F1 = (EZDBStringItem)newItem("xxRecHistCratByNm_F1");
		xxRecHistUpdTs_F1 = (EZDBStringItem)newItem("xxRecHistUpdTs_F1");
		xxRecHistUpdByNm_F1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_F1");
		xxRecHistTblNm_F1 = (EZDBStringItem)newItem("xxRecHistTblNm_F1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6720_FBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6720_FBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_F1", "xxChkBox_F1", "F1", null, TYPE_HANKAKUEIJI, "1", null},
	{"svcAccsPmitPk_F1", "svcAccsPmitPk_F1", "F1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcAccsPmitNum_F1", "svcAccsPmitNum_F1", "F1", null, TYPE_HANKAKUEISU, "6", null},
	{"svcAccsPmitDescTxt_F1", "svcAccsPmitDescTxt_F1", "F1", null, TYPE_HANKAKUEISU, "100", null},
	{"effFromDt_F1", "effFromDt_F1", "F1", null, TYPE_NENTSUKIHI, "8", null},
	{"effToDt_F1", "effToDt_F1", "F1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_F1", "ezUpTime_F1", "F1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_F1", "ezUpTimeZone_F1", "F1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_F1", "xxRecHistCratTs_F1", "F1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_F1", "xxRecHistCratByNm_F1", "F1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_F1", "xxRecHistUpdTs_F1", "F1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_F1", "xxRecHistUpdByNm_F1", "F1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_F1", "xxRecHistTblNm_F1", "F1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_F1
        {"SVC_ACCS_PMIT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitPk_F1
        {"SVC_ACCS_PMIT_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitNum_F1
        {"SVC_ACCS_PMIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitDescTxt_F1
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_F1
        {"EFF_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effToDt_F1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_F1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_F1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_F1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_F1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_F1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_F1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_F1
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
