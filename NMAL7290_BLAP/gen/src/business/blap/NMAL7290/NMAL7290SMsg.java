//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161108161332000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7290SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7290;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7290 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7290SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_RULE_CATG_CD*/
	public final EZDSStringItem              prcRuleCatgCd;

    /** PRC_RULE_NM*/
	public final EZDSStringItem              prcRuleNm;

    /** LINE_BIZ_TP_CD*/
	public final EZDSStringItem              lineBizTpCd;

    /** PRC_RULE_COND_TP_CD*/
	public final EZDSStringItem              prcRuleCondTpCd;

    /** DS_ORD_CATG_CD*/
	public final EZDSStringItem              dsOrdCatgCd;

    /** DS_ORD_TP_CD*/
	public final EZDSStringItem              dsOrdTpCd;

    /** A*/
	public final business.blap.NMAL7290.NMAL7290_ASMsgArray              A;

    /** PRC_RULE_PRCD_GRP_NUM*/
	public final EZDSBigDecimalItem              prcRulePrcdGrpNum;

    /** PRC_RULE_PRCD_GRP_NM*/
	public final EZDSStringItem              prcRulePrcdGrpNm;

    /** B*/
	public final business.blap.NMAL7290.NMAL7290_BSMsgArray              B;

    /** X*/
	public final business.blap.NMAL7290.NMAL7290_XSMsgArray              X;


	/**
	 * NMAL7290SMsg is constructor.
	 * The initialization when the instance of NMAL7290SMsg is generated.
	 */
	public NMAL7290SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7290SMsg is constructor.
	 * The initialization when the instance of NMAL7290SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7290SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcRuleCatgCd = (EZDSStringItem)newItem("prcRuleCatgCd");
		prcRuleNm = (EZDSStringItem)newItem("prcRuleNm");
		lineBizTpCd = (EZDSStringItem)newItem("lineBizTpCd");
		prcRuleCondTpCd = (EZDSStringItem)newItem("prcRuleCondTpCd");
		dsOrdCatgCd = (EZDSStringItem)newItem("dsOrdCatgCd");
		dsOrdTpCd = (EZDSStringItem)newItem("dsOrdTpCd");
		A = (business.blap.NMAL7290.NMAL7290_ASMsgArray)newMsgArray("A");
		prcRulePrcdGrpNum = (EZDSBigDecimalItem)newItem("prcRulePrcdGrpNum");
		prcRulePrcdGrpNm = (EZDSStringItem)newItem("prcRulePrcdGrpNm");
		B = (business.blap.NMAL7290.NMAL7290_BSMsgArray)newMsgArray("B");
		X = (business.blap.NMAL7290.NMAL7290_XSMsgArray)newMsgArray("X");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7290SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7290SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcRuleCatgCd", "prcRuleCatgCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcRuleNm", "prcRuleNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"prcRuleCondTpCd", "prcRuleCondTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "200", "business.blap.NMAL7290.NMAL7290_ASMsgArray", null, null},
	
	{"prcRulePrcdGrpNum", "prcRulePrcdGrpNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prcRulePrcdGrpNm", "prcRulePrcdGrpNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"B", "B", null, "200", "business.blap.NMAL7290.NMAL7290_BSMsgArray", null, null},
	
	{"X", "X", null, "200", "business.blap.NMAL7290.NMAL7290_XSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_RULE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCatgCd
        {"PRC_RULE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleNm
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"PRC_RULE_COND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpCd
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
		null,	//A
        {"PRC_RULE_PRCD_GRP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRulePrcdGrpNum
        {"PRC_RULE_PRCD_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRulePrcdGrpNm
		null,	//B
		null,	//X
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

