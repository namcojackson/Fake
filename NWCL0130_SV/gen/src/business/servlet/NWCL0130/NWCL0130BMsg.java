//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170217125810000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0130BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWCL0130;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWCL0130 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWCL0130BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** O*/
	public final business.servlet.NWCL0130.NWCL0130_OBMsgArray              O;

    /** P*/
	public final business.servlet.NWCL0130.NWCL0130_PBMsgArray              P;

    /** XX_PAGE_SHOW_FROM_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_10;

    /** XX_PAGE_SHOW_TO_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowToNum_10;

    /** XX_PAGE_SHOW_OF_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_10;

    /** XX_PAGE_SHOW_CUR_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowCurNum_10;

    /** XX_PAGE_SHOW_TOT_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowTotNum_10;

    /** XX_SORT_TBL_NM_01*/
	public final EZDBStringItem              xxSortTblNm_01;

    /** XX_SORT_ITEM_NM_01*/
	public final EZDBStringItem              xxSortItemNm_01;

    /** XX_SORT_ORD_BY_TXT_01*/
	public final EZDBStringItem              xxSortOrdByTxt_01;

    /** CONSL_RGNR_ACT_TP_CD_H1*/
	public final EZDBStringItem              conslRgnrActTpCd_H1;

    /** CONSL_RGNR_ACT_TP_CD_PL*/
	public final EZDBStringItemArray              conslRgnrActTpCd_PL;

    /** CONSL_RGNR_ACT_TP_DESC_TXT_PL*/
	public final EZDBStringItemArray              conslRgnrActTpDescTxt_PL;

    /** XX_LINK_PROT_01*/
	public final EZDBStringItem              xxLinkProt_01;

    /** CONSL_BILL_NUM_H1*/
	public final EZDBStringItem              conslBillNum_H1;

    /** XX_LINK_PROT_02*/
	public final EZDBStringItem              xxLinkProt_02;

    /** INV_NUM_H1*/
	public final EZDBStringItem              invNum_H1;

    /** XX_DTL_CNT_H1*/
	public final EZDBBigDecimalItem              xxDtlCnt_H1;

    /** XX_DTL_CNT_H2*/
	public final EZDBBigDecimalItem              xxDtlCnt_H2;

    /** XX_CHK_BOX_AL*/
	public final EZDBStringItem              xxChkBox_AL;

    /** A*/
	public final business.servlet.NWCL0130.NWCL0130_ABMsgArray              A;


	/**
	 * NWCL0130BMsg is constructor.
	 * The initialization when the instance of NWCL0130BMsg is generated.
	 */
	public NWCL0130BMsg() {
		this(false, -1);
	}

	/**
	 * NWCL0130BMsg is constructor.
	 * The initialization when the instance of NWCL0130BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWCL0130BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		O = (business.servlet.NWCL0130.NWCL0130_OBMsgArray)newMsgArray("O");
		P = (business.servlet.NWCL0130.NWCL0130_PBMsgArray)newMsgArray("P");
		xxPageShowFromNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_10");
		xxPageShowToNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowToNum_10");
		xxPageShowOfNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_10");
		xxPageShowCurNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowCurNum_10");
		xxPageShowTotNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowTotNum_10");
		xxSortTblNm_01 = (EZDBStringItem)newItem("xxSortTblNm_01");
		xxSortItemNm_01 = (EZDBStringItem)newItem("xxSortItemNm_01");
		xxSortOrdByTxt_01 = (EZDBStringItem)newItem("xxSortOrdByTxt_01");
		conslRgnrActTpCd_H1 = (EZDBStringItem)newItem("conslRgnrActTpCd_H1");
		conslRgnrActTpCd_PL = (EZDBStringItemArray)newItemArray("conslRgnrActTpCd_PL");
		conslRgnrActTpDescTxt_PL = (EZDBStringItemArray)newItemArray("conslRgnrActTpDescTxt_PL");
		xxLinkProt_01 = (EZDBStringItem)newItem("xxLinkProt_01");
		conslBillNum_H1 = (EZDBStringItem)newItem("conslBillNum_H1");
		xxLinkProt_02 = (EZDBStringItem)newItem("xxLinkProt_02");
		invNum_H1 = (EZDBStringItem)newItem("invNum_H1");
		xxDtlCnt_H1 = (EZDBBigDecimalItem)newItem("xxDtlCnt_H1");
		xxDtlCnt_H2 = (EZDBBigDecimalItem)newItem("xxDtlCnt_H2");
		xxChkBox_AL = (EZDBStringItem)newItem("xxChkBox_AL");
		A = (business.servlet.NWCL0130.NWCL0130_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWCL0130BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWCL0130BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"O", "O", null, "20", "business.servlet.NWCL0130.NWCL0130_OBMsgArray", null, null},
	
	{"P", "P", null, "30", "business.servlet.NWCL0130.NWCL0130_PBMsgArray", null, null},
	
	{"xxPageShowFromNum_10", "xxPageShowFromNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_10", "xxPageShowToNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_10", "xxPageShowOfNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_10", "xxPageShowCurNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_10", "xxPageShowTotNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm_01", "xxSortTblNm_01", "01", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_01", "xxSortItemNm_01", "01", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_01", "xxSortOrdByTxt_01", "01", null, TYPE_HANKAKUEISU, "4", null},
	{"conslRgnrActTpCd_H1", "conslRgnrActTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"conslRgnrActTpCd_PL", "conslRgnrActTpCd_PL", "PL", "99", TYPE_HANKAKUEISU, "2", null},
	{"conslRgnrActTpDescTxt_PL", "conslRgnrActTpDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_01", "xxLinkProt_01", "01", null, TYPE_HANKAKUEISU, "1", null},
	{"conslBillNum_H1", "conslBillNum_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxLinkProt_02", "xxLinkProt_02", "02", null, TYPE_HANKAKUEISU, "1", null},
	{"invNum_H1", "invNum_H1", "H1", null, TYPE_HANKAKUEISU, "13", null},
	{"xxDtlCnt_H1", "xxDtlCnt_H1", "H1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxDtlCnt_H2", "xxDtlCnt_H2", "H2", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_AL", "xxChkBox_AL", "AL", null, TYPE_HANKAKUEIJI, "1", null},
	{"A", "A", null, "200", "business.servlet.NWCL0130.NWCL0130_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//O
		null,	//P
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_10
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_10
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_10
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_10
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_10
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_01
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_01
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_01
        {"CONSL_RGNR_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslRgnrActTpCd_H1
        {"CONSL_RGNR_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslRgnrActTpCd_PL
        {"CONSL_RGNR_ACT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslRgnrActTpDescTxt_PL
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_01
        {"CONSL_BILL_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillNum_H1
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_02
        {"INV_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_H1
        {"XX_DTL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_H1
        {"XX_DTL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_H2
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AL
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
