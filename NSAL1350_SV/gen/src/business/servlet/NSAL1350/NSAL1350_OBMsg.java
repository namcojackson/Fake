//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240325171753000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1350_OBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1350;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1350 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1350_OBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ORD_POSN_NUM_O*/
	public final EZDBStringItem              dsOrdPosnNum_O;

    /** MDL_ID_O*/
	public final EZDBBigDecimalItem              mdlId_O;

    /** T_MDL_NM_O*/
	public final EZDBStringItem              t_MdlNm_O;

    /** DPLY_LINE_NUM_O*/
	public final EZDBStringItem              dplyLineNum_O;

    /** CPO_DTL_LINE_NUM_O*/
	public final EZDBStringItem              cpoDtlLineNum_O;

    /** CPO_DTL_LINE_SUB_NUM_O*/
	public final EZDBStringItem              cpoDtlLineSubNum_O;

    /** MDSE_CD_O*/
	public final EZDBStringItem              mdseCd_O;

    /** MDSE_DESC_SHORT_TXT_O*/
	public final EZDBStringItem              mdseDescShortTxt_O;

    /** MDSE_ITEM_TP_CD_O*/
	public final EZDBStringItem              mdseItemTpCd_O;

    /** MDSE_ITEM_TP_NM_O*/
	public final EZDBStringItem              mdseItemTpNm_O;

    /** XX_GENL_FLD_AREA_TXT_O*/
	public final EZDBStringItem              xxGenlFldAreaTxt_O;

    /** BILL_TO_CUST_LOC_CD_O*/
	public final EZDBStringItem              billToCustLocCd_O;

    /** SHIP_TO_CUST_LOC_CD_O*/
	public final EZDBStringItem              shipToCustLocCd_O;

    /** SHPG_STS_CD_O*/
	public final EZDBStringItem              shpgStsCd_O;

    /** ADD_ASRY_FLG_O*/
	public final EZDBStringItem              addAsryFlg_O;


	/**
	 * NSAL1350_OBMsg is constructor.
	 * The initialization when the instance of NSAL1350_OBMsg is generated.
	 */
	public NSAL1350_OBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1350_OBMsg is constructor.
	 * The initialization when the instance of NSAL1350_OBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1350_OBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsOrdPosnNum_O = (EZDBStringItem)newItem("dsOrdPosnNum_O");
		mdlId_O = (EZDBBigDecimalItem)newItem("mdlId_O");
		t_MdlNm_O = (EZDBStringItem)newItem("t_MdlNm_O");
		dplyLineNum_O = (EZDBStringItem)newItem("dplyLineNum_O");
		cpoDtlLineNum_O = (EZDBStringItem)newItem("cpoDtlLineNum_O");
		cpoDtlLineSubNum_O = (EZDBStringItem)newItem("cpoDtlLineSubNum_O");
		mdseCd_O = (EZDBStringItem)newItem("mdseCd_O");
		mdseDescShortTxt_O = (EZDBStringItem)newItem("mdseDescShortTxt_O");
		mdseItemTpCd_O = (EZDBStringItem)newItem("mdseItemTpCd_O");
		mdseItemTpNm_O = (EZDBStringItem)newItem("mdseItemTpNm_O");
		xxGenlFldAreaTxt_O = (EZDBStringItem)newItem("xxGenlFldAreaTxt_O");
		billToCustLocCd_O = (EZDBStringItem)newItem("billToCustLocCd_O");
		shipToCustLocCd_O = (EZDBStringItem)newItem("shipToCustLocCd_O");
		shpgStsCd_O = (EZDBStringItem)newItem("shpgStsCd_O");
		addAsryFlg_O = (EZDBStringItem)newItem("addAsryFlg_O");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1350_OBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1350_OBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsOrdPosnNum_O", "dsOrdPosnNum_O", "O", null, TYPE_HANKAKUEISU, "6", null},
	{"mdlId_O", "mdlId_O", "O", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"t_MdlNm_O", "t_MdlNm_O", "O", null, TYPE_HANKAKUEISU, "50", null},
	{"dplyLineNum_O", "dplyLineNum_O", "O", null, TYPE_HANKAKUEISU, "20", null},
	{"cpoDtlLineNum_O", "cpoDtlLineNum_O", "O", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_O", "cpoDtlLineSubNum_O", "O", null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd_O", "mdseCd_O", "O", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_O", "mdseDescShortTxt_O", "O", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseItemTpCd_O", "mdseItemTpCd_O", "O", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpNm_O", "mdseItemTpNm_O", "O", null, TYPE_HANKAKUEISU, "40", null},
	{"xxGenlFldAreaTxt_O", "xxGenlFldAreaTxt_O", "O", null, TYPE_HANKAKUEISU, "1000", null},
	{"billToCustLocCd_O", "billToCustLocCd_O", "O", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd_O", "shipToCustLocCd_O", "O", null, TYPE_HANKAKUEISU, "20", null},
	{"shpgStsCd_O", "shpgStsCd_O", "O", null, TYPE_HANKAKUEISU, "2", null},
	{"addAsryFlg_O", "addAsryFlg_O", "O", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_O
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_O
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_O
        {"DPLY_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_O
        {"CPO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_O
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_O
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_O
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_O
        {"MDSE_ITEM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_O
        {"MDSE_ITEM_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpNm_O
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_O
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd_O
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_O
        {"SHPG_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsCd_O
        {"ADD_ASRY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addAsryFlg_O
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

