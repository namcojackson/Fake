//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230710182104000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3140_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3140 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3140_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_WH_COND_PK_B*/
	public final EZDSBigDecimalItem              mdseWhCondPk_B;

    /** XX_CHK_BOX_B*/
	public final EZDSStringItem              xxChkBox_B;

    /** LINE_BIZ_TP_CD_B*/
	public final EZDSStringItem              lineBizTpCd_B;

    /** DS_ORD_CATG_CD_B*/
	public final EZDSStringItem              dsOrdCatgCd_B;

    /** DS_ORD_CATG_DESC_TXT_B*/
	public final EZDSStringItem              dsOrdCatgDescTxt_B;

    /** RTL_WH_CD_B*/
	public final EZDSStringItem              rtlWhCd_B;

    /** RTL_WH_NM_B*/
	public final EZDSStringItem              rtlWhNm_B;

    /** RTL_SWH_CD_B*/
	public final EZDSStringItem              rtlSwhCd_B;

    /** RTL_SWH_NM_B*/
	public final EZDSStringItem              rtlSwhNm_B;

    /** HARD_ALLOC_TP_CD_B*/
	public final EZDSStringItem              hardAllocTpCd_B;

    /** TM_FENCE_DAYS_AOT_B*/
	public final EZDSBigDecimalItem              tmFenceDaysAot_B;

    /** _EZUpdateDateTime_B*/
	public final EZDSStringItem              ezUpTime_B;

    /** _EZUpTimeZone_B*/
	public final EZDSStringItem              ezUpTimeZone_B;

    /** COA_PROD_CD_B*/
	public final EZDSStringItem              coaProdCd_B;

    /** COA_PROD_NM_B*/
	public final EZDSStringItem              coaProdNm_B;


	/**
	 * NLBL3140_BSMsg is constructor.
	 * The initialization when the instance of NLBL3140_BSMsg is generated.
	 */
	public NLBL3140_BSMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3140_BSMsg is constructor.
	 * The initialization when the instance of NLBL3140_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3140_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseWhCondPk_B = (EZDSBigDecimalItem)newItem("mdseWhCondPk_B");
		xxChkBox_B = (EZDSStringItem)newItem("xxChkBox_B");
		lineBizTpCd_B = (EZDSStringItem)newItem("lineBizTpCd_B");
		dsOrdCatgCd_B = (EZDSStringItem)newItem("dsOrdCatgCd_B");
		dsOrdCatgDescTxt_B = (EZDSStringItem)newItem("dsOrdCatgDescTxt_B");
		rtlWhCd_B = (EZDSStringItem)newItem("rtlWhCd_B");
		rtlWhNm_B = (EZDSStringItem)newItem("rtlWhNm_B");
		rtlSwhCd_B = (EZDSStringItem)newItem("rtlSwhCd_B");
		rtlSwhNm_B = (EZDSStringItem)newItem("rtlSwhNm_B");
		hardAllocTpCd_B = (EZDSStringItem)newItem("hardAllocTpCd_B");
		tmFenceDaysAot_B = (EZDSBigDecimalItem)newItem("tmFenceDaysAot_B");
		ezUpTime_B = (EZDSStringItem)newItem("ezUpTime_B");
		ezUpTimeZone_B = (EZDSStringItem)newItem("ezUpTimeZone_B");
		coaProdCd_B = (EZDSStringItem)newItem("coaProdCd_B");
		coaProdNm_B = (EZDSStringItem)newItem("coaProdNm_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3140_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3140_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseWhCondPk_B", "mdseWhCondPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"lineBizTpCd_B", "lineBizTpCd_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdCatgCd_B", "dsOrdCatgCd_B", "B", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgDescTxt_B", "dsOrdCatgDescTxt_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhCd_B", "rtlWhCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_B", "rtlWhNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_B", "rtlSwhCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_B", "rtlSwhNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"hardAllocTpCd_B", "hardAllocTpCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"tmFenceDaysAot_B", "tmFenceDaysAot_B", "B", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"ezUpTime_B", "ezUpTime_B", "B", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B", "ezUpTimeZone_B", "B", null, TYPE_HANKAKUEISU, "5", null},
	{"coaProdCd_B", "coaProdCd_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdNm_B", "coaProdNm_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_WH_COND_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseWhCondPk_B
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_B
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd_B
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_B
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_B
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_B
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_B
        {"HARD_ALLOC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hardAllocTpCd_B
        {"TM_FENCE_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tmFenceDaysAot_B
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_B
        {"COA_PROD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdNm_B
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

