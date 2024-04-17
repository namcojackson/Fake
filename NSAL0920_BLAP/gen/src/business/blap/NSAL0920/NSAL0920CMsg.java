//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180524162705000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0920CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0920;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0920 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0920CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** SVC_ORG_FUNC_ROLE_TP_CD_VC*/
	public final EZDCStringItem              svcOrgFuncRoleTpCd_VC;

    /** DS_CONTR_NUM*/
	public final EZDCStringItem              dsContrNum;

    /** DS_ACCT_NUM*/
	public final EZDCStringItem              dsAcctNum;

    /** DS_ACCT_NM*/
	public final EZDCStringItem              dsAcctNm;

    /** LOC_NUM*/
	public final EZDCStringItem              locNum;

    /** LOC_NM*/
	public final EZDCStringItem              locNm;

    /** SVC_RG_PK_H1*/
	public final EZDCBigDecimalItemArray              svcRgPk_H1;

    /** SVC_RG_DESC_TXT_H2*/
	public final EZDCStringItemArray              svcRgDescTxt_H2;

    /** SVC_RG_PK_H3*/
	public final EZDCBigDecimalItem              svcRgPk_H3;

    /** SVC_CONTR_BR_DESC_TXT_H2*/
	public final EZDCStringItem              svcContrBrDescTxt_H2;

    /** SVC_CONTR_BR_CD_H3*/
	public final EZDCStringItem              svcContrBrCd_H3;

    /** XX_ALL_PSN_NM_H2*/
	public final EZDCStringItem              xxAllPsnNm_H2;

    /** PSN_CD_H3*/
	public final EZDCStringItem              psnCd_H3;

    /** XX_FROM_DT*/
	public final EZDCDateItem              xxFromDt;

    /** XX_THRU_DT*/
	public final EZDCDateItem              xxThruDt;

    /** XX_CHK_BOX_HY*/
	public final EZDCStringItem              xxChkBox_HY;

    /** XX_CHK_BOX_HN*/
	public final EZDCStringItem              xxChkBox_HN;

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

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.blap.NSAL0920.NSAL0920_ACMsgArray              A;


	/**
	 * NSAL0920CMsg is constructor.
	 * The initialization when the instance of NSAL0920CMsg is generated.
	 */
	public NSAL0920CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0920CMsg is constructor.
	 * The initialization when the instance of NSAL0920CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0920CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		svcOrgFuncRoleTpCd_VC = (EZDCStringItem)newItem("svcOrgFuncRoleTpCd_VC");
		dsContrNum = (EZDCStringItem)newItem("dsContrNum");
		dsAcctNum = (EZDCStringItem)newItem("dsAcctNum");
		dsAcctNm = (EZDCStringItem)newItem("dsAcctNm");
		locNum = (EZDCStringItem)newItem("locNum");
		locNm = (EZDCStringItem)newItem("locNm");
		svcRgPk_H1 = (EZDCBigDecimalItemArray)newItemArray("svcRgPk_H1");
		svcRgDescTxt_H2 = (EZDCStringItemArray)newItemArray("svcRgDescTxt_H2");
		svcRgPk_H3 = (EZDCBigDecimalItem)newItem("svcRgPk_H3");
		svcContrBrDescTxt_H2 = (EZDCStringItem)newItem("svcContrBrDescTxt_H2");
		svcContrBrCd_H3 = (EZDCStringItem)newItem("svcContrBrCd_H3");
		xxAllPsnNm_H2 = (EZDCStringItem)newItem("xxAllPsnNm_H2");
		psnCd_H3 = (EZDCStringItem)newItem("psnCd_H3");
		xxFromDt = (EZDCDateItem)newItem("xxFromDt");
		xxThruDt = (EZDCDateItem)newItem("xxThruDt");
		xxChkBox_HY = (EZDCStringItem)newItem("xxChkBox_HY");
		xxChkBox_HN = (EZDCStringItem)newItem("xxChkBox_HN");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.NSAL0920.NSAL0920_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0920CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0920CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcOrgFuncRoleTpCd_VC", "svcOrgFuncRoleTpCd_VC", "VC", null, TYPE_HANKAKUEISU, "8", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"locNum", "locNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"locNm", "locNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"svcRgPk_H1", "svcRgPk_H1", "H1", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"svcRgDescTxt_H2", "svcRgDescTxt_H2", "H2", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcRgPk_H3", "svcRgPk_H3", "H3", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcContrBrDescTxt_H2", "svcContrBrDescTxt_H2", "H2", null, TYPE_HANKAKUEISU, "50", null},
	{"svcContrBrCd_H3", "svcContrBrCd_H3", "H3", null, TYPE_HANKAKUEISU, "3", null},
	{"xxAllPsnNm_H2", "xxAllPsnNm_H2", "H2", null, TYPE_HANKAKUEISU, "99", null},
	{"psnCd_H3", "psnCd_H3", "H3", null, TYPE_HANKAKUEISU, "8", null},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxThruDt", "xxThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_HY", "xxChkBox_HY", "HY", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_HN", "xxChkBox_HN", "HN", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "50", "business.blap.NSAL0920.NSAL0920_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"SVC_ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcOrgFuncRoleTpCd_VC
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm
        {"SVC_RG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgPk_H1
        {"SVC_RG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgDescTxt_H2
        {"SVC_RG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgPk_H3
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_H2
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_H3
        {"XX_ALL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllPsnNm_H2
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H3
        {"XX_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFromDt
        {"XX_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxThruDt
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_HY
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_HN
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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

