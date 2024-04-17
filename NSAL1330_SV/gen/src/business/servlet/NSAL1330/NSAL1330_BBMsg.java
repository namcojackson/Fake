//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190118110609000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1330;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1330 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1330_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B*/
	public final EZDBStringItem              xxChkBox_B;

    /** XX_LINE_NUM_B*/
	public final EZDBStringItem              xxLineNum_B;

    /** MDSE_CD_B*/
	public final EZDBStringItem              mdseCd_B;

    /** MDSE_DESC_SHORT_TXT_B*/
	public final EZDBStringItem              mdseDescShortTxt_B;

    /** ADDL_BASE_PRC_DEAL_AMT_B*/
	public final EZDBBigDecimalItem              addlBasePrcDealAmt_B;

    /** ADDL_BASE_PRC_DEAL_AMT_BB*/
	public final EZDBBigDecimalItem              addlBasePrcDealAmt_BB;

    /** PRC_CATG_NM_B*/
	public final EZDBStringItem              prcCatgNm_B;

    /** PRC_LIST_EQUIP_CONFIG_NUM_B*/
	public final EZDBBigDecimalItem              prcListEquipConfigNum_B;

    /** PRC_LIST_EQUIP_CONFIG_NM_B*/
	public final EZDBStringItem              prcListEquipConfigNm_B;

    /** CPO_DTL_LINE_NUM_B*/
	public final EZDBStringItem              cpoDtlLineNum_B;

    /** CPO_DTL_LINE_SUB_NUM_B*/
	public final EZDBStringItem              cpoDtlLineSubNum_B;

    /** ADDL_BASE_PRC_CATG_CD_B*/
	public final EZDBStringItem              addlBasePrcCatgCd_B;

    /** DEAL_PRC_LIST_PRC_AMT_B*/
	public final EZDBBigDecimalItem              dealPrcListPrcAmt_B;

    /** PRC_CATG_CD_B*/
	public final EZDBStringItem              prcCatgCd_B;

    /** SVC_PRC_CATG_CD_B*/
	public final EZDBStringItem              svcPrcCatgCd_B;

    /** BILL_WITH_EQUIP_INVD_FLG_B*/
	public final EZDBStringItem              billWithEquipInvdFlg_B;

    /** CR_REBIL_CD_B*/
	public final EZDBStringItem              crRebilCd_B;

    /** SHELL_LINE_NUM_B*/
	public final EZDBBigDecimalItem              shellLineNum_B;

    /** DS_CONTR_ADDL_CHRG_PK_B*/
	public final EZDBBigDecimalItem              dsContrAddlChrgPk_B;

    /** DS_CONTR_DTL_PK_B*/
	public final EZDBBigDecimalItem              dsContrDtlPk_B;

    /** SCR_ENT_AVAL_FLG_B*/
	public final EZDBStringItem              scrEntAvalFlg_B;


	/**
	 * NSAL1330_BBMsg is constructor.
	 * The initialization when the instance of NSAL1330_BBMsg is generated.
	 */
	public NSAL1330_BBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1330_BBMsg is constructor.
	 * The initialization when the instance of NSAL1330_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1330_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B = (EZDBStringItem)newItem("xxChkBox_B");
		xxLineNum_B = (EZDBStringItem)newItem("xxLineNum_B");
		mdseCd_B = (EZDBStringItem)newItem("mdseCd_B");
		mdseDescShortTxt_B = (EZDBStringItem)newItem("mdseDescShortTxt_B");
		addlBasePrcDealAmt_B = (EZDBBigDecimalItem)newItem("addlBasePrcDealAmt_B");
		addlBasePrcDealAmt_BB = (EZDBBigDecimalItem)newItem("addlBasePrcDealAmt_BB");
		prcCatgNm_B = (EZDBStringItem)newItem("prcCatgNm_B");
		prcListEquipConfigNum_B = (EZDBBigDecimalItem)newItem("prcListEquipConfigNum_B");
		prcListEquipConfigNm_B = (EZDBStringItem)newItem("prcListEquipConfigNm_B");
		cpoDtlLineNum_B = (EZDBStringItem)newItem("cpoDtlLineNum_B");
		cpoDtlLineSubNum_B = (EZDBStringItem)newItem("cpoDtlLineSubNum_B");
		addlBasePrcCatgCd_B = (EZDBStringItem)newItem("addlBasePrcCatgCd_B");
		dealPrcListPrcAmt_B = (EZDBBigDecimalItem)newItem("dealPrcListPrcAmt_B");
		prcCatgCd_B = (EZDBStringItem)newItem("prcCatgCd_B");
		svcPrcCatgCd_B = (EZDBStringItem)newItem("svcPrcCatgCd_B");
		billWithEquipInvdFlg_B = (EZDBStringItem)newItem("billWithEquipInvdFlg_B");
		crRebilCd_B = (EZDBStringItem)newItem("crRebilCd_B");
		shellLineNum_B = (EZDBBigDecimalItem)newItem("shellLineNum_B");
		dsContrAddlChrgPk_B = (EZDBBigDecimalItem)newItem("dsContrAddlChrgPk_B");
		dsContrDtlPk_B = (EZDBBigDecimalItem)newItem("dsContrDtlPk_B");
		scrEntAvalFlg_B = (EZDBStringItem)newItem("scrEntAvalFlg_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1330_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1330_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxLineNum_B", "xxLineNum_B", "B", null, TYPE_HANKAKUEISU, "11", null},
	{"mdseCd_B", "mdseCd_B", "B", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B", "mdseDescShortTxt_B", "B", null, TYPE_HANKAKUEISU, "250", null},
	{"addlBasePrcDealAmt_B", "addlBasePrcDealAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"addlBasePrcDealAmt_BB", "addlBasePrcDealAmt_BB", "BB", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcCatgNm_B", "prcCatgNm_B", "B", null, TYPE_HANKAKUEISU, "75", null},
	{"prcListEquipConfigNum_B", "prcListEquipConfigNum_B", "B", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prcListEquipConfigNm_B", "prcListEquipConfigNm_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"cpoDtlLineNum_B", "cpoDtlLineNum_B", "B", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_B", "cpoDtlLineSubNum_B", "B", null, TYPE_HANKAKUEISU, "3", null},
	{"addlBasePrcCatgCd_B", "addlBasePrcCatgCd_B", "B", null, TYPE_HANKAKUEISU, "10", null},
	{"dealPrcListPrcAmt_B", "dealPrcListPrcAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcCatgCd_B", "prcCatgCd_B", "B", null, TYPE_HANKAKUEISU, "10", null},
	{"svcPrcCatgCd_B", "svcPrcCatgCd_B", "B", null, TYPE_HANKAKUEISU, "4", null},
	{"billWithEquipInvdFlg_B", "billWithEquipInvdFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"crRebilCd_B", "crRebilCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"shellLineNum_B", "shellLineNum_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsContrAddlChrgPk_B", "dsContrAddlChrgPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_B", "dsContrDtlPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"scrEntAvalFlg_B", "scrEntAvalFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"XX_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_B
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B
        {"ADDL_BASE_PRC_DEAL_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//addlBasePrcDealAmt_B
        {"ADDL_BASE_PRC_DEAL_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlBasePrcDealAmt_BB
        {"PRC_CATG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_B
        {"PRC_LIST_EQUIP_CONFIG_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipConfigNum_B
        {"PRC_LIST_EQUIP_CONFIG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipConfigNm_B
        {"CPO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_B
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_B
        {"ADDL_BASE_PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlBasePrcCatgCd_B
        {"DEAL_PRC_LIST_PRC_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//dealPrcListPrcAmt_B
        {"PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_B
        {"SVC_PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPrcCatgCd_B
        {"BILL_WITH_EQUIP_INVD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billWithEquipInvdFlg_B
        {"CR_REBIL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilCd_B
        {"SHELL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shellLineNum_B
        {"DS_CONTR_ADDL_CHRG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrAddlChrgPk_B
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_B
        {"SCR_ENT_AVAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrEntAvalFlg_B
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
