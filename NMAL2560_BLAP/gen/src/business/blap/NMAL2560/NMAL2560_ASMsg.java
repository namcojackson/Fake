//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530160127000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2560_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2560;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2560 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2560_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_ID_A*/
	public final EZDSStringItem              xxRowId_A;

    /** XX_CHK_BOX_A*/
	public final EZDSStringItem              xxChkBox_A;

    /** BIZ_AREA_ORG_CD_A*/
	public final EZDSStringItem              bizAreaOrgCd_A;

    /** ORG_HRCH_STRU_DFN_NM_A*/
	public final EZDSStringItem              orgHrchStruDfnNm_A;

    /** EFF_FROM_DT_A*/
	public final EZDSDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDSDateItem              effThruDt_A;

    /** STRU_DFN_CD_1*/
	public final EZDSStringItem              struDfnCd_1;

    /** STRU_DFN_CD_2*/
	public final EZDSStringItem              struDfnCd_2;

    /** STRU_DFN_CD_3*/
	public final EZDSStringItem              struDfnCd_3;

    /** STRU_DFN_CD_4*/
	public final EZDSStringItem              struDfnCd_4;

    /** STRU_DFN_CD_5*/
	public final EZDSStringItem              struDfnCd_5;

    /** STRU_DFN_CD_6*/
	public final EZDSStringItem              struDfnCd_6;

    /** STRU_DFN_CD_7*/
	public final EZDSStringItem              struDfnCd_7;

    /** STRU_DFN_CD_8*/
	public final EZDSStringItem              struDfnCd_8;

    /** STRU_DFN_CD_9*/
	public final EZDSStringItem              struDfnCd_9;

    /** STRU_DFN_CD_10*/
	public final EZDSStringItem              struDfnCd_10;

    /** XX_CHK_MAX_DESC_TXT_IN*/
	public final EZDSStringItem              xxChkMaxDescTxt_IN;

    /** XX_DT_TM_IN*/
	public final EZDSStringItem              xxDtTm_IN;

    /** XX_CHK_MAX_DESC_TXT_UP*/
	public final EZDSStringItem              xxChkMaxDescTxt_UP;

    /** XX_DT_TM_UP*/
	public final EZDSStringItem              xxDtTm_UP;

    /** ORG_HRCH_STRU_DFN_PK_1*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_1;

    /** ORG_HRCH_STRU_DFN_PK_2*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_2;

    /** ORG_HRCH_STRU_DFN_PK_3*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_3;

    /** ORG_HRCH_STRU_DFN_PK_4*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_4;

    /** ORG_HRCH_STRU_DFN_PK_5*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_5;

    /** ORG_HRCH_STRU_DFN_PK_6*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_6;

    /** ORG_HRCH_STRU_DFN_PK_7*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_7;

    /** ORG_HRCH_STRU_DFN_PK_8*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_8;

    /** ORG_HRCH_STRU_DFN_PK_9*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_9;

    /** ORG_HRCH_STRU_DFN_PK_10*/
	public final EZDSBigDecimalItem              orgHrchStruDfnPk_10;

    /** _EZUpdateDateTime_A*/
	public final EZDSStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDSStringItem              ezUpTimeZone_A;


	/**
	 * NMAL2560_ASMsg is constructor.
	 * The initialization when the instance of NMAL2560_ASMsg is generated.
	 */
	public NMAL2560_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2560_ASMsg is constructor.
	 * The initialization when the instance of NMAL2560_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2560_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowId_A = (EZDSStringItem)newItem("xxRowId_A");
		xxChkBox_A = (EZDSStringItem)newItem("xxChkBox_A");
		bizAreaOrgCd_A = (EZDSStringItem)newItem("bizAreaOrgCd_A");
		orgHrchStruDfnNm_A = (EZDSStringItem)newItem("orgHrchStruDfnNm_A");
		effFromDt_A = (EZDSDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDSDateItem)newItem("effThruDt_A");
		struDfnCd_1 = (EZDSStringItem)newItem("struDfnCd_1");
		struDfnCd_2 = (EZDSStringItem)newItem("struDfnCd_2");
		struDfnCd_3 = (EZDSStringItem)newItem("struDfnCd_3");
		struDfnCd_4 = (EZDSStringItem)newItem("struDfnCd_4");
		struDfnCd_5 = (EZDSStringItem)newItem("struDfnCd_5");
		struDfnCd_6 = (EZDSStringItem)newItem("struDfnCd_6");
		struDfnCd_7 = (EZDSStringItem)newItem("struDfnCd_7");
		struDfnCd_8 = (EZDSStringItem)newItem("struDfnCd_8");
		struDfnCd_9 = (EZDSStringItem)newItem("struDfnCd_9");
		struDfnCd_10 = (EZDSStringItem)newItem("struDfnCd_10");
		xxChkMaxDescTxt_IN = (EZDSStringItem)newItem("xxChkMaxDescTxt_IN");
		xxDtTm_IN = (EZDSStringItem)newItem("xxDtTm_IN");
		xxChkMaxDescTxt_UP = (EZDSStringItem)newItem("xxChkMaxDescTxt_UP");
		xxDtTm_UP = (EZDSStringItem)newItem("xxDtTm_UP");
		orgHrchStruDfnPk_1 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_1");
		orgHrchStruDfnPk_2 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_2");
		orgHrchStruDfnPk_3 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_3");
		orgHrchStruDfnPk_4 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_4");
		orgHrchStruDfnPk_5 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_5");
		orgHrchStruDfnPk_6 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_6");
		orgHrchStruDfnPk_7 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_7");
		orgHrchStruDfnPk_8 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_8");
		orgHrchStruDfnPk_9 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_9");
		orgHrchStruDfnPk_10 = (EZDSBigDecimalItem)newItem("orgHrchStruDfnPk_10");
		ezUpTime_A = (EZDSStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDSStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2560_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2560_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowId_A", "xxRowId_A", "A", null, TYPE_HANKAKUEISU, "18", null},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"bizAreaOrgCd_A", "bizAreaOrgCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"orgHrchStruDfnNm_A", "orgHrchStruDfnNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"struDfnCd_1", "struDfnCd_1", "1", null, TYPE_HANKAKUEISU, "8", null},
	{"struDfnCd_2", "struDfnCd_2", "2", null, TYPE_HANKAKUEISU, "8", null},
	{"struDfnCd_3", "struDfnCd_3", "3", null, TYPE_HANKAKUEISU, "8", null},
	{"struDfnCd_4", "struDfnCd_4", "4", null, TYPE_HANKAKUEISU, "8", null},
	{"struDfnCd_5", "struDfnCd_5", "5", null, TYPE_HANKAKUEISU, "8", null},
	{"struDfnCd_6", "struDfnCd_6", "6", null, TYPE_HANKAKUEISU, "8", null},
	{"struDfnCd_7", "struDfnCd_7", "7", null, TYPE_HANKAKUEISU, "8", null},
	{"struDfnCd_8", "struDfnCd_8", "8", null, TYPE_HANKAKUEISU, "8", null},
	{"struDfnCd_9", "struDfnCd_9", "9", null, TYPE_HANKAKUEISU, "8", null},
	{"struDfnCd_10", "struDfnCd_10", "10", null, TYPE_HANKAKUEISU, "8", null},
	{"xxChkMaxDescTxt_IN", "xxChkMaxDescTxt_IN", "IN", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTm_IN", "xxDtTm_IN", "IN", null, TYPE_HANKAKUEISU, "23", null},
	{"xxChkMaxDescTxt_UP", "xxChkMaxDescTxt_UP", "UP", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTm_UP", "xxDtTm_UP", "UP", null, TYPE_HANKAKUEISU, "23", null},
	{"orgHrchStruDfnPk_1", "orgHrchStruDfnPk_1", "1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"orgHrchStruDfnPk_2", "orgHrchStruDfnPk_2", "2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"orgHrchStruDfnPk_3", "orgHrchStruDfnPk_3", "3", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"orgHrchStruDfnPk_4", "orgHrchStruDfnPk_4", "4", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"orgHrchStruDfnPk_5", "orgHrchStruDfnPk_5", "5", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"orgHrchStruDfnPk_6", "orgHrchStruDfnPk_6", "6", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"orgHrchStruDfnPk_7", "orgHrchStruDfnPk_7", "7", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"orgHrchStruDfnPk_8", "orgHrchStruDfnPk_8", "8", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"orgHrchStruDfnPk_9", "orgHrchStruDfnPk_9", "9", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"orgHrchStruDfnPk_10", "orgHrchStruDfnPk_10", "10", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowId_A
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_A
        {"ORG_HRCH_STRU_DFN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnNm_A
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_1
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_2
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_3
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_4
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_5
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_6
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_7
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_8
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_9
        {"STRU_DFN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnCd_10
        {"XX_CHK_MAX_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkMaxDescTxt_IN
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_IN
        {"XX_CHK_MAX_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkMaxDescTxt_UP
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_UP
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_1
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_2
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_3
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_4
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_5
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_6
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_7
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_8
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_9
        {"ORG_HRCH_STRU_DFN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgHrchStruDfnPk_10
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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

