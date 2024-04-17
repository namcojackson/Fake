//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160721161155000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1340CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1340;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1340 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1340CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_CATG_CD*/
	public final EZDCStringItem              prcCatgCd;

    /** MDL_ID*/
	public final EZDCBigDecimalItem              mdlId;

    /** MDL_NM*/
	public final EZDCStringItem              mdlNm;

    /** PRC_MTR_PKG_PK*/
	public final EZDCBigDecimalItem              prcMtrPkgPk;

    /** PRC_SVC_PLN_TP_CD*/
	public final EZDCStringItem              prcSvcPlnTpCd;

    /** PRC_SVC_CONTR_TP_CD*/
	public final EZDCStringItem              prcSvcContrTpCd;

    /** BLLG_MTR_LB_CD*/
	public final EZDCStringItem              bllgMtrLbCd;

    /** PRC_BASE_DT*/
	public final EZDCDateItem              prcBaseDt;

    /** FROM_PER_MTH_NUM_I*/
	public final EZDCBigDecimalItem              fromPerMthNum_I;

    /** TO_PER_MTH_NUM_I*/
	public final EZDCBigDecimalItem              toPerMthNum_I;

    /** PRC_LIST_BAND_DESC_TXT_I*/
	public final EZDCStringItem              prcListBandDescTxt_I;

    /** PRC_CATG_NM*/
	public final EZDCStringItem              prcCatgNm;

    /** PRC_MTR_PKG_NM*/
	public final EZDCStringItem              prcMtrPkgNm;

    /** MTR_LB_NM*/
	public final EZDCStringItem              mtrLbNm;

    /** XX_RADIO_BTN*/
	public final EZDCBigDecimalItem              xxRadioBtn;

    /** A*/
	public final business.blap.NSAL1340.NSAL1340_ACMsgArray              A;


	/**
	 * NSAL1340CMsg is constructor.
	 * The initialization when the instance of NSAL1340CMsg is generated.
	 */
	public NSAL1340CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1340CMsg is constructor.
	 * The initialization when the instance of NSAL1340CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1340CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcCatgCd = (EZDCStringItem)newItem("prcCatgCd");
		mdlId = (EZDCBigDecimalItem)newItem("mdlId");
		mdlNm = (EZDCStringItem)newItem("mdlNm");
		prcMtrPkgPk = (EZDCBigDecimalItem)newItem("prcMtrPkgPk");
		prcSvcPlnTpCd = (EZDCStringItem)newItem("prcSvcPlnTpCd");
		prcSvcContrTpCd = (EZDCStringItem)newItem("prcSvcContrTpCd");
		bllgMtrLbCd = (EZDCStringItem)newItem("bllgMtrLbCd");
		prcBaseDt = (EZDCDateItem)newItem("prcBaseDt");
		fromPerMthNum_I = (EZDCBigDecimalItem)newItem("fromPerMthNum_I");
		toPerMthNum_I = (EZDCBigDecimalItem)newItem("toPerMthNum_I");
		prcListBandDescTxt_I = (EZDCStringItem)newItem("prcListBandDescTxt_I");
		prcCatgNm = (EZDCStringItem)newItem("prcCatgNm");
		prcMtrPkgNm = (EZDCStringItem)newItem("prcMtrPkgNm");
		mtrLbNm = (EZDCStringItem)newItem("mtrLbNm");
		xxRadioBtn = (EZDCBigDecimalItem)newItem("xxRadioBtn");
		A = (business.blap.NSAL1340.NSAL1340_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1340CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1340CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcCatgCd", "prcCatgCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"mdlId", "mdlId", null, null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlNm", "mdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcMtrPkgPk", "prcMtrPkgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcSvcPlnTpCd", "prcSvcPlnTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcContrTpCd", "prcSvcContrTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"bllgMtrLbCd", "bllgMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcBaseDt", "prcBaseDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"fromPerMthNum_I", "fromPerMthNum_I", "I", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"toPerMthNum_I", "toPerMthNum_I", "I", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prcListBandDescTxt_I", "prcListBandDescTxt_I", "I", null, TYPE_HANKAKUEISU, "50", null},
	{"prcCatgNm", "prcCatgNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"prcMtrPkgNm", "prcMtrPkgNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mtrLbNm", "mtrLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "200", "business.blap.NSAL1340.NSAL1340_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk
        {"PRC_SVC_PLN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcPlnTpCd
        {"PRC_SVC_CONTR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcContrTpCd
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
        {"PRC_BASE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBaseDt
        {"FROM_PER_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromPerMthNum_I
        {"TO_PER_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toPerMthNum_I
        {"PRC_LIST_BAND_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandDescTxt_I
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm
        {"PRC_MTR_PKG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgNm
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
		null,	//A
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

