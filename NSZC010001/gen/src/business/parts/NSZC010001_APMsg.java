//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190528084614000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC010001_APMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC010001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC010001_APMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PHYS_MTR_NUM*/
	public final EZDPStringItem              physMtrNum;

    /** PHYS_MTR_ID*/
	public final EZDPStringItem              physMtrId;

    /** DS_MTR_READ_TP_CD*/
	public final EZDPStringItem              dsMtrReadTpCd;

    /** MTR_READ_DT*/
	public final EZDPDateItem              mtrReadDt;

    /** RGTN_MTR_DT*/
	public final EZDPDateItem              rgtnMtrDt;

    /** SVC_PHYS_MTR_READ_PK*/
	public final EZDPBigDecimalItem              svcPhysMtrReadPk;

    /** SVC_PHYS_MTR_READ_PK_O*/
	public final EZDPBigDecimalItem              svcPhysMtrReadPk_O;

    /** READ_MTR_CNT*/
	public final EZDPBigDecimalItem              readMtrCnt;

    /** TEST_MTR_CNT*/
	public final EZDPBigDecimalItem              testMtrCnt;

    /** RGTN_USR_ID*/
	public final EZDPStringItem              rgtnUsrId;

    /** EST_FLG*/
	public final EZDPStringItem              estFlg;

    /** MTR_LB_CD*/
	public final EZDPStringItem              mtrLbCd;

    /** MTR_CLS_TP_CD*/
	public final EZDPStringItem              mtrClsTpCd;

    /** INV_PROC_FLG*/
	public final EZDPStringItem              invProcFlg;

    /** DS_MDL_MTR_PK*/
	public final EZDPBigDecimalItem              dsMdlMtrPk;

    /** SVC_PHYS_MTR_READ_GRP_SQ*/
	public final EZDPBigDecimalItem              svcPhysMtrReadGrpSq;

    /** SVC_PHYS_MTR_PK*/
	public final EZDPBigDecimalItem              svcPhysMtrPk;

    /** MTR_ENTRY_CMNT_TXT*/
	public final EZDPStringItem              mtrEntryCmntTxt;

    /** MDL_MTR_LB_CD*/
	public final EZDPStringItem              mdlMtrLbCd;

    /** VLD_MTR_FLG*/
	public final EZDPStringItem              vldMtrFlg;

    /** XX_RSLT_STS_CD*/
	public final EZDPStringItem              xxRsltStsCd;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;

    /** XX_DS_MSG_ENTRY_TXT*/
	public final EZDPStringItem              xxDsMsgEntryTxt;

    /** CNTR_RESET_TP_CD*/
	public final EZDPStringItem              cntrResetTpCd;


	/**
	 * NSZC010001_APMsg is constructor.
	 * The initialization when the instance of NSZC010001_APMsg is generated.
	 */
	public NSZC010001_APMsg() {
		this(false, -1);
	}

	/**
	 * NSZC010001_APMsg is constructor.
	 * The initialization when the instance of NSZC010001_APMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC010001_APMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		physMtrNum = (EZDPStringItem)newItem("physMtrNum");
		physMtrId = (EZDPStringItem)newItem("physMtrId");
		dsMtrReadTpCd = (EZDPStringItem)newItem("dsMtrReadTpCd");
		mtrReadDt = (EZDPDateItem)newItem("mtrReadDt");
		rgtnMtrDt = (EZDPDateItem)newItem("rgtnMtrDt");
		svcPhysMtrReadPk = (EZDPBigDecimalItem)newItem("svcPhysMtrReadPk");
		svcPhysMtrReadPk_O = (EZDPBigDecimalItem)newItem("svcPhysMtrReadPk_O");
		readMtrCnt = (EZDPBigDecimalItem)newItem("readMtrCnt");
		testMtrCnt = (EZDPBigDecimalItem)newItem("testMtrCnt");
		rgtnUsrId = (EZDPStringItem)newItem("rgtnUsrId");
		estFlg = (EZDPStringItem)newItem("estFlg");
		mtrLbCd = (EZDPStringItem)newItem("mtrLbCd");
		mtrClsTpCd = (EZDPStringItem)newItem("mtrClsTpCd");
		invProcFlg = (EZDPStringItem)newItem("invProcFlg");
		dsMdlMtrPk = (EZDPBigDecimalItem)newItem("dsMdlMtrPk");
		svcPhysMtrReadGrpSq = (EZDPBigDecimalItem)newItem("svcPhysMtrReadGrpSq");
		svcPhysMtrPk = (EZDPBigDecimalItem)newItem("svcPhysMtrPk");
		mtrEntryCmntTxt = (EZDPStringItem)newItem("mtrEntryCmntTxt");
		mdlMtrLbCd = (EZDPStringItem)newItem("mdlMtrLbCd");
		vldMtrFlg = (EZDPStringItem)newItem("vldMtrFlg");
		xxRsltStsCd = (EZDPStringItem)newItem("xxRsltStsCd");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
		xxDsMsgEntryTxt = (EZDPStringItem)newItem("xxDsMsgEntryTxt");
		cntrResetTpCd = (EZDPStringItem)newItem("cntrResetTpCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC010001_APMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC010001_APMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"physMtrNum", "physMtrNum", null, null, TYPE_HANKAKUEISU, "2", null},
	{"physMtrId", "physMtrId", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsMtrReadTpCd", "dsMtrReadTpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"mtrReadDt", "mtrReadDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rgtnMtrDt", "rgtnMtrDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcPhysMtrReadPk", "svcPhysMtrReadPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcPhysMtrReadPk_O", "svcPhysMtrReadPk_O", "O", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"readMtrCnt", "readMtrCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"testMtrCnt", "testMtrCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rgtnUsrId", "rgtnUsrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"estFlg", "estFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"mtrLbCd", "mtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mtrClsTpCd", "mtrClsTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"invProcFlg", "invProcFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsMdlMtrPk", "dsMdlMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcPhysMtrReadGrpSq", "svcPhysMtrReadGrpSq", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcPhysMtrPk", "svcPhysMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrEntryCmntTxt", "mtrEntryCmntTxt", null, null, TYPE_HANKAKUEISU, "400", null},
	{"mdlMtrLbCd", "mdlMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"vldMtrFlg", "vldMtrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxRsltStsCd", "xxRsltStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	{"xxDsMsgEntryTxt", "xxDsMsgEntryTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"cntrResetTpCd", "cntrResetTpCd", null, null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PHYS_MTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMtrNum
        {"PHYS_MTR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMtrId
        {"DS_MTR_READ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpCd
        {"MTR_READ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadDt
        {"RGTN_MTR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rgtnMtrDt
        {"SVC_PHYS_MTR_READ_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrReadPk
        {"SVC_PHYS_MTR_READ_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrReadPk_O
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt
        {"TEST_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//testMtrCnt
        {"RGTN_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rgtnUsrId
        {"EST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estFlg
        {"MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd
        {"MTR_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrClsTpCd
        {"INV_PROC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invProcFlg
        {"DS_MDL_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMdlMtrPk
        {"SVC_PHYS_MTR_READ_GRP_SQ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrReadGrpSq
        {"SVC_PHYS_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrPk
        {"MTR_ENTRY_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEntryCmntTxt
        {"MDL_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlMtrLbCd
        {"VLD_MTR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldMtrFlg
        {"XX_RSLT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltStsCd
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
        {"XX_DS_MSG_ENTRY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMsgEntryTxt
        {"CNTR_RESET_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntrResetTpCd
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

