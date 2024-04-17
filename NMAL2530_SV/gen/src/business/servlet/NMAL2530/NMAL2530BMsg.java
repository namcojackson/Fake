//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170223093719000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2530BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2530;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2530 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2530BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_STRU_TP_CD_H1*/
	public final EZDBStringItem              orgStruTpCd_H1;

    /** ORG_STRU_TP_CD_H2*/
	public final EZDBStringItemArray              orgStruTpCd_H2;

    /** ORG_STRU_TP_NM_H1*/
	public final EZDBStringItemArray              orgStruTpNm_H1;

    /** ORG_NM_H1*/
	public final EZDBStringItem              orgNm_H1;

    /** ORG_NM_H2*/
	public final EZDBStringItem              orgNm_H2;

    /** TOC_NM_H1*/
	public final EZDBStringItem              tocNm_H1;

    /** PSN_CD_H1*/
	public final EZDBStringItem              psnCd_H1;

    /** ORG_TIER_CD_H1*/
	public final EZDBStringItem              orgTierCd_H1;

    /** ORG_TIER_CD_H2*/
	public final EZDBStringItemArray              orgTierCd_H2;

    /** ORG_TIER_NM_H1*/
	public final EZDBStringItemArray              orgTierNm_H1;

    /** CSR_RG_TP_CD_H1*/
	public final EZDBStringItem              csrRgTpCd_H1;

    /** CSR_RG_TP_CD_H2*/
	public final EZDBStringItemArray              csrRgTpCd_H2;

    /** CSR_RG_TP_DESC_TXT_H1*/
	public final EZDBStringItemArray              csrRgTpDescTxt_H1;

    /** EFF_FROM_DT_FR*/
	public final EZDBDateItem              effFromDt_FR;

    /** EFF_FROM_DT_TO*/
	public final EZDBDateItem              effFromDt_TO;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.servlet.NMAL2530.NMAL2530_ABMsgArray              A;


	/**
	 * NMAL2530BMsg is constructor.
	 * The initialization when the instance of NMAL2530BMsg is generated.
	 */
	public NMAL2530BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2530BMsg is constructor.
	 * The initialization when the instance of NMAL2530BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2530BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgStruTpCd_H1 = (EZDBStringItem)newItem("orgStruTpCd_H1");
		orgStruTpCd_H2 = (EZDBStringItemArray)newItemArray("orgStruTpCd_H2");
		orgStruTpNm_H1 = (EZDBStringItemArray)newItemArray("orgStruTpNm_H1");
		orgNm_H1 = (EZDBStringItem)newItem("orgNm_H1");
		orgNm_H2 = (EZDBStringItem)newItem("orgNm_H2");
		tocNm_H1 = (EZDBStringItem)newItem("tocNm_H1");
		psnCd_H1 = (EZDBStringItem)newItem("psnCd_H1");
		orgTierCd_H1 = (EZDBStringItem)newItem("orgTierCd_H1");
		orgTierCd_H2 = (EZDBStringItemArray)newItemArray("orgTierCd_H2");
		orgTierNm_H1 = (EZDBStringItemArray)newItemArray("orgTierNm_H1");
		csrRgTpCd_H1 = (EZDBStringItem)newItem("csrRgTpCd_H1");
		csrRgTpCd_H2 = (EZDBStringItemArray)newItemArray("csrRgTpCd_H2");
		csrRgTpDescTxt_H1 = (EZDBStringItemArray)newItemArray("csrRgTpDescTxt_H1");
		effFromDt_FR = (EZDBDateItem)newItem("effFromDt_FR");
		effFromDt_TO = (EZDBDateItem)newItem("effFromDt_TO");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.servlet.NMAL2530.NMAL2530_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2530BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2530BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgStruTpCd_H1", "orgStruTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpCd_H2", "orgStruTpCd_H2", "H2", "99", TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpNm_H1", "orgStruTpNm_H1", "H1", "99", TYPE_HANKAKUEISU, "50", null},
	{"orgNm_H1", "orgNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_H2", "orgNm_H2", "H2", null, TYPE_HANKAKUEISU, "50", null},
	{"tocNm_H1", "tocNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"psnCd_H1", "psnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgTierCd_H1", "orgTierCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"orgTierCd_H2", "orgTierCd_H2", "H2", "99", TYPE_HANKAKUEISU, "2", null},
	{"orgTierNm_H1", "orgTierNm_H1", "H1", "99", TYPE_HANKAKUEISU, "70", null},
	{"csrRgTpCd_H1", "csrRgTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"csrRgTpCd_H2", "csrRgTpCd_H2", "H2", "99", TYPE_HANKAKUEISU, "8", null},
	{"csrRgTpDescTxt_H1", "csrRgTpDescTxt_H1", "H1", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_FR", "effFromDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_TO", "effFromDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.servlet.NMAL2530.NMAL2530_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_STRU_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_H1
        {"ORG_STRU_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_H2
        {"ORG_STRU_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpNm_H1
        {"ORG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H1
        {"ORG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H2
        {"TOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_H1
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H1
        {"ORG_TIER_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_H1
        {"ORG_TIER_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_H2
        {"ORG_TIER_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierNm_H1
        {"CSR_RG_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csrRgTpCd_H1
        {"CSR_RG_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csrRgTpCd_H2
        {"CSR_RG_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csrRgTpDescTxt_H1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_FR
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_TO
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
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
