//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231031100058000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010_GBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0010 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0010_GBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_ORD_NUM_GA*/
	public final EZDBStringItem              cpoOrdNum_GA;

    /** CPO_ORD_NUM_G*/
	public final EZDBStringItem              cpoOrdNum_G;

    /** CPO_ORD_TS_G*/
	public final EZDBStringItem              cpoOrdTs_G;

    /** CPO_ORD_DT_G*/
	public final EZDBDateItem              cpoOrdDt_G;

    /** DS_ORD_CATG_CD_G*/
	public final EZDBStringItem              dsOrdCatgCd_G;

    /** DS_ORD_CATG_DESC_TXT_G*/
	public final EZDBStringItem              dsOrdCatgDescTxt_G;

    /** DS_ORD_TP_CD_G*/
	public final EZDBStringItem              dsOrdTpCd_G;

    /** DS_ORD_TP_DESC_TXT_G*/
	public final EZDBStringItem              dsOrdTpDescTxt_G;

    /** DS_ORD_LINE_CATG_CD_G*/
	public final EZDBStringItem              dsOrdLineCatgCd_G;

    /** DS_ORD_LINE_CATG_DESC_TXT_G*/
	public final EZDBStringItem              dsOrdLineCatgDescTxt_G;

    /** SELL_TO_CUST_CD_G*/
	public final EZDBStringItem              sellToCustCd_G;

    /** LOC_NM_GA*/
	public final EZDBStringItem              locNm_GA;

    /** LOC_NM_G1*/
	public final EZDBStringItem              locNm_G1;

    /** BILL_TO_CUST_CD_G*/
	public final EZDBStringItem              billToCustCd_G;

    /** LOC_NM_GB*/
	public final EZDBStringItem              locNm_GB;

    /** LOC_NM_G2*/
	public final EZDBStringItem              locNm_G2;

    /** SHIP_TO_CUST_CD_G*/
	public final EZDBStringItem              shipToCustCd_G;

    /** SHIP_TO_LOC_NM_GC*/
	public final EZDBStringItem              shipToLocNm_GC;

    /** SHIP_TO_LOC_NM_G*/
	public final EZDBStringItem              shipToLocNm_G;

    /** SHIP_TO_ADDR_G*/
	public final EZDBStringItem              shipToAddr_G;

    /** BILL_TO_CUST_CD_GP*/
	public final EZDBStringItem              billToCustCd_GP;

    /** SHIP_TO_CUST_CD_GP*/
	public final EZDBStringItem              shipToCustCd_GP;


	/**
	 * NSAL0010_GBMsg is constructor.
	 * The initialization when the instance of NSAL0010_GBMsg is generated.
	 */
	public NSAL0010_GBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0010_GBMsg is constructor.
	 * The initialization when the instance of NSAL0010_GBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0010_GBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoOrdNum_GA = (EZDBStringItem)newItem("cpoOrdNum_GA");
		cpoOrdNum_G = (EZDBStringItem)newItem("cpoOrdNum_G");
		cpoOrdTs_G = (EZDBStringItem)newItem("cpoOrdTs_G");
		cpoOrdDt_G = (EZDBDateItem)newItem("cpoOrdDt_G");
		dsOrdCatgCd_G = (EZDBStringItem)newItem("dsOrdCatgCd_G");
		dsOrdCatgDescTxt_G = (EZDBStringItem)newItem("dsOrdCatgDescTxt_G");
		dsOrdTpCd_G = (EZDBStringItem)newItem("dsOrdTpCd_G");
		dsOrdTpDescTxt_G = (EZDBStringItem)newItem("dsOrdTpDescTxt_G");
		dsOrdLineCatgCd_G = (EZDBStringItem)newItem("dsOrdLineCatgCd_G");
		dsOrdLineCatgDescTxt_G = (EZDBStringItem)newItem("dsOrdLineCatgDescTxt_G");
		sellToCustCd_G = (EZDBStringItem)newItem("sellToCustCd_G");
		locNm_GA = (EZDBStringItem)newItem("locNm_GA");
		locNm_G1 = (EZDBStringItem)newItem("locNm_G1");
		billToCustCd_G = (EZDBStringItem)newItem("billToCustCd_G");
		locNm_GB = (EZDBStringItem)newItem("locNm_GB");
		locNm_G2 = (EZDBStringItem)newItem("locNm_G2");
		shipToCustCd_G = (EZDBStringItem)newItem("shipToCustCd_G");
		shipToLocNm_GC = (EZDBStringItem)newItem("shipToLocNm_GC");
		shipToLocNm_G = (EZDBStringItem)newItem("shipToLocNm_G");
		shipToAddr_G = (EZDBStringItem)newItem("shipToAddr_G");
		billToCustCd_GP = (EZDBStringItem)newItem("billToCustCd_GP");
		shipToCustCd_GP = (EZDBStringItem)newItem("shipToCustCd_GP");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0010_GBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0010_GBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoOrdNum_GA", "cpoOrdNum_GA", "GA", null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdNum_G", "cpoOrdNum_G", "G", null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdTs_G", "cpoOrdTs_G", "G", null, TYPE_HANKAKUSUJI, "17", null},
	{"cpoOrdDt_G", "cpoOrdDt_G", "G", null, TYPE_NENTSUKIHI, "8", null},
	{"dsOrdCatgCd_G", "dsOrdCatgCd_G", "G", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgDescTxt_G", "dsOrdCatgDescTxt_G", "G", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpCd_G", "dsOrdTpCd_G", "G", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpDescTxt_G", "dsOrdTpDescTxt_G", "G", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdLineCatgCd_G", "dsOrdLineCatgCd_G", "G", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdLineCatgDescTxt_G", "dsOrdLineCatgDescTxt_G", "G", null, TYPE_HANKAKUEISU, "50", null},
	{"sellToCustCd_G", "sellToCustCd_G", "G", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_GA", "locNm_GA", "GA", null, TYPE_HANKAKUEISU, "60", null},
	{"locNm_G1", "locNm_G1", "G1", null, TYPE_HANKAKUEISU, "60", null},
	{"billToCustCd_G", "billToCustCd_G", "G", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_GB", "locNm_GB", "GB", null, TYPE_HANKAKUEISU, "60", null},
	{"locNm_G2", "locNm_G2", "G2", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToCustCd_G", "shipToCustCd_G", "G", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToLocNm_GC", "shipToLocNm_GC", "GC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToLocNm_G", "shipToLocNm_G", "G", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToAddr_G", "shipToAddr_G", "G", null, TYPE_HANKAKUEISU, "120", null},
	{"billToCustCd_GP", "billToCustCd_GP", "GP", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_GP", "shipToCustCd_GP", "GP", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_GA
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_G
        {"CPO_ORD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdTs_G
        {"CPO_ORD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cpoOrdDt_G
        {"DS_ORD_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd_G
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_G
        {"DS_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd_G
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_G
        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd_G
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_G
        {"SELL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_G
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_GA
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_G1
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_G
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_GB
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_G2
        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_G
        {"SHIP_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_GC
        {"SHIP_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_G
        {"SHIP_TO_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToAddr_G
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_GP
        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_GP
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
