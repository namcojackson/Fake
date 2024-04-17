//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160905111136000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7200_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7200 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7200_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_ITEM_10_TXT*/
	public final EZDCStringItem              xxScrItem10Txt;

    /** PRC_CATG_NM*/
	public final EZDCStringItem              prcCatgNm;

    /** XX_SCR_ITEM_30_TXT*/
	public final EZDCStringItem              xxScrItem30Txt;

    /** XX_SCR_ITEM_7_TXT*/
	public final EZDCStringItem              xxScrItem7Txt;

    /** PRC_RULE_COND_TP_CD*/
	public final EZDCStringItem              prcRuleCondTpCd;


	/**
	 * NMAL7200_ACMsg is constructor.
	 * The initialization when the instance of NMAL7200_ACMsg is generated.
	 */
	public NMAL7200_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7200_ACMsg is constructor.
	 * The initialization when the instance of NMAL7200_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7200_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrItem10Txt = (EZDCStringItem)newItem("xxScrItem10Txt");
		prcCatgNm = (EZDCStringItem)newItem("prcCatgNm");
		xxScrItem30Txt = (EZDCStringItem)newItem("xxScrItem30Txt");
		xxScrItem7Txt = (EZDCStringItem)newItem("xxScrItem7Txt");
		prcRuleCondTpCd = (EZDCStringItem)newItem("prcRuleCondTpCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7200_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7200_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrItem10Txt", "xxScrItem10Txt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm", "prcCatgNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"xxScrItem30Txt", "xxScrItem30Txt", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem7Txt", "xxScrItem7Txt", null, null, TYPE_HANKAKUEISU, "7", null},
	{"prcRuleCondTpCd", "prcRuleCondTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_ITEM_10_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem10Txt
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt
        {"XX_SCR_ITEM_7_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem7Txt
        {"PRC_RULE_COND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpCd
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
