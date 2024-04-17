//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20181120162610000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1150BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1150 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1150BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** VND_CD_A1*/
	public final EZDBStringItem              vndCd_A1;

    /** DPLY_VND_NM_A1*/
	public final EZDBStringItem              dplyVndNm_A1;

    /** EDI_PO_ORD_NUM_A1*/
	public final EZDBStringItem              ediPoOrdNum_A1;

    /** ASN_EDI_PROC_STS_CD_SV*/
	public final EZDBStringItem              asnEdiProcStsCd_SV;

    /** ASN_EDI_PROC_STS_CD_CD*/
	public final EZDBStringItemArray              asnEdiProcStsCd_CD;

    /** ASN_EDI_PROC_STS_NM_DI*/
	public final EZDBStringItemArray              asnEdiProcStsNm_DI;

    /** XX_FROM_DT_A1*/
	public final EZDBDateItem              xxFromDt_A1;

    /** XX_TO_DT_A1*/
	public final EZDBDateItem              xxToDt_A1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_DPLY_TAB*/
	public final EZDBStringItem              xxDplyTab;

    /** XX_NUM*/
	public final EZDBBigDecimalItem              xxNum;

    /** XX_ERR_FLG_A1*/
	public final EZDBStringItem              xxErrFlg_A1;

    /** BAT_ERR_MSG_TXT_A1*/
	public final EZDBStringItem              batErrMsgTxt_A1;

    /** VND_CD_A2*/
	public final EZDBStringItem              vndCd_A2;

    /** EDI_PO_ORD_NUM_A2*/
	public final EZDBStringItem              ediPoOrdNum_A2;

    /** ASN_EDI_PROC_STS_CD_A2*/
	public final EZDBStringItem              asnEdiProcStsCd_A2;

    /** XX_FROM_DT_A2*/
	public final EZDBDateItem              xxFromDt_A2;

    /** XX_TO_DT_A2*/
	public final EZDBDateItem              xxToDt_A2;

    /** BAT_ERR_MSG_TXT_A2*/
	public final EZDBStringItem              batErrMsgTxt_A2;

    /** A*/
	public final business.servlet.NPAL1150.NPAL1150_ABMsgArray              A;

    /** B*/
	public final business.servlet.NPAL1150.NPAL1150_BBMsgArray              B;

    /** XX_CHK_BOX_H1*/
	public final EZDBStringItem              xxChkBox_H1;

    /** C*/
	public final business.servlet.NPAL1150.NPAL1150_CBMsgArray              C;

    /** XX_TBL_NM*/
	public final EZDBStringItem              xxTblNm;

    /** XX_TBL_CD_COL_NM*/
	public final EZDBStringItem              xxTblCdColNm;

    /** XX_TBL_NM_COL_NM*/
	public final EZDBStringItem              xxTblNmColNm;

    /** XX_TBL_SORT_COL_NM*/
	public final EZDBStringItem              xxTblSortColNm;

    /** XX_SCR_NM*/
	public final EZDBStringItem              xxScrNm;

    /** XX_HDR_CD_LB_NM*/
	public final EZDBStringItem              xxHdrCdLbNm;

    /** XX_HDR_NM_LB_NM*/
	public final EZDBStringItem              xxHdrNmLbNm;

    /** XX_DTL_CD_LB_NM*/
	public final EZDBStringItem              xxDtlCdLbNm;

    /** XX_DTL_NM_LB_NM*/
	public final EZDBStringItem              xxDtlNmLbNm;


	/**
	 * NPAL1150BMsg is constructor.
	 * The initialization when the instance of NPAL1150BMsg is generated.
	 */
	public NPAL1150BMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1150BMsg is constructor.
	 * The initialization when the instance of NPAL1150BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1150BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		vndCd_A1 = (EZDBStringItem)newItem("vndCd_A1");
		dplyVndNm_A1 = (EZDBStringItem)newItem("dplyVndNm_A1");
		ediPoOrdNum_A1 = (EZDBStringItem)newItem("ediPoOrdNum_A1");
		asnEdiProcStsCd_SV = (EZDBStringItem)newItem("asnEdiProcStsCd_SV");
		asnEdiProcStsCd_CD = (EZDBStringItemArray)newItemArray("asnEdiProcStsCd_CD");
		asnEdiProcStsNm_DI = (EZDBStringItemArray)newItemArray("asnEdiProcStsNm_DI");
		xxFromDt_A1 = (EZDBDateItem)newItem("xxFromDt_A1");
		xxToDt_A1 = (EZDBDateItem)newItem("xxToDt_A1");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxDplyTab = (EZDBStringItem)newItem("xxDplyTab");
		xxNum = (EZDBBigDecimalItem)newItem("xxNum");
		xxErrFlg_A1 = (EZDBStringItem)newItem("xxErrFlg_A1");
		batErrMsgTxt_A1 = (EZDBStringItem)newItem("batErrMsgTxt_A1");
		vndCd_A2 = (EZDBStringItem)newItem("vndCd_A2");
		ediPoOrdNum_A2 = (EZDBStringItem)newItem("ediPoOrdNum_A2");
		asnEdiProcStsCd_A2 = (EZDBStringItem)newItem("asnEdiProcStsCd_A2");
		xxFromDt_A2 = (EZDBDateItem)newItem("xxFromDt_A2");
		xxToDt_A2 = (EZDBDateItem)newItem("xxToDt_A2");
		batErrMsgTxt_A2 = (EZDBStringItem)newItem("batErrMsgTxt_A2");
		A = (business.servlet.NPAL1150.NPAL1150_ABMsgArray)newMsgArray("A");
		B = (business.servlet.NPAL1150.NPAL1150_BBMsgArray)newMsgArray("B");
		xxChkBox_H1 = (EZDBStringItem)newItem("xxChkBox_H1");
		C = (business.servlet.NPAL1150.NPAL1150_CBMsgArray)newMsgArray("C");
		xxTblNm = (EZDBStringItem)newItem("xxTblNm");
		xxTblCdColNm = (EZDBStringItem)newItem("xxTblCdColNm");
		xxTblNmColNm = (EZDBStringItem)newItem("xxTblNmColNm");
		xxTblSortColNm = (EZDBStringItem)newItem("xxTblSortColNm");
		xxScrNm = (EZDBStringItem)newItem("xxScrNm");
		xxHdrCdLbNm = (EZDBStringItem)newItem("xxHdrCdLbNm");
		xxHdrNmLbNm = (EZDBStringItem)newItem("xxHdrNmLbNm");
		xxDtlCdLbNm = (EZDBStringItem)newItem("xxDtlCdLbNm");
		xxDtlNmLbNm = (EZDBStringItem)newItem("xxDtlNmLbNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1150BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1150BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"vndCd_A1", "vndCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dplyVndNm_A1", "dplyVndNm_A1", "A1", null, TYPE_HANKAKUEISU, "320", null},
	{"ediPoOrdNum_A1", "ediPoOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"asnEdiProcStsCd_SV", "asnEdiProcStsCd_SV", "SV", null, TYPE_HANKAKUEISU, "1", null},
	{"asnEdiProcStsCd_CD", "asnEdiProcStsCd_CD", "CD", "99", TYPE_HANKAKUEISU, "1", null},
	{"asnEdiProcStsNm_DI", "asnEdiProcStsNm_DI", "DI", "99", TYPE_HANKAKUEISU, "30", null},
	{"xxFromDt_A1", "xxFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxToDt_A1", "xxToDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxErrFlg_A1", "xxErrFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"batErrMsgTxt_A1", "batErrMsgTxt_A1", "A1", null, TYPE_HANKAKUEISU, "400", null},
	{"vndCd_A2", "vndCd_A2", "A2", null, TYPE_HANKAKUEISU, "20", null},
	{"ediPoOrdNum_A2", "ediPoOrdNum_A2", "A2", null, TYPE_HANKAKUEISU, "35", null},
	{"asnEdiProcStsCd_A2", "asnEdiProcStsCd_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxFromDt_A2", "xxFromDt_A2", "A2", null, TYPE_NENTSUKIHI, "8", null},
	{"xxToDt_A2", "xxToDt_A2", "A2", null, TYPE_NENTSUKIHI, "8", null},
	{"batErrMsgTxt_A2", "batErrMsgTxt_A2", "A2", null, TYPE_HANKAKUEISU, "400", null},
	{"A", "A", null, "200", "business.servlet.NPAL1150.NPAL1150_ABMsgArray", null, null},
	
	{"B", "B", null, "2", "business.servlet.NPAL1150.NPAL1150_BBMsgArray", null, null},
	
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"C", "C", null, "200", "business.servlet.NPAL1150.NPAL1150_CBMsgArray", null, null},
	
	{"xxTblNm", "xxTblNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblCdColNm", "xxTblCdColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblNmColNm", "xxTblNmColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblSortColNm", "xxTblSortColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"xxHdrCdLbNm", "xxHdrCdLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxHdrNmLbNm", "xxHdrNmLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlCdLbNm", "xxDtlCdLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlNmLbNm", "xxDtlNmLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_A1
        {"DPLY_VND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_A1
        {"EDI_PO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_A1
        {"ASN_EDI_PROC_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnEdiProcStsCd_SV
        {"ASN_EDI_PROC_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnEdiProcStsCd_CD
        {"ASN_EDI_PROC_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnEdiProcStsNm_DI
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt_A1
        {"XX_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxToDt_A1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_DPLY_TAB",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"XX_ERR_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg_A1
        {"BAT_ERR_MSG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_A1
        {"VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_A2
        {"EDI_PO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_A2
        {"ASN_EDI_PROC_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnEdiProcStsCd_A2
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt_A2
        {"XX_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxToDt_A2
        {"BAT_ERR_MSG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_A2
		null,	//A
		null,	//B
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
		null,	//C
        {"XX_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm
        {"XX_TBL_CD_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblCdColNm
        {"XX_TBL_NM_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNmColNm
        {"XX_TBL_SORT_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm
        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"XX_HDR_CD_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrCdLbNm
        {"XX_HDR_NM_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNmLbNm
        {"XX_DTL_CD_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCdLbNm
        {"XX_DTL_NM_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNmLbNm
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

