//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180710115057000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6760_CSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6760;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6760 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6760_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NUM_C1*/
	public final EZDSStringItem              dsAcctNum_C1;

    /** DS_ACCT_RELN_TP_NM_C1*/
	public final EZDSStringItem              dsAcctRelnTpNm_C1;

    /** DS_ACCT_NM_C1*/
	public final EZDSStringItem              dsAcctNm_C1;

    /** XX_ALL_LINE_ADDR_C1*/
	public final EZDSStringItem              xxAllLineAddr_C1;

    /** LOC_NUM_C1*/
	public final EZDSStringItem              locNum_C1;

    /** DS_LOC_NM_C1*/
	public final EZDSStringItem              dsLocNm_C1;

    /** CTY_ADDR_C1*/
	public final EZDSStringItem              ctyAddr_C1;

    /** ST_CD_C1*/
	public final EZDSStringItem              stCd_C1;

    /** POST_CD_C1*/
	public final EZDSStringItem              postCd_C1;

    /** XX_YES_NO_CD_C1*/
	public final EZDSStringItem              xxYesNoCd_C1;

    /** DS_ACCT_TP_NM_C1*/
	public final EZDSStringItem              dsAcctTpNm_C1;

    /** XX_CTL_NM_C1*/
	public final EZDSStringItem              xxCtlNm_C1;

    /** RELN_DS_ACCT_NUM_C1*/
	public final EZDSStringItem              relnDsAcctNum_C1;

    /** DS_ACCT_NUM_C2*/
	public final EZDSStringItem              dsAcctNum_C2;

    /** BILL_TO_CUST_CD_C1*/
	public final EZDSStringItem              billToCustCd_C1;

    /** SHIP_TO_CUST_CD_C1*/
	public final EZDSStringItem              shipToCustCd_C1;

    /** FIRST_LINE_ADDR_C1*/
	public final EZDSStringItem              firstLineAddr_C1;

    /** SCD_LINE_ADDR_C1*/
	public final EZDSStringItem              scdLineAddr_C1;

    /** THIRD_LINE_ADDR_C1*/
	public final EZDSStringItem              thirdLineAddr_C1;

    /** FRTH_LINE_ADDR_C1*/
	public final EZDSStringItem              frthLineAddr_C1;

    /** PROV_NM_C1*/
	public final EZDSStringItem              provNm_C1;

    /** CNTY_NM_C1*/
	public final EZDSStringItem              cntyNm_C1;

    /** CTRY_CD_C1*/
	public final EZDSStringItem              ctryCd_C1;

    /** XX_RGTN_STS_TXT_C1*/
	public final EZDSStringItem              xxRgtnStsTxt_C1;

    /** XX_RGTN_STS_TXT_C2*/
	public final EZDSStringItem              xxRgtnStsTxt_C2;

    /** XX_RGTN_STS_TXT_C3*/
	public final EZDSStringItem              xxRgtnStsTxt_C3;

    /** XX_RGTN_STS_TXT_C4*/
	public final EZDSStringItem              xxRgtnStsTxt_C4;

    /** XX_RGTN_STS_TXT_C5*/
	public final EZDSStringItem              xxRgtnStsTxt_C5;

    /** FIRST_REF_CMNT_TXT_C1*/
	public final EZDSStringItem              firstRefCmntTxt_C1;

    /** SCD_REF_CMNT_TXT_C1*/
	public final EZDSStringItem              scdRefCmntTxt_C1;

    /** XX_MST_CHK_FLG_C1*/
	public final EZDSStringItem              xxMstChkFlg_C1;


	/**
	 * NMAL6760_CSMsg is constructor.
	 * The initialization when the instance of NMAL6760_CSMsg is generated.
	 */
	public NMAL6760_CSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6760_CSMsg is constructor.
	 * The initialization when the instance of NMAL6760_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6760_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNum_C1 = (EZDSStringItem)newItem("dsAcctNum_C1");
		dsAcctRelnTpNm_C1 = (EZDSStringItem)newItem("dsAcctRelnTpNm_C1");
		dsAcctNm_C1 = (EZDSStringItem)newItem("dsAcctNm_C1");
		xxAllLineAddr_C1 = (EZDSStringItem)newItem("xxAllLineAddr_C1");
		locNum_C1 = (EZDSStringItem)newItem("locNum_C1");
		dsLocNm_C1 = (EZDSStringItem)newItem("dsLocNm_C1");
		ctyAddr_C1 = (EZDSStringItem)newItem("ctyAddr_C1");
		stCd_C1 = (EZDSStringItem)newItem("stCd_C1");
		postCd_C1 = (EZDSStringItem)newItem("postCd_C1");
		xxYesNoCd_C1 = (EZDSStringItem)newItem("xxYesNoCd_C1");
		dsAcctTpNm_C1 = (EZDSStringItem)newItem("dsAcctTpNm_C1");
		xxCtlNm_C1 = (EZDSStringItem)newItem("xxCtlNm_C1");
		relnDsAcctNum_C1 = (EZDSStringItem)newItem("relnDsAcctNum_C1");
		dsAcctNum_C2 = (EZDSStringItem)newItem("dsAcctNum_C2");
		billToCustCd_C1 = (EZDSStringItem)newItem("billToCustCd_C1");
		shipToCustCd_C1 = (EZDSStringItem)newItem("shipToCustCd_C1");
		firstLineAddr_C1 = (EZDSStringItem)newItem("firstLineAddr_C1");
		scdLineAddr_C1 = (EZDSStringItem)newItem("scdLineAddr_C1");
		thirdLineAddr_C1 = (EZDSStringItem)newItem("thirdLineAddr_C1");
		frthLineAddr_C1 = (EZDSStringItem)newItem("frthLineAddr_C1");
		provNm_C1 = (EZDSStringItem)newItem("provNm_C1");
		cntyNm_C1 = (EZDSStringItem)newItem("cntyNm_C1");
		ctryCd_C1 = (EZDSStringItem)newItem("ctryCd_C1");
		xxRgtnStsTxt_C1 = (EZDSStringItem)newItem("xxRgtnStsTxt_C1");
		xxRgtnStsTxt_C2 = (EZDSStringItem)newItem("xxRgtnStsTxt_C2");
		xxRgtnStsTxt_C3 = (EZDSStringItem)newItem("xxRgtnStsTxt_C3");
		xxRgtnStsTxt_C4 = (EZDSStringItem)newItem("xxRgtnStsTxt_C4");
		xxRgtnStsTxt_C5 = (EZDSStringItem)newItem("xxRgtnStsTxt_C5");
		firstRefCmntTxt_C1 = (EZDSStringItem)newItem("firstRefCmntTxt_C1");
		scdRefCmntTxt_C1 = (EZDSStringItem)newItem("scdRefCmntTxt_C1");
		xxMstChkFlg_C1 = (EZDSStringItem)newItem("xxMstChkFlg_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6760_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6760_CSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctNum_C1", "dsAcctNum_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctRelnTpNm_C1", "dsAcctRelnTpNm_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_C1", "dsAcctNm_C1", "C1", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_C1", "xxAllLineAddr_C1", "C1", null, TYPE_HANKAKUEISU, "400", null},
	{"locNum_C1", "locNum_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsLocNm_C1", "dsLocNm_C1", "C1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_C1", "ctyAddr_C1", "C1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_C1", "stCd_C1", "C1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_C1", "postCd_C1", "C1", null, TYPE_HANKAKUEISU, "15", null},
	{"xxYesNoCd_C1", "xxYesNoCd_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsAcctTpNm_C1", "dsAcctTpNm_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxCtlNm_C1", "xxCtlNm_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	{"relnDsAcctNum_C1", "relnDsAcctNum_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_C2", "dsAcctNum_C2", "C2", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_C1", "billToCustCd_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_C1", "shipToCustCd_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"firstLineAddr_C1", "firstLineAddr_C1", "C1", null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr_C1", "scdLineAddr_C1", "C1", null, TYPE_HANKAKUEISU, "60", null},
	{"thirdLineAddr_C1", "thirdLineAddr_C1", "C1", null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr_C1", "frthLineAddr_C1", "C1", null, TYPE_HANKAKUEISU, "60", null},
	{"provNm_C1", "provNm_C1", "C1", null, TYPE_HANKAKUEISU, "25", null},
	{"cntyNm_C1", "cntyNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"ctryCd_C1", "ctryCd_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"xxRgtnStsTxt_C1", "xxRgtnStsTxt_C1", "C1", null, TYPE_HANKAKUEISU, "56", null},
	{"xxRgtnStsTxt_C2", "xxRgtnStsTxt_C2", "C2", null, TYPE_HANKAKUEISU, "56", null},
	{"xxRgtnStsTxt_C3", "xxRgtnStsTxt_C3", "C3", null, TYPE_HANKAKUEISU, "56", null},
	{"xxRgtnStsTxt_C4", "xxRgtnStsTxt_C4", "C4", null, TYPE_HANKAKUEISU, "56", null},
	{"xxRgtnStsTxt_C5", "xxRgtnStsTxt_C5", "C5", null, TYPE_HANKAKUEISU, "56", null},
	{"firstRefCmntTxt_C1", "firstRefCmntTxt_C1", "C1", null, TYPE_HANKAKUEISU, "90", null},
	{"scdRefCmntTxt_C1", "scdRefCmntTxt_C1", "C1", null, TYPE_HANKAKUEISU, "90", null},
	{"xxMstChkFlg_C1", "xxMstChkFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_C1
        {"DS_ACCT_RELN_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRelnTpNm_C1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_C1
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_C1
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_C1
        {"DS_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocNm_C1
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_C1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_C1
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_C1
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_C1
        {"DS_ACCT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpNm_C1
        {"XX_CTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCtlNm_C1
        {"RELN_DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//relnDsAcctNum_C1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_C2
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_C1
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_C1
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_C1
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr_C1
        {"THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr_C1
        {"FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr_C1
        {"PROV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//provNm_C1
        {"CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm_C1
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd_C1
        {"XX_RGTN_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_C1
        {"XX_RGTN_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_C2
        {"XX_RGTN_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_C3
        {"XX_RGTN_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_C4
        {"XX_RGTN_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_C5
        {"FIRST_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstRefCmntTxt_C1
        {"SCD_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdRefCmntTxt_C1
        {"XX_MST_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMstChkFlg_C1
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
