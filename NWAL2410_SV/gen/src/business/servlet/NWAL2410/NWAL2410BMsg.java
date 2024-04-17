//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170217142741000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2410BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2410;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2410 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2410BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DOC_MGT_ORG_CD*/
	public final EZDBStringItem              docMgtOrgCd;

    /** DOC_MGT_ORG_CD_P*/
	public final EZDBStringItemArray              docMgtOrgCd_P;

    /** DOC_MGT_ORG_DESC_TXT_P*/
	public final EZDBStringItemArray              docMgtOrgDescTxt_P;

    /** DOC_MGT_SCAN_BR_CD*/
	public final EZDBStringItem              docMgtScanBrCd;

    /** DOC_MGT_SCAN_BR_NM*/
	public final EZDBStringItem              docMgtScanBrNm;

    /** DOC_MGT_SCAN_RG_NM*/
	public final EZDBStringItem              docMgtScanRgNm;

    /** DOC_MGT_SCAN_ZN_NM*/
	public final EZDBStringItem              docMgtScanZnNm;

    /** DEF_ORD_PROC_PSN_CD*/
	public final EZDBStringItem              defOrdProcPsnCd;

    /** DEF_BR_ADMIN_PSN_CD*/
	public final EZDBStringItem              defBrAdminPsnCd;

    /** LEASE_CMPY_PROC_PSN_CD*/
	public final EZDBStringItem              leaseCmpyProcPsnCd;

    /** ACTV_FLG*/
	public final EZDBStringItem              actvFlg;

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

    /** XX_RQST_FLG*/
	public final EZDBStringItem              xxRqstFlg;

    /** XX_EDT_MODE_FLG*/
	public final EZDBStringItem              xxEdtModeFlg;

    /** A*/
	public final business.servlet.NWAL2410.NWAL2410_ABMsgArray              A;

    /** R*/
	public final business.servlet.NWAL2410.NWAL2410_RBMsgArray              R;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;


	/**
	 * NWAL2410BMsg is constructor.
	 * The initialization when the instance of NWAL2410BMsg is generated.
	 */
	public NWAL2410BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2410BMsg is constructor.
	 * The initialization when the instance of NWAL2410BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2410BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		docMgtOrgCd = (EZDBStringItem)newItem("docMgtOrgCd");
		docMgtOrgCd_P = (EZDBStringItemArray)newItemArray("docMgtOrgCd_P");
		docMgtOrgDescTxt_P = (EZDBStringItemArray)newItemArray("docMgtOrgDescTxt_P");
		docMgtScanBrCd = (EZDBStringItem)newItem("docMgtScanBrCd");
		docMgtScanBrNm = (EZDBStringItem)newItem("docMgtScanBrNm");
		docMgtScanRgNm = (EZDBStringItem)newItem("docMgtScanRgNm");
		docMgtScanZnNm = (EZDBStringItem)newItem("docMgtScanZnNm");
		defOrdProcPsnCd = (EZDBStringItem)newItem("defOrdProcPsnCd");
		defBrAdminPsnCd = (EZDBStringItem)newItem("defBrAdminPsnCd");
		leaseCmpyProcPsnCd = (EZDBStringItem)newItem("leaseCmpyProcPsnCd");
		actvFlg = (EZDBStringItem)newItem("actvFlg");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxRqstFlg = (EZDBStringItem)newItem("xxRqstFlg");
		xxEdtModeFlg = (EZDBStringItem)newItem("xxEdtModeFlg");
		A = (business.servlet.NWAL2410.NWAL2410_ABMsgArray)newMsgArray("A");
		R = (business.servlet.NWAL2410.NWAL2410_RBMsgArray)newMsgArray("R");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2410BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2410BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"docMgtOrgCd", "docMgtOrgCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"docMgtOrgCd_P", "docMgtOrgCd_P", "P", "99", TYPE_HANKAKUEISU, "5", null},
	{"docMgtOrgDescTxt_P", "docMgtOrgDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"docMgtScanBrCd", "docMgtScanBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"docMgtScanBrNm", "docMgtScanBrNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"docMgtScanRgNm", "docMgtScanRgNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"docMgtScanZnNm", "docMgtScanZnNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"defOrdProcPsnCd", "defOrdProcPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"defBrAdminPsnCd", "defBrAdminPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"leaseCmpyProcPsnCd", "leaseCmpyProcPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"actvFlg", "actvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRqstFlg", "xxRqstFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxEdtModeFlg", "xxEdtModeFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "40", "business.servlet.NWAL2410.NWAL2410_ABMsgArray", null, null},
	
	{"R", "R", null, "99", "business.servlet.NWAL2410.NWAL2410_RBMsgArray", null, null},
	
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DOC_MGT_ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtOrgCd
        {"DOC_MGT_ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtOrgCd_P
        {"DOC_MGT_ORG_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtOrgDescTxt_P
        {"DOC_MGT_SCAN_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtScanBrCd
        {"DOC_MGT_SCAN_BR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtScanBrNm
        {"DOC_MGT_SCAN_RG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtScanRgNm
        {"DOC_MGT_SCAN_ZN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtScanZnNm
        {"DEF_ORD_PROC_PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defOrdProcPsnCd
        {"DEF_BR_ADMIN_PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defBrAdminPsnCd
        {"LEASE_CMPY_PROC_PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyProcPsnCd
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_RQST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstFlg
        {"XX_EDT_MODE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtModeFlg
		null,	//A
		null,	//R
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
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

