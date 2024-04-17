//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170213142339000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0170SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0170 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0170SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SUPD_TO_MDSE_CD*/
	public final EZDSStringItem              supdToMdseCd;

    /** MDSE_ITEM_TP_CD_H1*/
	public final EZDSStringItem              mdseItemTpCd_H1;

    /** MDSE_ITEM_TP_CD_L1*/
	public final EZDSStringItemArray              mdseItemTpCd_L1;

    /** MDSE_ITEM_TP_DESC_TXT_L1*/
	public final EZDSStringItemArray              mdseItemTpDescTxt_L1;

    /** MDSE_ITEM_CLS_TP_CD_H1*/
	public final EZDSStringItem              mdseItemClsTpCd_H1;

    /** MDSE_ITEM_CLS_TP_CD_L1*/
	public final EZDSStringItemArray              mdseItemClsTpCd_L1;

    /** MDSE_ITEM_CLS_TP_DESC_TXT_L1*/
	public final EZDSStringItemArray              mdseItemClsTpDescTxt_L1;

    /** SUPD_RELN_STAGE_DT_FM*/
	public final EZDSDateItem              supdRelnStageDt_FM;

    /** SUPD_RELN_STAGE_DT_TO*/
	public final EZDSDateItem              supdRelnStageDt_TO;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDSBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDSBigDecimalItem              xxPageShowTotNum;

    /** A*/
	public final business.blap.NMAL0170.NMAL0170_ASMsgArray              A;

    /** SUPD_RELN_STAGE_PK_P*/
	public final EZDSBigDecimalItem              supdRelnStagePk_P;

    /** B*/
	public final business.blap.NMAL0170.NMAL0170_BSMsgArray              B;


	/**
	 * NMAL0170SMsg is constructor.
	 * The initialization when the instance of NMAL0170SMsg is generated.
	 */
	public NMAL0170SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0170SMsg is constructor.
	 * The initialization when the instance of NMAL0170SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0170SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		supdToMdseCd = (EZDSStringItem)newItem("supdToMdseCd");
		mdseItemTpCd_H1 = (EZDSStringItem)newItem("mdseItemTpCd_H1");
		mdseItemTpCd_L1 = (EZDSStringItemArray)newItemArray("mdseItemTpCd_L1");
		mdseItemTpDescTxt_L1 = (EZDSStringItemArray)newItemArray("mdseItemTpDescTxt_L1");
		mdseItemClsTpCd_H1 = (EZDSStringItem)newItem("mdseItemClsTpCd_H1");
		mdseItemClsTpCd_L1 = (EZDSStringItemArray)newItemArray("mdseItemClsTpCd_L1");
		mdseItemClsTpDescTxt_L1 = (EZDSStringItemArray)newItemArray("mdseItemClsTpDescTxt_L1");
		supdRelnStageDt_FM = (EZDSDateItem)newItem("supdRelnStageDt_FM");
		supdRelnStageDt_TO = (EZDSDateItem)newItem("supdRelnStageDt_TO");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDSBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDSBigDecimalItem)newItem("xxPageShowTotNum");
		A = (business.blap.NMAL0170.NMAL0170_ASMsgArray)newMsgArray("A");
		supdRelnStagePk_P = (EZDSBigDecimalItem)newItem("supdRelnStagePk_P");
		B = (business.blap.NMAL0170.NMAL0170_BSMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0170SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0170SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"supdToMdseCd", "supdToMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseItemTpCd_H1", "mdseItemTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpCd_L1", "mdseItemTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpDescTxt_L1", "mdseItemTpDescTxt_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"mdseItemClsTpCd_H1", "mdseItemClsTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemClsTpCd_L1", "mdseItemClsTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemClsTpDescTxt_L1", "mdseItemClsTpDescTxt_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"supdRelnStageDt_FM", "supdRelnStageDt_FM", "FM", null, TYPE_NENTSUKIHI, "8", null},
	{"supdRelnStageDt_TO", "supdRelnStageDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "4000", "business.blap.NMAL0170.NMAL0170_ASMsgArray", null, null},
	
	{"supdRelnStagePk_P", "supdRelnStagePk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"B", "B", null, "4000", "business.blap.NMAL0170.NMAL0170_BSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SUPD_TO_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdToMdseCd
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_H1
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_L1
        {"MDSE_ITEM_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpDescTxt_L1
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_H1
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_L1
        {"MDSE_ITEM_CLS_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpDescTxt_L1
        {"SUPD_RELN_STAGE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdRelnStageDt_FM
        {"SUPD_RELN_STAGE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdRelnStageDt_TO
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
		null,	//A
        {"SUPD_RELN_STAGE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdRelnStagePk_P
		null,	//B
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
