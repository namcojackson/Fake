//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190213100019000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2510_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2510 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2510_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BIZ_AREA_ORG_CD_A1*/
	public final EZDCStringItemArray              bizAreaOrgCd_A1;

    /** BIZ_AREA_ORG_NM_A1*/
	public final EZDCStringItemArray              bizAreaOrgNm_A1;

    /** BIZ_AREA_ORG_CD_P2*/
	public final EZDCStringItem              bizAreaOrgCd_P2;

    /** ORG_FUNC_ROLE_TP_CD_A2*/
	public final EZDCStringItemArray              orgFuncRoleTpCd_A2;

    /** ORG_FUNC_ROLE_TP_NM_A2*/
	public final EZDCStringItemArray              orgFuncRoleTpNm_A2;

    /** ORG_FUNC_ROLE_TP_CD_P2*/
	public final EZDCStringItem              orgFuncRoleTpCd_P2;

    /** ORG_NM_A2*/
	public final EZDCStringItem              orgNm_A2;

    /** ORG_STRU_TP_CD_A2*/
	public final EZDCStringItem              orgStruTpCd_A2;

    /** EFF_FROM_DT_A2*/
	public final EZDCDateItem              effFromDt_A2;

    /** EFF_THRU_DT_A2*/
	public final EZDCDateItem              effThruDt_A2;

    /** EFF_FROM_DT_A3*/
	public final EZDCDateItem              effFromDt_A3;

    /** EFF_THRU_DT_A3*/
	public final EZDCDateItem              effThruDt_A3;

    /** _EZUpdateDateTime_A3*/
	public final EZDCStringItem              ezUpTime_A3;

    /** _EZUpTimeZone_A3*/
	public final EZDCStringItem              ezUpTimeZone_A3;

    /** _EZUpdateDateTime_A4*/
	public final EZDCStringItem              ezUpTime_A4;

    /** _EZUpTimeZone_A4*/
	public final EZDCStringItem              ezUpTimeZone_A4;

    /** _EZUpdateDateTime_A5*/
	public final EZDCStringItem              ezUpTime_A5;

    /** _EZUpTimeZone_A5*/
	public final EZDCStringItem              ezUpTimeZone_A5;

    /** ORG_CD_A2*/
	public final EZDCStringItem              orgCd_A2;

    /** TOC_CD_A2*/
	public final EZDCStringItem              tocCd_A2;

    /** ORG_CHNG_RQST_PK_A2*/
	public final EZDCBigDecimalItem              orgChngRqstPk_A2;

    /** GNRN_TP_CD_A2*/
	public final EZDCStringItem              gnrnTpCd_A2;

    /** ACTV_FLG_BA*/
	public final EZDCStringItem              actvFlg_BA;

    /** BIZ_AREA_ORG_CD_AB*/
	public final EZDCStringItem              bizAreaOrgCd_AB;

    /** ORG_FUNC_ROLE_TP_CD_AB*/
	public final EZDCStringItem              orgFuncRoleTpCd_AB;

    /** ORG_CD_AB*/
	public final EZDCStringItem              orgCd_AB;

    /** ORG_NM_AB*/
	public final EZDCStringItem              orgNm_AB;

    /** ORG_STRU_TP_CD_AB*/
	public final EZDCStringItem              orgStruTpCd_AB;

    /** EFF_FROM_DT_AB*/
	public final EZDCDateItem              effFromDt_AB;

    /** EFF_THRU_DT_AB*/
	public final EZDCDateItem              effThruDt_AB;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDCStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDCStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDCStringItem              xxRecHistTblNm_A1;


	/**
	 * NMAL2510_ACMsg is constructor.
	 * The initialization when the instance of NMAL2510_ACMsg is generated.
	 */
	public NMAL2510_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2510_ACMsg is constructor.
	 * The initialization when the instance of NMAL2510_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2510_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		bizAreaOrgCd_A1 = (EZDCStringItemArray)newItemArray("bizAreaOrgCd_A1");
		bizAreaOrgNm_A1 = (EZDCStringItemArray)newItemArray("bizAreaOrgNm_A1");
		bizAreaOrgCd_P2 = (EZDCStringItem)newItem("bizAreaOrgCd_P2");
		orgFuncRoleTpCd_A2 = (EZDCStringItemArray)newItemArray("orgFuncRoleTpCd_A2");
		orgFuncRoleTpNm_A2 = (EZDCStringItemArray)newItemArray("orgFuncRoleTpNm_A2");
		orgFuncRoleTpCd_P2 = (EZDCStringItem)newItem("orgFuncRoleTpCd_P2");
		orgNm_A2 = (EZDCStringItem)newItem("orgNm_A2");
		orgStruTpCd_A2 = (EZDCStringItem)newItem("orgStruTpCd_A2");
		effFromDt_A2 = (EZDCDateItem)newItem("effFromDt_A2");
		effThruDt_A2 = (EZDCDateItem)newItem("effThruDt_A2");
		effFromDt_A3 = (EZDCDateItem)newItem("effFromDt_A3");
		effThruDt_A3 = (EZDCDateItem)newItem("effThruDt_A3");
		ezUpTime_A3 = (EZDCStringItem)newItem("ezUpTime_A3");
		ezUpTimeZone_A3 = (EZDCStringItem)newItem("ezUpTimeZone_A3");
		ezUpTime_A4 = (EZDCStringItem)newItem("ezUpTime_A4");
		ezUpTimeZone_A4 = (EZDCStringItem)newItem("ezUpTimeZone_A4");
		ezUpTime_A5 = (EZDCStringItem)newItem("ezUpTime_A5");
		ezUpTimeZone_A5 = (EZDCStringItem)newItem("ezUpTimeZone_A5");
		orgCd_A2 = (EZDCStringItem)newItem("orgCd_A2");
		tocCd_A2 = (EZDCStringItem)newItem("tocCd_A2");
		orgChngRqstPk_A2 = (EZDCBigDecimalItem)newItem("orgChngRqstPk_A2");
		gnrnTpCd_A2 = (EZDCStringItem)newItem("gnrnTpCd_A2");
		actvFlg_BA = (EZDCStringItem)newItem("actvFlg_BA");
		bizAreaOrgCd_AB = (EZDCStringItem)newItem("bizAreaOrgCd_AB");
		orgFuncRoleTpCd_AB = (EZDCStringItem)newItem("orgFuncRoleTpCd_AB");
		orgCd_AB = (EZDCStringItem)newItem("orgCd_AB");
		orgNm_AB = (EZDCStringItem)newItem("orgNm_AB");
		orgStruTpCd_AB = (EZDCStringItem)newItem("orgStruTpCd_AB");
		effFromDt_AB = (EZDCDateItem)newItem("effFromDt_AB");
		effThruDt_AB = (EZDCDateItem)newItem("effThruDt_AB");
		xxRecHistCratTs_A1 = (EZDCStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDCStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDCStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDCStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2510_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2510_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"bizAreaOrgCd_A1", "bizAreaOrgCd_A1", "A1", "99", TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgNm_A1", "bizAreaOrgNm_A1", "A1", "99", TYPE_HANKAKUEISU, "70", null},
	{"bizAreaOrgCd_P2", "bizAreaOrgCd_P2", "P2", null, TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpCd_A2", "orgFuncRoleTpCd_A2", "A2", "300", TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpNm_A2", "orgFuncRoleTpNm_A2", "A2", "300", TYPE_HANKAKUEISU, "50", null},
	{"orgFuncRoleTpCd_P2", "orgFuncRoleTpCd_P2", "P2", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_A2", "orgNm_A2", "A2", null, TYPE_HANKAKUEISU, "50", null},
	{"orgStruTpCd_A2", "orgStruTpCd_A2", "A2", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_A2", "effFromDt_A2", "A2", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A2", "effThruDt_A2", "A2", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_A3", "effFromDt_A3", "A3", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A3", "effThruDt_A3", "A3", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_A3", "ezUpTime_A3", "A3", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A3", "ezUpTimeZone_A3", "A3", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_A4", "ezUpTime_A4", "A4", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A4", "ezUpTimeZone_A4", "A4", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_A5", "ezUpTime_A5", "A5", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A5", "ezUpTimeZone_A5", "A5", null, TYPE_HANKAKUEISU, "5", null},
	{"orgCd_A2", "orgCd_A2", "A2", null, TYPE_HANKAKUEISU, "8", null},
	{"tocCd_A2", "tocCd_A2", "A2", null, TYPE_HANKAKUEISU, "8", null},
	{"orgChngRqstPk_A2", "orgChngRqstPk_A2", "A2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"gnrnTpCd_A2", "gnrnTpCd_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"actvFlg_BA", "actvFlg_BA", "BA", null, TYPE_HANKAKUEISU, "1", null},
	{"bizAreaOrgCd_AB", "bizAreaOrgCd_AB", "AB", null, TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpCd_AB", "orgFuncRoleTpCd_AB", "AB", null, TYPE_HANKAKUEISU, "8", null},
	{"orgCd_AB", "orgCd_AB", "AB", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_AB", "orgNm_AB", "AB", null, TYPE_HANKAKUEISU, "50", null},
	{"orgStruTpCd_AB", "orgStruTpCd_AB", "AB", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_AB", "effFromDt_AB", "AB", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_AB", "effThruDt_AB", "AB", null, TYPE_NENTSUKIHI, "8", null},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_A1
        {"BIZ_AREA_ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgNm_A1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_P2
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_A2
        {"ORG_FUNC_ROLE_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpNm_A2
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_P2
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_A2
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_A2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A2
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A3
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A3
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A3
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A3
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A4
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A4
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A5
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A5
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_A2
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_A2
        {"ORG_CHNG_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgChngRqstPk_A2
        {"GNRN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_A2
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_BA
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_AB
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_AB
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_AB
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_AB
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_AB
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_AB
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_AB
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
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
