//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20221206135353000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC610001_InstructionListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMZC610001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC610001_InstructionListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CUST_MSG_TXT*/
	public final EZDPStringItem              dsCustMsgTxt;

    /** DS_PRINT_ON_INV_FLG*/
	public final EZDPStringItem              dsPrintOnInvFlg;

    /** CUST_EFF_LVL_CD*/
	public final EZDPStringItem              custEffLvlCd;

    /** EFF_THRU_DT*/
	public final EZDPDateItem              effThruDt;

    /** DS_CUST_SPCL_MSG_PK*/
	public final EZDPBigDecimalItem              dsCustSpclMsgPk;

    /** LINE_BIZ_TP_CD*/
	public final EZDPStringItem              lineBizTpCd;

    /** DS_BIZ_AREA_CD*/
	public final EZDPStringItem              dsBizAreaCd;

    /** DS_CUST_MSG_TP_CD*/
	public final EZDPStringItem              dsCustMsgTpCd;


	/**
	 * NMZC610001_InstructionListPMsg is constructor.
	 * The initialization when the instance of NMZC610001_InstructionListPMsg is generated.
	 */
	public NMZC610001_InstructionListPMsg() {
		this(false, -1);
	}

	/**
	 * NMZC610001_InstructionListPMsg is constructor.
	 * The initialization when the instance of NMZC610001_InstructionListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC610001_InstructionListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsCustMsgTxt = (EZDPStringItem)newItem("dsCustMsgTxt");
		dsPrintOnInvFlg = (EZDPStringItem)newItem("dsPrintOnInvFlg");
		custEffLvlCd = (EZDPStringItem)newItem("custEffLvlCd");
		effThruDt = (EZDPDateItem)newItem("effThruDt");
		dsCustSpclMsgPk = (EZDPBigDecimalItem)newItem("dsCustSpclMsgPk");
		lineBizTpCd = (EZDPStringItem)newItem("lineBizTpCd");
		dsBizAreaCd = (EZDPStringItem)newItem("dsBizAreaCd");
		dsCustMsgTpCd = (EZDPStringItem)newItem("dsCustMsgTpCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC610001_InstructionListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC610001_InstructionListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsCustMsgTxt", "dsCustMsgTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"dsPrintOnInvFlg", "dsPrintOnInvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"custEffLvlCd", "custEffLvlCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsCustSpclMsgPk", "dsCustSpclMsgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsBizAreaCd", "dsBizAreaCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsCustMsgTpCd", "dsCustMsgTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CUST_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustMsgTxt
        {"DS_PRINT_ON_INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrintOnInvFlg
        {"CUST_EFF_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custEffLvlCd
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"DS_CUST_SPCL_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSpclMsgPk
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd
        {"DS_CUST_MSG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustMsgTpCd
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
