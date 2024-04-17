//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20171226042337000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC047002PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC047002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC047002PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** BASE_CHRG_FLG*/
	public final EZDPStringItem              baseChrgFlg;

    /** USG_CHRG_FLG*/
	public final EZDPStringItem              usgChrgFlg;

    /** CONTR_CLO_DAY*/
	public final EZDPStringItem              contrCloDay;

    /** BASE_BLLG_TMG_CD*/
	public final EZDPStringItem              baseBllgTmgCd;

    /** CONTR_BLLG_DAY*/
	public final EZDPStringItem              contrBllgDay;

    /** MTR_CLO_DAY*/
	public final EZDPStringItem              mtrCloDay;

    /** MTR_BLLG_TMG_CD*/
	public final EZDPStringItem              mtrBllgTmgCd;

    /** MTR_BLLG_DAY*/
	public final EZDPStringItem              mtrBllgDay;

    /** CONTR_EFF_FROM_DT*/
	public final EZDPDateItem              contrEffFromDt;

    /** CONTR_EFF_THRU_DT*/
	public final EZDPDateItem              contrEffThruDt;

    /** CHNG_RNG_FLG*/
	public final EZDPStringItem              chngRngFlg;

    /** xxBaseLineList*/
	public final business.parts.NSZC047002_xxBaseLineListPMsgArray              xxBaseLineList;

    /** xxMtrLineList*/
	public final business.parts.NSZC047002_xxMtrLineListPMsgArray              xxMtrLineList;

    /** xxMsgIdList*/
	public final business.parts.NSZC047002_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC047002PMsg is constructor.
	 * The initialization when the instance of NSZC047002PMsg is generated.
	 */
	public NSZC047002PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC047002PMsg is constructor.
	 * The initialization when the instance of NSZC047002PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC047002PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		baseChrgFlg = (EZDPStringItem)newItem("baseChrgFlg");
		usgChrgFlg = (EZDPStringItem)newItem("usgChrgFlg");
		contrCloDay = (EZDPStringItem)newItem("contrCloDay");
		baseBllgTmgCd = (EZDPStringItem)newItem("baseBllgTmgCd");
		contrBllgDay = (EZDPStringItem)newItem("contrBllgDay");
		mtrCloDay = (EZDPStringItem)newItem("mtrCloDay");
		mtrBllgTmgCd = (EZDPStringItem)newItem("mtrBllgTmgCd");
		mtrBllgDay = (EZDPStringItem)newItem("mtrBllgDay");
		contrEffFromDt = (EZDPDateItem)newItem("contrEffFromDt");
		contrEffThruDt = (EZDPDateItem)newItem("contrEffThruDt");
		chngRngFlg = (EZDPStringItem)newItem("chngRngFlg");
		xxBaseLineList = (business.parts.NSZC047002_xxBaseLineListPMsgArray)newMsgArray("xxBaseLineList");
		xxMtrLineList = (business.parts.NSZC047002_xxMtrLineListPMsgArray)newMsgArray("xxMtrLineList");
		xxMsgIdList = (business.parts.NSZC047002_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC047002PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC047002PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"baseChrgFlg", "baseChrgFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"usgChrgFlg", "usgChrgFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"contrCloDay", "contrCloDay", null, null, TYPE_HANKAKUSUJI, "2", null},
	{"baseBllgTmgCd", "baseBllgTmgCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"contrBllgDay", "contrBllgDay", null, null, TYPE_HANKAKUEISU, "4", null},
	{"mtrCloDay", "mtrCloDay", null, null, TYPE_HANKAKUSUJI, "2", null},
	{"mtrBllgTmgCd", "mtrBllgTmgCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mtrBllgDay", "mtrBllgDay", null, null, TYPE_HANKAKUEISU, "4", null},
	{"contrEffFromDt", "contrEffFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"contrEffThruDt", "contrEffThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"chngRngFlg", "chngRngFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxBaseLineList", "xxBaseLineList", null, "100", "business.parts.NSZC047002_xxBaseLineListPMsgArray", null, null},
	
	{"xxMtrLineList", "xxMtrLineList", null, "100", "business.parts.NSZC047002_xxMtrLineListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NSZC047002_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"BASE_CHRG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseChrgFlg
        {"USG_CHRG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usgChrgFlg
        {"CONTR_CLO_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCloDay
        {"BASE_BLLG_TMG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgTmgCd
        {"CONTR_BLLG_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrBllgDay
        {"MTR_CLO_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrCloDay
        {"MTR_BLLG_TMG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrBllgTmgCd
        {"MTR_BLLG_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrBllgDay
        {"CONTR_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffFromDt
        {"CONTR_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffThruDt
        {"CHNG_RNG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chngRngFlg
		null,	//xxBaseLineList
		null,	//xxMtrLineList
		null,	//xxMsgIdList
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

