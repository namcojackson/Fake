//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530152855000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2500_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2500 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2500_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PSN_NUM_A1*/
	public final EZDCStringItem              psnNum_A1;

    /** PSN_TP_CD_A1*/
	public final EZDCStringItem              psnTpCd_A1;

    /** PSN_TP_NM_A1*/
	public final EZDCStringItem              psnTpNm_A1;

    /** PSN_LAST_NM_A1*/
	public final EZDCStringItem              psnLastNm_A1;

    /** PSN_FIRST_NM_A1*/
	public final EZDCStringItem              psnFirstNm_A1;

    /** JOB_TTL_NM_A1*/
	public final EZDCStringItem              jobTtlNm_A1;

    /** JOB_TTL_CD_A1*/
	public final EZDCStringItem              jobTtlCd_A1;

    /** HR_TTL_NM_A1*/
	public final EZDCStringItem              hrTtlNm_A1;

    /** PSN_CD_A1*/
	public final EZDCStringItem              psnCd_A1;

    /** HR_SUPV_NM_A1*/
	public final EZDCStringItem              hrSupvNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDCDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDCDateItem              effThruDt_A1;


	/**
	 * NMAL2500_ACMsg is constructor.
	 * The initialization when the instance of NMAL2500_ACMsg is generated.
	 */
	public NMAL2500_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2500_ACMsg is constructor.
	 * The initialization when the instance of NMAL2500_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2500_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		psnNum_A1 = (EZDCStringItem)newItem("psnNum_A1");
		psnTpCd_A1 = (EZDCStringItem)newItem("psnTpCd_A1");
		psnTpNm_A1 = (EZDCStringItem)newItem("psnTpNm_A1");
		psnLastNm_A1 = (EZDCStringItem)newItem("psnLastNm_A1");
		psnFirstNm_A1 = (EZDCStringItem)newItem("psnFirstNm_A1");
		jobTtlNm_A1 = (EZDCStringItem)newItem("jobTtlNm_A1");
		jobTtlCd_A1 = (EZDCStringItem)newItem("jobTtlCd_A1");
		hrTtlNm_A1 = (EZDCStringItem)newItem("hrTtlNm_A1");
		psnCd_A1 = (EZDCStringItem)newItem("psnCd_A1");
		hrSupvNm_A1 = (EZDCStringItem)newItem("hrSupvNm_A1");
		effFromDt_A1 = (EZDCDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDCDateItem)newItem("effThruDt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2500_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2500_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"psnNum_A1", "psnNum_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"psnTpCd_A1", "psnTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"psnTpNm_A1", "psnTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_A1", "psnLastNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"psnFirstNm_A1", "psnFirstNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"jobTtlNm_A1", "jobTtlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"jobTtlCd_A1", "jobTtlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"hrTtlNm_A1", "hrTtlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"psnCd_A1", "psnCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"hrSupvNm_A1", "hrSupvNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_A1
        {"PSN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnTpCd_A1
        {"PSN_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnTpNm_A1
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_A1
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_A1
        {"JOB_TTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jobTtlNm_A1
        {"JOB_TTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jobTtlCd_A1
        {"HR_TTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrTtlNm_A1
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_A1
        {"HR_SUPV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrSupvNm_A1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A1
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
