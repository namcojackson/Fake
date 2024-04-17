//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160916132223000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0430_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0430;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0430 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0430_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_PHYS_MTR_PK_A*/
	public final EZDCBigDecimalItem              svcPhysMtrPk_A;

    /** MTR_LB_CD_A*/
	public final EZDCStringItem              mtrLbCd_A;

    /** MTR_LB_DESC_TXT_A*/
	public final EZDCStringItem              mtrLbDescTxt_A;

    /** XX_COPY_UNIT_DAYS_QTY_AH*/
	public final EZDCBigDecimalItem              xxCopyUnitDaysQty_AH;

    /** XX_COPY_UNIT_DAYS_QTY_AE*/
	public final EZDCBigDecimalItem              xxCopyUnitDaysQty_AE;

    /** READ_MTR_CNT_AE*/
	public final EZDCBigDecimalItem              readMtrCnt_AE;


	/**
	 * NSAL0430_ACMsg is constructor.
	 * The initialization when the instance of NSAL0430_ACMsg is generated.
	 */
	public NSAL0430_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0430_ACMsg is constructor.
	 * The initialization when the instance of NSAL0430_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0430_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcPhysMtrPk_A = (EZDCBigDecimalItem)newItem("svcPhysMtrPk_A");
		mtrLbCd_A = (EZDCStringItem)newItem("mtrLbCd_A");
		mtrLbDescTxt_A = (EZDCStringItem)newItem("mtrLbDescTxt_A");
		xxCopyUnitDaysQty_AH = (EZDCBigDecimalItem)newItem("xxCopyUnitDaysQty_AH");
		xxCopyUnitDaysQty_AE = (EZDCBigDecimalItem)newItem("xxCopyUnitDaysQty_AE");
		readMtrCnt_AE = (EZDCBigDecimalItem)newItem("readMtrCnt_AE");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0430_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0430_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcPhysMtrPk_A", "svcPhysMtrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbCd_A", "mtrLbCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_A", "mtrLbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxCopyUnitDaysQty_AH", "xxCopyUnitDaysQty_AH", "AH", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxCopyUnitDaysQty_AE", "xxCopyUnitDaysQty_AE", "AE", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"readMtrCnt_AE", "readMtrCnt_AE", "AE", null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_PHYS_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrPk_A
        {"MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_A
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_A
        {"XX_COPY_UNIT_DAYS_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCopyUnitDaysQty_AH
        {"XX_COPY_UNIT_DAYS_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCopyUnitDaysQty_AE
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt_AE
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

