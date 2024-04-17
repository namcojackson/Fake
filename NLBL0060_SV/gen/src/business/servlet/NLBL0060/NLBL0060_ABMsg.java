//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151005143216000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0060_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0060_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_LINK_PROT_A1*/
	public final EZDBStringItem              xxLinkProt_A1;

    /** WH_CD_A1*/
	public final EZDBStringItem              whCd_A1;

    /** LOC_NM_A1*/
	public final EZDBStringItem              locNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDBDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDBDateItem              effThruDt_A1;

    /** SHPG_MODE_NM_A1*/
	public final EZDBStringItem              shpgModeNm_A1;

    /** SHPG_SVC_LVL_NM_A1*/
	public final EZDBStringItem              shpgSvcLvlNm_A1;

    /** SHPG_CLO_TM_TS_A1*/
	public final EZDBStringItem              shpgCloTmTs_A1;

    /** XX_DPLY_LEAD_TM_DAYS_AOT_A1*/
	public final EZDBBigDecimalItem              xxDplyLeadTmDaysAot_A1;

    /** XX_NEW_ROW_NUM_A9*/
	public final EZDBBigDecimalItem              xxNewRowNum_A9;

    /** WH_CD_A9*/
	public final EZDBStringItem              whCd_A9;

    /** EFF_FROM_DT_A9*/
	public final EZDBDateItem              effFromDt_A9;

    /** EFF_THRU_DT_A9*/
	public final EZDBDateItem              effThruDt_A9;

    /** SHPG_MODE_CD_A9*/
	public final EZDBStringItem              shpgModeCd_A9;

    /** SHPG_SVC_LVL_CD_A9*/
	public final EZDBStringItem              shpgSvcLvlCd_A9;


	/**
	 * NLBL0060_ABMsg is constructor.
	 * The initialization when the instance of NLBL0060_ABMsg is generated.
	 */
	public NLBL0060_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0060_ABMsg is constructor.
	 * The initialization when the instance of NLBL0060_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0060_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxLinkProt_A1 = (EZDBStringItem)newItem("xxLinkProt_A1");
		whCd_A1 = (EZDBStringItem)newItem("whCd_A1");
		locNm_A1 = (EZDBStringItem)newItem("locNm_A1");
		effFromDt_A1 = (EZDBDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDBDateItem)newItem("effThruDt_A1");
		shpgModeNm_A1 = (EZDBStringItem)newItem("shpgModeNm_A1");
		shpgSvcLvlNm_A1 = (EZDBStringItem)newItem("shpgSvcLvlNm_A1");
		shpgCloTmTs_A1 = (EZDBStringItem)newItem("shpgCloTmTs_A1");
		xxDplyLeadTmDaysAot_A1 = (EZDBBigDecimalItem)newItem("xxDplyLeadTmDaysAot_A1");
		xxNewRowNum_A9 = (EZDBBigDecimalItem)newItem("xxNewRowNum_A9");
		whCd_A9 = (EZDBStringItem)newItem("whCd_A9");
		effFromDt_A9 = (EZDBDateItem)newItem("effFromDt_A9");
		effThruDt_A9 = (EZDBDateItem)newItem("effThruDt_A9");
		shpgModeCd_A9 = (EZDBStringItem)newItem("shpgModeCd_A9");
		shpgSvcLvlCd_A9 = (EZDBStringItem)newItem("shpgSvcLvlCd_A9");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0060_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0060_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxLinkProt_A1", "xxLinkProt_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"whCd_A1", "whCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_A1", "locNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"shpgModeNm_A1", "shpgModeNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"shpgSvcLvlNm_A1", "shpgSvcLvlNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"shpgCloTmTs_A1", "shpgCloTmTs_A1", "A1", null, TYPE_HANKAKUSUJI, "4", null},
	{"xxDplyLeadTmDaysAot_A1", "xxDplyLeadTmDaysAot_A1", "A1", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"xxNewRowNum_A9", "xxNewRowNum_A9", "A9", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"whCd_A9", "whCd_A9", "A9", null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt_A9", "effFromDt_A9", "A9", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A9", "effThruDt_A9", "A9", null, TYPE_NENTSUKIHI, "8", null},
	{"shpgModeCd_A9", "shpgModeCd_A9", "A9", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgSvcLvlCd_A9", "shpgSvcLvlCd_A9", "A9", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_LINK_PROT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_A1
        {"WH_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_A1
        {"LOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A1
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A1
        {"EFF_THRU_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A1
        {"SHPG_MODE_NM",  NO,  null,null,"0", NO, NO, NO, NO,"10",null,null, null,  NO,  NO},	//shpgModeNm_A1
        {"SHPG_SVC_LVL_NM",  NO,  null,null,"0", NO, NO, NO, NO,"16",null,null, null,  NO,  NO},	//shpgSvcLvlNm_A1
        {"SHPG_CLO_TM_TS", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgCloTmTs_A1
        {"XX_DPLY_LEAD_TM_DAYS_AOT", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyLeadTmDaysAot_A1
        {"XX_NEW_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNewRowNum_A9
        {"WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_A9
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A9
        {"EFF_THRU_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A9
        {"SHPG_MODE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeCd_A9
        {"SHPG_SVC_LVL_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_A9
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
