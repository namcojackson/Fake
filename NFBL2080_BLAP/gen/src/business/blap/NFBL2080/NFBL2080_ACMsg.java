//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200519135115000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2080_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2080_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** LOC_NM_A1*/
	public final EZDCStringItem              locNm_A1;

    /** VND_INV_NUM_A1*/
	public final EZDCStringItem              vndInvNum_A1;

    /** VND_INV_PROC_STS_CD_A1*/
	public final EZDCStringItem              vndInvProcStsCd_A1;

    /** INV_AR_PROC_STS_CD_A1*/
	public final EZDCStringItem              invArProcStsCd_A1;

    /** PO_ORD_NUM_A1*/
	public final EZDCStringItem              poOrdNum_A1;

    /** EDI_PO_ORD_NUM_A1*/
	public final EZDCStringItem              ediPoOrdNum_A1;

    /** XX_CRAT_DT_A1*/
	public final EZDCDateItem              xxCratDt_A1;

    /** INV_TOT_DEAL_NET_AMT_A1*/
	public final EZDCBigDecimalItem              invTotDealNetAmt_A1;

    /** BAT_ERR_MSG_TXT_AP*/
	public final EZDCStringItem              batErrMsgTxt_AP;

    /** BAT_ERR_MSG_TXT_AD*/
	public final EZDCStringItemArray              batErrMsgTxt_AD;

    /** BAT_ERR_MSG_TXT_AC*/
	public final EZDCStringItemArray              batErrMsgTxt_AC;

    /** XX_CHK_BOX_A2*/
	public final EZDCStringItem              xxChkBox_A2;

    /** VND_INV_BOL_LINE_NUM_A1*/
	public final EZDCStringItem              vndInvBolLineNum_A1;


	/**
	 * NFBL2080_ACMsg is constructor.
	 * The initialization when the instance of NFBL2080_ACMsg is generated.
	 */
	public NFBL2080_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2080_ACMsg is constructor.
	 * The initialization when the instance of NFBL2080_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2080_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		locNm_A1 = (EZDCStringItem)newItem("locNm_A1");
		vndInvNum_A1 = (EZDCStringItem)newItem("vndInvNum_A1");
		vndInvProcStsCd_A1 = (EZDCStringItem)newItem("vndInvProcStsCd_A1");
		invArProcStsCd_A1 = (EZDCStringItem)newItem("invArProcStsCd_A1");
		poOrdNum_A1 = (EZDCStringItem)newItem("poOrdNum_A1");
		ediPoOrdNum_A1 = (EZDCStringItem)newItem("ediPoOrdNum_A1");
		xxCratDt_A1 = (EZDCDateItem)newItem("xxCratDt_A1");
		invTotDealNetAmt_A1 = (EZDCBigDecimalItem)newItem("invTotDealNetAmt_A1");
		batErrMsgTxt_AP = (EZDCStringItem)newItem("batErrMsgTxt_AP");
		batErrMsgTxt_AD = (EZDCStringItemArray)newItemArray("batErrMsgTxt_AD");
		batErrMsgTxt_AC = (EZDCStringItemArray)newItemArray("batErrMsgTxt_AC");
		xxChkBox_A2 = (EZDCStringItem)newItem("xxChkBox_A2");
		vndInvBolLineNum_A1 = (EZDCStringItem)newItem("vndInvBolLineNum_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2080_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2080_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"locNm_A1", "locNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"vndInvNum_A1", "vndInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"vndInvProcStsCd_A1", "vndInvProcStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"invArProcStsCd_A1", "invArProcStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"poOrdNum_A1", "poOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"ediPoOrdNum_A1", "ediPoOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"xxCratDt_A1", "xxCratDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"invTotDealNetAmt_A1", "invTotDealNetAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"batErrMsgTxt_AP", "batErrMsgTxt_AP", "AP", null, TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_AD", "batErrMsgTxt_AD", "AD", "99", TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_AC", "batErrMsgTxt_AC", "AC", "99", TYPE_HANKAKUEISU, "400", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"vndInvBolLineNum_A1", "vndInvBolLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A1
        {"VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvNum_A1
        {"VND_INV_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvProcStsCd_A1
        {"INV_AR_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invArProcStsCd_A1
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_A1
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_A1
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_A1
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt_A1
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_AP
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_AD
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_AC
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
        {"VND_INV_BOL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvBolLineNum_A1
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

