//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190528084614000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC010001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSZC010001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** MTR_READ_SRC_TP_CD*/
	public final EZDPStringItem              mtrReadSrcTpCd;

    /** DS_MTR_READ_TP_CD*/
	public final EZDPStringItem              dsMtrReadTpCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** SVC_TASK_NUM*/
	public final EZDPStringItem              svcTaskNum;

    /** FSR_NUM*/
	public final EZDPStringItem              fsrNum;

    /** FSR_VISIT_NUM*/
	public final EZDPStringItem              fsrVisitNum;

    /** DS_TEST_COPY_CLS_CD*/
	public final EZDPStringItem              dsTestCopyClsCd;

    /** XX_WRN_SKIP_FLG*/
	public final EZDPStringItem              xxWrnSkipFlg;

    /** XX_START_READ_FLG*/
	public final EZDPStringItem              xxStartReadFlg;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** CONTR_EFF_FROM_DT*/
	public final EZDPDateItem              contrEffFromDt;

    /** DS_MTR_READ_TP_GRP_CD*/
	public final EZDPStringItem              dsMtrReadTpGrpCd;

    /** XX_RQST_FLG_WR*/
	public final EZDPStringItem              xxRqstFlg_WR;

    /** A*/
	public final business.parts.NSZC010001_APMsgArray              A;

    /** XX_RSLT_STS_CD*/
	public final EZDPStringItem              xxRsltStsCd;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;

    /** XX_DS_MSG_ENTRY_TXT*/
	public final EZDPStringItem              xxDsMsgEntryTxt;

    /** xxMsgIdList*/
	public final business.parts.NSZC010001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC010001PMsg is constructor.
	 * The initialization when the instance of NSZC010001PMsg is generated.
	 */
	public NSZC010001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC010001PMsg is constructor.
	 * The initialization when the instance of NSZC010001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC010001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		mtrReadSrcTpCd = (EZDPStringItem)newItem("mtrReadSrcTpCd");
		dsMtrReadTpCd = (EZDPStringItem)newItem("dsMtrReadTpCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		svcTaskNum = (EZDPStringItem)newItem("svcTaskNum");
		fsrNum = (EZDPStringItem)newItem("fsrNum");
		fsrVisitNum = (EZDPStringItem)newItem("fsrVisitNum");
		dsTestCopyClsCd = (EZDPStringItem)newItem("dsTestCopyClsCd");
		xxWrnSkipFlg = (EZDPStringItem)newItem("xxWrnSkipFlg");
		xxStartReadFlg = (EZDPStringItem)newItem("xxStartReadFlg");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		contrEffFromDt = (EZDPDateItem)newItem("contrEffFromDt");
		dsMtrReadTpGrpCd = (EZDPStringItem)newItem("dsMtrReadTpGrpCd");
		xxRqstFlg_WR = (EZDPStringItem)newItem("xxRqstFlg_WR");
		A = (business.parts.NSZC010001_APMsgArray)newMsgArray("A");
		xxRsltStsCd = (EZDPStringItem)newItem("xxRsltStsCd");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
		xxDsMsgEntryTxt = (EZDPStringItem)newItem("xxDsMsgEntryTxt");
		xxMsgIdList = (business.parts.NSZC010001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC010001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC010001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"mtrReadSrcTpCd", "mtrReadSrcTpCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"dsMtrReadTpCd", "dsMtrReadTpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcTaskNum", "svcTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"fsrNum", "fsrNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"fsrVisitNum", "fsrVisitNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"dsTestCopyClsCd", "dsTestCopyClsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxStartReadFlg", "xxStartReadFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"contrEffFromDt", "contrEffFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsMtrReadTpGrpCd", "dsMtrReadTpGrpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"xxRqstFlg_WR", "xxRqstFlg_WR", "WR", null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "999", "business.parts.NSZC010001_APMsgArray", null, null},
	
	{"xxRsltStsCd", "xxRsltStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	{"xxDsMsgEntryTxt", "xxDsMsgEntryTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxMsgIdList", "xxMsgIdList", null, "999", "business.parts.NSZC010001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"MTR_READ_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadSrcTpCd
        {"DS_MTR_READ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum
        {"FSR_VISIT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitNum
        {"DS_TEST_COPY_CLS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsTestCopyClsCd
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
        {"XX_START_READ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxStartReadFlg
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"CONTR_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffFromDt
        {"DS_MTR_READ_TP_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpGrpCd
        {"XX_RQST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstFlg_WR
		null,	//A
        {"XX_RSLT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltStsCd
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
        {"XX_DS_MSG_ENTRY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMsgEntryTxt
		null,	//xxMsgIdList
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
