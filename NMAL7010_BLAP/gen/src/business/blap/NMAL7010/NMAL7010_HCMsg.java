//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20181206112439000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010_HCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7010 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010_HCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_SH*/
	public final EZDCStringItem              xxChkBox_SH;

    /** XX_CHK_BOX_PH*/
	public final EZDCStringItem              xxChkBox_PH;

    /** MDL_ID_PH*/
	public final EZDCBigDecimalItem              mdlId_PH;

    /** MDL_NM_PH*/
	public final EZDCStringItem              mdlNm_PH;

    /** TI_AMT_PH*/
	public final EZDCBigDecimalItem              tiAmt_PH;

    /** MTR_RNG_REQ_FLG_PH*/
	public final EZDCStringItem              mtrRngReqFlg_PH;

    /** FROM_MTR_COPY_VOL_CNT_PH*/
	public final EZDCBigDecimalItem              fromMtrCopyVolCnt_PH;

    /** THRU_MTR_COPY_VOL_CNT_PH*/
	public final EZDCBigDecimalItem              thruMtrCopyVolCnt_PH;

    /** EFF_FROM_DT_PH*/
	public final EZDCDateItem              effFromDt_PH;

    /** EFF_THRU_DT_PH*/
	public final EZDCDateItem              effThruDt_PH;

    /** DEL_FLG_PH*/
	public final EZDCStringItem              delFlg_PH;

    /** PRC_LIST_TI_VAL_PK_PH*/
	public final EZDCBigDecimalItem              prcListTiValPk_PH;

    /** XX_SCR_STS_TXT_H1*/
	public final EZDCStringItem              xxScrStsTxt_H1;

    /** XX_FULL_NM_H1*/
	public final EZDCStringItem              xxFullNm_H1;

    /** CRAT_DT_PH*/
	public final EZDCDateItem              cratDt_PH;

    /** XX_FULL_NM_H2*/
	public final EZDCStringItem              xxFullNm_H2;

    /** LAST_UPD_DT_PH*/
	public final EZDCDateItem              lastUpdDt_PH;

    /** _EZUpdateDateTime_PH*/
	public final EZDCStringItem              ezUpTime_PH;

    /** _EZUpTimeZone_PH*/
	public final EZDCStringItem              ezUpTimeZone_PH;


	/**
	 * NMAL7010_HCMsg is constructor.
	 * The initialization when the instance of NMAL7010_HCMsg is generated.
	 */
	public NMAL7010_HCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7010_HCMsg is constructor.
	 * The initialization when the instance of NMAL7010_HCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7010_HCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_SH = (EZDCStringItem)newItem("xxChkBox_SH");
		xxChkBox_PH = (EZDCStringItem)newItem("xxChkBox_PH");
		mdlId_PH = (EZDCBigDecimalItem)newItem("mdlId_PH");
		mdlNm_PH = (EZDCStringItem)newItem("mdlNm_PH");
		tiAmt_PH = (EZDCBigDecimalItem)newItem("tiAmt_PH");
		mtrRngReqFlg_PH = (EZDCStringItem)newItem("mtrRngReqFlg_PH");
		fromMtrCopyVolCnt_PH = (EZDCBigDecimalItem)newItem("fromMtrCopyVolCnt_PH");
		thruMtrCopyVolCnt_PH = (EZDCBigDecimalItem)newItem("thruMtrCopyVolCnt_PH");
		effFromDt_PH = (EZDCDateItem)newItem("effFromDt_PH");
		effThruDt_PH = (EZDCDateItem)newItem("effThruDt_PH");
		delFlg_PH = (EZDCStringItem)newItem("delFlg_PH");
		prcListTiValPk_PH = (EZDCBigDecimalItem)newItem("prcListTiValPk_PH");
		xxScrStsTxt_H1 = (EZDCStringItem)newItem("xxScrStsTxt_H1");
		xxFullNm_H1 = (EZDCStringItem)newItem("xxFullNm_H1");
		cratDt_PH = (EZDCDateItem)newItem("cratDt_PH");
		xxFullNm_H2 = (EZDCStringItem)newItem("xxFullNm_H2");
		lastUpdDt_PH = (EZDCDateItem)newItem("lastUpdDt_PH");
		ezUpTime_PH = (EZDCStringItem)newItem("ezUpTime_PH");
		ezUpTimeZone_PH = (EZDCStringItem)newItem("ezUpTimeZone_PH");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7010_HCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7010_HCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_SH", "xxChkBox_SH", "SH", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_PH", "xxChkBox_PH", "PH", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdlId_PH", "mdlId_PH", "PH", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlNm_PH", "mdlNm_PH", "PH", null, TYPE_HANKAKUEISU, "50", null},
	{"tiAmt_PH", "tiAmt_PH", "PH", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mtrRngReqFlg_PH", "mtrRngReqFlg_PH", "PH", null, TYPE_HANKAKUEISU, "1", null},
	{"fromMtrCopyVolCnt_PH", "fromMtrCopyVolCnt_PH", "PH", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"thruMtrCopyVolCnt_PH", "thruMtrCopyVolCnt_PH", "PH", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"effFromDt_PH", "effFromDt_PH", "PH", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_PH", "effThruDt_PH", "PH", null, TYPE_NENTSUKIHI, "8", null},
	{"delFlg_PH", "delFlg_PH", "PH", null, TYPE_HANKAKUEISU, "1", null},
	{"prcListTiValPk_PH", "prcListTiValPk_PH", "PH", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxScrStsTxt_H1", "xxScrStsTxt_H1", "H1", null, TYPE_HANKAKUEISU, "256", null},
	{"xxFullNm_H1", "xxFullNm_H1", "H1", null, TYPE_HANKAKUEISU, "100", null},
	{"cratDt_PH", "cratDt_PH", "PH", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFullNm_H2", "xxFullNm_H2", "H2", null, TYPE_HANKAKUEISU, "100", null},
	{"lastUpdDt_PH", "lastUpdDt_PH", "PH", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_PH", "ezUpTime_PH", "PH", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_PH", "ezUpTimeZone_PH", "PH", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SH
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_PH
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_PH
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_PH
        {"TI_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tiAmt_PH
        {"MTR_RNG_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrRngReqFlg_PH
        {"FROM_MTR_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromMtrCopyVolCnt_PH
        {"THRU_MTR_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thruMtrCopyVolCnt_PH
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_PH
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_PH
        {"DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg_PH
        {"PRC_LIST_TI_VAL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTiValPk_PH
        {"XX_SCR_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrStsTxt_H1
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_H1
        {"CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratDt_PH
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_H2
        {"LAST_UPD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastUpdDt_PH
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_PH
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_PH
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

