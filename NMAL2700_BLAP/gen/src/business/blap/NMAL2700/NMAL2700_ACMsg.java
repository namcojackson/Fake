//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170531092249000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2700_ACMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NMAL2700_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** FIRST_ORG_CD_A*/
	public final EZDCStringItem              firstOrgCd_A;

    /** ORG_FUNC_ROLE_TP_CD_A*/
	public final EZDCStringItem              orgFuncRoleTpCd_A;

    /** ORG_FUNC_ROLE_TP_NM_A*/
	public final EZDCStringItem              orgFuncRoleTpNm_A;

    /** ORG_FUNC_ROLE_TP_DESC_TXT_A*/
	public final EZDCStringItem              orgFuncRoleTpDescTxt_A;

    /** ACTV_FLG_A*/
	public final EZDCStringItem              actvFlg_A;

    /** EQUIP_FLG_A*/
	public final EZDCStringItem              equipFlg_A;

    /** MGR_FLG_A*/
	public final EZDCStringItem              mgrFlg_A;

    /** SPCL_FLG_A*/
	public final EZDCStringItem              spclFlg_A;

    /** CMSN_FLG_A*/
	public final EZDCStringItem              cmsnFlg_A;

    /** ADMIN_FLG_A*/
	public final EZDCStringItem              adminFlg_A;

    /** GES_TP_CD_A*/
	public final EZDCStringItem              gesTpCd_A;

    /** APVL_LIMIT_AMT_A*/
	public final EZDCBigDecimalItem              apvlLimitAmt_A;

    /** SLS_REP_FLG_A*/
	public final EZDCStringItem              slsRepFlg_A;

    /** ASG_CONTR_FLG_A*/
	public final EZDCStringItem              asgContrFlg_A;

    /** THIRD_PTY_TECH_FLG_A*/
	public final EZDCStringItem              thirdPtyTechFlg_A;

    /** TECH_MSTR_REQ_FLG_A*/
	public final EZDCStringItem              techMstrReqFlg_A;

    /** CRM_SLS_EXCL_FLG_A*/
	public final EZDCStringItem              crmSlsExclFlg_A;

    /** CRM_SLS_PRFL_NM_A*/
	public final EZDCStringItem              crmSlsPrflNm_A;

    /** CRM_SLS_PRFL_NM_LK*/
	public final EZDCStringItem              crmSlsPrflNm_LK;

    /** SLS_ASG_ELIG_FLG_A*/
	public final EZDCStringItem              slsAsgEligFlg_A;

    /** SLS_ADMIN_ELIG_FLG_A*/
	public final EZDCStringItem              slsAdminEligFlg_A;

    /** MULT_PSN_ASG_FLG_A*/
	public final EZDCStringItem              multPsnAsgFlg_A;

    /** TECH_ASG_ELIG_FLG_A*/
	public final EZDCStringItem              techAsgEligFlg_A;

    /** TOC_REQ_FLG_A*/
	public final EZDCStringItem              tocReqFlg_A;

    /** ORG_STRU_TP_CD_A*/
	public final EZDCStringItem              orgStruTpCd_A;

    /** S21_ORG_REQ_FLG_A*/
	public final EZDCStringItem              s21OrgReqFlg_A;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;

    /** XX_ROW_ID_A*/
	public final EZDCStringItem              xxRowId_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDCStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDCStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDCStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDCStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDCStringItem              xxRecHistTblNm_A;


	/**
	 * NMAL2700_ACMsg is constructor.
	 * The initialization when the instance of NMAL2700_ACMsg is generated.
	 */
	public NMAL2700_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2700_ACMsg is constructor.
	 * The initialization when the instance of NMAL2700_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2700_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		firstOrgCd_A = (EZDCStringItem)newItem("firstOrgCd_A");
		orgFuncRoleTpCd_A = (EZDCStringItem)newItem("orgFuncRoleTpCd_A");
		orgFuncRoleTpNm_A = (EZDCStringItem)newItem("orgFuncRoleTpNm_A");
		orgFuncRoleTpDescTxt_A = (EZDCStringItem)newItem("orgFuncRoleTpDescTxt_A");
		actvFlg_A = (EZDCStringItem)newItem("actvFlg_A");
		equipFlg_A = (EZDCStringItem)newItem("equipFlg_A");
		mgrFlg_A = (EZDCStringItem)newItem("mgrFlg_A");
		spclFlg_A = (EZDCStringItem)newItem("spclFlg_A");
		cmsnFlg_A = (EZDCStringItem)newItem("cmsnFlg_A");
		adminFlg_A = (EZDCStringItem)newItem("adminFlg_A");
		gesTpCd_A = (EZDCStringItem)newItem("gesTpCd_A");
		apvlLimitAmt_A = (EZDCBigDecimalItem)newItem("apvlLimitAmt_A");
		slsRepFlg_A = (EZDCStringItem)newItem("slsRepFlg_A");
		asgContrFlg_A = (EZDCStringItem)newItem("asgContrFlg_A");
		thirdPtyTechFlg_A = (EZDCStringItem)newItem("thirdPtyTechFlg_A");
		techMstrReqFlg_A = (EZDCStringItem)newItem("techMstrReqFlg_A");
		crmSlsExclFlg_A = (EZDCStringItem)newItem("crmSlsExclFlg_A");
		crmSlsPrflNm_A = (EZDCStringItem)newItem("crmSlsPrflNm_A");
		crmSlsPrflNm_LK = (EZDCStringItem)newItem("crmSlsPrflNm_LK");
		slsAsgEligFlg_A = (EZDCStringItem)newItem("slsAsgEligFlg_A");
		slsAdminEligFlg_A = (EZDCStringItem)newItem("slsAdminEligFlg_A");
		multPsnAsgFlg_A = (EZDCStringItem)newItem("multPsnAsgFlg_A");
		techAsgEligFlg_A = (EZDCStringItem)newItem("techAsgEligFlg_A");
		tocReqFlg_A = (EZDCStringItem)newItem("tocReqFlg_A");
		orgStruTpCd_A = (EZDCStringItem)newItem("orgStruTpCd_A");
		s21OrgReqFlg_A = (EZDCStringItem)newItem("s21OrgReqFlg_A");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
		xxRowId_A = (EZDCStringItem)newItem("xxRowId_A");
		xxRecHistCratTs_A = (EZDCStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDCStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDCStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDCStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDCStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2700_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2700_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"firstOrgCd_A", "firstOrgCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpCd_A", "orgFuncRoleTpCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpNm_A", "orgFuncRoleTpNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"orgFuncRoleTpDescTxt_A", "orgFuncRoleTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"actvFlg_A", "actvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"equipFlg_A", "equipFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"mgrFlg_A", "mgrFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"spclFlg_A", "spclFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"cmsnFlg_A", "cmsnFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"adminFlg_A", "adminFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"gesTpCd_A", "gesTpCd_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"apvlLimitAmt_A", "apvlLimitAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"slsRepFlg_A", "slsRepFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"asgContrFlg_A", "asgContrFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"thirdPtyTechFlg_A", "thirdPtyTechFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"techMstrReqFlg_A", "techMstrReqFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"crmSlsExclFlg_A", "crmSlsExclFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"crmSlsPrflNm_A", "crmSlsPrflNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"crmSlsPrflNm_LK", "crmSlsPrflNm_LK", "LK", null, TYPE_HANKAKUEISU, "100", null},
	{"slsAsgEligFlg_A", "slsAsgEligFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"slsAdminEligFlg_A", "slsAdminEligFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"multPsnAsgFlg_A", "multPsnAsgFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"techAsgEligFlg_A", "techAsgEligFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"tocReqFlg_A", "tocReqFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"orgStruTpCd_A", "orgStruTpCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"s21OrgReqFlg_A", "s21OrgReqFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRowId_A", "xxRowId_A", "A", null, TYPE_HANKAKUEISU, "18", null},
	{"xxRecHistCratTs_A", "xxRecHistCratTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A", "xxRecHistCratByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A", "xxRecHistUpdTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A", "xxRecHistUpdByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A", "xxRecHistTblNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"FIRST_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstOrgCd_A
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_A
        {"ORG_FUNC_ROLE_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpNm_A
        {"ORG_FUNC_ROLE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpDescTxt_A
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A
        {"EQUIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//equipFlg_A
        {"MGR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mgrFlg_A
        {"SPCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//spclFlg_A
        {"CMSN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmsnFlg_A
        {"ADMIN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adminFlg_A
        {"GES_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gesTpCd_A
        {"APVL_LIMIT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlLimitAmt_A
        {"SLS_REP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepFlg_A
        {"ASG_CONTR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgContrFlg_A
        {"THIRD_PTY_TECH_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdPtyTechFlg_A
        {"TECH_MSTR_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techMstrReqFlg_A
        {"CRM_SLS_EXCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crmSlsExclFlg_A
        {"CRM_SLS_PRFL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crmSlsPrflNm_A
        {"CRM_SLS_PRFL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crmSlsPrflNm_LK
        {"SLS_ASG_ELIG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsAsgEligFlg_A
        {"SLS_ADMIN_ELIG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsAdminEligFlg_A
        {"MULT_PSN_ASG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//multPsnAsgFlg_A
        {"TECH_ASG_ELIG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techAsgEligFlg_A
        {"TOC_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocReqFlg_A
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_A
        {"S21_ORG_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//s21OrgReqFlg_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"XX_ROW_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowId_A
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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

