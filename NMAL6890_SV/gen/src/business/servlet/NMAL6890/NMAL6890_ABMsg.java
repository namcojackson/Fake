//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230308144648000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6890_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6890;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6890 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6890_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RTL_WH_CD_A*/
	public final EZDBStringItem              rtlWhCd_A;

    /** RTL_WH_NM_A1*/
	public final EZDBStringItem              rtlWhNm_A1;

    /** RTL_WH_DESC_TXT_A*/
	public final EZDBStringItem              rtlWhDescTxt_A;

    /** RTL_WH_CATG_DESC_TXT_A*/
	public final EZDBStringItem              rtlWhCatgDescTxt_A;

    /** FULL_PSN_NM_AO*/
	public final EZDBStringItem              fullPsnNm_AO;

    /** FULL_PSN_NM_AA*/
	public final EZDBStringItem              fullPsnNm_AA;

    /** INVTY_ACCT_DESC_TXT_A*/
	public final EZDBStringItem              invtyAcctDescTxt_A;

    /** SHIP_TO_LOC_NM_A*/
	public final EZDBStringItem              shipToLocNm_A;

    /** RTRN_TO_LOC_NM_A*/
	public final EZDBStringItem              rtrnToLocNm_A;

    /** PROCR_TP_DESC_TXT_AD*/
	public final EZDBStringItem              procrTpDescTxt_AD;

    /** RTL_WH_NM_A2*/
	public final EZDBStringItem              rtlWhNm_A2;

    /** PROCR_TP_DESC_TXT_AR*/
	public final EZDBStringItem              procrTpDescTxt_AR;

    /** RTL_WH_NM_A3*/
	public final EZDBStringItem              rtlWhNm_A3;

    /** PROCR_TP_DESC_TXT_AE*/
	public final EZDBStringItem              procrTpDescTxt_AE;

    /** RTL_WH_NM_A4*/
	public final EZDBStringItem              rtlWhNm_A4;

    /** PLN_ITEM_INSRC_DESC_TXT_A*/
	public final EZDBStringItem              plnItemInsrcDescTxt_A;

    /** CTY_ADDR_A*/
	public final EZDBStringItem              ctyAddr_A;

    /** VND_LOC_CD_A*/
	public final EZDBStringItem              vndLocCd_A;

    /** INVTY_OWNR_DESC_TXT_A*/
	public final EZDBStringItem              invtyOwnrDescTxt_A;

    /** FIRST_REF_CMNT_TXT_A*/
	public final EZDBStringItem              firstRefCmntTxt_A;

    /** EFF_FROM_DT_A*/
	public final EZDBDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDBDateItem              effThruDt_A;

    /** RTL_SWH_CD_A*/
	public final EZDBStringItem              rtlSwhCd_A;


	/**
	 * NMAL6890_ABMsg is constructor.
	 * The initialization when the instance of NMAL6890_ABMsg is generated.
	 */
	public NMAL6890_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6890_ABMsg is constructor.
	 * The initialization when the instance of NMAL6890_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6890_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rtlWhCd_A = (EZDBStringItem)newItem("rtlWhCd_A");
		rtlWhNm_A1 = (EZDBStringItem)newItem("rtlWhNm_A1");
		rtlWhDescTxt_A = (EZDBStringItem)newItem("rtlWhDescTxt_A");
		rtlWhCatgDescTxt_A = (EZDBStringItem)newItem("rtlWhCatgDescTxt_A");
		fullPsnNm_AO = (EZDBStringItem)newItem("fullPsnNm_AO");
		fullPsnNm_AA = (EZDBStringItem)newItem("fullPsnNm_AA");
		invtyAcctDescTxt_A = (EZDBStringItem)newItem("invtyAcctDescTxt_A");
		shipToLocNm_A = (EZDBStringItem)newItem("shipToLocNm_A");
		rtrnToLocNm_A = (EZDBStringItem)newItem("rtrnToLocNm_A");
		procrTpDescTxt_AD = (EZDBStringItem)newItem("procrTpDescTxt_AD");
		rtlWhNm_A2 = (EZDBStringItem)newItem("rtlWhNm_A2");
		procrTpDescTxt_AR = (EZDBStringItem)newItem("procrTpDescTxt_AR");
		rtlWhNm_A3 = (EZDBStringItem)newItem("rtlWhNm_A3");
		procrTpDescTxt_AE = (EZDBStringItem)newItem("procrTpDescTxt_AE");
		rtlWhNm_A4 = (EZDBStringItem)newItem("rtlWhNm_A4");
		plnItemInsrcDescTxt_A = (EZDBStringItem)newItem("plnItemInsrcDescTxt_A");
		ctyAddr_A = (EZDBStringItem)newItem("ctyAddr_A");
		vndLocCd_A = (EZDBStringItem)newItem("vndLocCd_A");
		invtyOwnrDescTxt_A = (EZDBStringItem)newItem("invtyOwnrDescTxt_A");
		firstRefCmntTxt_A = (EZDBStringItem)newItem("firstRefCmntTxt_A");
		effFromDt_A = (EZDBDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDBDateItem)newItem("effThruDt_A");
		rtlSwhCd_A = (EZDBStringItem)newItem("rtlSwhCd_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6890_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6890_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rtlWhCd_A", "rtlWhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhDescTxt_A", "rtlWhDescTxt_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"rtlWhCatgDescTxt_A", "rtlWhCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"fullPsnNm_AO", "fullPsnNm_AO", "AO", null, TYPE_HANKAKUEISU, "62", null},
	{"fullPsnNm_AA", "fullPsnNm_AA", "AA", null, TYPE_HANKAKUEISU, "62", null},
	{"invtyAcctDescTxt_A", "invtyAcctDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"shipToLocNm_A", "shipToLocNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"rtrnToLocNm_A", "rtrnToLocNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"procrTpDescTxt_AD", "procrTpDescTxt_AD", "AD", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhNm_A2", "rtlWhNm_A2", "A2", null, TYPE_HANKAKUEISU, "30", null},
	{"procrTpDescTxt_AR", "procrTpDescTxt_AR", "AR", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhNm_A3", "rtlWhNm_A3", "A3", null, TYPE_HANKAKUEISU, "30", null},
	{"procrTpDescTxt_AE", "procrTpDescTxt_AE", "AE", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhNm_A4", "rtlWhNm_A4", "A4", null, TYPE_HANKAKUEISU, "30", null},
	{"plnItemInsrcDescTxt_A", "plnItemInsrcDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"ctyAddr_A", "ctyAddr_A", "A", null, TYPE_HANKAKUEISU, "25", null},
	{"vndLocCd_A", "vndLocCd_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"invtyOwnrDescTxt_A", "invtyOwnrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"firstRefCmntTxt_A", "firstRefCmntTxt_A", "A", null, TYPE_HANKAKUEISU, "90", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"rtlSwhCd_A", "rtlSwhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RTL_WH_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A
        {"RTL_WH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"RTL_WH_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhDescTxt_A
        {"RTL_WH_CATG_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgDescTxt_A
        {"FULL_PSN_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm_AO
        {"FULL_PSN_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm_AA
        {"INVTY_ACCT_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAcctDescTxt_A
        {"SHIP_TO_LOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_A
        {"RTRN_TO_LOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnToLocNm_A
        {"PROCR_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_AD
        {"RTL_WH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A2
        {"PROCR_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_AR
        {"RTL_WH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A3
        {"PROCR_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_AE
        {"RTL_WH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A4
        {"PLN_ITEM_INSRC_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//plnItemInsrcDescTxt_A
        {"CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_A
        {"VND_LOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndLocCd_A
        {"INVTY_OWNR_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOwnrDescTxt_A
        {"FIRST_REF_CMNT_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstRefCmntTxt_A
        {"EFF_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A
        {"RTL_SWH_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A
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
