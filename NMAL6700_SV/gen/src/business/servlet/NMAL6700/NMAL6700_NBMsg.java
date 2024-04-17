//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231106144517000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700_NBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6700 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6700_NBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_ACCS_PMIT_PK_N1*/
	public final EZDBBigDecimalItem              svcAccsPmitPk_N1;

    /** XX_CHK_BOX_N1*/
	public final EZDBStringItem              xxChkBox_N1;

    /** SVC_ACCS_PMIT_NUM_N1*/
	public final EZDBStringItem              svcAccsPmitNum_N1;

    /** SVC_ACCS_PMIT_DESC_TXT_N1*/
	public final EZDBStringItem              svcAccsPmitDescTxt_N1;

    /** EFF_FROM_DT_N1*/
	public final EZDBDateItem              effFromDt_N1;

    /** EFF_TO_DT_N1*/
	public final EZDBDateItem              effToDt_N1;

    /** _EZUpdateDateTime_N1*/
	public final EZDBStringItem              ezUpTime_N1;

    /** _EZUpTimeZone_N1*/
	public final EZDBStringItem              ezUpTimeZone_N1;

    /** XX_REC_HIST_CRAT_TS_N1*/
	public final EZDBStringItem              xxRecHistCratTs_N1;

    /** XX_REC_HIST_CRAT_BY_NM_N1*/
	public final EZDBStringItem              xxRecHistCratByNm_N1;

    /** XX_REC_HIST_UPD_TS_N1*/
	public final EZDBStringItem              xxRecHistUpdTs_N1;

    /** XX_REC_HIST_UPD_BY_NM_N1*/
	public final EZDBStringItem              xxRecHistUpdByNm_N1;

    /** XX_REC_HIST_TBL_NM_N1*/
	public final EZDBStringItem              xxRecHistTblNm_N1;


	/**
	 * NMAL6700_NBMsg is constructor.
	 * The initialization when the instance of NMAL6700_NBMsg is generated.
	 */
	public NMAL6700_NBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6700_NBMsg is constructor.
	 * The initialization when the instance of NMAL6700_NBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6700_NBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcAccsPmitPk_N1 = (EZDBBigDecimalItem)newItem("svcAccsPmitPk_N1");
		xxChkBox_N1 = (EZDBStringItem)newItem("xxChkBox_N1");
		svcAccsPmitNum_N1 = (EZDBStringItem)newItem("svcAccsPmitNum_N1");
		svcAccsPmitDescTxt_N1 = (EZDBStringItem)newItem("svcAccsPmitDescTxt_N1");
		effFromDt_N1 = (EZDBDateItem)newItem("effFromDt_N1");
		effToDt_N1 = (EZDBDateItem)newItem("effToDt_N1");
		ezUpTime_N1 = (EZDBStringItem)newItem("ezUpTime_N1");
		ezUpTimeZone_N1 = (EZDBStringItem)newItem("ezUpTimeZone_N1");
		xxRecHistCratTs_N1 = (EZDBStringItem)newItem("xxRecHistCratTs_N1");
		xxRecHistCratByNm_N1 = (EZDBStringItem)newItem("xxRecHistCratByNm_N1");
		xxRecHistUpdTs_N1 = (EZDBStringItem)newItem("xxRecHistUpdTs_N1");
		xxRecHistUpdByNm_N1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_N1");
		xxRecHistTblNm_N1 = (EZDBStringItem)newItem("xxRecHistTblNm_N1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6700_NBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6700_NBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcAccsPmitPk_N1", "svcAccsPmitPk_N1", "N1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_N1", "xxChkBox_N1", "N1", null, TYPE_HANKAKUEIJI, "1", null},
	{"svcAccsPmitNum_N1", "svcAccsPmitNum_N1", "N1", null, TYPE_HANKAKUEISU, "6", null},
	{"svcAccsPmitDescTxt_N1", "svcAccsPmitDescTxt_N1", "N1", null, TYPE_HANKAKUEISU, "100", null},
	{"effFromDt_N1", "effFromDt_N1", "N1", null, TYPE_NENTSUKIHI, "8", null},
	{"effToDt_N1", "effToDt_N1", "N1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_N1", "ezUpTime_N1", "N1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_N1", "ezUpTimeZone_N1", "N1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_N1", "xxRecHistCratTs_N1", "N1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_N1", "xxRecHistCratByNm_N1", "N1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_N1", "xxRecHistUpdTs_N1", "N1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_N1", "xxRecHistUpdByNm_N1", "N1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_N1", "xxRecHistTblNm_N1", "N1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_ACCS_PMIT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitPk_N1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_N1
        {"SVC_ACCS_PMIT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitNum_N1
        {"SVC_ACCS_PMIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitDescTxt_N1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_N1
        {"EFF_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effToDt_N1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_N1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_N1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_N1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_N1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_N1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_N1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_N1
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

