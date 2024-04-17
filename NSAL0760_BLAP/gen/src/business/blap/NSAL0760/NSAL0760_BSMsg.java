//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230511190706000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0760_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0760;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0760 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0760_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_A1*/
	public final EZDSBigDecimalItem              xxRowNum_A1;

    /** XX_REF_CSE_NUM*/
	public final EZDSBigDecimalItem              xxRefCseNum;

    /** DS_CONTR_TRK_TP_CD*/
	public final EZDSStringItem              dsContrTrkTpCd;

    /** DS_CONTR_NUM*/
	public final EZDSStringItem              dsContrNum;

    /** SER_NUM*/
	public final EZDSStringItem              serNum;

    /** MDSE_DESC_SHORT_TXT_AS*/
	public final EZDSStringItem              mdseDescShortTxt_AS;

    /** DS_CONTR_DTL_PK*/
	public final EZDSBigDecimalItem              dsContrDtlPk;

    /** DS_CONTR_BLLG_MTR_PK*/
	public final EZDSBigDecimalItem              dsContrBllgMtrPk;

    /** XX_BTN_FLG_A1*/
	public final EZDSStringItem              xxBtnFlg_A1;

    /** XX_DPLY_BY_CD_NM_CNCT_TXT*/
	public final EZDSStringItem              xxDplyByCdNmCnctTxt;

    /** XX_BTN_FLG_A2*/
	public final EZDSStringItem              xxBtnFlg_A2;

    /** DS_CONTR_PRC_EFF_SQ_NUM*/
	public final EZDSBigDecimalItem              dsContrPrcEffSqNum;

    /** XX_BTN_FLG_A3*/
	public final EZDSStringItem              xxBtnFlg_A3;

    /** XX_FROM_DT*/
	public final EZDSDateItem              xxFromDt;

    /** XX_THRU_DT*/
	public final EZDSDateItem              xxThruDt;

    /** CONTR_CLO_DT*/
	public final EZDSDateItem              contrCloDt;

    /** DS_CONTR_STS_DESC_TXT*/
	public final EZDSStringItem              dsContrStsDescTxt;


	/**
	 * NSAL0760_BSMsg is constructor.
	 * The initialization when the instance of NSAL0760_BSMsg is generated.
	 */
	public NSAL0760_BSMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0760_BSMsg is constructor.
	 * The initialization when the instance of NSAL0760_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0760_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_A1 = (EZDSBigDecimalItem)newItem("xxRowNum_A1");
		xxRefCseNum = (EZDSBigDecimalItem)newItem("xxRefCseNum");
		dsContrTrkTpCd = (EZDSStringItem)newItem("dsContrTrkTpCd");
		dsContrNum = (EZDSStringItem)newItem("dsContrNum");
		serNum = (EZDSStringItem)newItem("serNum");
		mdseDescShortTxt_AS = (EZDSStringItem)newItem("mdseDescShortTxt_AS");
		dsContrDtlPk = (EZDSBigDecimalItem)newItem("dsContrDtlPk");
		dsContrBllgMtrPk = (EZDSBigDecimalItem)newItem("dsContrBllgMtrPk");
		xxBtnFlg_A1 = (EZDSStringItem)newItem("xxBtnFlg_A1");
		xxDplyByCdNmCnctTxt = (EZDSStringItem)newItem("xxDplyByCdNmCnctTxt");
		xxBtnFlg_A2 = (EZDSStringItem)newItem("xxBtnFlg_A2");
		dsContrPrcEffSqNum = (EZDSBigDecimalItem)newItem("dsContrPrcEffSqNum");
		xxBtnFlg_A3 = (EZDSStringItem)newItem("xxBtnFlg_A3");
		xxFromDt = (EZDSDateItem)newItem("xxFromDt");
		xxThruDt = (EZDSDateItem)newItem("xxThruDt");
		contrCloDt = (EZDSDateItem)newItem("contrCloDt");
		dsContrStsDescTxt = (EZDSStringItem)newItem("dsContrStsDescTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0760_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0760_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_A1", "xxRowNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRefCseNum", "xxRefCseNum", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"dsContrTrkTpCd", "dsContrTrkTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"mdseDescShortTxt_AS", "mdseDescShortTxt_AS", "AS", null, TYPE_HANKAKUEISU, "250", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk", "dsContrBllgMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxBtnFlg_A1", "xxBtnFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyByCdNmCnctTxt", "xxDplyByCdNmCnctTxt", null, null, TYPE_HANKAKUEISU, "70", null},
	{"xxBtnFlg_A2", "xxBtnFlg_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrPrcEffSqNum", "dsContrPrcEffSqNum", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxBtnFlg_A3", "xxBtnFlg_A3", "A3", null, TYPE_HANKAKUEISU, "1", null},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxThruDt", "xxThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"contrCloDt", "contrCloDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrStsDescTxt", "dsContrStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A1
        {"XX_REF_CSE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRefCseNum
        {"DS_CONTR_TRK_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrTrkTpCd
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_AS
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_A1
        {"XX_DPLY_BY_CD_NM_CNCT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCdNmCnctTxt
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_A2
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_A3
        {"XX_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFromDt
        {"XX_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxThruDt
        {"CONTR_CLO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCloDt
        {"DS_CONTR_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsDescTxt
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

