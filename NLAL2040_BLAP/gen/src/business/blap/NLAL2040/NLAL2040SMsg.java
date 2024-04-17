//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530120341000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2040SMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLAL2040 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2040SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** XX_RSLT_FLG*/
	public final EZDSStringItem              xxRsltFlg;

    /** T_MDL_NM*/
	public final EZDSStringItem              t_MdlNm;

    /** XX_CHK_BOX*/
	public final EZDSStringItem              xxChkBox;

    /** SVC_SEG_CD_PS*/
	public final EZDSStringItem              svcSegCd_PS;

    /** SVC_SEG_CD_PC*/
	public final EZDSStringItemArray              svcSegCd_PC;

    /** SVC_SEG_DESC_TXT_PD*/
	public final EZDSStringItemArray              svcSegDescTxt_PD;

    /** COA_MDSE_TP_CD_PS*/
	public final EZDSStringItem              coaMdseTpCd_PS;

    /** COA_MDSE_TP_CD_PC*/
	public final EZDSStringItemArray              coaMdseTpCd_PC;

    /** COA_MDSE_TP_DESC_TXT_PD*/
	public final EZDSStringItemArray              coaMdseTpDescTxt_PD;

    /** COA_PROD_CD_PS*/
	public final EZDSStringItem              coaProdCd_PS;

    /** COA_PROD_CD_PC*/
	public final EZDSStringItemArray              coaProdCd_PC;

    /** COA_PROD_DESC_TXT_PD*/
	public final EZDSStringItemArray              coaProdDescTxt_PD;

    /** RTL_SWH_CD_PC*/
	public final EZDSStringItemArray              rtlSwhCd_PC;

    /** RTL_SWH_CD_PD*/
	public final EZDSStringItemArray              rtlSwhCd_PD;

    /** INVTY_OWNR_CD_PC*/
	public final EZDSStringItemArray              invtyOwnrCd_PC;

    /** INVTY_OWNR_DESC_TXT_PD*/
	public final EZDSStringItemArray              invtyOwnrDescTxt_PD;

    /** THIRD_PTY_DSP_TP_CD_PC*/
	public final EZDSStringItemArray              thirdPtyDspTpCd_PC;

    /** THIRD_PTY_DSP_TP_DESC_TXT_PD*/
	public final EZDSStringItemArray              thirdPtyDspTpDescTxt_PD;

    /** EFF_FROM_DT*/
	public final EZDSDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDSDateItem              effThruDt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.NLAL2040.NLAL2040_ASMsgArray              A;

    /** B*/
	public final business.blap.NLAL2040.NLAL2040_BSMsgArray              B;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** XX_FILE_DATA_UP*/
	public final EZDSMimeSourceItem              xxFileData_UP;

    /** XX_FILE_DATA_DW*/
	public final EZDSMimeSourceItem              xxFileData_DW;

    /** XX_TBL_NM_P1*/
	public final EZDSStringItem              xxTblNm_P1;

    /** XX_TBL_CD_COL_NM_P1*/
	public final EZDSStringItem              xxTblCdColNm_P1;

    /** XX_TBL_NM_COL_NM_P1*/
	public final EZDSStringItem              xxTblNmColNm_P1;

    /** XX_TBL_SORT_COL_NM_P1*/
	public final EZDSStringItem              xxTblSortColNm_P1;

    /** XX_SCR_NM_P1*/
	public final EZDSStringItem              xxScrNm_P1;

    /** XX_HDR_CD_LB_NM_P1*/
	public final EZDSStringItem              xxHdrCdLbNm_P1;

    /** XX_HDR_NM_LB_NM_P1*/
	public final EZDSStringItem              xxHdrNmLbNm_P1;

    /** XX_DTL_CD_LB_NM_P1*/
	public final EZDSStringItem              xxDtlCdLbNm_P1;

    /** XX_DTL_NM_LB_NM_P1*/
	public final EZDSStringItem              xxDtlNmLbNm_P1;

    /** XX_COND_CD_P1*/
	public final EZDSStringItem              xxCondCd_P1;

    /** XX_COND_NM_P1*/
	public final EZDSStringItem              xxCondNm_P1;


	/**
	 * NLAL2040SMsg is constructor.
	 * The initialization when the instance of NLAL2040SMsg is generated.
	 */
	public NLAL2040SMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2040SMsg is constructor.
	 * The initialization when the instance of NLAL2040SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2040SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		xxRsltFlg = (EZDSStringItem)newItem("xxRsltFlg");
		t_MdlNm = (EZDSStringItem)newItem("t_MdlNm");
		xxChkBox = (EZDSStringItem)newItem("xxChkBox");
		svcSegCd_PS = (EZDSStringItem)newItem("svcSegCd_PS");
		svcSegCd_PC = (EZDSStringItemArray)newItemArray("svcSegCd_PC");
		svcSegDescTxt_PD = (EZDSStringItemArray)newItemArray("svcSegDescTxt_PD");
		coaMdseTpCd_PS = (EZDSStringItem)newItem("coaMdseTpCd_PS");
		coaMdseTpCd_PC = (EZDSStringItemArray)newItemArray("coaMdseTpCd_PC");
		coaMdseTpDescTxt_PD = (EZDSStringItemArray)newItemArray("coaMdseTpDescTxt_PD");
		coaProdCd_PS = (EZDSStringItem)newItem("coaProdCd_PS");
		coaProdCd_PC = (EZDSStringItemArray)newItemArray("coaProdCd_PC");
		coaProdDescTxt_PD = (EZDSStringItemArray)newItemArray("coaProdDescTxt_PD");
		rtlSwhCd_PC = (EZDSStringItemArray)newItemArray("rtlSwhCd_PC");
		rtlSwhCd_PD = (EZDSStringItemArray)newItemArray("rtlSwhCd_PD");
		invtyOwnrCd_PC = (EZDSStringItemArray)newItemArray("invtyOwnrCd_PC");
		invtyOwnrDescTxt_PD = (EZDSStringItemArray)newItemArray("invtyOwnrDescTxt_PD");
		thirdPtyDspTpCd_PC = (EZDSStringItemArray)newItemArray("thirdPtyDspTpCd_PC");
		thirdPtyDspTpDescTxt_PD = (EZDSStringItemArray)newItemArray("thirdPtyDspTpDescTxt_PD");
		effFromDt = (EZDSDateItem)newItem("effFromDt");
		effThruDt = (EZDSDateItem)newItem("effThruDt");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.NLAL2040.NLAL2040_ASMsgArray)newMsgArray("A");
		B = (business.blap.NLAL2040.NLAL2040_BSMsgArray)newMsgArray("B");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		xxFileData_UP = (EZDSMimeSourceItem)newItem("xxFileData_UP");
		xxFileData_DW = (EZDSMimeSourceItem)newItem("xxFileData_DW");
		xxTblNm_P1 = (EZDSStringItem)newItem("xxTblNm_P1");
		xxTblCdColNm_P1 = (EZDSStringItem)newItem("xxTblCdColNm_P1");
		xxTblNmColNm_P1 = (EZDSStringItem)newItem("xxTblNmColNm_P1");
		xxTblSortColNm_P1 = (EZDSStringItem)newItem("xxTblSortColNm_P1");
		xxScrNm_P1 = (EZDSStringItem)newItem("xxScrNm_P1");
		xxHdrCdLbNm_P1 = (EZDSStringItem)newItem("xxHdrCdLbNm_P1");
		xxHdrNmLbNm_P1 = (EZDSStringItem)newItem("xxHdrNmLbNm_P1");
		xxDtlCdLbNm_P1 = (EZDSStringItem)newItem("xxDtlCdLbNm_P1");
		xxDtlNmLbNm_P1 = (EZDSStringItem)newItem("xxDtlNmLbNm_P1");
		xxCondCd_P1 = (EZDSStringItem)newItem("xxCondCd_P1");
		xxCondNm_P1 = (EZDSStringItem)newItem("xxCondNm_P1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2040SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2040SMsgArray();
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
	{"A", "A", null, "999", "business.blap.NLAL2040.NLAL2040_ASMsgArray", null, null},
	
	{"B", "B", null, "999", "business.blap.NLAL2040.NLAL2040_BSMsgArray", null, null},
	
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

