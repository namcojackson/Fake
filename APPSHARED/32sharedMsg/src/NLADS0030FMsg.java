//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20100108120415000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLADS0030FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLADS0030 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NLADS0030FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** IMPT_INV_CNSGN_CD*/
	public final EZDFStringItem              imptInvCnsgnCd;

    /** IMPT_INV_CNSGN_ABBR_NM*/
	public final EZDFStringItem              imptInvCnsgnAbbrNm;

    /** IMPT_INV_CNSGN_NM_01*/
	public final EZDFStringItem              imptInvCnsgnNm_01;

    /** IMPT_INV_CNSGN_NM_02*/
	public final EZDFStringItem              imptInvCnsgnNm_02;

    /** IMPT_INV_CNSGN_ADDR_01*/
	public final EZDFStringItem              imptInvCnsgnAddr_01;

    /** IMPT_INV_CNSGN_ADDR_02*/
	public final EZDFStringItem              imptInvCnsgnAddr_02;

    /** IMPT_INV_CNSGN_ADDR_03*/
	public final EZDFStringItem              imptInvCnsgnAddr_03;

    /** IMPT_INV_CNSGN_ADDR_04*/
	public final EZDFStringItem              imptInvCnsgnAddr_04;

    /** CTRY_CD*/
	public final EZDFStringItem              ctryCd;

    /** XD_FLG*/
	public final EZDFStringItem              xdFlg;

    /** DITL_FLG*/
	public final EZDFStringItem              ditlFlg;

    /** DROP_SHIP_FLG*/
	public final EZDFStringItem              dropShipFlg;

    /** SHIP_TO_CUST_CD*/
	public final EZDFStringItem              shipToCustCd;

    /** DEF_INVTY_LOC_CD*/
	public final EZDFStringItem              defInvtyLocCd;

    /** EFF_FROM_DT_TXT*/
	public final EZDFStringItem              effFromDtTxt;

    /** EFF_THRU_DT_TXT*/
	public final EZDFStringItem              effThruDtTxt;

    /** FIRST_PROD_CTRL_CD*/
	public final EZDFStringItem              firstProdCtrlCd;

    /** INVTY_LOC_CD*/
	public final EZDFStringItem              invtyLocCd;


	/**
	 * NLADS0030FMsg is constructor.
	 * The initialization when the instance of NLADS0030FMsg is generated.
	 */
	public NLADS0030FMsg() {
		this(false, -1);
	}

	/**
	 * NLADS0030FMsg is constructor.
	 * The initialization when the instance of NLADS0030FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLADS0030FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		imptInvCnsgnCd = (EZDFStringItem)newItem("imptInvCnsgnCd");
		imptInvCnsgnAbbrNm = (EZDFStringItem)newItem("imptInvCnsgnAbbrNm");
		imptInvCnsgnNm_01 = (EZDFStringItem)newItem("imptInvCnsgnNm_01");
		imptInvCnsgnNm_02 = (EZDFStringItem)newItem("imptInvCnsgnNm_02");
		imptInvCnsgnAddr_01 = (EZDFStringItem)newItem("imptInvCnsgnAddr_01");
		imptInvCnsgnAddr_02 = (EZDFStringItem)newItem("imptInvCnsgnAddr_02");
		imptInvCnsgnAddr_03 = (EZDFStringItem)newItem("imptInvCnsgnAddr_03");
		imptInvCnsgnAddr_04 = (EZDFStringItem)newItem("imptInvCnsgnAddr_04");
		ctryCd = (EZDFStringItem)newItem("ctryCd");
		xdFlg = (EZDFStringItem)newItem("xdFlg");
		ditlFlg = (EZDFStringItem)newItem("ditlFlg");
		dropShipFlg = (EZDFStringItem)newItem("dropShipFlg");
		shipToCustCd = (EZDFStringItem)newItem("shipToCustCd");
		defInvtyLocCd = (EZDFStringItem)newItem("defInvtyLocCd");
		effFromDtTxt = (EZDFStringItem)newItem("effFromDtTxt");
		effThruDtTxt = (EZDFStringItem)newItem("effThruDtTxt");
		firstProdCtrlCd = (EZDFStringItem)newItem("firstProdCtrlCd");
		invtyLocCd = (EZDFStringItem)newItem("invtyLocCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NLADS0030FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLADS0030FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"imptInvCnsgnCd", "imptInvCnsgnCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"imptInvCnsgnAbbrNm", "imptInvCnsgnAbbrNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"imptInvCnsgnNm_01", "imptInvCnsgnNm_01", "01", null, TYPE_HANKAKUEISU, "30", null},
	{"imptInvCnsgnNm_02", "imptInvCnsgnNm_02", "02", null, TYPE_HANKAKUEISU, "30", null},
	{"imptInvCnsgnAddr_01", "imptInvCnsgnAddr_01", "01", null, TYPE_HANKAKUEISU, "30", null},
	{"imptInvCnsgnAddr_02", "imptInvCnsgnAddr_02", "02", null, TYPE_HANKAKUEISU, "30", null},
	{"imptInvCnsgnAddr_03", "imptInvCnsgnAddr_03", "03", null, TYPE_HANKAKUEISU, "30", null},
	{"imptInvCnsgnAddr_04", "imptInvCnsgnAddr_04", "04", null, TYPE_HANKAKUEISU, "30", null},
	{"ctryCd", "ctryCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xdFlg", "xdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ditlFlg", "ditlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dropShipFlg", "dropShipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"defInvtyLocCd", "defInvtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDtTxt", "effFromDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"effThruDtTxt", "effThruDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"firstProdCtrlCd", "firstProdCtrlCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"IMPT_INV_CNSGN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnCd
        {"IMPT_INV_CNSGN_ABBR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnAbbrNm
        {"IMPT_INV_CNSGN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnNm_01
        {"IMPT_INV_CNSGN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnNm_02
        {"IMPT_INV_CNSGN_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnAddr_01
        {"IMPT_INV_CNSGN_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnAddr_02
        {"IMPT_INV_CNSGN_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnAddr_03
        {"IMPT_INV_CNSGN_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnAddr_04
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd
        {"XD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xdFlg
        {"DITL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ditlFlg
        {"DROP_SHIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dropShipFlg
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"DEF_INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defInvtyLocCd
        {"EFF_FROM_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDtTxt
        {"EFF_THRU_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDtTxt
        {"FIRST_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlCd
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
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

