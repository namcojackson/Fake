//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20181206112439000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010_WCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7010 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010_WCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_SW*/
	public final EZDCStringItem              xxChkBox_SW;

    /** PRC_CATG_NM_SW*/
	public final EZDCStringItem              prcCatgNm_SW;

    /** EFF_FROM_DT_SW*/
	public final EZDCDateItem              effFromDt_SW;

    /** EFF_THRU_DT_SW*/
	public final EZDCDateItem              effThruDt_SW;

    /** SUB_PRC_PRTY_NUM_SW*/
	public final EZDCBigDecimalItem              subPrcPrtyNum_SW;

    /** SUB_PRC_CATG_CD_SW*/
	public final EZDCStringItem              subPrcCatgCd_SW;

    /** DS_PRC_CATG_SUB_PK_SW*/
	public final EZDCBigDecimalItem              dsPrcCatgSubPk_SW;

    /** _EZUpdateDateTime_SW*/
	public final EZDCStringItem              ezUpTime_SW;

    /** _EZUpTimeZone_SW*/
	public final EZDCStringItem              ezUpTimeZone_SW;


	/**
	 * NMAL7010_WCMsg is constructor.
	 * The initialization when the instance of NMAL7010_WCMsg is generated.
	 */
	public NMAL7010_WCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7010_WCMsg is constructor.
	 * The initialization when the instance of NMAL7010_WCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7010_WCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_SW = (EZDCStringItem)newItem("xxChkBox_SW");
		prcCatgNm_SW = (EZDCStringItem)newItem("prcCatgNm_SW");
		effFromDt_SW = (EZDCDateItem)newItem("effFromDt_SW");
		effThruDt_SW = (EZDCDateItem)newItem("effThruDt_SW");
		subPrcPrtyNum_SW = (EZDCBigDecimalItem)newItem("subPrcPrtyNum_SW");
		subPrcCatgCd_SW = (EZDCStringItem)newItem("subPrcCatgCd_SW");
		dsPrcCatgSubPk_SW = (EZDCBigDecimalItem)newItem("dsPrcCatgSubPk_SW");
		ezUpTime_SW = (EZDCStringItem)newItem("ezUpTime_SW");
		ezUpTimeZone_SW = (EZDCStringItem)newItem("ezUpTimeZone_SW");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7010_WCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7010_WCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_SW", "xxChkBox_SW", "SW", null, TYPE_HANKAKUEIJI, "1", null},
	{"prcCatgNm_SW", "prcCatgNm_SW", "SW", null, TYPE_HANKAKUEISU, "75", null},
	{"effFromDt_SW", "effFromDt_SW", "SW", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_SW", "effThruDt_SW", "SW", null, TYPE_NENTSUKIHI, "8", null},
	{"subPrcPrtyNum_SW", "subPrcPrtyNum_SW", "SW", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"subPrcCatgCd_SW", "subPrcCatgCd_SW", "SW", null, TYPE_HANKAKUEISU, "10", null},
	{"dsPrcCatgSubPk_SW", "dsPrcCatgSubPk_SW", "SW", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_SW", "ezUpTime_SW", "SW", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_SW", "ezUpTimeZone_SW", "SW", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SW
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_SW
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_SW
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_SW
        {"SUB_PRC_PRTY_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//subPrcPrtyNum_SW
        {"SUB_PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//subPrcCatgCd_SW
        {"DS_PRC_CATG_SUB_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrcCatgSubPk_SW
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_SW
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_SW
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

