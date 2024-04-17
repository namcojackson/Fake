//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20210130090728000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC225001_splyPgmPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC225001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC225001_splyPgmPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_SRC_REF_NUM*/
	public final EZDPStringItem              ordSrcRefNum;

    /** DS_IMPT_SVC_LINE_NUM*/
	public final EZDPBigDecimalItem              dsImptSvcLineNum;

    /** DS_ORD_POSN_NUM*/
	public final EZDPStringItem              dsOrdPosnNum;

    /** ORD_SRC_REF_LINE_NUM*/
	public final EZDPStringItem              ordSrcRefLineNum;

    /** DS_ORD_TP_CD*/
	public final EZDPStringItem              dsOrdTpCd;

    /** SPLY_AGMT_DOC_TP_CD*/
	public final EZDPStringItem              splyAgmtDocTpCd;

    /** QTY_CONTR_CAP_QTY*/
	public final EZDPBigDecimalItem              qtyContrCapQty;

    /** SPLY_AGMT_PLN_SHORT_NM*/
	public final EZDPStringItem              splyAgmtPlnShortNm;

    /** SPLY_BASE_AMT*/
	public final EZDPBigDecimalItem              splyBaseAmt;


	/**
	 * NWZC225001_splyPgmPMsg is constructor.
	 * The initialization when the instance of NWZC225001_splyPgmPMsg is generated.
	 */
	public NWZC225001_splyPgmPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC225001_splyPgmPMsg is constructor.
	 * The initialization when the instance of NWZC225001_splyPgmPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC225001_splyPgmPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordSrcRefNum = (EZDPStringItem)newItem("ordSrcRefNum");
		dsImptSvcLineNum = (EZDPBigDecimalItem)newItem("dsImptSvcLineNum");
		dsOrdPosnNum = (EZDPStringItem)newItem("dsOrdPosnNum");
		ordSrcRefLineNum = (EZDPStringItem)newItem("ordSrcRefLineNum");
		dsOrdTpCd = (EZDPStringItem)newItem("dsOrdTpCd");
		splyAgmtDocTpCd = (EZDPStringItem)newItem("splyAgmtDocTpCd");
		qtyContrCapQty = (EZDPBigDecimalItem)newItem("qtyContrCapQty");
		splyAgmtPlnShortNm = (EZDPStringItem)newItem("splyAgmtPlnShortNm");
		splyBaseAmt = (EZDPBigDecimalItem)newItem("splyBaseAmt");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC225001_splyPgmPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC225001_splyPgmPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordSrcRefNum", "ordSrcRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsImptSvcLineNum", "dsImptSvcLineNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"ordSrcRefLineNum", "ordSrcRefLineNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"splyAgmtDocTpCd", "splyAgmtDocTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"qtyContrCapQty", "qtyContrCapQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"splyAgmtPlnShortNm", "splyAgmtPlnShortNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyBaseAmt", "splyBaseAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum
        {"DS_IMPT_SVC_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcLineNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"ORD_SRC_REF_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefLineNum
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"SPLY_AGMT_DOC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpCd
        {"QTY_CONTR_CAP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qtyContrCapQty
        {"SPLY_AGMT_PLN_SHORT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnShortNm
        {"SPLY_BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyBaseAmt
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

