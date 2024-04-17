//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171114085153000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0100_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWCL0100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWCL0100 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWCL0100_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A0*/
	public final EZDCStringItem              xxChkBox_A0;

    /** BILL_TO_CUST_ACCT_CD_A0*/
	public final EZDCStringItem              billToCustAcctCd_A0;

    /** XX_LINK_ANCR_A0*/
	public final EZDCStringItem              xxLinkAncr_A0;

    /** DS_ACCT_NM_A0*/
	public final EZDCStringItem              dsAcctNm_A0;

    /** PRC_GRP_PK_A0*/
	public final EZDCBigDecimalItem              prcGrpPk_A0;

    /** XX_LINK_PROT_A0*/
	public final EZDCStringItem              xxLinkProt_A0;

    /** PRC_GRP_NM_A0*/
	public final EZDCStringItem              prcGrpNm_A0;

    /** EFF_FROM_DT_A0*/
	public final EZDCDateItem              effFromDt_A0;

    /** EFF_THRU_DT_A0*/
	public final EZDCDateItem              effThruDt_A0;

    /** SPLY_PGM_UNIT_AMT_RATE_A0*/
	public final EZDCBigDecimalItem              splyPgmUnitAmtRate_A0;

    /** SPLY_PGM_MLY_QUOT_QTY_A0*/
	public final EZDCBigDecimalItem              splyPgmMlyQuotQty_A0;

    /** AUTO_DR_CRAT_FLG_A0*/
	public final EZDCStringItem              autoDrCratFlg_A0;

    /** CONTR_CRAT_PSN_CD_A0*/
	public final EZDCStringItem              contrCratPsnCd_A0;

    /** CONTR_CRAT_DT_A0*/
	public final EZDCDateItem              contrCratDt_A0;

    /** CONTR_UPD_PSN_CD_A0*/
	public final EZDCStringItem              contrUpdPsnCd_A0;

    /** CONTR_UPD_DT_A0*/
	public final EZDCDateItem              contrUpdDt_A0;

    /** XX_PSN_NM_A0*/
	public final EZDCStringItem              xxPsnNm_A0;

    /** XX_PSN_NM_A1*/
	public final EZDCStringItem              xxPsnNm_A1;

    /** SPLY_PGM_CONTR_PK_A0*/
	public final EZDCBigDecimalItem              splyPgmContrPk_A0;

    /** _EZUpdateDateTime_A0*/
	public final EZDCStringItem              ezUpTime_A0;

    /** _EZUpTimeZone_A0*/
	public final EZDCStringItem              ezUpTimeZone_A0;

    /** DPLY_CTRL_CD_A0*/
	public final EZDCStringItem              dplyCtrlCd_A0;


	/**
	 * NWCL0100_ACMsg is constructor.
	 * The initialization when the instance of NWCL0100_ACMsg is generated.
	 */
	public NWCL0100_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWCL0100_ACMsg is constructor.
	 * The initialization when the instance of NWCL0100_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWCL0100_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A0 = (EZDCStringItem)newItem("xxChkBox_A0");
		billToCustAcctCd_A0 = (EZDCStringItem)newItem("billToCustAcctCd_A0");
		xxLinkAncr_A0 = (EZDCStringItem)newItem("xxLinkAncr_A0");
		dsAcctNm_A0 = (EZDCStringItem)newItem("dsAcctNm_A0");
		prcGrpPk_A0 = (EZDCBigDecimalItem)newItem("prcGrpPk_A0");
		xxLinkProt_A0 = (EZDCStringItem)newItem("xxLinkProt_A0");
		prcGrpNm_A0 = (EZDCStringItem)newItem("prcGrpNm_A0");
		effFromDt_A0 = (EZDCDateItem)newItem("effFromDt_A0");
		effThruDt_A0 = (EZDCDateItem)newItem("effThruDt_A0");
		splyPgmUnitAmtRate_A0 = (EZDCBigDecimalItem)newItem("splyPgmUnitAmtRate_A0");
		splyPgmMlyQuotQty_A0 = (EZDCBigDecimalItem)newItem("splyPgmMlyQuotQty_A0");
		autoDrCratFlg_A0 = (EZDCStringItem)newItem("autoDrCratFlg_A0");
		contrCratPsnCd_A0 = (EZDCStringItem)newItem("contrCratPsnCd_A0");
		contrCratDt_A0 = (EZDCDateItem)newItem("contrCratDt_A0");
		contrUpdPsnCd_A0 = (EZDCStringItem)newItem("contrUpdPsnCd_A0");
		contrUpdDt_A0 = (EZDCDateItem)newItem("contrUpdDt_A0");
		xxPsnNm_A0 = (EZDCStringItem)newItem("xxPsnNm_A0");
		xxPsnNm_A1 = (EZDCStringItem)newItem("xxPsnNm_A1");
		splyPgmContrPk_A0 = (EZDCBigDecimalItem)newItem("splyPgmContrPk_A0");
		ezUpTime_A0 = (EZDCStringItem)newItem("ezUpTime_A0");
		ezUpTimeZone_A0 = (EZDCStringItem)newItem("ezUpTimeZone_A0");
		dplyCtrlCd_A0 = (EZDCStringItem)newItem("dplyCtrlCd_A0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWCL0100_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWCL0100_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A0", "xxChkBox_A0", "A0", null, TYPE_HANKAKUEIJI, "1", null},
	{"billToCustAcctCd_A0", "billToCustAcctCd_A0", "A0", null, TYPE_HANKAKUEISU, "20", null},
	{"xxLinkAncr_A0", "xxLinkAncr_A0", "A0", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNm_A0", "dsAcctNm_A0", "A0", null, TYPE_HANKAKUEISU, "360", null},
	{"prcGrpPk_A0", "prcGrpPk_A0", "A0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxLinkProt_A0", "xxLinkProt_A0", "A0", null, TYPE_HANKAKUEISU, "1", null},
	{"prcGrpNm_A0", "prcGrpNm_A0", "A0", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_A0", "effFromDt_A0", "A0", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A0", "effThruDt_A0", "A0", null, TYPE_NENTSUKIHI, "8", null},
	{"splyPgmUnitAmtRate_A0", "splyPgmUnitAmtRate_A0", "A0", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"splyPgmMlyQuotQty_A0", "splyPgmMlyQuotQty_A0", "A0", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"autoDrCratFlg_A0", "autoDrCratFlg_A0", "A0", null, TYPE_HANKAKUEISU, "1", null},
	{"contrCratPsnCd_A0", "contrCratPsnCd_A0", "A0", null, TYPE_HANKAKUEISU, "8", null},
	{"contrCratDt_A0", "contrCratDt_A0", "A0", null, TYPE_NENTSUKIHI, "8", null},
	{"contrUpdPsnCd_A0", "contrUpdPsnCd_A0", "A0", null, TYPE_HANKAKUEISU, "8", null},
	{"contrUpdDt_A0", "contrUpdDt_A0", "A0", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPsnNm_A0", "xxPsnNm_A0", "A0", null, TYPE_HANKAKUEISU, "62", null},
	{"xxPsnNm_A1", "xxPsnNm_A1", "A1", null, TYPE_HANKAKUEISU, "62", null},
	{"splyPgmContrPk_A0", "splyPgmContrPk_A0", "A0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A0", "ezUpTime_A0", "A0", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A0", "ezUpTimeZone_A0", "A0", null, TYPE_HANKAKUEISU, "5", null},
	{"dplyCtrlCd_A0", "dplyCtrlCd_A0", "A0", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A0
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_A0
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_A0
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A0
        {"PRC_GRP_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpPk_A0
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_A0
        {"PRC_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpNm_A0
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A0
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A0
        {"SPLY_PGM_UNIT_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyPgmUnitAmtRate_A0
        {"SPLY_PGM_MLY_QUOT_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyPgmMlyQuotQty_A0
        {"AUTO_DR_CRAT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoDrCratFlg_A0
        {"CONTR_CRAT_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCratPsnCd_A0
        {"CONTR_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCratDt_A0
        {"CONTR_UPD_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrUpdPsnCd_A0
        {"CONTR_UPD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrUpdDt_A0
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_A0
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_A1
        {"SPLY_PGM_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyPgmContrPk_A0
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A0
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A0
        {"DPLY_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyCtrlCd_A0
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
