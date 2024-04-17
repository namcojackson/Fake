//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190123180316000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC043001_taskListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC043001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC043001_taskListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_TASK_NUM*/
	public final EZDPStringItem              svcTaskNum;

    /** SVC_CALL_PRTY_CD*/
	public final EZDPStringItem              svcCallPrtyCd;

    /** TECH_CD*/
	public final EZDPStringItem              techCd;

    /** SCHD_DISPT_EML_FLG*/
	public final EZDPStringItem              schdDisptEmlFlg;

    /** TECH_SCHD_FROM_DT*/
	public final EZDPDateItem              techSchdFromDt;

    /** TECH_SCHD_FROM_TM*/
	public final EZDPStringItem              techSchdFromTm;

    /** TECH_SCHD_TO_DT*/
	public final EZDPDateItem              techSchdToDt;

    /** TECH_SCHD_TO_TM*/
	public final EZDPStringItem              techSchdToTm;

    /** TECH_SCHD_TZ*/
	public final EZDPStringItem              techSchdTz;

    /** TECH_ACPT_DT*/
	public final EZDPDateItem              techAcptDt;

    /** TECH_ACPT_TM*/
	public final EZDPStringItem              techAcptTm;

    /** FSR_VISIT_DISPT_DT*/
	public final EZDPDateItem              fsrVisitDisptDt;

    /** FSR_VISIT_DISPT_TM*/
	public final EZDPStringItem              fsrVisitDisptTm;

    /** ERL_START_TS*/
	public final EZDPStringItem              erlStartTs;

    /** LATE_START_TS*/
	public final EZDPStringItem              lateStartTs;

    /** SVC_RG_CD*/
	public final EZDPStringItem              svcRgCd;

    /** SVC_BR_CD*/
	public final EZDPStringItem              svcBrCd;

    /** SVC_TEAM_TXT*/
	public final EZDPStringItem              svcTeamTxt;

    /** SVC_BR_MGR_PSN_CD*/
	public final EZDPStringItem              svcBrMgrPsnCd;

    /** SVC_TRTY_MGR_PSN_CD*/
	public final EZDPStringItem              svcTrtyMgrPsnCd;

    /** SVC_TEAM_MGR_PSN_CD*/
	public final EZDPStringItem              svcTeamMgrPsnCd;

    /** SVC_ZN_CD*/
	public final EZDPStringItem              svcZnCd;

    /** SVC_BILL_CHNG_RSN_CD*/
	public final EZDPStringItem              svcBillChngRsnCd;

    /** MBL_INTFC_FLG*/
	public final EZDPStringItem              mblIntfcFlg;

    /** SVC_LTTD_NUM*/
	public final EZDPStringItem              svcLttdNum;

    /** SVC_LGTD_NUM*/
	public final EZDPStringItem              svcLgtdNum;

    /** SVC_UN_ASG_RSN_TXT*/
	public final EZDPStringItem              svcUnAsgRsnTxt;

    /** SVC_ASG_TP_CD*/
	public final EZDPStringItem              svcAsgTpCd;

    /** FUT_SVC_DT*/
	public final EZDPDateItem              futSvcDt;

    /** FUT_SVC_TM*/
	public final EZDPStringItem              futSvcTm;

    /** SVC_CUST_CLLR_TEL_NUM*/
	public final EZDPStringItem              svcCustCllrTelNum;

    /** SVC_CUST_CLLR_TEL_EXTN_NUM*/
	public final EZDPStringItem              svcCustCllrTelExtnNum;

    /** CUST_AWARE_CHRG_FLG*/
	public final EZDPStringItem              custAwareChrgFlg;

    /** XX_ORIG_FOLL_UP_TASK_FLG*/
	public final EZDPStringItem              xxOrigFollUpTaskFlg;

    /** CELL_PHO_NUM*/
	public final EZDPStringItem              cellPhoNum;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;

    /** OVRD_FLG*/
	public final EZDPStringItem              ovrdFlg;

    /** TECH_ACPT_FLG*/
	public final EZDPStringItem              techAcptFlg;

    /** XX_MSG_TXT*/
	public final EZDPStringItem              xxMsgTxt;


	/**
	 * NSZC043001_taskListPMsg is constructor.
	 * The initialization when the instance of NSZC043001_taskListPMsg is generated.
	 */
	public NSZC043001_taskListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC043001_taskListPMsg is constructor.
	 * The initialization when the instance of NSZC043001_taskListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC043001_taskListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcTaskNum = (EZDPStringItem)newItem("svcTaskNum");
		svcCallPrtyCd = (EZDPStringItem)newItem("svcCallPrtyCd");
		techCd = (EZDPStringItem)newItem("techCd");
		schdDisptEmlFlg = (EZDPStringItem)newItem("schdDisptEmlFlg");
		techSchdFromDt = (EZDPDateItem)newItem("techSchdFromDt");
		techSchdFromTm = (EZDPStringItem)newItem("techSchdFromTm");
		techSchdToDt = (EZDPDateItem)newItem("techSchdToDt");
		techSchdToTm = (EZDPStringItem)newItem("techSchdToTm");
		techSchdTz = (EZDPStringItem)newItem("techSchdTz");
		techAcptDt = (EZDPDateItem)newItem("techAcptDt");
		techAcptTm = (EZDPStringItem)newItem("techAcptTm");
		fsrVisitDisptDt = (EZDPDateItem)newItem("fsrVisitDisptDt");
		fsrVisitDisptTm = (EZDPStringItem)newItem("fsrVisitDisptTm");
		erlStartTs = (EZDPStringItem)newItem("erlStartTs");
		lateStartTs = (EZDPStringItem)newItem("lateStartTs");
		svcRgCd = (EZDPStringItem)newItem("svcRgCd");
		svcBrCd = (EZDPStringItem)newItem("svcBrCd");
		svcTeamTxt = (EZDPStringItem)newItem("svcTeamTxt");
		svcBrMgrPsnCd = (EZDPStringItem)newItem("svcBrMgrPsnCd");
		svcTrtyMgrPsnCd = (EZDPStringItem)newItem("svcTrtyMgrPsnCd");
		svcTeamMgrPsnCd = (EZDPStringItem)newItem("svcTeamMgrPsnCd");
		svcZnCd = (EZDPStringItem)newItem("svcZnCd");
		svcBillChngRsnCd = (EZDPStringItem)newItem("svcBillChngRsnCd");
		mblIntfcFlg = (EZDPStringItem)newItem("mblIntfcFlg");
		svcLttdNum = (EZDPStringItem)newItem("svcLttdNum");
		svcLgtdNum = (EZDPStringItem)newItem("svcLgtdNum");
		svcUnAsgRsnTxt = (EZDPStringItem)newItem("svcUnAsgRsnTxt");
		svcAsgTpCd = (EZDPStringItem)newItem("svcAsgTpCd");
		futSvcDt = (EZDPDateItem)newItem("futSvcDt");
		futSvcTm = (EZDPStringItem)newItem("futSvcTm");
		svcCustCllrTelNum = (EZDPStringItem)newItem("svcCustCllrTelNum");
		svcCustCllrTelExtnNum = (EZDPStringItem)newItem("svcCustCllrTelExtnNum");
		custAwareChrgFlg = (EZDPStringItem)newItem("custAwareChrgFlg");
		xxOrigFollUpTaskFlg = (EZDPStringItem)newItem("xxOrigFollUpTaskFlg");
		cellPhoNum = (EZDPStringItem)newItem("cellPhoNum");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
		ovrdFlg = (EZDPStringItem)newItem("ovrdFlg");
		techAcptFlg = (EZDPStringItem)newItem("techAcptFlg");
		xxMsgTxt = (EZDPStringItem)newItem("xxMsgTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC043001_taskListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC043001_taskListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcTaskNum", "svcTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcCallPrtyCd", "svcCallPrtyCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"techCd", "techCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"schdDisptEmlFlg", "schdDisptEmlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"techSchdFromDt", "techSchdFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"techSchdFromTm", "techSchdFromTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"techSchdToDt", "techSchdToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"techSchdToTm", "techSchdToTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"techSchdTz", "techSchdTz", null, null, TYPE_HANKAKUEISU, "5", null},
	{"techAcptDt", "techAcptDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"techAcptTm", "techAcptTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"fsrVisitDisptDt", "fsrVisitDisptDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"fsrVisitDisptTm", "fsrVisitDisptTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"erlStartTs", "erlStartTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"lateStartTs", "lateStartTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"svcRgCd", "svcRgCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"svcBrCd", "svcBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcTeamTxt", "svcTeamTxt", null, null, TYPE_HANKAKUEISU, "64", null},
	{"svcBrMgrPsnCd", "svcBrMgrPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"svcTrtyMgrPsnCd", "svcTrtyMgrPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"svcTeamMgrPsnCd", "svcTeamMgrPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"svcZnCd", "svcZnCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcBillChngRsnCd", "svcBillChngRsnCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mblIntfcFlg", "mblIntfcFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"svcLttdNum", "svcLttdNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcLgtdNum", "svcLgtdNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcUnAsgRsnTxt", "svcUnAsgRsnTxt", null, null, TYPE_HANKAKUEISU, "80", null},
	{"svcAsgTpCd", "svcAsgTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"futSvcDt", "futSvcDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"futSvcTm", "futSvcTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"svcCustCllrTelNum", "svcCustCllrTelNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"svcCustCllrTelExtnNum", "svcCustCllrTelExtnNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"custAwareChrgFlg", "custAwareChrgFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxOrigFollUpTaskFlg", "xxOrigFollUpTaskFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"cellPhoNum", "cellPhoNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	{"ovrdFlg", "ovrdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"techAcptFlg", "techAcptFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgTxt", "xxMsgTxt", null, null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum
        {"SVC_CALL_PRTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCallPrtyCd
        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd
        {"SCHD_DISPT_EML_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDisptEmlFlg
        {"TECH_SCHD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techSchdFromDt
        {"TECH_SCHD_FROM_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techSchdFromTm
        {"TECH_SCHD_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techSchdToDt
        {"TECH_SCHD_TO_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techSchdToTm
        {"TECH_SCHD_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techSchdTz
        {"TECH_ACPT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techAcptDt
        {"TECH_ACPT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techAcptTm
        {"FSR_VISIT_DISPT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitDisptDt
        {"FSR_VISIT_DISPT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitDisptTm
        {"ERL_START_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//erlStartTs
        {"LATE_START_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lateStartTs
        {"SVC_RG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgCd
        {"SVC_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBrCd
        {"SVC_TEAM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTeamTxt
        {"SVC_BR_MGR_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBrMgrPsnCd
        {"SVC_TRTY_MGR_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTrtyMgrPsnCd
        {"SVC_TEAM_MGR_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTeamMgrPsnCd
        {"SVC_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcZnCd
        {"SVC_BILL_CHNG_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillChngRsnCd
        {"MBL_INTFC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mblIntfcFlg
        {"SVC_LTTD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLttdNum
        {"SVC_LGTD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLgtdNum
        {"SVC_UN_ASG_RSN_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcUnAsgRsnTxt
        {"SVC_ASG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAsgTpCd
        {"FUT_SVC_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//futSvcDt
        {"FUT_SVC_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//futSvcTm
        {"SVC_CUST_CLLR_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCustCllrTelNum
        {"SVC_CUST_CLLR_TEL_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCustCllrTelExtnNum
        {"CUST_AWARE_CHRG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custAwareChrgFlg
        {"XX_ORIG_FOLL_UP_TASK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrigFollUpTaskFlg
        {"CELL_PHO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cellPhoNum
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
        {"OVRD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ovrdFlg
        {"TECH_ACPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techAcptFlg
        {"XX_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgTxt
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

