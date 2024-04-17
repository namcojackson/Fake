//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180919101304000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1910_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1910;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1910 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1910_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DT_TM_A*/
	public final EZDSStringItem              xxDtTm_A;

    /** XX_SR_USR_ID_A*/
	public final EZDSStringItem              xxSrUsrId_A;

    /** DS_ORD_POSN_NUM_A*/
	public final EZDSStringItem              dsOrdPosnNum_A;

    /** XX_LINE_NUM_A*/
	public final EZDSStringItem              xxLineNum_A;

    /** MDSE_CD_A*/
	public final EZDSStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDSStringItem              mdseDescShortTxt_A;

    /** PRC_DTL_GRP_DESC_TXT_A*/
	public final EZDSStringItem              prcDtlGrpDescTxt_A;

    /** UNIT_PRC_AMT_A*/
	public final EZDSBigDecimalItem              unitPrcAmt_A;


	/**
	 * NWAL1910_ASMsg is constructor.
	 * The initialization when the instance of NWAL1910_ASMsg is generated.
	 */
	public NWAL1910_ASMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1910_ASMsg is constructor.
	 * The initialization when the instance of NWAL1910_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1910_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDtTm_A = (EZDSStringItem)newItem("xxDtTm_A");
		xxSrUsrId_A = (EZDSStringItem)newItem("xxSrUsrId_A");
		dsOrdPosnNum_A = (EZDSStringItem)newItem("dsOrdPosnNum_A");
		xxLineNum_A = (EZDSStringItem)newItem("xxLineNum_A");
		mdseCd_A = (EZDSStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDSStringItem)newItem("mdseDescShortTxt_A");
		prcDtlGrpDescTxt_A = (EZDSStringItem)newItem("prcDtlGrpDescTxt_A");
		unitPrcAmt_A = (EZDSBigDecimalItem)newItem("unitPrcAmt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1910_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1910_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDtTm_A", "xxDtTm_A", "A", null, TYPE_HANKAKUEISU, "23", null},
	{"xxSrUsrId_A", "xxSrUsrId_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"dsOrdPosnNum_A", "dsOrdPosnNum_A", "A", null, TYPE_HANKAKUEISU, "6", null},
	{"xxLineNum_A", "xxLineNum_A", "A", null, TYPE_HANKAKUEISU, "11", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"prcDtlGrpDescTxt_A", "prcDtlGrpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"unitPrcAmt_A", "unitPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A
        {"XX_SR_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSrUsrId_A
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_A
        {"XX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"PRC_DTL_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDtlGrpDescTxt_A
        {"UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPrcAmt_A
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

