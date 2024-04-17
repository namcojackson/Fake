//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180803095224000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2170_SBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2170 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2170_SBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDL_ID_S*/
	public final EZDBBigDecimalItem              mdlId_S;

    /** CPO_SVC_DTL_PK_S*/
	public final EZDBBigDecimalItem              cpoSvcDtlPk_S;

    /** CPO_SVC_PRC_PK_S*/
	public final EZDBBigDecimalItem              cpoSvcPrcPk_S;

    /** PRC_LIST_BAND_CD_S*/
	public final EZDBStringItem              prcListBandCd_S;

    /** PRC_BOOK_MDSE_CD_S*/
	public final EZDBStringItem              prcBookMdseCd_S;

    /** BLLG_MTR_LB_CD_S*/
	public final EZDBStringItem              bllgMtrLbCd_S;

    /** USG_MDSE_CD_S*/
	public final EZDBStringItem              usgMdseCd_S;

    /** COPY_INCL_PRC_QTY_S*/
	public final EZDBBigDecimalItem              copyInclPrcQty_S;

    /** REG_MTR_LB_CD_S*/
	public final EZDBStringItem              regMtrLbCd_S;

    /** MLY_COPY_INCL_PRC_QTY_S*/
	public final EZDBBigDecimalItem              mlyCopyInclPrcQty_S;

    /** XS_MTR_AMT_RATE_S*/
	public final EZDBBigDecimalItem              xsMtrAmtRate_S;

    /** CONTR_MTR_MULT_RATE_S*/
	public final EZDBBigDecimalItem              contrMtrMultRate_S;

    /** PRC_SVC_TIER_TP_CD_S*/
	public final EZDBStringItem              prcSvcTierTpCd_S;

    /** MIN_COPY_VOL_CNT_S*/
	public final EZDBBigDecimalItem              minCopyVolCnt_S;

    /** MAX_COPY_VOL_CNT_S*/
	public final EZDBBigDecimalItem              maxCopyVolCnt_S;


	/**
	 * NWAL2170_SBMsg is constructor.
	 * The initialization when the instance of NWAL2170_SBMsg is generated.
	 */
	public NWAL2170_SBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2170_SBMsg is constructor.
	 * The initialization when the instance of NWAL2170_SBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2170_SBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdlId_S = (EZDBBigDecimalItem)newItem("mdlId_S");
		cpoSvcDtlPk_S = (EZDBBigDecimalItem)newItem("cpoSvcDtlPk_S");
		cpoSvcPrcPk_S = (EZDBBigDecimalItem)newItem("cpoSvcPrcPk_S");
		prcListBandCd_S = (EZDBStringItem)newItem("prcListBandCd_S");
		prcBookMdseCd_S = (EZDBStringItem)newItem("prcBookMdseCd_S");
		bllgMtrLbCd_S = (EZDBStringItem)newItem("bllgMtrLbCd_S");
		usgMdseCd_S = (EZDBStringItem)newItem("usgMdseCd_S");
		copyInclPrcQty_S = (EZDBBigDecimalItem)newItem("copyInclPrcQty_S");
		regMtrLbCd_S = (EZDBStringItem)newItem("regMtrLbCd_S");
		mlyCopyInclPrcQty_S = (EZDBBigDecimalItem)newItem("mlyCopyInclPrcQty_S");
		xsMtrAmtRate_S = (EZDBBigDecimalItem)newItem("xsMtrAmtRate_S");
		contrMtrMultRate_S = (EZDBBigDecimalItem)newItem("contrMtrMultRate_S");
		prcSvcTierTpCd_S = (EZDBStringItem)newItem("prcSvcTierTpCd_S");
		minCopyVolCnt_S = (EZDBBigDecimalItem)newItem("minCopyVolCnt_S");
		maxCopyVolCnt_S = (EZDBBigDecimalItem)newItem("maxCopyVolCnt_S");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2170_SBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2170_SBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdlId_S", "mdlId_S", "S", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"cpoSvcDtlPk_S", "cpoSvcDtlPk_S", "S", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cpoSvcPrcPk_S", "cpoSvcPrcPk_S", "S", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcListBandCd_S", "prcListBandCd_S", "S", null, TYPE_HANKAKUEISU, "2", null},
	{"prcBookMdseCd_S", "prcBookMdseCd_S", "S", null, TYPE_HANKAKUEISU, "16", null},
	{"bllgMtrLbCd_S", "bllgMtrLbCd_S", "S", null, TYPE_HANKAKUEISU, "2", null},
	{"usgMdseCd_S", "usgMdseCd_S", "S", null, TYPE_HANKAKUEISU, "16", null},
	{"copyInclPrcQty_S", "copyInclPrcQty_S", "S", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"regMtrLbCd_S", "regMtrLbCd_S", "S", null, TYPE_HANKAKUEISU, "2", null},
	{"mlyCopyInclPrcQty_S", "mlyCopyInclPrcQty_S", "S", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_S", "xsMtrAmtRate_S", "S", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"contrMtrMultRate_S", "contrMtrMultRate_S", "S", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"prcSvcTierTpCd_S", "prcSvcTierTpCd_S", "S", null, TYPE_HANKAKUEISU, "2", null},
	{"minCopyVolCnt_S", "minCopyVolCnt_S", "S", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt_S", "maxCopyVolCnt_S", "S", null, TYPE_SEISU_SYOSU, "12", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_S
        {"CPO_SVC_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcDtlPk_S
        {"CPO_SVC_PRC_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcPrcPk_S
        {"PRC_LIST_BAND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandCd_S
        {"PRC_BOOK_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBookMdseCd_S
        {"BLLG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_S
        {"USG_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usgMdseCd_S
        {"COPY_INCL_PRC_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//copyInclPrcQty_S
        {"REG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//regMtrLbCd_S
        {"MLY_COPY_INCL_PRC_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlyCopyInclPrcQty_S
        {"XS_MTR_AMT_RATE",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_S
        {"CONTR_MTR_MULT_RATE",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrMtrMultRate_S
        {"PRC_SVC_TIER_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcTierTpCd_S
        {"MIN_COPY_VOL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt_S
        {"MAX_COPY_VOL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt_S
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

