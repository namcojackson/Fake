//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220912153614000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0400_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0400;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0400 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0400_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_B*/
	public final EZDCBigDecimalItem              dsContrPk_B;

    /** DS_CONTR_NUM_B*/
	public final EZDCStringItem              dsContrNum_B;

    /** DS_CONTR_CATG_CD_B*/
	public final EZDCStringItem              dsContrCatgCd_B;

    /** XX_SCR_ITEM_40_TXT_B*/
	public final EZDCStringItem              xxScrItem40Txt_B;

    /** CONTR_VRSN_EFF_FROM_DT_B*/
	public final EZDCDateItem              contrVrsnEffFromDt_B;

    /** CONTR_VRSN_EFF_THRU_DT_B*/
	public final EZDCDateItem              contrVrsnEffThruDt_B;

    /** DS_ACCT_NM_B*/
	public final EZDCStringItem              dsAcctNm_B;

    /** DS_CONTR_CTRL_STS_CD_B*/
	public final EZDCStringItem              dsContrCtrlStsCd_B;

    /** DS_CONTR_CTRL_STS_NM_B*/
	public final EZDCStringItem              dsContrCtrlStsNm_B;

    /** CONTR_CLO_DT_B*/
	public final EZDCDateItem              contrCloDt_B;

    /** SUPPR_CR_FLG_B*/
	public final EZDCStringItem              supprCrFlg_B;

    /** TRMN_TOT_AMT_B*/
	public final EZDCBigDecimalItem              trmnTotAmt_B;

    /** TRMN_OVRD_TOT_AMT_B*/
	public final EZDCBigDecimalItem              trmnOvrdTotAmt_B;

    /** XX_CHK_BOX_B*/
	public final EZDCStringItem              xxChkBox_B;

    /** CONTR_TRMN_FLG_B*/
	public final EZDCStringItem              contrTrmnFlg_B;


	/**
	 * NSAL0400_BCMsg is constructor.
	 * The initialization when the instance of NSAL0400_BCMsg is generated.
	 */
	public NSAL0400_BCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0400_BCMsg is constructor.
	 * The initialization when the instance of NSAL0400_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0400_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_B = (EZDCBigDecimalItem)newItem("dsContrPk_B");
		dsContrNum_B = (EZDCStringItem)newItem("dsContrNum_B");
		dsContrCatgCd_B = (EZDCStringItem)newItem("dsContrCatgCd_B");
		xxScrItem40Txt_B = (EZDCStringItem)newItem("xxScrItem40Txt_B");
		contrVrsnEffFromDt_B = (EZDCDateItem)newItem("contrVrsnEffFromDt_B");
		contrVrsnEffThruDt_B = (EZDCDateItem)newItem("contrVrsnEffThruDt_B");
		dsAcctNm_B = (EZDCStringItem)newItem("dsAcctNm_B");
		dsContrCtrlStsCd_B = (EZDCStringItem)newItem("dsContrCtrlStsCd_B");
		dsContrCtrlStsNm_B = (EZDCStringItem)newItem("dsContrCtrlStsNm_B");
		contrCloDt_B = (EZDCDateItem)newItem("contrCloDt_B");
		supprCrFlg_B = (EZDCStringItem)newItem("supprCrFlg_B");
		trmnTotAmt_B = (EZDCBigDecimalItem)newItem("trmnTotAmt_B");
		trmnOvrdTotAmt_B = (EZDCBigDecimalItem)newItem("trmnOvrdTotAmt_B");
		xxChkBox_B = (EZDCStringItem)newItem("xxChkBox_B");
		contrTrmnFlg_B = (EZDCStringItem)newItem("contrTrmnFlg_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0400_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0400_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_B", "dsContrPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum_B", "dsContrNum_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrCatgCd_B", "dsContrCatgCd_B", "B", null, TYPE_HANKAKUEISU, "3", null},
	{"xxScrItem40Txt_B", "xxScrItem40Txt_B", "B", null, TYPE_HANKAKUEISU, "40", null},
	{"contrVrsnEffFromDt_B", "contrVrsnEffFromDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"contrVrsnEffThruDt_B", "contrVrsnEffThruDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"dsAcctNm_B", "dsAcctNm_B", "B", null, TYPE_HANKAKUEISU, "360", null},
	{"dsContrCtrlStsCd_B", "dsContrCtrlStsCd_B", "B", null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrCtrlStsNm_B", "dsContrCtrlStsNm_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"contrCloDt_B", "contrCloDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"supprCrFlg_B", "supprCrFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"trmnTotAmt_B", "trmnTotAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"trmnOvrdTotAmt_B", "trmnOvrdTotAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"contrTrmnFlg_B", "contrTrmnFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_B
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_B
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_B
        {"XX_SCR_ITEM_40_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem40Txt_B
        {"CONTR_VRSN_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffFromDt_B
        {"CONTR_VRSN_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffThruDt_B
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_B
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd_B
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm_B
        {"CONTR_CLO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCloDt_B
        {"SUPPR_CR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supprCrFlg_B
        {"TRMN_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trmnTotAmt_B
        {"TRMN_OVRD_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trmnOvrdTotAmt_B
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"CONTR_TRMN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrTrmnFlg_B
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
