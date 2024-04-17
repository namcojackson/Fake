//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170712091016000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6170CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6170 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6170CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_RELN_PK_F*/
	public final EZDCBigDecimalItem              dsAcctRelnPk_F;

    /** DS_ACCT_RELN_TP_CD_H*/
	public final EZDCStringItem              dsAcctRelnTpCd_H;

    /** DS_ACCT_RELN_TP_CD_C*/
	public final EZDCStringItemArray              dsAcctRelnTpCd_C;

    /** DS_ACCT_RELN_TP_NM_F*/
	public final EZDCStringItemArray              dsAcctRelnTpNm_F;

    /** SELL_TO_CUST_PK_F*/
	public final EZDCBigDecimalItem              sellToCustPk_F;

    /** DS_ACCT_NUM_F*/
	public final EZDCStringItem              dsAcctNum_F;

    /** DS_ACCT_NM_F*/
	public final EZDCStringItem              dsAcctNm_F;

    /** XX_CHK_BOX_FB*/
	public final EZDCStringItem              xxChkBox_FB;

    /** XX_CHK_BOX_FS*/
	public final EZDCStringItem              xxChkBox_FS;

    /** XX_CHK_BOX_FR*/
	public final EZDCStringItem              xxChkBox_FR;

    /** EFF_FROM_DT_F1*/
	public final EZDCDateItem              effFromDt_F1;

    /** EFF_FROM_DT_F2*/
	public final EZDCDateItem              effFromDt_F2;

    /** EFF_THRU_DT_F1*/
	public final EZDCDateItem              effThruDt_F1;

    /** EFF_THRU_DT_F2*/
	public final EZDCDateItem              effThruDt_F2;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** XX_POP_PRM_Z0*/
	public final EZDCStringItem              xxPopPrm_Z0;

    /** XX_POP_PRM_Z1*/
	public final EZDCStringItem              xxPopPrm_Z1;

    /** XX_POP_PRM_Z2*/
	public final EZDCStringItem              xxPopPrm_Z2;

    /** XX_POP_PRM_Z3*/
	public final EZDCStringItem              xxPopPrm_Z3;

    /** XX_POP_PRM_Z4*/
	public final EZDCStringItem              xxPopPrm_Z4;

    /** XX_POP_PRM_Z5*/
	public final EZDCStringItem              xxPopPrm_Z5;

    /** XX_POP_PRM_Z6*/
	public final EZDCStringItem              xxPopPrm_Z6;

    /** XX_POP_PRM_Z7*/
	public final EZDCStringItem              xxPopPrm_Z7;

    /** XX_POP_PRM_Z8*/
	public final EZDCStringItem              xxPopPrm_Z8;

    /** XX_POP_PRM_Z9*/
	public final EZDCStringItem              xxPopPrm_Z9;

    /** XX_POP_PRM_ZA*/
	public final EZDCStringItem              xxPopPrm_ZA;

    /** DS_ACCT_NUM_H1*/
	public final EZDCStringItem              dsAcctNum_H1;


	/**
	 * NMAL6170CMsg is constructor.
	 * The initialization when the instance of NMAL6170CMsg is generated.
	 */
	public NMAL6170CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6170CMsg is constructor.
	 * The initialization when the instance of NMAL6170CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6170CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctRelnPk_F = (EZDCBigDecimalItem)newItem("dsAcctRelnPk_F");
		dsAcctRelnTpCd_H = (EZDCStringItem)newItem("dsAcctRelnTpCd_H");
		dsAcctRelnTpCd_C = (EZDCStringItemArray)newItemArray("dsAcctRelnTpCd_C");
		dsAcctRelnTpNm_F = (EZDCStringItemArray)newItemArray("dsAcctRelnTpNm_F");
		sellToCustPk_F = (EZDCBigDecimalItem)newItem("sellToCustPk_F");
		dsAcctNum_F = (EZDCStringItem)newItem("dsAcctNum_F");
		dsAcctNm_F = (EZDCStringItem)newItem("dsAcctNm_F");
		xxChkBox_FB = (EZDCStringItem)newItem("xxChkBox_FB");
		xxChkBox_FS = (EZDCStringItem)newItem("xxChkBox_FS");
		xxChkBox_FR = (EZDCStringItem)newItem("xxChkBox_FR");
		effFromDt_F1 = (EZDCDateItem)newItem("effFromDt_F1");
		effFromDt_F2 = (EZDCDateItem)newItem("effFromDt_F2");
		effThruDt_F1 = (EZDCDateItem)newItem("effThruDt_F1");
		effThruDt_F2 = (EZDCDateItem)newItem("effThruDt_F2");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		xxPopPrm_Z0 = (EZDCStringItem)newItem("xxPopPrm_Z0");
		xxPopPrm_Z1 = (EZDCStringItem)newItem("xxPopPrm_Z1");
		xxPopPrm_Z2 = (EZDCStringItem)newItem("xxPopPrm_Z2");
		xxPopPrm_Z3 = (EZDCStringItem)newItem("xxPopPrm_Z3");
		xxPopPrm_Z4 = (EZDCStringItem)newItem("xxPopPrm_Z4");
		xxPopPrm_Z5 = (EZDCStringItem)newItem("xxPopPrm_Z5");
		xxPopPrm_Z6 = (EZDCStringItem)newItem("xxPopPrm_Z6");
		xxPopPrm_Z7 = (EZDCStringItem)newItem("xxPopPrm_Z7");
		xxPopPrm_Z8 = (EZDCStringItem)newItem("xxPopPrm_Z8");
		xxPopPrm_Z9 = (EZDCStringItem)newItem("xxPopPrm_Z9");
		xxPopPrm_ZA = (EZDCStringItem)newItem("xxPopPrm_ZA");
		dsAcctNum_H1 = (EZDCStringItem)newItem("dsAcctNum_H1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6170CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6170CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctRelnPk_F", "dsAcctRelnPk_F", "F", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAcctRelnTpCd_H", "dsAcctRelnTpCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"dsAcctRelnTpCd_C", "dsAcctRelnTpCd_C", "C", "99", TYPE_HANKAKUEISU, "2", null},
	{"dsAcctRelnTpNm_F", "dsAcctRelnTpNm_F", "F", "99", TYPE_HANKAKUEISU, "20", null},
	{"sellToCustPk_F", "sellToCustPk_F", "F", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAcctNum_F", "dsAcctNum_F", "F", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_F", "dsAcctNm_F", "F", null, TYPE_HANKAKUEISU, "360", null},
	{"xxChkBox_FB", "xxChkBox_FB", "FB", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_FS", "xxChkBox_FS", "FS", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_FR", "xxChkBox_FR", "FR", null, TYPE_HANKAKUEIJI, "1", null},
	{"effFromDt_F1", "effFromDt_F1", "F1", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_F2", "effFromDt_F2", "F2", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_F1", "effThruDt_F1", "F1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_F2", "effThruDt_F2", "F2", null, TYPE_NENTSUKIHI, "8", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxPopPrm_Z0", "xxPopPrm_Z0", "Z0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z1", "xxPopPrm_Z1", "Z1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z2", "xxPopPrm_Z2", "Z2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z3", "xxPopPrm_Z3", "Z3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z4", "xxPopPrm_Z4", "Z4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z5", "xxPopPrm_Z5", "Z5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z6", "xxPopPrm_Z6", "Z6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z7", "xxPopPrm_Z7", "Z7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z8", "xxPopPrm_Z8", "Z8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z9", "xxPopPrm_Z9", "Z9", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_ZA", "xxPopPrm_ZA", "ZA", null, TYPE_HANKAKUEISU, "300", null},
	{"dsAcctNum_H1", "dsAcctNum_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRelnPk_F
        {"DS_ACCT_RELN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRelnTpCd_H
        {"DS_ACCT_RELN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRelnTpCd_C
        {"DS_ACCT_RELN_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRelnTpNm_F
        {"SELL_TO_CUST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustPk_F
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_F
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_F
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_FB
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_FS
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_FR
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_F1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_F2
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_F1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_F2
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z9
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_ZA
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_H1
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

