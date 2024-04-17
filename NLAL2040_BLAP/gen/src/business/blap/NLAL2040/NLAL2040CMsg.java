//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530120339000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2040CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL2040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL2040 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2040CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** XX_RSLT_FLG*/
	public final EZDCStringItem              xxRsltFlg;

    /** T_MDL_NM*/
	public final EZDCStringItem              t_MdlNm;

    /** XX_CHK_BOX*/
	public final EZDCStringItem              xxChkBox;

    /** SVC_SEG_CD_PS*/
	public final EZDCStringItem              svcSegCd_PS;

    /** SVC_SEG_CD_PC*/
	public final EZDCStringItemArray              svcSegCd_PC;

    /** SVC_SEG_DESC_TXT_PD*/
	public final EZDCStringItemArray              svcSegDescTxt_PD;

    /** COA_MDSE_TP_CD_PS*/
	public final EZDCStringItem              coaMdseTpCd_PS;

    /** COA_MDSE_TP_CD_PC*/
	public final EZDCStringItemArray              coaMdseTpCd_PC;

    /** COA_MDSE_TP_DESC_TXT_PD*/
	public final EZDCStringItemArray              coaMdseTpDescTxt_PD;

    /** COA_PROD_CD_PS*/
	public final EZDCStringItem              coaProdCd_PS;

    /** COA_PROD_CD_PC*/
	public final EZDCStringItemArray              coaProdCd_PC;

    /** COA_PROD_DESC_TXT_PD*/
	public final EZDCStringItemArray              coaProdDescTxt_PD;

    /** RTL_SWH_CD_PC*/
	public final EZDCStringItemArray              rtlSwhCd_PC;

    /** RTL_SWH_CD_PD*/
	public final EZDCStringItemArray              rtlSwhCd_PD;

    /** INVTY_OWNR_CD_PC*/
	public final EZDCStringItemArray              invtyOwnrCd_PC;

    /** INVTY_OWNR_DESC_TXT_PD*/
	public final EZDCStringItemArray              invtyOwnrDescTxt_PD;

    /** THIRD_PTY_DSP_TP_CD_PC*/
	public final EZDCStringItemArray              thirdPtyDspTpCd_PC;

    /** THIRD_PTY_DSP_TP_DESC_TXT_PD*/
	public final EZDCStringItemArray              thirdPtyDspTpDescTxt_PD;

    /** EFF_FROM_DT*/
	public final EZDCDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDCDateItem              effThruDt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.NLAL2040.NLAL2040_ACMsgArray              A;

    /** B*/
	public final business.blap.NLAL2040.NLAL2040_BCMsgArray              B;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_FILE_DATA_UP*/
	public final EZDCMimeSourceItem              xxFileData_UP;

    /** XX_FILE_DATA_DW*/
	public final EZDCMimeSourceItem              xxFileData_DW;

    /** XX_TBL_NM_P1*/
	public final EZDCStringItem              xxTblNm_P1;

    /** XX_TBL_CD_COL_NM_P1*/
	public final EZDCStringItem              xxTblCdColNm_P1;

    /** XX_TBL_NM_COL_NM_P1*/
	public final EZDCStringItem              xxTblNmColNm_P1;

    /** XX_TBL_SORT_COL_NM_P1*/
	public final EZDCStringItem              xxTblSortColNm_P1;

    /** XX_SCR_NM_P1*/
	public final EZDCStringItem              xxScrNm_P1;

    /** XX_HDR_CD_LB_NM_P1*/
	public final EZDCStringItem              xxHdrCdLbNm_P1;

    /** XX_HDR_NM_LB_NM_P1*/
	public final EZDCStringItem              xxHdrNmLbNm_P1;

    /** XX_DTL_CD_LB_NM_P1*/
	public final EZDCStringItem              xxDtlCdLbNm_P1;

    /** XX_DTL_NM_LB_NM_P1*/
	public final EZDCStringItem              xxDtlNmLbNm_P1;

    /** XX_COND_CD_P1*/
	public final EZDCStringItem              xxCondCd_P1;

    /** XX_COND_NM_P1*/
	public final EZDCStringItem              xxCondNm_P1;


	/**
	 * NLAL2040CMsg is constructor.
	 * The initialization when the instance of NLAL2040CMsg is generated.
	 */
	public NLAL2040CMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2040CMsg is constructor.
	 * The initialization when the instance of NLAL2040CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2040CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		xxRsltFlg = (EZDCStringItem)newItem("xxRsltFlg");
		t_MdlNm = (EZDCStringItem)newItem("t_MdlNm");
		xxChkBox = (EZDCStringItem)newItem("xxChkBox");
		svcSegCd_PS = (EZDCStringItem)newItem("svcSegCd_PS");
		svcSegCd_PC = (EZDCStringItemArray)newItemArray("svcSegCd_PC");
		svcSegDescTxt_PD = (EZDCStringItemArray)newItemArray("svcSegDescTxt_PD");
		coaMdseTpCd_PS = (EZDCStringItem)newItem("coaMdseTpCd_PS");
		coaMdseTpCd_PC = (EZDCStringItemArray)newItemArray("coaMdseTpCd_PC");
		coaMdseTpDescTxt_PD = (EZDCStringItemArray)newItemArray("coaMdseTpDescTxt_PD");
		coaProdCd_PS = (EZDCStringItem)newItem("coaProdCd_PS");
		coaProdCd_PC = (EZDCStringItemArray)newItemArray("coaProdCd_PC");
		coaProdDescTxt_PD = (EZDCStringItemArray)newItemArray("coaProdDescTxt_PD");
		rtlSwhCd_PC = (EZDCStringItemArray)newItemArray("rtlSwhCd_PC");
		rtlSwhCd_PD = (EZDCStringItemArray)newItemArray("rtlSwhCd_PD");
		invtyOwnrCd_PC = (EZDCStringItemArray)newItemArray("invtyOwnrCd_PC");
		invtyOwnrDescTxt_PD = (EZDCStringItemArray)newItemArray("invtyOwnrDescTxt_PD");
		thirdPtyDspTpCd_PC = (EZDCStringItemArray)newItemArray("thirdPtyDspTpCd_PC");
		thirdPtyDspTpDescTxt_PD = (EZDCStringItemArray)newItemArray("thirdPtyDspTpDescTxt_PD");
		effFromDt = (EZDCDateItem)newItem("effFromDt");
		effThruDt = (EZDCDateItem)newItem("effThruDt");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.NLAL2040.NLAL2040_ACMsgArray)newMsgArray("A");
		B = (business.blap.NLAL2040.NLAL2040_BCMsgArray)newMsgArray("B");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxFileData_UP = (EZDCMimeSourceItem)newItem("xxFileData_UP");
		xxFileData_DW = (EZDCMimeSourceItem)newItem("xxFileData_DW");
		xxTblNm_P1 = (EZDCStringItem)newItem("xxTblNm_P1");
		xxTblCdColNm_P1 = (EZDCStringItem)newItem("xxTblCdColNm_P1");
		xxTblNmColNm_P1 = (EZDCStringItem)newItem("xxTblNmColNm_P1");
		xxTblSortColNm_P1 = (EZDCStringItem)newItem("xxTblSortColNm_P1");
		xxScrNm_P1 = (EZDCStringItem)newItem("xxScrNm_P1");
		xxHdrCdLbNm_P1 = (EZDCStringItem)newItem("xxHdrCdLbNm_P1");
		xxHdrNmLbNm_P1 = (EZDCStringItem)newItem("xxHdrNmLbNm_P1");
		xxDtlCdLbNm_P1 = (EZDCStringItem)newItem("xxDtlCdLbNm_P1");
		xxDtlNmLbNm_P1 = (EZDCStringItem)newItem("xxDtlNmLbNm_P1");
		xxCondCd_P1 = (EZDCStringItem)newItem("xxCondCd_P1");
		xxCondNm_P1 = (EZDCStringItem)newItem("xxCondNm_P1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2040CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2040CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxRsltFlg", "xxRsltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"t_MdlNm", "t_MdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"svcSegCd_PS", "svcSegCd_PS", "PS", null, TYPE_HANKAKUEISU, "2", null},
	{"svcSegCd_PC", "svcSegCd_PC", "PC", "99", TYPE_HANKAKUEISU, "2", null},
	{"svcSegDescTxt_PD", "svcSegDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"coaMdseTpCd_PS", "coaMdseTpCd_PS", "PS", null, TYPE_HANKAKUEISU, "2", null},
	{"coaMdseTpCd_PC", "coaMdseTpCd_PC", "PC", "99", TYPE_HANKAKUEISU, "2", null},
	{"coaMdseTpDescTxt_PD", "coaMdseTpDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"coaProdCd_PS", "coaProdCd_PS", "PS", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd_PC", "coaProdCd_PC", "PC", "99", TYPE_HANKAKUEISU, "8", null},
	{"coaProdDescTxt_PD", "coaProdDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"rtlSwhCd_PC", "rtlSwhCd_PC", "PC", "99", TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd_PD", "rtlSwhCd_PD", "PD", "99", TYPE_HANKAKUEISU, "20", null},
	{"invtyOwnrCd_PC", "invtyOwnrCd_PC", "PC", "99", TYPE_HANKAKUEISU, "3", null},
	{"invtyOwnrDescTxt_PD", "invtyOwnrDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"thirdPtyDspTpCd_PC", "thirdPtyDspTpCd_PC", "PC", "99", TYPE_HANKAKUEISU, "2", null},
	{"thirdPtyDspTpDescTxt_PD", "thirdPtyDspTpDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "20", "business.blap.NLAL2040.NLAL2040_ACMsgArray", null, null},
	
	{"B", "B", null, "20", "business.blap.NLAL2040.NLAL2040_BCMsgArray", null, null},
	
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxFileData_UP", "xxFileData_UP", "UP", null, TYPE_UPLOAD, null, null},
	{"xxFileData_DW", "xxFileData_DW", "DW", null, TYPE_UPLOAD, null, null},
	{"xxTblNm_P1", "xxTblNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblCdColNm_P1", "xxTblCdColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblNmColNm_P1", "xxTblNmColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblSortColNm_P1", "xxTblSortColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrNm_P1", "xxScrNm_P1", "P1", null, TYPE_HANKAKUEISU, "70", null},
	{"xxHdrCdLbNm_P1", "xxHdrCdLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxHdrNmLbNm_P1", "xxHdrNmLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlCdLbNm_P1", "xxDtlCdLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlNmLbNm_P1", "xxDtlNmLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxCondCd_P1", "xxCondCd_P1", "P1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxCondNm_P1", "xxCondNm_P1", "P1", null, TYPE_HANKAKUEISU, "70", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"SVC_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSegCd_PS
        {"SVC_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSegCd_PC
        {"SVC_SEG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSegDescTxt_PD
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_PS
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_PC
        {"COA_MDSE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpDescTxt_PD
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_PS
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_PC
        {"COA_PROD_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdDescTxt_PD
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_PC
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_PD
        {"INVTY_OWNR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOwnrCd_PC
        {"INVTY_OWNR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOwnrDescTxt_PD
        {"THIRD_PTY_DSP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdPtyDspTpCd_PC
        {"THIRD_PTY_DSP_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdPtyDspTpDescTxt_PD
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
		null,	//B
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_UP
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_DW
        {"XX_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm_P1
        {"XX_TBL_CD_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblCdColNm_P1
        {"XX_TBL_NM_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNmColNm_P1
        {"XX_TBL_SORT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm_P1
        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm_P1
        {"XX_HDR_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrCdLbNm_P1
        {"XX_HDR_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNmLbNm_P1
        {"XX_DTL_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCdLbNm_P1
        {"XX_DTL_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNmLbNm_P1
        {"XX_COND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondCd_P1
        {"XX_COND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondNm_P1
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
