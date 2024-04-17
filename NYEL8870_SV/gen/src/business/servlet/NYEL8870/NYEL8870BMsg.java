//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160630124535000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8870BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NYEL8870;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8870 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8870BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WF_PROC_NM_V*/
	public final EZDBStringItem              wfProcNm_V;

    /** WF_PROC_NM_L*/
	public final EZDBStringItemArray              wfProcNm_L;

    /** WF_DESC_TXT_LD*/
	public final EZDBStringItemArray              wfDescTxt_LD;

    /** WF_NTFY_TP_CD_V*/
	public final EZDBStringItem              wfNtfyTpCd_V;

    /** WF_NTFY_TP_CD_L*/
	public final EZDBStringItemArray              wfNtfyTpCd_L;

    /** WF_NTFY_TP_DESC_TXT_LD*/
	public final EZDBStringItemArray              wfNtfyTpDescTxt_LD;

    /** WF_NTFY_EML_TP_CD_V*/
	public final EZDBStringItem              wfNtfyEmlTpCd_V;

    /** WF_NTFY_EML_TP_CD_L*/
	public final EZDBStringItemArray              wfNtfyEmlTpCd_L;

    /** WF_NTFY_EML_TP_DESC_TXT_LD*/
	public final EZDBStringItemArray              wfNtfyEmlTpDescTxt_LD;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.servlet.NYEL8870.NYEL8870_ABMsgArray              A;


	/**
	 * NYEL8870BMsg is constructor.
	 * The initialization when the instance of NYEL8870BMsg is generated.
	 */
	public NYEL8870BMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8870BMsg is constructor.
	 * The initialization when the instance of NYEL8870BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8870BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wfProcNm_V = (EZDBStringItem)newItem("wfProcNm_V");
		wfProcNm_L = (EZDBStringItemArray)newItemArray("wfProcNm_L");
		wfDescTxt_LD = (EZDBStringItemArray)newItemArray("wfDescTxt_LD");
		wfNtfyTpCd_V = (EZDBStringItem)newItem("wfNtfyTpCd_V");
		wfNtfyTpCd_L = (EZDBStringItemArray)newItemArray("wfNtfyTpCd_L");
		wfNtfyTpDescTxt_LD = (EZDBStringItemArray)newItemArray("wfNtfyTpDescTxt_LD");
		wfNtfyEmlTpCd_V = (EZDBStringItem)newItem("wfNtfyEmlTpCd_V");
		wfNtfyEmlTpCd_L = (EZDBStringItemArray)newItemArray("wfNtfyEmlTpCd_L");
		wfNtfyEmlTpDescTxt_LD = (EZDBStringItemArray)newItemArray("wfNtfyEmlTpDescTxt_LD");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		A = (business.servlet.NYEL8870.NYEL8870_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8870BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8870BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wfProcNm_V", "wfProcNm_V", "V", null, TYPE_HANKAKUEISU, "40", null},
	{"wfProcNm_L", "wfProcNm_L", "L", "99", TYPE_HANKAKUEISU, "40", null},
	{"wfDescTxt_LD", "wfDescTxt_LD", "LD", "99", TYPE_HANKAKUEISU, "50", null},
	{"wfNtfyTpCd_V", "wfNtfyTpCd_V", "V", null, TYPE_HANKAKUEISU, "2", null},
	{"wfNtfyTpCd_L", "wfNtfyTpCd_L", "L", "20", TYPE_HANKAKUEISU, "2", null},
	{"wfNtfyTpDescTxt_LD", "wfNtfyTpDescTxt_LD", "LD", "20", TYPE_HANKAKUEISU, "50", null},
	{"wfNtfyEmlTpCd_V", "wfNtfyEmlTpCd_V", "V", null, TYPE_HANKAKUEISU, "2", null},
	{"wfNtfyEmlTpCd_L", "wfNtfyEmlTpCd_L", "L", "20", TYPE_HANKAKUEISU, "2", null},
	{"wfNtfyEmlTpDescTxt_LD", "wfNtfyEmlTpDescTxt_LD", "LD", "20", TYPE_HANKAKUEISU, "50", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "200", "business.servlet.NYEL8870.NYEL8870_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WF_PROC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfProcNm_V
        {"WF_PROC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfProcNm_L
        {"WF_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_LD
        {"WF_NTFY_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfNtfyTpCd_V
        {"WF_NTFY_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfNtfyTpCd_L
        {"WF_NTFY_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfNtfyTpDescTxt_LD
        {"WF_NTFY_EML_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfNtfyEmlTpCd_V
        {"WF_NTFY_EML_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfNtfyEmlTpCd_L
        {"WF_NTFY_EML_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfNtfyEmlTpDescTxt_LD
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
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
