//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230427160329000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1700_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1700 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1700_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** LINE_PROC_DFN_SORT_NUM_A*/
	public final EZDBBigDecimalItem              lineProcDfnSortNum_A;

    /** DS_ORD_LINE_CATG_CD_A*/
	public final EZDBStringItem              dsOrdLineCatgCd_A;

    /** ORD_PROC_TP_CD_A*/
	public final EZDBStringItem              ordProcTpCd_A;

    /** PRIM_LINE_CATG_FLG_A*/
	public final EZDBStringItem              primLineCatgFlg_A;

    /** RMA_PRIM_LINE_CATG_FLG_A*/
	public final EZDBStringItem              rmaPrimLineCatgFlg_A;

    /** AJE_ACCT_BAT_CD_A*/
	public final EZDBStringItem              ajeAcctBatCd_A;

    /** AJE_ACCT_BAT_DESC_TXT_A*/
	public final EZDBStringItem              ajeAcctBatDescTxt_A;

    /** DS_ORD_LINE_DRCTN_CD_A*/
	public final EZDBStringItem              dsOrdLineDrctnCd_A;

    /** DS_ORD_LINE_DRCTN_NM_A*/
	public final EZDBStringItem              dsOrdLineDrctnNm_A;

    /** EFF_FROM_DT_A*/
	public final EZDBDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDBDateItem              effThruDt_A;

    /** REV_FCST_CD_A*/
	public final EZDBStringItem              revFcstCd_A;

    /** ACTV_FLG_A*/
	public final EZDBStringItem              actvFlg_A;

    /** XX_ROW_ID_A*/
	public final EZDBStringItem              xxRowId_A;

    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;


	/**
	 * NWAL1700_ABMsg is constructor.
	 * The initialization when the instance of NWAL1700_ABMsg is generated.
	 */
	public NWAL1700_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1700_ABMsg is constructor.
	 * The initialization when the instance of NWAL1700_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1700_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		lineProcDfnSortNum_A = (EZDBBigDecimalItem)newItem("lineProcDfnSortNum_A");
		dsOrdLineCatgCd_A = (EZDBStringItem)newItem("dsOrdLineCatgCd_A");
		ordProcTpCd_A = (EZDBStringItem)newItem("ordProcTpCd_A");
		primLineCatgFlg_A = (EZDBStringItem)newItem("primLineCatgFlg_A");
		rmaPrimLineCatgFlg_A = (EZDBStringItem)newItem("rmaPrimLineCatgFlg_A");
		ajeAcctBatCd_A = (EZDBStringItem)newItem("ajeAcctBatCd_A");
		ajeAcctBatDescTxt_A = (EZDBStringItem)newItem("ajeAcctBatDescTxt_A");
		dsOrdLineDrctnCd_A = (EZDBStringItem)newItem("dsOrdLineDrctnCd_A");
		dsOrdLineDrctnNm_A = (EZDBStringItem)newItem("dsOrdLineDrctnNm_A");
		effFromDt_A = (EZDBDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDBDateItem)newItem("effThruDt_A");
		revFcstCd_A = (EZDBStringItem)newItem("revFcstCd_A");
		actvFlg_A = (EZDBStringItem)newItem("actvFlg_A");
		xxRowId_A = (EZDBStringItem)newItem("xxRowId_A");
		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1700_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1700_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"lineProcDfnSortNum_A", "lineProcDfnSortNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"dsOrdLineCatgCd_A", "dsOrdLineCatgCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"ordProcTpCd_A", "ordProcTpCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"primLineCatgFlg_A", "primLineCatgFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"rmaPrimLineCatgFlg_A", "rmaPrimLineCatgFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"ajeAcctBatCd_A", "ajeAcctBatCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"ajeAcctBatDescTxt_A", "ajeAcctBatDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdLineDrctnCd_A", "dsOrdLineDrctnCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"dsOrdLineDrctnNm_A", "dsOrdLineDrctnNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"revFcstCd_A", "revFcstCd_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"actvFlg_A", "actvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRowId_A", "xxRowId_A", "A", null, TYPE_HANKAKUEISU, "18", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"LINE_PROC_DFN_SORT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineProcDfnSortNum_A
        {"DS_ORD_LINE_CATG_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd_A
        {"ORD_PROC_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordProcTpCd_A
        {"PRIM_LINE_CATG_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//primLineCatgFlg_A
        {"RMA_PRIM_LINE_CATG_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmaPrimLineCatgFlg_A
        {"AJE_ACCT_BAT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeAcctBatCd_A
        {"AJE_ACCT_BAT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeAcctBatDescTxt_A
        {"DS_ORD_LINE_DRCTN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineDrctnCd_A
        {"DS_ORD_LINE_DRCTN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineDrctnNm_A
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A
        {"REV_FCST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//revFcstCd_A
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A
        {"XX_ROW_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowId_A
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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

