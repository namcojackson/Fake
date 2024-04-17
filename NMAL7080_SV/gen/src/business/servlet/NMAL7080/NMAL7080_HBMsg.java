//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160317151811000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7080_HBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7080;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDMsgArray;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7080 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7080_HBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SPLY_AGMT_PLN_PK_H*/
	public final EZDBBigDecimalItem              splyAgmtPlnPk_H;

    /** SPLY_AGMT_PLN_NM_H*/
	public final EZDBStringItem              splyAgmtPlnNm_H;

    /** SPLY_AGMT_PLN_SHORT_NM_H*/
	public final EZDBStringItem              splyAgmtPlnShortNm_H;

    /** SPLY_AGMT_PLN_DESC_TXT_H*/
	public final EZDBStringItem              splyAgmtPlnDescTxt_H;

    /** SPLY_AGMT_PLN_TP_CD_H*/
	public final EZDBStringItem              splyAgmtPlnTpCd_H;

    /** SPLY_AGMT_DOC_TP_CD_H*/
	public final EZDBStringItem              splyAgmtDocTpCd_H;

    /** EFF_FROM_DT_H*/
	public final EZDBDateItem              effFromDt_H;

    /** EFF_THRU_DT_H*/
	public final EZDBDateItem              effThruDt_H;

    /** ANN_TERM_CAP_NUM_H*/
	public final EZDBBigDecimalItem              annTermCapNum_H;

    /** XX_CHK_BOX_H*/
	public final EZDBStringItem              xxChkBox_H;

    /** SPLY_AGMT_PLN_STS_NM_H*/
	public final EZDBStringItem              splyAgmtPlnStsNm_H;

    /** _EZUpdateDateTime_H*/
	public final EZDBStringItem              ezUpTime_H;

    /** _EZUpTimeZone_H*/
	public final EZDBStringItem              ezUpTimeZone_H;


	/**
	 * NMAL7080_HBMsg is constructor.
	 * The initialization when the instance of NMAL7080_HBMsg is generated.
	 */
	public NMAL7080_HBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7080_HBMsg is constructor.
	 * The initialization when the instance of NMAL7080_HBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7080_HBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		splyAgmtPlnPk_H = (EZDBBigDecimalItem)newItem("splyAgmtPlnPk_H");
		splyAgmtPlnNm_H = (EZDBStringItem)newItem("splyAgmtPlnNm_H");
		splyAgmtPlnShortNm_H = (EZDBStringItem)newItem("splyAgmtPlnShortNm_H");
		splyAgmtPlnDescTxt_H = (EZDBStringItem)newItem("splyAgmtPlnDescTxt_H");
		splyAgmtPlnTpCd_H = (EZDBStringItem)newItem("splyAgmtPlnTpCd_H");
		splyAgmtDocTpCd_H = (EZDBStringItem)newItem("splyAgmtDocTpCd_H");
		effFromDt_H = (EZDBDateItem)newItem("effFromDt_H");
		effThruDt_H = (EZDBDateItem)newItem("effThruDt_H");
		annTermCapNum_H = (EZDBBigDecimalItem)newItem("annTermCapNum_H");
		xxChkBox_H = (EZDBStringItem)newItem("xxChkBox_H");
		splyAgmtPlnStsNm_H = (EZDBStringItem)newItem("splyAgmtPlnStsNm_H");
		ezUpTime_H = (EZDBStringItem)newItem("ezUpTime_H");
		ezUpTimeZone_H = (EZDBStringItem)newItem("ezUpTimeZone_H");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7080_HBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7080_HBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"splyAgmtPlnPk_H", "splyAgmtPlnPk_H", "H", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"splyAgmtPlnNm_H", "splyAgmtPlnNm_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnShortNm_H", "splyAgmtPlnShortNm_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnDescTxt_H", "splyAgmtPlnDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnTpCd_H", "splyAgmtPlnTpCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtDocTpCd_H", "splyAgmtDocTpCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"effFromDt_H", "effFromDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H", "effThruDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"annTermCapNum_H", "annTermCapNum_H", "H", null, TYPE_SEISU_SYOSU, "9", "0"},
	{"xxChkBox_H", "xxChkBox_H", "H", null, TYPE_HANKAKUEIJI, "1", null},
	{"splyAgmtPlnStsNm_H", "splyAgmtPlnStsNm_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"ezUpTime_H", "ezUpTime_H", "H", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H", "ezUpTimeZone_H", "H", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SPLY_AGMT_PLN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnPk_H
        {"SPLY_AGMT_PLN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnNm_H
        {"SPLY_AGMT_PLN_SHORT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnShortNm_H
        {"SPLY_AGMT_PLN_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnDescTxt_H
        {"SPLY_AGMT_PLN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpCd_H
        {"SPLY_AGMT_DOC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpCd_H
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_H
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_H
        {"ANN_TERM_CAP_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//annTermCapNum_H
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H
        {"SPLY_AGMT_PLN_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnStsNm_H
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H
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

