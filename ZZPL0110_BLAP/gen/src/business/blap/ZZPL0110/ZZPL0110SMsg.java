//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20140623122351000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZPL0110SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZPL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZPL0110 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZPL0110SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** EIP_RPT_PROC_LOG_PK*/
	public final EZDSBigDecimalItem              eipRptProcLogPk;

    /** SUB_SYS_CD*/
	public final EZDSStringItem              subSysCd;

    /** RPT_JOB_ID*/
	public final EZDSStringItem              rptJobId;

    /** RPT_JOB_STS_TXT*/
	public final EZDSStringItem              rptJobStsTxt;

    /** XX_FROM_DT*/
	public final EZDSDateItem              xxFromDt;

    /** XX_TO_DT*/
	public final EZDSDateItem              xxToDt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.ZZPL0110.ZZPL0110_ASMsgArray              A;

    /** XX_SCR_NM*/
	public final EZDSStringItem              xxScrNm;

    /** XX_SORT_TBL_NM*/
	public final EZDSStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDSStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDSStringItem              xxSortOrdByTxt;


	/**
	 * ZZPL0110SMsg is constructor.
	 * The initialization when the instance of ZZPL0110SMsg is generated.
	 */
	public ZZPL0110SMsg() {
		this(false, -1);
	}

	/**
	 * ZZPL0110SMsg is constructor.
	 * The initialization when the instance of ZZPL0110SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZPL0110SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		eipRptProcLogPk = (EZDSBigDecimalItem)newItem("eipRptProcLogPk");
		subSysCd = (EZDSStringItem)newItem("subSysCd");
		rptJobId = (EZDSStringItem)newItem("rptJobId");
		rptJobStsTxt = (EZDSStringItem)newItem("rptJobStsTxt");
		xxFromDt = (EZDSDateItem)newItem("xxFromDt");
		xxToDt = (EZDSDateItem)newItem("xxToDt");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.ZZPL0110.ZZPL0110_ASMsgArray)newMsgArray("A");
		xxScrNm = (EZDSStringItem)newItem("xxScrNm");
		xxSortTblNm = (EZDSStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDSStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDSStringItem)newItem("xxSortOrdByTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZPL0110SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZPL0110SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"eipRptProcLogPk", "eipRptProcLogPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"subSysCd", "subSysCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"rptJobId", "rptJobId", null, null, TYPE_HANKAKUEISU, "18", null},
	{"rptJobStsTxt", "rptJobStsTxt", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxToDt", "xxToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.blap.ZZPL0110.ZZPL0110_ASMsgArray", null, null},
	
	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"EIP_RPT_PROC_LOG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptProcLogPk
        {"SUB_SYS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//subSysCd
        {"RPT_JOB_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rptJobId
        {"RPT_JOB_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rptJobStsTxt
        {"XX_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFromDt
        {"XX_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxToDt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
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

