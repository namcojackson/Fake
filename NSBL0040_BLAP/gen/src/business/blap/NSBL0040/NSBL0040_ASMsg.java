//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161205153331000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0040_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0040 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0040_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** XX_LINK_PROT_A1*/
	public final EZDSStringItem              xxLinkProt_A1;

    /** BILL_TO_CUST_CD_A1*/
	public final EZDSStringItem              billToCustCd_A1;

    /** LOC_NM_A1*/
	public final EZDSStringItem              locNm_A1;

    /** XX_LINK_PROT_A2*/
	public final EZDSStringItem              xxLinkProt_A2;

    /** XX_EXST_FLG_A1*/
	public final EZDSStringItem              xxExstFlg_A1;

    /** XX_LINK_PROT_A3*/
	public final EZDSStringItem              xxLinkProt_A3;

    /** SVC_TASK_NUM_A1*/
	public final EZDSStringItem              svcTaskNum_A1;

    /** FSR_NUM_A1*/
	public final EZDSStringItem              fsrNum_A1;

    /** XX_LINK_PROT_A4*/
	public final EZDSStringItem              xxLinkProt_A4;

    /** XX_EXST_FLG_A2*/
	public final EZDSStringItem              xxExstFlg_A2;

    /** SVC_BILL_TP_CD_A1*/
	public final EZDSStringItem              svcBillTpCd_A1;

    /** SVC_BILL_TP_NM_A1*/
	public final EZDSStringItem              svcBillTpNm_A1;

    /** PMT_TERM_CASH_DISC_CD_A1*/
	public final EZDSStringItem              pmtTermCashDiscCd_A1;

    /** PMT_TERM_CASH_DISC_NM_A1*/
	public final EZDSStringItem              pmtTermCashDiscNm_A1;

    /** XX_LINK_PROT_A5*/
	public final EZDSStringItem              xxLinkProt_A5;

    /** TECH_CD_A1*/
	public final EZDSStringItem              techCd_A1;

    /** XX_LINK_PROT_A6*/
	public final EZDSStringItem              xxLinkProt_A6;

    /** ORIG_INV_CCY_CD_A1*/
	public final EZDSStringItem              origInvCcyCd_A1;

    /** SVC_LBOR_DEAL_AMT_A1*/
	public final EZDSBigDecimalItem              svcLborDealAmt_A1;

    /** _EZInUserID_A1*/
	public final EZDSStringItem              ezInUserID_A1;

    /** HR_TTL_NM_A1*/
	public final EZDSStringItem              hrTtlNm_A1;

    /** SVC_MACH_MSTR_PK_A1*/
	public final EZDSBigDecimalItem              svcMachMstrPk_A1;

    /** SVC_TASK_RCV_DT_A1*/
	public final EZDSDateItem              svcTaskRcvDt_A1;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDSStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDSStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDSStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDSStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDSStringItem              xxRecHistTblNm_A;


	/**
	 * NSBL0040_ASMsg is constructor.
	 * The initialization when the instance of NSBL0040_ASMsg is generated.
	 */
	public NSBL0040_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0040_ASMsg is constructor.
	 * The initialization when the instance of NSBL0040_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0040_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		xxLinkProt_A1 = (EZDSStringItem)newItem("xxLinkProt_A1");
		billToCustCd_A1 = (EZDSStringItem)newItem("billToCustCd_A1");
		locNm_A1 = (EZDSStringItem)newItem("locNm_A1");
		xxLinkProt_A2 = (EZDSStringItem)newItem("xxLinkProt_A2");
		xxExstFlg_A1 = (EZDSStringItem)newItem("xxExstFlg_A1");
		xxLinkProt_A3 = (EZDSStringItem)newItem("xxLinkProt_A3");
		svcTaskNum_A1 = (EZDSStringItem)newItem("svcTaskNum_A1");
		fsrNum_A1 = (EZDSStringItem)newItem("fsrNum_A1");
		xxLinkProt_A4 = (EZDSStringItem)newItem("xxLinkProt_A4");
		xxExstFlg_A2 = (EZDSStringItem)newItem("xxExstFlg_A2");
		svcBillTpCd_A1 = (EZDSStringItem)newItem("svcBillTpCd_A1");
		svcBillTpNm_A1 = (EZDSStringItem)newItem("svcBillTpNm_A1");
		pmtTermCashDiscCd_A1 = (EZDSStringItem)newItem("pmtTermCashDiscCd_A1");
		pmtTermCashDiscNm_A1 = (EZDSStringItem)newItem("pmtTermCashDiscNm_A1");
		xxLinkProt_A5 = (EZDSStringItem)newItem("xxLinkProt_A5");
		techCd_A1 = (EZDSStringItem)newItem("techCd_A1");
		xxLinkProt_A6 = (EZDSStringItem)newItem("xxLinkProt_A6");
		origInvCcyCd_A1 = (EZDSStringItem)newItem("origInvCcyCd_A1");
		svcLborDealAmt_A1 = (EZDSBigDecimalItem)newItem("svcLborDealAmt_A1");
		ezInUserID_A1 = (EZDSStringItem)newItem("ezInUserID_A1");
		hrTtlNm_A1 = (EZDSStringItem)newItem("hrTtlNm_A1");
		svcMachMstrPk_A1 = (EZDSBigDecimalItem)newItem("svcMachMstrPk_A1");
		svcTaskRcvDt_A1 = (EZDSDateItem)newItem("svcTaskRcvDt_A1");
		xxRecHistCratTs_A = (EZDSStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDSStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDSStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDSStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDSStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0040_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0040_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxLinkProt_A1", "xxLinkProt_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"billToCustCd_A1", "billToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_A1", "locNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxLinkProt_A2", "xxLinkProt_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxExstFlg_A1", "xxExstFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxLinkProt_A3", "xxLinkProt_A3", "A3", null, TYPE_HANKAKUEISU, "1", null},
	{"svcTaskNum_A1", "svcTaskNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"fsrNum_A1", "fsrNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxLinkProt_A4", "xxLinkProt_A4", "A4", null, TYPE_HANKAKUEISU, "1", null},
	{"xxExstFlg_A2", "xxExstFlg_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"svcBillTpCd_A1", "svcBillTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"svcBillTpNm_A1", "svcBillTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"pmtTermCashDiscCd_A1", "pmtTermCashDiscCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"pmtTermCashDiscNm_A1", "pmtTermCashDiscNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxLinkProt_A5", "xxLinkProt_A5", "A5", null, TYPE_HANKAKUEISU, "1", null},
	{"techCd_A1", "techCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxLinkProt_A6", "xxLinkProt_A6", "A6", null, TYPE_HANKAKUEISU, "1", null},
	{"origInvCcyCd_A1", "origInvCcyCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"svcLborDealAmt_A1", "svcLborDealAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ezInUserID_A1", "ezInUserID_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"hrTtlNm_A1", "hrTtlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"svcMachMstrPk_A1", "svcMachMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcTaskRcvDt_A1", "svcTaskRcvDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxRecHistCratTs_A", "xxRecHistCratTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A", "xxRecHistCratByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A", "xxRecHistUpdTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A", "xxRecHistUpdByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A", "xxRecHistTblNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_A1
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_A2
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_A1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_A3
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_A1
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_A1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_A4
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_A2
        {"SVC_BILL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillTpCd_A1
        {"SVC_BILL_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillTpNm_A1
        {"PMT_TERM_CASH_DISC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscCd_A1
        {"PMT_TERM_CASH_DISC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscNm_A1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_A5
        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd_A1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_A6
        {"ORIG_INV_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origInvCcyCd_A1
        {"SVC_LBOR_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLborDealAmt_A1
        {"_EZInUserID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInUserID_A1
        {"HR_TTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrTtlNm_A1
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A1
        {"SVC_TASK_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskRcvDt_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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

