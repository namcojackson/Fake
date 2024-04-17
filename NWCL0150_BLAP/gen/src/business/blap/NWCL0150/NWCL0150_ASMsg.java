//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180104145241000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0150_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWCL0150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWCL0150 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWCL0150_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CFS_PO_NUM_A1*/
	public final EZDSStringItem              cfsPoNum_A1;

    /** CPO_ORD_NUM_A1*/
	public final EZDSStringItem              cpoOrdNum_A1;

    /** DS_ACCT_NUM_A1*/
	public final EZDSStringItem              dsAcctNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDSStringItem              dsAcctNm_A1;

    /** CFS_PO_AMT_A1*/
	public final EZDSBigDecimalItem              cfsPoAmt_A1;

    /** INV_CPLT_AMT_RATE_A1*/
	public final EZDSBigDecimalItem              invCpltAmtRate_A1;

    /** CR_REBIL_HLD_FLG_A1*/
	public final EZDSStringItem              crRebilHldFlg_A1;

    /** CFS_LEASE_PKG_HLD_FLG_A1*/
	public final EZDSStringItem              cfsLeasePkgHldFlg_A1;

    /** LEASE_PKG_CRAT_FLG_A1*/
	public final EZDSStringItem              leasePkgCratFlg_A1;

    /** PO_INFO_PROC_FLG_A1*/
	public final EZDSStringItem              poInfoProcFlg_A1;

    /** CFS_MAN_HLD_ACT_TP_CD_A1*/
	public final EZDSStringItem              cfsManHldActTpCd_A1;

    /** CFS_LEASE_PKG_PO_HDR_PK_A1*/
	public final EZDSBigDecimalItem              cfsLeasePkgPoHdrPk_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;


	/**
	 * NWCL0150_ASMsg is constructor.
	 * The initialization when the instance of NWCL0150_ASMsg is generated.
	 */
	public NWCL0150_ASMsg() {
		this(false, -1);
	}

	/**
	 * NWCL0150_ASMsg is constructor.
	 * The initialization when the instance of NWCL0150_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWCL0150_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cfsPoNum_A1 = (EZDSStringItem)newItem("cfsPoNum_A1");
		cpoOrdNum_A1 = (EZDSStringItem)newItem("cpoOrdNum_A1");
		dsAcctNum_A1 = (EZDSStringItem)newItem("dsAcctNum_A1");
		dsAcctNm_A1 = (EZDSStringItem)newItem("dsAcctNm_A1");
		cfsPoAmt_A1 = (EZDSBigDecimalItem)newItem("cfsPoAmt_A1");
		invCpltAmtRate_A1 = (EZDSBigDecimalItem)newItem("invCpltAmtRate_A1");
		crRebilHldFlg_A1 = (EZDSStringItem)newItem("crRebilHldFlg_A1");
		cfsLeasePkgHldFlg_A1 = (EZDSStringItem)newItem("cfsLeasePkgHldFlg_A1");
		leasePkgCratFlg_A1 = (EZDSStringItem)newItem("leasePkgCratFlg_A1");
		poInfoProcFlg_A1 = (EZDSStringItem)newItem("poInfoProcFlg_A1");
		cfsManHldActTpCd_A1 = (EZDSStringItem)newItem("cfsManHldActTpCd_A1");
		cfsLeasePkgPoHdrPk_A1 = (EZDSBigDecimalItem)newItem("cfsLeasePkgPoHdrPk_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWCL0150_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWCL0150_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cfsPoNum_A1", "cfsPoNum_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"cpoOrdNum_A1", "cpoOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"dsAcctNum_A1", "dsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"cfsPoAmt_A1", "cfsPoAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invCpltAmtRate_A1", "invCpltAmtRate_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"crRebilHldFlg_A1", "crRebilHldFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"cfsLeasePkgHldFlg_A1", "cfsLeasePkgHldFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"leasePkgCratFlg_A1", "leasePkgCratFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"poInfoProcFlg_A1", "poInfoProcFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"cfsManHldActTpCd_A1", "cfsManHldActTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"cfsLeasePkgPoHdrPk_A1", "cfsLeasePkgPoHdrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CFS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cfsPoNum_A1
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"CFS_PO_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cfsPoAmt_A1
        {"INV_CPLT_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invCpltAmtRate_A1
        {"CR_REBIL_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilHldFlg_A1
        {"CFS_LEASE_PKG_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cfsLeasePkgHldFlg_A1
        {"LEASE_PKG_CRAT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leasePkgCratFlg_A1
        {"PO_INFO_PROC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poInfoProcFlg_A1
        {"CFS_MAN_HLD_ACT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cfsManHldActTpCd_A1
        {"CFS_LEASE_PKG_PO_HDR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cfsLeasePkgPoHdrPk_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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

