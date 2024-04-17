//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160413205422000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0090_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL0090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0090 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0090_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BOL_NUM_A1*/
	public final EZDBStringItem              bolNum_A1;

    /** PRO_NUM_A1*/
	public final EZDBStringItem              proNum_A1;

    /** LOC_NM_A1*/
	public final EZDBStringItem              locNm_A1;

    /** WH_CD_A1*/
	public final EZDBStringItem              whCd_A1;

    /** SELL_TO_CUST_CD_A1*/
	public final EZDBStringItem              sellToCustCd_A1;

    /** SHIP_TO_CUST_CD_A1*/
	public final EZDBStringItem              shipToCustCd_A1;

    /** TRX_HDR_NUM_A1*/
	public final EZDBStringItem              trxHdrNum_A1;

    /** SO_NUM_A1*/
	public final EZDBStringItem              soNum_A1;

    /** POD_STS_DT_A1*/
	public final EZDBDateItem              podStsDt_A1;

    /** POD_STS_CD_A2*/
	public final EZDBStringItem              podStsCd_A2;

    /** POD_STS_NM_A1*/
	public final EZDBStringItem              podStsNm_A1;

    /** POD_STS_RSN_CD_A2*/
	public final EZDBStringItem              podStsRsnCd_A2;

    /** POD_STS_RSN_NM_A1*/
	public final EZDBStringItem              podStsRsnNm_A1;

    /** CARR_CD_A1*/
	public final EZDBStringItem              carrCd_A1;

    /** CARR_TRK_URL_A1*/
	public final EZDBStringItem              carrTrkUrl_A1;

    /** POD_STS_TP_FOR_SCR_CD_A1*/
	public final EZDBStringItem              podStsTpForScrCd_A1;


	/**
	 * NLBL0090_ABMsg is constructor.
	 * The initialization when the instance of NLBL0090_ABMsg is generated.
	 */
	public NLBL0090_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0090_ABMsg is constructor.
	 * The initialization when the instance of NLBL0090_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0090_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		bolNum_A1 = (EZDBStringItem)newItem("bolNum_A1");
		proNum_A1 = (EZDBStringItem)newItem("proNum_A1");
		locNm_A1 = (EZDBStringItem)newItem("locNm_A1");
		whCd_A1 = (EZDBStringItem)newItem("whCd_A1");
		sellToCustCd_A1 = (EZDBStringItem)newItem("sellToCustCd_A1");
		shipToCustCd_A1 = (EZDBStringItem)newItem("shipToCustCd_A1");
		trxHdrNum_A1 = (EZDBStringItem)newItem("trxHdrNum_A1");
		soNum_A1 = (EZDBStringItem)newItem("soNum_A1");
		podStsDt_A1 = (EZDBDateItem)newItem("podStsDt_A1");
		podStsCd_A2 = (EZDBStringItem)newItem("podStsCd_A2");
		podStsNm_A1 = (EZDBStringItem)newItem("podStsNm_A1");
		podStsRsnCd_A2 = (EZDBStringItem)newItem("podStsRsnCd_A2");
		podStsRsnNm_A1 = (EZDBStringItem)newItem("podStsRsnNm_A1");
		carrCd_A1 = (EZDBStringItem)newItem("carrCd_A1");
		carrTrkUrl_A1 = (EZDBStringItem)newItem("carrTrkUrl_A1");
		podStsTpForScrCd_A1 = (EZDBStringItem)newItem("podStsTpForScrCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0090_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0090_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"bolNum_A1", "bolNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"proNum_A1", "proNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"locNm_A1", "locNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"whCd_A1", "whCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustCd_A1", "sellToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_A1", "shipToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"trxHdrNum_A1", "trxHdrNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"soNum_A1", "soNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"podStsDt_A1", "podStsDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"podStsCd_A2", "podStsCd_A2", "A2", null, TYPE_HANKAKUEISU, "2", null},
	{"podStsNm_A1", "podStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "70", null},
	{"podStsRsnCd_A2", "podStsRsnCd_A2", "A2", null, TYPE_HANKAKUEISU, "3", null},
	{"podStsRsnNm_A1", "podStsRsnNm_A1", "A1", null, TYPE_HANKAKUEISU, "70", null},
	{"carrCd_A1", "carrCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"carrTrkUrl_A1", "carrTrkUrl_A1", "A1", null, TYPE_HANKAKUEISU, "512", null},
	{"podStsTpForScrCd_A1", "podStsTpForScrCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BOL_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bolNum_A1
        {"PRO_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_A1
        {"LOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A1
        {"WH_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_A1
        {"SELL_TO_CUST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_A1
        {"SHIP_TO_CUST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A1
        {"TRX_HDR_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_A1
        {"SO_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_A1
        {"POD_STS_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//podStsDt_A1
        {"POD_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//podStsCd_A2
        {"POD_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//podStsNm_A1
        {"POD_STS_RSN_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//podStsRsnCd_A2
        {"POD_STS_RSN_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//podStsRsnNm_A1
        {"CARR_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_A1
        {"CARR_TRK_URL",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrTrkUrl_A1
        {"POD_STS_TP_FOR_SCR_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//podStsTpForScrCd_A1
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
