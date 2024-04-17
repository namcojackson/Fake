//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180704115006000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0390CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0390;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0390 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0390CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;

    /** SVC_MOD_YR*/
	public final EZDCDateItem              svcModYr;

    /** XX_DPLY_BY_CTRL_ITEM_CD_YY*/
	public final EZDCStringItemArray              xxDplyByCtrlItemCd_YY;

    /** XX_DPLY_BY_CTRL_ITEM_CD_NM_YY*/
	public final EZDCStringItemArray              xxDplyByCtrlItemCdNm_YY;

    /** SVC_MOD_MTH*/
	public final EZDCStringItem              svcModMth;

    /** SVC_MOD_PK*/
	public final EZDCBigDecimalItem              svcModPk;

    /** XX_DPLY_BY_CTRL_ITEM_CD_MM*/
	public final EZDCStringItemArray              xxDplyByCtrlItemCd_MM;

    /** XX_DPLY_BY_CTRL_ITEM_CD_NM_MM*/
	public final EZDCStringItemArray              xxDplyByCtrlItemCdNm_MM;

    /** SVC_MOD_DAY*/
	public final EZDCStringItem              svcModDay;

    /** XX_DPLY_BY_CTRL_ITEM_CD_DD*/
	public final EZDCStringItemArray              xxDplyByCtrlItemCd_DD;

    /** XX_DPLY_BY_CTRL_ITEM_CD_NM_DD*/
	public final EZDCStringItemArray              xxDplyByCtrlItemCdNm_DD;

    /** SVC_MNF_CD*/
	public final EZDCStringItem              svcMnfCd;

    /** SVC_MNF_CD_01*/
	public final EZDCStringItemArray              svcMnfCd_01;

    /** XX_DPLY_BY_CD_NM_CNCT_TXT_01*/
	public final EZDCStringItemArray              xxDplyByCdNmCnctTxt_01;

    /** SVC_MOD_SQ_NUM*/
	public final EZDCStringItem              svcModSqNum;

    /** SVC_MOD_PLN_ID*/
	public final EZDCStringItem              svcModPlnId;

    /** SVC_MOD_NM*/
	public final EZDCStringItem              svcModNm;


	/**
	 * NSBL0390CMsg is constructor.
	 * The initialization when the instance of NSBL0390CMsg is generated.
	 */
	public NSBL0390CMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0390CMsg is constructor.
	 * The initialization when the instance of NSBL0390CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0390CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
		svcModYr = (EZDCDateItem)newItem("svcModYr");
		xxDplyByCtrlItemCd_YY = (EZDCStringItemArray)newItemArray("xxDplyByCtrlItemCd_YY");
		xxDplyByCtrlItemCdNm_YY = (EZDCStringItemArray)newItemArray("xxDplyByCtrlItemCdNm_YY");
		svcModMth = (EZDCStringItem)newItem("svcModMth");
		svcModPk = (EZDCBigDecimalItem)newItem("svcModPk");
		xxDplyByCtrlItemCd_MM = (EZDCStringItemArray)newItemArray("xxDplyByCtrlItemCd_MM");
		xxDplyByCtrlItemCdNm_MM = (EZDCStringItemArray)newItemArray("xxDplyByCtrlItemCdNm_MM");
		svcModDay = (EZDCStringItem)newItem("svcModDay");
		xxDplyByCtrlItemCd_DD = (EZDCStringItemArray)newItemArray("xxDplyByCtrlItemCd_DD");
		xxDplyByCtrlItemCdNm_DD = (EZDCStringItemArray)newItemArray("xxDplyByCtrlItemCdNm_DD");
		svcMnfCd = (EZDCStringItem)newItem("svcMnfCd");
		svcMnfCd_01 = (EZDCStringItemArray)newItemArray("svcMnfCd_01");
		xxDplyByCdNmCnctTxt_01 = (EZDCStringItemArray)newItemArray("xxDplyByCdNmCnctTxt_01");
		svcModSqNum = (EZDCStringItem)newItem("svcModSqNum");
		svcModPlnId = (EZDCStringItem)newItem("svcModPlnId");
		svcModNm = (EZDCStringItem)newItem("svcModNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0390CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0390CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"svcModYr", "svcModYr", null, null, TYPE_NEN, "4", null},
	{"xxDplyByCtrlItemCd_YY", "xxDplyByCtrlItemCd_YY", "YY", "11", TYPE_HANKAKUEISU, "50", null},
	{"xxDplyByCtrlItemCdNm_YY", "xxDplyByCtrlItemCdNm_YY", "YY", "11", TYPE_HANKAKUEISU, "50", null},
	{"svcModMth", "svcModMth", null, null, TYPE_HANKAKUSUJI, "2", null},
	{"svcModPk", "svcModPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxDplyByCtrlItemCd_MM", "xxDplyByCtrlItemCd_MM", "MM", "12", TYPE_HANKAKUEISU, "50", null},
	{"xxDplyByCtrlItemCdNm_MM", "xxDplyByCtrlItemCdNm_MM", "MM", "12", TYPE_HANKAKUEISU, "50", null},
	{"svcModDay", "svcModDay", null, null, TYPE_HANKAKUSUJI, "2", null},
	{"xxDplyByCtrlItemCd_DD", "xxDplyByCtrlItemCd_DD", "DD", "31", TYPE_HANKAKUEISU, "50", null},
	{"xxDplyByCtrlItemCdNm_DD", "xxDplyByCtrlItemCdNm_DD", "DD", "31", TYPE_HANKAKUEISU, "50", null},
	{"svcMnfCd", "svcMnfCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcMnfCd_01", "svcMnfCd_01", "01", "99", TYPE_HANKAKUEISU, "2", null},
	{"xxDplyByCdNmCnctTxt_01", "xxDplyByCdNmCnctTxt_01", "01", "99", TYPE_HANKAKUEISU, "70", null},
	{"svcModSqNum", "svcModSqNum", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcModPlnId", "svcModPlnId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"svcModNm", "svcModNm", null, null, TYPE_HANKAKUEISU, "40", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"SVC_MOD_YR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModYr
        {"XX_DPLY_BY_CTRL_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCtrlItemCd_YY
        {"XX_DPLY_BY_CTRL_ITEM_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCtrlItemCdNm_YY
        {"SVC_MOD_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModMth
        {"SVC_MOD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModPk
        {"XX_DPLY_BY_CTRL_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCtrlItemCd_MM
        {"XX_DPLY_BY_CTRL_ITEM_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCtrlItemCdNm_MM
        {"SVC_MOD_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModDay
        {"XX_DPLY_BY_CTRL_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCtrlItemCd_DD
        {"XX_DPLY_BY_CTRL_ITEM_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCtrlItemCdNm_DD
        {"SVC_MNF_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMnfCd
        {"SVC_MNF_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMnfCd_01
        {"XX_DPLY_BY_CD_NM_CNCT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCdNmCnctTxt_01
        {"SVC_MOD_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModSqNum
        {"SVC_MOD_PLN_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModPlnId
        {"SVC_MOD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModNm
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

