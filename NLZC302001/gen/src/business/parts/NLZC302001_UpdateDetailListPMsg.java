//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151007204029000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC302001_UpdateDetailListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC302001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC302001_UpdateDetailListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_TRX_PK*/
	public final EZDPBigDecimalItem              serTrxPk;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** SER_TRX_TS*/
	public final EZDPStringItem              serTrxTs;

    /** SER_TRX_EVENT_CD*/
	public final EZDPStringItem              serTrxEventCd;

    /** SER_TRX_SRC_TP_CD*/
	public final EZDPStringItem              serTrxSrcTpCd;

    /** SER_TRX_SRC_HDR_NUM*/
	public final EZDPStringItem              serTrxSrcHdrNum;

    /** SER_TRX_SRC_LINE_NUM*/
	public final EZDPStringItem              serTrxSrcLineNum;

    /** SER_TRX_SRC_LINE_SUB_NUM*/
	public final EZDPStringItem              serTrxSrcLineSubNum;

    /** SER_TRX_REF_NUM*/
	public final EZDPStringItem              serTrxRefNum;

    /** FROM_LOC_CD*/
	public final EZDPStringItem              fromLocCd;

    /** TO_LOC_CD*/
	public final EZDPStringItem              toLocCd;

    /** OLD_MDSE_CD*/
	public final EZDPStringItem              oldMdseCd;

    /** MAN_CRAT_FLG*/
	public final EZDPStringItem              manCratFlg;

    /** SER_TRX_CMNT_TXT*/
	public final EZDPStringItem              serTrxCmntTxt;

    /** SER_ERR_STS_CD*/
	public final EZDPStringItem              serErrStsCd;

    /** TO_STK_STS_CD*/
	public final EZDPStringItem              toStkStsCd;

    /** FROM_STK_STS_CD*/
	public final EZDPStringItem              fromStkStsCd;

    /** LOC_STS_CD*/
	public final EZDPStringItem              locStsCd;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;

    /** XX_DS_MSG_ENTRY_TXT*/
	public final EZDPStringItem              xxDsMsgEntryTxt;


	/**
	 * NLZC302001_UpdateDetailListPMsg is constructor.
	 * The initialization when the instance of NLZC302001_UpdateDetailListPMsg is generated.
	 */
	public NLZC302001_UpdateDetailListPMsg() {
		this(false, -1);
	}

	/**
	 * NLZC302001_UpdateDetailListPMsg is constructor.
	 * The initialization when the instance of NLZC302001_UpdateDetailListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC302001_UpdateDetailListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serTrxPk = (EZDPBigDecimalItem)newItem("serTrxPk");
		serNum = (EZDPStringItem)newItem("serNum");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		serTrxTs = (EZDPStringItem)newItem("serTrxTs");
		serTrxEventCd = (EZDPStringItem)newItem("serTrxEventCd");
		serTrxSrcTpCd = (EZDPStringItem)newItem("serTrxSrcTpCd");
		serTrxSrcHdrNum = (EZDPStringItem)newItem("serTrxSrcHdrNum");
		serTrxSrcLineNum = (EZDPStringItem)newItem("serTrxSrcLineNum");
		serTrxSrcLineSubNum = (EZDPStringItem)newItem("serTrxSrcLineSubNum");
		serTrxRefNum = (EZDPStringItem)newItem("serTrxRefNum");
		fromLocCd = (EZDPStringItem)newItem("fromLocCd");
		toLocCd = (EZDPStringItem)newItem("toLocCd");
		oldMdseCd = (EZDPStringItem)newItem("oldMdseCd");
		manCratFlg = (EZDPStringItem)newItem("manCratFlg");
		serTrxCmntTxt = (EZDPStringItem)newItem("serTrxCmntTxt");
		serErrStsCd = (EZDPStringItem)newItem("serErrStsCd");
		toStkStsCd = (EZDPStringItem)newItem("toStkStsCd");
		fromStkStsCd = (EZDPStringItem)newItem("fromStkStsCd");
		locStsCd = (EZDPStringItem)newItem("locStsCd");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
		xxDsMsgEntryTxt = (EZDPStringItem)newItem("xxDsMsgEntryTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC302001_UpdateDetailListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC302001_UpdateDetailListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serTrxPk", "serTrxPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"serTrxTs", "serTrxTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"serTrxEventCd", "serTrxEventCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"serTrxSrcTpCd", "serTrxSrcTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"serTrxSrcHdrNum", "serTrxSrcHdrNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"serTrxSrcLineNum", "serTrxSrcLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"serTrxSrcLineSubNum", "serTrxSrcLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"serTrxRefNum", "serTrxRefNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"fromLocCd", "fromLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"toLocCd", "toLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"oldMdseCd", "oldMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"manCratFlg", "manCratFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"serTrxCmntTxt", "serTrxCmntTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"serErrStsCd", "serErrStsCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"toStkStsCd", "toStkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"fromStkStsCd", "fromStkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"locStsCd", "locStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	{"xxDsMsgEntryTxt", "xxDsMsgEntryTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_TRX_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxPk
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"SER_TRX_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxTs
        {"SER_TRX_EVENT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxEventCd
        {"SER_TRX_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxSrcTpCd
        {"SER_TRX_SRC_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxSrcHdrNum
        {"SER_TRX_SRC_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxSrcLineNum
        {"SER_TRX_SRC_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxSrcLineSubNum
        {"SER_TRX_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxRefNum
        {"FROM_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromLocCd
        {"TO_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toLocCd
        {"OLD_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseCd
        {"MAN_CRAT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manCratFlg
        {"SER_TRX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxCmntTxt
        {"SER_ERR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serErrStsCd
        {"TO_STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toStkStsCd
        {"FROM_STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromStkStsCd
        {"LOC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locStsCd
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
        {"XX_DS_MSG_ENTRY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMsgEntryTxt
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

