//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180515095208000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6710_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6710;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6710 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6710_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NUM_T1*/
	public final EZDBStringItem              dsAcctNum_T1;

    /** DS_ACCT_NUM_T2*/
	public final EZDBStringItem              dsAcctNum_T2;

    /** DS_ACCT_NUM_T3*/
	public final EZDBStringItem              dsAcctNum_T3;

    /** DS_ACCT_NUM_T4*/
	public final EZDBStringItem              dsAcctNum_T4;

    /** DS_ACCT_NUM_T5*/
	public final EZDBStringItem              dsAcctNum_T5;

    /** DS_ACCT_NUM_T6*/
	public final EZDBStringItem              dsAcctNum_T6;

    /** DS_ACCT_NUM_T7*/
	public final EZDBStringItem              dsAcctNum_T7;

    /** DS_ACCT_NUM_T8*/
	public final EZDBStringItem              dsAcctNum_T8;

    /** DS_ACCT_NUM_T9*/
	public final EZDBStringItem              dsAcctNum_T9;

    /** DS_ACCT_NUM_TA*/
	public final EZDBStringItem              dsAcctNum_TA;

    /** DS_ACCT_NUM_TB*/
	public final EZDBStringItem              dsAcctNum_TB;

    /** DS_ACCT_NUM_TL*/
	public final EZDBStringItem              dsAcctNum_TL;

    /** DS_ACCT_NUM_T*/
	public final EZDBStringItem              dsAcctNum_T;

    /** DS_ACCT_RELN_TP_NM_T*/
	public final EZDBStringItem              dsAcctRelnTpNm_T;

    /** DS_ACCT_NM_T*/
	public final EZDBStringItem              dsAcctNm_T;

    /** XX_ALL_LINE_ADDR_T*/
	public final EZDBStringItem              xxAllLineAddr_T;

    /** LOC_NUM_T*/
	public final EZDBStringItem              locNum_T;

    /** XX_YES_NO_CD_T*/
	public final EZDBStringItem              xxYesNoCd_T;

    /** DS_ACCT_TP_NM_T*/
	public final EZDBStringItem              dsAcctTpNm_T;

    /** XX_CTL_NM_T*/
	public final EZDBStringItem              xxCtlNm_T;

    /** RELN_DS_ACCT_NUM_T*/
	public final EZDBStringItem              relnDsAcctNum_T;

    /** DS_ACCT_NUM_TO*/
	public final EZDBStringItem              dsAcctNum_TO;

    /** BILL_TO_CUST_CD_T*/
	public final EZDBStringItem              billToCustCd_T;

    /** SHIP_TO_CUST_CD_T*/
	public final EZDBStringItem              shipToCustCd_T;

    /** XX_RADIO_BTN_T*/
	public final EZDBBigDecimalItem              xxRadioBtn_T;

    /** DS_CTAC_PNT_PK_P1*/
	public final EZDBBigDecimalItem              dsCtacPntPk_P1;

    /** DS_CTAC_PNT_PK_P2*/
	public final EZDBBigDecimalItem              dsCtacPntPk_P2;

    /** DS_CTAC_PNT_PK_P3*/
	public final EZDBBigDecimalItem              dsCtacPntPk_P3;

    /** CTAC_PSN_PK_P1*/
	public final EZDBBigDecimalItem              ctacPsnPk_P1;

    /** XX_RGTN_STS_TXT_A1*/
	public final EZDBStringItem              xxRgtnStsTxt_A1;

    /** XX_RGTN_STS_TXT_A2*/
	public final EZDBStringItem              xxRgtnStsTxt_A2;

    /** XX_RGTN_STS_TXT_A3*/
	public final EZDBStringItem              xxRgtnStsTxt_A3;

    /** XX_RGTN_STS_TXT_A4*/
	public final EZDBStringItem              xxRgtnStsTxt_A4;

    /** XX_RGTN_STS_TXT_A5*/
	public final EZDBStringItem              xxRgtnStsTxt_A5;


	/**
	 * NMAL6710_ABMsg is constructor.
	 * The initialization when the instance of NMAL6710_ABMsg is generated.
	 */
	public NMAL6710_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6710_ABMsg is constructor.
	 * The initialization when the instance of NMAL6710_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6710_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNum_T1 = (EZDBStringItem)newItem("dsAcctNum_T1");
		dsAcctNum_T2 = (EZDBStringItem)newItem("dsAcctNum_T2");
		dsAcctNum_T3 = (EZDBStringItem)newItem("dsAcctNum_T3");
		dsAcctNum_T4 = (EZDBStringItem)newItem("dsAcctNum_T4");
		dsAcctNum_T5 = (EZDBStringItem)newItem("dsAcctNum_T5");
		dsAcctNum_T6 = (EZDBStringItem)newItem("dsAcctNum_T6");
		dsAcctNum_T7 = (EZDBStringItem)newItem("dsAcctNum_T7");
		dsAcctNum_T8 = (EZDBStringItem)newItem("dsAcctNum_T8");
		dsAcctNum_T9 = (EZDBStringItem)newItem("dsAcctNum_T9");
		dsAcctNum_TA = (EZDBStringItem)newItem("dsAcctNum_TA");
		dsAcctNum_TB = (EZDBStringItem)newItem("dsAcctNum_TB");
		dsAcctNum_TL = (EZDBStringItem)newItem("dsAcctNum_TL");
		dsAcctNum_T = (EZDBStringItem)newItem("dsAcctNum_T");
		dsAcctRelnTpNm_T = (EZDBStringItem)newItem("dsAcctRelnTpNm_T");
		dsAcctNm_T = (EZDBStringItem)newItem("dsAcctNm_T");
		xxAllLineAddr_T = (EZDBStringItem)newItem("xxAllLineAddr_T");
		locNum_T = (EZDBStringItem)newItem("locNum_T");
		xxYesNoCd_T = (EZDBStringItem)newItem("xxYesNoCd_T");
		dsAcctTpNm_T = (EZDBStringItem)newItem("dsAcctTpNm_T");
		xxCtlNm_T = (EZDBStringItem)newItem("xxCtlNm_T");
		relnDsAcctNum_T = (EZDBStringItem)newItem("relnDsAcctNum_T");
		dsAcctNum_TO = (EZDBStringItem)newItem("dsAcctNum_TO");
		billToCustCd_T = (EZDBStringItem)newItem("billToCustCd_T");
		shipToCustCd_T = (EZDBStringItem)newItem("shipToCustCd_T");
		xxRadioBtn_T = (EZDBBigDecimalItem)newItem("xxRadioBtn_T");
		dsCtacPntPk_P1 = (EZDBBigDecimalItem)newItem("dsCtacPntPk_P1");
		dsCtacPntPk_P2 = (EZDBBigDecimalItem)newItem("dsCtacPntPk_P2");
		dsCtacPntPk_P3 = (EZDBBigDecimalItem)newItem("dsCtacPntPk_P3");
		ctacPsnPk_P1 = (EZDBBigDecimalItem)newItem("ctacPsnPk_P1");
		xxRgtnStsTxt_A1 = (EZDBStringItem)newItem("xxRgtnStsTxt_A1");
		xxRgtnStsTxt_A2 = (EZDBStringItem)newItem("xxRgtnStsTxt_A2");
		xxRgtnStsTxt_A3 = (EZDBStringItem)newItem("xxRgtnStsTxt_A3");
		xxRgtnStsTxt_A4 = (EZDBStringItem)newItem("xxRgtnStsTxt_A4");
		xxRgtnStsTxt_A5 = (EZDBStringItem)newItem("xxRgtnStsTxt_A5");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6710_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6710_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctNum_T1", "dsAcctNum_T1", "T1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_T2", "dsAcctNum_T2", "T2", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_T3", "dsAcctNum_T3", "T3", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_T4", "dsAcctNum_T4", "T4", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_T5", "dsAcctNum_T5", "T5", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_T6", "dsAcctNum_T6", "T6", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_T7", "dsAcctNum_T7", "T7", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_T8", "dsAcctNum_T8", "T8", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_T9", "dsAcctNum_T9", "T9", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_TA", "dsAcctNum_TA", "TA", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_TB", "dsAcctNum_TB", "TB", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_TL", "dsAcctNum_TL", "TL", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_T", "dsAcctNum_T", "T", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctRelnTpNm_T", "dsAcctRelnTpNm_T", "T", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_T", "dsAcctNm_T", "T", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_T", "xxAllLineAddr_T", "T", null, TYPE_HANKAKUEISU, "400", null},
	{"locNum_T", "locNum_T", "T", null, TYPE_HANKAKUEISU, "30", null},
	{"xxYesNoCd_T", "xxYesNoCd_T", "T", null, TYPE_HANKAKUEISU, "1", null},
	{"dsAcctTpNm_T", "dsAcctTpNm_T", "T", null, TYPE_HANKAKUEISU, "20", null},
	{"xxCtlNm_T", "xxCtlNm_T", "T", null, TYPE_HANKAKUEISU, "50", null},
	{"relnDsAcctNum_T", "relnDsAcctNum_T", "T", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_TO", "dsAcctNum_TO", "TO", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_T", "billToCustCd_T", "T", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_T", "shipToCustCd_T", "T", null, TYPE_HANKAKUEISU, "20", null},
	{"xxRadioBtn_T", "xxRadioBtn_T", "T", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"dsCtacPntPk_P1", "dsCtacPntPk_P1", "P1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsCtacPntPk_P2", "dsCtacPntPk_P2", "P2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsCtacPntPk_P3", "dsCtacPntPk_P3", "P3", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnPk_P1", "ctacPsnPk_P1", "P1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRgtnStsTxt_A1", "xxRgtnStsTxt_A1", "A1", null, TYPE_HANKAKUEISU, "56", null},
	{"xxRgtnStsTxt_A2", "xxRgtnStsTxt_A2", "A2", null, TYPE_HANKAKUEISU, "56", null},
	{"xxRgtnStsTxt_A3", "xxRgtnStsTxt_A3", "A3", null, TYPE_HANKAKUEISU, "56", null},
	{"xxRgtnStsTxt_A4", "xxRgtnStsTxt_A4", "A4", null, TYPE_HANKAKUEISU, "56", null},
	{"xxRgtnStsTxt_A5", "xxRgtnStsTxt_A5", "A5", null, TYPE_HANKAKUEISU, "56", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T1
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T2
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T3
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T4
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T5
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T6
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T7
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T8
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T9
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_TA
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_TB
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_TL
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_T
        {"DS_ACCT_RELN_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRelnTpNm_T
        {"DS_ACCT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_T
        {"XX_ALL_LINE_ADDR",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_T
        {"LOC_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_T
        {"XX_YES_NO_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_T
        {"DS_ACCT_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpNm_T
        {"XX_CTL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCtlNm_T
        {"RELN_DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//relnDsAcctNum_T
        {"DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_TO
        {"BILL_TO_CUST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_T
        {"SHIP_TO_CUST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_T
        {"XX_RADIO_BTN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_T
        {"DS_CTAC_PNT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk_P1
        {"DS_CTAC_PNT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk_P2
        {"DS_CTAC_PNT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk_P3
        {"CTAC_PSN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_P1
        {"XX_RGTN_STS_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_A1
        {"XX_RGTN_STS_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_A2
        {"XX_RGTN_STS_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_A3
        {"XX_RGTN_STS_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_A4
        {"XX_RGTN_STS_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRgtnStsTxt_A5
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
