//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231106144517000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700_ABMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NMAL6700_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** LOC_NUM_A1*/
	public final EZDBStringItem              locNum_A1;

    /** FIRST_LINE_ADDR_A1*/
	public final EZDBStringItem              firstLineAddr_A1;

    /** SCD_LINE_ADDR_A1*/
	public final EZDBStringItem              scdLineAddr_A1;

    /** CTY_ADDR_A1*/
	public final EZDBStringItem              ctyAddr_A1;

    /** ST_CD_A1*/
	public final EZDBStringItem              stCd_A1;

    /** POST_CD_A1*/
	public final EZDBStringItem              postCd_A1;

    /** XX_CHK_BOX_AB*/
	public final EZDBStringItem              xxChkBox_AB;

    /** XX_CHK_BOX_AS*/
	public final EZDBStringItem              xxChkBox_AS;

    /** XX_CHK_BOX_AP*/
	public final EZDBStringItem              xxChkBox_AP;

    /** XX_CHK_BOX_AH*/
	public final EZDBStringItem              xxChkBox_AH;

    /** XX_RELN_DS_ACCT_TXT_A1*/
	public final EZDBStringItem              xxRelnDsAcctTxt_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDBDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDBDateItem              effThruDt_A1;

    /** DS_ACCT_STS_NM_A1*/
	public final EZDBStringItem              dsAcctStsNm_A1;

    /** CTRY_CD_A1*/
	public final EZDBStringItem              ctryCd_A1;

    /** THIRD_LINE_ADDR_A1*/
	public final EZDBStringItem              thirdLineAddr_A1;

    /** FRTH_LINE_ADDR_A1*/
	public final EZDBStringItem              frthLineAddr_A1;

    /** CNTY_NM_A1*/
	public final EZDBStringItem              cntyNm_A1;

    /** PROV_NM_A1*/
	public final EZDBStringItem              provNm_A1;

    /** BILL_TO_EFF_FROM_DT_TXT_A1*/
	public final EZDBStringItem              billToEffFromDtTxt_A1;

    /** BILL_TO_EFF_THRU_DT_TXT_A1*/
	public final EZDBStringItem              billToEffThruDtTxt_A1;

    /** SHIP_TO_EFF_FROM_DT_TXT_A1*/
	public final EZDBStringItem              shipToEffFromDtTxt_A1;

    /** SHIP_TO_EFF_THRU_DT_TXT_A1*/
	public final EZDBStringItem              shipToEffThruDtTxt_A1;

    /** DS_LOC_NM_A1*/
	public final EZDBStringItem              dsLocNm_A1;

    /** LINE_BIZ_TP_CD_A1*/
	public final EZDBStringItem              lineBizTpCd_A1;

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
	 * NMAL6700_ABMsg is constructor.
	 * The initialization when the instance of NMAL6700_ABMsg is generated.
	 */
	public NMAL6700_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6700_ABMsg is constructor.
	 * The initialization when the instance of NMAL6700_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6700_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		locNum_A1 = (EZDBStringItem)newItem("locNum_A1");
		firstLineAddr_A1 = (EZDBStringItem)newItem("firstLineAddr_A1");
		scdLineAddr_A1 = (EZDBStringItem)newItem("scdLineAddr_A1");
		ctyAddr_A1 = (EZDBStringItem)newItem("ctyAddr_A1");
		stCd_A1 = (EZDBStringItem)newItem("stCd_A1");
		postCd_A1 = (EZDBStringItem)newItem("postCd_A1");
		xxChkBox_AB = (EZDBStringItem)newItem("xxChkBox_AB");
		xxChkBox_AS = (EZDBStringItem)newItem("xxChkBox_AS");
		xxChkBox_AP = (EZDBStringItem)newItem("xxChkBox_AP");
		xxChkBox_AH = (EZDBStringItem)newItem("xxChkBox_AH");
		xxRelnDsAcctTxt_A1 = (EZDBStringItem)newItem("xxRelnDsAcctTxt_A1");
		effFromDt_A1 = (EZDBDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDBDateItem)newItem("effThruDt_A1");
		dsAcctStsNm_A1 = (EZDBStringItem)newItem("dsAcctStsNm_A1");
		ctryCd_A1 = (EZDBStringItem)newItem("ctryCd_A1");
		thirdLineAddr_A1 = (EZDBStringItem)newItem("thirdLineAddr_A1");
		frthLineAddr_A1 = (EZDBStringItem)newItem("frthLineAddr_A1");
		cntyNm_A1 = (EZDBStringItem)newItem("cntyNm_A1");
		provNm_A1 = (EZDBStringItem)newItem("provNm_A1");
		billToEffFromDtTxt_A1 = (EZDBStringItem)newItem("billToEffFromDtTxt_A1");
		billToEffThruDtTxt_A1 = (EZDBStringItem)newItem("billToEffThruDtTxt_A1");
		shipToEffFromDtTxt_A1 = (EZDBStringItem)newItem("shipToEffFromDtTxt_A1");
		shipToEffThruDtTxt_A1 = (EZDBStringItem)newItem("shipToEffThruDtTxt_A1");
		dsLocNm_A1 = (EZDBStringItem)newItem("dsLocNm_A1");
		lineBizTpCd_A1 = (EZDBStringItem)newItem("lineBizTpCd_A1");
		xxRecHistCratTs_A1 = (EZDBStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDBStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDBStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDBStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6700_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6700_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"locNum_A1", "locNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"firstLineAddr_A1", "firstLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr_A1", "scdLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_A1", "ctyAddr_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_A1", "stCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_A1", "postCd_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"xxChkBox_AB", "xxChkBox_AB", "AB", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_AS", "xxChkBox_AS", "AS", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_AP", "xxChkBox_AP", "AP", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_AH", "xxChkBox_AH", "AH", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxRelnDsAcctTxt_A1", "xxRelnDsAcctTxt_A1", "A1", null, TYPE_HANKAKUEISU, "400", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsAcctStsNm_A1", "dsAcctStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"ctryCd_A1", "ctryCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"thirdLineAddr_A1", "thirdLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr_A1", "frthLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"cntyNm_A1", "cntyNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"provNm_A1", "provNm_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"billToEffFromDtTxt_A1", "billToEffFromDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"billToEffThruDtTxt_A1", "billToEffThruDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"shipToEffFromDtTxt_A1", "shipToEffFromDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"shipToEffThruDtTxt_A1", "shipToEffThruDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"dsLocNm_A1", "dsLocNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"lineBizTpCd_A1", "lineBizTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
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

        {"LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A1
        {"FIRST_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_A1
        {"SCD_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr_A1
        {"CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_A1
        {"ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_A1
        {"POST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_A1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AB
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AS
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AP
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AH
        {"XX_RELN_DS_ACCT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRelnDsAcctTxt_A1
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A1
        {"DS_ACCT_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctStsNm_A1
        {"CTRY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd_A1
        {"THIRD_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr_A1
        {"FRTH_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr_A1
        {"CNTY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm_A1
        {"PROV_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//provNm_A1
        {"BILL_TO_EFF_FROM_DT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToEffFromDtTxt_A1
        {"BILL_TO_EFF_THRU_DT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToEffThruDtTxt_A1
        {"SHIP_TO_EFF_FROM_DT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToEffFromDtTxt_A1
        {"SHIP_TO_EFF_THRU_DT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToEffThruDtTxt_A1
        {"DS_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocNm_A1
        {"LINE_BIZ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_A1
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

