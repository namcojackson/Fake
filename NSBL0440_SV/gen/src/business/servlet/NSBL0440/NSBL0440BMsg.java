//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180601133142000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0440BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0440;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0440 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0440BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;

    /** SVC_MOD_PK*/
	public final EZDBBigDecimalItem              svcModPk;

    /** SVC_MOD_PLN_ID*/
	public final EZDBStringItem              svcModPlnId;

    /** SVC_MOD_NM*/
	public final EZDBStringItem              svcModNm;

    /** MDSE_CD_F*/
	public final EZDBStringItem              mdseCd_F;

    /** XX_CHK_BOX_OR*/
	public final EZDBStringItem              xxChkBox_OR;

    /** SER_NUM_F*/
	public final EZDBStringItem              serNum_F;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** SVC_MACH_MSTR_STS_CD*/
	public final EZDBStringItem              svcMachMstrStsCd;

    /** SVC_MACH_MSTR_STS_CD_01*/
	public final EZDBStringItemArray              svcMachMstrStsCd_01;

    /** SVC_MACH_MSTR_STS_DESC_TXT_01*/
	public final EZDBStringItemArray              svcMachMstrStsDescTxt_01;

    /** SVC_MOD_PROC_STS_CD*/
	public final EZDBStringItem              svcModProcStsCd;

    /** SVC_MOD_PROC_STS_CD_01*/
	public final EZDBStringItemArray              svcModProcStsCd_01;

    /** SVC_MOD_PROC_STS_DESC_TXT_01*/
	public final EZDBStringItemArray              svcModProcStsDescTxt_01;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** SVC_MOD_OPT_CD_AO*/
	public final EZDBStringItemArray              svcModOptCd_AO;

    /** SVC_MOD_OPT_DESC_TXT_AO*/
	public final EZDBStringItemArray              svcModOptDescTxt_AO;

    /** A*/
	public final business.servlet.NSBL0440.NSBL0440_ABMsgArray              A;


	/**
	 * NSBL0440BMsg is constructor.
	 * The initialization when the instance of NSBL0440BMsg is generated.
	 */
	public NSBL0440BMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0440BMsg is constructor.
	 * The initialization when the instance of NSBL0440BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0440BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		slsDt = (EZDBDateItem)newItem("slsDt");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
		svcModPk = (EZDBBigDecimalItem)newItem("svcModPk");
		svcModPlnId = (EZDBStringItem)newItem("svcModPlnId");
		svcModNm = (EZDBStringItem)newItem("svcModNm");
		mdseCd_F = (EZDBStringItem)newItem("mdseCd_F");
		xxChkBox_OR = (EZDBStringItem)newItem("xxChkBox_OR");
		serNum_F = (EZDBStringItem)newItem("serNum_F");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		svcMachMstrStsCd = (EZDBStringItem)newItem("svcMachMstrStsCd");
		svcMachMstrStsCd_01 = (EZDBStringItemArray)newItemArray("svcMachMstrStsCd_01");
		svcMachMstrStsDescTxt_01 = (EZDBStringItemArray)newItemArray("svcMachMstrStsDescTxt_01");
		svcModProcStsCd = (EZDBStringItem)newItem("svcModProcStsCd");
		svcModProcStsCd_01 = (EZDBStringItemArray)newItemArray("svcModProcStsCd_01");
		svcModProcStsDescTxt_01 = (EZDBStringItemArray)newItemArray("svcModProcStsDescTxt_01");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		svcModOptCd_AO = (EZDBStringItemArray)newItemArray("svcModOptCd_AO");
		svcModOptDescTxt_AO = (EZDBStringItemArray)newItemArray("svcModOptDescTxt_AO");
		A = (business.servlet.NSBL0440.NSBL0440_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0440BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0440BMsgArray();
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
	{"A", "A", null, "20", "business.servlet.NSBL0440.NSBL0440_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//slsDt
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"SVC_MOD_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModPk
        {"SVC_MOD_PLN_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModPlnId
        {"SVC_MOD_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModNm
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_F
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_OR
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_F
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"SVC_MACH_MSTR_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsCd
        {"SVC_MACH_MSTR_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsCd_01
        {"SVC_MACH_MSTR_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsDescTxt_01
        {"SVC_MOD_PROC_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModProcStsCd
        {"SVC_MOD_PROC_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModProcStsCd_01
        {"SVC_MOD_PROC_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModProcStsDescTxt_01
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"SVC_MOD_OPT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModOptCd_AO
        {"SVC_MOD_OPT_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModOptDescTxt_AO
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
