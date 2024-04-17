//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190118110520000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_VCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1330;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1330 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1330_VCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CONTR_XS_COPY_PK_V*/
	public final EZDCBigDecimalItem              contrXsCopyPk_V;

    /** DS_CONTR_BLLG_MTR_PK_V*/
	public final EZDCBigDecimalItem              dsContrBllgMtrPk_V;

    /** DS_CONTR_DTL_PK_V*/
	public final EZDCBigDecimalItem              dsContrDtlPk_V;

    /** MDL_ID_V*/
	public final EZDCBigDecimalItem              mdlId_V;

    /** DS_ORD_POSN_NUM_V*/
	public final EZDCStringItem              dsOrdPosnNum_V;

    /** BLLG_MTR_LB_CD_V*/
	public final EZDCStringItem              bllgMtrLbCd_V;

    /** PRC_SVC_TIER_TP_CD_V*/
	public final EZDCStringItem              prcSvcTierTpCd_V;

    /** PRC_SVC_TIER_TP_DESC_TXT_V*/
	public final EZDCStringItem              prcSvcTierTpDescTxt_V;

    /** MIN_COPY_VOL_CNT_V*/
	public final EZDCBigDecimalItem              minCopyVolCnt_V;

    /** MAX_COPY_VOL_CNT_V*/
	public final EZDCBigDecimalItem              maxCopyVolCnt_V;

    /** XS_MTR_AMT_RATE_V*/
	public final EZDCBigDecimalItem              xsMtrAmtRate_V;

    /** ACTV_FLG_V*/
	public final EZDCStringItem              actvFlg_V;


	/**
	 * NSAL1330_VCMsg is constructor.
	 * The initialization when the instance of NSAL1330_VCMsg is generated.
	 */
	public NSAL1330_VCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1330_VCMsg is constructor.
	 * The initialization when the instance of NSAL1330_VCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1330_VCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		contrXsCopyPk_V = (EZDCBigDecimalItem)newItem("contrXsCopyPk_V");
		dsContrBllgMtrPk_V = (EZDCBigDecimalItem)newItem("dsContrBllgMtrPk_V");
		dsContrDtlPk_V = (EZDCBigDecimalItem)newItem("dsContrDtlPk_V");
		mdlId_V = (EZDCBigDecimalItem)newItem("mdlId_V");
		dsOrdPosnNum_V = (EZDCStringItem)newItem("dsOrdPosnNum_V");
		bllgMtrLbCd_V = (EZDCStringItem)newItem("bllgMtrLbCd_V");
		prcSvcTierTpCd_V = (EZDCStringItem)newItem("prcSvcTierTpCd_V");
		prcSvcTierTpDescTxt_V = (EZDCStringItem)newItem("prcSvcTierTpDescTxt_V");
		minCopyVolCnt_V = (EZDCBigDecimalItem)newItem("minCopyVolCnt_V");
		maxCopyVolCnt_V = (EZDCBigDecimalItem)newItem("maxCopyVolCnt_V");
		xsMtrAmtRate_V = (EZDCBigDecimalItem)newItem("xsMtrAmtRate_V");
		actvFlg_V = (EZDCStringItem)newItem("actvFlg_V");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1330_VCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1330_VCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"contrXsCopyPk_V", "contrXsCopyPk_V", "V", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk_V", "dsContrBllgMtrPk_V", "V", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_V", "dsContrDtlPk_V", "V", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId_V", "mdlId_V", "V", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"dsOrdPosnNum_V", "dsOrdPosnNum_V", "V", null, TYPE_HANKAKUEISU, "6", null},
	{"bllgMtrLbCd_V", "bllgMtrLbCd_V", "V", null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcTierTpCd_V", "prcSvcTierTpCd_V", "V", null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcTierTpDescTxt_V", "prcSvcTierTpDescTxt_V", "V", null, TYPE_HANKAKUEISU, "50", null},
	{"minCopyVolCnt_V", "minCopyVolCnt_V", "V", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt_V", "maxCopyVolCnt_V", "V", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"xsMtrAmtRate_V", "xsMtrAmtRate_V", "V", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"actvFlg_V", "actvFlg_V", "V", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CONTR_XS_COPY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_V
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_V
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_V
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_V
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_V
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_V
        {"PRC_SVC_TIER_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcTierTpCd_V
        {"PRC_SVC_TIER_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcTierTpDescTxt_V
        {"MIN_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt_V
        {"MAX_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt_V
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_V
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_V
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

