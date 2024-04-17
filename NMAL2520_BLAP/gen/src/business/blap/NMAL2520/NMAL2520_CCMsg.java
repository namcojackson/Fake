//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170615155548000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2520_CCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2520;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2520 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2520_CCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_C1*/
	public final EZDCStringItem              xxChkBox_C1;

    /** PSN_NUM_C1*/
	public final EZDCStringItem              psnNum_C1;

    /** ORG_FUNC_ROLE_TP_CD_P1*/
	public final EZDCStringItem              orgFuncRoleTpCd_P1;

    /** PSN_LAST_NM_C1*/
	public final EZDCStringItem              psnLastNm_C1;

    /** PSN_FIRST_NM_C1*/
	public final EZDCStringItem              psnFirstNm_C1;

    /** ASG_CUST_FROM_NM_C1*/
	public final EZDCStringItem              asgCustFromNm_C1;

    /** ASG_CUST_TO_NM_C1*/
	public final EZDCStringItem              asgCustToNm_C1;

    /** EFF_FROM_DT_C1*/
	public final EZDCDateItem              effFromDt_C1;

    /** EFF_THRU_DT_C1*/
	public final EZDCDateItem              effThruDt_C1;

    /** PSN_CD_C1*/
	public final EZDCStringItem              psnCd_C1;

    /** GNRN_TP_CD_C1*/
	public final EZDCStringItem              gnrnTpCd_C1;

    /** _EZUpdateDateTime_U1*/
	public final EZDCStringItem              ezUpTime_U1;

    /** _EZUpTimeZone_U1*/
	public final EZDCStringItem              ezUpTimeZone_U1;

    /** ORG_STRU_TP_CD_U1*/
	public final EZDCStringItem              orgStruTpCd_U1;

    /** TOC_CD_U1*/
	public final EZDCStringItem              tocCd_U1;

    /** ORG_CD_U1*/
	public final EZDCStringItem              orgCd_U1;

    /** _EZUpdateDateTime_U2*/
	public final EZDCStringItem              ezUpTime_U2;

    /** _EZUpTimeZone_U2*/
	public final EZDCStringItem              ezUpTimeZone_U2;

    /** ORG_CHNG_RQST_PK_U2*/
	public final EZDCBigDecimalItem              orgChngRqstPk_U2;

    /** COA_BR_CD_U2*/
	public final EZDCStringItem              coaBrCd_U2;

    /** COA_EXTN_CD_U2*/
	public final EZDCStringItem              coaExtnCd_U2;

    /** COA_CC_CD_U2*/
	public final EZDCStringItem              coaCcCd_U2;

    /** COA_CMPY_CD_U2*/
	public final EZDCStringItem              coaCmpyCd_U2;

    /** _EZUpdateDateTime_U3*/
	public final EZDCStringItem              ezUpTime_U3;

    /** _EZUpTimeZone_U3*/
	public final EZDCStringItem              ezUpTimeZone_U3;

    /** TOC_CD_U3*/
	public final EZDCStringItem              tocCd_U3;

    /** PSN_CD_U3*/
	public final EZDCStringItem              psnCd_U3;

    /** EFF_FROM_DT_U3*/
	public final EZDCDateItem              effFromDt_U3;

    /** EFF_FROM_DT_U4*/
	public final EZDCDateItem              effFromDt_U4;

    /** EFF_THRU_DT_U4*/
	public final EZDCDateItem              effThruDt_U4;

    /** PSN_NUM_CB*/
	public final EZDCStringItem              psnNum_CB;

    /** ORG_FUNC_ROLE_TP_CD_CB*/
	public final EZDCStringItem              orgFuncRoleTpCd_CB;

    /** PSN_LAST_NM_CB*/
	public final EZDCStringItem              psnLastNm_CB;

    /** PSN_FIRST_NM_CB*/
	public final EZDCStringItem              psnFirstNm_CB;

    /** ASG_CUST_FROM_NM_CB*/
	public final EZDCStringItem              asgCustFromNm_CB;

    /** ASG_CUST_TO_NM_CB*/
	public final EZDCStringItem              asgCustToNm_CB;

    /** EFF_FROM_DT_CB*/
	public final EZDCDateItem              effFromDt_CB;

    /** EFF_THRU_DT_CB*/
	public final EZDCDateItem              effThruDt_CB;

    /** PSN_CD_CB*/
	public final EZDCStringItem              psnCd_CB;

    /** GNRN_TP_CD_CB*/
	public final EZDCStringItem              gnrnTpCd_CB;

    /** XX_REC_HIST_CRAT_TS_C1*/
	public final EZDCStringItem              xxRecHistCratTs_C1;

    /** XX_REC_HIST_CRAT_BY_NM_C1*/
	public final EZDCStringItem              xxRecHistCratByNm_C1;

    /** XX_REC_HIST_UPD_TS_C1*/
	public final EZDCStringItem              xxRecHistUpdTs_C1;

    /** XX_REC_HIST_UPD_BY_NM_C1*/
	public final EZDCStringItem              xxRecHistUpdByNm_C1;

    /** XX_REC_HIST_TBL_NM_C1*/
	public final EZDCStringItem              xxRecHistTblNm_C1;


	/**
	 * NMAL2520_CCMsg is constructor.
	 * The initialization when the instance of NMAL2520_CCMsg is generated.
	 */
	public NMAL2520_CCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2520_CCMsg is constructor.
	 * The initialization when the instance of NMAL2520_CCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2520_CCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_C1 = (EZDCStringItem)newItem("xxChkBox_C1");
		psnNum_C1 = (EZDCStringItem)newItem("psnNum_C1");
		orgFuncRoleTpCd_P1 = (EZDCStringItem)newItem("orgFuncRoleTpCd_P1");
		psnLastNm_C1 = (EZDCStringItem)newItem("psnLastNm_C1");
		psnFirstNm_C1 = (EZDCStringItem)newItem("psnFirstNm_C1");
		asgCustFromNm_C1 = (EZDCStringItem)newItem("asgCustFromNm_C1");
		asgCustToNm_C1 = (EZDCStringItem)newItem("asgCustToNm_C1");
		effFromDt_C1 = (EZDCDateItem)newItem("effFromDt_C1");
		effThruDt_C1 = (EZDCDateItem)newItem("effThruDt_C1");
		psnCd_C1 = (EZDCStringItem)newItem("psnCd_C1");
		gnrnTpCd_C1 = (EZDCStringItem)newItem("gnrnTpCd_C1");
		ezUpTime_U1 = (EZDCStringItem)newItem("ezUpTime_U1");
		ezUpTimeZone_U1 = (EZDCStringItem)newItem("ezUpTimeZone_U1");
		orgStruTpCd_U1 = (EZDCStringItem)newItem("orgStruTpCd_U1");
		tocCd_U1 = (EZDCStringItem)newItem("tocCd_U1");
		orgCd_U1 = (EZDCStringItem)newItem("orgCd_U1");
		ezUpTime_U2 = (EZDCStringItem)newItem("ezUpTime_U2");
		ezUpTimeZone_U2 = (EZDCStringItem)newItem("ezUpTimeZone_U2");
		orgChngRqstPk_U2 = (EZDCBigDecimalItem)newItem("orgChngRqstPk_U2");
		coaBrCd_U2 = (EZDCStringItem)newItem("coaBrCd_U2");
		coaExtnCd_U2 = (EZDCStringItem)newItem("coaExtnCd_U2");
		coaCcCd_U2 = (EZDCStringItem)newItem("coaCcCd_U2");
		coaCmpyCd_U2 = (EZDCStringItem)newItem("coaCmpyCd_U2");
		ezUpTime_U3 = (EZDCStringItem)newItem("ezUpTime_U3");
		ezUpTimeZone_U3 = (EZDCStringItem)newItem("ezUpTimeZone_U3");
		tocCd_U3 = (EZDCStringItem)newItem("tocCd_U3");
		psnCd_U3 = (EZDCStringItem)newItem("psnCd_U3");
		effFromDt_U3 = (EZDCDateItem)newItem("effFromDt_U3");
		effFromDt_U4 = (EZDCDateItem)newItem("effFromDt_U4");
		effThruDt_U4 = (EZDCDateItem)newItem("effThruDt_U4");
		psnNum_CB = (EZDCStringItem)newItem("psnNum_CB");
		orgFuncRoleTpCd_CB = (EZDCStringItem)newItem("orgFuncRoleTpCd_CB");
		psnLastNm_CB = (EZDCStringItem)newItem("psnLastNm_CB");
		psnFirstNm_CB = (EZDCStringItem)newItem("psnFirstNm_CB");
		asgCustFromNm_CB = (EZDCStringItem)newItem("asgCustFromNm_CB");
		asgCustToNm_CB = (EZDCStringItem)newItem("asgCustToNm_CB");
		effFromDt_CB = (EZDCDateItem)newItem("effFromDt_CB");
		effThruDt_CB = (EZDCDateItem)newItem("effThruDt_CB");
		psnCd_CB = (EZDCStringItem)newItem("psnCd_CB");
		gnrnTpCd_CB = (EZDCStringItem)newItem("gnrnTpCd_CB");
		xxRecHistCratTs_C1 = (EZDCStringItem)newItem("xxRecHistCratTs_C1");
		xxRecHistCratByNm_C1 = (EZDCStringItem)newItem("xxRecHistCratByNm_C1");
		xxRecHistUpdTs_C1 = (EZDCStringItem)newItem("xxRecHistUpdTs_C1");
		xxRecHistUpdByNm_C1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_C1");
		xxRecHistTblNm_C1 = (EZDCStringItem)newItem("xxRecHistTblNm_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2520_CCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2520_CCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_C1", "xxChkBox_C1", "C1", null, TYPE_HANKAKUEIJI, "1", null},
	{"psnNum_C1", "psnNum_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgFuncRoleTpCd_P1", "orgFuncRoleTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnLastNm_C1", "psnLastNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"psnFirstNm_C1", "psnFirstNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"asgCustFromNm_C1", "asgCustFromNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"asgCustToNm_C1", "asgCustToNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"effFromDt_C1", "effFromDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_C1", "effThruDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"psnCd_C1", "psnCd_C1", "C1", null, TYPE_HANKAKUEISU, "8", null},
	{"gnrnTpCd_C1", "gnrnTpCd_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_U1", "ezUpTime_U1", "U1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_U1", "ezUpTimeZone_U1", "U1", null, TYPE_HANKAKUEISU, "5", null},
	{"orgStruTpCd_U1", "orgStruTpCd_U1", "U1", null, TYPE_HANKAKUEISU, "8", null},
	{"tocCd_U1", "tocCd_U1", "U1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgCd_U1", "orgCd_U1", "U1", null, TYPE_HANKAKUEISU, "8", null},
	{"ezUpTime_U2", "ezUpTime_U2", "U2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_U2", "ezUpTimeZone_U2", "U2", null, TYPE_HANKAKUEISU, "5", null},
	{"orgChngRqstPk_U2", "orgChngRqstPk_U2", "U2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"coaBrCd_U2", "coaBrCd_U2", "U2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaExtnCd_U2", "coaExtnCd_U2", "U2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_U2", "coaCcCd_U2", "U2", null, TYPE_HANKAKUEISU, "6", null},
	{"coaCmpyCd_U2", "coaCmpyCd_U2", "U2", null, TYPE_HANKAKUEISU, "3", null},
	{"ezUpTime_U3", "ezUpTime_U3", "U3", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_U3", "ezUpTimeZone_U3", "U3", null, TYPE_HANKAKUEISU, "5", null},
	{"tocCd_U3", "tocCd_U3", "U3", null, TYPE_HANKAKUEISU, "8", null},
	{"psnCd_U3", "psnCd_U3", "U3", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_U3", "effFromDt_U3", "U3", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_U4", "effFromDt_U4", "U4", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_U4", "effThruDt_U4", "U4", null, TYPE_NENTSUKIHI, "8", null},
	{"psnNum_CB", "psnNum_CB", "CB", null, TYPE_HANKAKUEISU, "50", null},
	{"orgFuncRoleTpCd_CB", "orgFuncRoleTpCd_CB", "CB", null, TYPE_HANKAKUEISU, "8", null},
	{"psnLastNm_CB", "psnLastNm_CB", "CB", null, TYPE_HANKAKUEISU, "30", null},
	{"psnFirstNm_CB", "psnFirstNm_CB", "CB", null, TYPE_HANKAKUEISU, "30", null},
	{"asgCustFromNm_CB", "asgCustFromNm_CB", "CB", null, TYPE_HANKAKUEISU, "30", null},
	{"asgCustToNm_CB", "asgCustToNm_CB", "CB", null, TYPE_HANKAKUEISU, "30", null},
	{"effFromDt_CB", "effFromDt_CB", "CB", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_CB", "effThruDt_CB", "CB", null, TYPE_NENTSUKIHI, "8", null},
	{"psnCd_CB", "psnCd_CB", "CB", null, TYPE_HANKAKUEISU, "8", null},
	{"gnrnTpCd_CB", "gnrnTpCd_CB", "CB", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs_C1", "xxRecHistCratTs_C1", "C1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_C1", "xxRecHistCratByNm_C1", "C1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_C1", "xxRecHistUpdTs_C1", "C1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_C1", "xxRecHistUpdByNm_C1", "C1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_C1", "xxRecHistTblNm_C1", "C1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C1
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_C1
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_P1
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_C1
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_C1
        {"ASG_CUST_FROM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgCustFromNm_C1
        {"ASG_CUST_TO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgCustToNm_C1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_C1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_C1
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_C1
        {"GNRN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_C1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_U1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_U1
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_U1
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_U1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_U1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_U2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_U2
        {"ORG_CHNG_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgChngRqstPk_U2
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_U2
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_U2
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_U2
        {"COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_U2
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_U3
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_U3
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_U3
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_U3
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_U3
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_U4
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_U4
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_CB
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_CB
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_CB
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_CB
        {"ASG_CUST_FROM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgCustFromNm_CB
        {"ASG_CUST_TO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgCustToNm_CB
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_CB
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_CB
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_CB
        {"GNRN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_CB
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_C1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_C1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_C1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_C1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_C1
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

