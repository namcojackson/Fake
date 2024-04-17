//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230208094927000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6730_KCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6730 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6730_KCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_REF_ATTRB_PK_K1*/
	public final EZDCBigDecimalItem              dsAcctRefAttrbPk_K1;

    /** XX_CHK_BOX_K1*/
	public final EZDCStringItem              xxChkBox_K1;

    /** DS_BIZ_AREA_CD_K1*/
	public final EZDCStringItemArray              dsBizAreaCd_K1;

    /** DS_BIZ_AREA_NM_K2*/
	public final EZDCStringItemArray              dsBizAreaNm_K2;

    /** DS_BIZ_AREA_CD_K3*/
	public final EZDCStringItem              dsBizAreaCd_K3;

    /** XX_CTL_NM_K1*/
	public final EZDCStringItem              xxCtlNm_K1;

    /** DS_ACCT_REF_ATTRB_NUM_K1*/
	public final EZDCStringItem              dsAcctRefAttrbNum_K1;

    /** BLLG_ATTRB_NM_K1*/
	public final EZDCStringItem              bllgAttrbNm_K1;

    /** BLLG_ATTRB_VAL_TXT_K1*/
	public final EZDCStringItem              bllgAttrbValTxt_K1;

    /** BLLG_ATTRB_ENBL_FLG_K1*/
	public final EZDCStringItem              bllgAttrbEnblFlg_K1;

    /** BLLG_ATTRB_REQ_FLG_K1*/
	public final EZDCStringItem              bllgAttrbReqFlg_K1;

    /** CUST_EFF_LVL_CD_K3*/
	public final EZDCStringItem              custEffLvlCd_K3;

    /** _EZUpdateDateTime_K1*/
	public final EZDCStringItem              ezUpTime_K1;

    /** _EZUpTimeZone_K1*/
	public final EZDCStringItem              ezUpTimeZone_K1;

    /** XX_REC_HIST_CRAT_TS_K1*/
	public final EZDCStringItem              xxRecHistCratTs_K1;

    /** XX_REC_HIST_CRAT_BY_NM_K1*/
	public final EZDCStringItem              xxRecHistCratByNm_K1;

    /** XX_REC_HIST_UPD_TS_K1*/
	public final EZDCStringItem              xxRecHistUpdTs_K1;

    /** XX_REC_HIST_UPD_BY_NM_K1*/
	public final EZDCStringItem              xxRecHistUpdByNm_K1;

    /** XX_REC_HIST_TBL_NM_K1*/
	public final EZDCStringItem              xxRecHistTblNm_K1;


	/**
	 * NMAL6730_KCMsg is constructor.
	 * The initialization when the instance of NMAL6730_KCMsg is generated.
	 */
	public NMAL6730_KCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6730_KCMsg is constructor.
	 * The initialization when the instance of NMAL6730_KCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6730_KCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctRefAttrbPk_K1 = (EZDCBigDecimalItem)newItem("dsAcctRefAttrbPk_K1");
		xxChkBox_K1 = (EZDCStringItem)newItem("xxChkBox_K1");
		dsBizAreaCd_K1 = (EZDCStringItemArray)newItemArray("dsBizAreaCd_K1");
		dsBizAreaNm_K2 = (EZDCStringItemArray)newItemArray("dsBizAreaNm_K2");
		dsBizAreaCd_K3 = (EZDCStringItem)newItem("dsBizAreaCd_K3");
		xxCtlNm_K1 = (EZDCStringItem)newItem("xxCtlNm_K1");
		dsAcctRefAttrbNum_K1 = (EZDCStringItem)newItem("dsAcctRefAttrbNum_K1");
		bllgAttrbNm_K1 = (EZDCStringItem)newItem("bllgAttrbNm_K1");
		bllgAttrbValTxt_K1 = (EZDCStringItem)newItem("bllgAttrbValTxt_K1");
		bllgAttrbEnblFlg_K1 = (EZDCStringItem)newItem("bllgAttrbEnblFlg_K1");
		bllgAttrbReqFlg_K1 = (EZDCStringItem)newItem("bllgAttrbReqFlg_K1");
		custEffLvlCd_K3 = (EZDCStringItem)newItem("custEffLvlCd_K3");
		ezUpTime_K1 = (EZDCStringItem)newItem("ezUpTime_K1");
		ezUpTimeZone_K1 = (EZDCStringItem)newItem("ezUpTimeZone_K1");
		xxRecHistCratTs_K1 = (EZDCStringItem)newItem("xxRecHistCratTs_K1");
		xxRecHistCratByNm_K1 = (EZDCStringItem)newItem("xxRecHistCratByNm_K1");
		xxRecHistUpdTs_K1 = (EZDCStringItem)newItem("xxRecHistUpdTs_K1");
		xxRecHistUpdByNm_K1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_K1");
		xxRecHistTblNm_K1 = (EZDCStringItem)newItem("xxRecHistTblNm_K1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6730_KCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6730_KCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctRefAttrbPk_K1", "dsAcctRefAttrbPk_K1", "K1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_K1", "xxChkBox_K1", "K1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsBizAreaCd_K1", "dsBizAreaCd_K1", "K1", "99", TYPE_HANKAKUEISU, "2", null},
	{"dsBizAreaNm_K2", "dsBizAreaNm_K2", "K2", "99", TYPE_HANKAKUEISU, "60", null},
	{"dsBizAreaCd_K3", "dsBizAreaCd_K3", "K3", null, TYPE_HANKAKUEISU, "2", null},
	{"xxCtlNm_K1", "xxCtlNm_K1", "K1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsAcctRefAttrbNum_K1", "dsAcctRefAttrbNum_K1", "K1", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgAttrbNm_K1", "bllgAttrbNm_K1", "K1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgAttrbValTxt_K1", "bllgAttrbValTxt_K1", "K1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgAttrbEnblFlg_K1", "bllgAttrbEnblFlg_K1", "K1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgAttrbReqFlg_K1", "bllgAttrbReqFlg_K1", "K1", null, TYPE_HANKAKUEISU, "1", null},
	{"custEffLvlCd_K3", "custEffLvlCd_K3", "K3", null, TYPE_HANKAKUEISU, "2", null},
	{"ezUpTime_K1", "ezUpTime_K1", "K1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_K1", "ezUpTimeZone_K1", "K1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_K1", "xxRecHistCratTs_K1", "K1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_K1", "xxRecHistCratByNm_K1", "K1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_K1", "xxRecHistUpdTs_K1", "K1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_K1", "xxRecHistUpdByNm_K1", "K1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_K1", "xxRecHistTblNm_K1", "K1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_REF_ATTRB_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRefAttrbPk_K1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_K1
        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd_K1
        {"DS_BIZ_AREA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaNm_K2
        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd_K3
        {"XX_CTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCtlNm_K1
        {"DS_ACCT_REF_ATTRB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRefAttrbNum_K1
        {"BLLG_ATTRB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbNm_K1
        {"BLLG_ATTRB_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbValTxt_K1
        {"BLLG_ATTRB_ENBL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbEnblFlg_K1
        {"BLLG_ATTRB_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbReqFlg_K1
        {"CUST_EFF_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custEffLvlCd_K3
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_K1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_K1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_K1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_K1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_K1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_K1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_K1
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

