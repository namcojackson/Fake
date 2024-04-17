//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170531092249000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2700CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2700 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2700CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** FIRST_ORG_CD*/
	public final EZDCStringItem              firstOrgCd;

    /** FIRST_ORG_CD_P*/
	public final EZDCStringItemArray              firstOrgCd_P;

    /** BIZ_AREA_ORG_NM_P*/
	public final EZDCStringItemArray              bizAreaOrgNm_P;

    /** ORG_FUNC_ROLE_TP_CD*/
	public final EZDCStringItem              orgFuncRoleTpCd;

    /** ORG_FUNC_ROLE_TP_NM*/
	public final EZDCStringItem              orgFuncRoleTpNm;

    /** ORG_FUNC_ROLE_TP_DESC_TXT*/
	public final EZDCStringItem              orgFuncRoleTpDescTxt;

    /** MGR_FLG*/
	public final EZDCStringItem              mgrFlg;

    /** SPCL_FLG*/
	public final EZDCStringItem              spclFlg;

    /** EQUIP_FLG*/
	public final EZDCStringItem              equipFlg;

    /** CMSN_FLG*/
	public final EZDCStringItem              cmsnFlg;

    /** ACTV_FLG*/
	public final EZDCStringItem              actvFlg;

    /** GES_TP_CD_P*/
	public final EZDCStringItemArray              gesTpCd_P;

    /** GES_TP_DESC_TXT_P*/
	public final EZDCStringItemArray              gesTpDescTxt_P;

    /** A*/
	public final business.blap.NMAL2700.NMAL2700_ACMsgArray              A;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** Z*/
	public final business.blap.NMAL2700.NMAL2700_ZCMsgArray              Z;


	/**
	 * NMAL2700CMsg is constructor.
	 * The initialization when the instance of NMAL2700CMsg is generated.
	 */
	public NMAL2700CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2700CMsg is constructor.
	 * The initialization when the instance of NMAL2700CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2700CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		firstOrgCd = (EZDCStringItem)newItem("firstOrgCd");
		firstOrgCd_P = (EZDCStringItemArray)newItemArray("firstOrgCd_P");
		bizAreaOrgNm_P = (EZDCStringItemArray)newItemArray("bizAreaOrgNm_P");
		orgFuncRoleTpCd = (EZDCStringItem)newItem("orgFuncRoleTpCd");
		orgFuncRoleTpNm = (EZDCStringItem)newItem("orgFuncRoleTpNm");
		orgFuncRoleTpDescTxt = (EZDCStringItem)newItem("orgFuncRoleTpDescTxt");
		mgrFlg = (EZDCStringItem)newItem("mgrFlg");
		spclFlg = (EZDCStringItem)newItem("spclFlg");
		equipFlg = (EZDCStringItem)newItem("equipFlg");
		cmsnFlg = (EZDCStringItem)newItem("cmsnFlg");
		actvFlg = (EZDCStringItem)newItem("actvFlg");
		gesTpCd_P = (EZDCStringItemArray)newItemArray("gesTpCd_P");
		gesTpDescTxt_P = (EZDCStringItemArray)newItemArray("gesTpDescTxt_P");
		A = (business.blap.NMAL2700.NMAL2700_ACMsgArray)newMsgArray("A");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		Z = (business.blap.NMAL2700.NMAL2700_ZCMsgArray)newMsgArray("Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2700CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2700CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"firstOrgCd", "firstOrgCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"firstOrgCd_P", "firstOrgCd_P", "P", "99", TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgNm_P", "bizAreaOrgNm_P", "P", "99", TYPE_HANKAKUEISU, "70", null},
	{"orgFuncRoleTpCd", "orgFuncRoleTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpNm", "orgFuncRoleTpNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"orgFuncRoleTpDescTxt", "orgFuncRoleTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mgrFlg", "mgrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"spclFlg", "spclFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"equipFlg", "equipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"cmsnFlg", "cmsnFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"actvFlg", "actvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"gesTpCd_P", "gesTpCd_P", "P", "99", TYPE_HANKAKUEISU, "5", null},
	{"gesTpDescTxt_P", "gesTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "200", "business.blap.NMAL2700.NMAL2700_ACMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"Z", "Z", null, "200", "business.blap.NMAL2700.NMAL2700_ZCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"FIRST_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstOrgCd
        {"FIRST_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstOrgCd_P
        {"BIZ_AREA_ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgNm_P
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd
        {"ORG_FUNC_ROLE_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpNm
        {"ORG_FUNC_ROLE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpDescTxt
        {"MGR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mgrFlg
        {"SPCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//spclFlg
        {"EQUIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//equipFlg
        {"CMSN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmsnFlg
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg
        {"GES_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gesTpCd_P
        {"GES_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gesTpDescTxt_P
		null,	//A
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//Z
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
