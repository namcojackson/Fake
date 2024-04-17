//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20181128185753000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1900CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1900;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1900 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1900CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD*/
	public final EZDCStringItem              xxModeCd;

    /** LINE_BIZ_TP_CD*/
	public final EZDCStringItem              lineBizTpCd;

    /** PRC_RULE_MOD_TP_CD*/
	public final EZDCStringItem              prcRuleModTpCd;

    /** DS_ORD_CATG_CD*/
	public final EZDCStringItem              dsOrdCatgCd;

    /** DS_ORD_TP_CD*/
	public final EZDCStringItem              dsOrdTpCd;

    /** PRC_GRP_TRX_TP_CD_H1*/
	public final EZDCStringItem              prcGrpTrxTpCd_H1;

    /** PRC_GRP_TRX_TP_CD_L1*/
	public final EZDCStringItemArray              prcGrpTrxTpCd_L1;

    /** PRC_GRP_TRX_TP_DESC_TXT_L1*/
	public final EZDCStringItemArray              prcGrpTrxTpDescTxt_L1;

    /** A*/
	public final business.blap.NWAL1900.NWAL1900_ACMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** B*/
	public final business.blap.NWAL1900.NWAL1900_BCMsgArray              B;

    /** L*/
	public final business.blap.NWAL1900.NWAL1900_LCMsgArray              L;


	/**
	 * NWAL1900CMsg is constructor.
	 * The initialization when the instance of NWAL1900CMsg is generated.
	 */
	public NWAL1900CMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1900CMsg is constructor.
	 * The initialization when the instance of NWAL1900CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1900CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd = (EZDCStringItem)newItem("xxModeCd");
		lineBizTpCd = (EZDCStringItem)newItem("lineBizTpCd");
		prcRuleModTpCd = (EZDCStringItem)newItem("prcRuleModTpCd");
		dsOrdCatgCd = (EZDCStringItem)newItem("dsOrdCatgCd");
		dsOrdTpCd = (EZDCStringItem)newItem("dsOrdTpCd");
		prcGrpTrxTpCd_H1 = (EZDCStringItem)newItem("prcGrpTrxTpCd_H1");
		prcGrpTrxTpCd_L1 = (EZDCStringItemArray)newItemArray("prcGrpTrxTpCd_L1");
		prcGrpTrxTpDescTxt_L1 = (EZDCStringItemArray)newItemArray("prcGrpTrxTpDescTxt_L1");
		A = (business.blap.NWAL1900.NWAL1900_ACMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		B = (business.blap.NWAL1900.NWAL1900_BCMsgArray)newMsgArray("B");
		L = (business.blap.NWAL1900.NWAL1900_LCMsgArray)newMsgArray("L");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1900CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1900CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"prcRuleModTpCd", "prcRuleModTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"prcGrpTrxTpCd_H1", "prcGrpTrxTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcGrpTrxTpCd_L1", "prcGrpTrxTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcGrpTrxTpDescTxt_L1", "prcGrpTrxTpDescTxt_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "40", "business.blap.NWAL1900.NWAL1900_ACMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"B", "B", null, "200", "business.blap.NWAL1900.NWAL1900_BCMsgArray", null, null},
	
	{"L", "L", null, "200", "business.blap.NWAL1900.NWAL1900_LCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"PRC_RULE_MOD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleModTpCd
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"PRC_GRP_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTrxTpCd_H1
        {"PRC_GRP_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTrxTpCd_L1
        {"PRC_GRP_TRX_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTrxTpDescTxt_L1
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//B
		null,	//L
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

