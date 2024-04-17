//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161207163130000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1290_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1290;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1290 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1290_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_A*/
	public final EZDCBigDecimalItem              xxRowNum_A;

    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** BLLG_MTR_LB_CD_BC*/
	public final EZDCStringItem              bllgMtrLbCd_BC;

    /** MTR_LB_NM_BC*/
	public final EZDCStringItem              mtrLbNm_BC;

    /** MTR_LB_DESC_TXT_BC*/
	public final EZDCStringItem              mtrLbDescTxt_BC;

    /** BLLG_MTR_MAP_LVL_NUM_A*/
	public final EZDCStringItem              bllgMtrMapLvlNum_A;

    /** ACTV_FLG_A*/
	public final EZDCStringItem              actvFlg_A;

    /** EFF_FROM_DT_A*/
	public final EZDCDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDCDateItem              effThruDt_A;

    /** BLLG_MTR_MAP_PK_A*/
	public final EZDCBigDecimalItem              bllgMtrMapPk_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDCStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDCStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDCStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDCStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDCStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL1290_ACMsg is constructor.
	 * The initialization when the instance of NSAL1290_ACMsg is generated.
	 */
	public NSAL1290_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1290_ACMsg is constructor.
	 * The initialization when the instance of NSAL1290_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1290_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_A = (EZDCBigDecimalItem)newItem("xxRowNum_A");
		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		bllgMtrLbCd_BC = (EZDCStringItem)newItem("bllgMtrLbCd_BC");
		mtrLbNm_BC = (EZDCStringItem)newItem("mtrLbNm_BC");
		mtrLbDescTxt_BC = (EZDCStringItem)newItem("mtrLbDescTxt_BC");
		bllgMtrMapLvlNum_A = (EZDCStringItem)newItem("bllgMtrMapLvlNum_A");
		actvFlg_A = (EZDCStringItem)newItem("actvFlg_A");
		effFromDt_A = (EZDCDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDCDateItem)newItem("effThruDt_A");
		bllgMtrMapPk_A = (EZDCBigDecimalItem)newItem("bllgMtrMapPk_A");
		xxRecHistCratTs_A = (EZDCStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDCStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDCStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDCStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDCStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1290_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1290_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"bllgMtrLbCd_BC", "bllgMtrLbCd_BC", "BC", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbNm_BC", "mtrLbNm_BC", "BC", null, TYPE_HANKAKUEISU, "30", null},
	{"mtrLbDescTxt_BC", "mtrLbDescTxt_BC", "BC", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgMtrMapLvlNum_A", "bllgMtrMapLvlNum_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"actvFlg_A", "actvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgMtrMapPk_A", "bllgMtrMapPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
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

        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_BC
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_BC
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_BC
        {"BLLG_MTR_MAP_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrMapLvlNum_A
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A
        {"BLLG_MTR_MAP_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrMapPk_A
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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

