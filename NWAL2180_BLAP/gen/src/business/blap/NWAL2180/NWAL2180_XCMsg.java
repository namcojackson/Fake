//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180508101658000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2180_XCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2180;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2180 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2180_XCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_IMPT_SVC_USG_PRC_PK_X*/
	public final EZDCBigDecimalItem              dsImptSvcUsgPrcPk_X;

    /** MDL_ID_X*/
	public final EZDCBigDecimalItem              mdlId_X;

    /** BLLG_MTR_LB_CD_X*/
	public final EZDCStringItem              bllgMtrLbCd_X;

    /** PRC_SVC_TIER_TP_CD_X*/
	public final EZDCStringItem              prcSvcTierTpCd_X;

    /** PRC_SVC_TIER_TP_DESC_TXT_X*/
	public final EZDCStringItem              prcSvcTierTpDescTxt_X;

    /** MIN_COPY_VOL_CNT_X*/
	public final EZDCBigDecimalItem              minCopyVolCnt_X;

    /** MAX_COPY_VOL_CNT_X*/
	public final EZDCBigDecimalItem              maxCopyVolCnt_X;

    /** XS_MTR_AMT_RATE_X*/
	public final EZDCBigDecimalItem              xsMtrAmtRate_X;


	/**
	 * NWAL2180_XCMsg is constructor.
	 * The initialization when the instance of NWAL2180_XCMsg is generated.
	 */
	public NWAL2180_XCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2180_XCMsg is constructor.
	 * The initialization when the instance of NWAL2180_XCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2180_XCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsImptSvcUsgPrcPk_X = (EZDCBigDecimalItem)newItem("dsImptSvcUsgPrcPk_X");
		mdlId_X = (EZDCBigDecimalItem)newItem("mdlId_X");
		bllgMtrLbCd_X = (EZDCStringItem)newItem("bllgMtrLbCd_X");
		prcSvcTierTpCd_X = (EZDCStringItem)newItem("prcSvcTierTpCd_X");
		prcSvcTierTpDescTxt_X = (EZDCStringItem)newItem("prcSvcTierTpDescTxt_X");
		minCopyVolCnt_X = (EZDCBigDecimalItem)newItem("minCopyVolCnt_X");
		maxCopyVolCnt_X = (EZDCBigDecimalItem)newItem("maxCopyVolCnt_X");
		xsMtrAmtRate_X = (EZDCBigDecimalItem)newItem("xsMtrAmtRate_X");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2180_XCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2180_XCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsImptSvcUsgPrcPk_X", "dsImptSvcUsgPrcPk_X", "X", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId_X", "mdlId_X", "X", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"bllgMtrLbCd_X", "bllgMtrLbCd_X", "X", null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcTierTpCd_X", "prcSvcTierTpCd_X", "X", null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcTierTpDescTxt_X", "prcSvcTierTpDescTxt_X", "X", null, TYPE_HANKAKUEISU, "50", null},
	{"minCopyVolCnt_X", "minCopyVolCnt_X", "X", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt_X", "maxCopyVolCnt_X", "X", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"xsMtrAmtRate_X", "xsMtrAmtRate_X", "X", null, TYPE_SEISU_SYOSU, "19", "6"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_IMPT_SVC_USG_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcUsgPrcPk_X
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_X
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_X
        {"PRC_SVC_TIER_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcTierTpCd_X
        {"PRC_SVC_TIER_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcTierTpDescTxt_X
        {"MIN_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt_X
        {"MAX_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt_X
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_X
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

