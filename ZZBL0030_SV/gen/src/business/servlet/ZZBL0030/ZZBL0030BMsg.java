//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20091027175518000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZBL0030BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZBL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZBL0030 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZBL0030BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZREQBusinessApplicationName*/
	public final EZDBStringItem              ezReqBusinessName;

    /** _EZREQProcessStatus*/
	public final EZDBStringItem              ezReqJobStatus;

    /** _EZREQProcessStatus_DP*/
	public final EZDBStringItemArray              ezReqJobStatus_DP;

    /** XX_BAT_PROC_TRM_NM_DP*/
	public final EZDBStringItemArray              xxBatProcTrmNm_DP;

    /** _EZREQRegisteredDate_ST*/
	public final EZDBDateItem              ezReqInputDate_ST;

    /** _EZREQRegisteredTime_ST*/
	public final EZDBStringItem              ezReqInputTime_ST;

    /** _EZREQRegisteredTime_P1*/
	public final EZDBStringItemArray              ezReqInputTime_P1;

    /** XX_HRS_MN_P1*/
	public final EZDBStringItemArray              xxHrsMn_P1;

    /** _EZREQRegisteredDate_EN*/
	public final EZDBDateItem              ezReqInputDate_EN;

    /** _EZREQRegisteredTime_EN*/
	public final EZDBStringItem              ezReqInputTime_EN;

    /** _EZREQRegisteredTime_P2*/
	public final EZDBStringItemArray              ezReqInputTime_P2;

    /** XX_HRS_MN_P2*/
	public final EZDBStringItemArray              xxHrsMn_P2;

    /** _EZREQProcessStartDate*/
	public final EZDBDateItem              ezReqJobStartDate;

    /** _EZREQProcessStartTime*/
	public final EZDBStringItem              ezReqJobStartTime;

    /** _EZREQProcessStartTime_P3*/
	public final EZDBStringItemArray              ezReqJobStartTime_P3;

    /** XX_HRS_MN_P3*/
	public final EZDBStringItemArray              xxHrsMn_P3;

    /** _EZREQProcessCompleteDate*/
	public final EZDBDateItem              ezReqJobEndDate;

    /** _EZREQProcessCompleteTime*/
	public final EZDBStringItem              ezReqJobEndTime;

    /** _EZREQProcessCompleteTime_P4*/
	public final EZDBStringItemArray              ezReqJobEndTime_P4;

    /** XX_HRS_MN_P4*/
	public final EZDBStringItemArray              xxHrsMn_P4;

    /** A*/
	public final business.servlet.ZZBL0030.ZZBL0030_ABMsgArray              A;

    /** XX_SCR_NM*/
	public final EZDBStringItem              xxScrNm;

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

    /** XX_LAST_BTN_NM*/
	public final EZDBStringItem              xxLastBtnNm;


	/**
	 * ZZBL0030BMsg is constructor.
	 * The initialization when the instance of ZZBL0030BMsg is generated.
	 */
	public ZZBL0030BMsg() {
		this(false, -1);
	}

	/**
	 * ZZBL0030BMsg is constructor.
	 * The initialization when the instance of ZZBL0030BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZBL0030BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezReqBusinessName = (EZDBStringItem)newItem("ezReqBusinessName");
		ezReqJobStatus = (EZDBStringItem)newItem("ezReqJobStatus");
		ezReqJobStatus_DP = (EZDBStringItemArray)newItemArray("ezReqJobStatus_DP");
		xxBatProcTrmNm_DP = (EZDBStringItemArray)newItemArray("xxBatProcTrmNm_DP");
		ezReqInputDate_ST = (EZDBDateItem)newItem("ezReqInputDate_ST");
		ezReqInputTime_ST = (EZDBStringItem)newItem("ezReqInputTime_ST");
		ezReqInputTime_P1 = (EZDBStringItemArray)newItemArray("ezReqInputTime_P1");
		xxHrsMn_P1 = (EZDBStringItemArray)newItemArray("xxHrsMn_P1");
		ezReqInputDate_EN = (EZDBDateItem)newItem("ezReqInputDate_EN");
		ezReqInputTime_EN = (EZDBStringItem)newItem("ezReqInputTime_EN");
		ezReqInputTime_P2 = (EZDBStringItemArray)newItemArray("ezReqInputTime_P2");
		xxHrsMn_P2 = (EZDBStringItemArray)newItemArray("xxHrsMn_P2");
		ezReqJobStartDate = (EZDBDateItem)newItem("ezReqJobStartDate");
		ezReqJobStartTime = (EZDBStringItem)newItem("ezReqJobStartTime");
		ezReqJobStartTime_P3 = (EZDBStringItemArray)newItemArray("ezReqJobStartTime_P3");
		xxHrsMn_P3 = (EZDBStringItemArray)newItemArray("xxHrsMn_P3");
		ezReqJobEndDate = (EZDBDateItem)newItem("ezReqJobEndDate");
		ezReqJobEndTime = (EZDBStringItem)newItem("ezReqJobEndTime");
		ezReqJobEndTime_P4 = (EZDBStringItemArray)newItemArray("ezReqJobEndTime_P4");
		xxHrsMn_P4 = (EZDBStringItemArray)newItemArray("xxHrsMn_P4");
		A = (business.servlet.ZZBL0030.ZZBL0030_ABMsgArray)newMsgArray("A");
		xxScrNm = (EZDBStringItem)newItem("xxScrNm");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxLastBtnNm = (EZDBStringItem)newItem("xxLastBtnNm");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZBL0030BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZBL0030BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezReqBusinessName", "ezReqBusinessName", null, null, TYPE_HANKAKUEISU, "40", null},
	{"ezReqJobStatus", "ezReqJobStatus", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ezReqJobStatus_DP", "ezReqJobStatus_DP", "DP", "6", TYPE_HANKAKUEISU, "1", null},
	{"xxBatProcTrmNm_DP", "xxBatProcTrmNm_DP", "DP", "6", TYPE_HANKAKUEISU, "13", null},
	{"ezReqInputDate_ST", "ezReqInputDate_ST", "ST", null, TYPE_NENTSUKIHI, "8", null},
	{"ezReqInputTime_ST", "ezReqInputTime_ST", "ST", null, TYPE_HANKAKUEISU, "9", null},
	{"ezReqInputTime_P1", "ezReqInputTime_P1", "P1", "24", TYPE_HANKAKUEISU, "9", null},
	{"xxHrsMn_P1", "xxHrsMn_P1", "P1", "24", TYPE_HANKAKUEISU, "5", null},
	{"ezReqInputDate_EN", "ezReqInputDate_EN", "EN", null, TYPE_NENTSUKIHI, "8", null},
	{"ezReqInputTime_EN", "ezReqInputTime_EN", "EN", null, TYPE_HANKAKUEISU, "9", null},
	{"ezReqInputTime_P2", "ezReqInputTime_P2", "P2", "24", TYPE_HANKAKUEISU, "9", null},
	{"xxHrsMn_P2", "xxHrsMn_P2", "P2", "24", TYPE_HANKAKUEISU, "5", null},
	{"ezReqJobStartDate", "ezReqJobStartDate", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ezReqJobStartTime", "ezReqJobStartTime", null, null, TYPE_HANKAKUEISU, "9", null},
	{"ezReqJobStartTime_P3", "ezReqJobStartTime_P3", "P3", "24", TYPE_HANKAKUEISU, "9", null},
	{"xxHrsMn_P3", "xxHrsMn_P3", "P3", "24", TYPE_HANKAKUEISU, "5", null},
	{"ezReqJobEndDate", "ezReqJobEndDate", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ezReqJobEndTime", "ezReqJobEndTime", null, null, TYPE_HANKAKUEISU, "9", null},
	{"ezReqJobEndTime_P4", "ezReqJobEndTime_P4", "P4", "24", TYPE_HANKAKUEISU, "9", null},
	{"xxHrsMn_P4", "xxHrsMn_P4", "P4", "24", TYPE_HANKAKUEISU, "5", null},
	{"A", "A", null, "40", "business.servlet.ZZBL0030.ZZBL0030_ABMsgArray", null, null},
	
	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxLastBtnNm", "xxLastBtnNm", null, null, TYPE_HANKAKUEISU, "13", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZREQBusinessApplicationName",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqBusinessName
        {"_EZREQProcessStatus",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobStatus
        {"_EZREQProcessStatus",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobStatus_DP
        {"XX_BAT_PROC_TRM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBatProcTrmNm_DP
        {"_EZREQRegisteredDate", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//ezReqInputDate_ST
        {"_EZREQRegisteredTime", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqInputTime_ST
        {"_EZREQRegisteredTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqInputTime_P1
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_P1
        {"_EZREQRegisteredDate", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//ezReqInputDate_EN
        {"_EZREQRegisteredTime", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqInputTime_EN
        {"_EZREQRegisteredTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqInputTime_P2
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_P2
        {"_EZREQProcessStartDate",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//ezReqJobStartDate
        {"_EZREQProcessStartTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobStartTime
        {"_EZREQProcessStartTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobStartTime_P3
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_P3
        {"_EZREQProcessCompleteDate",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//ezReqJobEndDate
        {"_EZREQProcessCompleteTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobEndTime
        {"_EZREQProcessCompleteTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobEndTime_P4
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_P4
		null,	//A
        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_LAST_BTN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLastBtnNm
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

