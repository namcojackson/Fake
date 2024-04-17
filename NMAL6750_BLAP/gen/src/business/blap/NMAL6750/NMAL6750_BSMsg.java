//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170829174857000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6750_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6750;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6750 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6750_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CTAC_PNT_PK_B1*/
	public final EZDSBigDecimalItem              dsCtacPntPk_B1;

    /** DS_CTAC_PNT_TP_CD_B1*/
	public final EZDSStringItem              dsCtacPntTpCd_B1;

    /** CTAC_PNT_FMT_TXT_B1*/
	public final EZDSStringItem              ctacPntFmtTxt_B1;

    /** DS_CTAC_PNT_VAL_TXT_B1*/
	public final EZDSStringItem              dsCtacPntValTxt_B1;

    /** DS_CTAC_PSN_EXTN_NUM_B1*/
	public final EZDSStringItem              dsCtacPsnExtnNum_B1;

    /** DS_OPS_OUT_FLG_B1*/
	public final EZDSStringItem              dsOpsOutFlg_B1;

    /** DS_CTAC_PNT_ACTV_FLG_B1*/
	public final EZDSStringItem              dsCtacPntActvFlg_B1;

    /** _EZUpdateDateTime_B1*/
	public final EZDSStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDSStringItem              ezUpTimeZone_B1;

    /** XX_REC_HIST_CRAT_TS_B1*/
	public final EZDSStringItem              xxRecHistCratTs_B1;

    /** XX_REC_HIST_CRAT_BY_NM_B1*/
	public final EZDSStringItem              xxRecHistCratByNm_B1;

    /** XX_REC_HIST_UPD_TS_B1*/
	public final EZDSStringItem              xxRecHistUpdTs_B1;

    /** XX_REC_HIST_UPD_BY_NM_B1*/
	public final EZDSStringItem              xxRecHistUpdByNm_B1;

    /** XX_REC_HIST_TBL_NM_B1*/
	public final EZDSStringItem              xxRecHistTblNm_B1;


	/**
	 * NMAL6750_BSMsg is constructor.
	 * The initialization when the instance of NMAL6750_BSMsg is generated.
	 */
	public NMAL6750_BSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6750_BSMsg is constructor.
	 * The initialization when the instance of NMAL6750_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6750_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsCtacPntPk_B1 = (EZDSBigDecimalItem)newItem("dsCtacPntPk_B1");
		dsCtacPntTpCd_B1 = (EZDSStringItem)newItem("dsCtacPntTpCd_B1");
		ctacPntFmtTxt_B1 = (EZDSStringItem)newItem("ctacPntFmtTxt_B1");
		dsCtacPntValTxt_B1 = (EZDSStringItem)newItem("dsCtacPntValTxt_B1");
		dsCtacPsnExtnNum_B1 = (EZDSStringItem)newItem("dsCtacPsnExtnNum_B1");
		dsOpsOutFlg_B1 = (EZDSStringItem)newItem("dsOpsOutFlg_B1");
		dsCtacPntActvFlg_B1 = (EZDSStringItem)newItem("dsCtacPntActvFlg_B1");
		ezUpTime_B1 = (EZDSStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDSStringItem)newItem("ezUpTimeZone_B1");
		xxRecHistCratTs_B1 = (EZDSStringItem)newItem("xxRecHistCratTs_B1");
		xxRecHistCratByNm_B1 = (EZDSStringItem)newItem("xxRecHistCratByNm_B1");
		xxRecHistUpdTs_B1 = (EZDSStringItem)newItem("xxRecHistUpdTs_B1");
		xxRecHistUpdByNm_B1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_B1");
		xxRecHistTblNm_B1 = (EZDSStringItem)newItem("xxRecHistTblNm_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6750_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6750_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsCtacPntPk_B1", "dsCtacPntPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsCtacPntTpCd_B1", "dsCtacPntTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"ctacPntFmtTxt_B1", "ctacPntFmtTxt_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"dsCtacPntValTxt_B1", "dsCtacPntValTxt_B1", "B1", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPsnExtnNum_B1", "dsCtacPsnExtnNum_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"dsOpsOutFlg_B1", "dsOpsOutFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsCtacPntActvFlg_B1", "dsCtacPntActvFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_B1", "xxRecHistCratTs_B1", "B1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_B1", "xxRecHistCratByNm_B1", "B1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_B1", "xxRecHistUpdTs_B1", "B1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_B1", "xxRecHistUpdByNm_B1", "B1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_B1", "xxRecHistTblNm_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CTAC_PNT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk_B1
        {"DS_CTAC_PNT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntTpCd_B1
        {"CTAC_PNT_FMT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPntFmtTxt_B1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_B1
        {"DS_CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnExtnNum_B1
        {"DS_OPS_OUT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOpsOutFlg_B1
        {"DS_CTAC_PNT_ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntActvFlg_B1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_B1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_B1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_B1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_B1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_B1
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

