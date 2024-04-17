//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20191002110339000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0050_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWCL0050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWCL0050 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWCL0050_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** INV_PROC_TP_CD_A*/
	public final EZDBStringItem              invProcTpCd_A;

    /** XX_URN_NUM_A*/
	public final EZDBStringItem              xxUrnNum_A;

    /** INV_FILE_URL_AU*/
	public final EZDBStringItem              invFileUrl_AU;

    /** INV_FILE_URL_DF*/
	public final EZDBStringItem              invFileUrl_DF;

    /** XX_SRV_URL_TXT_A*/
	public final EZDBStringItem              xxSrvUrlTxt_A;

    /** XX_INV_PATH_TXT_A*/
	public final EZDBStringItem              xxInvPathTxt_A;

    /** CONSL_BILL_NUM_A*/
	public final EZDBStringItem              conslBillNum_A;

    /** INV_FILE_URL_AB*/
	public final EZDBStringItem              invFileUrl_AB;

    /** INV_NUM_A*/
	public final EZDBStringItem              invNum_A;

    /** INV_FILE_URL_AI*/
	public final EZDBStringItem              invFileUrl_AI;

    /** BILL_TO_DS_ACCT_NUM_A*/
	public final EZDBStringItem              billToDsAcctNum_A;

    /** BILL_TO_DS_ACCT_NM_A*/
	public final EZDBStringItem              billToDsAcctNm_A;

    /** CONSL_BILL_INV_DT_A*/
	public final EZDBDateItem              conslBillInvDt_A;

    /** XX_DT_TM_A*/
	public final EZDBStringItem              xxDtTm_A;

    /** CONSL_BILL_TOT_AMT_A*/
	public final EZDBBigDecimalItem              conslBillTotAmt_A;

    /** ORIG_CONSL_BILL_NUM_A*/
	public final EZDBStringItem              origConslBillNum_A;

    /** INV_EML_ADDR_A*/
	public final EZDBStringItem              invEmlAddr_A;

    /** INV_PRT_CTRL_REC_CD_A*/
	public final EZDBStringItem              invPrtCtrlRecCd_A;

    /** INV_FILE_URL_A*/
	public final EZDBStringItem              invFileUrl_A;

    /** XX_INV_FILE_NM_A*/
	public final EZDBStringItem              xxInvFileNm_A;

    /** INV_PRT_CTRL_PK_A*/
	public final EZDBBigDecimalItem              invPrtCtrlPk_A;

    /** EIP_RPT_RQST_PK_A*/
	public final EZDBBigDecimalItem              eipRptRqstPk_A;

    /** EIP_RPT_RQST_PK_BL*/
	public final EZDBBigDecimalItem              eipRptRqstPk_BL;

    /** CUST_ISS_PO_NUM_A*/
	public final EZDBStringItem              custIssPoNum_A;


	/**
	 * NWCL0050_ABMsg is constructor.
	 * The initialization when the instance of NWCL0050_ABMsg is generated.
	 */
	public NWCL0050_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWCL0050_ABMsg is constructor.
	 * The initialization when the instance of NWCL0050_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWCL0050_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		invProcTpCd_A = (EZDBStringItem)newItem("invProcTpCd_A");
		xxUrnNum_A = (EZDBStringItem)newItem("xxUrnNum_A");
		invFileUrl_AU = (EZDBStringItem)newItem("invFileUrl_AU");
		invFileUrl_DF = (EZDBStringItem)newItem("invFileUrl_DF");
		xxSrvUrlTxt_A = (EZDBStringItem)newItem("xxSrvUrlTxt_A");
		xxInvPathTxt_A = (EZDBStringItem)newItem("xxInvPathTxt_A");
		conslBillNum_A = (EZDBStringItem)newItem("conslBillNum_A");
		invFileUrl_AB = (EZDBStringItem)newItem("invFileUrl_AB");
		invNum_A = (EZDBStringItem)newItem("invNum_A");
		invFileUrl_AI = (EZDBStringItem)newItem("invFileUrl_AI");
		billToDsAcctNum_A = (EZDBStringItem)newItem("billToDsAcctNum_A");
		billToDsAcctNm_A = (EZDBStringItem)newItem("billToDsAcctNm_A");
		conslBillInvDt_A = (EZDBDateItem)newItem("conslBillInvDt_A");
		xxDtTm_A = (EZDBStringItem)newItem("xxDtTm_A");
		conslBillTotAmt_A = (EZDBBigDecimalItem)newItem("conslBillTotAmt_A");
		origConslBillNum_A = (EZDBStringItem)newItem("origConslBillNum_A");
		invEmlAddr_A = (EZDBStringItem)newItem("invEmlAddr_A");
		invPrtCtrlRecCd_A = (EZDBStringItem)newItem("invPrtCtrlRecCd_A");
		invFileUrl_A = (EZDBStringItem)newItem("invFileUrl_A");
		xxInvFileNm_A = (EZDBStringItem)newItem("xxInvFileNm_A");
		invPrtCtrlPk_A = (EZDBBigDecimalItem)newItem("invPrtCtrlPk_A");
		eipRptRqstPk_A = (EZDBBigDecimalItem)newItem("eipRptRqstPk_A");
		eipRptRqstPk_BL = (EZDBBigDecimalItem)newItem("eipRptRqstPk_BL");
		custIssPoNum_A = (EZDBStringItem)newItem("custIssPoNum_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWCL0050_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWCL0050_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"invProcTpCd_A", "invProcTpCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"xxUrnNum_A", "xxUrnNum_A", "A", null, TYPE_HANKAKUEISU, "200", null},
	{"invFileUrl_AU", "invFileUrl_AU", "AU", null, TYPE_HANKAKUEISU, "4000", null},
	{"invFileUrl_DF", "invFileUrl_DF", "DF", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxSrvUrlTxt_A", "xxSrvUrlTxt_A", "A", null, TYPE_HANKAKUEISU, "2000", null},
	{"xxInvPathTxt_A", "xxInvPathTxt_A", "A", null, TYPE_HANKAKUEISU, "2000", null},
	{"conslBillNum_A", "conslBillNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"invFileUrl_AB", "invFileUrl_AB", "AB", null, TYPE_HANKAKUEISU, "4000", null},
	{"invNum_A", "invNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"invFileUrl_AI", "invFileUrl_AI", "AI", null, TYPE_HANKAKUEISU, "4000", null},
	{"billToDsAcctNum_A", "billToDsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"billToDsAcctNm_A", "billToDsAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"conslBillInvDt_A", "conslBillInvDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDtTm_A", "xxDtTm_A", "A", null, TYPE_HANKAKUEISU, "23", null},
	{"conslBillTotAmt_A", "conslBillTotAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"origConslBillNum_A", "origConslBillNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"invEmlAddr_A", "invEmlAddr_A", "A", null, TYPE_HANKAKUEISU, "320", null},
	{"invPrtCtrlRecCd_A", "invPrtCtrlRecCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"invFileUrl_A", "invFileUrl_A", "A", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxInvFileNm_A", "xxInvFileNm_A", "A", null, TYPE_HANKAKUEISU, "300", null},
	{"invPrtCtrlPk_A", "invPrtCtrlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"eipRptRqstPk_A", "eipRptRqstPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"eipRptRqstPk_BL", "eipRptRqstPk_BL", "BL", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"custIssPoNum_A", "custIssPoNum_A", "A", null, TYPE_HANKAKUEISU, "35", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"INV_PROC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invProcTpCd_A
        {"XX_URN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUrnNum_A
        {"INV_FILE_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFileUrl_AU
        {"INV_FILE_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFileUrl_DF
        {"XX_SRV_URL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSrvUrlTxt_A
        {"XX_INV_PATH_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInvPathTxt_A
        {"CONSL_BILL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillNum_A
        {"INV_FILE_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFileUrl_AB
        {"INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A
        {"INV_FILE_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFileUrl_AI
        {"BILL_TO_DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNum_A
        {"BILL_TO_DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNm_A
        {"CONSL_BILL_INV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//conslBillInvDt_A
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A
        {"CONSL_BILL_TOT_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillTotAmt_A
        {"ORIG_CONSL_BILL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origConslBillNum_A
        {"INV_EML_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invEmlAddr_A
        {"INV_PRT_CTRL_REC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtCtrlRecCd_A
        {"INV_FILE_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFileUrl_A
        {"XX_INV_FILE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInvFileNm_A
        {"INV_PRT_CTRL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtCtrlPk_A
        {"EIP_RPT_RQST_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_A
        {"EIP_RPT_RQST_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_BL
        {"CUST_ISS_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_A
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
