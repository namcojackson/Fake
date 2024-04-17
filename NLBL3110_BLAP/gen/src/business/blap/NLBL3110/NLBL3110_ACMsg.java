//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180221135526000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3110_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3110 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3110_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_NUM_A1*/
	public final EZDCStringItem              cpoNum_A1;

    /** TRX_HDR_NUM_A1*/
	public final EZDCStringItem              trxHdrNum_A1;

    /** TRX_LINE_NUM_A1*/
	public final EZDCStringItem              trxLineNum_A1;

    /** SHIP_TO_CUST_CD_A1*/
	public final EZDCStringItem              shipToCustCd_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDCStringItem              dsAcctNm_A1;

    /** XX_ALL_LINE_ADDR_A1*/
	public final EZDCStringItem              xxAllLineAddr_A1;

    /** CTY_ADDR_A1*/
	public final EZDCStringItem              ctyAddr_A1;

    /** ST_CD_A1*/
	public final EZDCStringItem              stCd_A1;

    /** UPD_USR_ID_A1*/
	public final EZDCStringItem              updUsrId_A1;

    /** UPD_TS_A1*/
	public final EZDCStringItem              updTs_A1;

    /** XX_TS_DSP_19_TXT_A1*/
	public final EZDCStringItem              xxTsDsp19Txt_A1;

    /** SCHD_COORD_STS_DESC_TXT_A1*/
	public final EZDCStringItem              schdCoordStsDescTxt_A1;

    /** SCHD_COORD_PSN_CD_A1*/
	public final EZDCStringItem              schdCoordPsnCd_A1;

    /** SCHD_DELY_DT_A1*/
	public final EZDCDateItem              schdDelyDt_A1;

    /** SCHD_DELY_TM_A1*/
	public final EZDCStringItem              schdDelyTm_A1;

    /** XX_TS_DSP_19_TXT_AS*/
	public final EZDCStringItem              xxTsDsp19Txt_AS;

    /** SHPG_SVC_LVL_DESC_TXT_A1*/
	public final EZDCStringItem              shpgSvcLvlDescTxt_A1;

    /** CARR_NM_A1*/
	public final EZDCStringItem              carrNm_A1;

    /** TEMP_SCHD_RSN_DESC_TXT_A1*/
	public final EZDCStringItem              tempSchdRsnDescTxt_A1;

    /** RTRN_TRK_STS_DESC_TXT_A1*/
	public final EZDCStringItem              rtrnTrkStsDescTxt_A1;

    /** CARR_CMNT_TXT_A1*/
	public final EZDCStringItem              carrCmntTxt_A1;

    /** CARR_UPD_USR_NM_A1*/
	public final EZDCStringItem              carrUpdUsrNm_A1;

    /** TEMP_SCHD_CMNT_TXT_A1*/
	public final EZDCStringItem              tempSchdCmntTxt_A1;


	/**
	 * NLBL3110_ACMsg is constructor.
	 * The initialization when the instance of NLBL3110_ACMsg is generated.
	 */
	public NLBL3110_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3110_ACMsg is constructor.
	 * The initialization when the instance of NLBL3110_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3110_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoNum_A1 = (EZDCStringItem)newItem("cpoNum_A1");
		trxHdrNum_A1 = (EZDCStringItem)newItem("trxHdrNum_A1");
		trxLineNum_A1 = (EZDCStringItem)newItem("trxLineNum_A1");
		shipToCustCd_A1 = (EZDCStringItem)newItem("shipToCustCd_A1");
		dsAcctNm_A1 = (EZDCStringItem)newItem("dsAcctNm_A1");
		xxAllLineAddr_A1 = (EZDCStringItem)newItem("xxAllLineAddr_A1");
		ctyAddr_A1 = (EZDCStringItem)newItem("ctyAddr_A1");
		stCd_A1 = (EZDCStringItem)newItem("stCd_A1");
		updUsrId_A1 = (EZDCStringItem)newItem("updUsrId_A1");
		updTs_A1 = (EZDCStringItem)newItem("updTs_A1");
		xxTsDsp19Txt_A1 = (EZDCStringItem)newItem("xxTsDsp19Txt_A1");
		schdCoordStsDescTxt_A1 = (EZDCStringItem)newItem("schdCoordStsDescTxt_A1");
		schdCoordPsnCd_A1 = (EZDCStringItem)newItem("schdCoordPsnCd_A1");
		schdDelyDt_A1 = (EZDCDateItem)newItem("schdDelyDt_A1");
		schdDelyTm_A1 = (EZDCStringItem)newItem("schdDelyTm_A1");
		xxTsDsp19Txt_AS = (EZDCStringItem)newItem("xxTsDsp19Txt_AS");
		shpgSvcLvlDescTxt_A1 = (EZDCStringItem)newItem("shpgSvcLvlDescTxt_A1");
		carrNm_A1 = (EZDCStringItem)newItem("carrNm_A1");
		tempSchdRsnDescTxt_A1 = (EZDCStringItem)newItem("tempSchdRsnDescTxt_A1");
		rtrnTrkStsDescTxt_A1 = (EZDCStringItem)newItem("rtrnTrkStsDescTxt_A1");
		carrCmntTxt_A1 = (EZDCStringItem)newItem("carrCmntTxt_A1");
		carrUpdUsrNm_A1 = (EZDCStringItem)newItem("carrUpdUsrNm_A1");
		tempSchdCmntTxt_A1 = (EZDCStringItem)newItem("tempSchdCmntTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3110_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3110_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoNum_A1", "cpoNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"trxHdrNum_A1", "trxHdrNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"trxLineNum_A1", "trxLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"shipToCustCd_A1", "shipToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_A1", "xxAllLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "400", null},
	{"ctyAddr_A1", "ctyAddr_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_A1", "stCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"updUsrId_A1", "updUsrId_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"updTs_A1", "updTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxTsDsp19Txt_A1", "xxTsDsp19Txt_A1", "A1", null, TYPE_HANKAKUEISU, "19", null},
	{"schdCoordStsDescTxt_A1", "schdCoordStsDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"schdCoordPsnCd_A1", "schdCoordPsnCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"schdDelyDt_A1", "schdDelyDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"schdDelyTm_A1", "schdDelyTm_A1", "A1", null, TYPE_HANKAKUSUJI, "6", null},
	{"xxTsDsp19Txt_AS", "xxTsDsp19Txt_AS", "AS", null, TYPE_HANKAKUEISU, "19", null},
	{"shpgSvcLvlDescTxt_A1", "shpgSvcLvlDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"carrNm_A1", "carrNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"tempSchdRsnDescTxt_A1", "tempSchdRsnDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"rtrnTrkStsDescTxt_A1", "rtrnTrkStsDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"carrCmntTxt_A1", "carrCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "4000", null},
	{"carrUpdUsrNm_A1", "carrUpdUsrNm_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"tempSchdCmntTxt_A1", "tempSchdCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "180", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoNum_A1
        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_A1
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum_A1
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_A1
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_A1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_A1
        {"UPD_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updUsrId_A1
        {"UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updTs_A1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_A1
        {"SCHD_COORD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordStsDescTxt_A1
        {"SCHD_COORD_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordPsnCd_A1
        {"SCHD_DELY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDelyDt_A1
        {"SCHD_DELY_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDelyTm_A1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_AS
        {"SHPG_SVC_LVL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlDescTxt_A1
        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_A1
        {"TEMP_SCHD_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tempSchdRsnDescTxt_A1
        {"RTRN_TRK_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnTrkStsDescTxt_A1
        {"CARR_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCmntTxt_A1
        {"CARR_UPD_USR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrUpdUsrNm_A1
        {"TEMP_SCHD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tempSchdCmntTxt_A1
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
