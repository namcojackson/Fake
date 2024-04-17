//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160517130545000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0860F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFCL0860F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL0860F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDFStringItem              xxChkBox_A;

    /** AR_TRX_TP_DESC_TXT_A*/
	public final EZDFStringItem              arTrxTpDescTxt_A;

    /** AR_ADJ_TP_DESC_TXT_A*/
	public final EZDFStringItem              arAdjTpDescTxt_A;

    /** AR_TRX_NUM_A*/
	public final EZDFStringItem              arTrxNum_A;

    /** XX_DT_TXT_AT*/
	public final EZDFStringItem              xxDtTxt_AT;

    /** DEAL_ORIG_GRS_AMT_A*/
	public final EZDFBigDecimalItem              dealOrigGrsAmt_A;

    /** DEAL_APPLY_AMT_A*/
	public final EZDFBigDecimalItem              dealApplyAmt_A;

    /** XX_DT_TXT_CA*/
	public final EZDFStringItem              xxDtTxt_CA;

    /** XX_DT_TXT_GL*/
	public final EZDFStringItem              xxDtTxt_GL;

    /** AR_ADJ_STS_NM_A*/
	public final EZDFStringItem              arAdjStsNm_A;

    /** ADJ_CMNT_TXT_A*/
	public final EZDFStringItem              adjCmntTxt_A;


	/**
	 * NFCL0860F00FMsg is constructor.
	 * The initialization when the instance of NFCL0860F00FMsg is generated.
	 */
	public NFCL0860F00FMsg() {
		this(false, -1);
	}

	/**
	 * NFCL0860F00FMsg is constructor.
	 * The initialization when the instance of NFCL0860F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL0860F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDFStringItem)newItem("xxChkBox_A");
		arTrxTpDescTxt_A = (EZDFStringItem)newItem("arTrxTpDescTxt_A");
		arAdjTpDescTxt_A = (EZDFStringItem)newItem("arAdjTpDescTxt_A");
		arTrxNum_A = (EZDFStringItem)newItem("arTrxNum_A");
		xxDtTxt_AT = (EZDFStringItem)newItem("xxDtTxt_AT");
		dealOrigGrsAmt_A = (EZDFBigDecimalItem)newItem("dealOrigGrsAmt_A");
		dealApplyAmt_A = (EZDFBigDecimalItem)newItem("dealApplyAmt_A");
		xxDtTxt_CA = (EZDFStringItem)newItem("xxDtTxt_CA");
		xxDtTxt_GL = (EZDFStringItem)newItem("xxDtTxt_GL");
		arAdjStsNm_A = (EZDFStringItem)newItem("arAdjStsNm_A");
		adjCmntTxt_A = (EZDFStringItem)newItem("adjCmntTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL0860F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL0860F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"arTrxTpDescTxt_A", "arTrxTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"arAdjTpDescTxt_A", "arAdjTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"arTrxNum_A", "arTrxNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"xxDtTxt_AT", "xxDtTxt_AT", "AT", null, TYPE_HANKAKUEISU, "10", null},
	{"dealOrigGrsAmt_A", "dealOrigGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealApplyAmt_A", "dealApplyAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxDtTxt_CA", "xxDtTxt_CA", "CA", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_GL", "xxDtTxt_GL", "GL", null, TYPE_HANKAKUEISU, "10", null},
	{"arAdjStsNm_A", "arAdjStsNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"adjCmntTxt_A", "adjCmntTxt_A", "A", null, TYPE_HANKAKUEISU, "65", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"AR_TRX_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpDescTxt_A
        {"AR_ADJ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpDescTxt_A
        {"AR_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_AT
        {"DEAL_ORIG_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealOrigGrsAmt_A
        {"DEAL_APPLY_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAmt_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_CA
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_GL
        {"AR_ADJ_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjStsNm_A
        {"ADJ_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCmntTxt_A
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

