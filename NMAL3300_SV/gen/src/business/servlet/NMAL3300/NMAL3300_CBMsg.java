//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180727133558000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL3300_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL3300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL3300 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL3300_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** HRCH_EFF_LVL_TP_CD_C1*/
	public final EZDBStringItem              hrchEffLvlTpCd_C1;

    /** HRCH_EFF_LVL_TP_NM_C1*/
	public final EZDBStringItem              hrchEffLvlTpNm_C1;

    /** LOC_NUM_C1*/
	public final EZDBStringItem              locNum_C1;

    /** DS_SPCL_HDLG_TP_NM_C1*/
	public final EZDBStringItem              dsSpclHdlgTpNm_C1;

    /** DS_SPCL_HDLG_TP_VAL_NM_C1*/
	public final EZDBStringItem              dsSpclHdlgTpValNm_C1;

    /** CUST_EFF_LVL_NM_C1*/
	public final EZDBStringItem              custEffLvlNm_C1;

    /** EFF_FROM_DT_C1*/
	public final EZDBDateItem              effFromDt_C1;

    /** EFF_THRU_DT_C1*/
	public final EZDBDateItem              effThruDt_C1;

    /** _EZRegistrationDateTime_C1*/
	public final EZDBStringItem              ezInTime_C1;

    /** _EZUpdateDateTime_C1*/
	public final EZDBStringItem              ezUpTime_C1;


	/**
	 * NMAL3300_CBMsg is constructor.
	 * The initialization when the instance of NMAL3300_CBMsg is generated.
	 */
	public NMAL3300_CBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL3300_CBMsg is constructor.
	 * The initialization when the instance of NMAL3300_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL3300_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		hrchEffLvlTpCd_C1 = (EZDBStringItem)newItem("hrchEffLvlTpCd_C1");
		hrchEffLvlTpNm_C1 = (EZDBStringItem)newItem("hrchEffLvlTpNm_C1");
		locNum_C1 = (EZDBStringItem)newItem("locNum_C1");
		dsSpclHdlgTpNm_C1 = (EZDBStringItem)newItem("dsSpclHdlgTpNm_C1");
		dsSpclHdlgTpValNm_C1 = (EZDBStringItem)newItem("dsSpclHdlgTpValNm_C1");
		custEffLvlNm_C1 = (EZDBStringItem)newItem("custEffLvlNm_C1");
		effFromDt_C1 = (EZDBDateItem)newItem("effFromDt_C1");
		effThruDt_C1 = (EZDBDateItem)newItem("effThruDt_C1");
		ezInTime_C1 = (EZDBStringItem)newItem("ezInTime_C1");
		ezUpTime_C1 = (EZDBStringItem)newItem("ezUpTime_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL3300_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL3300_CBMsgArray();
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

        {"HRCH_EFF_LVL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrchEffLvlTpCd_C1
        {"HRCH_EFF_LVL_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrchEffLvlTpNm_C1
        {"LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_C1
        {"DS_SPCL_HDLG_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSpclHdlgTpNm_C1
        {"DS_SPCL_HDLG_TP_VAL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSpclHdlgTpValNm_C1
        {"CUST_EFF_LVL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custEffLvlNm_C1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_C1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_C1
        {"_EZRegistrationDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_C1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_C1
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
