//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20221227111437000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8840_DSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8840 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8840_DSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_WF_ASG_CD_D2*/
	public final EZDSStringItem              xxWfAsgCd_D2;

    /** XX_WF_ASG_NM_D2*/
	public final EZDSStringItem              xxWfAsgNm_D2;

    /** XX_WF_ASG_CD_D1*/
	public final EZDSStringItem              xxWfAsgCd_D1;

    /** XX_WF_ASG_NM_D1*/
	public final EZDSStringItem              xxWfAsgNm_D1;

    /** WF_BIZ_APP_ID_D1*/
	public final EZDSStringItem              wfBizAppId_D1;

    /** WF_DESC_TXT_DP*/
	public final EZDSStringItem              wfDescTxt_DP;

    /** EFF_FROM_DT_D1*/
	public final EZDSDateItem              effFromDt_D1;

    /** EFF_FROM_HOUR_MN_DH*/
	public final EZDSStringItem              effFromHourMn_DH;

    /** EFF_FROM_HOUR_MN_DM*/
	public final EZDSStringItem              effFromHourMn_DM;

    /** WF_DESC_TXT_DF*/
	public final EZDSStringItem              wfDescTxt_DF;

    /** EFF_THRU_DT_D1*/
	public final EZDSDateItem              effThruDt_D1;

    /** EFF_THRU_HOUR_MN_DH*/
	public final EZDSStringItem              effThruHourMn_DH;

    /** EFF_THRU_HOUR_MN_DM*/
	public final EZDSStringItem              effThruHourMn_DM;

    /** WF_DESC_TXT_DT*/
	public final EZDSStringItem              wfDescTxt_DT;

    /** WF_DLGT_USR_PK_D1*/
	public final EZDSBigDecimalItem              wfDlgtUsrPk_D1;

    /** WF_DESC_TXT_D1*/
	public final EZDSStringItem              wfDescTxt_D1;

    /** _EZUpdateDateTime_D1*/
	public final EZDSStringItem              ezUpTime_D1;


	/**
	 * NYEL8840_DSMsg is constructor.
	 * The initialization when the instance of NYEL8840_DSMsg is generated.
	 */
	public NYEL8840_DSMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8840_DSMsg is constructor.
	 * The initialization when the instance of NYEL8840_DSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8840_DSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxWfAsgCd_D2 = (EZDSStringItem)newItem("xxWfAsgCd_D2");
		xxWfAsgNm_D2 = (EZDSStringItem)newItem("xxWfAsgNm_D2");
		xxWfAsgCd_D1 = (EZDSStringItem)newItem("xxWfAsgCd_D1");
		xxWfAsgNm_D1 = (EZDSStringItem)newItem("xxWfAsgNm_D1");
		wfBizAppId_D1 = (EZDSStringItem)newItem("wfBizAppId_D1");
		wfDescTxt_DP = (EZDSStringItem)newItem("wfDescTxt_DP");
		effFromDt_D1 = (EZDSDateItem)newItem("effFromDt_D1");
		effFromHourMn_DH = (EZDSStringItem)newItem("effFromHourMn_DH");
		effFromHourMn_DM = (EZDSStringItem)newItem("effFromHourMn_DM");
		wfDescTxt_DF = (EZDSStringItem)newItem("wfDescTxt_DF");
		effThruDt_D1 = (EZDSDateItem)newItem("effThruDt_D1");
		effThruHourMn_DH = (EZDSStringItem)newItem("effThruHourMn_DH");
		effThruHourMn_DM = (EZDSStringItem)newItem("effThruHourMn_DM");
		wfDescTxt_DT = (EZDSStringItem)newItem("wfDescTxt_DT");
		wfDlgtUsrPk_D1 = (EZDSBigDecimalItem)newItem("wfDlgtUsrPk_D1");
		wfDescTxt_D1 = (EZDSStringItem)newItem("wfDescTxt_D1");
		ezUpTime_D1 = (EZDSStringItem)newItem("ezUpTime_D1");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8840_DSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8840_DSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxWfAsgCd_D2", "xxWfAsgCd_D2", "D2", null, TYPE_HANKAKUEISU, "32", null},
	{"xxWfAsgNm_D2", "xxWfAsgNm_D2", "D2", null, TYPE_HANKAKUEISU, "255", null},
	{"xxWfAsgCd_D1", "xxWfAsgCd_D1", "D1", null, TYPE_HANKAKUEISU, "32", null},
	{"xxWfAsgNm_D1", "xxWfAsgNm_D1", "D1", null, TYPE_HANKAKUEISU, "255", null},
	{"wfBizAppId_D1", "wfBizAppId_D1", "D1", null, TYPE_HANKAKUEISU, "16", null},
	{"wfDescTxt_DP", "wfDescTxt_DP", "DP", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_D1", "effFromDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromHourMn_DH", "effFromHourMn_DH", "DH", null, TYPE_HANKAKUSUJI, "4", null},
	{"effFromHourMn_DM", "effFromHourMn_DM", "DM", null, TYPE_HANKAKUSUJI, "4", null},
	{"wfDescTxt_DF", "wfDescTxt_DF", "DF", null, TYPE_HANKAKUEISU, "50", null},
	{"effThruDt_D1", "effThruDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruHourMn_DH", "effThruHourMn_DH", "DH", null, TYPE_HANKAKUSUJI, "4", null},
	{"effThruHourMn_DM", "effThruHourMn_DM", "DM", null, TYPE_HANKAKUSUJI, "4", null},
	{"wfDescTxt_DT", "wfDescTxt_DT", "DT", null, TYPE_HANKAKUEISU, "50", null},
	{"wfDlgtUsrPk_D1", "wfDlgtUsrPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wfDescTxt_D1", "wfDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"ezUpTime_D1", "ezUpTime_D1", "D1", null, TYPE_HANKAKUEISU, "17", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_WF_ASG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgCd_D2
        {"XX_WF_ASG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgNm_D2
        {"XX_WF_ASG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgCd_D1
        {"XX_WF_ASG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgNm_D1
        {"WF_BIZ_APP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAppId_D1
        {"WF_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_DP
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_D1
        {"EFF_FROM_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromHourMn_DH
        {"EFF_FROM_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromHourMn_DM
        {"WF_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_DF
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_D1
        {"EFF_THRU_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruHourMn_DH
        {"EFF_THRU_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruHourMn_DM
        {"WF_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_DT
        {"WF_DLGT_USR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDlgtUsrPk_D1
        {"WF_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_D1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D1
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

