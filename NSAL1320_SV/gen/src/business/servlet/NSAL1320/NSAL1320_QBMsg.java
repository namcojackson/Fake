//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240325170156000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1320_QBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1320;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1320 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1320_QBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ORD_POSN_NUM_Q*/
	public final EZDBStringItem              dsOrdPosnNum_Q;

    /** MDL_ID_Q*/
	public final EZDBBigDecimalItem              mdlId_Q;

    /** T_MDL_NM_Q*/
	public final EZDBStringItem              t_MdlNm_Q;

    /** DPLY_LINE_NUM_Q*/
	public final EZDBStringItem              dplyLineNum_Q;

    /** CPO_DTL_LINE_NUM_Q*/
	public final EZDBStringItem              cpoDtlLineNum_Q;

    /** CPO_DTL_LINE_SUB_NUM_Q*/
	public final EZDBStringItem              cpoDtlLineSubNum_Q;

    /** MDSE_CD_Q*/
	public final EZDBStringItem              mdseCd_Q;

    /** MDSE_NM_Q*/
	public final EZDBStringItem              mdseNm_Q;

    /** MDSE_ITEM_TP_CD_Q*/
	public final EZDBStringItem              mdseItemTpCd_Q;

    /** MDSE_ITEM_TP_NM_Q*/
	public final EZDBStringItem              mdseItemTpNm_Q;

    /** XX_GENL_FLD_AREA_TXT_Q*/
	public final EZDBStringItem              xxGenlFldAreaTxt_Q;

    /** MTR_READ_METH_CD_Q*/
	public final EZDBStringItem              mtrReadMethCd_Q;

    /** BASE_AMT_Q*/
	public final EZDBBigDecimalItem              baseAmt_Q;

    /** MAN_CONTR_OVRD_FLG_Q*/
	public final EZDBStringItem              manContrOvrdFlg_Q;

    /** MAN_CONTR_OVRD_RSN_CD_Q*/
	public final EZDBStringItem              manContrOvrdRsnCd_Q;

    /** MAN_CONTR_OVRD_CMNT_TXT_Q*/
	public final EZDBStringItem              manContrOvrdCmntTxt_Q;

    /** DS_CONTR_PK_Q*/
	public final EZDBBigDecimalItem              dsContrPk_Q;

    /** BILL_TO_CUST_LOC_CD_Q*/
	public final EZDBStringItem              billToCustLocCd_Q;

    /** SHIP_TO_CUST_LOC_CD_Q*/
	public final EZDBStringItem              shipToCustLocCd_Q;

    /** SHPG_STS_CD_Q*/
	public final EZDBStringItem              shpgStsCd_Q;

    /** ADD_ASRY_FLG_Q*/
	public final EZDBStringItem              addAsryFlg_Q;


	/**
	 * NSAL1320_QBMsg is constructor.
	 * The initialization when the instance of NSAL1320_QBMsg is generated.
	 */
	public NSAL1320_QBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1320_QBMsg is constructor.
	 * The initialization when the instance of NSAL1320_QBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1320_QBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsOrdPosnNum_Q = (EZDBStringItem)newItem("dsOrdPosnNum_Q");
		mdlId_Q = (EZDBBigDecimalItem)newItem("mdlId_Q");
		t_MdlNm_Q = (EZDBStringItem)newItem("t_MdlNm_Q");
		dplyLineNum_Q = (EZDBStringItem)newItem("dplyLineNum_Q");
		cpoDtlLineNum_Q = (EZDBStringItem)newItem("cpoDtlLineNum_Q");
		cpoDtlLineSubNum_Q = (EZDBStringItem)newItem("cpoDtlLineSubNum_Q");
		mdseCd_Q = (EZDBStringItem)newItem("mdseCd_Q");
		mdseNm_Q = (EZDBStringItem)newItem("mdseNm_Q");
		mdseItemTpCd_Q = (EZDBStringItem)newItem("mdseItemTpCd_Q");
		mdseItemTpNm_Q = (EZDBStringItem)newItem("mdseItemTpNm_Q");
		xxGenlFldAreaTxt_Q = (EZDBStringItem)newItem("xxGenlFldAreaTxt_Q");
		mtrReadMethCd_Q = (EZDBStringItem)newItem("mtrReadMethCd_Q");
		baseAmt_Q = (EZDBBigDecimalItem)newItem("baseAmt_Q");
		manContrOvrdFlg_Q = (EZDBStringItem)newItem("manContrOvrdFlg_Q");
		manContrOvrdRsnCd_Q = (EZDBStringItem)newItem("manContrOvrdRsnCd_Q");
		manContrOvrdCmntTxt_Q = (EZDBStringItem)newItem("manContrOvrdCmntTxt_Q");
		dsContrPk_Q = (EZDBBigDecimalItem)newItem("dsContrPk_Q");
		billToCustLocCd_Q = (EZDBStringItem)newItem("billToCustLocCd_Q");
		shipToCustLocCd_Q = (EZDBStringItem)newItem("shipToCustLocCd_Q");
		shpgStsCd_Q = (EZDBStringItem)newItem("shpgStsCd_Q");
		addAsryFlg_Q = (EZDBStringItem)newItem("addAsryFlg_Q");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1320_QBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1320_QBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsOrdPosnNum_Q", "dsOrdPosnNum_Q", "Q", null, TYPE_HANKAKUEISU, "6", null},
	{"mdlId_Q", "mdlId_Q", "Q", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"t_MdlNm_Q", "t_MdlNm_Q", "Q", null, TYPE_HANKAKUEISU, "50", null},
	{"dplyLineNum_Q", "dplyLineNum_Q", "Q", null, TYPE_HANKAKUEISU, "20", null},
	{"cpoDtlLineNum_Q", "cpoDtlLineNum_Q", "Q", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_Q", "cpoDtlLineSubNum_Q", "Q", null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd_Q", "mdseCd_Q", "Q", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm_Q", "mdseNm_Q", "Q", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseItemTpCd_Q", "mdseItemTpCd_Q", "Q", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpNm_Q", "mdseItemTpNm_Q", "Q", null, TYPE_HANKAKUEISU, "40", null},
	{"xxGenlFldAreaTxt_Q", "xxGenlFldAreaTxt_Q", "Q", null, TYPE_HANKAKUEISU, "1000", null},
	{"mtrReadMethCd_Q", "mtrReadMethCd_Q", "Q", null, TYPE_HANKAKUEISU, "2", null},
	{"baseAmt_Q", "baseAmt_Q", "Q", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"manContrOvrdFlg_Q", "manContrOvrdFlg_Q", "Q", null, TYPE_HANKAKUEISU, "1", null},
	{"manContrOvrdRsnCd_Q", "manContrOvrdRsnCd_Q", "Q", null, TYPE_HANKAKUEISU, "5", null},
	{"manContrOvrdCmntTxt_Q", "manContrOvrdCmntTxt_Q", "Q", null, TYPE_HANKAKUEISU, "2000", null},
	{"dsContrPk_Q", "dsContrPk_Q", "Q", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"billToCustLocCd_Q", "billToCustLocCd_Q", "Q", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd_Q", "shipToCustLocCd_Q", "Q", null, TYPE_HANKAKUEISU, "20", null},
	{"shpgStsCd_Q", "shpgStsCd_Q", "Q", null, TYPE_HANKAKUEISU, "2", null},
	{"addAsryFlg_Q", "addAsryFlg_Q", "Q", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_Q
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_Q
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_Q
        {"DPLY_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_Q
        {"CPO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_Q
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_Q
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_Q
        {"MDSE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_Q
        {"MDSE_ITEM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_Q
        {"MDSE_ITEM_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpNm_Q
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_Q
        {"MTR_READ_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_Q
        {"BASE_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseAmt_Q
        {"MAN_CONTR_OVRD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manContrOvrdFlg_Q
        {"MAN_CONTR_OVRD_RSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manContrOvrdRsnCd_Q
        {"MAN_CONTR_OVRD_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manContrOvrdCmntTxt_Q
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_Q
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd_Q
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_Q
        {"SHPG_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsCd_Q
        {"ADD_ASRY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addAsryFlg_Q
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
