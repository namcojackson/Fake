//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530120947000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0870_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0870;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0870 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0870_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_NUM_A*/
	public final EZDCStringItem              serNum_A;

    /** SVC_PHYS_MTR_READ_GRP_SQ_A*/
	public final EZDCBigDecimalItem              svcPhysMtrReadGrpSq_A;

    /** T_MDL_NM_A*/
	public final EZDCStringItem              t_MdlNm_A;

    /** SVC_CONTR_BR_DESC_TXT_A*/
	public final EZDCStringItem              svcContrBrDescTxt_A;

    /** MTR_READ_SRC_TP_DESC_TXT_A*/
	public final EZDCStringItem              mtrReadSrcTpDescTxt_A;

    /** MTR_READ_DT_A*/
	public final EZDCDateItem              mtrReadDt_A;

    /** MTR_CNT_A*/
	public final EZDCBigDecimalItem              mtrCnt_A;

    /** DS_MSG_TXT_A*/
	public final EZDCStringItem              dsMsgTxt_A;

    /** DS_MTR_PROC_STS_DESC_TXT_A*/
	public final EZDCStringItem              dsMtrProcStsDescTxt_A;

    /** DS_MTR_PROC_TS_A1*/
	public final EZDCStringItem              dsMtrProcTs_A1;

    /** DS_MTR_PROC_TS_A2*/
	public final EZDCStringItem              dsMtrProcTs_A2;

    /** RGTN_USR_ID_A*/
	public final EZDCStringItem              rgtnUsrId_A;

    /** MDSE_CD_A*/
	public final EZDCStringItem              mdseCd_A;

    /** SVC_MACH_MSTR_PK_A*/
	public final EZDCBigDecimalItem              svcMachMstrPk_A;

    /** MTR_READ_SRC_TP_CD_A*/
	public final EZDCStringItem              mtrReadSrcTpCd_A;

    /** XX_DT_TM_A1*/
	public final EZDCStringItem              xxDtTm_A1;

    /** XX_DT_TM_A2*/
	public final EZDCStringItem              xxDtTm_A2;


	/**
	 * NSAL0870_ACMsg is constructor.
	 * The initialization when the instance of NSAL0870_ACMsg is generated.
	 */
	public NSAL0870_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0870_ACMsg is constructor.
	 * The initialization when the instance of NSAL0870_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0870_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serNum_A = (EZDCStringItem)newItem("serNum_A");
		svcPhysMtrReadGrpSq_A = (EZDCBigDecimalItem)newItem("svcPhysMtrReadGrpSq_A");
		t_MdlNm_A = (EZDCStringItem)newItem("t_MdlNm_A");
		svcContrBrDescTxt_A = (EZDCStringItem)newItem("svcContrBrDescTxt_A");
		mtrReadSrcTpDescTxt_A = (EZDCStringItem)newItem("mtrReadSrcTpDescTxt_A");
		mtrReadDt_A = (EZDCDateItem)newItem("mtrReadDt_A");
		mtrCnt_A = (EZDCBigDecimalItem)newItem("mtrCnt_A");
		dsMsgTxt_A = (EZDCStringItem)newItem("dsMsgTxt_A");
		dsMtrProcStsDescTxt_A = (EZDCStringItem)newItem("dsMtrProcStsDescTxt_A");
		dsMtrProcTs_A1 = (EZDCStringItem)newItem("dsMtrProcTs_A1");
		dsMtrProcTs_A2 = (EZDCStringItem)newItem("dsMtrProcTs_A2");
		rgtnUsrId_A = (EZDCStringItem)newItem("rgtnUsrId_A");
		mdseCd_A = (EZDCStringItem)newItem("mdseCd_A");
		svcMachMstrPk_A = (EZDCBigDecimalItem)newItem("svcMachMstrPk_A");
		mtrReadSrcTpCd_A = (EZDCStringItem)newItem("mtrReadSrcTpCd_A");
		xxDtTm_A1 = (EZDCStringItem)newItem("xxDtTm_A1");
		xxDtTm_A2 = (EZDCStringItem)newItem("xxDtTm_A2");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0870_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0870_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcPhysMtrReadGrpSq_A", "svcPhysMtrReadGrpSq_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"t_MdlNm_A", "t_MdlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcContrBrDescTxt_A", "svcContrBrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"mtrReadSrcTpDescTxt_A", "mtrReadSrcTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"mtrReadDt_A", "mtrReadDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"mtrCnt_A", "mtrCnt_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsMsgTxt_A", "dsMsgTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"dsMtrProcStsDescTxt_A", "dsMtrProcStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsMtrProcTs_A1", "dsMtrProcTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"dsMtrProcTs_A2", "dsMtrProcTs_A2", "A2", null, TYPE_HANKAKUSUJI, "17", null},
	{"rgtnUsrId_A", "rgtnUsrId_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"svcMachMstrPk_A", "svcMachMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrReadSrcTpCd_A", "mtrReadSrcTpCd_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"xxDtTm_A1", "xxDtTm_A1", "A1", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_A2", "xxDtTm_A2", "A2", null, TYPE_HANKAKUEISU, "23", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"SVC_PHYS_MTR_READ_GRP_SQ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrReadGrpSq_A
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A
        {"MTR_READ_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadSrcTpDescTxt_A
        {"MTR_READ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadDt_A
        {"MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrCnt_A
        {"DS_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMsgTxt_A
        {"DS_MTR_PROC_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrProcStsDescTxt_A
        {"DS_MTR_PROC_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrProcTs_A1
        {"DS_MTR_PROC_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrProcTs_A2
        {"RGTN_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rgtnUsrId_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A
        {"MTR_READ_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadSrcTpCd_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A2
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
