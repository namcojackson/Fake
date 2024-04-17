//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230705160706000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3140_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3140 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3140_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_WH_COND_PK_A*/
	public final EZDBBigDecimalItem              mdseWhCondPk_A;

    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** LINE_BIZ_TP_CD_A*/
	public final EZDBStringItem              lineBizTpCd_A;

    /** DS_ORD_CATG_CD_A*/
	public final EZDBStringItem              dsOrdCatgCd_A;

    /** DS_ORD_CATG_DESC_TXT_A*/
	public final EZDBStringItem              dsOrdCatgDescTxt_A;

    /** RTL_WH_CD_A*/
	public final EZDBStringItem              rtlWhCd_A;

    /** RTL_WH_NM_A*/
	public final EZDBStringItem              rtlWhNm_A;

    /** RTL_SWH_CD_A*/
	public final EZDBStringItem              rtlSwhCd_A;

    /** RTL_SWH_NM_A*/
	public final EZDBStringItem              rtlSwhNm_A;

    /** HARD_ALLOC_TP_CD_A*/
	public final EZDBStringItem              hardAllocTpCd_A;

    /** TM_FENCE_DAYS_AOT_A*/
	public final EZDBBigDecimalItem              tmFenceDaysAot_A;

    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;

    /** COA_PROD_CD_A*/
	public final EZDBStringItem              coaProdCd_A;

    /** COA_PROD_NM_A*/
	public final EZDBStringItem              coaProdNm_A;


	/**
	 * NLBL3140_ABMsg is constructor.
	 * The initialization when the instance of NLBL3140_ABMsg is generated.
	 */
	public NLBL3140_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3140_ABMsg is constructor.
	 * The initialization when the instance of NLBL3140_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3140_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseWhCondPk_A = (EZDBBigDecimalItem)newItem("mdseWhCondPk_A");
		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		lineBizTpCd_A = (EZDBStringItem)newItem("lineBizTpCd_A");
		dsOrdCatgCd_A = (EZDBStringItem)newItem("dsOrdCatgCd_A");
		dsOrdCatgDescTxt_A = (EZDBStringItem)newItem("dsOrdCatgDescTxt_A");
		rtlWhCd_A = (EZDBStringItem)newItem("rtlWhCd_A");
		rtlWhNm_A = (EZDBStringItem)newItem("rtlWhNm_A");
		rtlSwhCd_A = (EZDBStringItem)newItem("rtlSwhCd_A");
		rtlSwhNm_A = (EZDBStringItem)newItem("rtlSwhNm_A");
		hardAllocTpCd_A = (EZDBStringItem)newItem("hardAllocTpCd_A");
		tmFenceDaysAot_A = (EZDBBigDecimalItem)newItem("tmFenceDaysAot_A");
		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
		coaProdCd_A = (EZDBStringItem)newItem("coaProdCd_A");
		coaProdNm_A = (EZDBStringItem)newItem("coaProdNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3140_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3140_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseWhCondPk_A", "mdseWhCondPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"lineBizTpCd_A", "lineBizTpCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdCatgCd_A", "dsOrdCatgCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgDescTxt_A", "dsOrdCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhCd_A", "rtlWhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A", "rtlWhNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_A", "rtlSwhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A", "rtlSwhNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"hardAllocTpCd_A", "hardAllocTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"tmFenceDaysAot_A", "tmFenceDaysAot_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"coaProdCd_A", "coaProdCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdNm_A", "coaProdNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_WH_COND_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseWhCondPk_A
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"LINE_BIZ_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_A
        {"DS_ORD_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd_A
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_A
        {"RTL_WH_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A
        {"RTL_WH_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A
        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A
        {"HARD_ALLOC_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hardAllocTpCd_A
        {"TM_FENCE_DAYS_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tmFenceDaysAot_A
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"COA_PROD_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_A
        {"COA_PROD_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdNm_A
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
