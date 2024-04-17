//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160914094205000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0030CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWWL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWWL0030 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWWL0030CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** NTFY_HDR_NM*/
	public final EZDCStringItem              ntfyHdrNm;

    /** NTFY_HDR_DESC_TXT*/
	public final EZDCStringItem              ntfyHdrDescTxt;

    /** NTFY_BIZ_AREA_TP_CD*/
	public final EZDCStringItem              ntfyBizAreaTpCd;

    /** NTFY_BIZ_AREA_TP_CD_P*/
	public final EZDCStringItemArray              ntfyBizAreaTpCd_P;

    /** NTFY_BIZ_AREA_TP_DESC_TXT_P*/
	public final EZDCStringItemArray              ntfyBizAreaTpDescTxt_P;

    /** NTFY_SUB_AREA_TP_CD*/
	public final EZDCStringItem              ntfySubAreaTpCd;

    /** NTFY_SUB_AREA_TP_CD_P*/
	public final EZDCStringItemArray              ntfySubAreaTpCd_P;

    /** NTFY_SUB_AREA_TP_DESC_TXT_P*/
	public final EZDCStringItemArray              ntfySubAreaTpDescTxt_P;

    /** NTFY_RUN_JOB_ID*/
	public final EZDCStringItem              ntfyRunJobId;

    /** EFF_FROM_DT*/
	public final EZDCDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDCDateItem              effThruDt;

    /** A*/
	public final business.blap.NWWL0030.NWWL0030_ACMsgArray              A;

    /** B*/
	public final business.blap.NWWL0030.NWWL0030_BCMsgArray              B;

    /** NTFY_ACT_TP_DESC_TXT*/
	public final EZDCStringItem              ntfyActTpDescTxt;

    /** NTFY_OTPT_TP_DESC_TXT*/
	public final EZDCStringItem              ntfyOtptTpDescTxt;

    /** NTFY_EML_RPY_TO_ADDR*/
	public final EZDCStringItem              ntfyEmlRpyToAddr;

    /** NTFY_EML_TO_ADDR*/
	public final EZDCStringItem              ntfyEmlToAddr;

    /** NTFY_EML_CC_ADDR*/
	public final EZDCStringItem              ntfyEmlCcAddr;

    /** NTFY_EML_BCC_ADDR*/
	public final EZDCStringItem              ntfyEmlBccAddr;

    /** NTFY_DIST_LIST_NM_LIST_TXT*/
	public final EZDCStringItem              ntfyDistListNmListTxt;

    /** NTFY_EML_SUBJ_TXT*/
	public final EZDCStringItem              ntfyEmlSubjTxt;

    /** XX_NTFY_EML_BODY_TXT*/
	public final EZDCStringItem              xxNtfyEmlBodyTxt;

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

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDCBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDCBigDecimalItem              xxPageShowTotNum;

    /** XX_SEL_NUM*/
	public final EZDCStringItem              xxSelNum;

    /** R*/
	public final business.blap.NWWL0030.NWWL0030_RCMsgArray              R;


	/**
	 * NWWL0030CMsg is constructor.
	 * The initialization when the instance of NWWL0030CMsg is generated.
	 */
	public NWWL0030CMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0030CMsg is constructor.
	 * The initialization when the instance of NWWL0030CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0030CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ntfyHdrNm = (EZDCStringItem)newItem("ntfyHdrNm");
		ntfyHdrDescTxt = (EZDCStringItem)newItem("ntfyHdrDescTxt");
		ntfyBizAreaTpCd = (EZDCStringItem)newItem("ntfyBizAreaTpCd");
		ntfyBizAreaTpCd_P = (EZDCStringItemArray)newItemArray("ntfyBizAreaTpCd_P");
		ntfyBizAreaTpDescTxt_P = (EZDCStringItemArray)newItemArray("ntfyBizAreaTpDescTxt_P");
		ntfySubAreaTpCd = (EZDCStringItem)newItem("ntfySubAreaTpCd");
		ntfySubAreaTpCd_P = (EZDCStringItemArray)newItemArray("ntfySubAreaTpCd_P");
		ntfySubAreaTpDescTxt_P = (EZDCStringItemArray)newItemArray("ntfySubAreaTpDescTxt_P");
		ntfyRunJobId = (EZDCStringItem)newItem("ntfyRunJobId");
		effFromDt = (EZDCDateItem)newItem("effFromDt");
		effThruDt = (EZDCDateItem)newItem("effThruDt");
		A = (business.blap.NWWL0030.NWWL0030_ACMsgArray)newMsgArray("A");
		B = (business.blap.NWWL0030.NWWL0030_BCMsgArray)newMsgArray("B");
		ntfyActTpDescTxt = (EZDCStringItem)newItem("ntfyActTpDescTxt");
		ntfyOtptTpDescTxt = (EZDCStringItem)newItem("ntfyOtptTpDescTxt");
		ntfyEmlRpyToAddr = (EZDCStringItem)newItem("ntfyEmlRpyToAddr");
		ntfyEmlToAddr = (EZDCStringItem)newItem("ntfyEmlToAddr");
		ntfyEmlCcAddr = (EZDCStringItem)newItem("ntfyEmlCcAddr");
		ntfyEmlBccAddr = (EZDCStringItem)newItem("ntfyEmlBccAddr");
		ntfyDistListNmListTxt = (EZDCStringItem)newItem("ntfyDistListNmListTxt");
		ntfyEmlSubjTxt = (EZDCStringItem)newItem("ntfyEmlSubjTxt");
		xxNtfyEmlBodyTxt = (EZDCStringItem)newItem("xxNtfyEmlBodyTxt");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSelNum = (EZDCStringItem)newItem("xxSelNum");
		R = (business.blap.NWWL0030.NWWL0030_RCMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0030CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0030CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ntfyHdrNm", "ntfyHdrNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyHdrDescTxt", "ntfyHdrDescTxt", null, null, TYPE_HANKAKUEISU, "240", null},
	{"ntfyBizAreaTpCd", "ntfyBizAreaTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"ntfyBizAreaTpCd_P", "ntfyBizAreaTpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfyBizAreaTpDescTxt_P", "ntfyBizAreaTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"ntfySubAreaTpCd", "ntfySubAreaTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpCd_P", "ntfySubAreaTpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpDescTxt_P", "ntfySubAreaTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"ntfyRunJobId", "ntfyRunJobId", null, null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"A", "A", null, "20", "business.blap.NWWL0030.NWWL0030_ACMsgArray", null, null},
	
	{"B", "B", null, "100", "business.blap.NWWL0030.NWWL0030_BCMsgArray", null, null},
	
	{"ntfyActTpDescTxt", "ntfyActTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"ntfyOtptTpDescTxt", "ntfyOtptTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"ntfyEmlRpyToAddr", "ntfyEmlRpyToAddr", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyEmlToAddr", "ntfyEmlToAddr", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyEmlCcAddr", "ntfyEmlCcAddr", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyEmlBccAddr", "ntfyEmlBccAddr", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyDistListNmListTxt", "ntfyDistListNmListTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyEmlSubjTxt", "ntfyEmlSubjTxt", null, null, TYPE_HANKAKUEISU, "200", null},
	{"xxNtfyEmlBodyTxt", "xxNtfyEmlBodyTxt", null, null, TYPE_HANKAKUEISU, "200000", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSelNum", "xxSelNum", null, null, TYPE_HANKAKUEISU, "5", null},
	{"R", "R", null, "99", "business.blap.NWWL0030.NWWL0030_RCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"NTFY_HDR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrNm
        {"NTFY_HDR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrDescTxt
        {"NTFY_BIZ_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpCd
        {"NTFY_BIZ_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpCd_P
        {"NTFY_BIZ_AREA_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpDescTxt_P
        {"NTFY_SUB_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpCd
        {"NTFY_SUB_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpCd_P
        {"NTFY_SUB_AREA_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpDescTxt_P
        {"NTFY_RUN_JOB_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunJobId
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
		null,	//A
		null,	//B
        {"NTFY_ACT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActTpDescTxt
        {"NTFY_OTPT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyOtptTpDescTxt
        {"NTFY_EML_RPY_TO_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlRpyToAddr
        {"NTFY_EML_TO_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlToAddr
        {"NTFY_EML_CC_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlCcAddr
        {"NTFY_EML_BCC_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlBccAddr
        {"NTFY_DIST_LIST_NM_LIST_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyDistListNmListTxt
        {"NTFY_EML_SUBJ_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlSubjTxt
        {"XX_NTFY_EML_BODY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNtfyEmlBodyTxt
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelNum
		null,	//R
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
