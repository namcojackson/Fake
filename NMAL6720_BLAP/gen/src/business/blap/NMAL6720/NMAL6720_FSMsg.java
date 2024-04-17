//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231108093943000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6720_FSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6720 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6720_FSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_ACCS_PMIT_PK_F1*/
	public final EZDSBigDecimalItem              svcAccsPmitPk_F1;

    /** SVC_ACCS_PMIT_NUM_F1*/
	public final EZDSStringItem              svcAccsPmitNum_F1;

    /** SVC_ACCS_PMIT_DESC_TXT_F1*/
	public final EZDSStringItem              svcAccsPmitDescTxt_F1;

    /** EFF_FROM_DT_F1*/
	public final EZDSDateItem              effFromDt_F1;

    /** EFF_TO_DT_F1*/
	public final EZDSDateItem              effToDt_F1;

    /** _EZUpdateDateTime_F1*/
	public final EZDSStringItem              ezUpTime_F1;

    /** _EZUpTimeZone_F1*/
	public final EZDSStringItem              ezUpTimeZone_F1;

    /** XX_REC_HIST_CRAT_TS_F1*/
	public final EZDSStringItem              xxRecHistCratTs_F1;

    /** XX_REC_HIST_CRAT_BY_NM_F1*/
	public final EZDSStringItem              xxRecHistCratByNm_F1;

    /** XX_REC_HIST_UPD_TS_F1*/
	public final EZDSStringItem              xxRecHistUpdTs_F1;

    /** XX_REC_HIST_UPD_BY_NM_F1*/
	public final EZDSStringItem              xxRecHistUpdByNm_F1;

    /** XX_REC_HIST_TBL_NM_F1*/
	public final EZDSStringItem              xxRecHistTblNm_F1;


	/**
	 * NMAL6720_FSMsg is constructor.
	 * The initialization when the instance of NMAL6720_FSMsg is generated.
	 */
	public NMAL6720_FSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6720_FSMsg is constructor.
	 * The initialization when the instance of NMAL6720_FSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6720_FSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcAccsPmitPk_F1 = (EZDSBigDecimalItem)newItem("svcAccsPmitPk_F1");
		svcAccsPmitNum_F1 = (EZDSStringItem)newItem("svcAccsPmitNum_F1");
		svcAccsPmitDescTxt_F1 = (EZDSStringItem)newItem("svcAccsPmitDescTxt_F1");
		effFromDt_F1 = (EZDSDateItem)newItem("effFromDt_F1");
		effToDt_F1 = (EZDSDateItem)newItem("effToDt_F1");
		ezUpTime_F1 = (EZDSStringItem)newItem("ezUpTime_F1");
		ezUpTimeZone_F1 = (EZDSStringItem)newItem("ezUpTimeZone_F1");
		xxRecHistCratTs_F1 = (EZDSStringItem)newItem("xxRecHistCratTs_F1");
		xxRecHistCratByNm_F1 = (EZDSStringItem)newItem("xxRecHistCratByNm_F1");
		xxRecHistUpdTs_F1 = (EZDSStringItem)newItem("xxRecHistUpdTs_F1");
		xxRecHistUpdByNm_F1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_F1");
		xxRecHistTblNm_F1 = (EZDSStringItem)newItem("xxRecHistTblNm_F1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6720_FSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6720_FSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

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

        {"SVC_ACCS_PMIT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitPk_F1
        {"SVC_ACCS_PMIT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitNum_F1
        {"SVC_ACCS_PMIT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAccsPmitDescTxt_F1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_F1
        {"EFF_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effToDt_F1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_F1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_F1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_F1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_F1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_F1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_F1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_F1
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

