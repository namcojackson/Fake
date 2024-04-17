//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180205111948000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1190_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1190;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1190 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1190_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_A*/
	public final EZDCBigDecimalItem              xxRowNum_A;

    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** MTR_LB_CD_A*/
	public final EZDCStringItem              mtrLbCd_A;

    /** MTR_LB_NM_A*/
	public final EZDCStringItem              mtrLbNm_A;

    /** MTR_LB_TP_CD_A*/
	public final EZDCStringItem              mtrLbTpCd_A;

    /** MTR_IDX_CD_A*/
	public final EZDCStringItem              mtrIdxCd_A;

    /** BLLG_MTR_LVL_NUM_A*/
	public final EZDCStringItem              bllgMtrLvlNum_A;

    /** MTR_LB_DESC_TXT_A*/
	public final EZDCStringItem              mtrLbDescTxt_A;

    /** FLEET_MTR_LB_CD_A*/
	public final EZDCStringItem              fleetMtrLbCd_A;

    /** MTR_LB_NM_AF*/
	public final EZDCStringItem              mtrLbNm_AF;

    /** AGGR_MTR_LB_CD_A*/
	public final EZDCStringItem              aggrMtrLbCd_A;

    /** MTR_LB_NM_AG*/
	public final EZDCStringItem              mtrLbNm_AG;

    /** ACTV_FLG_A*/
	public final EZDCStringItem              actvFlg_A;

    /** EFF_FROM_DT_A*/
	public final EZDCDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDCDateItem              effThruDt_A;

    /** BW_MTR_FLG_A*/
	public final EZDCStringItem              bwMtrFlg_A;

    /** COLOR_MTR_FLG_A*/
	public final EZDCStringItem              colorMtrFlg_A;

    /** TOT_MTR_FLG_A*/
	public final EZDCStringItem              totMtrFlg_A;

    /** CORP_ADVTG_FLG_A*/
	public final EZDCStringItem              corpAdvtgFlg_A;

    /** MTR_CNTR_ID_A*/
	public final EZDCStringItem              mtrCntrId_A;

    /** INTG_MDSE_CD_A*/
	public final EZDCStringItem              intgMdseCd_A;

    /** INV_PRINT_MTR_LB_DESC_TXT_A*/
	public final EZDCStringItem              invPrintMtrLbDescTxt_A;

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
	 * NSAL1190_ACMsg is constructor.
	 * The initialization when the instance of NSAL1190_ACMsg is generated.
	 */
	public NSAL1190_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1190_ACMsg is constructor.
	 * The initialization when the instance of NSAL1190_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1190_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_A = (EZDCBigDecimalItem)newItem("xxRowNum_A");
		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		mtrLbCd_A = (EZDCStringItem)newItem("mtrLbCd_A");
		mtrLbNm_A = (EZDCStringItem)newItem("mtrLbNm_A");
		mtrLbTpCd_A = (EZDCStringItem)newItem("mtrLbTpCd_A");
		mtrIdxCd_A = (EZDCStringItem)newItem("mtrIdxCd_A");
		bllgMtrLvlNum_A = (EZDCStringItem)newItem("bllgMtrLvlNum_A");
		mtrLbDescTxt_A = (EZDCStringItem)newItem("mtrLbDescTxt_A");
		fleetMtrLbCd_A = (EZDCStringItem)newItem("fleetMtrLbCd_A");
		mtrLbNm_AF = (EZDCStringItem)newItem("mtrLbNm_AF");
		aggrMtrLbCd_A = (EZDCStringItem)newItem("aggrMtrLbCd_A");
		mtrLbNm_AG = (EZDCStringItem)newItem("mtrLbNm_AG");
		actvFlg_A = (EZDCStringItem)newItem("actvFlg_A");
		effFromDt_A = (EZDCDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDCDateItem)newItem("effThruDt_A");
		bwMtrFlg_A = (EZDCStringItem)newItem("bwMtrFlg_A");
		colorMtrFlg_A = (EZDCStringItem)newItem("colorMtrFlg_A");
		totMtrFlg_A = (EZDCStringItem)newItem("totMtrFlg_A");
		corpAdvtgFlg_A = (EZDCStringItem)newItem("corpAdvtgFlg_A");
		mtrCntrId_A = (EZDCStringItem)newItem("mtrCntrId_A");
		intgMdseCd_A = (EZDCStringItem)newItem("intgMdseCd_A");
		invPrintMtrLbDescTxt_A = (EZDCStringItem)newItem("invPrintMtrLbDescTxt_A");
		xxRecHistCratTs_A = (EZDCStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDCStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDCStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDCStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDCStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1190_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1190_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"mtrLbCd_A", "mtrLbCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbNm_A", "mtrLbNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"mtrLbTpCd_A", "mtrLbTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrIdxCd_A", "mtrIdxCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgMtrLvlNum_A", "bllgMtrLvlNum_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_A", "mtrLbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"fleetMtrLbCd_A", "fleetMtrLbCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbNm_AF", "mtrLbNm_AF", "AF", null, TYPE_HANKAKUEISU, "30", null},
	{"aggrMtrLbCd_A", "aggrMtrLbCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbNm_AG", "mtrLbNm_AG", "AG", null, TYPE_HANKAKUEISU, "30", null},
	{"actvFlg_A", "actvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"bwMtrFlg_A", "bwMtrFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"colorMtrFlg_A", "colorMtrFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"totMtrFlg_A", "totMtrFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"corpAdvtgFlg_A", "corpAdvtgFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"mtrCntrId_A", "mtrCntrId_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"intgMdseCd_A", "intgMdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"invPrintMtrLbDescTxt_A", "invPrintMtrLbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
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
        {"MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_A
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_A
        {"MTR_LB_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbTpCd_A
        {"MTR_IDX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrIdxCd_A
        {"BLLG_MTR_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLvlNum_A
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_A
        {"FLEET_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fleetMtrLbCd_A
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_AF
        {"AGGR_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aggrMtrLbCd_A
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_AG
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A
        {"BW_MTR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bwMtrFlg_A
        {"COLOR_MTR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//colorMtrFlg_A
        {"TOT_MTR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totMtrFlg_A
        {"CORP_ADVTG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//corpAdvtgFlg_A
        {"MTR_CNTR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrCntrId_A
        {"INTG_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intgMdseCd_A
        {"INV_PRINT_MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrintMtrLbDescTxt_A
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
