//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20140623122320000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZPL0110BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZPL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZPL0110 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZPL0110BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** EIP_RPT_PROC_LOG_PK*/
	public final EZDBBigDecimalItem              eipRptProcLogPk;

    /** SUB_SYS_CD*/
	public final EZDBStringItem              subSysCd;

    /** RPT_JOB_ID*/
	public final EZDBStringItem              rptJobId;

    /** RPT_JOB_STS_TXT*/
	public final EZDBStringItem              rptJobStsTxt;

    /** XX_FROM_DT*/
	public final EZDBDateItem              xxFromDt;

    /** XX_TO_DT*/
	public final EZDBDateItem              xxToDt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.servlet.ZZPL0110.ZZPL0110_ABMsgArray              A;

    /** XX_SCR_NM*/
	public final EZDBStringItem              xxScrNm;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;


	/**
	 * ZZPL0110BMsg is constructor.
	 * The initialization when the instance of ZZPL0110BMsg is generated.
	 */
	public ZZPL0110BMsg() {
		this(false, -1);
	}

	/**
	 * ZZPL0110BMsg is constructor.
	 * The initialization when the instance of ZZPL0110BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZPL0110BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		eipRptProcLogPk = (EZDBBigDecimalItem)newItem("eipRptProcLogPk");
		subSysCd = (EZDBStringItem)newItem("subSysCd");
		rptJobId = (EZDBStringItem)newItem("rptJobId");
		rptJobStsTxt = (EZDBStringItem)newItem("rptJobStsTxt");
		xxFromDt = (EZDBDateItem)newItem("xxFromDt");
		xxToDt = (EZDBDateItem)newItem("xxToDt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.servlet.ZZPL0110.ZZPL0110_ABMsgArray)newMsgArray("A");
		xxScrNm = (EZDBStringItem)newItem("xxScrNm");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZPL0110BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZPL0110BMsgArray();
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
	{"A", "A", null, "40", "business.servlet.ZZPL0110.ZZPL0110_ABMsgArray", null, null},
	
	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"EIP_RPT_PROC_LOG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptProcLogPk
        {"SUB_SYS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//subSysCd
        {"RPT_JOB_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rptJobId
        {"RPT_JOB_STS_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rptJobStsTxt
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt
        {"XX_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxToDt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_SCR_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"XX_SORT_TBL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
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

