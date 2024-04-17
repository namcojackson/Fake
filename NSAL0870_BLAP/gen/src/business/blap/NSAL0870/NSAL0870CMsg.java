//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530120947000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0870CMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0870CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MTR_READ_SRC_TP_CD_1V*/
	public final EZDCStringItem              mtrReadSrcTpCd_1V;

    /** MTR_READ_SRC_TP_CD_1C*/
	public final EZDCStringItemArray              mtrReadSrcTpCd_1C;

    /** MTR_READ_SRC_TP_DESC_TXT_1D*/
	public final EZDCStringItemArray              mtrReadSrcTpDescTxt_1D;

    /** SER_NUM_01*/
	public final EZDCStringItem              serNum_01;

    /** DS_MTR_PROC_STS_CD_1V*/
	public final EZDCStringItem              dsMtrProcStsCd_1V;

    /** DS_MTR_PROC_STS_CD_1C*/
	public final EZDCStringItemArray              dsMtrProcStsCd_1C;

    /** DS_MTR_PROC_STS_DESC_TXT_1D*/
	public final EZDCStringItemArray              dsMtrProcStsDescTxt_1D;

    /** MTR_READ_DT_FR*/
	public final EZDCDateItem              mtrReadDt_FR;

    /** MTR_READ_DT_TO*/
	public final EZDCDateItem              mtrReadDt_TO;

    /** MTR_READ_SRC_TP_CD_H*/
	public final EZDCStringItem              mtrReadSrcTpCd_H;

    /** SER_NUM_H*/
	public final EZDCStringItem              serNum_H;

    /** DS_MTR_PROC_STS_CD_H*/
	public final EZDCStringItem              dsMtrProcStsCd_H;

    /** MTR_READ_DT_FH*/
	public final EZDCDateItem              mtrReadDt_FH;

    /** MTR_READ_DT_TH*/
	public final EZDCDateItem              mtrReadDt_TH;

    /** A*/
	public final business.blap.NSAL0870.NSAL0870_ACMsgArray              A;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** SVC_MACH_MSTR_PK*/
	public final EZDCBigDecimalItem              svcMachMstrPk;

    /** SVC_PHYS_MTR_READ_GRP_SQ*/
	public final EZDCBigDecimalItem              svcPhysMtrReadGrpSq;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;


	/**
	 * NSAL0870CMsg is constructor.
	 * The initialization when the instance of NSAL0870CMsg is generated.
	 */
	public NSAL0870CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0870CMsg is constructor.
	 * The initialization when the instance of NSAL0870CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0870CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mtrReadSrcTpCd_1V = (EZDCStringItem)newItem("mtrReadSrcTpCd_1V");
		mtrReadSrcTpCd_1C = (EZDCStringItemArray)newItemArray("mtrReadSrcTpCd_1C");
		mtrReadSrcTpDescTxt_1D = (EZDCStringItemArray)newItemArray("mtrReadSrcTpDescTxt_1D");
		serNum_01 = (EZDCStringItem)newItem("serNum_01");
		dsMtrProcStsCd_1V = (EZDCStringItem)newItem("dsMtrProcStsCd_1V");
		dsMtrProcStsCd_1C = (EZDCStringItemArray)newItemArray("dsMtrProcStsCd_1C");
		dsMtrProcStsDescTxt_1D = (EZDCStringItemArray)newItemArray("dsMtrProcStsDescTxt_1D");
		mtrReadDt_FR = (EZDCDateItem)newItem("mtrReadDt_FR");
		mtrReadDt_TO = (EZDCDateItem)newItem("mtrReadDt_TO");
		mtrReadSrcTpCd_H = (EZDCStringItem)newItem("mtrReadSrcTpCd_H");
		serNum_H = (EZDCStringItem)newItem("serNum_H");
		dsMtrProcStsCd_H = (EZDCStringItem)newItem("dsMtrProcStsCd_H");
		mtrReadDt_FH = (EZDCDateItem)newItem("mtrReadDt_FH");
		mtrReadDt_TH = (EZDCDateItem)newItem("mtrReadDt_TH");
		A = (business.blap.NSAL0870.NSAL0870_ACMsgArray)newMsgArray("A");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		svcMachMstrPk = (EZDCBigDecimalItem)newItem("svcMachMstrPk");
		svcPhysMtrReadGrpSq = (EZDCBigDecimalItem)newItem("svcPhysMtrReadGrpSq");
		slsDt = (EZDCDateItem)newItem("slsDt");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0870CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0870CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mtrReadSrcTpCd_1V", "mtrReadSrcTpCd_1V", "1V", null, TYPE_HANKAKUEISU, "5", null},
	{"mtrReadSrcTpCd_1C", "mtrReadSrcTpCd_1C", "1C", "99", TYPE_HANKAKUEISU, "5", null},
	{"mtrReadSrcTpDescTxt_1D", "mtrReadSrcTpDescTxt_1D", "1D", "99", TYPE_HANKAKUEISU, "60", null},
	{"serNum_01", "serNum_01", "01", null, TYPE_HANKAKUEISU, "30", null},
	{"dsMtrProcStsCd_1V", "dsMtrProcStsCd_1V", "1V", null, TYPE_HANKAKUEISU, "2", null},
	{"dsMtrProcStsCd_1C", "dsMtrProcStsCd_1C", "1C", "99", TYPE_HANKAKUEISU, "2", null},
	{"dsMtrProcStsDescTxt_1D", "dsMtrProcStsDescTxt_1D", "1D", "99", TYPE_HANKAKUEISU, "50", null},
	{"mtrReadDt_FR", "mtrReadDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"mtrReadDt_TO", "mtrReadDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"mtrReadSrcTpCd_H", "mtrReadSrcTpCd_H", "H", null, TYPE_HANKAKUEISU, "5", null},
	{"serNum_H", "serNum_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"dsMtrProcStsCd_H", "dsMtrProcStsCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrReadDt_FH", "mtrReadDt_FH", "FH", null, TYPE_NENTSUKIHI, "8", null},
	{"mtrReadDt_TH", "mtrReadDt_TH", "TH", null, TYPE_NENTSUKIHI, "8", null},
	{"A", "A", null, "40", "business.blap.NSAL0870.NSAL0870_ACMsgArray", null, null},
	
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcPhysMtrReadGrpSq", "svcPhysMtrReadGrpSq", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MTR_READ_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadSrcTpCd_1V
        {"MTR_READ_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadSrcTpCd_1C
        {"MTR_READ_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadSrcTpDescTxt_1D
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_01
        {"DS_MTR_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrProcStsCd_1V
        {"DS_MTR_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrProcStsCd_1C
        {"DS_MTR_PROC_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrProcStsDescTxt_1D
        {"MTR_READ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadDt_FR
        {"MTR_READ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadDt_TO
        {"MTR_READ_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadSrcTpCd_H
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_H
        {"DS_MTR_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrProcStsCd_H
        {"MTR_READ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadDt_FH
        {"MTR_READ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadDt_TH
		null,	//A
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SVC_PHYS_MTR_READ_GRP_SQ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrReadGrpSq
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
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

