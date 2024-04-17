//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20151105133347000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0590_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0590;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0590 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0590_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_RPT_GRP_NUM*/
	public final EZDCStringItem              dsContrRptGrpNum;

    /** DS_CONTR_RPT_GRP_DESC_TXT*/
	public final EZDCStringItem              dsContrRptGrpDescTxt;

    /** DS_CONTR_RPT_GRP_START_DT*/
	public final EZDCDateItem              dsContrRptGrpStartDt;

    /** DS_CONTR_RPT_GRP_END_DT*/
	public final EZDCDateItem              dsContrRptGrpEndDt;

    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;


	/**
	 * NSAL0590_ACMsg is constructor.
	 * The initialization when the instance of NSAL0590_ACMsg is generated.
	 */
	public NSAL0590_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0590_ACMsg is constructor.
	 * The initialization when the instance of NSAL0590_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0590_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrRptGrpNum = (EZDCStringItem)newItem("dsContrRptGrpNum");
		dsContrRptGrpDescTxt = (EZDCStringItem)newItem("dsContrRptGrpDescTxt");
		dsContrRptGrpStartDt = (EZDCDateItem)newItem("dsContrRptGrpStartDt");
		dsContrRptGrpEndDt = (EZDCDateItem)newItem("dsContrRptGrpEndDt");
		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0590_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0590_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrRptGrpNum", "dsContrRptGrpNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"dsContrRptGrpDescTxt", "dsContrRptGrpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrRptGrpStartDt", "dsContrRptGrpStartDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrRptGrpEndDt", "dsContrRptGrpEndDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_RPT_GRP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrRptGrpNum
        {"DS_CONTR_RPT_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrRptGrpDescTxt
        {"DS_CONTR_RPT_GRP_START_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrRptGrpStartDt
        {"DS_CONTR_RPT_GRP_END_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrRptGrpEndDt
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
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

