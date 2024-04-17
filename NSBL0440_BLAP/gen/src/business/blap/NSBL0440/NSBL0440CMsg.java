//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180601133121000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0440CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0440;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0440 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0440CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;

    /** SVC_MOD_PK*/
	public final EZDCBigDecimalItem              svcModPk;

    /** SVC_MOD_PLN_ID*/
	public final EZDCStringItem              svcModPlnId;

    /** SVC_MOD_NM*/
	public final EZDCStringItem              svcModNm;

    /** MDSE_CD_F*/
	public final EZDCStringItem              mdseCd_F;

    /** XX_CHK_BOX_OR*/
	public final EZDCStringItem              xxChkBox_OR;

    /** SER_NUM_F*/
	public final EZDCStringItem              serNum_F;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** SVC_MACH_MSTR_STS_CD*/
	public final EZDCStringItem              svcMachMstrStsCd;

    /** SVC_MACH_MSTR_STS_CD_01*/
	public final EZDCStringItemArray              svcMachMstrStsCd_01;

    /** SVC_MACH_MSTR_STS_DESC_TXT_01*/
	public final EZDCStringItemArray              svcMachMstrStsDescTxt_01;

    /** SVC_MOD_PROC_STS_CD*/
	public final EZDCStringItem              svcModProcStsCd;

    /** SVC_MOD_PROC_STS_CD_01*/
	public final EZDCStringItemArray              svcModProcStsCd_01;

    /** SVC_MOD_PROC_STS_DESC_TXT_01*/
	public final EZDCStringItemArray              svcModProcStsDescTxt_01;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDCBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDCBigDecimalItem              xxPageShowTotNum;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** SVC_MOD_OPT_CD_AO*/
	public final EZDCStringItemArray              svcModOptCd_AO;

    /** SVC_MOD_OPT_DESC_TXT_AO*/
	public final EZDCStringItemArray              svcModOptDescTxt_AO;

    /** A*/
	public final business.blap.NSBL0440.NSBL0440_ACMsgArray              A;


	/**
	 * NSBL0440CMsg is constructor.
	 * The initialization when the instance of NSBL0440CMsg is generated.
	 */
	public NSBL0440CMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0440CMsg is constructor.
	 * The initialization when the instance of NSBL0440CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0440CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
		svcModPk = (EZDCBigDecimalItem)newItem("svcModPk");
		svcModPlnId = (EZDCStringItem)newItem("svcModPlnId");
		svcModNm = (EZDCStringItem)newItem("svcModNm");
		mdseCd_F = (EZDCStringItem)newItem("mdseCd_F");
		xxChkBox_OR = (EZDCStringItem)newItem("xxChkBox_OR");
		serNum_F = (EZDCStringItem)newItem("serNum_F");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		svcMachMstrStsCd = (EZDCStringItem)newItem("svcMachMstrStsCd");
		svcMachMstrStsCd_01 = (EZDCStringItemArray)newItemArray("svcMachMstrStsCd_01");
		svcMachMstrStsDescTxt_01 = (EZDCStringItemArray)newItemArray("svcMachMstrStsDescTxt_01");
		svcModProcStsCd = (EZDCStringItem)newItem("svcModProcStsCd");
		svcModProcStsCd_01 = (EZDCStringItemArray)newItemArray("svcModProcStsCd_01");
		svcModProcStsDescTxt_01 = (EZDCStringItemArray)newItemArray("svcModProcStsDescTxt_01");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		svcModOptCd_AO = (EZDCStringItemArray)newItemArray("svcModOptCd_AO");
		svcModOptDescTxt_AO = (EZDCStringItemArray)newItemArray("svcModOptDescTxt_AO");
		A = (business.blap.NSBL0440.NSBL0440_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0440CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0440CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"svcModPk", "svcModPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcModPlnId", "svcModPlnId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"svcModNm", "svcModNm", null, null, TYPE_HANKAKUEISU, "40", null},
	{"mdseCd_F", "mdseCd_F", "F", null, TYPE_HANKAKUEISU, "16", null},
	{"xxChkBox_OR", "xxChkBox_OR", "OR", null, TYPE_HANKAKUEIJI, "1", null},
	{"serNum_F", "serNum_F", "F", null, TYPE_HANKAKUEISU, "30", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"svcMachMstrStsCd", "svcMachMstrStsCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"svcMachMstrStsCd_01", "svcMachMstrStsCd_01", "01", "99", TYPE_HANKAKUEISU, "5", null},
	{"svcMachMstrStsDescTxt_01", "svcMachMstrStsDescTxt_01", "01", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcModProcStsCd", "svcModProcStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcModProcStsCd_01", "svcModProcStsCd_01", "01", "99", TYPE_HANKAKUEISU, "2", null},
	{"svcModProcStsDescTxt_01", "svcModProcStsDescTxt_01", "01", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcModOptCd_AO", "svcModOptCd_AO", "AO", "99", TYPE_HANKAKUEISU, "2", null},
	{"svcModOptDescTxt_AO", "svcModOptDescTxt_AO", "AO", "99", TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "20", "business.blap.NSBL0440.NSBL0440_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"SVC_MOD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModPk
        {"SVC_MOD_PLN_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModPlnId
        {"SVC_MOD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModNm
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_F
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_OR
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_F
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"SVC_MACH_MSTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsCd
        {"SVC_MACH_MSTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsCd_01
        {"SVC_MACH_MSTR_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsDescTxt_01
        {"SVC_MOD_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModProcStsCd
        {"SVC_MOD_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModProcStsCd_01
        {"SVC_MOD_PROC_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModProcStsDescTxt_01
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"SVC_MOD_OPT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModOptCd_AO
        {"SVC_MOD_OPT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModOptDescTxt_AO
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

