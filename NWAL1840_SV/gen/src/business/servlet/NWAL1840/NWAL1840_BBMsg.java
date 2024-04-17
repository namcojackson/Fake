//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231010145423000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1840_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1840 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1840_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B*/
	public final EZDBStringItem              xxChkBox_B;

    /** XX_LINE_NUM_B*/
	public final EZDBStringItem              xxLineNum_B;

    /** SCHD_AGMT_NUM_B*/
	public final EZDBStringItem              schdAgmtNum_B;

    /** SCHD_AGMT_LINE_NUM_B*/
	public final EZDBBigDecimalItem              schdAgmtLineNum_B;

    /** SCHD_AGMT_SCHD_LINE_NUM_B*/
	public final EZDBBigDecimalItem              schdAgmtSchdLineNum_B;

    /** SCHD_AGMT_SCHD_LINE_NUM_DB*/
	public final EZDBBigDecimalItem              schdAgmtSchdLineNum_DB;

    /** RDD_DT_B*/
	public final EZDBDateItem              rddDt_B;

    /** ORD_QTY_B*/
	public final EZDBBigDecimalItem              ordQty_B;

    /** ORD_QTY_BS*/
	public final EZDBBigDecimalItem              ordQty_BS;

    /** ORD_QTY_BD*/
	public final EZDBBigDecimalItem              ordQty_BD;

    /** CPO_ORD_NUM_LK*/
	public final EZDBStringItem              cpoOrdNum_LK;

    /** CPO_ORD_NUM_B*/
	public final EZDBStringItem              cpoOrdNum_B;

    /** CPO_DTL_LINE_NUM_B*/
	public final EZDBStringItem              cpoDtlLineNum_B;

    /** CPO_DTL_LINE_SUB_NUM_B*/
	public final EZDBStringItem              cpoDtlLineSubNum_B;

    /** DS_ORD_POSN_NUM_B*/
	public final EZDBStringItem              dsOrdPosnNum_B;

    /** ORD_CRAT_TS_B*/
	public final EZDBStringItem              ordCratTs_B;

    /** SCHD_AGMT_PLN_CANC_FLG_B*/
	public final EZDBStringItem              schdAgmtPlnCancFlg_B;

    /** ORD_MDSE_CD_B*/
	public final EZDBStringItem              ordMdseCd_B;

    /** XX_EXST_FLG_B*/
	public final EZDBStringItem              xxExstFlg_B;

    /** CANC_FLG_B*/
	public final EZDBStringItem              cancFlg_B;


	/**
	 * NWAL1840_BBMsg is constructor.
	 * The initialization when the instance of NWAL1840_BBMsg is generated.
	 */
	public NWAL1840_BBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1840_BBMsg is constructor.
	 * The initialization when the instance of NWAL1840_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1840_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B = (EZDBStringItem)newItem("xxChkBox_B");
		xxLineNum_B = (EZDBStringItem)newItem("xxLineNum_B");
		schdAgmtNum_B = (EZDBStringItem)newItem("schdAgmtNum_B");
		schdAgmtLineNum_B = (EZDBBigDecimalItem)newItem("schdAgmtLineNum_B");
		schdAgmtSchdLineNum_B = (EZDBBigDecimalItem)newItem("schdAgmtSchdLineNum_B");
		schdAgmtSchdLineNum_DB = (EZDBBigDecimalItem)newItem("schdAgmtSchdLineNum_DB");
		rddDt_B = (EZDBDateItem)newItem("rddDt_B");
		ordQty_B = (EZDBBigDecimalItem)newItem("ordQty_B");
		ordQty_BS = (EZDBBigDecimalItem)newItem("ordQty_BS");
		ordQty_BD = (EZDBBigDecimalItem)newItem("ordQty_BD");
		cpoOrdNum_LK = (EZDBStringItem)newItem("cpoOrdNum_LK");
		cpoOrdNum_B = (EZDBStringItem)newItem("cpoOrdNum_B");
		cpoDtlLineNum_B = (EZDBStringItem)newItem("cpoDtlLineNum_B");
		cpoDtlLineSubNum_B = (EZDBStringItem)newItem("cpoDtlLineSubNum_B");
		dsOrdPosnNum_B = (EZDBStringItem)newItem("dsOrdPosnNum_B");
		ordCratTs_B = (EZDBStringItem)newItem("ordCratTs_B");
		schdAgmtPlnCancFlg_B = (EZDBStringItem)newItem("schdAgmtPlnCancFlg_B");
		ordMdseCd_B = (EZDBStringItem)newItem("ordMdseCd_B");
		xxExstFlg_B = (EZDBStringItem)newItem("xxExstFlg_B");
		cancFlg_B = (EZDBStringItem)newItem("cancFlg_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1840_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1840_BBMsgArray();
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
	{"cpoOrdNum_LK", "cpoOrdNum_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
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

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"XX_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_B
        {"SCHD_AGMT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtNum_B
        {"SCHD_AGMT_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtLineNum_B
        {"SCHD_AGMT_SCHD_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtSchdLineNum_B
        {"SCHD_AGMT_SCHD_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtSchdLineNum_DB
        {"RDD_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rddDt_B
        {"ORD_QTY", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_B
        {"ORD_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_BS
        {"ORD_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_BD
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_LK
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_B
        {"CPO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_B
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_B
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_B
        {"ORD_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordCratTs_B
        {"SCHD_AGMT_PLN_CANC_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtPlnCancFlg_B
        {"ORD_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordMdseCd_B
        {"XX_EXST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_B
        {"CANC_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cancFlg_B
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
