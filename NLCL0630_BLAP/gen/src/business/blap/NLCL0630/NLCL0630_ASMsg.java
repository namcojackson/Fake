//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20181212142346000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0630_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0630;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0630 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0630_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PHYS_INVTY_NUM_A1*/
	public final EZDSStringItem              physInvtyNum_A1;

    /** TECH_TOC_CD_A1*/
	public final EZDSStringItem              techTocCd_A1;

    /** TECH_NM_A1*/
	public final EZDSStringItem              techNm_A1;

    /** PHYS_INVTY_DT_A1*/
	public final EZDSDateItem              physInvtyDt_A1;

    /** PHYS_INVTY_CTRL_NM_A1*/
	public final EZDSStringItem              physInvtyCtrlNm_A1;

    /** WH_CD_A1*/
	public final EZDSStringItem              whCd_A1;

    /** RTL_WH_CATG_DESC_TXT_A1*/
	public final EZDSStringItem              rtlWhCatgDescTxt_A1;

    /** SHIP_DT_A1*/
	public final EZDSDateItem              shipDt_A1;

    /** ADJ_GRS_AMT_A1*/
	public final EZDSBigDecimalItem              adjGrsAmt_A1;

    /** ADJ_NET_AMT_A1*/
	public final EZDSBigDecimalItem              adjNetAmt_A1;

    /** PHYS_INVTY_STS_CD_A1*/
	public final EZDSStringItem              physInvtyStsCd_A1;

    /** PHYS_INVTY_STS_NM_A1*/
	public final EZDSStringItem              physInvtyStsNm_A1;

    /** RTL_WH_CD_A1*/
	public final EZDSStringItem              rtlWhCd_A1;

    /** RTL_WH_NM_A1*/
	public final EZDSStringItem              rtlWhNm_A1;


	/**
	 * NLCL0630_ASMsg is constructor.
	 * The initialization when the instance of NLCL0630_ASMsg is generated.
	 */
	public NLCL0630_ASMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0630_ASMsg is constructor.
	 * The initialization when the instance of NLCL0630_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0630_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		physInvtyNum_A1 = (EZDSStringItem)newItem("physInvtyNum_A1");
		techTocCd_A1 = (EZDSStringItem)newItem("techTocCd_A1");
		techNm_A1 = (EZDSStringItem)newItem("techNm_A1");
		physInvtyDt_A1 = (EZDSDateItem)newItem("physInvtyDt_A1");
		physInvtyCtrlNm_A1 = (EZDSStringItem)newItem("physInvtyCtrlNm_A1");
		whCd_A1 = (EZDSStringItem)newItem("whCd_A1");
		rtlWhCatgDescTxt_A1 = (EZDSStringItem)newItem("rtlWhCatgDescTxt_A1");
		shipDt_A1 = (EZDSDateItem)newItem("shipDt_A1");
		adjGrsAmt_A1 = (EZDSBigDecimalItem)newItem("adjGrsAmt_A1");
		adjNetAmt_A1 = (EZDSBigDecimalItem)newItem("adjNetAmt_A1");
		physInvtyStsCd_A1 = (EZDSStringItem)newItem("physInvtyStsCd_A1");
		physInvtyStsNm_A1 = (EZDSStringItem)newItem("physInvtyStsNm_A1");
		rtlWhCd_A1 = (EZDSStringItem)newItem("rtlWhCd_A1");
		rtlWhNm_A1 = (EZDSStringItem)newItem("rtlWhNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0630_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0630_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"physInvtyNum_A1", "physInvtyNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"techTocCd_A1", "techTocCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"techNm_A1", "techNm_A1", "A1", null, TYPE_HANKAKUEISU, "45", null},
	{"physInvtyDt_A1", "physInvtyDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"physInvtyCtrlNm_A1", "physInvtyCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"whCd_A1", "whCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCatgDescTxt_A1", "rtlWhCatgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"shipDt_A1", "shipDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"adjGrsAmt_A1", "adjGrsAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"adjNetAmt_A1", "adjNetAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"physInvtyStsCd_A1", "physInvtyStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"physInvtyStsNm_A1", "physInvtyStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "70", null},
	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PHYS_INVTY_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyNum_A1
        {"TECH_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd_A1
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_A1
        {"PHYS_INVTY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyDt_A1
        {"PHYS_INVTY_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyCtrlNm_A1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_A1
        {"RTL_WH_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgDescTxt_A1
        {"SHIP_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipDt_A1
        {"ADJ_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjGrsAmt_A1
        {"ADJ_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjNetAmt_A1
        {"PHYS_INVTY_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyStsCd_A1
        {"PHYS_INVTY_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyStsNm_A1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
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

