//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530171132000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2600SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2600 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2600SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DPLY_TAB*/
	public final EZDSStringItem              xxDplyTab;

    /** XX_POP_PRM_0*/
	public final EZDSStringItem              xxPopPrm_0;

    /** XX_POP_PRM_1*/
	public final EZDSStringItem              xxPopPrm_1;

    /** XX_POP_PRM_2*/
	public final EZDSStringItem              xxPopPrm_2;

    /** XX_POP_PRM_3*/
	public final EZDSStringItem              xxPopPrm_3;

    /** XX_POP_PRM_4*/
	public final EZDSStringItem              xxPopPrm_4;

    /** XX_POP_PRM_5*/
	public final EZDSStringItem              xxPopPrm_5;

    /** XX_POP_PRM_6*/
	public final EZDSStringItem              xxPopPrm_6;

    /** XX_POP_PRM_7*/
	public final EZDSStringItem              xxPopPrm_7;

    /** XX_POP_PRM_8*/
	public final EZDSStringItem              xxPopPrm_8;

    /** XX_POP_PRM_9*/
	public final EZDSStringItem              xxPopPrm_9;

    /** XX_POP_PRM_10*/
	public final EZDSStringItem              xxPopPrm_10;

    /** XX_RADIO_BTN_H1*/
	public final EZDSBigDecimalItem              xxRadioBtn_H1;

    /** BIZ_AREA_ORG_CD_P1*/
	public final EZDSStringItem              bizAreaOrgCd_P1;

    /** TRTY_TP_CD_P1*/
	public final EZDSStringItem              trtyTpCd_P1;

    /** ORG_CD_H1*/
	public final EZDSStringItem              orgCd_H1;

    /** ORG_NM_H1*/
	public final EZDSStringItem              orgNm_H1;

    /** ORG_TIER_CD_P1*/
	public final EZDSStringItem              orgTierCd_P1;

    /** PSN_FIRST_NM_H1*/
	public final EZDSStringItem              psnFirstNm_H1;

    /** PSN_LAST_NM_H1*/
	public final EZDSStringItem              psnLastNm_H1;

    /** XX_PSN_NM_H1*/
	public final EZDSStringItem              xxPsnNm_H1;

    /** TRTY_GRP_TP_CD_P1*/
	public final EZDSStringItem              trtyGrpTpCd_P1;

    /** PSN_CD_H1*/
	public final EZDSStringItem              psnCd_H1;

    /** PSN_NUM_H1*/
	public final EZDSStringItem              psnNum_H1;

    /** EFF_FROM_DT_H1*/
	public final EZDSDateItem              effFromDt_H1;

    /** XX_CHK_BOX_H1*/
	public final EZDSStringItem              xxChkBox_H1;

    /** A*/
	public final business.blap.NMAL2600.NMAL2600_ASMsgArray              A;

    /** T*/
	public final business.blap.NMAL2600.NMAL2600_TSMsgArray              T;

    /** ORG_CD_P*/
	public final EZDSStringItem              orgCd_P;

    /** ORG_NM_P*/
	public final EZDSStringItem              orgNm_P;

    /** Q*/
	public final business.blap.NMAL2600.NMAL2600_QSMsgArray              Q;

    /** R*/
	public final business.blap.NMAL2600.NMAL2600_RSMsgArray              R;

    /** S*/
	public final business.blap.NMAL2600.NMAL2600_SSMsgArray              S;


	/**
	 * NMAL2600SMsg is constructor.
	 * The initialization when the instance of NMAL2600SMsg is generated.
	 */
	public NMAL2600SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2600SMsg is constructor.
	 * The initialization when the instance of NMAL2600SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2600SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDplyTab = (EZDSStringItem)newItem("xxDplyTab");
		xxPopPrm_0 = (EZDSStringItem)newItem("xxPopPrm_0");
		xxPopPrm_1 = (EZDSStringItem)newItem("xxPopPrm_1");
		xxPopPrm_2 = (EZDSStringItem)newItem("xxPopPrm_2");
		xxPopPrm_3 = (EZDSStringItem)newItem("xxPopPrm_3");
		xxPopPrm_4 = (EZDSStringItem)newItem("xxPopPrm_4");
		xxPopPrm_5 = (EZDSStringItem)newItem("xxPopPrm_5");
		xxPopPrm_6 = (EZDSStringItem)newItem("xxPopPrm_6");
		xxPopPrm_7 = (EZDSStringItem)newItem("xxPopPrm_7");
		xxPopPrm_8 = (EZDSStringItem)newItem("xxPopPrm_8");
		xxPopPrm_9 = (EZDSStringItem)newItem("xxPopPrm_9");
		xxPopPrm_10 = (EZDSStringItem)newItem("xxPopPrm_10");
		xxRadioBtn_H1 = (EZDSBigDecimalItem)newItem("xxRadioBtn_H1");
		bizAreaOrgCd_P1 = (EZDSStringItem)newItem("bizAreaOrgCd_P1");
		trtyTpCd_P1 = (EZDSStringItem)newItem("trtyTpCd_P1");
		orgCd_H1 = (EZDSStringItem)newItem("orgCd_H1");
		orgNm_H1 = (EZDSStringItem)newItem("orgNm_H1");
		orgTierCd_P1 = (EZDSStringItem)newItem("orgTierCd_P1");
		psnFirstNm_H1 = (EZDSStringItem)newItem("psnFirstNm_H1");
		psnLastNm_H1 = (EZDSStringItem)newItem("psnLastNm_H1");
		xxPsnNm_H1 = (EZDSStringItem)newItem("xxPsnNm_H1");
		trtyGrpTpCd_P1 = (EZDSStringItem)newItem("trtyGrpTpCd_P1");
		psnCd_H1 = (EZDSStringItem)newItem("psnCd_H1");
		psnNum_H1 = (EZDSStringItem)newItem("psnNum_H1");
		effFromDt_H1 = (EZDSDateItem)newItem("effFromDt_H1");
		xxChkBox_H1 = (EZDSStringItem)newItem("xxChkBox_H1");
		A = (business.blap.NMAL2600.NMAL2600_ASMsgArray)newMsgArray("A");
		T = (business.blap.NMAL2600.NMAL2600_TSMsgArray)newMsgArray("T");
		orgCd_P = (EZDSStringItem)newItem("orgCd_P");
		orgNm_P = (EZDSStringItem)newItem("orgNm_P");
		Q = (business.blap.NMAL2600.NMAL2600_QSMsgArray)newMsgArray("Q");
		R = (business.blap.NMAL2600.NMAL2600_RSMsgArray)newMsgArray("R");
		S = (business.blap.NMAL2600.NMAL2600_SSMsgArray)newMsgArray("S");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2600SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2600SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxPopPrm_0", "xxPopPrm_0", "0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_1", "xxPopPrm_1", "1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_2", "xxPopPrm_2", "2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_3", "xxPopPrm_3", "3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_4", "xxPopPrm_4", "4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_5", "xxPopPrm_5", "5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_6", "xxPopPrm_6", "6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_7", "xxPopPrm_7", "7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_8", "xxPopPrm_8", "8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_9", "xxPopPrm_9", "9", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_10", "xxPopPrm_10", "10", null, TYPE_HANKAKUEISU, "300", null},
	{"xxRadioBtn_H1", "xxRadioBtn_H1", "H1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"bizAreaOrgCd_P1", "bizAreaOrgCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"trtyTpCd_P1", "trtyTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgCd_H1", "orgCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_H1", "orgNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgTierCd_P1", "orgTierCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"psnFirstNm_H1", "psnFirstNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_H1", "psnLastNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxPsnNm_H1", "xxPsnNm_H1", "H1", null, TYPE_HANKAKUEISU, "62", null},
	{"trtyGrpTpCd_P1", "trtyGrpTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnCd_H1", "psnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnNum_H1", "psnNum_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"A", "A", null, "10", "business.blap.NMAL2600.NMAL2600_ASMsgArray", null, null},
	
	{"T", "T", null, "5000", "business.blap.NMAL2600.NMAL2600_TSMsgArray", null, null},
	
	{"orgCd_P", "orgCd_P", "P", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_P", "orgNm_P", "P", null, TYPE_HANKAKUEISU, "50", null},
	{"Q", "Q", null, "5000", "business.blap.NMAL2600.NMAL2600_QSMsgArray", null, null},
	
	{"R", "R", null, "200", "business.blap.NMAL2600.NMAL2600_RSMsgArray", null, null},
	
	{"S", "S", null, "200", "business.blap.NMAL2600.NMAL2600_SSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_9
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_10
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_H1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_P1
        {"TRTY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpCd_P1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_H1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H1
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_P1
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_H1
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_H1
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H1
        {"TRTY_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpCd_P1
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H1
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_H1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
		null,	//A
		null,	//T
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_P
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_P
		null,	//Q
		null,	//R
		null,	//S
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

