//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20140303122612000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0040SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0040 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0040SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DPLY_TAB*/
	public final EZDSStringItem              xxDplyTab;

    /** WH_CD_L1*/
	public final EZDSStringItem              whCd_L1;

    /** XX_EDT_CD_NM_L1*/
	public final EZDSStringItem              xxEdtCdNm_L1;

    /** WH_CD_H1*/
	public final EZDSStringItem              whCd_H1;

    /** WH_CD_B1*/
	public final EZDSStringItem              whCd_B1;

    /** XX_TAB_PROT_D1*/
	public final EZDSStringItem              xxTabProt_D1;

    /** XX_PAGE_SHOW_FROM_NUM_D1*/
	public final EZDSBigDecimalItem              xxPageShowFromNum_D1;

    /** XX_PAGE_SHOW_TO_NUM_D1*/
	public final EZDSBigDecimalItem              xxPageShowToNum_D1;

    /** XX_PAGE_SHOW_OF_NUM_D1*/
	public final EZDSBigDecimalItem              xxPageShowOfNum_D1;

    /** A*/
	public final business.blap.NLGL0040.NLGL0040_ASMsgArray              A;

    /** WMS_SHIP_VIA_TP_CD_D2*/
	public final EZDSStringItem              wmsShipViaTpCd_D2;

    /** WMS_DESC_TXT_D2*/
	public final EZDSStringItem              wmsDescTxt_D2;

    /** MD_BREAK_TP_CD_D2*/
	public final EZDSStringItemArray              mdBreakTpCd_D2;

    /** XX_EDT_CD_NM_D2*/
	public final EZDSStringItemArray              xxEdtCdNm_D2;

    /** MD_BREAK_TP_CD_P2*/
	public final EZDSStringItem              mdBreakTpCd_P2;

    /** RTE_GUIDE_NUM_D2*/
	public final EZDSStringItem              rteGuideNum_D2;

    /** PCL_CARR_CD_D2*/
	public final EZDSStringItem              pclCarrCd_D2;

    /** PCL_MAX_CAP_NUM_D2*/
	public final EZDSBigDecimalItem              pclMaxCapNum_D2;

    /** PCL_PRTY_CD_D2*/
	public final EZDSStringItem              pclPrtyCd_D2;

    /** LTL_CARR_CD_D2*/
	public final EZDSStringItem              ltlCarrCd_D2;

    /** LTL_MAX_CAP_NUM_D2*/
	public final EZDSBigDecimalItem              ltlMaxCapNum_D2;

    /** LTL_PRTY_CD_D2*/
	public final EZDSStringItem              ltlPrtyCd_D2;

    /** TL_CARR_CD_D2*/
	public final EZDSStringItem              tlCarrCd_D2;

    /** TL_MAX_CAP_NUM_D2*/
	public final EZDSBigDecimalItem              tlMaxCapNum_D2;

    /** TL_PRTY_CD_D2*/
	public final EZDSStringItem              tlPrtyCd_D2;

    /** _EZUpdateUserID_D2*/
	public final EZDSStringItem              ezUpUserID_D2;

    /** _EZUpdateDateTime_D2*/
	public final EZDSStringItem              ezUpTime_D2;

    /** _EZUpTimeZone_D2*/
	public final EZDSStringItem              ezUpTimeZone_D2;


	/**
	 * NLGL0040SMsg is constructor.
	 * The initialization when the instance of NLGL0040SMsg is generated.
	 */
	public NLGL0040SMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0040SMsg is constructor.
	 * The initialization when the instance of NLGL0040SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0040SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDplyTab = (EZDSStringItem)newItem("xxDplyTab");
		whCd_L1 = (EZDSStringItem)newItem("whCd_L1");
		xxEdtCdNm_L1 = (EZDSStringItem)newItem("xxEdtCdNm_L1");
		whCd_H1 = (EZDSStringItem)newItem("whCd_H1");
		whCd_B1 = (EZDSStringItem)newItem("whCd_B1");
		xxTabProt_D1 = (EZDSStringItem)newItem("xxTabProt_D1");
		xxPageShowFromNum_D1 = (EZDSBigDecimalItem)newItem("xxPageShowFromNum_D1");
		xxPageShowToNum_D1 = (EZDSBigDecimalItem)newItem("xxPageShowToNum_D1");
		xxPageShowOfNum_D1 = (EZDSBigDecimalItem)newItem("xxPageShowOfNum_D1");
		A = (business.blap.NLGL0040.NLGL0040_ASMsgArray)newMsgArray("A");
		wmsShipViaTpCd_D2 = (EZDSStringItem)newItem("wmsShipViaTpCd_D2");
		wmsDescTxt_D2 = (EZDSStringItem)newItem("wmsDescTxt_D2");
		mdBreakTpCd_D2 = (EZDSStringItemArray)newItemArray("mdBreakTpCd_D2");
		xxEdtCdNm_D2 = (EZDSStringItemArray)newItemArray("xxEdtCdNm_D2");
		mdBreakTpCd_P2 = (EZDSStringItem)newItem("mdBreakTpCd_P2");
		rteGuideNum_D2 = (EZDSStringItem)newItem("rteGuideNum_D2");
		pclCarrCd_D2 = (EZDSStringItem)newItem("pclCarrCd_D2");
		pclMaxCapNum_D2 = (EZDSBigDecimalItem)newItem("pclMaxCapNum_D2");
		pclPrtyCd_D2 = (EZDSStringItem)newItem("pclPrtyCd_D2");
		ltlCarrCd_D2 = (EZDSStringItem)newItem("ltlCarrCd_D2");
		ltlMaxCapNum_D2 = (EZDSBigDecimalItem)newItem("ltlMaxCapNum_D2");
		ltlPrtyCd_D2 = (EZDSStringItem)newItem("ltlPrtyCd_D2");
		tlCarrCd_D2 = (EZDSStringItem)newItem("tlCarrCd_D2");
		tlMaxCapNum_D2 = (EZDSBigDecimalItem)newItem("tlMaxCapNum_D2");
		tlPrtyCd_D2 = (EZDSStringItem)newItem("tlPrtyCd_D2");
		ezUpUserID_D2 = (EZDSStringItem)newItem("ezUpUserID_D2");
		ezUpTime_D2 = (EZDSStringItem)newItem("ezUpTime_D2");
		ezUpTimeZone_D2 = (EZDSStringItem)newItem("ezUpTimeZone_D2");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0040SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0040SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"whCd_L1", "whCd_L1", "L1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxEdtCdNm_L1", "xxEdtCdNm_L1", "L1", null, TYPE_HANKAKUEISU, "100", null},
	{"whCd_H1", "whCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"whCd_B1", "whCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxTabProt_D1", "xxTabProt_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPageShowFromNum_D1", "xxPageShowFromNum_D1", "D1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_D1", "xxPageShowToNum_D1", "D1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_D1", "xxPageShowOfNum_D1", "D1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.blap.NLGL0040.NLGL0040_ASMsgArray", null, null},
	
	{"wmsShipViaTpCd_D2", "wmsShipViaTpCd_D2", "D2", null, TYPE_HANKAKUEISU, "4", null},
	{"wmsDescTxt_D2", "wmsDescTxt_D2", "D2", null, TYPE_HANKAKUEISU, "100", null},
	{"mdBreakTpCd_D2", "mdBreakTpCd_D2", "D2", "99", TYPE_HANKAKUEISU, "3", null},
	{"xxEdtCdNm_D2", "xxEdtCdNm_D2", "D2", "99", TYPE_HANKAKUEISU, "100", null},
	{"mdBreakTpCd_P2", "mdBreakTpCd_P2", "P2", null, TYPE_HANKAKUEISU, "3", null},
	{"rteGuideNum_D2", "rteGuideNum_D2", "D2", null, TYPE_HANKAKUEISU, "10", null},
	{"pclCarrCd_D2", "pclCarrCd_D2", "D2", null, TYPE_HANKAKUEISU, "40", null},
	{"pclMaxCapNum_D2", "pclMaxCapNum_D2", "D2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"pclPrtyCd_D2", "pclPrtyCd_D2", "D2", null, TYPE_HANKAKUEISU, "4", null},
	{"ltlCarrCd_D2", "ltlCarrCd_D2", "D2", null, TYPE_HANKAKUEISU, "40", null},
	{"ltlMaxCapNum_D2", "ltlMaxCapNum_D2", "D2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ltlPrtyCd_D2", "ltlPrtyCd_D2", "D2", null, TYPE_HANKAKUEISU, "4", null},
	{"tlCarrCd_D2", "tlCarrCd_D2", "D2", null, TYPE_HANKAKUEISU, "40", null},
	{"tlMaxCapNum_D2", "tlMaxCapNum_D2", "D2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"tlPrtyCd_D2", "tlPrtyCd_D2", "D2", null, TYPE_HANKAKUEISU, "4", null},
	{"ezUpUserID_D2", "ezUpUserID_D2", "D2", null, TYPE_HANKAKUEISU, "16", null},
	{"ezUpTime_D2", "ezUpTime_D2", "D2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D2", "ezUpTimeZone_D2", "D2", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_L1
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_L1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_H1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_B1
        {"XX_TAB_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_D1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_D1
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_D1
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_D1
		null,	//A
        {"WMS_SHIP_VIA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsShipViaTpCd_D2
        {"WMS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDescTxt_D2
        {"MD_BREAK_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdBreakTpCd_D2
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_D2
        {"MD_BREAK_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdBreakTpCd_P2
        {"RTE_GUIDE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rteGuideNum_D2
        {"PCL_CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pclCarrCd_D2
        {"PCL_MAX_CAP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pclMaxCapNum_D2
        {"PCL_PRTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pclPrtyCd_D2
        {"LTL_CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ltlCarrCd_D2
        {"LTL_MAX_CAP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ltlMaxCapNum_D2
        {"LTL_PRTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ltlPrtyCd_D2
        {"TL_CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tlCarrCd_D2
        {"TL_MAX_CAP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tlMaxCapNum_D2
        {"TL_PRTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tlPrtyCd_D2
        {"_EZUpdateUserID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpUserID_D2
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D2
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

