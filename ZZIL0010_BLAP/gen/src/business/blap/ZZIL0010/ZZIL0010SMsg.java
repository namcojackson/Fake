//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20090820204705000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0010SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0010 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0010SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INTERFACE_ID*/
	public final EZDSStringItem              interfaceId;

    /** TRANSACTION_ID*/
	public final EZDSBigDecimalItem              transactionId;

    /** PROCESSED_FLAG_PS*/
	public final EZDSStringItem              processedFlag_PS;

    /** XX_FROM_DT_RF*/
	public final EZDSDateItem              xxFromDt_RF;

    /** XX_HRS_R1*/
	public final EZDSStringItem              xxHrs_R1;

    /** XX_TO_DT_RT*/
	public final EZDSDateItem              xxToDt_RT;

    /** XX_HRS_R2*/
	public final EZDSStringItem              xxHrs_R2;

    /** XX_FROM_DT_UF*/
	public final EZDSDateItem              xxFromDt_UF;

    /** XX_HRS_U1*/
	public final EZDSStringItem              xxHrs_U1;

    /** XX_TO_DT_UT*/
	public final EZDSDateItem              xxToDt_UT;

    /** XX_HRS_U2*/
	public final EZDSStringItem              xxHrs_U2;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.ZZIL0010.ZZIL0010_ASMsgArray              A;

    /** XX_SORT_TBL_NM*/
	public final EZDSStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDSStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDSStringItem              xxSortOrdByTxt;


	/**
	 * ZZIL0010SMsg is constructor.
	 * The initialization when the instance of ZZIL0010SMsg is generated.
	 */
	public ZZIL0010SMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0010SMsg is constructor.
	 * The initialization when the instance of ZZIL0010SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0010SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		interfaceId = (EZDSStringItem)newItem("interfaceId");
		transactionId = (EZDSBigDecimalItem)newItem("transactionId");
		processedFlag_PS = (EZDSStringItem)newItem("processedFlag_PS");
		xxFromDt_RF = (EZDSDateItem)newItem("xxFromDt_RF");
		xxHrs_R1 = (EZDSStringItem)newItem("xxHrs_R1");
		xxToDt_RT = (EZDSDateItem)newItem("xxToDt_RT");
		xxHrs_R2 = (EZDSStringItem)newItem("xxHrs_R2");
		xxFromDt_UF = (EZDSDateItem)newItem("xxFromDt_UF");
		xxHrs_U1 = (EZDSStringItem)newItem("xxHrs_U1");
		xxToDt_UT = (EZDSDateItem)newItem("xxToDt_UT");
		xxHrs_U2 = (EZDSStringItem)newItem("xxHrs_U2");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.ZZIL0010.ZZIL0010_ASMsgArray)newMsgArray("A");
		xxSortTblNm = (EZDSStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDSStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDSStringItem)newItem("xxSortOrdByTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0010SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0010SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"interfaceId", "interfaceId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"transactionId", "transactionId", null, null, TYPE_SEISU_SYOSU, "30", "0"},
	{"processedFlag_PS", "processedFlag_PS", "PS", null, TYPE_HANKAKUEISU, "1", null},
	{"xxFromDt_RF", "xxFromDt_RF", "RF", null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_R1", "xxHrs_R1", "R1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxToDt_RT", "xxToDt_RT", "RT", null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_R2", "xxHrs_R2", "R2", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxFromDt_UF", "xxFromDt_UF", "UF", null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_U1", "xxHrs_U1", "U1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxToDt_UT", "xxToDt_UT", "UT", null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_U2", "xxHrs_U2", "U2", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.blap.ZZIL0010.ZZIL0010_ASMsgArray", null, null},
	
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INTERFACE_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//interfaceId
        {"TRANSACTION_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//transactionId
        {"PROCESSED_FLAG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//processedFlag_PS
        {"XX_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFromDt_RF
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_R1
        {"XX_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxToDt_RT
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_R2
        {"XX_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFromDt_UF
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_U1
        {"XX_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxToDt_UT
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_U2
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
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
