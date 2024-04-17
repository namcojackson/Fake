//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231108082939000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6720_EBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6720 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6720_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_E1*/
	public final EZDBStringItem              xxChkBox_E1;

    /** DS_CUST_SPCL_MSG_PK_E1*/
	public final EZDBBigDecimalItem              dsCustSpclMsgPk_E1;

    /** LINE_BIZ_TP_CD_P2*/
	public final EZDBStringItem              lineBizTpCd_P2;

    /** DS_BIZ_AREA_CD_P1*/
	public final EZDBStringItem              dsBizAreaCd_P1;

    /** DS_CUST_MSG_TP_CD_P1*/
	public final EZDBStringItem              dsCustMsgTpCd_P1;

    /** DS_CUST_MSG_TXT_E1*/
	public final EZDBStringItem              dsCustMsgTxt_E1;

    /** DS_PRINT_ON_INV_FLG_E1*/
	public final EZDBStringItem              dsPrintOnInvFlg_E1;

    /** EFF_THRU_DT_E1*/
	public final EZDBDateItem              effThruDt_E1;

    /** _EZUpdateDateTime_E1*/
	public final EZDBStringItem              ezUpTime_E1;

    /** _EZUpTimeZone_E1*/
	public final EZDBStringItem              ezUpTimeZone_E1;

    /** XX_REC_HIST_CRAT_TS_E1*/
	public final EZDBStringItem              xxRecHistCratTs_E1;

    /** XX_REC_HIST_CRAT_BY_NM_E1*/
	public final EZDBStringItem              xxRecHistCratByNm_E1;

    /** XX_REC_HIST_UPD_TS_E1*/
	public final EZDBStringItem              xxRecHistUpdTs_E1;

    /** XX_REC_HIST_UPD_BY_NM_E1*/
	public final EZDBStringItem              xxRecHistUpdByNm_E1;

    /** XX_REC_HIST_TBL_NM_E1*/
	public final EZDBStringItem              xxRecHistTblNm_E1;


	/**
	 * NMAL6720_EBMsg is constructor.
	 * The initialization when the instance of NMAL6720_EBMsg is generated.
	 */
	public NMAL6720_EBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6720_EBMsg is constructor.
	 * The initialization when the instance of NMAL6720_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6720_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_E1 = (EZDBStringItem)newItem("xxChkBox_E1");
		dsCustSpclMsgPk_E1 = (EZDBBigDecimalItem)newItem("dsCustSpclMsgPk_E1");
		lineBizTpCd_P2 = (EZDBStringItem)newItem("lineBizTpCd_P2");
		dsBizAreaCd_P1 = (EZDBStringItem)newItem("dsBizAreaCd_P1");
		dsCustMsgTpCd_P1 = (EZDBStringItem)newItem("dsCustMsgTpCd_P1");
		dsCustMsgTxt_E1 = (EZDBStringItem)newItem("dsCustMsgTxt_E1");
		dsPrintOnInvFlg_E1 = (EZDBStringItem)newItem("dsPrintOnInvFlg_E1");
		effThruDt_E1 = (EZDBDateItem)newItem("effThruDt_E1");
		ezUpTime_E1 = (EZDBStringItem)newItem("ezUpTime_E1");
		ezUpTimeZone_E1 = (EZDBStringItem)newItem("ezUpTimeZone_E1");
		xxRecHistCratTs_E1 = (EZDBStringItem)newItem("xxRecHistCratTs_E1");
		xxRecHistCratByNm_E1 = (EZDBStringItem)newItem("xxRecHistCratByNm_E1");
		xxRecHistUpdTs_E1 = (EZDBStringItem)newItem("xxRecHistUpdTs_E1");
		xxRecHistUpdByNm_E1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_E1");
		xxRecHistTblNm_E1 = (EZDBStringItem)newItem("xxRecHistTblNm_E1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6720_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6720_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_E1", "xxChkBox_E1", "E1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsCustSpclMsgPk_E1", "dsCustSpclMsgPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"lineBizTpCd_P2", "lineBizTpCd_P2", "P2", null, TYPE_HANKAKUEISU, "8", null},
	{"dsBizAreaCd_P1", "dsBizAreaCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCustMsgTpCd_P1", "dsCustMsgTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCustMsgTxt_E1", "dsCustMsgTxt_E1", "E1", null, TYPE_HANKAKUEISU, "4000", null},
	{"dsPrintOnInvFlg_E1", "dsPrintOnInvFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"effThruDt_E1", "effThruDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_E1", "ezUpTime_E1", "E1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_E1", "ezUpTimeZone_E1", "E1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_E1", "xxRecHistCratTs_E1", "E1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_E1", "xxRecHistCratByNm_E1", "E1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_E1", "xxRecHistUpdTs_E1", "E1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_E1", "xxRecHistUpdByNm_E1", "E1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_E1", "xxRecHistTblNm_E1", "E1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_E1
        {"DS_CUST_SPCL_MSG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSpclMsgPk_E1
        {"LINE_BIZ_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_P2
        {"DS_BIZ_AREA_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd_P1
        {"DS_CUST_MSG_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustMsgTpCd_P1
        {"DS_CUST_MSG_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustMsgTxt_E1
        {"DS_PRINT_ON_INV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrintOnInvFlg_E1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_E1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_E1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_E1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_E1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_E1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_E1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_E1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_E1
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

