//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231219170400000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0110_EBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0110 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0110_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_E1*/
	public final EZDBStringItem              xxChkBox_E1;

    /** DS_ACCT_NUM_EB*/
	public final EZDBStringItem              dsAcctNum_EB;

    /** DS_ACCT_NUM_E1*/
	public final EZDBStringItem              dsAcctNum_E1;

    /** DS_ACCT_NM_E1*/
	public final EZDBStringItem              dsAcctNm_E1;

    /** CUST_MDSE_CD_EB*/
	public final EZDBStringItem              custMdseCd_EB;

    /** CUST_MDSE_CD_E1*/
	public final EZDBStringItem              custMdseCd_E1;

    /** CUST_MDSE_NM_E1*/
	public final EZDBStringItem              custMdseNm_E1;

    /** EFF_FROM_DT_E1*/
	public final EZDBDateItem              effFromDt_E1;

    /** XX_CHK_BOX_EN*/
	public final EZDBStringItem              xxChkBox_EN;

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
	 * NMAL0110_EBMsg is constructor.
	 * The initialization when the instance of NMAL0110_EBMsg is generated.
	 */
	public NMAL0110_EBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0110_EBMsg is constructor.
	 * The initialization when the instance of NMAL0110_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0110_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_E1 = (EZDBStringItem)newItem("xxChkBox_E1");
		dsAcctNum_EB = (EZDBStringItem)newItem("dsAcctNum_EB");
		dsAcctNum_E1 = (EZDBStringItem)newItem("dsAcctNum_E1");
		dsAcctNm_E1 = (EZDBStringItem)newItem("dsAcctNm_E1");
		custMdseCd_EB = (EZDBStringItem)newItem("custMdseCd_EB");
		custMdseCd_E1 = (EZDBStringItem)newItem("custMdseCd_E1");
		custMdseNm_E1 = (EZDBStringItem)newItem("custMdseNm_E1");
		effFromDt_E1 = (EZDBDateItem)newItem("effFromDt_E1");
		xxChkBox_EN = (EZDBStringItem)newItem("xxChkBox_EN");
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
	 * @return NMAL0110_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0110_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_E1", "xxChkBox_E1", "E1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsAcctNum_EB", "dsAcctNum_EB", "EB", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_E1", "dsAcctNum_E1", "E1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_E1", "dsAcctNm_E1", "E1", null, TYPE_HANKAKUEISU, "360", null},
	{"custMdseCd_EB", "custMdseCd_EB", "EB", null, TYPE_HANKAKUEISU, "30", null},
	{"custMdseCd_E1", "custMdseCd_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"custMdseNm_E1", "custMdseNm_E1", "E1", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_E1", "effFromDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_EN", "xxChkBox_EN", "EN", null, TYPE_HANKAKUEIJI, "1", null},
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
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_EB
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_E1
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_E1
        {"CUST_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMdseCd_EB
        {"CUST_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMdseCd_E1
        {"CUST_MDSE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMdseNm_E1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_E1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_EN
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_E1
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_E1
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
