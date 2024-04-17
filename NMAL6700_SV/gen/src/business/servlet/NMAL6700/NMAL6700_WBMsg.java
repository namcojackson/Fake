//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180821112920000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700_WBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6700 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6700_WBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_W1*/
	public final EZDBStringItem              xxChkBox_W1;

    /** VND_CD_W1*/
	public final EZDBStringItemArray              vndCd_W1;

    /** LOC_NM_W2*/
	public final EZDBStringItemArray              locNm_W2;

    /** VND_CD_W3*/
	public final EZDBStringItem              vndCd_W3;

    /** DS_CARR_ACCT_NUM_W1*/
	public final EZDBStringItem              dsCarrAcctNum_W1;

    /** EFF_FROM_DT_W1*/
	public final EZDBDateItem              effFromDt_W1;

    /** EFF_THRU_DT_W1*/
	public final EZDBDateItem              effThruDt_W1;

    /** XX_CHK_BOX_WD*/
	public final EZDBStringItem              xxChkBox_WD;

    /** XX_CHK_BOX_WS*/
	public final EZDBStringItem              xxChkBox_WS;

    /** DS_ACCT_CARR_PK_W1*/
	public final EZDBBigDecimalItem              dsAcctCarrPk_W1;

    /** _EZUpdateDateTime_W1*/
	public final EZDBStringItem              ezUpTime_W1;

    /** _EZUpTimeZone_W1*/
	public final EZDBStringItem              ezUpTimeZone_W1;

    /** XX_REC_HIST_CRAT_TS_W1*/
	public final EZDBStringItem              xxRecHistCratTs_W1;

    /** XX_REC_HIST_CRAT_BY_NM_W1*/
	public final EZDBStringItem              xxRecHistCratByNm_W1;

    /** XX_REC_HIST_UPD_TS_W1*/
	public final EZDBStringItem              xxRecHistUpdTs_W1;

    /** XX_REC_HIST_UPD_BY_NM_W1*/
	public final EZDBStringItem              xxRecHistUpdByNm_W1;

    /** XX_REC_HIST_TBL_NM_W1*/
	public final EZDBStringItem              xxRecHistTblNm_W1;


	/**
	 * NMAL6700_WBMsg is constructor.
	 * The initialization when the instance of NMAL6700_WBMsg is generated.
	 */
	public NMAL6700_WBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6700_WBMsg is constructor.
	 * The initialization when the instance of NMAL6700_WBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6700_WBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_W1 = (EZDBStringItem)newItem("xxChkBox_W1");
		vndCd_W1 = (EZDBStringItemArray)newItemArray("vndCd_W1");
		locNm_W2 = (EZDBStringItemArray)newItemArray("locNm_W2");
		vndCd_W3 = (EZDBStringItem)newItem("vndCd_W3");
		dsCarrAcctNum_W1 = (EZDBStringItem)newItem("dsCarrAcctNum_W1");
		effFromDt_W1 = (EZDBDateItem)newItem("effFromDt_W1");
		effThruDt_W1 = (EZDBDateItem)newItem("effThruDt_W1");
		xxChkBox_WD = (EZDBStringItem)newItem("xxChkBox_WD");
		xxChkBox_WS = (EZDBStringItem)newItem("xxChkBox_WS");
		dsAcctCarrPk_W1 = (EZDBBigDecimalItem)newItem("dsAcctCarrPk_W1");
		ezUpTime_W1 = (EZDBStringItem)newItem("ezUpTime_W1");
		ezUpTimeZone_W1 = (EZDBStringItem)newItem("ezUpTimeZone_W1");
		xxRecHistCratTs_W1 = (EZDBStringItem)newItem("xxRecHistCratTs_W1");
		xxRecHistCratByNm_W1 = (EZDBStringItem)newItem("xxRecHistCratByNm_W1");
		xxRecHistUpdTs_W1 = (EZDBStringItem)newItem("xxRecHistUpdTs_W1");
		xxRecHistUpdByNm_W1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_W1");
		xxRecHistTblNm_W1 = (EZDBStringItem)newItem("xxRecHistTblNm_W1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6700_WBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6700_WBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_W1", "xxChkBox_W1", "W1", null, TYPE_HANKAKUEIJI, "1", null},
	{"vndCd_W1", "vndCd_W1", "W1", "99", TYPE_HANKAKUEISU, "20", null},
	{"locNm_W2", "locNm_W2", "W2", "99", TYPE_HANKAKUEISU, "60", null},
	{"vndCd_W3", "vndCd_W3", "W3", null, TYPE_HANKAKUEISU, "20", null},
	{"dsCarrAcctNum_W1", "dsCarrAcctNum_W1", "W1", null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt_W1", "effFromDt_W1", "W1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_W1", "effThruDt_W1", "W1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_WD", "xxChkBox_WD", "WD", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_WS", "xxChkBox_WS", "WS", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsAcctCarrPk_W1", "dsAcctCarrPk_W1", "W1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_W1", "ezUpTime_W1", "W1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_W1", "ezUpTimeZone_W1", "W1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_W1", "xxRecHistCratTs_W1", "W1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_W1", "xxRecHistCratByNm_W1", "W1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_W1", "xxRecHistUpdTs_W1", "W1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_W1", "xxRecHistUpdByNm_W1", "W1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_W1", "xxRecHistTblNm_W1", "W1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_W1
        {"VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_W1
        {"LOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_W2
        {"VND_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_W3
        {"DS_CARR_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCarrAcctNum_W1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_W1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_W1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_WD
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_WS
        {"DS_ACCT_CARR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctCarrPk_W1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_W1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_W1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_W1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_W1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_W1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_W1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_W1
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

