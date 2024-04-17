//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221228092535000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8840BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NYEL8840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8840 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8840BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_WF_ASG_CD_F*/
	public final EZDBStringItem              xxWfAsgCd_F;

    /** XX_WF_ASG_CD_FK*/
	public final EZDBStringItem              xxWfAsgCd_FK;

    /** XX_WF_ASG_CD_FS*/
	public final EZDBStringItem              xxWfAsgCd_FS;

    /** XX_WF_ASG_NM_F*/
	public final EZDBStringItem              xxWfAsgNm_F;

    /** XX_WF_ASG_CD*/
	public final EZDBStringItem              xxWfAsgCd;

    /** XX_WF_ASG_CD_LK*/
	public final EZDBStringItem              xxWfAsgCd_LK;

    /** XX_WF_ASG_NM*/
	public final EZDBStringItem              xxWfAsgNm;

    /** WF_BIZ_APP_ID*/
	public final EZDBStringItem              wfBizAppId;

    /** WF_BIZ_APP_ID_L*/
	public final EZDBStringItemArray              wfBizAppId_L;

    /** WF_DESC_TXT_L*/
	public final EZDBStringItemArray              wfDescTxt_L;

    /** EFF_FROM_DT*/
	public final EZDBDateItem              effFromDt;

    /** EFF_FROM_HOUR_MN_H*/
	public final EZDBStringItem              effFromHourMn_H;

    /** EFF_FROM_HOUR_MN_FH*/
	public final EZDBStringItemArray              effFromHourMn_FH;

    /** WF_DESC_TXT_FH*/
	public final EZDBStringItemArray              wfDescTxt_FH;

    /** EFF_FROM_HOUR_MN_M*/
	public final EZDBStringItem              effFromHourMn_M;

    /** EFF_FROM_HOUR_MN_FM*/
	public final EZDBStringItemArray              effFromHourMn_FM;

    /** WF_DESC_TXT_FM*/
	public final EZDBStringItemArray              wfDescTxt_FM;

    /** EFF_THRU_DT*/
	public final EZDBDateItem              effThruDt;

    /** EFF_THRU_HOUR_MN_H*/
	public final EZDBStringItem              effThruHourMn_H;

    /** EFF_THRU_HOUR_MN_TH*/
	public final EZDBStringItemArray              effThruHourMn_TH;

    /** WF_DESC_TXT_TH*/
	public final EZDBStringItemArray              wfDescTxt_TH;

    /** EFF_THRU_HOUR_MN_M*/
	public final EZDBStringItem              effThruHourMn_M;

    /** EFF_THRU_HOUR_MN_TM*/
	public final EZDBStringItemArray              effThruHourMn_TM;

    /** WF_DESC_TXT_TM*/
	public final EZDBStringItemArray              wfDescTxt_TM;

    /** WF_DESC_TXT*/
	public final EZDBStringItem              wfDescTxt;

    /** WF_BIZ_APP_ID_S*/
	public final EZDBStringItem              wfBizAppId_S;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_MAX_SRCH_CNT*/
	public final EZDBBigDecimalItem              xxMaxSrchCnt;

    /** A*/
	public final business.servlet.NYEL8840.NYEL8840_ABMsgArray              A;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** P*/
	public final business.servlet.NYEL8840.NYEL8840_PBMsgArray              P;

    /** USR_NM_FR*/
	public final EZDBStringItem              usrNm_FR;

    /** LAST_NM_FR*/
	public final EZDBStringItem              lastNm_FR;

    /** FIRST_NM_FR*/
	public final EZDBStringItem              firstNm_FR;


	/**
	 * NYEL8840BMsg is constructor.
	 * The initialization when the instance of NYEL8840BMsg is generated.
	 */
	public NYEL8840BMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8840BMsg is constructor.
	 * The initialization when the instance of NYEL8840BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8840BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxWfAsgCd_F = (EZDBStringItem)newItem("xxWfAsgCd_F");
		xxWfAsgCd_FK = (EZDBStringItem)newItem("xxWfAsgCd_FK");
		xxWfAsgCd_FS = (EZDBStringItem)newItem("xxWfAsgCd_FS");
		xxWfAsgNm_F = (EZDBStringItem)newItem("xxWfAsgNm_F");
		xxWfAsgCd = (EZDBStringItem)newItem("xxWfAsgCd");
		xxWfAsgCd_LK = (EZDBStringItem)newItem("xxWfAsgCd_LK");
		xxWfAsgNm = (EZDBStringItem)newItem("xxWfAsgNm");
		wfBizAppId = (EZDBStringItem)newItem("wfBizAppId");
		wfBizAppId_L = (EZDBStringItemArray)newItemArray("wfBizAppId_L");
		wfDescTxt_L = (EZDBStringItemArray)newItemArray("wfDescTxt_L");
		effFromDt = (EZDBDateItem)newItem("effFromDt");
		effFromHourMn_H = (EZDBStringItem)newItem("effFromHourMn_H");
		effFromHourMn_FH = (EZDBStringItemArray)newItemArray("effFromHourMn_FH");
		wfDescTxt_FH = (EZDBStringItemArray)newItemArray("wfDescTxt_FH");
		effFromHourMn_M = (EZDBStringItem)newItem("effFromHourMn_M");
		effFromHourMn_FM = (EZDBStringItemArray)newItemArray("effFromHourMn_FM");
		wfDescTxt_FM = (EZDBStringItemArray)newItemArray("wfDescTxt_FM");
		effThruDt = (EZDBDateItem)newItem("effThruDt");
		effThruHourMn_H = (EZDBStringItem)newItem("effThruHourMn_H");
		effThruHourMn_TH = (EZDBStringItemArray)newItemArray("effThruHourMn_TH");
		wfDescTxt_TH = (EZDBStringItemArray)newItemArray("wfDescTxt_TH");
		effThruHourMn_M = (EZDBStringItem)newItem("effThruHourMn_M");
		effThruHourMn_TM = (EZDBStringItemArray)newItemArray("effThruHourMn_TM");
		wfDescTxt_TM = (EZDBStringItemArray)newItemArray("wfDescTxt_TM");
		wfDescTxt = (EZDBStringItem)newItem("wfDescTxt");
		wfBizAppId_S = (EZDBStringItem)newItem("wfBizAppId_S");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxMaxSrchCnt = (EZDBBigDecimalItem)newItem("xxMaxSrchCnt");
		A = (business.servlet.NYEL8840.NYEL8840_ABMsgArray)newMsgArray("A");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		P = (business.servlet.NYEL8840.NYEL8840_PBMsgArray)newMsgArray("P");
		usrNm_FR = (EZDBStringItem)newItem("usrNm_FR");
		lastNm_FR = (EZDBStringItem)newItem("lastNm_FR");
		firstNm_FR = (EZDBStringItem)newItem("firstNm_FR");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8840BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8840BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxWfAsgCd_F", "xxWfAsgCd_F", "F", null, TYPE_HANKAKUEISU, "32", null},
	{"xxWfAsgCd_FK", "xxWfAsgCd_FK", "FK", null, TYPE_HANKAKUEISU, "32", null},
	{"xxWfAsgCd_FS", "xxWfAsgCd_FS", "FS", null, TYPE_HANKAKUEISU, "32", null},
	{"xxWfAsgNm_F", "xxWfAsgNm_F", "F", null, TYPE_HANKAKUEISU, "255", null},
	{"xxWfAsgCd", "xxWfAsgCd", null, null, TYPE_HANKAKUEISU, "32", null},
	{"xxWfAsgCd_LK", "xxWfAsgCd_LK", "LK", null, TYPE_HANKAKUEISU, "32", null},
	{"xxWfAsgNm", "xxWfAsgNm", null, null, TYPE_HANKAKUEISU, "255", null},
	{"wfBizAppId", "wfBizAppId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"wfBizAppId_L", "wfBizAppId_L", "L", "99", TYPE_HANKAKUEISU, "16", null},
	{"wfDescTxt_L", "wfDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effFromHourMn_H", "effFromHourMn_H", "H", null, TYPE_HANKAKUSUJI, "4", null},
	{"effFromHourMn_FH", "effFromHourMn_FH", "FH", "99", TYPE_HANKAKUSUJI, "4", null},
	{"wfDescTxt_FH", "wfDescTxt_FH", "FH", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromHourMn_M", "effFromHourMn_M", "M", null, TYPE_HANKAKUSUJI, "4", null},
	{"effFromHourMn_FM", "effFromHourMn_FM", "FM", "99", TYPE_HANKAKUSUJI, "4", null},
	{"wfDescTxt_FM", "wfDescTxt_FM", "FM", "99", TYPE_HANKAKUEISU, "50", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruHourMn_H", "effThruHourMn_H", "H", null, TYPE_HANKAKUSUJI, "4", null},
	{"effThruHourMn_TH", "effThruHourMn_TH", "TH", "99", TYPE_HANKAKUSUJI, "4", null},
	{"wfDescTxt_TH", "wfDescTxt_TH", "TH", "99", TYPE_HANKAKUEISU, "50", null},
	{"effThruHourMn_M", "effThruHourMn_M", "M", null, TYPE_HANKAKUSUJI, "4", null},
	{"effThruHourMn_TM", "effThruHourMn_TM", "TM", "99", TYPE_HANKAKUSUJI, "4", null},
	{"wfDescTxt_TM", "wfDescTxt_TM", "TM", "99", TYPE_HANKAKUEISU, "50", null},
	{"wfDescTxt", "wfDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"wfBizAppId_S", "wfBizAppId_S", "S", null, TYPE_HANKAKUEISU, "16", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxMaxSrchCnt", "xxMaxSrchCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"A", "A", null, "20", "business.servlet.NYEL8840.NYEL8840_ABMsgArray", null, null},
	
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"P", "P", null, "11", "business.servlet.NYEL8840.NYEL8840_PBMsgArray", null, null},
	
	{"usrNm_FR", "usrNm_FR", "FR", null, TYPE_HANKAKUEISU, "30", null},
	{"lastNm_FR", "lastNm_FR", "FR", null, TYPE_HANKAKUEISU, "30", null},
	{"firstNm_FR", "firstNm_FR", "FR", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_WF_ASG_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgCd_F
        {"XX_WF_ASG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgCd_FK
        {"XX_WF_ASG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgCd_FS
        {"XX_WF_ASG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgNm_F
        {"XX_WF_ASG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgCd
        {"XX_WF_ASG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgCd_LK
        {"XX_WF_ASG_NM",  NO,  null,null,"0", NO, NO, NO, NO,"46",null,null, null,  NO,  NO},	//xxWfAsgNm
        {"WF_BIZ_APP_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAppId
        {"WF_BIZ_APP_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAppId_L
        {"WF_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_L
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt
        {"EFF_FROM_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromHourMn_H
        {"EFF_FROM_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromHourMn_FH
        {"WF_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_FH
        {"EFF_FROM_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromHourMn_M
        {"EFF_FROM_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromHourMn_FM
        {"WF_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_FM
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt
        {"EFF_THRU_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruHourMn_H
        {"EFF_THRU_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruHourMn_TH
        {"WF_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_TH
        {"EFF_THRU_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruHourMn_M
        {"EFF_THRU_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruHourMn_TM
        {"WF_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_TM
        {"WF_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt
        {"WF_BIZ_APP_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAppId_S
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_MAX_SRCH_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMaxSrchCnt
		null,	//A
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
		null,	//P
        {"USR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usrNm_FR
        {"LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastNm_FR
        {"FIRST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstNm_FR
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

