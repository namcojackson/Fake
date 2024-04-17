//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161014111822000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7080_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7080_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** MDSE_CD_A*/
	public final EZDCStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDCStringItem              mdseDescShortTxt_A;

    /** SPLY_AGMT_FREQ_TP_CD_A*/
	public final EZDCStringItem              splyAgmtFreqTpCd_A;

    /** SPLY_AGMT_PLN_FIRST_QTY_A*/
	public final EZDCBigDecimalItem              splyAgmtPlnFirstQty_A;

    /** SPLY_AGMT_PLN_QTY_A*/
	public final EZDCBigDecimalItem              splyAgmtPlnQty_A;

    /** SPLY_AGMT_PLN_SQ_NUM_A*/
	public final EZDCBigDecimalItem              splyAgmtPlnSqNum_A;

    /** EFF_FROM_DT_A*/
	public final EZDCDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDCDateItem              effThruDt_A;

    /** XX_DT_10_DT_AC*/
	public final EZDCDateItem              xxDt10Dt_AC;

    /** XX_FULL_NM_AC*/
	public final EZDCStringItem              xxFullNm_AC;

    /** XX_DT_10_DT_AU*/
	public final EZDCDateItem              xxDt10Dt_AU;

    /** XX_FULL_NM_AU*/
	public final EZDCStringItem              xxFullNm_AU;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;

    /** SPLY_AGMT_PLN_DTL_PK_A*/
	public final EZDCBigDecimalItem              splyAgmtPlnDtlPk_A;

    /** DEL_FLG_A*/
	public final EZDCStringItem              delFlg_A;


	/**
	 * NMAL7080_ACMsg is constructor.
	 * The initialization when the instance of NMAL7080_ACMsg is generated.
	 */
	public NMAL7080_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7080_ACMsg is constructor.
	 * The initialization when the instance of NMAL7080_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7080_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		mdseCd_A = (EZDCStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDCStringItem)newItem("mdseDescShortTxt_A");
		splyAgmtFreqTpCd_A = (EZDCStringItem)newItem("splyAgmtFreqTpCd_A");
		splyAgmtPlnFirstQty_A = (EZDCBigDecimalItem)newItem("splyAgmtPlnFirstQty_A");
		splyAgmtPlnQty_A = (EZDCBigDecimalItem)newItem("splyAgmtPlnQty_A");
		splyAgmtPlnSqNum_A = (EZDCBigDecimalItem)newItem("splyAgmtPlnSqNum_A");
		effFromDt_A = (EZDCDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDCDateItem)newItem("effThruDt_A");
		xxDt10Dt_AC = (EZDCDateItem)newItem("xxDt10Dt_AC");
		xxFullNm_AC = (EZDCStringItem)newItem("xxFullNm_AC");
		xxDt10Dt_AU = (EZDCDateItem)newItem("xxDt10Dt_AU");
		xxFullNm_AU = (EZDCStringItem)newItem("xxFullNm_AU");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
		splyAgmtPlnDtlPk_A = (EZDCBigDecimalItem)newItem("splyAgmtPlnDtlPk_A");
		delFlg_A = (EZDCStringItem)newItem("delFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7080_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7080_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"splyAgmtFreqTpCd_A", "splyAgmtFreqTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnFirstQty_A", "splyAgmtPlnFirstQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"splyAgmtPlnQty_A", "splyAgmtPlnQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"splyAgmtPlnSqNum_A", "splyAgmtPlnSqNum_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDt10Dt_AC", "xxDt10Dt_AC", "AC", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFullNm_AC", "xxFullNm_AC", "AC", null, TYPE_HANKAKUEISU, "100", null},
	{"xxDt10Dt_AU", "xxDt10Dt_AU", "AU", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFullNm_AU", "xxFullNm_AU", "AU", null, TYPE_HANKAKUEISU, "100", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"splyAgmtPlnDtlPk_A", "splyAgmtPlnDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"delFlg_A", "delFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"SPLY_AGMT_FREQ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtFreqTpCd_A
        {"SPLY_AGMT_PLN_FIRST_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnFirstQty_A
        {"SPLY_AGMT_PLN_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnQty_A
        {"SPLY_AGMT_PLN_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnSqNum_A
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A
        {"XX_DT_10_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDt10Dt_AC
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_AC
        {"XX_DT_10_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDt10Dt_AU
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_AU
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"SPLY_AGMT_PLN_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnDtlPk_A
        {"DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg_A
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

