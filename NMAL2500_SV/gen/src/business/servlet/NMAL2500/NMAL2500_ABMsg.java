//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170530152917000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2500_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2500 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2500_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PSN_NUM_A2*/
	public final EZDBStringItem              psnNum_A2;

    /** PSN_NUM_A1*/
	public final EZDBStringItem              psnNum_A1;

    /** PSN_TP_CD_A1*/
	public final EZDBStringItem              psnTpCd_A1;

    /** PSN_TP_NM_A1*/
	public final EZDBStringItem              psnTpNm_A1;

    /** PSN_LAST_NM_A1*/
	public final EZDBStringItem              psnLastNm_A1;

    /** PSN_FIRST_NM_A1*/
	public final EZDBStringItem              psnFirstNm_A1;

    /** JOB_TTL_NM_A1*/
	public final EZDBStringItem              jobTtlNm_A1;

    /** JOB_TTL_CD_A1*/
	public final EZDBStringItem              jobTtlCd_A1;

    /** HR_TTL_NM_A1*/
	public final EZDBStringItem              hrTtlNm_A1;

    /** PSN_CD_A1*/
	public final EZDBStringItem              psnCd_A1;

    /** HR_SUPV_NM_A1*/
	public final EZDBStringItem              hrSupvNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDBDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDBDateItem              effThruDt_A1;


	/**
	 * NMAL2500_ABMsg is constructor.
	 * The initialization when the instance of NMAL2500_ABMsg is generated.
	 */
	public NMAL2500_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2500_ABMsg is constructor.
	 * The initialization when the instance of NMAL2500_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2500_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		psnNum_A2 = (EZDBStringItem)newItem("psnNum_A2");
		psnNum_A1 = (EZDBStringItem)newItem("psnNum_A1");
		psnTpCd_A1 = (EZDBStringItem)newItem("psnTpCd_A1");
		psnTpNm_A1 = (EZDBStringItem)newItem("psnTpNm_A1");
		psnLastNm_A1 = (EZDBStringItem)newItem("psnLastNm_A1");
		psnFirstNm_A1 = (EZDBStringItem)newItem("psnFirstNm_A1");
		jobTtlNm_A1 = (EZDBStringItem)newItem("jobTtlNm_A1");
		jobTtlCd_A1 = (EZDBStringItem)newItem("jobTtlCd_A1");
		hrTtlNm_A1 = (EZDBStringItem)newItem("hrTtlNm_A1");
		psnCd_A1 = (EZDBStringItem)newItem("psnCd_A1");
		hrSupvNm_A1 = (EZDBStringItem)newItem("hrSupvNm_A1");
		effFromDt_A1 = (EZDBDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDBDateItem)newItem("effThruDt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2500_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2500_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"psnNum_A2", "psnNum_A2", "A2", null, TYPE_HANKAKUEISU, "50", null},
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

        {"PSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_A2
        {"PSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_A1
        {"PSN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnTpCd_A1
        {"PSN_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnTpNm_A1
        {"PSN_LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_A1
        {"PSN_FIRST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_A1
        {"JOB_TTL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jobTtlNm_A1
        {"JOB_TTL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jobTtlCd_A1
        {"HR_TTL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrTtlNm_A1
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_A1
        {"HR_SUPV_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrSupvNm_A1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A1
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

