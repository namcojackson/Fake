//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161128151027000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0320_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0320;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0320 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0320_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_AR*/
	public final EZDBStringItem              ezUpTime_AR;

    /** _EZUpTimeZone_AR*/
	public final EZDBStringItem              ezUpTimeZone_AR;

    /** CONTR_PHYS_BLLG_MTR_RELN_PK*/
	public final EZDBBigDecimalItem              contrPhysBllgMtrRelnPk;

    /** _EZUpdateDateTime_AB*/
	public final EZDBStringItem              ezUpTime_AB;

    /** _EZUpTimeZone_AB*/
	public final EZDBStringItem              ezUpTimeZone_AB;

    /** DS_CONTR_BLLG_MTR_PK*/
	public final EZDBBigDecimalItem              dsContrBllgMtrPk;

    /** SVC_PHYS_MTR_PK*/
	public final EZDBBigDecimalItem              svcPhysMtrPk;

    /** MTR_ENTRY_MND_FLG*/
	public final EZDBStringItem              mtrEntryMndFlg;

    /** MDL_MTR_LB_CD*/
	public final EZDBStringItem              mdlMtrLbCd;

    /** MTR_LB_DESC_TXT_AP*/
	public final EZDBStringItem              mtrLbDescTxt_AP;

    /** BLLBL_FLG*/
	public final EZDBStringItem              bllblFlg;

    /** CONTR_MTR_MULT_RATE*/
	public final EZDBBigDecimalItem              contrMtrMultRate;

    /** BLLG_MTR_MAP_LVL_NUM*/
	public final EZDBStringItem              bllgMtrMapLvlNum;

    /** BLLG_MTR_LB_CD*/
	public final EZDBStringItem              bllgMtrLbCd;

    /** BLLG_MTR_LB_CD_PR*/
	public final EZDBStringItem              bllgMtrLbCd_PR;

    /** BLLG_MTR_LB_CD_AB*/
	public final EZDBStringItemArray              bllgMtrLbCd_AB;

    /** MTR_LB_DESC_TXT_AB*/
	public final EZDBStringItemArray              mtrLbDescTxt_AB;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDBStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDBStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDBStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDBStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDBStringItem              xxRecHistTblNm_A1;


	/**
	 * NSAL0320_ABMsg is constructor.
	 * The initialization when the instance of NSAL0320_ABMsg is generated.
	 */
	public NSAL0320_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0320_ABMsg is constructor.
	 * The initialization when the instance of NSAL0320_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0320_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_AR = (EZDBStringItem)newItem("ezUpTime_AR");
		ezUpTimeZone_AR = (EZDBStringItem)newItem("ezUpTimeZone_AR");
		contrPhysBllgMtrRelnPk = (EZDBBigDecimalItem)newItem("contrPhysBllgMtrRelnPk");
		ezUpTime_AB = (EZDBStringItem)newItem("ezUpTime_AB");
		ezUpTimeZone_AB = (EZDBStringItem)newItem("ezUpTimeZone_AB");
		dsContrBllgMtrPk = (EZDBBigDecimalItem)newItem("dsContrBllgMtrPk");
		svcPhysMtrPk = (EZDBBigDecimalItem)newItem("svcPhysMtrPk");
		mtrEntryMndFlg = (EZDBStringItem)newItem("mtrEntryMndFlg");
		mdlMtrLbCd = (EZDBStringItem)newItem("mdlMtrLbCd");
		mtrLbDescTxt_AP = (EZDBStringItem)newItem("mtrLbDescTxt_AP");
		bllblFlg = (EZDBStringItem)newItem("bllblFlg");
		contrMtrMultRate = (EZDBBigDecimalItem)newItem("contrMtrMultRate");
		bllgMtrMapLvlNum = (EZDBStringItem)newItem("bllgMtrMapLvlNum");
		bllgMtrLbCd = (EZDBStringItem)newItem("bllgMtrLbCd");
		bllgMtrLbCd_PR = (EZDBStringItem)newItem("bllgMtrLbCd_PR");
		bllgMtrLbCd_AB = (EZDBStringItemArray)newItemArray("bllgMtrLbCd_AB");
		mtrLbDescTxt_AB = (EZDBStringItemArray)newItemArray("mtrLbDescTxt_AB");
		xxRecHistCratTs_A1 = (EZDBStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDBStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDBStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDBStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0320_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0320_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_AR", "ezUpTime_AR", "AR", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_AR", "ezUpTimeZone_AR", "AR", null, TYPE_HANKAKUEISU, "5", null},
	{"contrPhysBllgMtrRelnPk", "contrPhysBllgMtrRelnPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_AB", "ezUpTime_AB", "AB", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_AB", "ezUpTimeZone_AB", "AB", null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrBllgMtrPk", "dsContrBllgMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcPhysMtrPk", "svcPhysMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrEntryMndFlg", "mtrEntryMndFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"mdlMtrLbCd", "mdlMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_AP", "mtrLbDescTxt_AP", "AP", null, TYPE_HANKAKUEISU, "50", null},
	{"bllblFlg", "bllblFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"contrMtrMultRate", "contrMtrMultRate", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"bllgMtrMapLvlNum", "bllgMtrMapLvlNum", null, null, TYPE_HANKAKUEISU, "2", null},
	{"bllgMtrLbCd", "bllgMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"bllgMtrLbCd_PR", "bllgMtrLbCd_PR", "PR", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgMtrLbCd_AB", "bllgMtrLbCd_AB", "AB", "99", TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_AB", "mtrLbDescTxt_AB", "AB", "99", TYPE_HANKAKUEISU, "50", null},
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

        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_AR
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_AR
        {"CONTR_PHYS_BLLG_MTR_RELN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrPhysBllgMtrRelnPk
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_AB
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_AB
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk
        {"SVC_PHYS_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrPk
        {"MTR_ENTRY_MND_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEntryMndFlg
        {"MDL_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlMtrLbCd
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_AP
        {"BLLBL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllblFlg
        {"CONTR_MTR_MULT_RATE",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//contrMtrMultRate
        {"BLLG_MTR_MAP_LVL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrMapLvlNum
        {"BLLG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
        {"BLLG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_PR
        {"BLLG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_AB
        {"MTR_LB_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_AB
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
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

