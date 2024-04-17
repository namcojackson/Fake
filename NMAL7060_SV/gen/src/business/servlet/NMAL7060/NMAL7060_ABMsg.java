//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180821154600000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7060_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7060_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_MTR_PKG_MDL_PK_A1*/
	public final EZDBBigDecimalItem              prcMtrPkgMdlPk_A1;

    /** MDL_ID_A1*/
	public final EZDBBigDecimalItem              mdlId_A1;

    /** MTR_GRP_PK_A1*/
	public final EZDBBigDecimalItem              mtrGrpPk_A1;

    /** XX_LINK_ANCR_AM*/
	public final EZDBStringItem              xxLinkAncr_AM;

    /** MDL_NM_A1*/
	public final EZDBStringItem              mdlNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDBDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDBDateItem              effThruDt_A1;

    /** XX_DT_10_DT_AI*/
	public final EZDBDateItem              xxDt10Dt_AI;

    /** _EZRegistrationDateTime_AK*/
	public final EZDBStringItem              ezInTime_AK;

    /** _EZInTimeZone_A1*/
	public final EZDBStringItem              ezInTimeZone_A1;

    /** XX_FULL_NM_AI*/
	public final EZDBStringItem              xxFullNm_AI;

    /** XX_DT_10_DT_AU*/
	public final EZDBDateItem              xxDt10Dt_AU;

    /** _EZUpdateDateTime_AK*/
	public final EZDBStringItem              ezUpTime_AK;

    /** _EZUpTimeZone_A1*/
	public final EZDBStringItem              ezUpTimeZone_A1;

    /** XX_FULL_NM_AU*/
	public final EZDBStringItem              xxFullNm_AU;

    /** XX_INS_UPD_DEL_FLG_A1*/
	public final EZDBStringItem              xxInsUpdDelFlg_A1;


	/**
	 * NMAL7060_ABMsg is constructor.
	 * The initialization when the instance of NMAL7060_ABMsg is generated.
	 */
	public NMAL7060_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7060_ABMsg is constructor.
	 * The initialization when the instance of NMAL7060_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7060_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcMtrPkgMdlPk_A1 = (EZDBBigDecimalItem)newItem("prcMtrPkgMdlPk_A1");
		mdlId_A1 = (EZDBBigDecimalItem)newItem("mdlId_A1");
		mtrGrpPk_A1 = (EZDBBigDecimalItem)newItem("mtrGrpPk_A1");
		xxLinkAncr_AM = (EZDBStringItem)newItem("xxLinkAncr_AM");
		mdlNm_A1 = (EZDBStringItem)newItem("mdlNm_A1");
		effFromDt_A1 = (EZDBDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDBDateItem)newItem("effThruDt_A1");
		xxDt10Dt_AI = (EZDBDateItem)newItem("xxDt10Dt_AI");
		ezInTime_AK = (EZDBStringItem)newItem("ezInTime_AK");
		ezInTimeZone_A1 = (EZDBStringItem)newItem("ezInTimeZone_A1");
		xxFullNm_AI = (EZDBStringItem)newItem("xxFullNm_AI");
		xxDt10Dt_AU = (EZDBDateItem)newItem("xxDt10Dt_AU");
		ezUpTime_AK = (EZDBStringItem)newItem("ezUpTime_AK");
		ezUpTimeZone_A1 = (EZDBStringItem)newItem("ezUpTimeZone_A1");
		xxFullNm_AU = (EZDBStringItem)newItem("xxFullNm_AU");
		xxInsUpdDelFlg_A1 = (EZDBStringItem)newItem("xxInsUpdDelFlg_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7060_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7060_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcMtrPkgMdlPk_A1", "prcMtrPkgMdlPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId_A1", "mdlId_A1", "A1", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mtrGrpPk_A1", "mtrGrpPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxLinkAncr_AM", "xxLinkAncr_AM", "AM", null, TYPE_HANKAKUEISU, "30", null},
	{"mdlNm_A1", "mdlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDt10Dt_AI", "xxDt10Dt_AI", "AI", null, TYPE_NENTSUKIHI, "8", null},
	{"ezInTime_AK", "ezInTime_AK", "AK", null, TYPE_HANKAKUEISU, "17", null},
	{"ezInTimeZone_A1", "ezInTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxFullNm_AI", "xxFullNm_AI", "AI", null, TYPE_HANKAKUEISU, "100", null},
	{"xxDt10Dt_AU", "xxDt10Dt_AU", "AU", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_AK", "ezUpTime_AK", "AK", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxFullNm_AU", "xxFullNm_AU", "AU", null, TYPE_HANKAKUEISU, "100", null},
	{"xxInsUpdDelFlg_A1", "xxInsUpdDelFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_MTR_PKG_MDL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgMdlPk_A1
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_A1
        {"MTR_GRP_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrGrpPk_A1
        {"XX_LINK_ANCR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_AM
        {"MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_A1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A1
        {"XX_DT_10_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxDt10Dt_AI
        {"_EZRegistrationDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_AK
        {"_EZInTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTimeZone_A1
        {"XX_FULL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_AI
        {"XX_DT_10_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxDt10Dt_AU
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_AK
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"XX_FULL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_AU
        {"XX_INS_UPD_DEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInsUpdDelFlg_A1
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
