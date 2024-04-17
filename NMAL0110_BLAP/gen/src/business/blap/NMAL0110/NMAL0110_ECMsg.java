//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231219170257000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0110_ECMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0110 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0110_ECMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_E1*/
	public final EZDCStringItem              xxChkBox_E1;

    /** DS_ACCT_NUM_EB*/
	public final EZDCStringItem              dsAcctNum_EB;

    /** DS_ACCT_NUM_E1*/
	public final EZDCStringItem              dsAcctNum_E1;

    /** DS_ACCT_NM_E1*/
	public final EZDCStringItem              dsAcctNm_E1;

    /** CUST_MDSE_CD_EB*/
	public final EZDCStringItem              custMdseCd_EB;

    /** CUST_MDSE_CD_E1*/
	public final EZDCStringItem              custMdseCd_E1;

    /** CUST_MDSE_NM_E1*/
	public final EZDCStringItem              custMdseNm_E1;

    /** EFF_FROM_DT_E1*/
	public final EZDCDateItem              effFromDt_E1;

    /** XX_CHK_BOX_EN*/
	public final EZDCStringItem              xxChkBox_EN;

    /** _EZUpdateDateTime_E1*/
	public final EZDCStringItem              ezUpTime_E1;

    /** _EZUpTimeZone_E1*/
	public final EZDCStringItem              ezUpTimeZone_E1;

    /** XX_REC_HIST_CRAT_TS_E1*/
	public final EZDCStringItem              xxRecHistCratTs_E1;

    /** XX_REC_HIST_CRAT_BY_NM_E1*/
	public final EZDCStringItem              xxRecHistCratByNm_E1;

    /** XX_REC_HIST_UPD_TS_E1*/
	public final EZDCStringItem              xxRecHistUpdTs_E1;

    /** XX_REC_HIST_UPD_BY_NM_E1*/
	public final EZDCStringItem              xxRecHistUpdByNm_E1;

    /** XX_REC_HIST_TBL_NM_E1*/
	public final EZDCStringItem              xxRecHistTblNm_E1;


	/**
	 * NMAL0110_ECMsg is constructor.
	 * The initialization when the instance of NMAL0110_ECMsg is generated.
	 */
	public NMAL0110_ECMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0110_ECMsg is constructor.
	 * The initialization when the instance of NMAL0110_ECMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0110_ECMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_E1 = (EZDCStringItem)newItem("xxChkBox_E1");
		dsAcctNum_EB = (EZDCStringItem)newItem("dsAcctNum_EB");
		dsAcctNum_E1 = (EZDCStringItem)newItem("dsAcctNum_E1");
		dsAcctNm_E1 = (EZDCStringItem)newItem("dsAcctNm_E1");
		custMdseCd_EB = (EZDCStringItem)newItem("custMdseCd_EB");
		custMdseCd_E1 = (EZDCStringItem)newItem("custMdseCd_E1");
		custMdseNm_E1 = (EZDCStringItem)newItem("custMdseNm_E1");
		effFromDt_E1 = (EZDCDateItem)newItem("effFromDt_E1");
		xxChkBox_EN = (EZDCStringItem)newItem("xxChkBox_EN");
		ezUpTime_E1 = (EZDCStringItem)newItem("ezUpTime_E1");
		ezUpTimeZone_E1 = (EZDCStringItem)newItem("ezUpTimeZone_E1");
		xxRecHistCratTs_E1 = (EZDCStringItem)newItem("xxRecHistCratTs_E1");
		xxRecHistCratByNm_E1 = (EZDCStringItem)newItem("xxRecHistCratByNm_E1");
		xxRecHistUpdTs_E1 = (EZDCStringItem)newItem("xxRecHistUpdTs_E1");
		xxRecHistUpdByNm_E1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_E1");
		xxRecHistTblNm_E1 = (EZDCStringItem)newItem("xxRecHistTblNm_E1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0110_ECMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0110_ECMsgArray();
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

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_E1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_EB
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_E1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_E1
        {"CUST_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMdseCd_EB
        {"CUST_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMdseCd_E1
        {"CUST_MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMdseNm_E1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_E1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_EN
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_E1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_E1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_E1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_E1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_E1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_E1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_E1
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

