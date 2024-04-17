//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530152857000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2500SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2500 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2500SMsg extends EZDSMsg implements EZDSchemaItemDefines {

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

    /** XX_MODE_CD_P1*/
	public final EZDSStringItem              xxModeCd_P1;

    /** XX_RADIO_BTN_H1*/
	public final EZDSBigDecimalItem              xxRadioBtn_H1;

    /** A*/
	public final business.blap.NMAL2500.NMAL2500_ASMsgArray              A;

    /** T*/
	public final business.blap.NMAL2500.NMAL2500_TSMsgArray              T;

    /** _EZUpdateDateTime_G1*/
	public final EZDSStringItem              ezUpTime_G1;

    /** _EZUpTimeZone_G1*/
	public final EZDSStringItem              ezUpTimeZone_G1;

    /** ORG_CD_G1*/
	public final EZDSStringItem              orgCd_G1;

    /** ORG_NM_G1*/
	public final EZDSStringItem              orgNm_G1;

    /** ORG_STRU_TP_CD_G1*/
	public final EZDSStringItem              orgStruTpCd_G1;

    /** EFF_FROM_DT_G1*/
	public final EZDSDateItem              effFromDt_G1;

    /** DS_ORG_RELN_PK_G1*/
	public final EZDSBigDecimalItem              dsOrgRelnPk_G1;

    /** _EZUpdateDateTime_G2*/
	public final EZDSStringItem              ezUpTime_G2;

    /** _EZUpTimeZone_G2*/
	public final EZDSStringItem              ezUpTimeZone_G2;

    /** ORG_CD_G2*/
	public final EZDSStringItem              orgCd_G2;

    /** ORG_NM_G2*/
	public final EZDSStringItem              orgNm_G2;

    /** PRNT_ORG_CD_G2*/
	public final EZDSStringItem              prntOrgCd_G2;

    /** ORG_STRU_TP_CD_G2*/
	public final EZDSStringItem              orgStruTpCd_G2;

    /** EFF_FROM_DT_G2*/
	public final EZDSDateItem              effFromDt_G2;

    /** DS_ORG_RELN_PK_G2*/
	public final EZDSBigDecimalItem              dsOrgRelnPk_G2;

    /** O*/
	public final business.blap.NMAL2500.NMAL2500_OSMsgArray              O;

    /** R*/
	public final business.blap.NMAL2500.NMAL2500_RSMsgArray              R;


	/**
	 * NMAL2500SMsg is constructor.
	 * The initialization when the instance of NMAL2500SMsg is generated.
	 */
	public NMAL2500SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2500SMsg is constructor.
	 * The initialization when the instance of NMAL2500SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2500SMsg(boolean child, int eleNo) {
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
		xxModeCd_P1 = (EZDSStringItem)newItem("xxModeCd_P1");
		xxRadioBtn_H1 = (EZDSBigDecimalItem)newItem("xxRadioBtn_H1");
		A = (business.blap.NMAL2500.NMAL2500_ASMsgArray)newMsgArray("A");
		T = (business.blap.NMAL2500.NMAL2500_TSMsgArray)newMsgArray("T");
		ezUpTime_G1 = (EZDSStringItem)newItem("ezUpTime_G1");
		ezUpTimeZone_G1 = (EZDSStringItem)newItem("ezUpTimeZone_G1");
		orgCd_G1 = (EZDSStringItem)newItem("orgCd_G1");
		orgNm_G1 = (EZDSStringItem)newItem("orgNm_G1");
		orgStruTpCd_G1 = (EZDSStringItem)newItem("orgStruTpCd_G1");
		effFromDt_G1 = (EZDSDateItem)newItem("effFromDt_G1");
		dsOrgRelnPk_G1 = (EZDSBigDecimalItem)newItem("dsOrgRelnPk_G1");
		ezUpTime_G2 = (EZDSStringItem)newItem("ezUpTime_G2");
		ezUpTimeZone_G2 = (EZDSStringItem)newItem("ezUpTimeZone_G2");
		orgCd_G2 = (EZDSStringItem)newItem("orgCd_G2");
		orgNm_G2 = (EZDSStringItem)newItem("orgNm_G2");
		prntOrgCd_G2 = (EZDSStringItem)newItem("prntOrgCd_G2");
		orgStruTpCd_G2 = (EZDSStringItem)newItem("orgStruTpCd_G2");
		effFromDt_G2 = (EZDSDateItem)newItem("effFromDt_G2");
		dsOrgRelnPk_G2 = (EZDSBigDecimalItem)newItem("dsOrgRelnPk_G2");
		O = (business.blap.NMAL2500.NMAL2500_OSMsgArray)newMsgArray("O");
		R = (business.blap.NMAL2500.NMAL2500_RSMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2500SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2500SMsgArray();
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
	{"xxModeCd_P1", "xxModeCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxRadioBtn_H1", "xxRadioBtn_H1", "H1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "200", "business.blap.NMAL2500.NMAL2500_ASMsgArray", null, null},
	
	{"T", "T", null, "2000", "business.blap.NMAL2500.NMAL2500_TSMsgArray", null, null},
	
	{"ezUpTime_G1", "ezUpTime_G1", "G1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_G1", "ezUpTimeZone_G1", "G1", null, TYPE_HANKAKUEISU, "5", null},
	{"orgCd_G1", "orgCd_G1", "G1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_G1", "orgNm_G1", "G1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgStruTpCd_G1", "orgStruTpCd_G1", "G1", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_G1", "effFromDt_G1", "G1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsOrgRelnPk_G1", "dsOrgRelnPk_G1", "G1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_G2", "ezUpTime_G2", "G2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_G2", "ezUpTimeZone_G2", "G2", null, TYPE_HANKAKUEISU, "5", null},
	{"orgCd_G2", "orgCd_G2", "G2", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_G2", "orgNm_G2", "G2", null, TYPE_HANKAKUEISU, "50", null},
	{"prntOrgCd_G2", "prntOrgCd_G2", "G2", null, TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpCd_G2", "orgStruTpCd_G2", "G2", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_G2", "effFromDt_G2", "G2", null, TYPE_NENTSUKIHI, "8", null},
	{"dsOrgRelnPk_G2", "dsOrgRelnPk_G2", "G2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"O", "O", null, "200", "business.blap.NMAL2500.NMAL2500_OSMsgArray", null, null},
	
	{"R", "R", null, "200", "business.blap.NMAL2500.NMAL2500_RSMsgArray", null, null},
	
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
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_P1
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_H1
		null,	//A
		null,	//T
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_G1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_G1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_G1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_G1
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_G1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_G1
        {"DS_ORG_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrgRelnPk_G1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_G2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_G2
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_G2
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_G2
        {"PRNT_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntOrgCd_G2
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_G2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_G2
        {"DS_ORG_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrgRelnPk_G2
		null,	//O
		null,	//R
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

