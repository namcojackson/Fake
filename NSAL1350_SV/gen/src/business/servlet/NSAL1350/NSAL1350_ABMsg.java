//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240325171753000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1350_ABMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL1350_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** DS_ORD_POSN_NUM_A*/
	public final EZDBStringItem              dsOrdPosnNum_A;

    /** MDL_ID_A*/
	public final EZDBBigDecimalItem              mdlId_A;

    /** T_MDL_NM_A*/
	public final EZDBStringItem              t_MdlNm_A;

    /** DPLY_LINE_NUM_A*/
	public final EZDBStringItem              dplyLineNum_A;

    /** CPO_DTL_LINE_NUM_A*/
	public final EZDBStringItem              cpoDtlLineNum_A;

    /** CPO_DTL_LINE_SUB_NUM_A*/
	public final EZDBStringItem              cpoDtlLineSubNum_A;

    /** MDSE_CD_A*/
	public final EZDBStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDBStringItem              mdseDescShortTxt_A;

    /** MDSE_ITEM_TP_CD_A*/
	public final EZDBStringItem              mdseItemTpCd_A;

    /** MDSE_ITEM_TP_NM_A*/
	public final EZDBStringItem              mdseItemTpNm_A;

    /** XX_GENL_FLD_AREA_TXT_A*/
	public final EZDBStringItem              xxGenlFldAreaTxt_A;

    /** BILL_TO_CUST_LOC_CD_A*/
	public final EZDBStringItem              billToCustLocCd_A;

    /** SHIP_TO_CUST_LOC_CD_A*/
	public final EZDBStringItem              shipToCustLocCd_A;

    /** SHPG_STS_CD_A*/
	public final EZDBStringItem              shpgStsCd_A;


	/**
	 * NSAL1350_ABMsg is constructor.
	 * The initialization when the instance of NSAL1350_ABMsg is generated.
	 */
	public NSAL1350_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1350_ABMsg is constructor.
	 * The initialization when the instance of NSAL1350_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1350_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		dsOrdPosnNum_A = (EZDBStringItem)newItem("dsOrdPosnNum_A");
		mdlId_A = (EZDBBigDecimalItem)newItem("mdlId_A");
		t_MdlNm_A = (EZDBStringItem)newItem("t_MdlNm_A");
		dplyLineNum_A = (EZDBStringItem)newItem("dplyLineNum_A");
		cpoDtlLineNum_A = (EZDBStringItem)newItem("cpoDtlLineNum_A");
		cpoDtlLineSubNum_A = (EZDBStringItem)newItem("cpoDtlLineSubNum_A");
		mdseCd_A = (EZDBStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDBStringItem)newItem("mdseDescShortTxt_A");
		mdseItemTpCd_A = (EZDBStringItem)newItem("mdseItemTpCd_A");
		mdseItemTpNm_A = (EZDBStringItem)newItem("mdseItemTpNm_A");
		xxGenlFldAreaTxt_A = (EZDBStringItem)newItem("xxGenlFldAreaTxt_A");
		billToCustLocCd_A = (EZDBStringItem)newItem("billToCustLocCd_A");
		shipToCustLocCd_A = (EZDBStringItem)newItem("shipToCustLocCd_A");
		shpgStsCd_A = (EZDBStringItem)newItem("shpgStsCd_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1350_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1350_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsOrdPosnNum_A", "dsOrdPosnNum_A", "A", null, TYPE_HANKAKUEISU, "6", null},
	{"mdlId_A", "mdlId_A", "A", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"t_MdlNm_A", "t_MdlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dplyLineNum_A", "dplyLineNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"cpoDtlLineNum_A", "cpoDtlLineNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_A", "cpoDtlLineSubNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseItemTpCd_A", "mdseItemTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpNm_A", "mdseItemTpNm_A", "A", null, TYPE_HANKAKUEISU, "40", null},
	{"xxGenlFldAreaTxt_A", "xxGenlFldAreaTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"billToCustLocCd_A", "billToCustLocCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd_A", "shipToCustLocCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"shpgStsCd_A", "shpgStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_A
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_A
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A
        {"DPLY_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_A
        {"CPO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_A
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_A
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"MDSE_ITEM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_A
        {"MDSE_ITEM_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpNm_A
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_A
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd_A
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_A
        {"SHPG_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsCd_A
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

