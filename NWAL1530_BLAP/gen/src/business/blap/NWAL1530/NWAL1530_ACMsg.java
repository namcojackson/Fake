//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230417162950000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1530_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1530;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1530 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1530_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_ORD_NUM_A*/
	public final EZDCStringItem              cpoOrdNum_A;

    /** DS_ORD_POSN_NUM_A*/
	public final EZDCStringItem              dsOrdPosnNum_A;

    /** XX_LINE_NUM_A*/
	public final EZDCStringItem              xxLineNum_A;

    /** MDSE_CD_A*/
	public final EZDCStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDCStringItem              mdseDescShortTxt_A;

    /** MNF_ITEM_CD_A*/
	public final EZDCStringItem              mnfItemCd_A;

    /** DS_CPO_LINE_NUM_A*/
	public final EZDCBigDecimalItem              dsCpoLineNum_A;

    /** DS_CPO_LINE_SUB_NUM_A*/
	public final EZDCBigDecimalItem              dsCpoLineSubNum_A;

    /** ORD_QTY_A*/
	public final EZDCBigDecimalItem              ordQty_A;

    /** ORD_LINE_STS_NM_A*/
	public final EZDCStringItem              ordLineStsNm_A;

    /** SO_NUM_A*/
	public final EZDCStringItem              soNum_A;

    /** PRCH_REQ_NUM_A*/
	public final EZDCStringItem              prchReqNum_A;

    /** PO_ORD_NUM_A*/
	public final EZDCStringItem              poOrdNum_A;

    /** RWS_NUM_A*/
	public final EZDCStringItem              rwsNum_A;

    /** INV_NUM_A*/
	public final EZDCStringItem              invNum_A;

    /** XX_SCR_ITEM_20_TXT_A*/
	public final EZDCStringItem              xxScrItem20Txt_A;


	/**
	 * NWAL1530_ACMsg is constructor.
	 * The initialization when the instance of NWAL1530_ACMsg is generated.
	 */
	public NWAL1530_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1530_ACMsg is constructor.
	 * The initialization when the instance of NWAL1530_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1530_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoOrdNum_A = (EZDCStringItem)newItem("cpoOrdNum_A");
		dsOrdPosnNum_A = (EZDCStringItem)newItem("dsOrdPosnNum_A");
		xxLineNum_A = (EZDCStringItem)newItem("xxLineNum_A");
		mdseCd_A = (EZDCStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDCStringItem)newItem("mdseDescShortTxt_A");
		mnfItemCd_A = (EZDCStringItem)newItem("mnfItemCd_A");
		dsCpoLineNum_A = (EZDCBigDecimalItem)newItem("dsCpoLineNum_A");
		dsCpoLineSubNum_A = (EZDCBigDecimalItem)newItem("dsCpoLineSubNum_A");
		ordQty_A = (EZDCBigDecimalItem)newItem("ordQty_A");
		ordLineStsNm_A = (EZDCStringItem)newItem("ordLineStsNm_A");
		soNum_A = (EZDCStringItem)newItem("soNum_A");
		prchReqNum_A = (EZDCStringItem)newItem("prchReqNum_A");
		poOrdNum_A = (EZDCStringItem)newItem("poOrdNum_A");
		rwsNum_A = (EZDCStringItem)newItem("rwsNum_A");
		invNum_A = (EZDCStringItem)newItem("invNum_A");
		xxScrItem20Txt_A = (EZDCStringItem)newItem("xxScrItem20Txt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1530_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1530_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoOrdNum_A", "cpoOrdNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdPosnNum_A", "dsOrdPosnNum_A", "A", null, TYPE_HANKAKUEISU, "6", null},
	{"xxLineNum_A", "xxLineNum_A", "A", null, TYPE_HANKAKUEISU, "11", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"mnfItemCd_A", "mnfItemCd_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dsCpoLineNum_A", "dsCpoLineNum_A", "A", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"dsCpoLineSubNum_A", "dsCpoLineSubNum_A", "A", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"ordQty_A", "ordQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordLineStsNm_A", "ordLineStsNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"soNum_A", "soNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"prchReqNum_A", "prchReqNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"poOrdNum_A", "poOrdNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"rwsNum_A", "rwsNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"invNum_A", "invNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"xxScrItem20Txt_A", "xxScrItem20Txt_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_A
        {"XX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"MNF_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mnfItemCd_A
        {"DS_CPO_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineNum_A
        {"DS_CPO_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineSubNum_A
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_A
        {"ORD_LINE_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineStsNm_A
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_A
        {"PRCH_REQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_A
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_A
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_A
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A
        {"XX_SCR_ITEM_20_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem20Txt_A
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

