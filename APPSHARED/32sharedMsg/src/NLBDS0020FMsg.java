//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20090908120326000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBDS0020FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLBDS0020 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NLBDS0020FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_OWNR_ID*/
	public final EZDFStringItem              serOwnrId;

    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** SER_NUM*/
	public final EZDFStringItem              serNum;

    /** SER_EVENT_DT_TXT*/
	public final EZDFStringItem              serEventDtTxt;

    /** SER_LOC_GRP_CD*/
	public final EZDFStringItem              serLocGrpCd;

    /** SER_EVENT_CD*/
	public final EZDFStringItem              serEventCd;

    /** TRX_CD*/
	public final EZDFStringItem              trxCd;

    /** TRX_RSN_CD*/
	public final EZDFStringItem              trxRsnCd;

    /** CPO_ORD_TP_CD*/
	public final EZDFStringItem              cpoOrdTpCd;

    /** FROM_LOC_CD*/
	public final EZDFStringItem              fromLocCd;

    /** FROM_LOC_NM*/
	public final EZDFStringItem              fromLocNm;

    /** TO_LOC_CD*/
	public final EZDFStringItem              toLocCd;

    /** TO_LOC_NM*/
	public final EZDFStringItem              toLocNm;

    /** LIC_ACCS_NUM*/
	public final EZDFStringItem              licAccsNum;

    /** OLD_SER_NUM*/
	public final EZDFStringItem              oldSerNum;

    /** PROD_LINE_PROD_CTRL_CD*/
	public final EZDFStringItem              prodLineProdCtrlCd;

    /** KEY_INFO_CD_01*/
	public final EZDFStringItem              keyInfoCd_01;

    /** KEY_INFO_CD_02*/
	public final EZDFStringItem              keyInfoCd_02;

    /** KEY_INFO_CD_03*/
	public final EZDFStringItem              keyInfoCd_03;

    /** KEY_INFO_CD_04*/
	public final EZDFStringItem              keyInfoCd_04;

    /** KEY_INFO_CD_05*/
	public final EZDFStringItem              keyInfoCd_05;

    /** KEY_INFO_CD_06*/
	public final EZDFStringItem              keyInfoCd_06;

    /** KEY_INFO_CD_07*/
	public final EZDFStringItem              keyInfoCd_07;

    /** KEY_INFO_CD_08*/
	public final EZDFStringItem              keyInfoCd_08;

    /** KEY_INFO_CD_09*/
	public final EZDFStringItem              keyInfoCd_09;

    /** KEY_INFO_CD_10*/
	public final EZDFStringItem              keyInfoCd_10;


	/**
	 * NLBDS0020FMsg is constructor.
	 * The initialization when the instance of NLBDS0020FMsg is generated.
	 */
	public NLBDS0020FMsg() {
		this(false, -1);
	}

	/**
	 * NLBDS0020FMsg is constructor.
	 * The initialization when the instance of NLBDS0020FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBDS0020FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serOwnrId = (EZDFStringItem)newItem("serOwnrId");
		mdseCd = (EZDFStringItem)newItem("mdseCd");
		serNum = (EZDFStringItem)newItem("serNum");
		serEventDtTxt = (EZDFStringItem)newItem("serEventDtTxt");
		serLocGrpCd = (EZDFStringItem)newItem("serLocGrpCd");
		serEventCd = (EZDFStringItem)newItem("serEventCd");
		trxCd = (EZDFStringItem)newItem("trxCd");
		trxRsnCd = (EZDFStringItem)newItem("trxRsnCd");
		cpoOrdTpCd = (EZDFStringItem)newItem("cpoOrdTpCd");
		fromLocCd = (EZDFStringItem)newItem("fromLocCd");
		fromLocNm = (EZDFStringItem)newItem("fromLocNm");
		toLocCd = (EZDFStringItem)newItem("toLocCd");
		toLocNm = (EZDFStringItem)newItem("toLocNm");
		licAccsNum = (EZDFStringItem)newItem("licAccsNum");
		oldSerNum = (EZDFStringItem)newItem("oldSerNum");
		prodLineProdCtrlCd = (EZDFStringItem)newItem("prodLineProdCtrlCd");
		keyInfoCd_01 = (EZDFStringItem)newItem("keyInfoCd_01");
		keyInfoCd_02 = (EZDFStringItem)newItem("keyInfoCd_02");
		keyInfoCd_03 = (EZDFStringItem)newItem("keyInfoCd_03");
		keyInfoCd_04 = (EZDFStringItem)newItem("keyInfoCd_04");
		keyInfoCd_05 = (EZDFStringItem)newItem("keyInfoCd_05");
		keyInfoCd_06 = (EZDFStringItem)newItem("keyInfoCd_06");
		keyInfoCd_07 = (EZDFStringItem)newItem("keyInfoCd_07");
		keyInfoCd_08 = (EZDFStringItem)newItem("keyInfoCd_08");
		keyInfoCd_09 = (EZDFStringItem)newItem("keyInfoCd_09");
		keyInfoCd_10 = (EZDFStringItem)newItem("keyInfoCd_10");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBDS0020FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBDS0020FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serOwnrId", "serOwnrId", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"serEventDtTxt", "serEventDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"serLocGrpCd", "serLocGrpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"serEventCd", "serEventCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxCd", "trxCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxRsnCd", "trxRsnCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"cpoOrdTpCd", "cpoOrdTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"fromLocCd", "fromLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"fromLocNm", "fromLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"toLocCd", "toLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"toLocNm", "toLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"licAccsNum", "licAccsNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"oldSerNum", "oldSerNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"prodLineProdCtrlCd", "prodLineProdCtrlCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"keyInfoCd_01", "keyInfoCd_01", "01", null, TYPE_HANKAKUEISU, "30", null},
	{"keyInfoCd_02", "keyInfoCd_02", "02", null, TYPE_HANKAKUEISU, "30", null},
	{"keyInfoCd_03", "keyInfoCd_03", "03", null, TYPE_HANKAKUEISU, "30", null},
	{"keyInfoCd_04", "keyInfoCd_04", "04", null, TYPE_HANKAKUEISU, "30", null},
	{"keyInfoCd_05", "keyInfoCd_05", "05", null, TYPE_HANKAKUEISU, "30", null},
	{"keyInfoCd_06", "keyInfoCd_06", "06", null, TYPE_HANKAKUEISU, "30", null},
	{"keyInfoCd_07", "keyInfoCd_07", "07", null, TYPE_HANKAKUEISU, "30", null},
	{"keyInfoCd_08", "keyInfoCd_08", "08", null, TYPE_HANKAKUEISU, "30", null},
	{"keyInfoCd_09", "keyInfoCd_09", "09", null, TYPE_HANKAKUEISU, "30", null},
	{"keyInfoCd_10", "keyInfoCd_10", "10", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_OWNR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serOwnrId
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"SER_EVENT_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serEventDtTxt
        {"SER_LOC_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serLocGrpCd
        {"SER_EVENT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serEventCd
        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd
        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd
        {"CPO_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdTpCd
        {"FROM_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromLocCd
        {"FROM_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromLocNm
        {"TO_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toLocCd
        {"TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toLocNm
        {"LIC_ACCS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//licAccsNum
        {"OLD_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldSerNum
        {"PROD_LINE_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prodLineProdCtrlCd
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_01
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_02
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_03
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_04
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_05
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_06
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_07
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_08
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_09
        {"KEY_INFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_10
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

