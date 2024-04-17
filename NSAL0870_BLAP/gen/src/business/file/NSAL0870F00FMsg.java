//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170207105419000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0870F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSAL0870F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0870F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_NUM_A*/
	public final EZDFStringItem              serNum_A;

    /** T_MDL_NM_A*/
	public final EZDFStringItem              t_MdlNm_A;

    /** SVC_CONTR_BR_DESC_TXT_A*/
	public final EZDFStringItem              svcContrBrDescTxt_A;

    /** MTR_READ_SRC_TP_DESC_TXT_A*/
	public final EZDFStringItem              mtrReadSrcTpDescTxt_A;

    /** MTR_LB_DESC_TXT_A*/
	public final EZDFStringItem              mtrLbDescTxt_A;

    /** XX_DT_TXT_A*/
	public final EZDFStringItem              xxDtTxt_A;

    /** DS_MSG_TXT_A*/
	public final EZDFStringItem              dsMsgTxt_A;

    /** DS_MTR_PROC_STS_DESC_TXT_A*/
	public final EZDFStringItem              dsMtrProcStsDescTxt_A;

    /** XX_DT_TM_A1*/
	public final EZDFStringItem              xxDtTm_A1;

    /** XX_DT_TM_A2*/
	public final EZDFStringItem              xxDtTm_A2;

    /** RGTN_USR_ID_A*/
	public final EZDFStringItem              rgtnUsrId_A;


	/**
	 * NSAL0870F00FMsg is constructor.
	 * The initialization when the instance of NSAL0870F00FMsg is generated.
	 */
	public NSAL0870F00FMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0870F00FMsg is constructor.
	 * The initialization when the instance of NSAL0870F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0870F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serNum_A = (EZDFStringItem)newItem("serNum_A");
		t_MdlNm_A = (EZDFStringItem)newItem("t_MdlNm_A");
		svcContrBrDescTxt_A = (EZDFStringItem)newItem("svcContrBrDescTxt_A");
		mtrReadSrcTpDescTxt_A = (EZDFStringItem)newItem("mtrReadSrcTpDescTxt_A");
		mtrLbDescTxt_A = (EZDFStringItem)newItem("mtrLbDescTxt_A");
		xxDtTxt_A = (EZDFStringItem)newItem("xxDtTxt_A");
		dsMsgTxt_A = (EZDFStringItem)newItem("dsMsgTxt_A");
		dsMtrProcStsDescTxt_A = (EZDFStringItem)newItem("dsMtrProcStsDescTxt_A");
		xxDtTm_A1 = (EZDFStringItem)newItem("xxDtTm_A1");
		xxDtTm_A2 = (EZDFStringItem)newItem("xxDtTm_A2");
		rgtnUsrId_A = (EZDFStringItem)newItem("rgtnUsrId_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0870F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0870F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"t_MdlNm_A", "t_MdlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcContrBrDescTxt_A", "svcContrBrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"mtrReadSrcTpDescTxt_A", "mtrReadSrcTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"mtrLbDescTxt_A", "mtrLbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTxt_A", "xxDtTxt_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"dsMsgTxt_A", "dsMsgTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"dsMtrProcStsDescTxt_A", "dsMtrProcStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTm_A1", "xxDtTm_A1", "A1", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_A2", "xxDtTm_A2", "A2", null, TYPE_HANKAKUEISU, "23", null},
	{"rgtnUsrId_A", "rgtnUsrId_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A
        {"MTR_READ_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadSrcTpDescTxt_A
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A
        {"DS_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMsgTxt_A
        {"DS_MTR_PROC_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrProcStsDescTxt_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A2
        {"RGTN_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rgtnUsrId_A
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

