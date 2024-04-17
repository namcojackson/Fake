//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530171130000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2600_TCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2600 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2600_TCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_NM_T*/
	public final EZDCStringItem              orgNm_T;

    /** XX_QUERY_COND_TXT_T*/
	public final EZDCStringItem              xxQueryCondTxt_T;

    /** XX_PSN_NM_T*/
	public final EZDCStringItem              xxPsnNm_T;

    /** XX_FULL_NM_T*/
	public final EZDCStringItem              xxFullNm_T;

    /** ORG_FUNC_ROLE_TP_NM_T*/
	public final EZDCStringItem              orgFuncRoleTpNm_T;

    /** ORG_CD_T*/
	public final EZDCStringItem              orgCd_T;

    /** ORG_LAYER_NUM_T*/
	public final EZDCBigDecimalItem              orgLayerNum_T;

    /** ORG_TIER_CD_T*/
	public final EZDCStringItem              orgTierCd_T;

    /** PSN_NUM_T*/
	public final EZDCStringItem              psnNum_T;

    /** PSN_CD_T*/
	public final EZDCStringItem              psnCd_T;

    /** BIZ_AREA_ORG_NM_T*/
	public final EZDCStringItem              bizAreaOrgNm_T;

    /** ORG_NM_0*/
	public final EZDCStringItem              orgNm_0;

    /** ORG_NM_1*/
	public final EZDCStringItem              orgNm_1;

    /** ORG_NM_2*/
	public final EZDCStringItem              orgNm_2;

    /** ORG_NM_3*/
	public final EZDCStringItem              orgNm_3;

    /** ORG_NM_4*/
	public final EZDCStringItem              orgNm_4;

    /** ORG_NM_5*/
	public final EZDCStringItem              orgNm_5;

    /** ORG_NM_6*/
	public final EZDCStringItem              orgNm_6;

    /** ORG_NM_7*/
	public final EZDCStringItem              orgNm_7;

    /** ORG_NM_8*/
	public final EZDCStringItem              orgNm_8;

    /** ORG_NM_9*/
	public final EZDCStringItem              orgNm_9;

    /** ORG_NM_10*/
	public final EZDCStringItem              orgNm_10;


	/**
	 * NMAL2600_TCMsg is constructor.
	 * The initialization when the instance of NMAL2600_TCMsg is generated.
	 */
	public NMAL2600_TCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2600_TCMsg is constructor.
	 * The initialization when the instance of NMAL2600_TCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2600_TCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgNm_T = (EZDCStringItem)newItem("orgNm_T");
		xxQueryCondTxt_T = (EZDCStringItem)newItem("xxQueryCondTxt_T");
		xxPsnNm_T = (EZDCStringItem)newItem("xxPsnNm_T");
		xxFullNm_T = (EZDCStringItem)newItem("xxFullNm_T");
		orgFuncRoleTpNm_T = (EZDCStringItem)newItem("orgFuncRoleTpNm_T");
		orgCd_T = (EZDCStringItem)newItem("orgCd_T");
		orgLayerNum_T = (EZDCBigDecimalItem)newItem("orgLayerNum_T");
		orgTierCd_T = (EZDCStringItem)newItem("orgTierCd_T");
		psnNum_T = (EZDCStringItem)newItem("psnNum_T");
		psnCd_T = (EZDCStringItem)newItem("psnCd_T");
		bizAreaOrgNm_T = (EZDCStringItem)newItem("bizAreaOrgNm_T");
		orgNm_0 = (EZDCStringItem)newItem("orgNm_0");
		orgNm_1 = (EZDCStringItem)newItem("orgNm_1");
		orgNm_2 = (EZDCStringItem)newItem("orgNm_2");
		orgNm_3 = (EZDCStringItem)newItem("orgNm_3");
		orgNm_4 = (EZDCStringItem)newItem("orgNm_4");
		orgNm_5 = (EZDCStringItem)newItem("orgNm_5");
		orgNm_6 = (EZDCStringItem)newItem("orgNm_6");
		orgNm_7 = (EZDCStringItem)newItem("orgNm_7");
		orgNm_8 = (EZDCStringItem)newItem("orgNm_8");
		orgNm_9 = (EZDCStringItem)newItem("orgNm_9");
		orgNm_10 = (EZDCStringItem)newItem("orgNm_10");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2600_TCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2600_TCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgNm_T", "orgNm_T", "T", null, TYPE_HANKAKUEISU, "50", null},
	{"xxQueryCondTxt_T", "xxQueryCondTxt_T", "T", null, TYPE_HANKAKUEISU, "256", null},
	{"xxPsnNm_T", "xxPsnNm_T", "T", null, TYPE_HANKAKUEISU, "62", null},
	{"xxFullNm_T", "xxFullNm_T", "T", null, TYPE_HANKAKUEISU, "100", null},
	{"orgFuncRoleTpNm_T", "orgFuncRoleTpNm_T", "T", null, TYPE_HANKAKUEISU, "50", null},
	{"orgCd_T", "orgCd_T", "T", null, TYPE_HANKAKUEISU, "8", null},
	{"orgLayerNum_T", "orgLayerNum_T", "T", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"orgTierCd_T", "orgTierCd_T", "T", null, TYPE_HANKAKUEISU, "2", null},
	{"psnNum_T", "psnNum_T", "T", null, TYPE_HANKAKUEISU, "50", null},
	{"psnCd_T", "psnCd_T", "T", null, TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgNm_T", "bizAreaOrgNm_T", "T", null, TYPE_HANKAKUEISU, "70", null},
	{"orgNm_0", "orgNm_0", "0", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_1", "orgNm_1", "1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_2", "orgNm_2", "2", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_3", "orgNm_3", "3", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_4", "orgNm_4", "4", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_5", "orgNm_5", "5", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_6", "orgNm_6", "6", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_7", "orgNm_7", "7", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_8", "orgNm_8", "8", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_9", "orgNm_9", "9", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_10", "orgNm_10", "10", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_T
        {"XX_QUERY_COND_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQueryCondTxt_T
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_T
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_T
        {"ORG_FUNC_ROLE_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpNm_T
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_T
        {"ORG_LAYER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgLayerNum_T
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_T
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_T
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_T
        {"BIZ_AREA_ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgNm_T
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_0
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_2
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_3
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_4
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_5
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_6
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_7
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_8
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_9
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_10
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

