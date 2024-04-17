//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160117223730000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1500_CSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1500 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1500_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RWS_REF_NUM_C1*/
	public final EZDSStringItem              rwsRefNum_C1;

    /** RWS_NUM_C1*/
	public final EZDSStringItem              rwsNum_C1;

    /** RWS_DTL_LINE_NUM_C1*/
	public final EZDSStringItem              rwsDtlLineNum_C1;

    /** MDSE_CD_C1*/
	public final EZDSStringItem              mdseCd_C1;

    /** MDSE_DESC_LONG_TXT_C1*/
	public final EZDSStringItem              mdseDescLongTxt_C1;

    /** WH_CD_C1*/
	public final EZDSStringItem              whCd_C1;

    /** RWS_QTY_C1*/
	public final EZDSBigDecimalItem              rwsQty_C1;

    /** RWS_PUT_AWAY_QTY_C1*/
	public final EZDSBigDecimalItem              rwsPutAwayQty_C1;

    /** WH_IN_ETA_DT_C1*/
	public final EZDSDateItem              whInEtaDt_C1;

    /** RWS_STK_DT_TM_TS_C1*/
	public final EZDSStringItem              rwsStkDtTmTs_C1;

    /** RWS_STS_CD_C1*/
	public final EZDSStringItem              rwsStsCd_C1;

    /** RWS_STS_DESC_TXT_C1*/
	public final EZDSStringItem              rwsStsDescTxt_C1;


	/**
	 * NPAL1500_CSMsg is constructor.
	 * The initialization when the instance of NPAL1500_CSMsg is generated.
	 */
	public NPAL1500_CSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1500_CSMsg is constructor.
	 * The initialization when the instance of NPAL1500_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1500_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rwsRefNum_C1 = (EZDSStringItem)newItem("rwsRefNum_C1");
		rwsNum_C1 = (EZDSStringItem)newItem("rwsNum_C1");
		rwsDtlLineNum_C1 = (EZDSStringItem)newItem("rwsDtlLineNum_C1");
		mdseCd_C1 = (EZDSStringItem)newItem("mdseCd_C1");
		mdseDescLongTxt_C1 = (EZDSStringItem)newItem("mdseDescLongTxt_C1");
		whCd_C1 = (EZDSStringItem)newItem("whCd_C1");
		rwsQty_C1 = (EZDSBigDecimalItem)newItem("rwsQty_C1");
		rwsPutAwayQty_C1 = (EZDSBigDecimalItem)newItem("rwsPutAwayQty_C1");
		whInEtaDt_C1 = (EZDSDateItem)newItem("whInEtaDt_C1");
		rwsStkDtTmTs_C1 = (EZDSStringItem)newItem("rwsStkDtTmTs_C1");
		rwsStsCd_C1 = (EZDSStringItem)newItem("rwsStsCd_C1");
		rwsStsDescTxt_C1 = (EZDSStringItem)newItem("rwsStsDescTxt_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1500_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1500_CSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rwsRefNum_C1", "rwsRefNum_C1", "C1", null, TYPE_HANKAKUEISU, "15", null},
	{"rwsNum_C1", "rwsNum_C1", "C1", null, TYPE_HANKAKUEISU, "8", null},
	{"rwsDtlLineNum_C1", "rwsDtlLineNum_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd_C1", "mdseCd_C1", "C1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescLongTxt_C1", "mdseDescLongTxt_C1", "C1", null, TYPE_HANKAKUEISU, "250", null},
	{"whCd_C1", "whCd_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"rwsQty_C1", "rwsQty_C1", "C1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rwsPutAwayQty_C1", "rwsPutAwayQty_C1", "C1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"whInEtaDt_C1", "whInEtaDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"rwsStkDtTmTs_C1", "rwsStkDtTmTs_C1", "C1", null, TYPE_HANKAKUSUJI, "14", null},
	{"rwsStsCd_C1", "rwsStsCd_C1", "C1", null, TYPE_HANKAKUEISU, "2", null},
	{"rwsStsDescTxt_C1", "rwsStsDescTxt_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_C1
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_C1
        {"RWS_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsDtlLineNum_C1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_C1
        {"MDSE_DESC_LONG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescLongTxt_C1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_C1
        {"RWS_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsQty_C1
        {"RWS_PUT_AWAY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsPutAwayQty_C1
        {"WH_IN_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whInEtaDt_C1
        {"RWS_STK_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStkDtTmTs_C1
        {"RWS_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStsCd_C1
        {"RWS_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStsDescTxt_C1
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

