//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190723223437000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0990_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0990;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0990 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0990_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_C*/
	public final EZDBStringItem              xxChkBox_C;

    /** XX_DPLY_CTRL_FLG_C*/
	public final EZDBStringItem              xxDplyCtrlFlg_C;

    /** DS_CONTR_NUM_C*/
	public final EZDBStringItem              dsContrNum_C;

    /** DS_CONTR_CATG_CD_C*/
	public final EZDBStringItem              dsContrCatgCd_C;

    /** SER_NUM_C*/
	public final EZDBStringItem              serNum_C;

    /** MDSE_CD_C*/
	public final EZDBStringItem              mdseCd_C;

    /** MDSE_DESC_SHORT_TXT_C*/
	public final EZDBStringItem              mdseDescShortTxt_C;

    /** XX_ALLW_QTY_C*/
	public final EZDBBigDecimalItem              xxAllwQty_C;

    /** XX_USED_QTY_C*/
	public final EZDBBigDecimalItem              xxUsedQty_C;

    /** CPO_MIN_ORD_QTY_C*/
	public final EZDBBigDecimalItem              cpoMinOrdQty_C;

    /** ENT_CPO_DTL_DEAL_SLS_AMT_C*/
	public final EZDBBigDecimalItem              entCpoDtlDealSlsAmt_C;

    /** ORD_CUST_UOM_QTY_C*/
	public final EZDBBigDecimalItem              ordCustUomQty_C;

    /** ENT_DEAL_NET_UNIT_PRC_AMT_C*/
	public final EZDBBigDecimalItem              entDealNetUnitPrcAmt_C;

    /** IN_POUND_WT_C*/
	public final EZDBBigDecimalItem              inPoundWt_C;

    /** BLACK_COLOR_FLG_C*/
	public final EZDBStringItem              blackColorFlg_C;

    /** PRC_COND_MAN_DEL_FLG_C*/
	public final EZDBStringItem              prcCondManDelFlg_C;

    /** XX_TBL_SORT_NUM_C*/
	public final EZDBBigDecimalItem              xxTblSortNum_C;

    /** XX_SORT_NUM_C*/
	public final EZDBBigDecimalItem              xxSortNum_C;

    /** IMG_SPLY_COLOR_TP_NM_C*/
	public final EZDBStringItem              imgSplyColorTpNm_C;

    /** T_MDL_ID_C*/
	public final EZDBBigDecimalItem              t_MdlId_C;

    /** IMG_SPLY_OEM_CD_C*/
	public final EZDBStringItem              imgSplyOemCd_C;

    /** IMG_SPLY_TP_CD_C*/
	public final EZDBStringItem              imgSplyTpCd_C;

    /** XX_ROW_NUM_C*/
	public final EZDBBigDecimalItem              xxRowNum_C;


	/**
	 * NSAL0990_CBMsg is constructor.
	 * The initialization when the instance of NSAL0990_CBMsg is generated.
	 */
	public NSAL0990_CBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0990_CBMsg is constructor.
	 * The initialization when the instance of NSAL0990_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0990_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_C = (EZDBStringItem)newItem("xxChkBox_C");
		xxDplyCtrlFlg_C = (EZDBStringItem)newItem("xxDplyCtrlFlg_C");
		dsContrNum_C = (EZDBStringItem)newItem("dsContrNum_C");
		dsContrCatgCd_C = (EZDBStringItem)newItem("dsContrCatgCd_C");
		serNum_C = (EZDBStringItem)newItem("serNum_C");
		mdseCd_C = (EZDBStringItem)newItem("mdseCd_C");
		mdseDescShortTxt_C = (EZDBStringItem)newItem("mdseDescShortTxt_C");
		xxAllwQty_C = (EZDBBigDecimalItem)newItem("xxAllwQty_C");
		xxUsedQty_C = (EZDBBigDecimalItem)newItem("xxUsedQty_C");
		cpoMinOrdQty_C = (EZDBBigDecimalItem)newItem("cpoMinOrdQty_C");
		entCpoDtlDealSlsAmt_C = (EZDBBigDecimalItem)newItem("entCpoDtlDealSlsAmt_C");
		ordCustUomQty_C = (EZDBBigDecimalItem)newItem("ordCustUomQty_C");
		entDealNetUnitPrcAmt_C = (EZDBBigDecimalItem)newItem("entDealNetUnitPrcAmt_C");
		inPoundWt_C = (EZDBBigDecimalItem)newItem("inPoundWt_C");
		blackColorFlg_C = (EZDBStringItem)newItem("blackColorFlg_C");
		prcCondManDelFlg_C = (EZDBStringItem)newItem("prcCondManDelFlg_C");
		xxTblSortNum_C = (EZDBBigDecimalItem)newItem("xxTblSortNum_C");
		xxSortNum_C = (EZDBBigDecimalItem)newItem("xxSortNum_C");
		imgSplyColorTpNm_C = (EZDBStringItem)newItem("imgSplyColorTpNm_C");
		t_MdlId_C = (EZDBBigDecimalItem)newItem("t_MdlId_C");
		imgSplyOemCd_C = (EZDBStringItem)newItem("imgSplyOemCd_C");
		imgSplyTpCd_C = (EZDBStringItem)newItem("imgSplyTpCd_C");
		xxRowNum_C = (EZDBBigDecimalItem)newItem("xxRowNum_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0990_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0990_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_C", "xxChkBox_C", "C", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxDplyCtrlFlg_C", "xxDplyCtrlFlg_C", "C", null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrNum_C", "dsContrNum_C", "C", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrCatgCd_C", "dsContrCatgCd_C", "C", null, TYPE_HANKAKUEISU, "3", null},
	{"serNum_C", "serNum_C", "C", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_C", "mdseCd_C", "C", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_C", "mdseDescShortTxt_C", "C", null, TYPE_HANKAKUEISU, "250", null},
	{"xxAllwQty_C", "xxAllwQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxUsedQty_C", "xxUsedQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"cpoMinOrdQty_C", "cpoMinOrdQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"entCpoDtlDealSlsAmt_C", "entCpoDtlDealSlsAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ordCustUomQty_C", "ordCustUomQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"entDealNetUnitPrcAmt_C", "entDealNetUnitPrcAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"inPoundWt_C", "inPoundWt_C", "C", null, TYPE_SEISU_SYOSU, "9", "4"},
	{"blackColorFlg_C", "blackColorFlg_C", "C", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCondManDelFlg_C", "prcCondManDelFlg_C", "C", null, TYPE_HANKAKUEISU, "1", null},
	{"xxTblSortNum_C", "xxTblSortNum_C", "C", null, TYPE_SEISU_SYOSU, "7", "0"},
	{"xxSortNum_C", "xxSortNum_C", "C", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"imgSplyColorTpNm_C", "imgSplyColorTpNm_C", "C", null, TYPE_HANKAKUEISU, "40", null},
	{"t_MdlId_C", "t_MdlId_C", "C", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"imgSplyOemCd_C", "imgSplyOemCd_C", "C", null, TYPE_HANKAKUEISU, "30", null},
	{"imgSplyTpCd_C", "imgSplyTpCd_C", "C", null, TYPE_HANKAKUEISU, "2", null},
	{"xxRowNum_C", "xxRowNum_C", "C", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_C
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_C
        {"DS_CONTR_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_C
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_C
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_C
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_C
        {"XX_ALLW_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllwQty_C
        {"XX_USED_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUsedQty_C
        {"CPO_MIN_ORD_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoMinOrdQty_C
        {"ENT_CPO_DTL_DEAL_SLS_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entCpoDtlDealSlsAmt_C
        {"ORD_CUST_UOM_QTY",  NO,  "0",null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordCustUomQty_C
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt_C
        {"IN_POUND_WT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inPoundWt_C
        {"BLACK_COLOR_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//blackColorFlg_C
        {"PRC_COND_MAN_DEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManDelFlg_C
        {"XX_TBL_SORT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortNum_C
        {"XX_SORT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortNum_C
        {"IMG_SPLY_COLOR_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imgSplyColorTpNm_C
        {"T_MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlId_C
        {"IMG_SPLY_OEM_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imgSplyOemCd_C
        {"IMG_SPLY_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imgSplyTpCd_C
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_C
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

