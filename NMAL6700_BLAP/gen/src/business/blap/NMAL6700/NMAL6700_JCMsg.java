//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231106120030000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700_JCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6700 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6700_JCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CUST_SPCL_MSG_PK_J1*/
	public final EZDCBigDecimalItem              dsCustSpclMsgPk_J1;

    /** XX_CHK_BOX_J1*/
	public final EZDCStringItem              xxChkBox_J1;

    /** DS_ACCT_NUM_J1*/
	public final EZDCStringItem              dsAcctNum_J1;

    /** DS_ACCT_NM_J1*/
	public final EZDCStringItem              dsAcctNm_J1;

    /** LINE_BIZ_TP_CD_J1*/
	public final EZDCStringItemArray              lineBizTpCd_J1;

    /** LINE_BIZ_TP_NM_J2*/
	public final EZDCStringItemArray              lineBizTpNm_J2;

    /** LINE_BIZ_TP_CD_J3*/
	public final EZDCStringItem              lineBizTpCd_J3;

    /** DS_BIZ_AREA_CD_J1*/
	public final EZDCStringItemArray              dsBizAreaCd_J1;

    /** DS_BIZ_AREA_NM_J2*/
	public final EZDCStringItemArray              dsBizAreaNm_J2;

    /** DS_BIZ_AREA_CD_J3*/
	public final EZDCStringItem              dsBizAreaCd_J3;

    /** DS_CUST_MSG_TP_CD_J1*/
	public final EZDCStringItemArray              dsCustMsgTpCd_J1;

    /** DS_CUST_MSG_TP_NM_J2*/
	public final EZDCStringItemArray              dsCustMsgTpNm_J2;

    /** DS_CUST_MSG_TP_CD_J3*/
	public final EZDCStringItem              dsCustMsgTpCd_J3;

    /** CUST_EFF_LVL_CD_J1*/
	public final EZDCStringItemArray              custEffLvlCd_J1;

    /** CUST_EFF_LVL_NM_J2*/
	public final EZDCStringItemArray              custEffLvlNm_J2;

    /** CUST_EFF_LVL_CD_J3*/
	public final EZDCStringItem              custEffLvlCd_J3;

    /** DS_CUST_MSG_TXT_J1*/
	public final EZDCStringItem              dsCustMsgTxt_J1;

    /** DS_PRINT_ON_INV_FLG_J1*/
	public final EZDCStringItem              dsPrintOnInvFlg_J1;

    /** EFF_FROM_DT_J1*/
	public final EZDCDateItem              effFromDt_J1;

    /** EFF_THRU_DT_J1*/
	public final EZDCDateItem              effThruDt_J1;

    /** _EZUpdateDateTime_J1*/
	public final EZDCStringItem              ezUpTime_J1;

    /** _EZUpTimeZone_J1*/
	public final EZDCStringItem              ezUpTimeZone_J1;

    /** XX_REC_HIST_CRAT_TS_J1*/
	public final EZDCStringItem              xxRecHistCratTs_J1;

    /** XX_REC_HIST_CRAT_BY_NM_J1*/
	public final EZDCStringItem              xxRecHistCratByNm_J1;

    /** XX_REC_HIST_UPD_TS_J1*/
	public final EZDCStringItem              xxRecHistUpdTs_J1;

    /** XX_REC_HIST_UPD_BY_NM_J1*/
	public final EZDCStringItem              xxRecHistUpdByNm_J1;

    /** XX_REC_HIST_TBL_NM_J1*/
	public final EZDCStringItem              xxRecHistTblNm_J1;


	/**
	 * NMAL6700_JCMsg is constructor.
	 * The initialization when the instance of NMAL6700_JCMsg is generated.
	 */
	public NMAL6700_JCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6700_JCMsg is constructor.
	 * The initialization when the instance of NMAL6700_JCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6700_JCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsCustSpclMsgPk_J1 = (EZDCBigDecimalItem)newItem("dsCustSpclMsgPk_J1");
		xxChkBox_J1 = (EZDCStringItem)newItem("xxChkBox_J1");
		dsAcctNum_J1 = (EZDCStringItem)newItem("dsAcctNum_J1");
		dsAcctNm_J1 = (EZDCStringItem)newItem("dsAcctNm_J1");
		lineBizTpCd_J1 = (EZDCStringItemArray)newItemArray("lineBizTpCd_J1");
		lineBizTpNm_J2 = (EZDCStringItemArray)newItemArray("lineBizTpNm_J2");
		lineBizTpCd_J3 = (EZDCStringItem)newItem("lineBizTpCd_J3");
		dsBizAreaCd_J1 = (EZDCStringItemArray)newItemArray("dsBizAreaCd_J1");
		dsBizAreaNm_J2 = (EZDCStringItemArray)newItemArray("dsBizAreaNm_J2");
		dsBizAreaCd_J3 = (EZDCStringItem)newItem("dsBizAreaCd_J3");
		dsCustMsgTpCd_J1 = (EZDCStringItemArray)newItemArray("dsCustMsgTpCd_J1");
		dsCustMsgTpNm_J2 = (EZDCStringItemArray)newItemArray("dsCustMsgTpNm_J2");
		dsCustMsgTpCd_J3 = (EZDCStringItem)newItem("dsCustMsgTpCd_J3");
		custEffLvlCd_J1 = (EZDCStringItemArray)newItemArray("custEffLvlCd_J1");
		custEffLvlNm_J2 = (EZDCStringItemArray)newItemArray("custEffLvlNm_J2");
		custEffLvlCd_J3 = (EZDCStringItem)newItem("custEffLvlCd_J3");
		dsCustMsgTxt_J1 = (EZDCStringItem)newItem("dsCustMsgTxt_J1");
		dsPrintOnInvFlg_J1 = (EZDCStringItem)newItem("dsPrintOnInvFlg_J1");
		effFromDt_J1 = (EZDCDateItem)newItem("effFromDt_J1");
		effThruDt_J1 = (EZDCDateItem)newItem("effThruDt_J1");
		ezUpTime_J1 = (EZDCStringItem)newItem("ezUpTime_J1");
		ezUpTimeZone_J1 = (EZDCStringItem)newItem("ezUpTimeZone_J1");
		xxRecHistCratTs_J1 = (EZDCStringItem)newItem("xxRecHistCratTs_J1");
		xxRecHistCratByNm_J1 = (EZDCStringItem)newItem("xxRecHistCratByNm_J1");
		xxRecHistUpdTs_J1 = (EZDCStringItem)newItem("xxRecHistUpdTs_J1");
		xxRecHistUpdByNm_J1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_J1");
		xxRecHistTblNm_J1 = (EZDCStringItem)newItem("xxRecHistTblNm_J1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6700_JCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6700_JCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsCustSpclMsgPk_J1", "dsCustSpclMsgPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_J1", "xxChkBox_J1", "J1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsAcctNum_J1", "dsAcctNum_J1", "J1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_J1", "dsAcctNm_J1", "J1", null, TYPE_HANKAKUEISU, "360", null},
	{"lineBizTpCd_J1", "lineBizTpCd_J1", "J1", "99", TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpNm_J2", "lineBizTpNm_J2", "J2", "99", TYPE_HANKAKUEISU, "70", null},
	{"lineBizTpCd_J3", "lineBizTpCd_J3", "J3", null, TYPE_HANKAKUEISU, "8", null},
	{"dsBizAreaCd_J1", "dsBizAreaCd_J1", "J1", "99", TYPE_HANKAKUEISU, "2", null},
	{"dsBizAreaNm_J2", "dsBizAreaNm_J2", "J2", "99", TYPE_HANKAKUEISU, "60", null},
	{"dsBizAreaCd_J3", "dsBizAreaCd_J3", "J3", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCustMsgTpCd_J1", "dsCustMsgTpCd_J1", "J1", "99", TYPE_HANKAKUEISU, "2", null},
	{"dsCustMsgTpNm_J2", "dsCustMsgTpNm_J2", "J2", "99", TYPE_HANKAKUEISU, "60", null},
	{"dsCustMsgTpCd_J3", "dsCustMsgTpCd_J3", "J3", null, TYPE_HANKAKUEISU, "2", null},
	{"custEffLvlCd_J1", "custEffLvlCd_J1", "J1", "99", TYPE_HANKAKUEISU, "2", null},
	{"custEffLvlNm_J2", "custEffLvlNm_J2", "J2", "99", TYPE_HANKAKUEISU, "60", null},
	{"custEffLvlCd_J3", "custEffLvlCd_J3", "J3", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCustMsgTxt_J1", "dsCustMsgTxt_J1", "J1", null, TYPE_HANKAKUEISU, "4000", null},
	{"dsPrintOnInvFlg_J1", "dsPrintOnInvFlg_J1", "J1", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_J1", "effFromDt_J1", "J1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_J1", "effThruDt_J1", "J1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_J1", "ezUpTime_J1", "J1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_J1", "ezUpTimeZone_J1", "J1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_J1", "xxRecHistCratTs_J1", "J1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_J1", "xxRecHistCratByNm_J1", "J1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_J1", "xxRecHistUpdTs_J1", "J1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_J1", "xxRecHistUpdByNm_J1", "J1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_J1", "xxRecHistTblNm_J1", "J1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CUST_SPCL_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSpclMsgPk_J1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_J1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_J1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_J1
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_J1
        {"LINE_BIZ_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpNm_J2
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_J3
        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd_J1
        {"DS_BIZ_AREA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaNm_J2
        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd_J3
        {"DS_CUST_MSG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustMsgTpCd_J1
        {"DS_CUST_MSG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustMsgTpNm_J2
        {"DS_CUST_MSG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustMsgTpCd_J3
        {"CUST_EFF_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custEffLvlCd_J1
        {"CUST_EFF_LVL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custEffLvlNm_J2
        {"CUST_EFF_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custEffLvlCd_J3
        {"DS_CUST_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustMsgTxt_J1
        {"DS_PRINT_ON_INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrintOnInvFlg_J1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_J1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_J1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_J1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_J1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_J1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_J1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_J1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_J1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_J1
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
