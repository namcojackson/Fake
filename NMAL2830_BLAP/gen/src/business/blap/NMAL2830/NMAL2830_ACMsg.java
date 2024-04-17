//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530135910000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2830_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2830;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2830 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2830_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_ID_AT*/
	public final EZDCStringItem              xxRowId_AT;

    /** XX_ROW_ID_AN*/
	public final EZDCStringItem              xxRowId_AN;

    /** LOC_NUM_M*/
	public final EZDCStringItem              locNum_M;

    /** DS_ACCT_NUM_A1*/
	public final EZDCStringItem              dsAcctNum_A1;

    /** LOC_NUM_A1*/
	public final EZDCStringItem              locNum_A1;

    /** DS_XREF_ACCT_CD_A1*/
	public final EZDCStringItem              dsXrefAcctCd_A1;

    /** DS_ACCT_TP_CD_A1*/
	public final EZDCStringItem              dsAcctTpCd_A1;

    /** DS_ACCT_TP_NM_A1*/
	public final EZDCStringItem              dsAcctTpNm_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDCStringItem              dsAcctNm_A1;

    /** XX_ALL_LINE_ADDR_A1*/
	public final EZDCStringItem              xxAllLineAddr_A1;

    /** CTY_ADDR_A1*/
	public final EZDCStringItem              ctyAddr_A1;

    /** ST_CD_A1*/
	public final EZDCStringItem              stCd_A1;

    /** POST_CD_A1*/
	public final EZDCStringItem              postCd_A1;

    /** XX_ASG_TRTY_NM_A1*/
	public final EZDCStringItem              xxAsgTrtyNm_A1;

    /** XX_CHK_BOX_E1*/
	public final EZDCStringItem              xxChkBox_E1;

    /** XX_CHK_BOX_P1*/
	public final EZDCStringItem              xxChkBox_P1;

    /** XX_CHK_BOX_D1*/
	public final EZDCStringItem              xxChkBox_D1;

    /** DS_ACCT_NUM_A2*/
	public final EZDCStringItem              dsAcctNum_A2;

    /** LOC_NUM_A2*/
	public final EZDCStringItem              locNum_A2;

    /** DS_XREF_ACCT_CD_A2*/
	public final EZDCStringItem              dsXrefAcctCd_A2;

    /** DS_ACCT_TP_CD_A2*/
	public final EZDCStringItem              dsAcctTpCd_A2;

    /** DS_ACCT_TP_NM_A2*/
	public final EZDCStringItem              dsAcctTpNm_A2;

    /** DS_ACCT_NM_A2*/
	public final EZDCStringItem              dsAcctNm_A2;

    /** XX_ALL_LINE_ADDR_A2*/
	public final EZDCStringItem              xxAllLineAddr_A2;

    /** CTY_ADDR_A2*/
	public final EZDCStringItem              ctyAddr_A2;

    /** ST_CD_A2*/
	public final EZDCStringItem              stCd_A2;

    /** POST_CD_A2*/
	public final EZDCStringItem              postCd_A2;

    /** XX_ASG_TRTY_NM_A2*/
	public final EZDCStringItem              xxAsgTrtyNm_A2;

    /** XX_CHK_BOX_E2*/
	public final EZDCStringItem              xxChkBox_E2;

    /** XX_CHK_BOX_P2*/
	public final EZDCStringItem              xxChkBox_P2;

    /** XX_CHK_BOX_D2*/
	public final EZDCStringItem              xxChkBox_D2;

    /** XX_CHK_BOX_M*/
	public final EZDCStringItem              xxChkBox_M;

    /** XX_CHK_BOX_AM*/
	public final EZDCStringItem              xxChkBox_AM;

    /** DS_ACCT_NUM_A3*/
	public final EZDCStringItem              dsAcctNum_A3;

    /** LOC_NUM_A3*/
	public final EZDCStringItem              locNum_A3;

    /** DS_XREF_ACCT_CD_A3*/
	public final EZDCStringItem              dsXrefAcctCd_A3;

    /** DS_ACCT_TP_CD_A3*/
	public final EZDCStringItem              dsAcctTpCd_A3;

    /** DS_ACCT_TP_NM_A3*/
	public final EZDCStringItem              dsAcctTpNm_A3;

    /** DS_ACCT_NM_A3*/
	public final EZDCStringItem              dsAcctNm_A3;

    /** XX_ALL_LINE_ADDR_A3*/
	public final EZDCStringItem              xxAllLineAddr_A3;

    /** CTY_ADDR_A3*/
	public final EZDCStringItem              ctyAddr_A3;

    /** ST_CD_A3*/
	public final EZDCStringItem              stCd_A3;

    /** POST_CD_A3*/
	public final EZDCStringItem              postCd_A3;

    /** XX_ASG_TRTY_NM_A3*/
	public final EZDCStringItem              xxAsgTrtyNm_A3;

    /** XX_CHK_BOX_E3*/
	public final EZDCStringItem              xxChkBox_E3;

    /** XX_CHK_BOX_P3*/
	public final EZDCStringItem              xxChkBox_P3;

    /** XX_CHK_BOX_D3*/
	public final EZDCStringItem              xxChkBox_D3;


	/**
	 * NMAL2830_ACMsg is constructor.
	 * The initialization when the instance of NMAL2830_ACMsg is generated.
	 */
	public NMAL2830_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2830_ACMsg is constructor.
	 * The initialization when the instance of NMAL2830_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2830_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowId_AT = (EZDCStringItem)newItem("xxRowId_AT");
		xxRowId_AN = (EZDCStringItem)newItem("xxRowId_AN");
		locNum_M = (EZDCStringItem)newItem("locNum_M");
		dsAcctNum_A1 = (EZDCStringItem)newItem("dsAcctNum_A1");
		locNum_A1 = (EZDCStringItem)newItem("locNum_A1");
		dsXrefAcctCd_A1 = (EZDCStringItem)newItem("dsXrefAcctCd_A1");
		dsAcctTpCd_A1 = (EZDCStringItem)newItem("dsAcctTpCd_A1");
		dsAcctTpNm_A1 = (EZDCStringItem)newItem("dsAcctTpNm_A1");
		dsAcctNm_A1 = (EZDCStringItem)newItem("dsAcctNm_A1");
		xxAllLineAddr_A1 = (EZDCStringItem)newItem("xxAllLineAddr_A1");
		ctyAddr_A1 = (EZDCStringItem)newItem("ctyAddr_A1");
		stCd_A1 = (EZDCStringItem)newItem("stCd_A1");
		postCd_A1 = (EZDCStringItem)newItem("postCd_A1");
		xxAsgTrtyNm_A1 = (EZDCStringItem)newItem("xxAsgTrtyNm_A1");
		xxChkBox_E1 = (EZDCStringItem)newItem("xxChkBox_E1");
		xxChkBox_P1 = (EZDCStringItem)newItem("xxChkBox_P1");
		xxChkBox_D1 = (EZDCStringItem)newItem("xxChkBox_D1");
		dsAcctNum_A2 = (EZDCStringItem)newItem("dsAcctNum_A2");
		locNum_A2 = (EZDCStringItem)newItem("locNum_A2");
		dsXrefAcctCd_A2 = (EZDCStringItem)newItem("dsXrefAcctCd_A2");
		dsAcctTpCd_A2 = (EZDCStringItem)newItem("dsAcctTpCd_A2");
		dsAcctTpNm_A2 = (EZDCStringItem)newItem("dsAcctTpNm_A2");
		dsAcctNm_A2 = (EZDCStringItem)newItem("dsAcctNm_A2");
		xxAllLineAddr_A2 = (EZDCStringItem)newItem("xxAllLineAddr_A2");
		ctyAddr_A2 = (EZDCStringItem)newItem("ctyAddr_A2");
		stCd_A2 = (EZDCStringItem)newItem("stCd_A2");
		postCd_A2 = (EZDCStringItem)newItem("postCd_A2");
		xxAsgTrtyNm_A2 = (EZDCStringItem)newItem("xxAsgTrtyNm_A2");
		xxChkBox_E2 = (EZDCStringItem)newItem("xxChkBox_E2");
		xxChkBox_P2 = (EZDCStringItem)newItem("xxChkBox_P2");
		xxChkBox_D2 = (EZDCStringItem)newItem("xxChkBox_D2");
		xxChkBox_M = (EZDCStringItem)newItem("xxChkBox_M");
		xxChkBox_AM = (EZDCStringItem)newItem("xxChkBox_AM");
		dsAcctNum_A3 = (EZDCStringItem)newItem("dsAcctNum_A3");
		locNum_A3 = (EZDCStringItem)newItem("locNum_A3");
		dsXrefAcctCd_A3 = (EZDCStringItem)newItem("dsXrefAcctCd_A3");
		dsAcctTpCd_A3 = (EZDCStringItem)newItem("dsAcctTpCd_A3");
		dsAcctTpNm_A3 = (EZDCStringItem)newItem("dsAcctTpNm_A3");
		dsAcctNm_A3 = (EZDCStringItem)newItem("dsAcctNm_A3");
		xxAllLineAddr_A3 = (EZDCStringItem)newItem("xxAllLineAddr_A3");
		ctyAddr_A3 = (EZDCStringItem)newItem("ctyAddr_A3");
		stCd_A3 = (EZDCStringItem)newItem("stCd_A3");
		postCd_A3 = (EZDCStringItem)newItem("postCd_A3");
		xxAsgTrtyNm_A3 = (EZDCStringItem)newItem("xxAsgTrtyNm_A3");
		xxChkBox_E3 = (EZDCStringItem)newItem("xxChkBox_E3");
		xxChkBox_P3 = (EZDCStringItem)newItem("xxChkBox_P3");
		xxChkBox_D3 = (EZDCStringItem)newItem("xxChkBox_D3");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2830_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2830_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowId_AT", "xxRowId_AT", "AT", null, TYPE_HANKAKUEISU, "18", null},
	{"xxRowId_AN", "xxRowId_AN", "AN", null, TYPE_HANKAKUEISU, "18", null},
	{"locNum_M", "locNum_M", "M", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNum_A1", "dsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNum_A1", "locNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsXrefAcctCd_A1", "dsXrefAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"dsAcctTpCd_A1", "dsAcctTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsAcctTpNm_A1", "dsAcctTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_A1", "xxAllLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "400", null},
	{"ctyAddr_A1", "ctyAddr_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_A1", "stCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_A1", "postCd_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"xxAsgTrtyNm_A1", "xxAsgTrtyNm_A1", "A1", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxChkBox_E1", "xxChkBox_E1", "E1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_P1", "xxChkBox_P1", "P1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_D1", "xxChkBox_D1", "D1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsAcctNum_A2", "dsAcctNum_A2", "A2", null, TYPE_HANKAKUEISU, "20", null},
	{"locNum_A2", "locNum_A2", "A2", null, TYPE_HANKAKUEISU, "30", null},
	{"dsXrefAcctCd_A2", "dsXrefAcctCd_A2", "A2", null, TYPE_HANKAKUEISU, "60", null},
	{"dsAcctTpCd_A2", "dsAcctTpCd_A2", "A2", null, TYPE_HANKAKUEISU, "2", null},
	{"dsAcctTpNm_A2", "dsAcctTpNm_A2", "A2", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A2", "dsAcctNm_A2", "A2", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_A2", "xxAllLineAddr_A2", "A2", null, TYPE_HANKAKUEISU, "400", null},
	{"ctyAddr_A2", "ctyAddr_A2", "A2", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_A2", "stCd_A2", "A2", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_A2", "postCd_A2", "A2", null, TYPE_HANKAKUEISU, "15", null},
	{"xxAsgTrtyNm_A2", "xxAsgTrtyNm_A2", "A2", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxChkBox_E2", "xxChkBox_E2", "E2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_P2", "xxChkBox_P2", "P2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_D2", "xxChkBox_D2", "D2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_M", "xxChkBox_M", "M", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_AM", "xxChkBox_AM", "AM", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsAcctNum_A3", "dsAcctNum_A3", "A3", null, TYPE_HANKAKUEISU, "20", null},
	{"locNum_A3", "locNum_A3", "A3", null, TYPE_HANKAKUEISU, "30", null},
	{"dsXrefAcctCd_A3", "dsXrefAcctCd_A3", "A3", null, TYPE_HANKAKUEISU, "60", null},
	{"dsAcctTpCd_A3", "dsAcctTpCd_A3", "A3", null, TYPE_HANKAKUEISU, "2", null},
	{"dsAcctTpNm_A3", "dsAcctTpNm_A3", "A3", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A3", "dsAcctNm_A3", "A3", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_A3", "xxAllLineAddr_A3", "A3", null, TYPE_HANKAKUEISU, "400", null},
	{"ctyAddr_A3", "ctyAddr_A3", "A3", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_A3", "stCd_A3", "A3", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_A3", "postCd_A3", "A3", null, TYPE_HANKAKUEISU, "15", null},
	{"xxAsgTrtyNm_A3", "xxAsgTrtyNm_A3", "A3", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxChkBox_E3", "xxChkBox_E3", "E3", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_P3", "xxChkBox_P3", "P3", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_D3", "xxChkBox_D3", "D3", null, TYPE_HANKAKUEIJI, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowId_AT
        {"XX_ROW_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowId_AN
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_M
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A1
        {"DS_XREF_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsXrefAcctCd_A1
        {"DS_ACCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpCd_A1
        {"DS_ACCT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpNm_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_A1
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_A1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_A1
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_A1
        {"XX_ASG_TRTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAsgTrtyNm_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_E1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_P1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A2
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A2
        {"DS_XREF_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsXrefAcctCd_A2
        {"DS_ACCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpCd_A2
        {"DS_ACCT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpNm_A2
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A2
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_A2
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_A2
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_A2
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_A2
        {"XX_ASG_TRTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAsgTrtyNm_A2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_E2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_P2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_M
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AM
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A3
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A3
        {"DS_XREF_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsXrefAcctCd_A3
        {"DS_ACCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpCd_A3
        {"DS_ACCT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpNm_A3
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A3
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_A3
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_A3
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_A3
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_A3
        {"XX_ASG_TRTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAsgTrtyNm_A3
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_E3
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_P3
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D3
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

