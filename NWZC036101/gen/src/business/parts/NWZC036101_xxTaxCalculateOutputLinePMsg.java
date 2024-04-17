//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150424085711000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC036101_xxTaxCalculateOutputLinePMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC036101 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC036101_xxTaxCalculateOutputLinePMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_TAX_CALC_LINE_TAX_PCT*/
	public final EZDPBigDecimalItem              xxTaxCalcLineTaxPct;

    /** INV_LINE_FUNC_TAX_AMT*/
	public final EZDPBigDecimalItem              invLineFuncTaxAmt;

    /** TAX_PCT_01*/
	public final EZDPBigDecimalItem              taxPct_01;

    /** TAX_AMT_01*/
	public final EZDPBigDecimalItem              taxAmt_01;

    /** XX_VTX_TAX_RSLT_CD_01*/
	public final EZDPStringItem              xxVtxTaxRsltCd_01;

    /** TAX_PCT_02*/
	public final EZDPBigDecimalItem              taxPct_02;

    /** TAX_AMT_02*/
	public final EZDPBigDecimalItem              taxAmt_02;

    /** XX_VTX_TAX_RSLT_CD_02*/
	public final EZDPStringItem              xxVtxTaxRsltCd_02;

    /** TAX_PCT_03*/
	public final EZDPBigDecimalItem              taxPct_03;

    /** TAX_AMT_03*/
	public final EZDPBigDecimalItem              taxAmt_03;

    /** XX_VTX_TAX_RSLT_CD_03*/
	public final EZDPStringItem              xxVtxTaxRsltCd_03;

    /** TAX_PCT_04*/
	public final EZDPBigDecimalItem              taxPct_04;

    /** TAX_AMT_04*/
	public final EZDPBigDecimalItem              taxAmt_04;

    /** XX_VTX_TAX_RSLT_CD_04*/
	public final EZDPStringItem              xxVtxTaxRsltCd_04;

    /** TAX_PCT_05*/
	public final EZDPBigDecimalItem              taxPct_05;

    /** TAX_AMT_05*/
	public final EZDPBigDecimalItem              taxAmt_05;

    /** XX_VTX_TAX_RSLT_CD_05*/
	public final EZDPStringItem              xxVtxTaxRsltCd_05;

    /** TAX_PCT_06*/
	public final EZDPBigDecimalItem              taxPct_06;

    /** TAX_AMT_06*/
	public final EZDPBigDecimalItem              taxAmt_06;

    /** XX_VTX_TAX_RSLT_CD_06*/
	public final EZDPStringItem              xxVtxTaxRsltCd_06;

    /** VTX_GEO_CD*/
	public final EZDPStringItem              vtxGeoCd;


	/**
	 * NWZC036101_xxTaxCalculateOutputLinePMsg is constructor.
	 * The initialization when the instance of NWZC036101_xxTaxCalculateOutputLinePMsg is generated.
	 */
	public NWZC036101_xxTaxCalculateOutputLinePMsg() {
		this(false, -1);
	}

	/**
	 * NWZC036101_xxTaxCalculateOutputLinePMsg is constructor.
	 * The initialization when the instance of NWZC036101_xxTaxCalculateOutputLinePMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC036101_xxTaxCalculateOutputLinePMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxTaxCalcLineTaxPct = (EZDPBigDecimalItem)newItem("xxTaxCalcLineTaxPct");
		invLineFuncTaxAmt = (EZDPBigDecimalItem)newItem("invLineFuncTaxAmt");
		taxPct_01 = (EZDPBigDecimalItem)newItem("taxPct_01");
		taxAmt_01 = (EZDPBigDecimalItem)newItem("taxAmt_01");
		xxVtxTaxRsltCd_01 = (EZDPStringItem)newItem("xxVtxTaxRsltCd_01");
		taxPct_02 = (EZDPBigDecimalItem)newItem("taxPct_02");
		taxAmt_02 = (EZDPBigDecimalItem)newItem("taxAmt_02");
		xxVtxTaxRsltCd_02 = (EZDPStringItem)newItem("xxVtxTaxRsltCd_02");
		taxPct_03 = (EZDPBigDecimalItem)newItem("taxPct_03");
		taxAmt_03 = (EZDPBigDecimalItem)newItem("taxAmt_03");
		xxVtxTaxRsltCd_03 = (EZDPStringItem)newItem("xxVtxTaxRsltCd_03");
		taxPct_04 = (EZDPBigDecimalItem)newItem("taxPct_04");
		taxAmt_04 = (EZDPBigDecimalItem)newItem("taxAmt_04");
		xxVtxTaxRsltCd_04 = (EZDPStringItem)newItem("xxVtxTaxRsltCd_04");
		taxPct_05 = (EZDPBigDecimalItem)newItem("taxPct_05");
		taxAmt_05 = (EZDPBigDecimalItem)newItem("taxAmt_05");
		xxVtxTaxRsltCd_05 = (EZDPStringItem)newItem("xxVtxTaxRsltCd_05");
		taxPct_06 = (EZDPBigDecimalItem)newItem("taxPct_06");
		taxAmt_06 = (EZDPBigDecimalItem)newItem("taxAmt_06");
		xxVtxTaxRsltCd_06 = (EZDPStringItem)newItem("xxVtxTaxRsltCd_06");
		vtxGeoCd = (EZDPStringItem)newItem("vtxGeoCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC036101_xxTaxCalculateOutputLinePMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC036101_xxTaxCalculateOutputLinePMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxTaxCalcLineTaxPct", "xxTaxCalcLineTaxPct", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"invLineFuncTaxAmt", "invLineFuncTaxAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"taxPct_01", "taxPct_01", "01", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"taxAmt_01", "taxAmt_01", "01", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxVtxTaxRsltCd_01", "xxVtxTaxRsltCd_01", "01", null, TYPE_HANKAKUEISU, "20", null},
	{"taxPct_02", "taxPct_02", "02", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"taxAmt_02", "taxAmt_02", "02", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxVtxTaxRsltCd_02", "xxVtxTaxRsltCd_02", "02", null, TYPE_HANKAKUEISU, "20", null},
	{"taxPct_03", "taxPct_03", "03", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"taxAmt_03", "taxAmt_03", "03", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxVtxTaxRsltCd_03", "xxVtxTaxRsltCd_03", "03", null, TYPE_HANKAKUEISU, "20", null},
	{"taxPct_04", "taxPct_04", "04", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"taxAmt_04", "taxAmt_04", "04", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxVtxTaxRsltCd_04", "xxVtxTaxRsltCd_04", "04", null, TYPE_HANKAKUEISU, "20", null},
	{"taxPct_05", "taxPct_05", "05", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"taxAmt_05", "taxAmt_05", "05", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxVtxTaxRsltCd_05", "xxVtxTaxRsltCd_05", "05", null, TYPE_HANKAKUEISU, "20", null},
	{"taxPct_06", "taxPct_06", "06", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"taxAmt_06", "taxAmt_06", "06", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxVtxTaxRsltCd_06", "xxVtxTaxRsltCd_06", "06", null, TYPE_HANKAKUEISU, "20", null},
	{"vtxGeoCd", "vtxGeoCd", null, null, TYPE_HANKAKUEISU, "9", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_TAX_CALC_LINE_TAX_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTaxCalcLineTaxPct
        {"INV_LINE_FUNC_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineFuncTaxAmt
        {"TAX_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPct_01
        {"TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxAmt_01
        {"XX_VTX_TAX_RSLT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxVtxTaxRsltCd_01
        {"TAX_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPct_02
        {"TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxAmt_02
        {"XX_VTX_TAX_RSLT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxVtxTaxRsltCd_02
        {"TAX_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPct_03
        {"TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxAmt_03
        {"XX_VTX_TAX_RSLT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxVtxTaxRsltCd_03
        {"TAX_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPct_04
        {"TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxAmt_04
        {"XX_VTX_TAX_RSLT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxVtxTaxRsltCd_04
        {"TAX_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPct_05
        {"TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxAmt_05
        {"XX_VTX_TAX_RSLT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxVtxTaxRsltCd_05
        {"TAX_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPct_06
        {"TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxAmt_06
        {"XX_VTX_TAX_RSLT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxVtxTaxRsltCd_06
        {"VTX_GEO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vtxGeoCd
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
