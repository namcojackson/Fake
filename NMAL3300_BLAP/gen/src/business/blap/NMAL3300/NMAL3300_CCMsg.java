//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180727133625000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL3300_CCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL3300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL3300 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL3300_CCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** HRCH_EFF_LVL_TP_CD_C1*/
	public final EZDCStringItem              hrchEffLvlTpCd_C1;

    /** HRCH_EFF_LVL_TP_NM_C1*/
	public final EZDCStringItem              hrchEffLvlTpNm_C1;

    /** LOC_NUM_C1*/
	public final EZDCStringItem              locNum_C1;

    /** DS_SPCL_HDLG_TP_NM_C1*/
	public final EZDCStringItem              dsSpclHdlgTpNm_C1;

    /** DS_SPCL_HDLG_TP_VAL_NM_C1*/
	public final EZDCStringItem              dsSpclHdlgTpValNm_C1;

    /** CUST_EFF_LVL_NM_C1*/
	public final EZDCStringItem              custEffLvlNm_C1;

    /** EFF_FROM_DT_C1*/
	public final EZDCDateItem              effFromDt_C1;

    /** EFF_THRU_DT_C1*/
	public final EZDCDateItem              effThruDt_C1;

    /** _EZRegistrationDateTime_C1*/
	public final EZDCStringItem              ezInTime_C1;

    /** _EZUpdateDateTime_C1*/
	public final EZDCStringItem              ezUpTime_C1;


	/**
	 * NMAL3300_CCMsg is constructor.
	 * The initialization when the instance of NMAL3300_CCMsg is generated.
	 */
	public NMAL3300_CCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL3300_CCMsg is constructor.
	 * The initialization when the instance of NMAL3300_CCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL3300_CCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		hrchEffLvlTpCd_C1 = (EZDCStringItem)newItem("hrchEffLvlTpCd_C1");
		hrchEffLvlTpNm_C1 = (EZDCStringItem)newItem("hrchEffLvlTpNm_C1");
		locNum_C1 = (EZDCStringItem)newItem("locNum_C1");
		dsSpclHdlgTpNm_C1 = (EZDCStringItem)newItem("dsSpclHdlgTpNm_C1");
		dsSpclHdlgTpValNm_C1 = (EZDCStringItem)newItem("dsSpclHdlgTpValNm_C1");
		custEffLvlNm_C1 = (EZDCStringItem)newItem("custEffLvlNm_C1");
		effFromDt_C1 = (EZDCDateItem)newItem("effFromDt_C1");
		effThruDt_C1 = (EZDCDateItem)newItem("effThruDt_C1");
		ezInTime_C1 = (EZDCStringItem)newItem("ezInTime_C1");
		ezUpTime_C1 = (EZDCStringItem)newItem("ezUpTime_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL3300_CCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL3300_CCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"hrchEffLvlTpCd_C1", "hrchEffLvlTpCd_C1", "C1", null, TYPE_HANKAKUEISU, "2", null},
	{"hrchEffLvlTpNm_C1", "hrchEffLvlTpNm_C1", "C1", null, TYPE_HANKAKUEISU, "40", null},
	{"locNum_C1", "locNum_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsSpclHdlgTpNm_C1", "dsSpclHdlgTpNm_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsSpclHdlgTpValNm_C1", "dsSpclHdlgTpValNm_C1", "C1", null, TYPE_HANKAKUEISU, "100", null},
	{"custEffLvlNm_C1", "custEffLvlNm_C1", "C1", null, TYPE_HANKAKUEISU, "60", null},
	{"effFromDt_C1", "effFromDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_C1", "effThruDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezInTime_C1", "ezInTime_C1", "C1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTime_C1", "ezUpTime_C1", "C1", null, TYPE_HANKAKUEISU, "17", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"HRCH_EFF_LVL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrchEffLvlTpCd_C1
        {"HRCH_EFF_LVL_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrchEffLvlTpNm_C1
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_C1
        {"DS_SPCL_HDLG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSpclHdlgTpNm_C1
        {"DS_SPCL_HDLG_TP_VAL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSpclHdlgTpValNm_C1
        {"CUST_EFF_LVL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custEffLvlNm_C1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_C1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_C1
        {"_EZRegistrationDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_C1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_C1
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
