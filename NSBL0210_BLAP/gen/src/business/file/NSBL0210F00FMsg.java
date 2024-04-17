//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160511172856000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0210F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSBL0210F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0210F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDL_GRP_NM*/
	public final EZDFStringItem              mdlGrpNm;

    /** MDL_GRP_DESC_TXT*/
	public final EZDFStringItem              mdlGrpDescTxt;

    /** SVC_LINE_BIZ_CD*/
	public final EZDFStringItem              svcLineBizCd;

    /** SVC_PRC_TRVL_CHRG_FLG*/
	public final EZDFStringItem              svcPrcTrvlChrgFlg;

    /** SVC_PRC_SHIFT_NUM*/
	public final EZDFStringItem              svcPrcShiftNum;

    /** SVC_PRC_SHIFT_DESC_TXT*/
	public final EZDFStringItem              svcPrcShiftDescTxt;

    /** SVC_LBOR_UNIT_AMT*/
	public final EZDFBigDecimalItem              svcLborUnitAmt;

    /** SVC_MIN_CHRG_HRS_AOT*/
	public final EZDFBigDecimalItem              svcMinChrgHrsAot;

    /** INTG_MDSE_CD*/
	public final EZDFStringItem              intgMdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDFStringItem              mdseDescShortTxt;


	/**
	 * NSBL0210F00FMsg is constructor.
	 * The initialization when the instance of NSBL0210F00FMsg is generated.
	 */
	public NSBL0210F00FMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0210F00FMsg is constructor.
	 * The initialization when the instance of NSBL0210F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0210F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdlGrpNm = (EZDFStringItem)newItem("mdlGrpNm");
		mdlGrpDescTxt = (EZDFStringItem)newItem("mdlGrpDescTxt");
		svcLineBizCd = (EZDFStringItem)newItem("svcLineBizCd");
		svcPrcTrvlChrgFlg = (EZDFStringItem)newItem("svcPrcTrvlChrgFlg");
		svcPrcShiftNum = (EZDFStringItem)newItem("svcPrcShiftNum");
		svcPrcShiftDescTxt = (EZDFStringItem)newItem("svcPrcShiftDescTxt");
		svcLborUnitAmt = (EZDFBigDecimalItem)newItem("svcLborUnitAmt");
		svcMinChrgHrsAot = (EZDFBigDecimalItem)newItem("svcMinChrgHrsAot");
		intgMdseCd = (EZDFStringItem)newItem("intgMdseCd");
		mdseDescShortTxt = (EZDFStringItem)newItem("mdseDescShortTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0210F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0210F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdlGrpNm", "mdlGrpNm", null, null, TYPE_HANKAKUEISU, "20", null},
	{"mdlGrpDescTxt", "mdlGrpDescTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"svcLineBizCd", "svcLineBizCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"svcPrcTrvlChrgFlg", "svcPrcTrvlChrgFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"svcPrcShiftNum", "svcPrcShiftNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"svcPrcShiftDescTxt", "svcPrcShiftDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcLborUnitAmt", "svcLborUnitAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"svcMinChrgHrsAot", "svcMinChrgHrsAot", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"intgMdseCd", "intgMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDL_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpNm
        {"MDL_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpDescTxt
        {"SVC_LINE_BIZ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd
        {"SVC_PRC_TRVL_CHRG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPrcTrvlChrgFlg
        {"SVC_PRC_SHIFT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPrcShiftNum
        {"SVC_PRC_SHIFT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPrcShiftDescTxt
        {"SVC_LBOR_UNIT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLborUnitAmt
        {"SVC_MIN_CHRG_HRS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMinChrgHrsAot
        {"INTG_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intgMdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
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

