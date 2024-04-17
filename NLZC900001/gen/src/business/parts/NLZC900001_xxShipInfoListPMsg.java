//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20191226145407000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC900001_xxShipInfoListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLZC900001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC900001_xxShipInfoListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SO_NUM*/
	public final EZDPStringItem              soNum;

    /** SO_SLP_NUM*/
	public final EZDPStringItem              soSlpNum;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** RTL_WH_CD*/
	public final EZDPStringItem              rtlWhCd;

    /** INVTY_OWNR_CD*/
	public final EZDPStringItem              invtyOwnrCd;

    /** RTL_SWH_CD*/
	public final EZDPStringItem              rtlSwhCd;

    /** FROM_STK_STS_CD*/
	public final EZDPStringItem              fromStkStsCd;

    /** BIN_LOC_CD*/
	public final EZDPStringItem              binLocCd;

    /** SHPG_QTY*/
	public final EZDPBigDecimalItem              shpgQty;

    /** TOT_SHPG_WT*/
	public final EZDPBigDecimalItem              totShpgWt;


	/**
	 * NLZC900001_xxShipInfoListPMsg is constructor.
	 * The initialization when the instance of NLZC900001_xxShipInfoListPMsg is generated.
	 */
	public NLZC900001_xxShipInfoListPMsg() {
		this(false, -1);
	}

	/**
	 * NLZC900001_xxShipInfoListPMsg is constructor.
	 * The initialization when the instance of NLZC900001_xxShipInfoListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC900001_xxShipInfoListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		soNum = (EZDPStringItem)newItem("soNum");
		soSlpNum = (EZDPStringItem)newItem("soSlpNum");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		rtlWhCd = (EZDPStringItem)newItem("rtlWhCd");
		invtyOwnrCd = (EZDPStringItem)newItem("invtyOwnrCd");
		rtlSwhCd = (EZDPStringItem)newItem("rtlSwhCd");
		fromStkStsCd = (EZDPStringItem)newItem("fromStkStsCd");
		binLocCd = (EZDPStringItem)newItem("binLocCd");
		shpgQty = (EZDPBigDecimalItem)newItem("shpgQty");
		totShpgWt = (EZDPBigDecimalItem)newItem("totShpgWt");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC900001_xxShipInfoListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC900001_xxShipInfoListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"soNum", "soNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"soSlpNum", "soSlpNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"invtyOwnrCd", "invtyOwnrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"fromStkStsCd", "fromStkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"binLocCd", "binLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shpgQty", "shpgQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"totShpgWt", "totShpgWt", null, null, TYPE_SEISU_SYOSU, "11", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum
        {"SO_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soSlpNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"INVTY_OWNR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOwnrCd
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"FROM_STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromStkStsCd
        {"BIN_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//binLocCd
        {"SHPG_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgQty
        {"TOT_SHPG_WT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totShpgWt
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
