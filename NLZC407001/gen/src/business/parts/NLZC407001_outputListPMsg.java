//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170531123008000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC407001_outputListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC407001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC407001_outputListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM*/
	public final EZDPBigDecimalItem              xxNum;

    /** SCE_ORD_TP_CD*/
	public final EZDPStringItem              sceOrdTpCd;

    /** RWS_NUM*/
	public final EZDPStringItem              rwsNum;

    /** RWS_DTL_LINE_NUM*/
	public final EZDPStringItem              rwsDtlLineNum;

    /** RWS_REF_NUM*/
	public final EZDPStringItem              rwsRefNum;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** INVTY_STK_STS_CD*/
	public final EZDPStringItem              invtyStkStsCd;

    /** RWS_STK_DT_TM_TS*/
	public final EZDPStringItem              rwsStkDtTmTs;

    /** RWS_STK_QTY*/
	public final EZDPBigDecimalItem              rwsStkQty;

    /** WRK_TRX_ID*/
	public final EZDPStringItem              wrkTrxId;

    /** SQ_ID*/
	public final EZDPStringItem              sqId;

    /** INSTL_BASE_CTRL_FLG*/
	public final EZDPStringItem              instlBaseCtrlFlg;

    /** SER_NUM_TAKE_FLG*/
	public final EZDPStringItem              serNumTakeFlg;

    /** PO_RCV_TRX_HDR_NUM*/
	public final EZDPStringItem              poRcvTrxHdrNum;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDPBigDecimalItem              svcConfigMstrPk;

    /** TRX_HDR_NUM_SO*/
	public final EZDPStringItem              trxHdrNum_SO;

    /** INVTY_LOC_CD*/
	public final EZDPStringItem              invtyLocCd;

    /** SHIP_FROM_INVTY_LOC_CD*/
	public final EZDPStringItem              shipFromInvtyLocCd;

    /** RCV_FLG*/
	public final EZDPStringItem              rcvFlg;

    /** TRX_ORD_NUM*/
	public final EZDPStringItem              trxOrdNum;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;


	/**
	 * NLZC407001_outputListPMsg is constructor.
	 * The initialization when the instance of NLZC407001_outputListPMsg is generated.
	 */
	public NLZC407001_outputListPMsg() {
		this(false, -1);
	}

	/**
	 * NLZC407001_outputListPMsg is constructor.
	 * The initialization when the instance of NLZC407001_outputListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC407001_outputListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum = (EZDPBigDecimalItem)newItem("xxNum");
		sceOrdTpCd = (EZDPStringItem)newItem("sceOrdTpCd");
		rwsNum = (EZDPStringItem)newItem("rwsNum");
		rwsDtlLineNum = (EZDPStringItem)newItem("rwsDtlLineNum");
		rwsRefNum = (EZDPStringItem)newItem("rwsRefNum");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		invtyStkStsCd = (EZDPStringItem)newItem("invtyStkStsCd");
		rwsStkDtTmTs = (EZDPStringItem)newItem("rwsStkDtTmTs");
		rwsStkQty = (EZDPBigDecimalItem)newItem("rwsStkQty");
		wrkTrxId = (EZDPStringItem)newItem("wrkTrxId");
		sqId = (EZDPStringItem)newItem("sqId");
		instlBaseCtrlFlg = (EZDPStringItem)newItem("instlBaseCtrlFlg");
		serNumTakeFlg = (EZDPStringItem)newItem("serNumTakeFlg");
		poRcvTrxHdrNum = (EZDPStringItem)newItem("poRcvTrxHdrNum");
		svcConfigMstrPk = (EZDPBigDecimalItem)newItem("svcConfigMstrPk");
		trxHdrNum_SO = (EZDPStringItem)newItem("trxHdrNum_SO");
		invtyLocCd = (EZDPStringItem)newItem("invtyLocCd");
		shipFromInvtyLocCd = (EZDPStringItem)newItem("shipFromInvtyLocCd");
		rcvFlg = (EZDPStringItem)newItem("rcvFlg");
		trxOrdNum = (EZDPStringItem)newItem("trxOrdNum");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC407001_outputListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC407001_outputListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"sceOrdTpCd", "sceOrdTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"rwsNum", "rwsNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"rwsDtlLineNum", "rwsDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"rwsRefNum", "rwsRefNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"invtyStkStsCd", "invtyStkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"rwsStkDtTmTs", "rwsStkDtTmTs", null, null, TYPE_HANKAKUSUJI, "14", null},
	{"rwsStkQty", "rwsStkQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"wrkTrxId", "wrkTrxId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"sqId", "sqId", null, null, TYPE_HANKAKUEISU, "10", null},
	{"instlBaseCtrlFlg", "instlBaseCtrlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"serNumTakeFlg", "serNumTakeFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"poRcvTrxHdrNum", "poRcvTrxHdrNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"trxHdrNum_SO", "trxHdrNum_SO", "SO", null, TYPE_HANKAKUEISU, "8", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipFromInvtyLocCd", "shipFromInvtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rcvFlg", "rcvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"trxOrdNum", "trxOrdNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum
        {"RWS_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsDtlLineNum
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"INVTY_STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyStkStsCd
        {"RWS_STK_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStkDtTmTs
        {"RWS_STK_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStkQty
        {"WRK_TRX_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrkTrxId
        {"SQ_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sqId
        {"INSTL_BASE_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//instlBaseCtrlFlg
        {"SER_NUM_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumTakeFlg
        {"PO_RCV_TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvTrxHdrNum
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_SO
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
        {"SHIP_FROM_INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFromInvtyLocCd
        {"RCV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvFlg
        {"TRX_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxOrdNum
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
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
