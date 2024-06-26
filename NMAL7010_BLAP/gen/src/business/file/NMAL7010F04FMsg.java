//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20151013092052000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010F04FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL7010F04 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010F04FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** PRC_ADDL_CHRG_TP_NM*/
	public final EZDFStringItem              prcAddlChrgTpNm;

    /** MKT_MDL_SEG_NM*/
	public final EZDFStringItem              mktMdlSegNm;

    /** MDL_NM*/
	public final EZDFStringItem              mdlNm;

    /** ADDL_CHRG_PRC_AMT*/
	public final EZDFBigDecimalItem              addlChrgPrcAmt;

    /** EFF_FROM_DT*/
	public final EZDFDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDFDateItem              effThruDt;


	/**
	 * NMAL7010F04FMsg is constructor.
	 * The initialization when the instance of NMAL7010F04FMsg is generated.
	 */
	public NMAL7010F04FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7010F04FMsg is constructor.
	 * The initialization when the instance of NMAL7010F04FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7010F04FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDFStringItem)newItem("mdseCd");
		prcAddlChrgTpNm = (EZDFStringItem)newItem("prcAddlChrgTpNm");
		mktMdlSegNm = (EZDFStringItem)newItem("mktMdlSegNm");
		mdlNm = (EZDFStringItem)newItem("mdlNm");
		addlChrgPrcAmt = (EZDFBigDecimalItem)newItem("addlChrgPrcAmt");
		effFromDt = (EZDFDateItem)newItem("effFromDt");
		effThruDt = (EZDFDateItem)newItem("effThruDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7010F04FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7010F04FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"prcAddlChrgTpNm", "prcAddlChrgTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"mktMdlSegNm", "mktMdlSegNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdlNm", "mdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"addlChrgPrcAmt", "addlChrgPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"PRC_ADDL_CHRG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAddlChrgTpNm
        {"MKT_MDL_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlSegNm
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm
        {"ADDL_CHRG_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgPrcAmt
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
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

