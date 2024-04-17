//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20090814083400000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0010BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZIL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0010 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0010BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INTERFACE_ID*/
	public final EZDBStringItem              interfaceId;

    /** TRANSACTION_ID*/
	public final EZDBBigDecimalItem              transactionId;

    /** PROCESSED_FLAG_PC*/
	public final EZDBStringItemArray              processedFlag_PC;

    /** XX_PROC_FLG_NM_PC*/
	public final EZDBStringItemArray              xxProcFlgNm_PC;

    /** PROCESSED_FLAG_PS*/
	public final EZDBStringItem              processedFlag_PS;

    /** XX_FROM_DT_RF*/
	public final EZDBDateItem              xxFromDt_RF;

    /** XX_HRS_RF*/
	public final EZDBStringItemArray              xxHrs_RF;

    /** XX_HRS_MN_RF*/
	public final EZDBStringItemArray              xxHrsMn_RF;

    /** XX_HRS_R1*/
	public final EZDBStringItem              xxHrs_R1;

    /** XX_TO_DT_RT*/
	public final EZDBDateItem              xxToDt_RT;

    /** XX_HRS_RT*/
	public final EZDBStringItemArray              xxHrs_RT;

    /** XX_HRS_MN_RT*/
	public final EZDBStringItemArray              xxHrsMn_RT;

    /** XX_HRS_R2*/
	public final EZDBStringItem              xxHrs_R2;

    /** XX_FROM_DT_UF*/
	public final EZDBDateItem              xxFromDt_UF;

    /** XX_HRS_UF*/
	public final EZDBStringItemArray              xxHrs_UF;

    /** XX_HRS_MN_UF*/
	public final EZDBStringItemArray              xxHrsMn_UF;

    /** XX_HRS_U1*/
	public final EZDBStringItem              xxHrs_U1;

    /** XX_TO_DT_UT*/
	public final EZDBDateItem              xxToDt_UT;

    /** XX_HRS_UT*/
	public final EZDBStringItemArray              xxHrs_UT;

    /** XX_HRS_MN_UT*/
	public final EZDBStringItemArray              xxHrsMn_UT;

    /** XX_HRS_U2*/
	public final EZDBStringItem              xxHrs_U2;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.servlet.ZZIL0010.ZZIL0010_ABMsgArray              A;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_RSLT_FLG*/
	public final EZDBStringItem              xxRsltFlg;


	/**
	 * ZZIL0010BMsg is constructor.
	 * The initialization when the instance of ZZIL0010BMsg is generated.
	 */
	public ZZIL0010BMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0010BMsg is constructor.
	 * The initialization when the instance of ZZIL0010BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0010BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		interfaceId = (EZDBStringItem)newItem("interfaceId");
		transactionId = (EZDBBigDecimalItem)newItem("transactionId");
		processedFlag_PC = (EZDBStringItemArray)newItemArray("processedFlag_PC");
		xxProcFlgNm_PC = (EZDBStringItemArray)newItemArray("xxProcFlgNm_PC");
		processedFlag_PS = (EZDBStringItem)newItem("processedFlag_PS");
		xxFromDt_RF = (EZDBDateItem)newItem("xxFromDt_RF");
		xxHrs_RF = (EZDBStringItemArray)newItemArray("xxHrs_RF");
		xxHrsMn_RF = (EZDBStringItemArray)newItemArray("xxHrsMn_RF");
		xxHrs_R1 = (EZDBStringItem)newItem("xxHrs_R1");
		xxToDt_RT = (EZDBDateItem)newItem("xxToDt_RT");
		xxHrs_RT = (EZDBStringItemArray)newItemArray("xxHrs_RT");
		xxHrsMn_RT = (EZDBStringItemArray)newItemArray("xxHrsMn_RT");
		xxHrs_R2 = (EZDBStringItem)newItem("xxHrs_R2");
		xxFromDt_UF = (EZDBDateItem)newItem("xxFromDt_UF");
		xxHrs_UF = (EZDBStringItemArray)newItemArray("xxHrs_UF");
		xxHrsMn_UF = (EZDBStringItemArray)newItemArray("xxHrsMn_UF");
		xxHrs_U1 = (EZDBStringItem)newItem("xxHrs_U1");
		xxToDt_UT = (EZDBDateItem)newItem("xxToDt_UT");
		xxHrs_UT = (EZDBStringItemArray)newItemArray("xxHrs_UT");
		xxHrsMn_UT = (EZDBStringItemArray)newItemArray("xxHrsMn_UT");
		xxHrs_U2 = (EZDBStringItem)newItem("xxHrs_U2");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.servlet.ZZIL0010.ZZIL0010_ABMsgArray)newMsgArray("A");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxRsltFlg = (EZDBStringItem)newItem("xxRsltFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0010BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0010BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"interfaceId", "interfaceId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"transactionId", "transactionId", null, null, TYPE_SEISU_SYOSU, "30", "0"},
	{"processedFlag_PC", "processedFlag_PC", "PC", "3", TYPE_HANKAKUEISU, "1", null},
	{"xxProcFlgNm_PC", "xxProcFlgNm_PC", "PC", "3", TYPE_HANKAKUEISU, "11", null},
	{"processedFlag_PS", "processedFlag_PS", "PS", null, TYPE_HANKAKUEISU, "1", null},
	{"xxFromDt_RF", "xxFromDt_RF", "RF", null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_RF", "xxHrs_RF", "RF", "24", TYPE_HANKAKUSUJI, "2", null},
	{"xxHrsMn_RF", "xxHrsMn_RF", "RF", "24", TYPE_HANKAKUEISU, "5", null},
	{"xxHrs_R1", "xxHrs_R1", "R1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxToDt_RT", "xxToDt_RT", "RT", null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_RT", "xxHrs_RT", "RT", "24", TYPE_HANKAKUSUJI, "2", null},
	{"xxHrsMn_RT", "xxHrsMn_RT", "RT", "24", TYPE_HANKAKUEISU, "5", null},
	{"xxHrs_R2", "xxHrs_R2", "R2", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxFromDt_UF", "xxFromDt_UF", "UF", null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_UF", "xxHrs_UF", "UF", "24", TYPE_HANKAKUSUJI, "2", null},
	{"xxHrsMn_UF", "xxHrsMn_UF", "UF", "24", TYPE_HANKAKUEISU, "5", null},
	{"xxHrs_U1", "xxHrs_U1", "U1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxToDt_UT", "xxToDt_UT", "UT", null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_UT", "xxHrs_UT", "UT", "24", TYPE_HANKAKUSUJI, "2", null},
	{"xxHrsMn_UT", "xxHrsMn_UT", "UT", "24", TYPE_HANKAKUEISU, "5", null},
	{"xxHrs_U2", "xxHrs_U2", "U2", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "40", "business.servlet.ZZIL0010.ZZIL0010_ABMsgArray", null, null},
	
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxRsltFlg", "xxRsltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INTERFACE_ID", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//interfaceId
        {"TRANSACTION_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//transactionId
        {"PROCESSED_FLAG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//processedFlag_PC
        {"XX_PROC_FLG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxProcFlgNm_PC
        {"PROCESSED_FLAG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//processedFlag_PS
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt_RF
        {"XX_HRS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_RF
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_RF
        {"XX_HRS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_R1
        {"XX_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxToDt_RT
        {"XX_HRS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_RT
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_RT
        {"XX_HRS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_R2
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt_UF
        {"XX_HRS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_UF
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_UF
        {"XX_HRS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_U1
        {"XX_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxToDt_UT
        {"XX_HRS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_UT
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_UT
        {"XX_HRS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_U2
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_RSLT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg
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
