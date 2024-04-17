//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180625155248000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0080BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLEL0080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLEL0080 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLEL0080BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** ASSET_TP_NM_C*/
	public final EZDBStringItemArray              assetTpNm_C;

    /** ASSET_TP_DESC_TXT_D*/
	public final EZDBStringItemArray              assetTpDescTxt_D;

    /** ASSET_TP_NM_S*/
	public final EZDBStringItem              assetTpNm_S;

    /** ASSET_SRC_TP_CD_C*/
	public final EZDBStringItemArray              assetSrcTpCd_C;

    /** ASSET_SRC_TP_DESC_TXT_D*/
	public final EZDBStringItemArray              assetSrcTpDescTxt_D;

    /** ASSET_SRC_TP_CD_S*/
	public final EZDBStringItem              assetSrcTpCd_S;

    /** A*/
	public final business.servlet.NLEL0080.NLEL0080_ABMsgArray              A;

    /** XX_POP_PRM_P0*/
	public final EZDBStringItem              xxPopPrm_P0;

    /** XX_POP_PRM_P1*/
	public final EZDBStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDBStringItem              xxPopPrm_P2;

    /** XX_POP_PRM_P3*/
	public final EZDBStringItem              xxPopPrm_P3;

    /** XX_POP_PRM_P4*/
	public final EZDBStringItem              xxPopPrm_P4;

    /** XX_POP_PRM_P5*/
	public final EZDBStringItem              xxPopPrm_P5;

    /** XX_POP_PRM_P6*/
	public final EZDBStringItem              xxPopPrm_P6;

    /** XX_POP_PRM_P7*/
	public final EZDBStringItem              xxPopPrm_P7;

    /** XX_POP_PRM_P8*/
	public final EZDBStringItem              xxPopPrm_P8;

    /** XX_POP_PRM_P9*/
	public final EZDBStringItem              xxPopPrm_P9;

    /** XX_POP_PRM_PA*/
	public final EZDBStringItem              xxPopPrm_PA;

    /** XX_POP_PRM_PB*/
	public final EZDBStringItem              xxPopPrm_PB;

    /** XX_POP_PRM_PC*/
	public final EZDBStringItem              xxPopPrm_PC;

    /** XX_POP_PRM_PD*/
	public final EZDBStringItem              xxPopPrm_PD;

    /** XX_POP_PRM_PE*/
	public final EZDBStringItem              xxPopPrm_PE;

    /** XX_POP_PRM_PF*/
	public final EZDBStringItem              xxPopPrm_PF;

    /** XX_POP_PRM_PG*/
	public final EZDBStringItem              xxPopPrm_PG;

    /** XX_POP_PRM_PH*/
	public final EZDBStringItem              xxPopPrm_PH;

    /** XX_POP_PRM_PI*/
	public final EZDBStringItem              xxPopPrm_PI;

    /** XX_POP_PRM_PJ*/
	public final EZDBStringItem              xxPopPrm_PJ;

    /** XX_POP_PRM_PK*/
	public final EZDBStringItem              xxPopPrm_PK;

    /** XX_POP_PRM_PL*/
	public final EZDBStringItem              xxPopPrm_PL;

    /** XX_POP_PRM_PM*/
	public final EZDBStringItem              xxPopPrm_PM;

    /** XX_POP_PRM_PN*/
	public final EZDBStringItem              xxPopPrm_PN;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** P*/
	public final business.servlet.NLEL0080.NLEL0080_PBMsgArray              P;

    /** XX_SCR_DPLY*/
	public final EZDBStringItem              xxScrDply;

    /** XX_RSLT_FLG*/
	public final EZDBStringItem              xxRsltFlg;


	/**
	 * NLEL0080BMsg is constructor.
	 * The initialization when the instance of NLEL0080BMsg is generated.
	 */
	public NLEL0080BMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0080BMsg is constructor.
	 * The initialization when the instance of NLEL0080BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0080BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		assetTpNm_C = (EZDBStringItemArray)newItemArray("assetTpNm_C");
		assetTpDescTxt_D = (EZDBStringItemArray)newItemArray("assetTpDescTxt_D");
		assetTpNm_S = (EZDBStringItem)newItem("assetTpNm_S");
		assetSrcTpCd_C = (EZDBStringItemArray)newItemArray("assetSrcTpCd_C");
		assetSrcTpDescTxt_D = (EZDBStringItemArray)newItemArray("assetSrcTpDescTxt_D");
		assetSrcTpCd_S = (EZDBStringItem)newItem("assetSrcTpCd_S");
		A = (business.servlet.NLEL0080.NLEL0080_ABMsgArray)newMsgArray("A");
		xxPopPrm_P0 = (EZDBStringItem)newItem("xxPopPrm_P0");
		xxPopPrm_P1 = (EZDBStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDBStringItem)newItem("xxPopPrm_P2");
		xxPopPrm_P3 = (EZDBStringItem)newItem("xxPopPrm_P3");
		xxPopPrm_P4 = (EZDBStringItem)newItem("xxPopPrm_P4");
		xxPopPrm_P5 = (EZDBStringItem)newItem("xxPopPrm_P5");
		xxPopPrm_P6 = (EZDBStringItem)newItem("xxPopPrm_P6");
		xxPopPrm_P7 = (EZDBStringItem)newItem("xxPopPrm_P7");
		xxPopPrm_P8 = (EZDBStringItem)newItem("xxPopPrm_P8");
		xxPopPrm_P9 = (EZDBStringItem)newItem("xxPopPrm_P9");
		xxPopPrm_PA = (EZDBStringItem)newItem("xxPopPrm_PA");
		xxPopPrm_PB = (EZDBStringItem)newItem("xxPopPrm_PB");
		xxPopPrm_PC = (EZDBStringItem)newItem("xxPopPrm_PC");
		xxPopPrm_PD = (EZDBStringItem)newItem("xxPopPrm_PD");
		xxPopPrm_PE = (EZDBStringItem)newItem("xxPopPrm_PE");
		xxPopPrm_PF = (EZDBStringItem)newItem("xxPopPrm_PF");
		xxPopPrm_PG = (EZDBStringItem)newItem("xxPopPrm_PG");
		xxPopPrm_PH = (EZDBStringItem)newItem("xxPopPrm_PH");
		xxPopPrm_PI = (EZDBStringItem)newItem("xxPopPrm_PI");
		xxPopPrm_PJ = (EZDBStringItem)newItem("xxPopPrm_PJ");
		xxPopPrm_PK = (EZDBStringItem)newItem("xxPopPrm_PK");
		xxPopPrm_PL = (EZDBStringItem)newItem("xxPopPrm_PL");
		xxPopPrm_PM = (EZDBStringItem)newItem("xxPopPrm_PM");
		xxPopPrm_PN = (EZDBStringItem)newItem("xxPopPrm_PN");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		P = (business.servlet.NLEL0080.NLEL0080_PBMsgArray)newMsgArray("P");
		xxScrDply = (EZDBStringItem)newItem("xxScrDply");
		xxRsltFlg = (EZDBStringItem)newItem("xxRsltFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0080BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0080BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"assetTpNm_C", "assetTpNm_C", "C", "99", TYPE_HANKAKUEISU, "30", null},
	{"assetTpDescTxt_D", "assetTpDescTxt_D", "D", "99", TYPE_HANKAKUEISU, "50", null},
	{"assetTpNm_S", "assetTpNm_S", "S", null, TYPE_HANKAKUEISU, "30", null},
	{"assetSrcTpCd_C", "assetSrcTpCd_C", "C", "99", TYPE_HANKAKUEISU, "2", null},
	{"assetSrcTpDescTxt_D", "assetSrcTpDescTxt_D", "D", "99", TYPE_HANKAKUEISU, "50", null},
	{"assetSrcTpCd_S", "assetSrcTpCd_S", "S", null, TYPE_HANKAKUEISU, "2", null},
	{"A", "A", null, "200", "business.servlet.NLEL0080.NLEL0080_ABMsgArray", null, null},
	
	{"xxPopPrm_P0", "xxPopPrm_P0", "P0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P1", "xxPopPrm_P1", "P1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P2", "xxPopPrm_P2", "P2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P3", "xxPopPrm_P3", "P3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P4", "xxPopPrm_P4", "P4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P5", "xxPopPrm_P5", "P5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P6", "xxPopPrm_P6", "P6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P7", "xxPopPrm_P7", "P7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P8", "xxPopPrm_P8", "P8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P9", "xxPopPrm_P9", "P9", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PA", "xxPopPrm_PA", "PA", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PB", "xxPopPrm_PB", "PB", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PC", "xxPopPrm_PC", "PC", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PD", "xxPopPrm_PD", "PD", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PE", "xxPopPrm_PE", "PE", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PF", "xxPopPrm_PF", "PF", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PG", "xxPopPrm_PG", "PG", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PH", "xxPopPrm_PH", "PH", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PI", "xxPopPrm_PI", "PI", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PJ", "xxPopPrm_PJ", "PJ", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PK", "xxPopPrm_PK", "PK", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PL", "xxPopPrm_PL", "PL", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PM", "xxPopPrm_PM", "PM", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PN", "xxPopPrm_PN", "PN", null, TYPE_HANKAKUEISU, "300", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"P", "P", null, "200", "business.servlet.NLEL0080.NLEL0080_PBMsgArray", null, null},
	
	{"xxScrDply", "xxScrDply", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxRsltFlg", "xxRsltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"ASSET_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpNm_C
        {"ASSET_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpDescTxt_D
        {"ASSET_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpNm_S
        {"ASSET_SRC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetSrcTpCd_C
        {"ASSET_SRC_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetSrcTpDescTxt_D
        {"ASSET_SRC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetSrcTpCd_S
		null,	//A
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P0
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P3
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P4
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P5
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P6
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P7
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P8
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P9
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PA
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PB
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PC
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PD
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PE
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PF
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PG
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PH
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PI
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PJ
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PK
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PL
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PM
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PN
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
		null,	//P
        {"XX_SCR_DPLY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrDply
        {"XX_RSLT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg
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
