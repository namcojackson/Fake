//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170602151309000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0640CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0640;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0640 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0640CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** XX_ROW_NUM*/
	public final EZDCBigDecimalItem              xxRowNum;

    /** XX_SET_FLG*/
	public final EZDCStringItem              xxSetFlg;

    /** SVC_LINE_BIZ_CD_H*/
	public final EZDCStringItem              svcLineBizCd_H;

    /** SVC_CONTR_BR_CD_H*/
	public final EZDCStringItem              svcContrBrCd_H;

    /** SVC_CONTR_BR_DESC_TXT_H*/
	public final EZDCStringItem              svcContrBrDescTxt_H;

    /** XX_DPLY_BY_CD_NM_CNCT_TXT_H*/
	public final EZDCStringItem              xxDplyByCdNmCnctTxt_H;

    /** PSN_CD_H*/
	public final EZDCStringItem              psnCd_H;

    /** XX_PSN_NM_H*/
	public final EZDCStringItem              xxPsnNm_H;

    /** TOC_CD_H*/
	public final EZDCStringItem              tocCd_H;

    /** TOC_NM_H*/
	public final EZDCStringItem              tocNm_H;

    /** SVC_MEMO_RSN_CD_L*/
	public final EZDCStringItemArray              svcMemoRsnCd_L;

    /** SVC_MEMO_RSN_NM_L*/
	public final EZDCStringItemArray              svcMemoRsnNm_L;

    /** SVC_MEMO_RSN_CD_H*/
	public final EZDCStringItem              svcMemoRsnCd_H;

    /** SVC_CMNT_TXT_H*/
	public final EZDCStringItem              svcCmntTxt_H;

    /** XX_CHK_BOX_AL*/
	public final EZDCStringItem              xxChkBox_AL;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** P*/
	public final business.blap.NSAL0640.NSAL0640_PCMsgArray              P;

    /** A*/
	public final business.blap.NSAL0640.NSAL0640_ACMsgArray              A;

    /** XX_RSLT_FLG*/
	public final EZDCStringItem              xxRsltFlg;

    /** SVC_RG_NM_P*/
	public final EZDCStringItem              svcRgNm_P;

    /** SVC_LINE_BIZ_DESC_TXT_P*/
	public final EZDCStringItem              svcLineBizDescTxt_P;

    /** SVC_CONTR_BR_CD_P*/
	public final EZDCStringItem              svcContrBrCd_P;

    /** SVC_CONTR_BR_DESC_TXT_P*/
	public final EZDCStringItem              svcContrBrDescTxt_P;

    /** XX_GENL_FLD_AREA_TXT_P*/
	public final EZDCStringItem              xxGenlFldAreaTxt_P;

    /** ORG_FUNC_ROLE_TP_NM_P*/
	public final EZDCStringItem              orgFuncRoleTpNm_P;

    /** SVC_RG_PK_P*/
	public final EZDCBigDecimalItem              svcRgPk_P;

    /** PSN_CD_P*/
	public final EZDCStringItem              psnCd_P;

    /** XX_PSN_NM_P*/
	public final EZDCStringItem              xxPsnNm_P;

    /** SVC_LINE_BIZ_CD_P*/
	public final EZDCStringItem              svcLineBizCd_P;


	/**
	 * NSAL0640CMsg is constructor.
	 * The initialization when the instance of NSAL0640CMsg is generated.
	 */
	public NSAL0640CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0640CMsg is constructor.
	 * The initialization when the instance of NSAL0640CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0640CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		xxRowNum = (EZDCBigDecimalItem)newItem("xxRowNum");
		xxSetFlg = (EZDCStringItem)newItem("xxSetFlg");
		svcLineBizCd_H = (EZDCStringItem)newItem("svcLineBizCd_H");
		svcContrBrCd_H = (EZDCStringItem)newItem("svcContrBrCd_H");
		svcContrBrDescTxt_H = (EZDCStringItem)newItem("svcContrBrDescTxt_H");
		xxDplyByCdNmCnctTxt_H = (EZDCStringItem)newItem("xxDplyByCdNmCnctTxt_H");
		psnCd_H = (EZDCStringItem)newItem("psnCd_H");
		xxPsnNm_H = (EZDCStringItem)newItem("xxPsnNm_H");
		tocCd_H = (EZDCStringItem)newItem("tocCd_H");
		tocNm_H = (EZDCStringItem)newItem("tocNm_H");
		svcMemoRsnCd_L = (EZDCStringItemArray)newItemArray("svcMemoRsnCd_L");
		svcMemoRsnNm_L = (EZDCStringItemArray)newItemArray("svcMemoRsnNm_L");
		svcMemoRsnCd_H = (EZDCStringItem)newItem("svcMemoRsnCd_H");
		svcCmntTxt_H = (EZDCStringItem)newItem("svcCmntTxt_H");
		xxChkBox_AL = (EZDCStringItem)newItem("xxChkBox_AL");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		P = (business.blap.NSAL0640.NSAL0640_PCMsgArray)newMsgArray("P");
		A = (business.blap.NSAL0640.NSAL0640_ACMsgArray)newMsgArray("A");
		xxRsltFlg = (EZDCStringItem)newItem("xxRsltFlg");
		svcRgNm_P = (EZDCStringItem)newItem("svcRgNm_P");
		svcLineBizDescTxt_P = (EZDCStringItem)newItem("svcLineBizDescTxt_P");
		svcContrBrCd_P = (EZDCStringItem)newItem("svcContrBrCd_P");
		svcContrBrDescTxt_P = (EZDCStringItem)newItem("svcContrBrDescTxt_P");
		xxGenlFldAreaTxt_P = (EZDCStringItem)newItem("xxGenlFldAreaTxt_P");
		orgFuncRoleTpNm_P = (EZDCStringItem)newItem("orgFuncRoleTpNm_P");
		svcRgPk_P = (EZDCBigDecimalItem)newItem("svcRgPk_P");
		psnCd_P = (EZDCStringItem)newItem("psnCd_P");
		xxPsnNm_P = (EZDCStringItem)newItem("xxPsnNm_P");
		svcLineBizCd_P = (EZDCStringItem)newItem("svcLineBizCd_P");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0640CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0640CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSetFlg", "xxSetFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"svcLineBizCd_H", "svcLineBizCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"svcContrBrCd_H", "svcContrBrCd_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_H", "svcContrBrDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDplyByCdNmCnctTxt_H", "xxDplyByCdNmCnctTxt_H", "H", null, TYPE_HANKAKUEISU, "70", null},
	{"psnCd_H", "psnCd_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPsnNm_H", "xxPsnNm_H", "H", null, TYPE_HANKAKUEISU, "62", null},
	{"tocCd_H", "tocCd_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_H", "tocNm_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"svcMemoRsnCd_L", "svcMemoRsnCd_L", "L", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnNm_L", "svcMemoRsnNm_L", "L", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcMemoRsnCd_H", "svcMemoRsnCd_H", "H", null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt_H", "svcCmntTxt_H", "H", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxChkBox_AL", "xxChkBox_AL", "AL", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"P", "P", null, "1000", "business.blap.NSAL0640.NSAL0640_PCMsgArray", null, null},
	
	{"A", "A", null, "200", "business.blap.NSAL0640.NSAL0640_ACMsgArray", null, null},
	
	{"xxRsltFlg", "xxRsltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"svcRgNm_P", "svcRgNm_P", "P", null, TYPE_HANKAKUEISU, "30", null},
	{"svcLineBizDescTxt_P", "svcLineBizDescTxt_P", "P", null, TYPE_HANKAKUEISU, "50", null},
	{"svcContrBrCd_P", "svcContrBrCd_P", "P", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_P", "svcContrBrDescTxt_P", "P", null, TYPE_HANKAKUEISU, "50", null},
	{"xxGenlFldAreaTxt_P", "xxGenlFldAreaTxt_P", "P", null, TYPE_HANKAKUEISU, "1000", null},
	{"orgFuncRoleTpNm_P", "orgFuncRoleTpNm_P", "P", null, TYPE_HANKAKUEISU, "50", null},
	{"svcRgPk_P", "svcRgPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"psnCd_P", "psnCd_P", "P", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPsnNm_P", "xxPsnNm_P", "P", null, TYPE_HANKAKUEISU, "62", null},
	{"svcLineBizCd_P", "svcLineBizCd_P", "P", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
        {"XX_SET_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSetFlg
        {"SVC_LINE_BIZ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_H
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_H
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_H
        {"XX_DPLY_BY_CD_NM_CNCT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCdNmCnctTxt_H
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_H
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_H
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_L
        {"SVC_MEMO_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnNm_L
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_H
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AL
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//P
		null,	//A
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg
        {"SVC_RG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgNm_P
        {"SVC_LINE_BIZ_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizDescTxt_P
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_P
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_P
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_P
        {"ORG_FUNC_ROLE_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpNm_P
        {"SVC_RG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgPk_P
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_P
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_P
        {"SVC_LINE_BIZ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_P
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

