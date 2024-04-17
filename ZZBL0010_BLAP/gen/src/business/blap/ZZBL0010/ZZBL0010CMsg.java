//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20140618165316000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZBL0010CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZBL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZBL0010 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZBL0010CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BAT_PROC_LOG_PK*/
	public final EZDCBigDecimalItem              batProcLogPk;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** BAT_PROC_SUB_SYS_CD*/
	public final EZDCStringItem              batProcSubSysCd;

    /** BAT_PROC_JBNT_ID*/
	public final EZDCStringItem              batProcJbntId;

    /** BAT_PROC_JOB_ID*/
	public final EZDCStringItem              batProcJobId;

    /** BAT_PROC_PGM_ID*/
	public final EZDCStringItem              batProcPgmId;

    /** BAT_PROC_TRM_CD*/
	public final EZDCStringItem              batProcTrmCd;

    /** BAT_PROC_TRM_CD_DP*/
	public final EZDCStringItemArray              batProcTrmCd_DP;

    /** XX_BAT_PROC_TRM_NM_DP*/
	public final EZDCStringItemArray              xxBatProcTrmNm_DP;

    /** EFF_FROM_DT*/
	public final EZDCDateItem              effFromDt;

    /** EFF_TO_DT*/
	public final EZDCDateItem              effToDt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.blap.ZZBL0010.ZZBL0010_ACMsgArray              A;


	/**
	 * ZZBL0010CMsg is constructor.
	 * The initialization when the instance of ZZBL0010CMsg is generated.
	 */
	public ZZBL0010CMsg() {
		this(false, -1);
	}

	/**
	 * ZZBL0010CMsg is constructor.
	 * The initialization when the instance of ZZBL0010CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZBL0010CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		batProcLogPk = (EZDCBigDecimalItem)newItem("batProcLogPk");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		batProcSubSysCd = (EZDCStringItem)newItem("batProcSubSysCd");
		batProcJbntId = (EZDCStringItem)newItem("batProcJbntId");
		batProcJobId = (EZDCStringItem)newItem("batProcJobId");
		batProcPgmId = (EZDCStringItem)newItem("batProcPgmId");
		batProcTrmCd = (EZDCStringItem)newItem("batProcTrmCd");
		batProcTrmCd_DP = (EZDCStringItemArray)newItemArray("batProcTrmCd_DP");
		xxBatProcTrmNm_DP = (EZDCStringItemArray)newItemArray("xxBatProcTrmNm_DP");
		effFromDt = (EZDCDateItem)newItem("effFromDt");
		effToDt = (EZDCDateItem)newItem("effToDt");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.ZZBL0010.ZZBL0010_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZBL0010CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZBL0010CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"batProcLogPk", "batProcLogPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"batProcSubSysCd", "batProcSubSysCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"batProcJbntId", "batProcJbntId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"batProcJobId", "batProcJobId", null, null, TYPE_HANKAKUEISU, "18", null},
	{"batProcPgmId", "batProcPgmId", null, null, TYPE_HANKAKUEISU, "64", null},
	{"batProcTrmCd", "batProcTrmCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"batProcTrmCd_DP", "batProcTrmCd_DP", "DP", "99", TYPE_HANKAKUEISU, "2", null},
	{"xxBatProcTrmNm_DP", "xxBatProcTrmNm_DP", "DP", "99", TYPE_HANKAKUEISU, "13", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effToDt", "effToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "40", "business.blap.ZZBL0010.ZZBL0010_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BAT_PROC_LOG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcLogPk
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"BAT_PROC_SUB_SYS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcSubSysCd
        {"BAT_PROC_JBNT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcJbntId
        {"BAT_PROC_JOB_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcJobId
        {"BAT_PROC_PGM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcPgmId
        {"BAT_PROC_TRM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcTrmCd
        {"BAT_PROC_TRM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcTrmCd_DP
        {"XX_BAT_PROC_TRM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBatProcTrmNm_DP
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effToDt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
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
