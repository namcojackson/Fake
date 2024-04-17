//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231010145517000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1840_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1840 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1840_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B*/
	public final EZDCStringItem              xxChkBox_B;

    /** XX_LINE_NUM_B*/
	public final EZDCStringItem              xxLineNum_B;

    /** SCHD_AGMT_NUM_B*/
	public final EZDCStringItem              schdAgmtNum_B;

    /** SCHD_AGMT_LINE_NUM_B*/
	public final EZDCBigDecimalItem              schdAgmtLineNum_B;

    /** SCHD_AGMT_SCHD_LINE_NUM_B*/
	public final EZDCBigDecimalItem              schdAgmtSchdLineNum_B;

    /** SCHD_AGMT_SCHD_LINE_NUM_DB*/
	public final EZDCBigDecimalItem              schdAgmtSchdLineNum_DB;

    /** RDD_DT_B*/
	public final EZDCDateItem              rddDt_B;

    /** ORD_QTY_B*/
	public final EZDCBigDecimalItem              ordQty_B;

    /** ORD_QTY_BS*/
	public final EZDCBigDecimalItem              ordQty_BS;

    /** ORD_QTY_BD*/
	public final EZDCBigDecimalItem              ordQty_BD;

    /** CPO_ORD_NUM_B*/
	public final EZDCStringItem              cpoOrdNum_B;

    /** CPO_DTL_LINE_NUM_B*/
	public final EZDCStringItem              cpoDtlLineNum_B;

    /** CPO_DTL_LINE_SUB_NUM_B*/
	public final EZDCStringItem              cpoDtlLineSubNum_B;

    /** DS_ORD_POSN_NUM_B*/
	public final EZDCStringItem              dsOrdPosnNum_B;

    /** ORD_CRAT_TS_B*/
	public final EZDCStringItem              ordCratTs_B;

    /** SCHD_AGMT_PLN_CANC_FLG_B*/
	public final EZDCStringItem              schdAgmtPlnCancFlg_B;

    /** ORD_MDSE_CD_B*/
	public final EZDCStringItem              ordMdseCd_B;

    /** XX_EXST_FLG_B*/
	public final EZDCStringItem              xxExstFlg_B;

    /** CANC_FLG_B*/
	public final EZDCStringItem              cancFlg_B;


	/**
	 * NWAL1840_BCMsg is constructor.
	 * The initialization when the instance of NWAL1840_BCMsg is generated.
	 */
	public NWAL1840_BCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1840_BCMsg is constructor.
	 * The initialization when the instance of NWAL1840_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1840_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B = (EZDCStringItem)newItem("xxChkBox_B");
		xxLineNum_B = (EZDCStringItem)newItem("xxLineNum_B");
		schdAgmtNum_B = (EZDCStringItem)newItem("schdAgmtNum_B");
		schdAgmtLineNum_B = (EZDCBigDecimalItem)newItem("schdAgmtLineNum_B");
		schdAgmtSchdLineNum_B = (EZDCBigDecimalItem)newItem("schdAgmtSchdLineNum_B");
		schdAgmtSchdLineNum_DB = (EZDCBigDecimalItem)newItem("schdAgmtSchdLineNum_DB");
		rddDt_B = (EZDCDateItem)newItem("rddDt_B");
		ordQty_B = (EZDCBigDecimalItem)newItem("ordQty_B");
		ordQty_BS = (EZDCBigDecimalItem)newItem("ordQty_BS");
		ordQty_BD = (EZDCBigDecimalItem)newItem("ordQty_BD");
		cpoOrdNum_B = (EZDCStringItem)newItem("cpoOrdNum_B");
		cpoDtlLineNum_B = (EZDCStringItem)newItem("cpoDtlLineNum_B");
		cpoDtlLineSubNum_B = (EZDCStringItem)newItem("cpoDtlLineSubNum_B");
		dsOrdPosnNum_B = (EZDCStringItem)newItem("dsOrdPosnNum_B");
		ordCratTs_B = (EZDCStringItem)newItem("ordCratTs_B");
		schdAgmtPlnCancFlg_B = (EZDCStringItem)newItem("schdAgmtPlnCancFlg_B");
		ordMdseCd_B = (EZDCStringItem)newItem("ordMdseCd_B");
		xxExstFlg_B = (EZDCStringItem)newItem("xxExstFlg_B");
		cancFlg_B = (EZDCStringItem)newItem("cancFlg_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1840_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1840_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxLineNum_B", "xxLineNum_B", "B", null, TYPE_HANKAKUEISU, "11", null},
	{"schdAgmtNum_B", "schdAgmtNum_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"schdAgmtLineNum_B", "schdAgmtLineNum_B", "B", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"schdAgmtSchdLineNum_B", "schdAgmtSchdLineNum_B", "B", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"schdAgmtSchdLineNum_DB", "schdAgmtSchdLineNum_DB", "DB", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"rddDt_B", "rddDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"ordQty_B", "ordQty_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordQty_BS", "ordQty_BS", "BS", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordQty_BD", "ordQty_BD", "BD", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"cpoOrdNum_B", "cpoOrdNum_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"cpoDtlLineNum_B", "cpoDtlLineNum_B", "B", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_B", "cpoDtlLineSubNum_B", "B", null, TYPE_HANKAKUEISU, "3", null},
	{"dsOrdPosnNum_B", "dsOrdPosnNum_B", "B", null, TYPE_HANKAKUEISU, "6", null},
	{"ordCratTs_B", "ordCratTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"schdAgmtPlnCancFlg_B", "schdAgmtPlnCancFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"ordMdseCd_B", "ordMdseCd_B", "B", null, TYPE_HANKAKUEISU, "16", null},
	{"xxExstFlg_B", "xxExstFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"cancFlg_B", "cancFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"XX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_B
        {"SCHD_AGMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtNum_B
        {"SCHD_AGMT_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtLineNum_B
        {"SCHD_AGMT_SCHD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtSchdLineNum_B
        {"SCHD_AGMT_SCHD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtSchdLineNum_DB
        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt_B
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_B
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_BS
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_BD
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_B
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_B
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_B
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_B
        {"ORD_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordCratTs_B
        {"SCHD_AGMT_PLN_CANC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtPlnCancFlg_B
        {"ORD_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordMdseCd_B
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_B
        {"CANC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cancFlg_B
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

