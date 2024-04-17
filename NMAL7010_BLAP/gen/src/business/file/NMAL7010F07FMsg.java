//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20151013110327000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010F07FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL7010F07 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010F07FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_LEASE_CMPY_ABBR_NM*/
	public final EZDFStringItem              prcLeaseCmpyAbbrNm;

    /** SVC_SEG_NM*/
	public final EZDFStringItem              svcSegNm;

    /** PRC_IN_OUT_RG_NM*/
	public final EZDFStringItem              prcInOutRgNm;

    /** DST_MILE_AMT*/
	public final EZDFBigDecimalItem              dstMileAmt;

    /** RTRN_FEE_AMT*/
	public final EZDFBigDecimalItem              rtrnFeeAmt;

    /** EFF_FROM_DT*/
	public final EZDFDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDFDateItem              effThruDt;


	/**
	 * NMAL7010F07FMsg is constructor.
	 * The initialization when the instance of NMAL7010F07FMsg is generated.
	 */
	public NMAL7010F07FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7010F07FMsg is constructor.
	 * The initialization when the instance of NMAL7010F07FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7010F07FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcLeaseCmpyAbbrNm = (EZDFStringItem)newItem("prcLeaseCmpyAbbrNm");
		svcSegNm = (EZDFStringItem)newItem("svcSegNm");
		prcInOutRgNm = (EZDFStringItem)newItem("prcInOutRgNm");
		dstMileAmt = (EZDFBigDecimalItem)newItem("dstMileAmt");
		rtrnFeeAmt = (EZDFBigDecimalItem)newItem("rtrnFeeAmt");
		effFromDt = (EZDFDateItem)newItem("effFromDt");
		effThruDt = (EZDFDateItem)newItem("effThruDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7010F07FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7010F07FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcLeaseCmpyAbbrNm", "prcLeaseCmpyAbbrNm", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcSegNm", "svcSegNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"prcInOutRgNm", "prcInOutRgNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dstMileAmt", "dstMileAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rtrnFeeAmt", "rtrnFeeAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_LEASE_CMPY_ABBR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcLeaseCmpyAbbrNm
        {"SVC_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSegNm
        {"PRC_IN_OUT_RG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcInOutRgNm
        {"DST_MILE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dstMileAmt
        {"RTRN_FEE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnFeeAmt
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
