//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160119191435000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3000CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3000;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3000 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3000CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_H1*/
	public final EZDCStringItem              glblCmpyCd_H1;

    /** XX_HDR_NUM_H1*/
	public final EZDCStringItem              xxHdrNum_H1;

    /** MDSE_CD_H1*/
	public final EZDCStringItem              mdseCd_H1;

    /** MDSE_NM_H1*/
	public final EZDCStringItem              mdseNm_H1;

    /** ORD_QTY_H1*/
	public final EZDCBigDecimalItem              ordQty_H1;

    /** XX_SCR_EDT_TP_CD_H1*/
	public final EZDCStringItem              xxScrEdtTpCd_H1;

    /** TRX_LINE_NUM_H1*/
	public final EZDCStringItem              trxLineNum_H1;

    /** TRX_LINE_SUB_NUM_H1*/
	public final EZDCStringItem              trxLineSubNum_H1;

    /** RTL_WH_CD_H1*/
	public final EZDCStringItem              rtlWhCd_H1;

    /** RTL_WH_NM_H1*/
	public final EZDCStringItem              rtlWhNm_H1;

    /** INBD_OTBD_CD_H1*/
	public final EZDCStringItem              inbdOtbdCd_H1;

    /** INVTY_ACCT_CD_H1*/
	public final EZDCStringItem              invtyAcctCd_H1;

    /** XX_WRN_SKIP_FLG_H1*/
	public final EZDCStringItem              xxWrnSkipFlg_H1;

    /** XX_ERR_FLG_H1*/
	public final EZDCStringItem              xxErrFlg_H1;

    /** XX_LINE_NUM_H1*/
	public final EZDCStringItem              xxLineNum_H1;

    /** S*/
	public final business.blap.NLBL3000.NLBL3000_SCMsgArray              S;


	/**
	 * NLBL3000CMsg is constructor.
	 * The initialization when the instance of NLBL3000CMsg is generated.
	 */
	public NLBL3000CMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3000CMsg is constructor.
	 * The initialization when the instance of NLBL3000CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3000CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_H1 = (EZDCStringItem)newItem("glblCmpyCd_H1");
		xxHdrNum_H1 = (EZDCStringItem)newItem("xxHdrNum_H1");
		mdseCd_H1 = (EZDCStringItem)newItem("mdseCd_H1");
		mdseNm_H1 = (EZDCStringItem)newItem("mdseNm_H1");
		ordQty_H1 = (EZDCBigDecimalItem)newItem("ordQty_H1");
		xxScrEdtTpCd_H1 = (EZDCStringItem)newItem("xxScrEdtTpCd_H1");
		trxLineNum_H1 = (EZDCStringItem)newItem("trxLineNum_H1");
		trxLineSubNum_H1 = (EZDCStringItem)newItem("trxLineSubNum_H1");
		rtlWhCd_H1 = (EZDCStringItem)newItem("rtlWhCd_H1");
		rtlWhNm_H1 = (EZDCStringItem)newItem("rtlWhNm_H1");
		inbdOtbdCd_H1 = (EZDCStringItem)newItem("inbdOtbdCd_H1");
		invtyAcctCd_H1 = (EZDCStringItem)newItem("invtyAcctCd_H1");
		xxWrnSkipFlg_H1 = (EZDCStringItem)newItem("xxWrnSkipFlg_H1");
		xxErrFlg_H1 = (EZDCStringItem)newItem("xxErrFlg_H1");
		xxLineNum_H1 = (EZDCStringItem)newItem("xxLineNum_H1");
		S = (business.blap.NLBL3000.NLBL3000_SCMsgArray)newMsgArray("S");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3000CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3000CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_H1", "glblCmpyCd_H1", "H1", null, TYPE_HANKAKUEISU, "4", null},
	{"xxHdrNum_H1", "xxHdrNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_H1", "mdseCd_H1", "H1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm_H1", "mdseNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"ordQty_H1", "ordQty_H1", "H1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxScrEdtTpCd_H1", "xxScrEdtTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"trxLineNum_H1", "trxLineNum_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"trxLineSubNum_H1", "trxLineSubNum_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhCd_H1", "rtlWhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_H1", "rtlWhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"inbdOtbdCd_H1", "inbdOtbdCd_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"invtyAcctCd_H1", "invtyAcctCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxWrnSkipFlg_H1", "xxWrnSkipFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxErrFlg_H1", "xxErrFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxLineNum_H1", "xxLineNum_H1", "H1", null, TYPE_HANKAKUEISU, "11", null},
	{"S", "S", null, "200", "business.blap.NLBL3000.NLBL3000_SCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_H1
        {"XX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNum_H1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_H1
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_H1
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_H1
        {"XX_SCR_EDT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEdtTpCd_H1
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum_H1
        {"TRX_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineSubNum_H1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_H1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H1
        {"INBD_OTBD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOtbdCd_H1
        {"INVTY_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAcctCd_H1
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg_H1
        {"XX_ERR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg_H1
        {"XX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_H1
		null,	//S
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

