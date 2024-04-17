//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161116111703000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL3000_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL3000;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL3000 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL3000_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_ID_B*/
	public final EZDSStringItem              xxRowId_B;

    /** XX_CHK_BOX_B*/
	public final EZDSStringItem              xxChkBox_B;

    /** DS_ACCT_CUST_NUM_B*/
	public final EZDSStringItem              dsAcctCustNum_B;

    /** DS_ACCT_DLR_CD_B*/
	public final EZDSStringItem              dsAcctDlrCd_B;

    /** DS_ACCT_NM_B*/
	public final EZDSStringItem              dsAcctNm_B;

    /** LOC_NUM_B*/
	public final EZDSStringItem              locNum_B;

    /** FIRST_LINE_ADDR_B*/
	public final EZDSStringItem              firstLineAddr_B;

    /** CTY_ADDR_B*/
	public final EZDSStringItem              ctyAddr_B;

    /** ST_CD_B*/
	public final EZDSStringItem              stCd_B;

    /** POST_CD_B*/
	public final EZDSStringItem              postCd_B;

    /** MKT_MDL_CD_B*/
	public final EZDSStringItem              mktMdlCd_B;

    /** SLS_AUTH_FLG_B*/
	public final EZDSStringItem              slsAuthFlg_B;

    /** SVC_AUTH_FLG_B*/
	public final EZDSStringItem              svcAuthFlg_B;

    /** EFF_FROM_DT_B*/
	public final EZDSDateItem              effFromDt_B;

    /** EFF_THRU_DT_B*/
	public final EZDSDateItem              effThruDt_B;

    /** UPLD_USER_ID_B*/
	public final EZDSStringItem              upldUserId_B;

    /** UPLD_DT_B*/
	public final EZDSDateItem              upldDt_B;

    /** _EZUpdateDateTime_B*/
	public final EZDSStringItem              ezUpTime_B;

    /** _EZUpTimeZone_B*/
	public final EZDSStringItem              ezUpTimeZone_B;


	/**
	 * NMAL3000_BSMsg is constructor.
	 * The initialization when the instance of NMAL3000_BSMsg is generated.
	 */
	public NMAL3000_BSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL3000_BSMsg is constructor.
	 * The initialization when the instance of NMAL3000_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL3000_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowId_B = (EZDSStringItem)newItem("xxRowId_B");
		xxChkBox_B = (EZDSStringItem)newItem("xxChkBox_B");
		dsAcctCustNum_B = (EZDSStringItem)newItem("dsAcctCustNum_B");
		dsAcctDlrCd_B = (EZDSStringItem)newItem("dsAcctDlrCd_B");
		dsAcctNm_B = (EZDSStringItem)newItem("dsAcctNm_B");
		locNum_B = (EZDSStringItem)newItem("locNum_B");
		firstLineAddr_B = (EZDSStringItem)newItem("firstLineAddr_B");
		ctyAddr_B = (EZDSStringItem)newItem("ctyAddr_B");
		stCd_B = (EZDSStringItem)newItem("stCd_B");
		postCd_B = (EZDSStringItem)newItem("postCd_B");
		mktMdlCd_B = (EZDSStringItem)newItem("mktMdlCd_B");
		slsAuthFlg_B = (EZDSStringItem)newItem("slsAuthFlg_B");
		svcAuthFlg_B = (EZDSStringItem)newItem("svcAuthFlg_B");
		effFromDt_B = (EZDSDateItem)newItem("effFromDt_B");
		effThruDt_B = (EZDSDateItem)newItem("effThruDt_B");
		upldUserId_B = (EZDSStringItem)newItem("upldUserId_B");
		upldDt_B = (EZDSDateItem)newItem("upldDt_B");
		ezUpTime_B = (EZDSStringItem)newItem("ezUpTime_B");
		ezUpTimeZone_B = (EZDSStringItem)newItem("ezUpTimeZone_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL3000_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL3000_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowId_B", "xxRowId_B", "B", null, TYPE_HANKAKUEISU, "18", null},
	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsAcctCustNum_B", "dsAcctCustNum_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctDlrCd_B", "dsAcctDlrCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_B", "dsAcctNm_B", "B", null, TYPE_HANKAKUEISU, "360", null},
	{"locNum_B", "locNum_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"firstLineAddr_B", "firstLineAddr_B", "B", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_B", "ctyAddr_B", "B", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_B", "stCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_B", "postCd_B", "B", null, TYPE_HANKAKUEISU, "15", null},
	{"mktMdlCd_B", "mktMdlCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"slsAuthFlg_B", "slsAuthFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"svcAuthFlg_B", "svcAuthFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_B", "effFromDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_B", "effThruDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"upldUserId_B", "upldUserId_B", "B", null, TYPE_HANKAKUEISU, "16", null},
	{"upldDt_B", "upldDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_B", "ezUpTime_B", "B", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B", "ezUpTimeZone_B", "B", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowId_B
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"DS_ACCT_CUST_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctCustNum_B
        {"DS_ACCT_DLR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctDlrCd_B
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_B
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_B
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_B
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_B
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_B
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_B
        {"MKT_MDL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlCd_B
        {"SLS_AUTH_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsAuthFlg_B
        {"SVC_AUTH_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAuthFlg_B
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_B
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_B
        {"UPLD_USER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldUserId_B
        {"UPLD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldDt_B
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B
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
