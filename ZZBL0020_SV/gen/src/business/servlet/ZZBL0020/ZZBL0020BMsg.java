//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20140618142511000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZBL0020BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZBL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZBL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZBL0020BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZREQBusinessApplicationID*/
	public final EZDBStringItem              ezReqBusinessID;

    /** _EZREQExecutionControlNetID*/
	public final EZDBStringItem              ezReqJobCtlNetID;

    /** A*/
	public final business.servlet.ZZBL0020.ZZBL0020_ABMsgArray              A;

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

    /** _EZREQBusinessApplicationID_B*/
	public final EZDBStringItem              ezReqBusinessID_B;

    /** _EZREQBusinessApplicationName_B*/
	public final EZDBStringItem              ezReqBusinessName_B;

    /** _EZREQExecutionControlNetID_B*/
	public final EZDBStringItem              ezReqJobCtlNetID_B;

    /** _EZREQJobConcurrency_B*/
	public final EZDBStringItem              ezReqJobConcurrency_B;

    /** _EZREQJobConcurrency_P1*/
	public final EZDBStringItemArray              ezReqJobConcurrency_P1;

    /** XX_JOB_CNCR_CD_P1*/
	public final EZDBStringItemArray              xxJobCncrCd_P1;

    /** _EZREQJobBlockingFlag_B*/
	public final EZDBStringItem              ezReqJobStopFlag_B;

    /** _EZREQJobBlockingFlag_P2*/
	public final EZDBStringItemArray              ezReqJobStopFlag_P2;

    /** XX_JOB_BLOCK_FLG_NM_P2*/
	public final EZDBStringItemArray              xxJobBlockFlgNm_P2;

    /** _EZREQExecutionControlFlaginError_B*/
	public final EZDBStringItem              ezReqJobErrorCtlFlag_B;

    /** _EZREQExecutionControlFlaginError_P3*/
	public final EZDBStringItemArray              ezReqJobErrorCtlFlag_P3;

    /** XX_JOB_ERR_CTRL_FLG_NM_P3*/
	public final EZDBStringItemArray              xxJobErrCtrlFlgNm_P3;

    /** _EZREQExtractionCount_B*/
	public final EZDBBigDecimalItem              ezReqCountPerJob_B;

    /** ACTV_FLG_B*/
	public final EZDBStringItem              actvFlg_B;

    /** XX_LAST_BTN_NM*/
	public final EZDBStringItem              xxLastBtnNm;


	/**
	 * ZZBL0020BMsg is constructor.
	 * The initialization when the instance of ZZBL0020BMsg is generated.
	 */
	public ZZBL0020BMsg() {
		this(false, -1);
	}

	/**
	 * ZZBL0020BMsg is constructor.
	 * The initialization when the instance of ZZBL0020BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZBL0020BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezReqBusinessID = (EZDBStringItem)newItem("ezReqBusinessID");
		ezReqJobCtlNetID = (EZDBStringItem)newItem("ezReqJobCtlNetID");
		A = (business.servlet.ZZBL0020.ZZBL0020_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		ezReqBusinessID_B = (EZDBStringItem)newItem("ezReqBusinessID_B");
		ezReqBusinessName_B = (EZDBStringItem)newItem("ezReqBusinessName_B");
		ezReqJobCtlNetID_B = (EZDBStringItem)newItem("ezReqJobCtlNetID_B");
		ezReqJobConcurrency_B = (EZDBStringItem)newItem("ezReqJobConcurrency_B");
		ezReqJobConcurrency_P1 = (EZDBStringItemArray)newItemArray("ezReqJobConcurrency_P1");
		xxJobCncrCd_P1 = (EZDBStringItemArray)newItemArray("xxJobCncrCd_P1");
		ezReqJobStopFlag_B = (EZDBStringItem)newItem("ezReqJobStopFlag_B");
		ezReqJobStopFlag_P2 = (EZDBStringItemArray)newItemArray("ezReqJobStopFlag_P2");
		xxJobBlockFlgNm_P2 = (EZDBStringItemArray)newItemArray("xxJobBlockFlgNm_P2");
		ezReqJobErrorCtlFlag_B = (EZDBStringItem)newItem("ezReqJobErrorCtlFlag_B");
		ezReqJobErrorCtlFlag_P3 = (EZDBStringItemArray)newItemArray("ezReqJobErrorCtlFlag_P3");
		xxJobErrCtrlFlgNm_P3 = (EZDBStringItemArray)newItemArray("xxJobErrCtrlFlgNm_P3");
		ezReqCountPerJob_B = (EZDBBigDecimalItem)newItem("ezReqCountPerJob_B");
		actvFlg_B = (EZDBStringItem)newItem("actvFlg_B");
		xxLastBtnNm = (EZDBStringItem)newItem("xxLastBtnNm");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZBL0020BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZBL0020BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezReqBusinessID", "ezReqBusinessID", null, null, TYPE_HANKAKUEISU, "16", null},
	{"ezReqJobCtlNetID", "ezReqJobCtlNetID", null, null, TYPE_HANKAKUEISU, "16", null},
	{"A", "A", null, "40", "business.servlet.ZZBL0020.ZZBL0020_ABMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"ezReqBusinessID_B", "ezReqBusinessID_B", "B", null, TYPE_HANKAKUEISU, "16", null},
	{"ezReqBusinessName_B", "ezReqBusinessName_B", "B", null, TYPE_HANKAKUEISU, "40", null},
	{"ezReqJobCtlNetID_B", "ezReqJobCtlNetID_B", "B", null, TYPE_HANKAKUEISU, "16", null},
	{"ezReqJobConcurrency_B", "ezReqJobConcurrency_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"ezReqJobConcurrency_P1", "ezReqJobConcurrency_P1", "P1", "35", TYPE_HANKAKUEISU, "1", null},
	{"xxJobCncrCd_P1", "xxJobCncrCd_P1", "P1", "35", TYPE_HANKAKUEISU, "1", null},
	{"ezReqJobStopFlag_B", "ezReqJobStopFlag_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"ezReqJobStopFlag_P2", "ezReqJobStopFlag_P2", "P2", "2", TYPE_HANKAKUEISU, "1", null},
	{"xxJobBlockFlgNm_P2", "xxJobBlockFlgNm_P2", "P2", "2", TYPE_HANKAKUEIJI, "3", null},
	{"ezReqJobErrorCtlFlag_B", "ezReqJobErrorCtlFlag_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"ezReqJobErrorCtlFlag_P3", "ezReqJobErrorCtlFlag_P3", "P3", "2", TYPE_HANKAKUEISU, "1", null},
	{"xxJobErrCtrlFlgNm_P3", "xxJobErrCtrlFlgNm_P3", "P3", "2", TYPE_HANKAKUEIJI, "8", null},
	{"ezReqCountPerJob_B", "ezReqCountPerJob_B", "B", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"actvFlg_B", "actvFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"xxLastBtnNm", "xxLastBtnNm", null, null, TYPE_HANKAKUEISU, "13", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZREQBusinessApplicationID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqBusinessID
        {"_EZREQExecutionControlNetID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobCtlNetID
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"_EZREQBusinessApplicationID", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqBusinessID_B
        {"_EZREQBusinessApplicationName",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqBusinessName_B
        {"_EZREQExecutionControlNetID", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobCtlNetID_B
        {"_EZREQJobConcurrency",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobConcurrency_B
        {"_EZREQJobConcurrency",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobConcurrency_P1
        {"XX_JOB_CNCR_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxJobCncrCd_P1
        {"_EZREQJobBlockingFlag",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobStopFlag_B
        {"_EZREQJobBlockingFlag",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobStopFlag_P2
        {"XX_JOB_BLOCK_FLG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxJobBlockFlgNm_P2
        {"_EZREQExecutionControlFlaginError",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobErrorCtlFlag_B
        {"_EZREQExecutionControlFlaginError",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobErrorCtlFlag_P3
        {"XX_JOB_ERR_CTRL_FLG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxJobErrCtrlFlgNm_P3
        {"_EZREQExtractionCount", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqCountPerJob_B
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_B
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

