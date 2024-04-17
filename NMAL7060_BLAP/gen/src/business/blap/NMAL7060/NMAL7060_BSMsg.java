//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180821154635000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7060_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7060 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7060_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_MTR_PKG_BLLG_MTR_PK_B1*/
	public final EZDSBigDecimalItem              prcMtrPkgBllgMtrPk_B1;

    /** MTR_LB_CD_B1*/
	public final EZDSStringItem              mtrLbCd_B1;

    /** BLLG_MTR_LVL_NUM_B1*/
	public final EZDSStringItem              bllgMtrLvlNum_B1;

    /** MTR_LB_DESC_TXT_B1*/
	public final EZDSStringItem              mtrLbDescTxt_B1;

    /** MTR_LB_NM_B1*/
	public final EZDSStringItem              mtrLbNm_B1;

    /** MDSE_CD_B1*/
	public final EZDSStringItem              mdseCd_B1;

    /** _EZUpdateDateTime_B1*/
	public final EZDSStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDSStringItem              ezUpTimeZone_B1;


	/**
	 * NMAL7060_BSMsg is constructor.
	 * The initialization when the instance of NMAL7060_BSMsg is generated.
	 */
	public NMAL7060_BSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7060_BSMsg is constructor.
	 * The initialization when the instance of NMAL7060_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7060_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcMtrPkgBllgMtrPk_B1 = (EZDSBigDecimalItem)newItem("prcMtrPkgBllgMtrPk_B1");
		mtrLbCd_B1 = (EZDSStringItem)newItem("mtrLbCd_B1");
		bllgMtrLvlNum_B1 = (EZDSStringItem)newItem("bllgMtrLvlNum_B1");
		mtrLbDescTxt_B1 = (EZDSStringItem)newItem("mtrLbDescTxt_B1");
		mtrLbNm_B1 = (EZDSStringItem)newItem("mtrLbNm_B1");
		mdseCd_B1 = (EZDSStringItem)newItem("mdseCd_B1");
		ezUpTime_B1 = (EZDSStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDSStringItem)newItem("ezUpTimeZone_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7060_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7060_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcMtrPkgBllgMtrPk_B1", "prcMtrPkgBllgMtrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbCd_B1", "mtrLbCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgMtrLvlNum_B1", "bllgMtrLvlNum_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_B1", "mtrLbDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"mtrLbNm_B1", "mtrLbNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_MTR_PKG_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgBllgMtrPk_B1
        {"MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_B1
        {"BLLG_MTR_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLvlNum_B1
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_B1
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
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

