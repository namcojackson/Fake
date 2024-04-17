//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161206132549000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1030BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1030 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1030BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SVC_INV_NUM_P*/
	public final EZDBStringItem              svcInvNum_P;

    /** DS_CONTR_NUM_P*/
	public final EZDBStringItem              dsContrNum_P;

    /** CONSL_BILL_PK_P*/
	public final EZDBBigDecimalItem              conslBillPk_P;

    /** SER_NUM_P*/
	public final EZDBStringItem              serNum_P;

    /** BLLG_PER_FROM_DT_P*/
	public final EZDBDateItem              bllgPerFromDt_P;

    /** BLLG_PER_TO_DT_P*/
	public final EZDBDateItem              bllgPerToDt_P;

    /** SVC_CR_REBIL_PK_P*/
	public final EZDBBigDecimalItem              svcCrRebilPk_P;

    /** CUST_INCDT_ID_H*/
	public final EZDBStringItem              custIncdtId_H;

    /** SVC_CR_REBIL_STS_DESC_TXT_H*/
	public final EZDBStringItem              svcCrRebilStsDescTxt_H;

    /** SVC_CR_REBIL_DESC_TXT_H*/
	public final EZDBStringItem              svcCrRebilDescTxt_H;

    /** SVC_CR_REBIL_PK_H*/
	public final EZDBBigDecimalItem              svcCrRebilPk_H;

    /** SVC_CR_REBIL_STS_CD_H*/
	public final EZDBStringItem              svcCrRebilStsCd_H;

    /** A*/
	public final business.servlet.NSAL1030.NSAL1030_ABMsgArray              A;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** XX_MODE_CD_P0*/
	public final EZDBStringItem              xxModeCd_P0;

    /** SVC_CR_REBIL_PK_P0*/
	public final EZDBBigDecimalItem              svcCrRebilPk_P0;

    /** SVC_CR_REBIL_STS_CD_P0*/
	public final EZDBStringItem              svcCrRebilStsCd_P0;

    /** SVC_CR_REBIL_DTL_PK_P0*/
	public final EZDBBigDecimalItem              svcCrRebilDtlPk_P0;

    /** SVC_INV_LINE_PK_P0*/
	public final EZDBBigDecimalItem              svcInvLinePk_P0;


	/**
	 * NSAL1030BMsg is constructor.
	 * The initialization when the instance of NSAL1030BMsg is generated.
	 */
	public NSAL1030BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1030BMsg is constructor.
	 * The initialization when the instance of NSAL1030BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1030BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		svcInvNum_P = (EZDBStringItem)newItem("svcInvNum_P");
		dsContrNum_P = (EZDBStringItem)newItem("dsContrNum_P");
		conslBillPk_P = (EZDBBigDecimalItem)newItem("conslBillPk_P");
		serNum_P = (EZDBStringItem)newItem("serNum_P");
		bllgPerFromDt_P = (EZDBDateItem)newItem("bllgPerFromDt_P");
		bllgPerToDt_P = (EZDBDateItem)newItem("bllgPerToDt_P");
		svcCrRebilPk_P = (EZDBBigDecimalItem)newItem("svcCrRebilPk_P");
		custIncdtId_H = (EZDBStringItem)newItem("custIncdtId_H");
		svcCrRebilStsDescTxt_H = (EZDBStringItem)newItem("svcCrRebilStsDescTxt_H");
		svcCrRebilDescTxt_H = (EZDBStringItem)newItem("svcCrRebilDescTxt_H");
		svcCrRebilPk_H = (EZDBBigDecimalItem)newItem("svcCrRebilPk_H");
		svcCrRebilStsCd_H = (EZDBStringItem)newItem("svcCrRebilStsCd_H");
		A = (business.servlet.NSAL1030.NSAL1030_ABMsgArray)newMsgArray("A");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		xxModeCd_P0 = (EZDBStringItem)newItem("xxModeCd_P0");
		svcCrRebilPk_P0 = (EZDBBigDecimalItem)newItem("svcCrRebilPk_P0");
		svcCrRebilStsCd_P0 = (EZDBStringItem)newItem("svcCrRebilStsCd_P0");
		svcCrRebilDtlPk_P0 = (EZDBBigDecimalItem)newItem("svcCrRebilDtlPk_P0");
		svcInvLinePk_P0 = (EZDBBigDecimalItem)newItem("svcInvLinePk_P0");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1030BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1030BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcInvNum_P", "svcInvNum_P", "P", null, TYPE_HANKAKUEISU, "13", null},
	{"dsContrNum_P", "dsContrNum_P", "P", null, TYPE_HANKAKUEISU, "30", null},
	{"conslBillPk_P", "conslBillPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum_P", "serNum_P", "P", null, TYPE_HANKAKUEISU, "30", null},
	{"bllgPerFromDt_P", "bllgPerFromDt_P", "P", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgPerToDt_P", "bllgPerToDt_P", "P", null, TYPE_NENTSUKIHI, "8", null},
	{"svcCrRebilPk_P", "svcCrRebilPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"custIncdtId_H", "custIncdtId_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"svcCrRebilStsDescTxt_H", "svcCrRebilStsDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"svcCrRebilDescTxt_H", "svcCrRebilDescTxt_H", "H", null, TYPE_HANKAKUEISU, "4000", null},
	{"svcCrRebilPk_H", "svcCrRebilPk_H", "H", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCrRebilStsCd_H", "svcCrRebilStsCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"A", "A", null, "40", "business.servlet.NSAL1030.NSAL1030_ABMsgArray", null, null},
	
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxModeCd_P0", "xxModeCd_P0", "P0", null, TYPE_HANKAKUEISU, "2", null},
	{"svcCrRebilPk_P0", "svcCrRebilPk_P0", "P0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCrRebilStsCd_P0", "svcCrRebilStsCd_P0", "P0", null, TYPE_HANKAKUEISU, "2", null},
	{"svcCrRebilDtlPk_P0", "svcCrRebilDtlPk_P0", "P0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcInvLinePk_P0", "svcInvLinePk_P0", "P0", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SVC_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvNum_P
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_P
        {"CONSL_BILL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillPk_P
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_P
        {"BLLG_PER_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//bllgPerFromDt_P
        {"BLLG_PER_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//bllgPerToDt_P
        {"SVC_CR_REBIL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilPk_P
        {"CUST_INCDT_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIncdtId_H
        {"SVC_CR_REBIL_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilStsDescTxt_H
        {"SVC_CR_REBIL_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDescTxt_H
        {"SVC_CR_REBIL_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilPk_H
        {"SVC_CR_REBIL_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilStsCd_H
		null,	//A
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_P0
        {"SVC_CR_REBIL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilPk_P0
        {"SVC_CR_REBIL_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilStsCd_P0
        {"SVC_CR_REBIL_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDtlPk_P0
        {"SVC_INV_LINE_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvLinePk_P0
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

