//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170615170836000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2520_CSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL2520 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2520_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_C1*/
	public final EZDSStringItem              xxChkBox_C1;

    /** PSN_NUM_C1*/
	public final EZDSStringItem              psnNum_C1;

    /** ORG_FUNC_ROLE_TP_CD_P1*/
	public final EZDSStringItem              orgFuncRoleTpCd_P1;

    /** PSN_LAST_NM_C1*/
	public final EZDSStringItem              psnLastNm_C1;

    /** PSN_FIRST_NM_C1*/
	public final EZDSStringItem              psnFirstNm_C1;

    /** ASG_CUST_FROM_NM_C1*/
	public final EZDSStringItem              asgCustFromNm_C1;

    /** ASG_CUST_TO_NM_C1*/
	public final EZDSStringItem              asgCustToNm_C1;

    /** EFF_FROM_DT_C1*/
	public final EZDSDateItem              effFromDt_C1;

    /** EFF_THRU_DT_C1*/
	public final EZDSDateItem              effThruDt_C1;

    /** PSN_CD_C1*/
	public final EZDSStringItem              psnCd_C1;

    /** GNRN_TP_CD_C1*/
	public final EZDSStringItem              gnrnTpCd_C1;

    /** _EZUpdateDateTime_U1*/
	public final EZDSStringItem              ezUpTime_U1;

    /** _EZUpTimeZone_U1*/
	public final EZDSStringItem              ezUpTimeZone_U1;

    /** ORG_STRU_TP_CD_U1*/
	public final EZDSStringItem              orgStruTpCd_U1;

    /** TOC_CD_U1*/
	public final EZDSStringItem              tocCd_U1;

    /** ORG_CD_U1*/
	public final EZDSStringItem              orgCd_U1;

    /** _EZUpdateDateTime_U2*/
	public final EZDSStringItem              ezUpTime_U2;

    /** _EZUpTimeZone_U2*/
	public final EZDSStringItem              ezUpTimeZone_U2;

    /** ORG_CHNG_RQST_PK_U2*/
	public final EZDSBigDecimalItem              orgChngRqstPk_U2;

    /** COA_BR_CD_U2*/
	public final EZDSStringItem              coaBrCd_U2;

    /** COA_EXTN_CD_U2*/
	public final EZDSStringItem              coaExtnCd_U2;

    /** COA_CC_CD_U2*/
	public final EZDSStringItem              coaCcCd_U2;

    /** COA_CMPY_CD_U2*/
	public final EZDSStringItem              coaCmpyCd_U2;

    /** _EZUpdateDateTime_U3*/
	public final EZDSStringItem              ezUpTime_U3;

    /** _EZUpTimeZone_U3*/
	public final EZDSStringItem              ezUpTimeZone_U3;

    /** TOC_CD_U3*/
	public final EZDSStringItem              tocCd_U3;

    /** PSN_CD_U3*/
	public final EZDSStringItem              psnCd_U3;

    /** EFF_FROM_DT_U3*/
	public final EZDSDateItem              effFromDt_U3;

    /** EFF_FROM_DT_U4*/
	public final EZDSDateItem              effFromDt_U4;

    /** EFF_THRU_DT_U4*/
	public final EZDSDateItem              effThruDt_U4;

    /** PSN_NUM_CB*/
	public final EZDSStringItem              psnNum_CB;

    /** ORG_FUNC_ROLE_TP_CD_CB*/
	public final EZDSStringItem              orgFuncRoleTpCd_CB;

    /** PSN_LAST_NM_CB*/
	public final EZDSStringItem              psnLastNm_CB;

    /** PSN_FIRST_NM_CB*/
	public final EZDSStringItem              psnFirstNm_CB;

    /** ASG_CUST_FROM_NM_CB*/
	public final EZDSStringItem              asgCustFromNm_CB;

    /** ASG_CUST_TO_NM_CB*/
	public final EZDSStringItem              asgCustToNm_CB;

    /** EFF_FROM_DT_CB*/
	public final EZDSDateItem              effFromDt_CB;

    /** EFF_THRU_DT_CB*/
	public final EZDSDateItem              effThruDt_CB;

    /** PSN_CD_CB*/
	public final EZDSStringItem              psnCd_CB;

    /** GNRN_TP_CD_CB*/
	public final EZDSStringItem              gnrnTpCd_CB;

    /** XX_REC_HIST_CRAT_TS_C1*/
	public final EZDSStringItem              xxRecHistCratTs_C1;

    /** XX_REC_HIST_CRAT_BY_NM_C1*/
	public final EZDSStringItem              xxRecHistCratByNm_C1;

    /** XX_REC_HIST_UPD_TS_C1*/
	public final EZDSStringItem              xxRecHistUpdTs_C1;

    /** XX_REC_HIST_UPD_BY_NM_C1*/
	public final EZDSStringItem              xxRecHistUpdByNm_C1;

    /** XX_REC_HIST_TBL_NM_C1*/
	public final EZDSStringItem              xxRecHistTblNm_C1;


	/**
	 * NMAL2520_CSMsg is constructor.
	 * The initialization when the instance of NMAL2520_CSMsg is generated.
	 */
	public NMAL2520_CSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2520_CSMsg is constructor.
	 * The initialization when the instance of NMAL2520_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2520_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_C1 = (EZDSStringItem)newItem("xxChkBox_C1");
		psnNum_C1 = (EZDSStringItem)newItem("psnNum_C1");
		orgFuncRoleTpCd_P1 = (EZDSStringItem)newItem("orgFuncRoleTpCd_P1");
		psnLastNm_C1 = (EZDSStringItem)newItem("psnLastNm_C1");
		psnFirstNm_C1 = (EZDSStringItem)newItem("psnFirstNm_C1");
		asgCustFromNm_C1 = (EZDSStringItem)newItem("asgCustFromNm_C1");
		asgCustToNm_C1 = (EZDSStringItem)newItem("asgCustToNm_C1");
		effFromDt_C1 = (EZDSDateItem)newItem("effFromDt_C1");
		effThruDt_C1 = (EZDSDateItem)newItem("effThruDt_C1");
		psnCd_C1 = (EZDSStringItem)newItem("psnCd_C1");
		gnrnTpCd_C1 = (EZDSStringItem)newItem("gnrnTpCd_C1");
		ezUpTime_U1 = (EZDSStringItem)newItem("ezUpTime_U1");
		ezUpTimeZone_U1 = (EZDSStringItem)newItem("ezUpTimeZone_U1");
		orgStruTpCd_U1 = (EZDSStringItem)newItem("orgStruTpCd_U1");
		tocCd_U1 = (EZDSStringItem)newItem("tocCd_U1");
		orgCd_U1 = (EZDSStringItem)newItem("orgCd_U1");
		ezUpTime_U2 = (EZDSStringItem)newItem("ezUpTime_U2");
		ezUpTimeZone_U2 = (EZDSStringItem)newItem("ezUpTimeZone_U2");
		orgChngRqstPk_U2 = (EZDSBigDecimalItem)newItem("orgChngRqstPk_U2");
		coaBrCd_U2 = (EZDSStringItem)newItem("coaBrCd_U2");
		coaExtnCd_U2 = (EZDSStringItem)newItem("coaExtnCd_U2");
		coaCcCd_U2 = (EZDSStringItem)newItem("coaCcCd_U2");
		coaCmpyCd_U2 = (EZDSStringItem)newItem("coaCmpyCd_U2");
		ezUpTime_U3 = (EZDSStringItem)newItem("ezUpTime_U3");
		ezUpTimeZone_U3 = (EZDSStringItem)newItem("ezUpTimeZone_U3");
		tocCd_U3 = (EZDSStringItem)newItem("tocCd_U3");
		psnCd_U3 = (EZDSStringItem)newItem("psnCd_U3");
		effFromDt_U3 = (EZDSDateItem)newItem("effFromDt_U3");
		effFromDt_U4 = (EZDSDateItem)newItem("effFromDt_U4");
		effThruDt_U4 = (EZDSDateItem)newItem("effThruDt_U4");
		psnNum_CB = (EZDSStringItem)newItem("psnNum_CB");
		orgFuncRoleTpCd_CB = (EZDSStringItem)newItem("orgFuncRoleTpCd_CB");
		psnLastNm_CB = (EZDSStringItem)newItem("psnLastNm_CB");
		psnFirstNm_CB = (EZDSStringItem)newItem("psnFirstNm_CB");
		asgCustFromNm_CB = (EZDSStringItem)newItem("asgCustFromNm_CB");
		asgCustToNm_CB = (EZDSStringItem)newItem("asgCustToNm_CB");
		effFromDt_CB = (EZDSDateItem)newItem("effFromDt_CB");
		effThruDt_CB = (EZDSDateItem)newItem("effThruDt_CB");
		psnCd_CB = (EZDSStringItem)newItem("psnCd_CB");
		gnrnTpCd_CB = (EZDSStringItem)newItem("gnrnTpCd_CB");
		xxRecHistCratTs_C1 = (EZDSStringItem)newItem("xxRecHistCratTs_C1");
		xxRecHistCratByNm_C1 = (EZDSStringItem)newItem("xxRecHistCratByNm_C1");
		xxRecHistUpdTs_C1 = (EZDSStringItem)newItem("xxRecHistUpdTs_C1");
		xxRecHistUpdByNm_C1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_C1");
		xxRecHistTblNm_C1 = (EZDSStringItem)newItem("xxRecHistTblNm_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2520_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2520_CSMsgArray();
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

