//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170203075439000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0300F01FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0300F01 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0300F01FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_NUM*/
	public final EZDFStringItem              dsContrNum;

    /** SQ_ID*/
	public final EZDFStringItem              sqId;

    /** SVC_MACH_MSTR_PK*/
	public final EZDFBigDecimalItem              svcMachMstrPk;

    /** SER_NUM*/
	public final EZDFStringItem              serNum;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDFBigDecimalItem              svcConfigMstrPk;

    /** DS_CONTR_CTRL_STS_NM*/
	public final EZDFStringItem              dsContrCtrlStsNm;

    /** BILL_TO_CUST_LOC_ADDR*/
	public final EZDFStringItem              billToCustLocAddr;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDFStringItem              mdseDescShortTxt;

    /** XX_DT_TXT_SD*/
	public final EZDFStringItem              xxDtTxt_SD;

    /** XX_DT_TXT_ED*/
	public final EZDFStringItem              xxDtTxt_ED;

    /** BLLG_CYCLE_DESC_TXT*/
	public final EZDFStringItem              bllgCycleDescTxt;

    /** BASE_PRC_DEAL_AMT*/
	public final EZDFBigDecimalItem              basePrcDealAmt;

    /** MTR_READ_METH_DESC_TXT*/
	public final EZDFStringItem              mtrReadMethDescTxt;

    /** XX_DT_TXT_RD*/
	public final EZDFStringItem              xxDtTxt_RD;

    /** CONTR_RNW_TOT_CNT*/
	public final EZDFBigDecimalItem              contrRnwTotCnt;

    /** XX_DT_TXT_CD*/
	public final EZDFStringItem              xxDtTxt_CD;


	/**
	 * NSAL0300F01FMsg is constructor.
	 * The initialization when the instance of NSAL0300F01FMsg is generated.
	 */
	public NSAL0300F01FMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0300F01FMsg is constructor.
	 * The initialization when the instance of NSAL0300F01FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0300F01FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrNum = (EZDFStringItem)newItem("dsContrNum");
		sqId = (EZDFStringItem)newItem("sqId");
		svcMachMstrPk = (EZDFBigDecimalItem)newItem("svcMachMstrPk");
		serNum = (EZDFStringItem)newItem("serNum");
		svcConfigMstrPk = (EZDFBigDecimalItem)newItem("svcConfigMstrPk");
		dsContrCtrlStsNm = (EZDFStringItem)newItem("dsContrCtrlStsNm");
		billToCustLocAddr = (EZDFStringItem)newItem("billToCustLocAddr");
		mdseDescShortTxt = (EZDFStringItem)newItem("mdseDescShortTxt");
		xxDtTxt_SD = (EZDFStringItem)newItem("xxDtTxt_SD");
		xxDtTxt_ED = (EZDFStringItem)newItem("xxDtTxt_ED");
		bllgCycleDescTxt = (EZDFStringItem)newItem("bllgCycleDescTxt");
		basePrcDealAmt = (EZDFBigDecimalItem)newItem("basePrcDealAmt");
		mtrReadMethDescTxt = (EZDFStringItem)newItem("mtrReadMethDescTxt");
		xxDtTxt_RD = (EZDFStringItem)newItem("xxDtTxt_RD");
		contrRnwTotCnt = (EZDFBigDecimalItem)newItem("contrRnwTotCnt");
		xxDtTxt_CD = (EZDFStringItem)newItem("xxDtTxt_CD");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0300F01FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0300F01FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"sqId", "sqId", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrCtrlStsNm", "dsContrCtrlStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"billToCustLocAddr", "billToCustLocAddr", null, null, TYPE_HANKAKUEISU, "300", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"xxDtTxt_SD", "xxDtTxt_SD", "SD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_ED", "xxDtTxt_ED", "ED", null, TYPE_HANKAKUEISU, "10", null},
	{"bllgCycleDescTxt", "bllgCycleDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"basePrcDealAmt", "basePrcDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mtrReadMethDescTxt", "mtrReadMethDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTxt_RD", "xxDtTxt_RD", "RD", null, TYPE_HANKAKUEISU, "10", null},
	{"contrRnwTotCnt", "contrRnwTotCnt", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"xxDtTxt_CD", "xxDtTxt_CD", "CD", null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"SQ_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sqId
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm
        {"BILL_TO_CUST_LOC_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocAddr
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_SD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_ED
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt
        {"MTR_READ_METH_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethDescTxt
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_RD
        {"CONTR_RNW_TOT_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrRnwTotCnt
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_CD
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
