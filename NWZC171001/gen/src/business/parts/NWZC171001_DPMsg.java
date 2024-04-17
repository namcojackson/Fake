//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180918095620000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC171001_DPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC171001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC171001_DPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RQST_TP_CD*/
	public final EZDPStringItem              xxRqstTpCd;

    /** SCHD_AGMT_CTAC_PSN_PK*/
	public final EZDPBigDecimalItem              schdAgmtCtacPsnPk;

    /** CTAC_PSN_PK*/
	public final EZDPBigDecimalItem              ctacPsnPk;

    /** CTAC_PSN_TP_CD*/
	public final EZDPStringItem              ctacPsnTpCd;

    /** CTAC_PSN_OVRD_FLG*/
	public final EZDPStringItem              ctacPsnOvrdFlg;

    /** CTAC_PSN_FIRST_NM*/
	public final EZDPStringItem              ctacPsnFirstNm;

    /** CTAC_PSN_MID_NM*/
	public final EZDPStringItem              ctacPsnMidNm;

    /** CTAC_PSN_LAST_NM*/
	public final EZDPStringItem              ctacPsnLastNm;

    /** CTAC_PSN_TEL_NUM*/
	public final EZDPStringItem              ctacPsnTelNum;

    /** CTAC_PSN_EXTN_NUM*/
	public final EZDPStringItem              ctacPsnExtnNum;

    /** CTAC_PSN_CELL_PHO_NUM*/
	public final EZDPStringItem              ctacPsnCellPhoNum;

    /** CTAC_PSN_FAX_NUM*/
	public final EZDPStringItem              ctacPsnFaxNum;

    /** CTAC_PSN_EML_ADDR*/
	public final EZDPStringItem              ctacPsnEmlAddr;

    /** CTAC_PSN_CMNT_TXT*/
	public final EZDPStringItem              ctacPsnCmntTxt;

    /** CTAC_CUST_REF_TP_CD*/
	public final EZDPStringItem              ctacCustRefTpCd;


	/**
	 * NWZC171001_DPMsg is constructor.
	 * The initialization when the instance of NWZC171001_DPMsg is generated.
	 */
	public NWZC171001_DPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC171001_DPMsg is constructor.
	 * The initialization when the instance of NWZC171001_DPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC171001_DPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRqstTpCd = (EZDPStringItem)newItem("xxRqstTpCd");
		schdAgmtCtacPsnPk = (EZDPBigDecimalItem)newItem("schdAgmtCtacPsnPk");
		ctacPsnPk = (EZDPBigDecimalItem)newItem("ctacPsnPk");
		ctacPsnTpCd = (EZDPStringItem)newItem("ctacPsnTpCd");
		ctacPsnOvrdFlg = (EZDPStringItem)newItem("ctacPsnOvrdFlg");
		ctacPsnFirstNm = (EZDPStringItem)newItem("ctacPsnFirstNm");
		ctacPsnMidNm = (EZDPStringItem)newItem("ctacPsnMidNm");
		ctacPsnLastNm = (EZDPStringItem)newItem("ctacPsnLastNm");
		ctacPsnTelNum = (EZDPStringItem)newItem("ctacPsnTelNum");
		ctacPsnExtnNum = (EZDPStringItem)newItem("ctacPsnExtnNum");
		ctacPsnCellPhoNum = (EZDPStringItem)newItem("ctacPsnCellPhoNum");
		ctacPsnFaxNum = (EZDPStringItem)newItem("ctacPsnFaxNum");
		ctacPsnEmlAddr = (EZDPStringItem)newItem("ctacPsnEmlAddr");
		ctacPsnCmntTxt = (EZDPStringItem)newItem("ctacPsnCmntTxt");
		ctacCustRefTpCd = (EZDPStringItem)newItem("ctacCustRefTpCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC171001_DPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC171001_DPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRqstTpCd", "xxRqstTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"schdAgmtCtacPsnPk", "schdAgmtCtacPsnPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnPk", "ctacPsnPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnTpCd", "ctacPsnTpCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnOvrdFlg", "ctacPsnOvrdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ctacPsnFirstNm", "ctacPsnFirstNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnMidNm", "ctacPsnMidNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm", "ctacPsnLastNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnTelNum", "ctacPsnTelNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnExtnNum", "ctacPsnExtnNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"ctacPsnCellPhoNum", "ctacPsnCellPhoNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnFaxNum", "ctacPsnFaxNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnEmlAddr", "ctacPsnEmlAddr", null, null, TYPE_HANKAKUEISU, "320", null},
	{"ctacPsnCmntTxt", "ctacPsnCmntTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"ctacCustRefTpCd", "ctacCustRefTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd
        {"SCHD_AGMT_CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtCtacPsnPk
        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk
        {"CTAC_PSN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnTpCd
        {"CTAC_PSN_OVRD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnOvrdFlg
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm
        {"CTAC_PSN_MID_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnMidNm
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm
        {"CTAC_PSN_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnTelNum
        {"CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnExtnNum
        {"CTAC_PSN_CELL_PHO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnCellPhoNum
        {"CTAC_PSN_FAX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFaxNum
        {"CTAC_PSN_EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnEmlAddr
        {"CTAC_PSN_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnCmntTxt
        {"CTAC_CUST_REF_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacCustRefTpCd
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
